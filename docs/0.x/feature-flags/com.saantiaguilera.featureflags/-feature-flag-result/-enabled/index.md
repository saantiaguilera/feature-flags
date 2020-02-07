[feature-flags](../../../index.md) / [com.saantiaguilera.featureflags](../../index.md) / [FeatureFlagResult](../index.md) / [Enabled](./index.md)

# Enabled

`sealed class Enabled : `[`FeatureFlagResult`](../index.md)

Enabled result means the flag is enabled.
Regardless of this operation, the flag might've not been found (and this was a default value)

### Types

| Name | Summary |
|---|---|
| [Existing](-existing.md) | Enabled and existing result. This means it was found by the provider.`object Existing : Enabled` |
| [Missing](-missing.md) | Enabled and missing result. This means it wasn't found by the provider`object Missing : Enabled` |

### Properties

| Name | Summary |
|---|---|
| [exists](exists.md) | `open val exists: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
