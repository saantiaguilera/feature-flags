[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlagResult](index.md) / [create](./create.md)

# create

`@JvmStatic @JvmOverloads fun create(value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, exists: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`FeatureFlagResult`](index.md)

Create a result from a given value.

This is a shortcut to using [Enabled.Existing](-enabled/-existing.md) or [Disabled.Existing](-disabled/-existing.md) depending on the
input.

By default, the result will be existing. You can specify as a second optional parameter
if it wasn't found (hence, it was missing at the provider)
This will also be a shortcut to using [Enabled.Missing](-enabled/-missing.md) or [Disabled.Missing](-disabled/-missing.md) if a
second parameter is specified

