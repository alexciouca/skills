# Critique

A scored UX review of the target, with the fixes ranked. Change no code.

## Steps

1. Get context and the mode (SKILL.md steps 1–2). Render the target at both
   widths and read the screenshots; a critique from code alone says so.
2. Walk the main task as the user: arrive, understand, act, get feedback.
   Note where you hesitate.
3. Score each criterion 0–4, with the reason in one line. The mode decides
   what "good" means:
   - **Purpose** — within five seconds, is it clear what this is and what to
     do?
   - **Hierarchy** — one clear first thing, then a readable order.
   - **Flow** — the main task needs no guessing; steps are in the right order.
     (Sell: the path to the action. Read: navigation and finding things.)
   - **Feedback** — the interface answers every action: states, errors,
     progress, confirmation.
   - **Consistency** — same thing, same look and place, everywhere.
   - **Copy** — labels say what happens; errors say how to fix it; no filler.
   - **Accessibility** — the quality floor's contrast, focus, targets and
     semantics, at a glance (`audit` goes deep).
   - **Character** — does it look like this product, or like a template?
     Count the default tells.

   Scale: 0 broken, 1 poor, 2 works with friction, 3 good, 4 hard to improve.
4. Rank the fixes by impact on the user divided by effort. Keep the five that
   matter most; the rest go in one line.

## Report

```
**Mode:** <mode> · **Score:** <total>/32

| Criterion | Score | Why |
|---|---|---|
| Purpose | 3 | <one line> |
| ... | | |

**Fix first:**
1. <problem> — `path:line`
   - Fix: <smallest change>
   - With: `/sloth-design <command>`

**Also noticed:** <one line, the rest>

**Works well:** <what to keep, one or two bullets>
```

Be specific and harsh about the design, never about the people who built it.
A 4 needs a reason as much as a 1 does.
