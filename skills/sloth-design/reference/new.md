# New

Build a new screen or component, or replace an existing look when the user
asks for a redesign. Choose a direction first, then build it fully.

## Steps

1. **Brief.** Get, from the request, the code and the project docs:
   - what it is and who it's for;
   - the one action or understanding it must produce;
   - the content available (real copy, images, data);
   - constraints: framework, component library, pinned brand elements,
     `DESIGN.md`, deadlines on scope.
   Ask what you can't learn from the code, two or three questions in one go
   at most, shaped by the mode:
   - Sell: who must act, what they must believe, what real proof exists.
   - Work: the task, how often it's done, the states that matter.
   - Read: the reader's question, the source material, how they navigate.
   - Show: what leads, how people browse, which moment matters.
   Always worth asking: what must stay untouched, and what would make the
   result feel wrong. Missing content → build with clearly marked
   placeholders, never invented facts.
2. **Mode.** Pick and state it (SKILL.md step 2).
3. **Direction.** Invent only as much as the job needs:
   - The user or `DESIGN.md` already sets the look → use it, skip the
     catalog.
   - Existing product getting a new screen, section or state → it inherits
     the existing system, even without a `DESIGN.md`. A new component is
     never a reason for a new identity.
   - Some brand pieces exist (logo, a color) → keep them and grow the system
     around them with the user.
   - Redesign → keep the product's content, function and pinned brand
     elements; treat the old look as evidence of what the product is, not as
     something to polish.
   - Otherwise read [directions.md](directions.md) and choose three that fit
     the mode and the brief. Make one of them the safe fit and at least one a
     less obvious fit. For each, give the name, why it fits this brief, and
     the risk, in two or three lines. Ask the user to pick (AskUserQuestion
     if available), or to mix two.
4. **Direction note.** Before code, write five lines in the reply: type,
   color, layout move, signature detail, and what you will avoid. Adapt the
   catalog entry to this product; never copy it literally.
5. **Build.**
   - Read [quality-floor.md](quality-floor.md).
   - Use the project's framework, components and tokens. New tokens go where
     the project keeps them.
   - Real content first, then layout, then type and color, then the
     signature detail, then motion if any.
   - Build every state the screen needs (see `harden.md`): at least empty,
     loading and error where data is involved.
   - Make it work from 320px up, and in dark mode if the project has one.
6. **Look.** Screenshot at both widths (SKILL.md step 4), fix, look again.
   Compare to the direction note: did it end up as the direction, or drift
   back to defaults?

## Don't

Mix three directions into a beige average; start with a hero, three feature
cards and a footer because that's what pages have; add libraries for one
effect; ship the catalog's words as the product's copy.

Report the direction and why, the files created, and the screenshots.
Suggest `DESIGN.md` only if the user wants this look to carry on to other
screens.
