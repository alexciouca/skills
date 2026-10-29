"""Zip each skill for upload to the Claude app (claude.ai, desktop, mobile).

Claude app uploads accept only these frontmatter keys: name, description,
license, compatibility, metadata, allowed-tools. Any other key (such as
argument-hint or disable-model-invocation, used by Claude Code and Copilot)
makes the upload fail, so this script drops them from the zipped copies.
The source files in skills/ stay unchanged.

Usage: python3 scripts/package_claude_app.py [out-dir]   (default: dist/)
"""
import pathlib
import sys
import zipfile

ALLOWED_KEYS = {"name", "description", "license", "compatibility", "metadata", "allowed-tools"}

ROOT = pathlib.Path(__file__).resolve().parent.parent
SKILLS = ROOT / "skills"


def strip_frontmatter(text):
    """Return SKILL.md text with only the allowed top-level frontmatter keys."""
    if not text.startswith("---\n"):
        return text
    end = text.index("\n---\n", 4)
    kept, keep_current = [], True
    for line in text[4:end].splitlines():
        # A line starting without indentation opens a new top-level key;
        # indented lines belong to the key above (e.g. a folded description).
        if line and not line[0].isspace():
            keep_current = line.split(":", 1)[0].strip() in ALLOWED_KEYS
        if keep_current:
            kept.append(line)
    return "---\n" + "\n".join(kept) + text[end:]


def main():
    out = pathlib.Path(sys.argv[1]) if len(sys.argv) > 1 else ROOT / "dist"
    out.mkdir(parents=True, exist_ok=True)
    for skill in sorted(p for p in SKILLS.iterdir() if (p / "SKILL.md").is_file()):
        target = out / f"{skill.name}.zip"
        with zipfile.ZipFile(target, "w", zipfile.ZIP_DEFLATED) as zf:
            for f in sorted(skill.rglob("*")):
                # agents/openai.yaml is Codex-only metadata; the Claude app doesn't use it.
                if not f.is_file() or "agents" in f.relative_to(skill).parts[:-1]:
                    continue
                arcname = f.relative_to(SKILLS).as_posix()
                if f.name == "SKILL.md":
                    zf.writestr(arcname, strip_frontmatter(f.read_text(encoding="utf-8")))
                else:
                    zf.write(f, arcname)
        print(target.relative_to(ROOT) if target.is_relative_to(ROOT) else target)


if __name__ == "__main__":
    main()
