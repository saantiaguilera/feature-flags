[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlagProvider](index.md) / [isFeatureEnabled](./is-feature-enabled.md)

# isFeatureEnabled

`abstract fun isFeatureEnabled(feature: `[`FeatureFlag`](../-feature-flag/index.md)`): `[`FeatureFlagResult`](../-feature-flag-result/index.md)

Returns for the given feature a result.

The result should denote if the feature is [Enabled](../-feature-flag-result/-enabled/index.md) or
[Disabled](../-feature-flag-result/-disabled/index.md). It should also represent if it was found or not
through [FeatureFlagResult.exists](../-feature-flag-result/exists.md).

