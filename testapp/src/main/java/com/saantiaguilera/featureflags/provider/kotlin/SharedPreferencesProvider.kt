package com.saantiaguilera.featureflags.provider.kotlin

import android.content.Context
import com.saantiaguilera.featureflags.FeatureFlag
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.FeatureFlagResult

/**
 * Shared preferences provider.
 */
class SharedPreferencesProvider(context: Context) : FeatureFlagProvider {

    private val sharedPreference = context.getSharedPreferences("test.featureflags.sharedprefs", Context.MODE_PRIVATE)

    override fun provide(feature: FeatureFlag): FeatureFlagResult {
        if (!sharedPreference.contains(feature.key)) {
            return FeatureFlagResult(feature)
        }

        return FeatureFlagResult(feature, sharedPreference.getBoolean(feature.key, feature.value))
    }

}