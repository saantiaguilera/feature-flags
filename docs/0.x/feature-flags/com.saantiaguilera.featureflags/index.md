[feature-flags](../index.md) / [com.saantiaguilera.featureflags](./index.md)

## Package com.saantiaguilera.featureflags

### Types

| Name | Summary |
|---|---|
| [FeatureFlag](-feature-flag/index.md) | Open class for defining feature flags.`open class FeatureFlag : `[`Serializable`](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html) |
| [FeatureFlagProvider](-feature-flag-provider/index.md) | Provider of feature flags.`interface FeatureFlagProvider` |
| [FeatureFlagResult](-feature-flag-result/index.md) | Result of a feature-flag fetch.`sealed class FeatureFlagResult` |

### Functions

| Name | Summary |
|---|---|
| [createExistingResult](create-existing-result.md) | Create a result that was found, representing a given value.`fun createExistingResult(value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
| [createMissingResult](create-missing-result.md) | Create a result that wasn't found, representing a given value.`fun createMissingResult(value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
| [onDisabled](on-disabled.md) | Performs the given [action](on-disabled.md#com.saantiaguilera.featureflags$onDisabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult.Disabled, kotlin.Unit)))/action) if this instance represents [disable](-feature-flag-result/-disabled/index.md). Returns the original `FeatureFlagResult` unchanged.`fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onDisabled(action: (result: Disabled) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
| [onEnabled](on-enabled.md) | Performs the given [action](on-enabled.md#com.saantiaguilera.featureflags$onEnabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult.Enabled, kotlin.Unit)))/action) if this instance represents [enabled](-feature-flag-result/-enabled/index.md). Returns the original `FeatureFlagResult` unchanged.`fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onEnabled(action: (result: Enabled) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
| [onMissing](on-missing.md) | Performs the given [action](on-missing.md#com.saantiaguilera.featureflags$onMissing(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance is a not existing queried feature. Returns the original `FeatureFlagResult` unchanged.`fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onMissing(action: (result: `[`FeatureFlagResult`](-feature-flag-result/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md) |
