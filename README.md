<p align="center">
  <img src="assets/icon.png" width="220" alt="Sloth, the lazy-but-right senior dev">
</p>

<p align="center">
  <em>Moves at 0.2 km/h. Still ships before your sprint ends.</em>
</p>

## Sloth

A senior-level dev workflow for any language.

| Command | What it does |
|---|---|
| `sloth` | Align on the task, build the simplest change in the project's style, test, review harshly, update docs and changelog, report briefly. Stays on until "stop sloth". |
| `sloth-mini` | Same workflow, shorter instructions (fewer tokens). |
| `sloth-review` | Harsh review of a change. Numbered findings with `path:line` and the smallest fix. Changes no code. |
| `sloth-plan` | Interview one question at a time, compare approaches, write a step-by-step plan. Writes no code. |
| `sloth-ask` | Short answers about the code. Read-only. |
| `sloth-design` | Design work on a frontend, fully offline: `/sloth-design <command> <target>`. Checks the result in a real browser (Playwright) when it can. |

`sloth-design` commands:

| Command | What it does |
|---|---|
| `critique` | Scored UX review with ranked fixes. Changes nothing. |
| `audit` | Accessibility, responsive, theming and performance findings, P0–P3. Changes nothing. |
| `polish` | Final detail pass: alignment, spacing, states, consistency. |
| `harden` | Empty, loading and error states, long text, overflow, edge cases. |
| `typeset` | Fonts, type scale, hierarchy, readability. |
| `layout` | Spacing scale, grid, grouping, rhythm. |
| `bolder` / `quieter` | Give a timid design a point of view, or calm a loud one. |
| `new` | Build a new screen from one of 24 directions in a local catalog. |

