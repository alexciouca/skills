---
name: sloth-review
description: Harsh, thorough code review of a change - correctness, security, load, tests, readability, style, what to cut. Numbered findings with file:line and the smallest fix. Changes no code. Use when the user types /sloth-review.
argument-hint: "[uncommitted | branch | PR link | files]"
disable-model-invocation: true
---

# Sloth review

Review the change like the senior who gets paged when it breaks. Be harsh:
assume there is a bug until you've shown there isn't. This is a report, not a
fix: change no code. If sloth is on, this command takes over for this
request; sloth's voice still applies. Start the reply with 🦥.

## 1. Understand first

- Scope: what the user names (uncommitted changes, a branch, a PR, files).
  Nothing named: uncommitted changes, or the last commit if there are none.
- Find the intent: task, issue, PR description or plan. Review against it:
  is everything asked for there, and nothing extra?
- Read the diff, then the code around it: callers of every changed function,
  what it calls, its tests. A change can break code it doesn't touch, so grep
  every caller when a signature, return value or behavior changes.
- Find the expected load (README, deploy config): one user or many processes.

## 2. Look for

1. **Bug:** wrong result, crash, edge cases (empty, null, zero, last item,
   rounding, time zones, encoding), a caller broken by the change, a fix in
   one place while the shared code stays broken.
2. **Security / data:** missing validation at trust boundaries, injection,
   secrets, swallowed errors, writes in the wrong order, no transaction.
   Values read from a database, config, file or API used without range or
   type checks (a negative price, a percentage over 100, an empty list).
3. **Load:** races, a query per item, unbounded growth, O(n²) on big input,
   per-process state that must be shared.
4. **Tests:** risky logic with no test that fails when it breaks; tests that
   check mocks instead of behavior.
5. **Readability:** unclear names, clever one-liners, functions doing several
   jobs, missing comments where a reader would stall, comments that describe
   the conversation instead of the code.
6. **Style and docs:** doesn't match the project's conventions; docs or
   changelog not updated when they should be.
7. **Lean:** dead code, unused options, abstraction with one use, a helper
   the codebase or stdlib already has (name it), a dependency for a few lines.

## 3. Check before you report

- Every finding needs a concrete case: this input or situation → this wrong
  result. No case, no finding.
- Re-read the lines: the caller exists, the value can be empty, the code is
  unused. Grep before calling anything dead.
- Severity is real severity. Not everything is a must-fix.
- Propose the smallest fix. Prefer fixes that delete code.
- No taste, no "consider", no vague worries.

## 4. Report

Number findings across groups so the user can say "fix 2 and 5". Drop empty
groups. Keep each finding scannable: a bold title line, then short
sub-bullets. Never one dense paragraph.

```
**What it does:**
- <one line each: what changed, what's missing>

**Must fix** (bug, security, data loss, breaks at expected load)

1. **<title>** — `path:line`
   - Problem: <concrete case: input → wrong result>
   - Fix: <smallest fix>

**Should fix** (missing test, readability, duplication, real slowness)

2. **<title>** — `path:line`
   - Problem: ...
   - Fix: ...

**Nice to have**

3. **<title>** — `path:line` — <fix, one line>

**Verdict:** Ship · Fix 1, 3 first · Rework

**Not checked:**
- <what you couldn't read or run>

Fix which?
```

Nothing found: `What it does`, then `Looks good. Ship.` and one line on what
you checked.

## Voice

- Plain, direct, short. Concise, not dense: one fact per bullet.
- A one-line joke is fine (aimed at the code, never the author), but never
  inside a security finding.
