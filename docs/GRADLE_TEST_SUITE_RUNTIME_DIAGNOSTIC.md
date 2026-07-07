# Gradle Test Suite Runtime Diagnostic

This document records measured evidence from branch `phase2-test-only-provider-operation-trace-completion-audit-test-runtime-diagnostic`.

## Measured Facts

- Date of capture: July 7, 2026.
- Earlier long-running `:composeApp:desktopTest` and `:composeApp:allTests` runs on this branch were intentionally interrupted after user direction because they were not producing actionable timing output. No unrelated process was killed, and no reset, clean, stash, cache deletion, build-output deletion, or file removal was performed.
- `composeApp/build.gradle.kts` now exposes opt-in Gradle `Test` task properties:
  - `skald.test.maxParallelForks`, default `1`, clamped to at least `1`.
  - `skald.test.diagnostics.enabled`, default off.
  - `skald.test.timingFile`, default empty.
  - `skald.test.showStandardStreams`, default off.
- `SourceGuardCorpus` was added under `desktopTest` to cache source-guard file lists and file text for the test JVM. Normal production runtime guard corpus is limited to `composeApp/src/commonMain`, `composeApp/src/androidMain`, and `composeApp/src/desktopMain`.
- `SourceGuardCorpus` keeps test sources, Gradle/build config, and docs/README in separate corpora. It excludes `.git`, `.gradle`, build outputs, generated reports, binary artifacts, IDE folders, temporary directories, `BUILD_HISTORY.md`, `AGENTS.md`, `DESIGN_INTENT.md`, and `ROADMAP.md` from normal source/material guard corpora unless a test explicitly asks for local planning/history material.
- Isolated completion-audit desktop test now passes: `VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditTest`, 13 tests, 0 failures, 0 errors, XML time about 24.5s in the profiled allTests run.
- Isolated source-guard desktop test now passes: `ProductionBackendAdapterSourceGuardTest`, 106 tests, 0 failures, 0 errors, XML time 9.456s in the isolated diagnostic run and 14.921s in the profiled allTests run.
- Source-guard timing file `/tmp/skald-test-runtime-diagnostic-20260707-074912/source-guard-timing.txt` recorded cached corpus setup:
  - `repository_root`: 2ms.
  - `common_main_files`: 62ms, 140 files.
  - `android_main_files`: 2ms, 9 files.
  - `desktop_main_files`: 1ms, 9 files.
  - `production_runtime_source_files`: 67ms, 158 files.
- Focused branch desktop suite passed with controlled parallelism in about 2m.
- Profiled `:composeApp:allTests` passed with `--max-workers=12 -Pskald.test.maxParallelForks=6` in 6m59.65s wall time.
- Gradle profile report: `build/reports/profile/profile-2026-07-07-07-52-11.html`.
- Profile top tasks:
  - `:composeApp:testReleaseUnitTest`: 2m4.42s.
  - `:composeApp:testDebugUnitTest`: 2m4.02s.
  - `:composeApp:desktopTest`: 2m2.56s.
  - `:composeApp:compileReleaseUnitTestKotlinAndroid`: 38.978s.
  - `:composeApp:compileDebugUnitTestKotlinAndroid`: 38.134s.
- Parsed XML report covered 388 suites, with summed suite time 1182.666s. Full per-suite runtime report: `/tmp/skald-gradle-runtime-diagnostic-20260707-075202/test-suite-times-all.txt`.

## Slowest Test Suites

- `VaultTestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationTest`: 96.869s release unit, 95.162s desktop, 94.909s debug unit.
- `VaultTestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryTest`: 58.282s desktop, 56.303s release unit, 54.123s debug unit.
- `VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceValidationTest`: 39.533s release unit, 38.025s desktop, 37.351s debug unit.
- `VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportTest`: 38.353s release unit, 36.749s debug unit, 35.593s desktop.
- `VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceTest`: 33.037s debug unit, 32.294s release unit, 31.193s desktop.
- `VaultTestOnlyProviderIdentityProviderOperationNoopKatSuiteReportTest`: 29.693s debug unit, 29.498s release unit, 29.080s desktop.
- `VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditTest`: 24.772s release unit, 24.626s debug unit, 24.532s desktop.
- `ProductionBackendAdapterSourceGuardTest`: 14.921s desktop in the profiled allTests run.

## Evidence-Backed Conclusions

- Source/material/source-guard scans were a plausible earlier contributor, but after cached corpus refactoring they are not the dominant allTests bottleneck. The source guard now runs in roughly 9.5s isolated XML time and 14.9s in allTests XML time.
- The current dominant cost is repeated execution of provider-identity no-op execution-boundary and synthetic-trace evidence tests across desktop, debug unit, and release unit tasks.
- `:composeApp:allTests` runs desktop, debug unit, and release unit test suites; focused desktopTest runs before allTests are therefore duplicative for overlapping desktop suites, although they remain useful for targeted diagnosis before allTests.
- Controlled `maxParallelForks=6` was stable in this run: focused branch suite, allTests, build, assembleDebug, and packageDeb passed with one Gradle invocation at a time.
- Read-only Kotlin daemon marker writes under the user home still cause Kotlin daemon connection failures followed by successful fallback compilation. This adds noise and likely some compile overhead, but it did not fail the build.
- BUILD_HISTORY/docs scanning did not dominate the measured source-guard timing after the corpus split. BUILD_HISTORY is excluded from normal guard corpora; docs/README are available only through the separate docs corpus.

## Hypotheses

- Further runtime gains should focus on immutable evidence-chain construction in the provider-identity no-op execution-boundary and synthetic-trace tests, especially commonTest suites that are repeated across desktop, debug unit, and release unit tasks.
- A future pass could consider moving expensive common evidence construction behind shared immutable fixtures or per-test-class setup, while preserving all current assertions and non-authorization checks.
- If Kotlin daemon fallback overhead becomes material, a separate environment-focused pass could test an explicit writable Kotlin daemon/cache location. This branch did not change cache locations or clean caches.

## Remaining Unknowns

- The exact per-method hotspot inside the slowest no-op execution-boundary validation suite was not instrumented in this pass.
- The full effect of controlled parallelism on a cold workspace was not measured because this run reused existing build outputs.

## Recommendation

Future Codex prompts should use one Gradle invocation at a time. For ordinary non-opt-in verification, use:

```bash
./gradlew --no-daemon --stacktrace --console=plain --max-workers=12 -Pskald.test.maxParallelForks=6 ...
```

For opt-in local integration validations using bitcoind, electrs, BDK runtime wallet validation, or regtest harness environment flags, keep test forks serial:

```bash
./gradlew --no-daemon --stacktrace --console=plain --max-workers=4 -Pskald.test.maxParallelForks=1 ...
```

Next optimization work should not weaken source guards. It should target repeated provider-identity evidence-chain construction and should keep the same production/runtime absence, material absence, no-provider-operation execution, no-crypto execution, no-KAT-executor, no-persistence, no-sync, no-signing/broadcasting, no-endpoint/UI, and no-mainnet assertions.
