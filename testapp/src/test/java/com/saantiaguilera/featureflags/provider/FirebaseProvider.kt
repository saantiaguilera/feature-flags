package com.saantiaguilera.featureflags.provider

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.saantiaguilera.featureflags.FeatureFlag
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.FeatureFlagResult
import com.saantiaguilera.featureflags.createExistingResult

/**
 * Basic firebase feature flag provider
 */
class FirebaseFeatureFlagProvider() : FeatureFlagProvider, Refreshable {

    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder().build()
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
        // We are not handling missing cases here.
        return createExistingResult(remoteConfig.getBoolean(feature.key))
    }

    override fun refresh() {
        remoteConfig.fetchAndActivate()
    }
}