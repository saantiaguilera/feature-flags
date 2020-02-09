[feature-flags](../index.md) / [com.saantiaguilera.featureflags](index.md) / [onDisabled](./on-disabled.md)

# onDisabled

`inline fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onDisabled(action: (result: `[`FeatureFlagResult`](-feature-flag-result/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md)

Performs the given [action](on-disabled.md#com.saantiaguilera.featureflags$onDisabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance represents not [FeatureFlagResult.enabled](-feature-flag-result/enabled.md).
Returns the original [FeatureFlagResult](-feature-flag-result/index.md) unchanged.

