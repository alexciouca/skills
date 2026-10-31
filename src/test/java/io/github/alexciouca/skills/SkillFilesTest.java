package io.github.alexciouca.skills;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.alexciouca.skills.Repo.Skill;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.yaml.snakeyaml.Yaml;

/** Checks every skill against the rules Claude Code, Copilot and Codex enforce when loading it. */
class SkillFilesTest {

    // Agent Skills spec: lowercase letters, digits and single hyphens, at most 64 characters.
    private static final String NAME_PATTERN = "[a-z0-9]+(-[a-z0-9]+)*";

    static Stream<Path> skills() {
        return Repo.skillDirs().stream();
    }

    @Test
    void repoHasTheExpectedSkills() {
        List<String> names = Repo.skillDirs().stream().map(dir -> dir.getFileName().toString()).toList();
        assertEquals(List.of("sloth", "sloth-ask", "sloth-design", "sloth-mini", "sloth-plan", "sloth-review"),
                names);
    }

    @ParameterizedTest
    @MethodSource("skills")
    void referenceFilesAndLinksMatch(Path dir) throws IOException {
        // Every reference/*.md link in SKILL.md points to a file, and every file is linked,
        // so no playbook is missing or orphaned.
        String body = Skill.load(dir).body();
        Set<String> linked = new TreeSet<>();
        Matcher link = Pattern.compile("\\]\\((reference/[^)#\\s]+\\.md)\\)").matcher(body);
        while (link.find()) {
            linked.add(link.group(1));
        }
        Set<String> present = new TreeSet<>();
        Path reference = dir.resolve("reference");
        if (Files.isDirectory(reference)) {
            try (Stream<Path> files = Files.list(reference)) {
                files.filter(f -> f.toString().endsWith(".md"))
                        .forEach(f -> present.add("reference/" + f.getFileName()));
            }
        }
        assertEquals(present, linked, "reference files and SKILL.md links differ");
    }

    @ParameterizedTest
    @MethodSource("skills")
    void nameMatchesFolderAndSpec(Path dir) {
        Skill skill = Skill.load(dir);
        String name = skill.field("name");
        // VS Code and Copilot skip a skill whose name differs from its folder.
        assertEquals(skill.folderName(), name, "name must match the folder");
        assertTrue(name.matches(NAME_PATTERN), "name must be lowercase-hyphenated: " + name);
        assertTrue(name.length() <= 64, "name is longer than 64 characters");
    }

    @ParameterizedTest
    @MethodSource("skills")
    void descriptionIsPresentAndShortEnough(Path dir) {
        String description = Skill.load(dir).field("description");
        assertNotNull(description, "description is missing");
        assertFalse(description.isBlank(), "description is empty");
        assertTrue(description.length() <= 1024, "description is longer than 1024 characters");
    }

    @ParameterizedTest
    @MethodSource("skills")
    void bodyIsNotEmpty(Path dir) {
        assertFalse(Skill.load(dir).body().isBlank(), "SKILL.md has no instructions");
    }

    @ParameterizedTest
    @MethodSource("skills")
    void codexPolicyMatchesClaudeAndCopilotInvocation(Path dir) {
        Skill skill = Skill.load(dir);
        Path policy = dir.resolve("agents/openai.yaml");
        if (skill.commandOnly()) {
            // Codex ignores disable-model-invocation and reads agents/openai.yaml instead.
            assertTrue(Files.isRegularFile(policy), "command-only skill needs agents/openai.yaml for Codex");
            Map<String, Object> yaml = new Yaml().load(Repo.read(policy));
            @SuppressWarnings("unchecked")
            Map<String, Object> rules = (Map<String, Object>) yaml.get("policy");
            assertEquals(Boolean.FALSE, rules.get("allow_implicit_invocation"));
        } else {
            assertFalse(Files.exists(policy), "only command-only skills disable implicit invocation in Codex");
        }
    }

    @ParameterizedTest
    @MethodSource("skills")
    void frontmatterUsesOnlyKnownKeys(Path dir) {
        Set<String> known = Set.of("name", "description", "argument-hint", "disable-model-invocation",
                "user-invocable", "license", "compatibility", "metadata", "allowed-tools");
        for (String key : Skill.load(dir).frontmatter().keySet()) {
            assertTrue(known.contains(key), "unknown frontmatter key: " + key);
        }
    }
}
