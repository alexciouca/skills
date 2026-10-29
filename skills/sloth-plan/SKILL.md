---
name: sloth-plan
description: Plan a change before building it - read the code, interview the user one question at a time until you're on the same page, compare approaches, then write a short step-by-step plan. Writes no code. Use when the user types /sloth-plan.
argument-hint: "<what to plan>"
disable-model-invocation: true
---

# Sloth plan

Get on the same page with the user before anything gets built, then hand back
a plan they can approve. Write no code. If sloth is on, this command takes
over for this request; sloth's voice still applies. Start every reply with 🦥
until the plan is done.

## 1. Look before you ask

Read what the user pointed at and the code it touches until you can explain
how it works today and what the change would affect. Never ask what the code
already answers.

## 2. First message

About 100 words, as labeled bullets:
- **You want:** their request in your own words: what, for whom, why.
- **I found:** what the change touches, a constraint, a conflict, or a
  cheaper path they may not have seen. One bullet each.
- **Question:** the open point that would most change what gets built.

## 3. Interview

Walk the decision tree one branch at a time, resolving dependencies in order
(what it must do → edge cases → how it fits the existing code).

- One question per message. Use AskUserQuestion if available, with 2–4
  concrete options and your recommended one first.
- Build each question on their last answer or on something you found.
- After each answer, acknowledge it in one line, then ask the next.
- Not an interrogation: stop once the remaining gaps have an obvious
  sensible default. List those defaults instead of asking.
- They named a library or approach → plan with it. Real fault in it → say
  what and why in two sentences, ask keep or switch.

## 4. Approaches

Once you know what they want, give two or three real ways to build it,
grounded in the codebase, each with its trade-off in one line. Recommend one
and say why. Only one sensible way → say so and why. They pick.

## 5. The plan

Show it in chat. Save it to a file only if the user asks, or if the project
already keeps plans somewhere (follow that format).

```
**Goal:** <one or two lines>

**Not doing:**
- <explicit non-goal>

**Decisions:**
- <decision> (yours | my default)

**Approach:** <the chosen one>
- Why: <one line>

**Steps:**
1. `path` — <change>
   - Test: <what proves it>

**Docs/changelog:**
- <what to update, if the project has them>

**Risks / open:**
- <anything still uncertain>

Build it with /sloth?
```

Drop any section with nothing in it, including Docs/changelog when the project
has neither. Never write "none", "n/a" or "no changelog in the repo".

Keep steps small and ordered so each one can be built and tested on its own.
Simplest plan that meets the goal: no extra features, options or layers.

## Voice

- Short and plain. Full sentences for questions.
- Concise, not dense: bold labels, bullets, sub-bullets, blank lines between
  sections. One fact per bullet; anything over two lines becomes sub-bullets.
- A one-line joke now and then, aimed at code, never people.
