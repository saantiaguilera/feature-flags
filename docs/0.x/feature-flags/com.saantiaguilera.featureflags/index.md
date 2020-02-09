[feature-flags](../index.md) / [com.saantiaguilera.featureflags](./index.md)

## Package com.saantiaguilera.featureflags

General public API package. All the core elements reside here

### Types

| Name | Summary |
|---|---|
| [FeatureFlag](-feature-flag/index.md) | Simple data class for defining feature flags.`data class FeatureFlag : `[`Serializable`](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html) |
| [FeatureFlagProvider](-feature-flag-provider/index.md) | Provides responses against requested feature flags, potentially enabling or disabling a feature.`interface FeatureFlagProvider` |
| [FeatureFlagResult](-feature-flag-result/index.md) | Binary result of a feature-flag provision. It can be either [Enabled](-feature-flag-result/-enabled/index.md) or [Disabled](-feature-flag-result/-disabled/index.md).`sealed class FeatureFlagResult` |

### Functions

| Name | Summary |
|---|---|
| [onDisabled](on-disabled.md) | Performs the given [action](on-disabled.md#com.saantiaguilera.featureflags$onDisabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult.Disabled, kotlin.Unit)))/action) if this instance represents [disable](-feature-flag-result/-disabled/index.md). Returns the original `FeatureFlagResult` unchanged.`fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onDisabled(action: (result: Disabled) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
| [onEnabled](on-enabled.md) | Performs the given [action](on-enabled.md#com.saantiaguilera.featureflags$onEnabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult.Enabled, kotlin.Unit)))/action) if this instance represents [enabled](-feature-flag-result/-enabled/index.md). Returns the original `FeatureFlagResult` unchanged.`fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onEnabled(action: (result: Enabled) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
| [onMissing](on-missing.md) | Performs the given [action](on-missing.md#com.saantiaguilera.featureflags$onMissing(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance is a not existing queried feature. Returns the original `FeatureFlagResult` unchanged.`fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onMissing(action: (result: `[`FeatureFlagResult`](-feature-flag-result/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
