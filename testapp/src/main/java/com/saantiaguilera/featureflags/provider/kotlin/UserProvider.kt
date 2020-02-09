package com.saantiaguilera.featureflags.provider.kotlin

import com.saantiaguilera.featureflags.FeatureFlag
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.FeatureFlagResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Provider based on a user. Maybe some features aren't global and apply based on a user's info.
 * You could even do an AB Testing in the repository based on the user's data.
 */
class UserProvider(private val repository: UserRepository,
                   var user: User
) : FeatureFlagProvider,
    Refreshable {

    private var features: List<FeatureFlag> = emptyList()

    init {
        refresh()
    }

    override fun provide(feature: FeatureFlag): FeatureFlagResult {
        return features.find {
                it.key == feature.key
            }
            ?.value?.let { FeatureFlagResult(feature, it) }
            ?: FeatureFlagResult(feature)
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