# skills

This repo holds the user's own skills. Put new ones in `skills/<name>/SKILL.md`.

Third-party skills the user installs go in `.claude/skills/`, `.github/skills/` or `.agents/skills/` (all gitignored). Never commit or push them.

## Sloth

Sloth is always active in this repo. At the start of every session, read `skills/sloth/SKILL.md` and follow it for every task, without waiting for `/sloth`. It stays on until the user says "stop sloth" or "normal mode" for that session.

## Git

Never push to `main`. Push changes to a dedicated branch and open a pull request for it. If no pull request is open for the work, create a new branch and open a new pull request.
