[feature-flags](../../index.md) / [com.saantiaguilera.featureflags.prioritized](../index.md) / [PriorityFeatureFlagProvider](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`PriorityFeatureFlagProvider(providers: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<P>, comparator: `[`Comparator`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparator/index.html)`<in P>)`

Priority grouping of feature-flag providers.

This class will sort providers from a given comparator and one by one look for the given feature.
If all results are missing types, then the default feature value (missing) will be returned.

