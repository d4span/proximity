<!--
Sync Impact Report
- Version change: 0.1.3 -> 0.2.0
- Modified principles:
  - I. Test-Driven Development (moved Tests Review checkpoint to after unit tests)
  - VI. Encapsulation & Public Interfaces (NEW)
- Added sections: Human Review (adjusted ordering of checkpoints); Encapsulation principle
- Added note: Architecture documentation location (repo `docs/` folder, ADRs in `docs/adr/`)
- Removed sections: none
- Templates updated: ✅ .specify/templates/plan-template.md
                     ✅ .specify/templates/spec-template.md
                     ✅ .specify/templates/tasks-template.md
                     ✅ .specify/templates/agent-file-template.md
- Templates reviewed (no changes required): ✅ .specify/templates/checklist-template.md
- Follow-up TODOs: RATIFICATION_DATE left as TODO (original adoption date unknown)
-->



# Proximity Constitution

## Core Principles

### I. Test-Driven Development (NON-NEGOTIABLE)
All features and changes MUST follow a structured, test-first development cycle that
prioritizes delivering verified behavior and preventing regressions. The required
order of testing is:

- Acceptance (end-to-end / feature) tests first — these define the customer-facing
  acceptance criteria and are authored before implementation.
- Integration tests next — verify contracts, inter-service interaction, and schema
  compatibility between components discovered in acceptance tests.
- Unit tests last — focused on internal logic, and on the unit level use property-based
  testing where appropriate to explore invariants and edge-cases beyond example-based tests.

Tests MUST be written and run (and observed to FAIL) before any implementation
code is authored for the corresponding behavior. CI pipelines MUST run the full
suite (acceptance + integration + unit) for any change that touches related
components; failing pipelines block merges.

Rationale: Forcing tests first at the acceptance level keeps development scoped to
observable behavior and reduces rework caused by local optimizations that do not
satisfy user requirements. Property-based unit testing increases confidence in
logic correctness by exercising a wide input space.

### II. Independent, Incremental Delivery
Work is organized into small, independently testable user stories (slices). Each
story MUST be deliverable, demonstrable, and revertible on its own. Branches
and PRs should be small, focus on a single story, and contain the tests that
validate the story.

Rules:

- Stories map to clear acceptance criteria and automated acceptance tests.
- Tasks within a story follow the sequence: tests → failing confirmation → implementation → refactor.
- Mergeable units must pass all relevant gates and include a migration plan for
  any behaviour or schema changes.

Rationale: Small increments reduce review surface, make rollbacks safe, and
encourage clear acceptance criteria.

### III. Contract-First Integration
Integration and contract tests are treated as first-class artifacts. Changes that
affect interfaces or shared schemas MUST be accompanied by updated contract
tests and a compatibility statement.

Rules:

- Define or update consumer-driven contracts when APIs or schemas change.
- Integration tests MUST cover cross-component happy-paths and key failure modes.
- Backwards-incompatible changes require a documented migration strategy and
  a MAJOR version bump (see Governance: Versioning policy).

Rationale: Explicit contracts and integration tests reduce runtime surprises and
make automated verification of interoperability possible.

### IV. Observability & Structured Logging
All services and libraries MUST emit structured logs and expose metrics and
tracing hooks appropriate to their runtime environment. Logs and metrics MUST be
sufficient to diagnose failures from CI and in production with minimal additional
instrumentation.

Rules:

- Use structured, queryable logging (JSON or equivalent) with stable fields for
  timestamp, level, correlation id, component, and error context.
- Expose metrics for key business and system-level indicators; instrument latency
  and error rates for important paths.
- Provide README guidance for local debugging and reproducing CI failures.

Rationale: Observability reduces time-to-detection and time-to-resolution for
issues and makes test failures reproducible.

### V. Versioning, Compatibility & Simplicity
Versioning follows semantic versioning guidelines (MAJOR.MINOR.PATCH) and
prioritizes stability and simple migration paths.

Rules:

- MAJOR: incompatible API or data model changes requiring coordination and a
  migration plan.
- MINOR: new, backwards-compatible functionality or expanded guidance.
- PATCH: non-breaking clarifications, typos, or documentation-only changes.
- Prefer simple, documented solutions; YAGNI applies when complexity is not
  justified by clear value.

Rationale: Clear versioning expectations reduce upgrade friction and set
expectations for consumers.

## Additional Constraints

