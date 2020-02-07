[feature-flags](../../index.md) / [com.saantiaguilera.featureflags](../index.md) / [FeatureFlag](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FeatureFlag(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, usage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`

data class for defining feature flags. The reason it's not an interface is because a
feature flag shouldn't contain behaviours nor richer state, as it's solely reason of existence
is to transfer data.

This definition is based on the [flag](https://golang.org/pkg/flag) package definition.
