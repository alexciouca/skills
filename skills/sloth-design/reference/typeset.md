# Typeset

Fix the typography of the target: fonts, scale, hierarchy and reading
comfort. Type does most of the work on most screens; get it right before
reaching for color or decoration.

**Keeps:** copy, layout structure, brand fonts the project or `DESIGN.md`
pins.
**May change:** sizes, weights, line heights, letter spacing, which pinned
font goes where, font loading. Swapping a font family only if the user asks
or the project has none chosen.

## Steps

1. **Inventory.** List every font family, size, weight and line height in use
   on the target (from the CSS or computed styles). Usually there are too
   many.
2. **Families.** One family can carry a whole product. Two at most: a
   display or headline face and a reading face, chosen for contrast (serif
   and sans, or condensed and wide), not two similar sans-serifs. A mono only
   where code or data needs it.
3. **Scale.** Pick a ratio and derive at most about six sizes from a 16px base
   (or the project's base):
   - Work and Read: 1.125–1.2, so dense screens stay calm.
   - Sell and Show: 1.25–1.5, so headlines can carry the page.
   Map every existing size to the nearest step; add the scale to the
   project's tokens if it has them.
4. **Hierarchy.** Each level differs from its neighbor in more than one way:
   size and weight, or size and case, or weight and color. Headings closer to
   the text they introduce than to the text above.
5. **Reading comfort.** Body 16px or more; line height 1.4–1.6 for body,
   1.05–1.25 for large headings; line length 45–75 characters; paragraph
   spacing instead of indents on screen.
6. **Details.** Uppercase and small labels get 0.04–0.1em letter spacing;
   large display type often wants slightly negative spacing, but not past
   about -0.04em; tabular numerals in tables and prices.
7. **Line breaks.** `text-wrap: balance` on headings and short centered
   text, `pretty` on paragraphs. Break a heading by hand only between
   phrases, and check it at every width; a hard break that lands mid-line on
   a phone is worse than none. A narrow column that still breaks badly →
   edit the copy or widen the column.
8. **Loading.** Only the weights in use; `font-display: swap` or `optional`;
   a fallback stack with similar metrics (`size-adjust` if the shift is
   visible). Prefer fonts the project already hosts; never add a remote font
   service unasked.

## Default tells to fix

One family at one weight everywhere; gray body text below 4.5:1; headings
only bigger, never bolder; eight sizes that are all within 2px of each other;
line length across a 1440px screen.

Report the scale you chose, the families and where they apply, and what moved.
