[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlagResult](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FeatureFlagResult(featureFlag: `[`FeatureFlag`](../-feature-flag/index.md)`, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`

Convenience constructor for creating existing results.

`FeatureFlagResult(featureFlag: `[`FeatureFlag`](../-feature-flag/index.md)`)`

Convenience constructor for creating missing results.

`FeatureFlagResult(featureFlag: `[`FeatureFlag`](../-feature-flag/index.md)`, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, exists: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`

Binary result of a feature-flag provision. It can be [enabled](enabled.md) and should denote if it was
found at the requested provider through [exists](exists.md)

