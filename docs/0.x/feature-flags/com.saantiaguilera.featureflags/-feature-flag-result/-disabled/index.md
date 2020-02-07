[feature-flags](../../../index.md) / [com.saantiaguilera.featureflags](../../index.md) / [FeatureFlagResult](../index.md) / [Disabled](./index.md)

# Disabled

`sealed class Disabled : `[`FeatureFlagResult`](../index.md)

Disabled result means the flag is disabled.
Regardless of this operation, the flag might've not been found (and this was a default value)

### Types

| Name | Summary |
|---|---|
| [Existing](-existing.md) | Disabled and existing result. This means it was found by the provider.`object Existing : Disabled` |
| [Missing](-missing.md) | Disabled and missing result. This means it wasn't found by the provider`object Missing : Disabled` |

### Properties

| Name | Summary |
|---|---|
| [exists](exists.md) | `open val exists: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
