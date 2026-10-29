package io.github.alexciouca.skills;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Checks that every plugin and marketplace manifest describes the same plugin. */
class ManifestsTest {

    private static final String PLUGIN = "sloth";
    private static final String MARKETPLACE = "alexciouca-skills";
    private static final String AGENT_PLUGINS_SCHEMA = "https://agent-plugins.org/schemas/1.0.0/plugin.schema.json";

    private static final List<String> PLUGIN_MANIFESTS =
            List.of("plugin.json", ".claude-plugin/plugin.json", ".codex-plugin/plugin.json");

    private static final List<String> MARKETPLACES = List.of(".claude-plugin/marketplace.json",
            ".github/plugin/marketplace.json", ".agents/plugins/marketplace.json");

    @ParameterizedTest
    @ValueSource(strings = {"plugin.json", ".claude-plugin/plugin.json", ".codex-plugin/plugin.json"})
    void pluginManifestNamesTheSamePlugin(String path) {
        assertEquals(PLUGIN, Repo.json(path).path("name").asText(), path);
    }

    @Test
    void pluginManifestsShareOneVersion() {
        String version = Repo.json("plugin.json").path("version").asText();
        assertTrue(version.matches("\\d+\\.\\d+\\.\\d+"), "version must be semantic: " + version);
        for (String path : PLUGIN_MANIFESTS) {
            assertEquals(version, Repo.json(path).path("version").asText(), path);
        }
        // Marketplace entries that state a version must agree with the plugin.
        for (String path : MARKETPLACES) {
            for (JsonNode entry : Repo.json(path).path("plugins")) {
                if (entry.has("version")) {
                    assertEquals(version, entry.path("version").asText(), path);
                }
            }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {".claude-plugin/marketplace.json", ".github/plugin/marketplace.json",
            ".agents/plugins/marketplace.json"})
    void marketplaceListsThePlugin(String path) {
        JsonNode marketplace = Repo.json(path);
        assertEquals(MARKETPLACE, marketplace.path("name").asText(), path);
        JsonNode plugins = marketplace.path("plugins");
        assertEquals(1, plugins.size(), path);
        assertEquals(PLUGIN, plugins.get(0).path("name").asText(), path);
    }

    @Test
    void rootManifestFollowsAgentPlugins() {
        JsonNode root = Repo.json("plugin.json");
        assertEquals(AGENT_PLUGINS_SCHEMA, root.path("$schema").asText());
        // Agent Plugins 1.0 has a closed field list; Copilot reports anything else.
        Set<String> allowed = Set.of("$schema", "name", "version", "description", "author", "homepage",
                "repository", "license", "keywords", "extensions");
        root.fieldNames().forEachRemaining(field -> assertTrue(allowed.contains(field), "not allowed: " + field));
    }

    @Test
    void copilotMarketplaceOwnerUsesAllowedFields() {
        // Copilot validates marketplaces strictly; owner accepts only name and email.
        JsonNode owner = Repo.json(".github/plugin/marketplace.json").path("owner");
        owner.fieldNames().forEachRemaining(field ->
                assertTrue(Set.of("name", "email").contains(field), "owner field not allowed: " + field));
    }

    @Test
    void codexMarketplaceUsesLocalSource() {
        JsonNode source = Repo.json(".agents/plugins/marketplace.json").path("plugins").get(0).path("source");
        assertEquals("local", source.path("source").asText());
        assertEquals("./", source.path("path").asText());
    }

    @Test
    void iconsExist() {
        String claudeIcon = Repo.json(".claude-plugin/plugin.json").path("icon").asText();
        assertTrue(Files.isRegularFile(Repo.ROOT.resolve(claudeIcon)), "missing " + claudeIcon);
        JsonNode codex = Repo.json(".codex-plugin/plugin.json").path("interface");
        for (String key : List.of("logo", "composerIcon")) {
            String path = codex.path(key).asText();
            assertTrue(Files.isRegularFile(Repo.ROOT.resolve(path)), "missing " + path);
        }
    }

    @Test
    void licenseIsDeclaredAndPresent() {
        assertTrue(Files.isRegularFile(Repo.ROOT.resolve("LICENSE")), "LICENSE file is missing");
        for (String path : PLUGIN_MANIFESTS) {
            assertEquals("MIT", Repo.json(path).path("license").asText(), path);
        }
    }

    @Test
    void agentsMdMatchesClaudeMd() {
        // AGENTS.md is a standalone copy of CLAUDE.md for agents that don't read CLAUDE.md.
        assertEquals(Repo.read(Repo.ROOT.resolve("CLAUDE.md")), Repo.read(Repo.ROOT.resolve("AGENTS.md")));
    }
}
