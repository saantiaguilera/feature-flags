[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlag](./index.md)

# FeatureFlag

`data class FeatureFlag : `[`Serializable`](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)

Simple data class for defining feature flags.

This definition is based on the [flag](https://golang.org/pkg/flag) package definition.

Example:

``` kotlin
object PaymentsFeatureCatalog {
    val enableVisaPayments = FeatureFlag(
        "feature.payments.cards.visa",
        false,
        "Denotes the user should be able to make payments using VISA cards"
    )
}
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Simple data class for defining feature flags.`FeatureFlag(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, usage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [key](key.md) | `val key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [usage](usage.md) | `val usage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [value](value.md) | `val value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
