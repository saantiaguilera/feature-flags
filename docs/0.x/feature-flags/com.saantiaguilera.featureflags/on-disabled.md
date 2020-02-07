[feature-flags](../index.md) / [com.saantiaguilera.featureflags](index.md) / [onDisabled](./on-disabled.md)

# onDisabled

`inline fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onDisabled(action: (result: Disabled) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md)

Performs the given [action](on-disabled.md#com.saantiaguilera.featureflags$onDisabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult.Disabled, kotlin.Unit)))/action) if this instance represents [disable](-feature-flag-result/-disabled/index.md).
Returns the original `FeatureFlagResult` unchanged.

