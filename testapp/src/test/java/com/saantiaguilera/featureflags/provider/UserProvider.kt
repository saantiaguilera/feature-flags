package com.saantiaguilera.featureflags.provider

import com.saantiaguilera.featureflags.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Provider based on a user. Maybe some features aren't global and apply based on a user's info
 */
class UserProvider(private val repository: UserRepository,
                   var user: User) : FeatureFlagProvider, Refreshable {

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
            features = repository.getFeatures(user)
        }
    }

}

data class User(val id: Int, val name: String)

/**
 * This could be an api-call / cache / db query / etc.
 */
interface UserRepository {

    fun getFeatures(user: User): List<FeatureFlag>

}