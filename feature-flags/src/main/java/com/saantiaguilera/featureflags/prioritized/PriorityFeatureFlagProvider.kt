package com.saantiaguilera.featureflags.prioritized

import com.saantiaguilera.featureflags.FeatureFlag
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.FeatureFlagResult

/**
 * Priority grouping of feature-flag providers.
 *
 * This class will sort providers from a given comparator and one by one look for the given feature.
 * If all results are missing types, then the default feature value (missing) will be returned.
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
    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
        return providers
            .asSequence()
            .map { it.isFeatureEnabled(feature) }
            .firstOrNull { it.exists }
            ?: feature.toResult()
    }
}
