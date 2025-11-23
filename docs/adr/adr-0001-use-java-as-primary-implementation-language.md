# ADR 0001 — Use Java as primary implementation language

Status: Accepted

Date: 2025-11-23

## Context

- Project: interactive learning platform providing quizzes and assignments. Teachers/instructors can use built-in problem types and develop/integrate their own problem types via a plugin system.
- Primary UI: web.
- Runtime constraints: node-local in-memory session state acceptable; design point of up to ~5000 concurrent users per backend node; interactive latency up to ~1s acceptable.
- Plugins: JVM-only, delivered as simple JARs with a descriptor; light sandboxing acceptable; runtime hot-load/unload desired.
- Target Java version: Java 25. Spring is the preferred framework (stack—blocking vs reactive—undecided).
- Persistence: relational database(s) with stronger consistency guarantees.
- Prioritized criteria: 1) Scalability, 2) Plugin extensibility (runtime loading, classloader sandboxing), 3) Developer productivity/tooling.

## Decision

Choose Java (targeting Java 25) as the primary implementation platform, using Spring for application development, and adopt a simple JVM-JAR plugin model with classloader-based isolation and runtime loading.

## Rationale (mapped to prioritized criteria)

- Scalability
  - The JVM ecosystem is mature and proven for production scalability. Java 25 benefits from ongoing runtime optimizations and GC improvements.
  - Spring provides established patterns for connection pooling, observability, load handling, and integration with cloud platforms (Kubernetes/VMs).
  - With appropriate instance sizing and runtime tuning, a JVM-based backend can be optimized to handle the single-node target concurrency and throughput.

- Plugin extensibility
  - JVM classloader semantics enable runtime loading and unloading of JARs and offer per-plugin isolation without process-level IPC, matching the requirement for JVM-only plugins and light isolation.
  - Java’s reflection and annotation ecosystems simplify plugin discovery and lifecycle integration.
  - Existing JVM plugin frameworks (PF4J, OSGi) and Spring extension patterns provide proven approaches; the project can begin with a minimal custom classloader + registry and migrate to a framework if needed.

- Developer productivity / tooling
  - Java + Spring have excellent IDE support, debugging and refactoring tools, and a rich ecosystem for web, persistence, security, and monitoring.
  - Packaging and dependency management (JARs, Maven/Gradle) are well-supported and simplify CI/CD.

## Comparison to considered alternatives (high-level)

- Kotlin
  - Pros: modern language features, JVM compatibility.
  - Cons: minor tooling differences outside JetBrains IDEs; since Java is the developer’s primary platform and the prioritized criteria are met by Java, Kotlin is not chosen now.

- .NET
  - Pros: solid ecosystem and performance.
  - Cons: lower familiarity increases ramp-up and maintenance risk.

- Node.js
  - Pros: rapid web development and broad package ecosystem.
  - Cons: single-threaded model and JavaScript semantics complicate robust classloader-like plugin isolation and strict typing/security expectations.

- Go / Rust
  - Pros: high performance and small footprint.
  - Cons: lower-level semantics make a JVM-style plugin model and runtime hot-reload harder to implement, increasing complexity for plugin extensibility and developer productivity.