- Architecture documentation: Architecture decision records (ADRs) and other
  architecture documentation are stored in this repository's `docs/` folder
  (see `docs/adr/` for ADRs). Teams MUST consult `docs/` when making
  architecture-impacting changes.
- CI Gate: Every feature PR MUST include acceptance tests and run the acceptance,
  integration, and unit suites in CI. Any exception MUST be documented and
  approved by a maintainer prior to merge.
- Security: Security-sensitive changes MUST include threat modeling notes and
  testing notes (where applicable) and pass security review before merging.
- Tooling: Tests MUST be automated (e.g., via CI). Property-based unit tests are
  required where deterministic invariants can be expressed and exercised.

### VI. Encapsulation & Public Interfaces
Each module/package in this repository MUST expose a minimal, clearly documented
public interface (API) and keep internal implementation details private and
invisible to other packages. The public interface should be idiomatic for the
module's language and provide stable contracts for consumers.

Rules:

- Public interfaces MUST be documented and discoverable (README, public package
  docs, or API docs in `docs/`).
- Internal modules, helper functions, and implementation classes MUST be
  non-exported or placed behind package-private visibility mechanisms appropriate
  to the language (e.g., non-exported symbols, `internal`/`private` visibility,
  or separate internal modules).
- Breaking internal implementation that does not affect the declared public
  interface does not require a version MAJOR bump, provided the public contract
  remains compatible and tests continue to validate observable behavior.
- Any change that widens a public interface or changes its semantics MUST be
  documented, accompanied by updated contract tests, and treated as a potential
  MINOR/MAJOR version change per the Versioning policy.

Rationale: Strict encapsulation reduces coupling, improves maintainability,
clarifies responsibilities, and makes it safer to evolve implementations without
forcing consumers to adapt.

## Development Workflow

Follow this TDD workflow for each story:

1. Author acceptance tests that capture the user-facing behavior (feature/acceptance tests). Run and confirm they FAIL.
2. Author supporting integration tests that define component contracts and interactions. Run and confirm they FAIL.
3. Author unit tests (property-based where applicable) that codify invariants. Run and confirm they FAIL.
4. Request an assigned human reviewer (see "Human Review" guidance below) to review ALL authored tests (acceptance, integration, unit) and the test plans before implementing.
5. Implement the minimal code to make the tests pass (red → green).
6. After implementation and before merging, request an assigned human reviewer to validate the implemented change, test coverage, and the migration/compatibility note.
7. Refactor while keeping the test-suite green. Improve test coverage where gaps are found.
8. Update documentation and quickstart examples to reflect behavior changes.

Human Review (MANDATORY checkpoints):

- Review 1 (Tests Review): After all tests (acceptance, integration, unit) have been authored and confirmed failing, a human reviewer MUST verify that the tests correctly capture the user-visible behavior and that the test plans (including property-based unit test invariants) are sufficient.
- Review 2 (Implementation Review): After implementation (and before merge), a human reviewer MUST verify the implementation meets the acceptance tests, that integration and unit tests are present and sufficient (including property-based checks where applicable), and confirm no observability, security, or compatibility requirements were missed.

PRs MUST include:

- The failing acceptance test(s) demonstrating the problem being solved (or a
  reference to the feature ticket containing them).
- The integration and unit tests created or updated by the change.
- The assigned reviewer(s) for the Tests Review and Implementation Review (name or GitHub handle) and the review status (requested/approved).
- A concise migration/compatibility note if interfaces or persisted formats changed.

## Governance

- Amendments: Propose changes via a PR that updates this file and includes a
  migration plan for any behavioral or tooling changes. The PR MUST include at
  least two independent reviewers (or one maintainer + one reviewer if the
  project has a single active maintainer). Changes that alter required gates
  (e.g., removing a test requirement) MUST include a mitigation plan.
- Versioning policy: Bump MAJOR for incompatible changes to principles or
  required gates; bump MINOR for additions of new mandatory principles or
  materially expanded guidance; bump PATCH for wording clarifications and typo
  fixes. The change in `Version` in this file MUST match the version label in
  the Sync Impact Report at the top of this document.
- Compliance review: The project MUST perform an annual review of governance and
  testing gates. Ad-hoc reviews are required for major incidents that expose
  governance gaps.

**Version**: 0.2.0 | **Ratified**: TODO(RATIFICATION_DATE): original adoption date unknown | **Last Amended**: 2025-12-26
