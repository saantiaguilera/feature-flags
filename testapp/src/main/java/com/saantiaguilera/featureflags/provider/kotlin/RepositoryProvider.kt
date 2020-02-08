package com.saantiaguilera.featureflags.provider.kotlin

import com.saantiaguilera.featureflags.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Basic provider that will fetch async features at the start and query them.
 * By no means use this is production code. It's just for example purposes.
 */
class RepositoryProvider(private val repository: Repository) : FeatureFlagProvider,
    Refreshable {

    private var features: List<FeatureFlag> = emptyList()

    init {
        refresh()
    }

    override fun provide(feature: FeatureFlag): FeatureFlagResult {
        return features.find {
                it.key == feature.key
            }
            ?.value?.let { FeatureFlagResult.create(it) }
            ?: FeatureFlagResult.create(feature.value, exists = false)
    }

    override fun refresh() {
        // In production, consider doing something more UI/UX friendly
        runBlocking {
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