package com.saantiaguilera.featureflags.prioritized

import com.saantiaguilera.featureflags.FeatureFlag
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.FeatureFlagResult

class PriorityFeatureFlagProvider<P : FeatureFlagProvider>(
    private val providers: Collection<P>,
    private val comparator: Comparator<in P>
) : FeatureFlagProvider {

    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
        return providers.sortedWith(comparator)
            .asSequence()
            .map { it.isFeatureEnabled(feature) }
            .firstOrNull { it.exists }
            ?: feature.toResult()
    }

}