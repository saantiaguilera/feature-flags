[feature-flags](../index.md) / [com.saantiaguilera.featureflags](./index.md)

## Package com.saantiaguilera.featureflags

General public API package. All the core elements reside here

### Types

| Name | Summary |
|---|---|
| [FeatureFlag](-feature-flag/index.md) | Simple data class for defining feature flags.`data class FeatureFlag : `[`Serializable`](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html) |
| [FeatureFlagProvider](-feature-flag-provider/index.md) | Provides responses against requested feature flags, potentially enabling or disabling a feature.`interface FeatureFlagProvider` |
| [FeatureFlagResult](-feature-flag-result/index.md) | Binary result of a feature-flag provision. It can be [enabled](-feature-flag-result/enabled.md) and should denote if it was found at the requested provider through [exists](-feature-flag-result/exists.md)`data class FeatureFlagResult` |

### Functions

| Name | Summary |
|---|---|
| [onDisabled](on-disabled.md) | Performs the given [action](on-disabled.md#com.saantiaguilera.featureflags$onDisabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance represents not [FeatureFlagResult.enabled](-feature-flag-result/enabled.md). Returns the original [FeatureFlagResult](-feature-flag-result/index.md) unchanged.`fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onDisabled(action: (result: `[`FeatureFlagResult`](-feature-flag-result/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
| [onEnabled](on-enabled.md) | Performs the given [action](on-enabled.md#com.saantiaguilera.featureflags$onEnabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance represents [FeatureFlagResult.enabled](-feature-flag-result/enabled.md). Returns the original [FeatureFlagResult](-feature-flag-result/index.md) unchanged.`fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onEnabled(action: (result: `[`FeatureFlagResult`](-feature-flag-result/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
| [onMissing](on-missing.md) | Performs the given [action](on-missing.md#com.saantiaguilera.featureflags$onMissing(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance is a not [FeatureFlagResult.exists](-feature-flag-result/exists.md) queried feature. Returns the original [FeatureFlagResult](-feature-flag-result/index.md) unchanged.`fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onMissing(action: (result: `[`FeatureFlagResult`](-feature-flag-result/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
