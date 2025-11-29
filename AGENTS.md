# AGENT GUIDELINES

- Build: `gradle -p proximity-app build` (uses Gradle wrapper from repo root).
- Run app: `gradle -p proximity-app run`.
- Run all tests: `gradle -p proximity-app test`.
- Run a single test: `gradle -p proximity-app test --tests 'org.proximity.AppTest.appHasAGreeting'`.
- Lint/format: `gradle -p proximity-app ktlintCheck` and `gradle -p proximity-app ktlintFormat` (ktlint plugin v14.0.1).

- Keep Kotlin sources under `proximity-app/src/main|test/kotlin` in package `org.proximity`.
- Use explicit imports; avoid wildcard imports except when importing many symbols from one package.
- Naming: classes/objects PascalCase; functions, params, properties lowerCamelCase; constants UPPER_SNAKE_CASE.
- Prefer `val` over `var` and minimize mutable state; keep functions small and focused.
- Prefer non-nullable types; represent absence with nullable types or sealed hierarchies, not sentinel values.
- Use Kotlin idioms (`when`, `map`, `filter`, `let`, etc.) for clarity.
- For validation use `require`, `check`, and `error` rather than generic exceptions.
- Never swallow exceptions silently; handle, log, or rethrow with context.
- Keep `build.gradle.kts` minimal; avoid adding dependencies/plugins without justification.
- Run `gradle -p proximity-app build` before commits/PRs to ensure compile/tests pass.

- Cursor/Copilot rules: no `.cursor` or `.github/copilot-instructions.md` files detected; if added, follow them in addition to this file.

(If you update ktlint configuration or plugin version, update this file to document the change.)
