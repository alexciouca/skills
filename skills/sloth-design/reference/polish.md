# Polish

The last pass before shipping: the details that make an interface feel
finished. Small, many, careful changes. No new features, no new look.

**Keeps:** layout structure, visual identity, copy meaning, behavior.
**May change:** spacing, alignment, sizes within the scale, colors within the
palette, states, transitions, small copy fixes (typos, casing, punctuation).

## Order

Polish the whole target to one level rather than perfecting one corner. Fix
in this order, so a beautiful button never ships on a broken flow:

1. broken tasks, misleading states, anything inaccessible;
2. missing states (loading, empty, error, success, disabled);
3. hierarchy, responsive problems, drift from the design system;
4. visual and motion inconsistencies;
5. cleanup of styles and assets.

Items in group 1 that need real work → report them and suggest `harden`.

## Checklist

Render first and work from the screenshots, then the code.

- **Alignment:** edges line up across sections; icons centered optically, not
  just by box; text baselines line up in rows; numbers right-aligned in
  tables.
- **Spacing:** every value on the scale; related things closer than unrelated
  ones; same component, same padding everywhere.
- **Consistency:** one radius set, one shadow set, one border color; buttons of
  the same kind look the same; icon sizes and stroke widths match.
- **Type details:** no single word alone on the last line of a heading
  (`text-wrap: balance` for headings, `pretty` for paragraphs); real quotes,
  apostrophes and dashes; tabular numerals where numbers line up; consistent
  sentence or title case.
- **States:** hover, focus, active, disabled on every control; selected and
  current states visible; cursor matches what happens.
- **Transitions:** state changes ease in 150–250ms; nothing jumps; nothing
  animates that shouldn't.
- **Edges:** scroll areas end cleanly; sticky headers don't cover anchors;
  the last list item has the same spacing as the others.
- **Dark mode** (if supported): borders and shadows still visible; no pure
  white large surfaces; images and logos readable.
- **Small copy:** button labels are verbs; no trailing periods on labels;
  consistent terms for the same thing.

## Mode notes

- **Work:** density and alignment matter most; check tables and forms first.
- **Sell / Show:** the first screen gets the most attention; check it at both
  widths.
- **Read:** headings, lists, code blocks, tables and images inside the text.

Report each fix in a few words; group many small ones by file.
