package com.saantiaguilera.featureflags

/**
 * Provides responses against requested feature flags, potentially enabling or disabling a feature.
 *
 * There are no restrictions upon how to fetch the available features, this can be an underlying
 * cache, api-call, firebase remote config, shared-prefs, AB Test, etc. As long as it can return
 * the state of a flag, it's ok.
 *
 * Basic example
 * ```kotlin
 * val provider = FeatureFlagProvider { feature ->
 *     // check if the feature is enabled or not.
 * }
 * ```
 *
 * If a provider doesn't have the requested feature, it should respond with a missing one using
 * [FeatureFlagResult.create] (specifying as a second parameter that it doesn't)
 *
 * ```kotlin
 * val provider = FeatureFlagProvider { feature ->
 *     if (!/* check feature existence */) {
 *         // feature.value is the default provided value. We should also denote it doesn't exists.
 *         return FeatureFlagResult.create(feature.value, exists = false)
 *     }
 *
 *     // If the feature exists. return it from somewhere
 *     return FeatureFlagResult.create(/* get if the feature is enabled / disabled */)
 * }
 * ```
 *
 * On a more complex situation, you can create multi-providers such as
 * [com.saantiaguilera.featureflags.prioritized.PriorityFeatureFlagProvider] that will, whilst
 * abiding this contract, group a lot of providers with a given decision-rule (in this case,
 * sorting by priority).
 * You can also provide state into providers. Eg. If you have flags that depend on Users,
 * because you are performing an AB Test / are bound to it, you can create a custom provider
 * that constructs with a "User" (or know how to retrieve it) and react from it.
 *
 * Example:
 * ```
 * class UserBasedProvider(
 *     private val userRepository: UserRepository,
 *     private val flagsRepository: MyFlagsRepository
 * ) : FeatureFlagProvider {
 *     override fun provide(feature: FeatureFlag): FeatureFlagResult {
 *         // Somewhere to get the current user from.
 *         val user = userRepository.getCurrentUser()
 *         // Somewhere to get the flags based on this user from.
 *         val flagsForUser = flagsRepository.getFlagsForUser(user)
 *
 *         // Simple lookup of a map
 *         return flagsForUser.find { it.key == feature.key }
 *             ?.value?.let { FeatureFlagResult.create(it) }
 *             ?: FeatureFlagResult.create(feature.value, exists = false)
 *         }
 *     }
 * }
 * ```
 */
interface FeatureFlagProvider {

    /**
     * Provide for the given feature a result.
     *
     * The result should denote if the feature is [Enabled][FeatureFlagResult.Enabled] or
     * [Disabled][FeatureFlagResult.Disabled]. It should also represent if it was found or not
     * through [FeatureFlagResult.exists].
     */
    fun provide(feature: FeatureFlag): FeatureFlagResult

    companion object {
        /**
         * Constructs a provider for a lambda. This compact syntax is most useful for inline
         * providers.
         *
         * ```
         * val provider = FeatureFlagProvider { feature: FeatureFlag ->
         *     // Provide a result accordingly.
         * }
         * ```
         */
        inline operator fun invoke(crossinline block: (feature: FeatureFlag) -> FeatureFlagResult):
                FeatureFlagProvider =
            object : FeatureFlagProvider {
                override fun provide(feature: FeatureFlag) = block(feature)
            }
    }
}
