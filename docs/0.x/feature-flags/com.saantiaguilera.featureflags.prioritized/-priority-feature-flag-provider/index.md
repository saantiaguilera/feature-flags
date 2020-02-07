[feature-flags](../../index.md) / [com.saantiaguilera.featureflags.prioritized](../index.md) / [PriorityFeatureFlagProvider](./index.md)

# PriorityFeatureFlagProvider

`class PriorityFeatureFlagProvider<P : `[`FeatureFlagProvider`](../../com.saantiaguilera.featureflags/-feature-flag-provider/index.md)`> : `[`FeatureFlagProvider`](../../com.saantiaguilera.featureflags/-feature-flag-provider/index.md)

Priority grouping of feature-flag providers.

This class will sort providers from a given comparator and one by one look for the given feature.
If all results are missing types, then the default feature value (missing) will be returned.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Priority grouping of feature-flag providers.`PriorityFeatureFlagProvider(providers: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<P>, comparator: `[`Comparator`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparator/index.html)`<in P>)` |

### Functions

| Name | Summary |
|---|---|
| [provide](provide.md) | Look across all the providers for the given feature.`fun provide(feature: `[`FeatureFlag`](../../com.saantiaguilera.featureflags/-feature-flag/index.md)`): `[`FeatureFlagResult`](../../com.saantiaguilera.featureflags/-feature-flag-result/index.md) |
