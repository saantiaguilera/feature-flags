[feature-flags](../../index.md) / [com.saantiaguilera.featureflags.prioritized](../index.md) / [PriorityFeatureFlagProvider](./index.md)

# PriorityFeatureFlagProvider

`class PriorityFeatureFlagProvider<P : `[`FeatureFlagProvider`](../../com.saantiaguilera.featureflags/-feature-flag-provider/index.md)`> : `[`FeatureFlagProvider`](../../com.saantiaguilera.featureflags/-feature-flag-provider/index.md)

Priority grouping of feature-flag providers.

This class will sort providers from a given comparator and one by one look for the given feature.
If all results are missing types, then the default feature value (missing) will be returned.

Example of a basic multi-prioritized group using static integers for priorities:

``` kotlin
// Simple wrapper class for creating providers with an int priority
class StaticPriorityProvider(
    private val provider: FeatureFlagProvider,
    override val priority: Int
) : FeatureFlagProvider by provider, StaticPriority

// Contract for comparing later priorities based on integers
interface StaticPriority {
    val priority: Int
}

// Comparator that sorts from highest to lowest priorities
class StaticPriorityComparator : Comparator<StaticPriority> {
    override fun compare(o1: StaticPriority?, o2: StaticPriority?): Int {
        val x = o1?.priority ?: 0
        val y = o2?.priority ?: 0
        return if (x > y) -1 else if (x == y) 0 else 1
    }
}

// Usage:
fun using() {
    val provider = PriorityFeatureFlagProvider(
        listOf(
            StaticPriorityProvider(yourProviderOne, priorityProviderOne),
            StaticPriorityProvider(yourProviderTwo, priorityProviderTwo)
            /* ... */
            ),
            StaticPriorityComparator()
        )
    }
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Priority grouping of feature-flag providers.`PriorityFeatureFlagProvider(providers: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<P>, comparator: `[`Comparator`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparator/index.html)`<in P>)` |

### Functions

| Name | Summary |
|---|---|
| [provide](provide.md) | Look across all the providers for the given feature.`fun provide(feature: `[`FeatureFlag`](../../com.saantiaguilera.featureflags/-feature-flag/index.md)`): `[`FeatureFlagResult`](../../com.saantiaguilera.featureflags/-feature-flag-result/index.md) |
