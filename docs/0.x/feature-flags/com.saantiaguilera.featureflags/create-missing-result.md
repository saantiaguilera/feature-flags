[feature-flags](../index.md) / [com.saantiaguilera.featureflags](index.md) / [createMissingResult](./create-missing-result.md)

# createMissingResult

`fun createMissingResult(value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`FeatureFlagResult`](-feature-flag-result/index.md)

Create a result that wasn't found, representing a given value.

This is a shortcut to using [Enabled.Missing](-feature-flag-result/-enabled/-missing.md) or [Disabled.Missing](-feature-flag-result/-disabled/-missing.md) depending on the given value
