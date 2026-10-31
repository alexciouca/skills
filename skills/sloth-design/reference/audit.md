# Audit

A technical quality check of the target. Change no code. Measure where you
can; don't guess a contrast ratio.

## Checks

**Accessibility**
- Semantics: landmarks, heading order, real buttons and links, labelled
  inputs, `alt` text, page `lang` and `title`.
- Keyboard: tab through the target; every control reachable, visible focus,
  logical order, no traps, dialogs return focus.
- Contrast: compute it from the rendered colors (Playwright
  `getComputedStyle`), including placeholder, disabled, hover and text on
  images.
- ARIA: misuse (roles on elements that already have them, `aria-hidden` on
  focusable things, missing names on icon buttons).
- The project already uses axe, Lighthouse or a lint rule set → run it and
  include its findings. Don't install new ones.

**Responsive**
- 320, 375, 768, 1280 wide: horizontal overflow, clipped or overlapping text,
  touch target sizes, content hidden behind hover.
- Zoom to 200%: nothing lost.

**Theming**
- Hard-coded colors, sizes and spacing that bypass the project's tokens.
- Dark mode (if supported): every surface switches, contrast still passes,
  no images or icons left unreadable.

**Performance**
- Images: oversized files, missing dimensions (layout shift), no lazy loading
  below the fold, wrong format.
- Fonts: number of families and weights, `font-display`, fallback metrics.
- Animation: properties that trigger layout, long or blocking effects.
- Heavy libraries pulled in for small visual effects.

## Priority

- **P0** — blocks a user or breaks the law: keyboard trap, unusable on mobile,
  no label on a required field, unreadable text.
- **P1** — serious friction for many users: contrast fails, missing focus,
  layout breaks at a common width.
- **P2** — noticeable but workable: off-scale values, missing empty state,
  small overflow.
- **P3** — polish.

## Report

```
**Audit:** <target> · P0 <n> · P1 <n> · P2 <n> · P3 <n>

| # | Pri | Area | Issue | Where | Fix |
|---|---|---|---|---|---|
| 1 | P0 | A11y | <issue> | `path:line` | <smallest fix> |

**Patterns:** <issues that repeat across files: one root cause, one fix>

**Works well:** <what passed and should stay>

**How I checked:** <tools, widths, what was measured vs. read from code>

Fix P0–P1 now? (`/sloth-design harden` or `polish`)
```

Order by priority, then by how many users it hits.
