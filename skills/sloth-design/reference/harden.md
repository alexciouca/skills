# Harden

Make the target survive real data and real users. The design has to hold up
when the content is empty, huge, slow, wrong or in another language.

**Keeps:** visual identity, layout, the happy path.
**May change:** add states, constraints, messages and fallbacks; adjust
layout only where content breaks it.

## Stress the target

For each, check what happens now, then fix what breaks. Use real-looking test
data in the browser (devtools, a fixture, query params), not in committed
code, unless the project keeps fixtures.

- **Empty:** zero items, new account, no search results. Explain what goes
  here and offer the next action; never a blank area.
- **Loading:** skeleton or spinner placed where the content will appear; no
  layout jump when it arrives; slow networks (throttle) still feel alive.
- **Error:** failed request, server error, offline. Say what happened and how
  to recover; keep what the user typed; offer retry.
- **Partial:** missing image, missing optional fields, one item, a half-filled
  profile.
- **Long text:** very long names, emails, URLs, titles; text 30–40% longer
  for translation. Wrap or truncate on purpose (with a full value on hover or
  in a title), never overflow.
- **Big numbers:** 0, 1, 1,000,000, negative, many decimals, currencies.
  Plural forms right.
- **Many items:** 500 rows, 50 tags, 20 notifications. Pagination, scrolling
  or limits that still work.
- **Forms:** validation messages next to the field, on blur or submit, not on
  every keystroke; required fields marked; double submit blocked; autofill
  works.
- **Timing:** double clicks, two tabs editing the same thing, data that
  changed since the page loaded, a drag or swipe released halfway.
- **Locale:** dates, times, numbers and currencies formatted for the user's
  locale (`Intl`), not hand-built strings.
- **Permissions and roles:** disabled actions explain why; read-only views
  don't show dead buttons.
- **Right-to-left and other scripts:** only if the project supports them; use
  logical properties (`margin-inline-start`).
- **Media:** broken image fallback; very tall or very wide images.

## Mode notes

- **Work:** empty, error and large-data states matter most.
- **Sell:** long headlines, missing images, slow first load.
- **Read:** long code lines, wide tables, deep heading levels.

Report each state you added or fixed, and the cases you checked that already
held.
