[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlagProvider](index.md) / [provide](./provide.md)

# provide

`abstract fun provide(feature: `[`FeatureFlag`](../-feature-flag/index.md)`): `[`FeatureFlagResult`](../-feature-flag-result/index.md)

Provide for the given feature a result.

The result should denote if the feature is [enabled](../-feature-flag-result/enabled.md). It should
also represent if it was found or not through [FeatureFlagResult.exists](../-feature-flag-result/exists.md).

