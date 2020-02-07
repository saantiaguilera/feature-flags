[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlagProvider](./index.md)

# FeatureFlagProvider

`interface FeatureFlagProvider`

Provider of feature flags.

There are no restrictions upon how to fetch the data, this can be an underlying cache, api-call,
firebase remote config, shared-prefs, AB Test, etc. As long as it can return the state of a
flag, it's ok.

On a more complex situation, you can create multi-providers such as
[com.saantiaguilera.featureflags.prioritized.PriorityFeatureFlagProvider](../../com.saantiaguilera.featureflags.prioritized/-priority-feature-flag-provider/index.md) that will, whilst
abiding this contract, group a lot of providers with a given decision-rule (in this case,
sorting by priority).
You can also provide state into providers. Eg. If you have flags that depend on Users,
because you are performing an AB Test / are bound to it, you can create a custom provider
that constructs with a "User" (or know how to retrieve it) and use it.

### Functions

| Name | Summary |
|---|---|
| [isFeatureEnabled](is-feature-enabled.md) | Returns for the given feature a result.`abstract fun isFeatureEnabled(feature: `[`FeatureFlag`](../-feature-flag/index.md)`): `[`FeatureFlagResult`](../-feature-flag-result/index.md) |

### Inheritors

| Name | Summary |
|---|---|
| [PriorityFeatureFlagProvider](../../com.saantiaguilera.featureflags.prioritized/-priority-feature-flag-provider/index.md) | Priority grouping of feature-flag providers.`class PriorityFeatureFlagProvider<P : `[`FeatureFlagProvider`](./index.md)`> : `[`FeatureFlagProvider`](./index.md) |
