package com.saantiaguilera.featureflags

/**
 * Provider of feature flags.
 *
 * There are no restrictions upon how to fetch the data, this can be an underlying cache, api-call,
 * firebase remote config, shared-prefs, AB Test, etc. As long as it can return the state of a
 * flag, it's ok.
 *
 * On a more complex situation, you can create multi-providers such as
 * [com.saantiaguilera.featureflags.prioritized.PriorityFeatureFlagProvider] that will, whilst
 * abiding this contract, group a lot of providers with a given decision-rule (in this case,
 * sorting by priority).
 * You can also provide state into providers. Eg. If you have flags that depend on Users,
 * because you are performing an AB Test / are bound to it, you can create a custom provider
 * that constructs with a "User" (or know how to retrieve it) and use it.
 */
interface FeatureFlagProvider {

    /**
     * Returns for the given feature a result.
     *
     * The result should denote if the feature is [Enabled][FeatureFlagResult.Enabled] or
     * [Disabled][FeatureFlagResult.Disabled]. It should also represent if it was found or not
     * through [FeatureFlagResult.exists].
     *
     * For creating results, consider using:
     * - [createExistingResult] for creating a result in case you found the feature.
     * - [createMissingResult] for creating a result in case you didn't find the feature.
     * You can also create them manually if none of the above fit your needs.
     */
    fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult
}