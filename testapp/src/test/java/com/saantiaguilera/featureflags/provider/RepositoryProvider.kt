package com.saantiaguilera.featureflags.provider

import com.saantiaguilera.featureflags.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Basic provider that will fetch async features at the start and query them.
 * By no means use this is production code. It's just for example purposes.
 */
class RepositoryProvider(private val repository: Repository) : FeatureFlagProvider, Refreshable {

    private var features: List<FeatureFlag> = emptyList()

    init {
        refresh()
    }

    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
        return features.find {
                it.key == feature.key
            }
            ?.defaultValue?.let { createExistingResult(it) }
            ?: createMissingResult(feature.defaultValue)
    }

    override fun refresh() {
        GlobalScope.launch {
            features = repository.getFeatures()
        }
    }

}

/**
 * Since it's not queried every time, we allow refreshes.
 */
interface Refreshable {

    fun refresh()

}

/**
 * This could be a db / api-call / whatever.
 */
interface Repository {

    suspend fun getFeatures(): List<FeatureFlag>

}