[feature-flags](../index.md) / [com.saantiaguilera.featureflags](index.md) / [onMissing](./on-missing.md)

# onMissing

`inline fun `[`FeatureFlagResult`](-feature-flag-result/index.md)`.onMissing(action: (result: `[`FeatureFlagResult`](-feature-flag-result/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md)

Performs the given [action](on-missing.md#com.saantiaguilera.featureflags$onMissing(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance is a not [FeatureFlagResult.exists](-feature-flag-result/exists.md) queried feature.
Returns the original [FeatureFlagResult](-feature-flag-result/index.md) unchanged.

Note that this method is not mutually-exclusive with [onEnabled](on-enabled.md) and [onDisabled](on-disabled.md). A feature-flag
can be missing **and** enabled/disabled (depending on its default value)