It calls no services and sends no telemetry; everything it uses is in `skills/sloth-design/`. Ideas inspired by [Impeccable](https://github.com/pbakaus/impeccable), written from scratch.

## Install

Replace `alexciouca/skills` with a local path to install from a checkout.

### Claude Code

```
/plugin marketplace add alexciouca/skills
/plugin install sloth@alexciouca-skills
```

Send them as two separate prompts. Plugin skills are namespaced: `/sloth:sloth`, `/sloth:sloth-review`, and so on.

Without the plugin, copy the folders from `skills/` into `~/.claude/skills/` (every project) or `<project>/.claude/skills/` (one project). Then `/sloth`, `/sloth-review`, ... work directly.

### Claude app (claude.ai chat, desktop, mobile, Cowork)

1. Open [**Customize → Plugins**](https://claude.ai/customize/plugins) in claude.ai or the desktop app.
2. **Add → Add marketplace** → `alexciouca/skills`.
3. Add the `sloth` plugin.

The plugin is saved to your account, so chat, Cowork and Claude Code (as a synced plugin, next session start) all get it. Type `/` in the message box and pick a skill, or type `sloth:` to narrow the list. Turn on **Sync automatically** to get updates from GitHub.

**Fallback: single skills.** To upload individual skills instead of the plugin, build one zip per skill and upload each in **Customize → Skills**.

```bash
python3 scripts/package_claude_app.py   # writes dist/<skill>.zip
```

Single-skill uploads accept only the frontmatter keys `name`, `description`, `license`, `compatibility`, `metadata` and `allowed-tools`, so the script drops `argument-hint` and `disable-model-invocation` from the zipped copies. Side effect: Claude may then start `sloth-mini`, `sloth-review`, `sloth-plan` and `sloth-ask` on its own instead of only when you type them.

### GitHub Copilot in VS Code

Copy the folders from `skills/` into one of:

- `<project>/.github/skills/` (one project, shared with the team through git)
- `~/.copilot/skills/` (every project, just you)

Copilot also reads `.claude/skills/` and `.agents/skills/` (project) and `~/.claude/skills/` and `~/.agents/skills/` (personal). Type `/sloth`, `/sloth-review`, ... in Copilot Chat.

Or install the plugin (needs `chat.plugins.enabled`):

- **Agent Customizations editor → Plugins → Browse → Configure Marketplaces → Add Marketplace** → `alexciouca/skills`, then install `sloth`.
- Plugins installed with the Copilot CLI (below) show up in VS Code automatically.

Plugin skills get the plugin name as prefix in VS Code: `/sloth:sloth`, `/sloth:sloth-review`, ...

### GitHub Copilot CLI

```bash
copilot plugin marketplace add alexciouca/skills
copilot plugin install sloth@alexciouca-skills
```

Inside a session, `/plugin marketplace add ...` and `/plugin install ...` do the same. Skills keep their bare names (`/sloth`, `/sloth-plan`, ...). Only if another plugin has a skill with the same name, use `/sloth/sloth`.

Without the plugin: `copilot skill add <path>/skills/sloth/SKILL.md` (add `--project` for `.github/skills/`), one skill at a time.

### Codex (CLI, desktop app, VS Code extension)

```bash
codex plugin marketplace add alexciouca/skills
codex plugin add sloth@alexciouca-skills
```

Or copy the folders from `skills/` into `~/.agents/skills/` or `<project>/.agents/skills/`. The Codex harness in VS Code also finds skills in `.github/skills/`.

Codex invokes skills with `$`: `$sloth`, `$sloth-review`, `$sloth-plan`, ... Only `sloth` can also start on its own; the others run only when you call them (`agents/openai.yaml` in each skill).

### Other agents

Most agents that support `SKILL.md` read one of `.agents/skills/`, `.claude/skills/` or `~/.agents/skills/`. Copy the folders there.

## Always on

`CLAUDE.md` (Claude) and `AGENTS.md` (Copilot, Codex and most other agents) keep `sloth` active in this repo. To get the same in another project, add this to its `CLAUDE.md` or `AGENTS.md`:

> Sloth is always active: follow the `sloth` skill for every task until I say "stop sloth".

## Development

A Gradle project checks the skills and manifests and packages them. Needs JDK 17+.

| Command | Does |
|---|---|
| `./gradlew clean` | Deletes `build/` |
| `./gradlew build` | Runs the tests and builds `build/distributions/sloth-skills-<version>.zip` |
| `./gradlew test` | Runs the tests only |
| `./gradlew ciVerify` | Everything CI needs: `build` plus the Maven POM |
| `./gradlew publishToMavenLocal` | Publishes `io.github.alexciouca:sloth-skills:<version>` (the zip) to `~/.m2` |

The tests (`src/test/java/`) check that each skill's frontmatter loads in Claude Code, Copilot and Codex (name matches folder, description length, known keys), that command-only skills have the Codex policy, that every `reference/` playbook is linked from its `SKILL.md`, that every plugin and marketplace manifest names the same plugin and version, and that `AGENTS.md` matches `CLAUDE.md`. The version comes from `plugin.json`.

GitHub Actions (`.github/workflows/ci.yml`) runs `./gradlew ciVerify` on every pull request and on pushes to `main`.

## Layout

| Path | For |
|---|---|
| `skills/` | The skills themselves, shared by every agent |
| `plugin.json` | Portable plugin manifest (Agent Plugins 1.0), read by Copilot CLI, VS Code and Codex |
| `.claude-plugin/` | Claude Code plugin and marketplace |
| `.github/plugin/marketplace.json` | GitHub Copilot marketplace |
| `.codex-plugin/`, `.agents/plugins/` | Codex plugin (fallback manifest) and marketplace |
| `skills/*/agents/openai.yaml` | Codex invocation policy |
| `scripts/package_claude_app.py` | Builds single-skill zips for the Claude app (fallback) |
| `CLAUDE.md`, `AGENTS.md` | Project instructions for this repo |
| `build.gradle`, `settings.gradle`, `gradlew`, `gradle/` | Gradle build |
| `src/test/java/` | Tests for skills and manifests |
| `.github/workflows/ci.yml` | CI: runs `ciVerify` |
