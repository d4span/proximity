# ADR 0001 — Use Kotlin for the project

Status: Accepted

Date: 2025-11-21

## Context

- The project must run on the JVM now and will use Vaadin to accelerate UI development.
- There is no existing Java/JVM codebase to interoperate with today.
- The author is familiar with the JVM and wants flexibility to add additional target platforms later (Android, iOS, native).
- The project is currently a solo effort, but may attract external contributors in the future (open source).

## Decision

- Use Kotlin targeting the JVM now (Kotlin/JVM) and build the UI with Vaadin.
- Postpone adoption of Kotlin Multiplatform (MPP) until there is a concrete need to share code with other platforms (Android, iOS, native).
- Structure the codebase to make a future migration to MPP or native targets easier.

## Rationale

- Java/Vaadin interoperability: Kotlin runs on the JVM and interoperates seamlessly with Java and Vaadin, enabling fast development with existing Java ecosystem libraries.
- Ecosystem & maintainability: Kotlin benefits from the mature JVM ecosystem and good tooling (IntelliJ), helping long-term maintainability.
- Flexibility: Although the immediate target is JVM, Kotlin’s multiplatform capabilities preserve the option to migrate or share business logic later; starting on Kotlin/JVM avoids unnecessary complexity now.

## Considered Alternatives

- Java — rejected because it ties the project to JVM-centric Java language design and lacks Kotlin’s productivity features.
- C# / .NET — rejected because it ties the project to the .NET ecosystem.
- JavaScript / TypeScript — rejected because they are tied to the JS ecosystem and would not interoperate with Vaadin on JVM without extra layers.
- Rust — rejected as too low-level for this application’s rapid development needs and ecosystem expectations.

## Consequences

Positive

- Faster implementation with Vaadin + Kotlin interop.
- Safer code with Kotlin's null-safety and expressive type system.
- Easier future migration because Kotlin supports MPP; a modular design now will help later.

Negative / Trade-offs

- Some cognitive overhead for future contributors unfamiliar with Kotlin.
- Using Vaadin binds the UI to JVM server-side technologies; migrating UI off JVM will require a rewrite or different UI strategy.
- If/when moving to native targets, some JVM-only libraries will need replacement; migration work will be required.

## Risks & Mitigations

- Risk: Future contributors may be unfamiliar with Kotlin.
  - Mitigation: Add a short Getting Started and CONTRIBUTING guide; include recommended IDE (IntelliJ) settings and coding style.
- Risk: Vaadin coupling makes UI portability harder.
  - Mitigation: Keep UI isolated in its own module and design clear interfaces between UI and core logic.

---

Notes: This ADR captures the decision to use Kotlin/JVM now while preserving the option to adopt Kotlin Multiplatform later. The file is intentionally concise; we can expand sections (e.g., add links to Kotlin/MPP resources, or a more detailed migration checklist) if you want.
