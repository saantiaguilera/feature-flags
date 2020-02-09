[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlag](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FeatureFlag(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, usage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`

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

