[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlagProvider](index.md) / [invoke](./invoke.md)

# invoke

`operator inline fun invoke(crossinline block: (feature: `[`FeatureFlag`](../-feature-flag/index.md)`) -> `[`FeatureFlagResult`](../-feature-flag-result/index.md)`): `[`FeatureFlagProvider`](index.md)

Constructs a provider for a lambda. This compact syntax is most useful for inline
providers.

```
val provider = FeatureFlagProvider { feature: FeatureFlag ->
    // Provide a result accordingly.
}
```

