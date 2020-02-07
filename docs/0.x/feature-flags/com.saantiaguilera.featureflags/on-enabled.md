[feature-flags](../index.md) / [com.saantiaguilera.featureflags](index.md) / [onEnabled](./on-enabled.md)

# onEnabled

`inline fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onEnabled(action: (result: Enabled) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md)

Performs the given [action](on-enabled.md#com.saantiaguilera.featureflags$onEnabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult.Enabled, kotlin.Unit)))/action) if this instance represents [enabled](-feature-flag-result/-enabled/index.md).
Returns the original `FeatureFlagResult` unchanged.

