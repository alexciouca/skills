# Critique

A scored UX review of the target, with the fixes ranked. Change no code.

## Steps

1. Get context and the mode (SKILL.md steps 1–2). Render the target at both
   widths and read the screenshots; a critique from code alone says so.
2. Walk the main task as the user: arrive, understand, act, get feedback.
   Note where you hesitate. Then walk it again as the two or three of these
   people who matter most for this product, and note what stops each one:
   - **the expert in a hurry** — wants shortcuts, bulk actions, no forced
     tours, Esc that closes things;
   - **the first-timer** — reads labels literally, needs text next to icons,
     a clear next step and a way back;
   - **the keyboard or screen-reader user** — needs order, names, focus;
   - **the person on a phone, distracted** — one thumb, bad signal, gets
     interrupted halfway;
   - **the one who breaks things** — pastes huge text, double-clicks submit,
     goes back mid-flow.
3. Check the mental effort the screen asks for:
   - more than about four options or items in a group without structure;
   - the user has to remember something from a previous screen;
   - jargon the audience doesn't use;
   - the same thing done two different ways;
   - two decisions demanded at once.
4. Score each criterion 0–4, with the reason in one line. The mode decides
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
5. Rank the fixes by impact on the user divided by effort. Keep the five that
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

**Who gets stuck:** <persona> — <where and why, one line each>

**Also noticed:** <one line, the rest>

**Works well:** <what to keep, one or two bullets>
```

Be specific and harsh about the design, never about the people who built it.
A 4 needs a reason as much as a 1 does.
