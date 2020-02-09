package com.saantiaguilera.featureflags.prioritized

import com.saantiaguilera.featureflags.FeatureFlag
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.FeatureFlagResult

/**
 * Priority grouping of feature-flag providers.
 *
 * This class will sort providers from a given comparator and one by one look for the given feature.
 * If all results are missing types, then the default feature value (missing) will be returned.
 *
 * Example of a basic multi-prioritized group using static integers for priorities:
 * ```kotlin
 *  // Simple wrapper class for creating providers with an int priority
 *  class StaticPriorityProvider(
 *      private val provider: FeatureFlagProvider,
 *      override val priority: Int
 *  ) : FeatureFlagProvider by provider, StaticPriority
 *
 *  // Contract for comparing later priorities based on integers
 *  interface StaticPriority {
 *      val priority: Int
 *  }
 *
 *  // Comparator that sorts from highest to lowest priorities
 *  class StaticPriorityComparator : Comparator<StaticPriority> {
 *      override fun compare(o1: StaticPriority?, o2: StaticPriority?): Int {
 *          val x = o1?.priority ?: 0
 *          val y = o2?.priority ?: 0
 *          return if (x > y) -1 else if (x == y) 0 else 1
 *      }
 *  }
 *
 *  // Usage:
 *  fun using() {
 *      val provider = PriorityFeatureFlagProvider(
 *          listOf(
 *              StaticPriorityProvider(yourProviderOne, priorityProviderOne),
 *              StaticPriorityProvider(yourProviderTwo, priorityProviderTwo)
 *              /* ... */
 *          ),
 *          StaticPriorityComparator()
 *      )
 *  }
 * ```
 */
class PriorityFeatureFlagProvider<P : FeatureFlagProvider>(
    providers: Collection<P>,
    comparator: Comparator<in P>
) : FeatureFlagProvider {

    private val providers = providers.sortedWith(comparator)

    /**
     * Look across all the providers for the given feature.
     *
     * The look-up is done through a sorted list abiding the provided comparator.
     *
     * If all results are missing types, then the default feature value (missing) will be returned.
     */
    override fun provide(feature: FeatureFlag): FeatureFlagResult {
        return providers
            .asSequence()
            .map { it.provide(feature) }
            .firstOrNull { it.exists }
            ?: FeatureFlagResult.create(feature.value, exists = false)
    }
}
