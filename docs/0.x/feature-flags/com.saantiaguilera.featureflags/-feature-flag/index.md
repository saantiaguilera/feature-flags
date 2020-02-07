[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlag](./index.md)

# FeatureFlag

`data class FeatureFlag : `[`Serializable`](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)

data class for defining feature flags. The reason it's not an interface is because a
feature flag shouldn't contain behaviours nor richer state, as it's solely reason of existence
is to transfer data.

This definition is based on the [flag](https://golang.org/pkg/flag) package definition.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | data class for defining feature flags. The reason it's not an interface is because a feature flag shouldn't contain behaviours nor richer state, as it's solely reason of existence is to transfer data.`FeatureFlag(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, usage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [key](key.md) | `val key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [usage](usage.md) | `val usage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [value](value.md) | `val value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
