[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlagResult](./index.md)

# FeatureFlagResult

`data class FeatureFlagResult`

Binary result of a feature-flag provision. It can be [enabled](enabled.md) and should denote if it was
found at the requested provider through [exists](exists.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Convenience constructor for creating existing results.`FeatureFlagResult(featureFlag: `[`FeatureFlag`](../-feature-flag/index.md)`, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`<br>Convenience constructor for creating missing results.`FeatureFlagResult(featureFlag: `[`FeatureFlag`](../-feature-flag/index.md)`)`<br>Binary result of a feature-flag provision. It can be [enabled](enabled.md) and should denote if it was found at the requested provider through [exists](exists.md)`FeatureFlagResult(featureFlag: `[`FeatureFlag`](../-feature-flag/index.md)`, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, exists: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [enabled](enabled.md) | `val enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [exists](exists.md) | `val exists: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [featureFlag](feature-flag.md) | `val featureFlag: `[`FeatureFlag`](../-feature-flag/index.md) |

### Extension Functions

| Name | Summary |
|---|---|
| [onDisabled](../on-disabled.md) | Performs the given [action](../on-disabled.md#com.saantiaguilera.featureflags$onDisabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance represents not [FeatureFlagResult.enabled](enabled.md). Returns the original [FeatureFlagResult](./index.md) unchanged.`fun `[`FeatureFlagResult`](./index.md)`.onDisabled(action: (result: `[`FeatureFlagResult`](./index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](./index.md) |
| [onEnabled](../on-enabled.md) | Performs the given [action](../on-enabled.md#com.saantiaguilera.featureflags$onEnabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance represents [FeatureFlagResult.enabled](enabled.md). Returns the original [FeatureFlagResult](./index.md) unchanged.`fun `[`FeatureFlagResult`](./index.md)`.onEnabled(action: (result: `[`FeatureFlagResult`](./index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](./index.md) |
| [onMissing](../on-missing.md) | Performs the given [action](../on-missing.md#com.saantiaguilera.featureflags$onMissing(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance is a not [FeatureFlagResult.exists](exists.md) queried feature. Returns the original [FeatureFlagResult](./index.md) unchanged.`fun `[`FeatureFlagResult`](./index.md)`.onMissing(action: (result: `[`FeatureFlagResult`](./index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](./index.md) |
