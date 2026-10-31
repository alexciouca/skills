# Quality floor

Read this right before the first UI edit. Review commands use it as their
checklist. A brief or `DESIGN.md` that asks for something below may override
a default tell; nothing overrides the floors.

## Default tells

These are what an interface looks like when nobody made a decision. Avoid
them unless the brief asks for them; when you find one, replace it with a
choice that fits this product.

- One sans-serif for everything, all at one or two weights: no type contrast.
- A purple-to-blue gradient hero, gradient text, or a glowing blob behind
  the headline.
- Three feature cards in a row, each with a rounded icon tile above a
  heading and two lines of text.
- Cards inside cards; a border, a shadow and a background on the same box.
- A small uppercase label above every heading.
- Everything centered, every section the same height and rhythm.
- Pill buttons, large radii and soft shadows on everything, whatever the
  product.
- Glassmorphism or blur panels with no reason behind them.
- Gray text on a colored background; light gray body text on white.
- Emoji or random icons standing in for meaning.
- Pure black dark mode with neon accents.
- Filler copy: "Unlock the power of", "Seamless", "Elevate your workflow",
  lorem ipsum, fake logos and testimonials.
- Generic illustrations of people with laptops.
- The stats strip: a huge number, a tiny label, three more numbers beside it,
  used as decoration rather than because the numbers matter.
- A thick colored stripe on the left edge of cards, alerts or list items.
- Section numbers (01, 02, 03) when the order means nothing.
- A modal for something that needs neither interruption nor focus.
- Monospace as a costume for "technical", not for code or data.
- Unicode symbols standing in for an icon set; icons from mixed sets with
  different strokes.
- The same fade-up entrance on every section.
- Dark or light picked because of the category ("dev tools are dark")
  instead of where and when people use it.

## Floors

- **Contrast:** body text 4.5:1, large text (24px, or 18.66px bold) and UI
  parts such as borders of inputs and icons 3:1. Check the real colors,
  including text on images and on hover.
- **Targets:** interactive elements at least 44×44px on touch layouts, never
  under 24×24px; enough space between them.
- **Focus:** every interactive element shows a visible focus state; never
  `outline: none` without a replacement. Tab order follows reading order.
- **Keyboard:** everything clickable works with the keyboard; menus and
  dialogs trap and return focus.
- **Semantics:** real `button`, `a`, `label`, headings in order, landmarks.
  ARIA only where HTML has no element for it.
- **Color is never the only signal:** errors, status and links also differ in
  text, icon or shape.
- **Spacing** comes from one scale (for example 4, 8, 12, 16, 24, 32, 48, 64).
  Values off the scale need a reason.
- **Type:** at most about six sizes on a screen, from one scale. Body at least
  16px on the web; line height 1.4–1.6 for body; line length 45–75
  characters for reading text.
- **Responsive:** no horizontal scroll from 320px up; nothing hidden behind
  hover on touch; images scale and keep their ratio.
- **Motion:** 150–300ms for UI feedback, transform and opacity only where
  possible; respect `prefers-reduced-motion`.
- **States:** hover, focus, active and disabled for controls; loading, empty
  and error for anything that fetches or lists data.
- **Images:** width and height (or aspect ratio) set, meaningful `alt` or
  `alt=""` for decoration, sized for how they are shown.
- **One primary action** per view; secondary actions look secondary.
- **Headings** sit closer to what they introduce: more space above than below.
- **Depth:** shadows have an offset and a soft blur and come from one light
  direction; a glow with no offset is decoration, not depth. Hard block
  shadows only when the whole design is built on them.
- **Browser surfaces** carry the design too: text selection color, caret,
  focus ring, scrollbars where they show, link underline offset and
  thickness, form controls (`accent-color`). Theme them from the palette;
  browser defaults give away an assembled page.
- **Coverage:** everything the brief asked for is on the screen and findable
  in a few seconds.

## Before you call it done

- Would a designer on this product recognise it as theirs, or as a template?
- Is there one thing on the screen that is clearly the most important?
- Did you check it rendered, at both widths, not just in the code?
- Did anything outside the target change? Undo it.
