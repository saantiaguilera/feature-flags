package com.saantiaguilera.featureflags.provider

import com.saantiaguilera.featureflags.*

/**
 * Basic provider that hits a cache on every request
 */
class CacheProvider(private val repository: CacheRepository) : FeatureFlagProvider {

    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
        return repository.getFeatures()
            .find {
                it.first == feature.key
            }
            ?.second?.let { createExistingResult(it) }
            ?: createMissingResult(feature.defaultValue)
    }

}

/**
 * This sample uses a "cache", but it could be anything (db / api-call / etc). Bear in mind
 * we access it for each invoke in this sample.
 */
interface CacheRepository {

    fun getFeatures(): List<Pair<String, Boolean>>

}