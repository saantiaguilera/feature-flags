[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlagResult](./index.md)

# FeatureFlagResult

`sealed class FeatureFlagResult`

Binary result of a feature-flag provision. It can be either [Enabled](-enabled/index.md) or [Disabled](-disabled/index.md).

On advanced usages, you can also check if it was found at a provider through a depth 2 lookup of
[Enabled.Existing](-enabled/-existing.md) / [Enabled.Missing](-enabled/-missing.md) or the opposing [Disabled.Existing](-disabled/-existing.md) / [Disabled.Missing](-disabled/-missing.md)

### Types

| Name | Summary |
|---|---|
| [Disabled](-disabled/index.md) | Disabled result means the flag is disabled. Regardless of this operation, the flag might've not been found (and this was a default value)`sealed class Disabled : `[`FeatureFlagResult`](./index.md) |
| [Enabled](-enabled/index.md) | Enabled result means the flag is enabled. Regardless of this operation, the flag might've not been found (and this was a default value)`sealed class Enabled : `[`FeatureFlagResult`](./index.md) |

### Properties

| Name | Summary |
|---|---|
| [enabled](enabled.md) | `val enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [exists](exists.md) | `open val exists: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [create](create.md) | Create a result from a given value.`fun create(value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, exists: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`FeatureFlagResult`](./index.md) |

### Extension Functions

| Name | Summary |
|---|---|
| [onDisabled](../on-disabled.md) | Performs the given [action](../on-disabled.md#com.saantiaguilera.featureflags$onDisabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult.Disabled, kotlin.Unit)))/action) if this instance represents [disable](-disabled/index.md). Returns the original `FeatureFlagResult` unchanged.`fun `[`FeatureFlagResult`](./index.md)`.onDisabled(action: (result: Disabled) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](./index.md) |
| [onEnabled](../on-enabled.md) | Performs the given [action](../on-enabled.md#com.saantiaguilera.featureflags$onEnabled(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult.Enabled, kotlin.Unit)))/action) if this instance represents [enabled](-enabled/index.md). Returns the original `FeatureFlagResult` unchanged.`fun `[`FeatureFlagResult`](./index.md)`.onEnabled(action: (result: Enabled) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](./index.md) |
| [onMissing](../on-missing.md) | Performs the given [action](../on-missing.md#com.saantiaguilera.featureflags$onMissing(com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Function1((com.saantiaguilera.featureflags.FeatureFlagResult, kotlin.Unit)))/action) if this instance is a not existing queried feature. Returns the original `FeatureFlagResult` unchanged.`fun `[`FeatureFlagResult`](./index.md)`.onMissing(action: (result: `[`FeatureFlagResult`](./index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`FeatureFlagResult`](./index.md) |
