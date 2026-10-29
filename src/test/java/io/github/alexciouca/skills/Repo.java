package io.github.alexciouca.skills;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.yaml.snakeyaml.Yaml;

/** Reads the repository files the tests check: skills and plugin manifests. */
final class Repo {

    /** Repository root, passed in by Gradle; falls back to the working directory. */
    static final Path ROOT = Path.of(System.getProperty("repo.root", "."));

    static final Path SKILLS = ROOT.resolve("skills");

    private static final ObjectMapper JSON = new ObjectMapper();

    private Repo() {
    }

    /** Every skill folder: a direct subfolder of skills/ holding a SKILL.md. */
    static List<Path> skillDirs() {
        try (Stream<Path> dirs = Files.list(SKILLS)) {
            return dirs.filter(dir -> Files.isRegularFile(dir.resolve("SKILL.md"))).sorted().toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static String read(Path file) {
        try {
            return Files.readString(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static JsonNode json(String relativePath) {
        try {
            return JSON.readTree(ROOT.resolve(relativePath).toFile());
        } catch (IOException e) {
            throw new UncheckedIOException("Invalid JSON in " + relativePath, e);
        }
    }

    /** A SKILL.md split into its YAML frontmatter (as a map) and its Markdown body. */
    record Skill(Path dir, Map<String, Object> frontmatter, String body) {

        static Skill load(Path dir) {
            String text = read(dir.resolve("SKILL.md"));
            if (!text.startsWith("---\n")) {
                throw new IllegalStateException(dir + "/SKILL.md has no frontmatter");
            }
            // Frontmatter sits between the opening "---" line and the next "---" line.
            int end = text.indexOf("\n---\n", 4);
            if (end < 0) {
                throw new IllegalStateException(dir + "/SKILL.md frontmatter is not closed");
            }
            Map<String, Object> frontmatter = new Yaml().load(text.substring(4, end));
            return new Skill(dir, frontmatter, text.substring(end + 5));
        }

        String folderName() {
            return dir.getFileName().toString();
        }

        String field(String key) {
            Object value = frontmatter.get(key);
            return value == null ? null : value.toString().strip();
        }

        boolean commandOnly() {
            return Boolean.TRUE.equals(frontmatter.get("disable-model-invocation"));
        }
    }
}
