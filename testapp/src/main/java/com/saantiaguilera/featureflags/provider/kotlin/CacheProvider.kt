package com.saantiaguilera.featureflags.provider.kotlin

import com.saantiaguilera.featureflags.*

/**
 * Basic provider that hits a cache on every request
 */
class CacheProvider(private val repository: CacheRepository) : FeatureFlagProvider {

    override fun provide(feature: FeatureFlag): FeatureFlagResult {
        return repository.getFeatures()[feature.key]
            ?.let { createExistingResult(it) }
            ?: createMissingResult(feature.value)
    }

}

/**
 * This sample uses a "cache", but it could be anything (db / api-call / etc). Bear in mind
 * we access it for each invoke in this sample.
 */
interface CacheRepository {

    fun getFeatures(): Map<String, Boolean>

}