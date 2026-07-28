# Medieval Survival

A text-based village survival game written in Java, built as a learning project to practice object-oriented design, persistence, and clean architecture from the ground up.

## What it does

You manage a small medieval village: gather food and wood, recruit villagers, and survive random events (raids, spoilage, lucky finds) each day. The game state persists between sessions via JSON save files.

## What this project demonstrates

- **Encapsulation done properly** — resources (`food`, `wood`, `population`) are never exposed via raw getters/setters. Every mutation goes through a behavior method (`gatherFood()`, `recruitVillager()`) that enforces its own invariants (e.g. resources can't go negative or exceed their max).
- **Weighted random selection** — daily village events aren't chosen uniformly; each event carries a `probability` weight, selected via a cumulative-sum algorithm (the same principle behind loot tables in game design and CDFs in statistics).
- **Persistence via Gson** — game state is serialized to JSON and reloaded on startup, with a New Game / Load Game flow and proper handling of "no save file exists" as a real branch, not an afterthought.
- **Testable architecture via dependency injection** — randomness-dependent logic (`endDay()`'s event selection) is abstracted behind a `VillageEventProvider` interface, allowing deterministic JUnit tests via a fake implementation instead of mocking `Math.random()` directly.
- **Deliberate architecture decisions** — e.g. static initializer blocks to avoid fragile manual setup calls, and a clear separation between domain logic (`Village`) and I/O (`SaveManager`), so persistence could be swapped out without touching game rules.

## Tech stack

- Java 21
- Maven for build and dependency management
- [Gson](https://github.com/google/gson) for JSON serialization
- JUnit 5 for testing

## Running it

Clone the repo, then either open `medieval-survival` in IntelliJ (which will detect the `pom.xml` automatically) or run `mvn test` / `mvn compile exec:java` from the command line. Dependencies are resolved automatically via Maven.
