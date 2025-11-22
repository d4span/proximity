# AGENT GUIDELINES

1. Build from repo root with `gradle -p proximity-app build` (or `gradle -p proximity-app build`).
2. Run the app with `gradle -p proximity-app run`.
3. Run all tests with `gradle -p proximity-app test`.
4. Run a single test with `gradle -p proximity-app test --tests 'org.proximity.AppTest.appHasAGreeting'`.
5. A ktlint Gradle plugin is configured for `proximity-app` to enforce Kotlin style. Run `gradle -p proximity-app ktlintCheck` to check style and `gradle -p proximity-app ktlintFormat` to auto-format code. KtLint plugin version: `14.0.1`.
6. Keep Kotlin sources under `proximity-app/src/main|test/kotlin` in package `org.proximity`.
7. Use explicit imports; avoid wildcard imports unless importing many symbols from one package.
8. Name classes/objects in PascalCase; functions, parameters, and properties in lowerCamelCase; constants in UPPER_SNAKE_CASE.
9. Prefer `val` over `var`; limit mutable state and keep functions small and cohesive.
10. Prefer non-nullable types; model absence with nullable types or sealed hierarchies instead of sentinel values.
11. Use Kotlin standard library idioms (`when`, `map`, `filter`, `let`, etc.) where they improve clarity.
12. For argument/state validation, use `require`, `check`, and `error` instead of generic exceptions.
13. Never swallow exceptions silently; either handle them meaningfully, log, or rethrow with context.
14. Keep tests in `proximity-app/src/test/kotlin`, mirroring production package structure and using `kotlin.test` APIs.
15. Write focused, behavior-oriented tests (one concern per test) and keep test names descriptive.
16. Document non-trivial public APIs with KDoc, especially where behavior or edge cases are subtle.
17. Keep `build.gradle.kts` minimal; do not add plugins/dependencies without clear justification and consistency.
18. Run `gradle -p proximity-app build` before commits/PRs to ensure everything compiles and tests pass.
19. There are currently no Cursor (.cursor) or Copilot (.github/copilot-instructions.md) rules; if added later, follow them in addition to this file.
20. If you change ktlint configuration or update the plugin version, update this file to document the new version and any required steps for contributors.
