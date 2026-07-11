# CLAUDE.md

Guidance for Claude Code (or any AI coding agent) working in this repository.

## What this is

A single-module Maven project (Java 21, no Spring, no framework) that catalogs classic Gang-of-Four design patterns. It is not an application — there is no `main` entry point for the repo as a whole. Instead every pattern is a self-contained, runnable example under its own package, each with a `*Demo` class (a plain `main()`) and a JUnit 5 test.

All nine patterns are implemented against **one shared fictional domain** — an e-commerce checkout (orders, payments, invoices, support tickets, reports) — on purpose, so the catalog reads as one coherent story instead of nine unrelated toy snippets. When adding a new pattern, prefer extending this same domain (e.g. another `Order`/`Payment`/`Notification`-flavored example) over introducing an unrelated one (animals, shapes, etc.), unless the pattern genuinely doesn't fit the domain.

```
java-patterns-lab/
├── docs/patterns/<name>.md   Problem, solution, Mermaid UML — one per pattern
├── src/main/java/com/javapatternslab/<pattern>/   Interfaces, implementations, *Demo
└── src/test/java/com/javapatternslab/<pattern>/   One *Test per pattern (mirrors src/main)
```

## Conventions

- Base package: `com.javapatternslab`, one subpackage per pattern (lowercase, no separators — `chainofresponsibility`, `templatemethod`).
- Every pattern package has exactly one `*Demo` class with a `public static void main(String[] args)` that prints a short narrated run of the pattern (not just a silent instantiation) — this is what a reader runs first to see the pattern in action.
- Tests are JUnit 5 only (no Mockito/AssertJ — these examples are plain POJOs with no external dependencies to mock). Assert on actual behavioral differences the pattern produces (e.g. "swapping the Strategy changes the computed total"), not just "the object was constructed."
- Every pattern has a matching `docs/patterns/<name>.md` with four sections: **Problem**, **Solution**, **UML** (a ```mermaid classDiagram or sequenceDiagram fenced block — GitHub renders these natively, don't link out to an external diagram tool), and a short **Try it** pointer to the `*Demo` class.
- No Lombok, no Spring, no persistence — keep every example runnable with zero setup beyond `mvn test`. If a future pattern genuinely needs a dependency, add it scoped as narrowly as possible and explain why in that pattern's doc page.
- Commit convention: Conventional Commits (`feat`, `fix`, `docs`, `test`, `refactor`).

## Adding a new pattern

1. Create `src/main/java/com/javapatternslab/<pattern>/` with the pattern's interfaces/classes plus a `*Demo`.
2. Create `src/test/java/com/javapatternslab/<pattern>/` with at least one `*Test`.
3. Create `docs/patterns/<pattern>.md` (Problem / Solution / UML / Try it).
4. Add a row to the catalog table in the root `README.md`.
5. Run `mvn -B clean test` before committing — every pattern's tests must stay green. `.github/workflows/ci.yml` runs the same command on every push/PR to `main`, so a broken build will also fail CI.
