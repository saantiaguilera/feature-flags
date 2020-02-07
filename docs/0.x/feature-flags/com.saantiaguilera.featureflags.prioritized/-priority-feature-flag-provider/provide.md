[feature-flags](../../index.md) / [com.saantiaguilera.featureflags.prioritized](../index.md) / [PriorityFeatureFlagProvider](index.md) / [provide](./provide.md)

# provide

`fun provide(feature: `[`FeatureFlag`](../../com.saantiaguilera.featureflags/-feature-flag/index.md)`): `[`FeatureFlagResult`](../../com.saantiaguilera.featureflags/-feature-flag-result/index.md)

Look across all the providers for the given feature.

The look-up is done through a sorted list abiding the provided comparator.

If all results are missing types, then the default feature value (missing) will be returned.

