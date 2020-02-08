package com.saantiaguilera.featureflags

import org.junit.Assert
import org.junit.Test

class FeatureFlagProviderTest {

    @Test
    fun `Test given an inline feature flag provider, when running it, then it runs the lambda`() {
        val provider = FeatureFlagProvider { _ ->
            FeatureFlagResult.create(value = true, exists = false)
        }

        val result = provider.provide(FeatureFlag("", false, ""))

        Assert.assertNotNull(result)
        Assert.assertTrue(result is FeatureFlagResult.Enabled.Missing)
    }
}
