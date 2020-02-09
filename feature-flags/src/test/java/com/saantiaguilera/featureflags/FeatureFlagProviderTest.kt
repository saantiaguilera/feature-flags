package com.saantiaguilera.featureflags

import org.junit.Assert
import org.junit.Test

class FeatureFlagProviderTest {

    @Test
    fun `Test given an inline feature flag provider, when running it, then it runs the lambda`() {
        val provider = FeatureFlagProvider {
            FeatureFlagResult(feature, enabled = true, exists = false)
        }

        val result = provider.provide(FeatureFlag("", false, ""))

        Assert.assertTrue(result.enabled)
        Assert.assertFalse(result.exists)
    }

    companion object TestCatalog {
        val feature = FeatureFlag("test.key", false, "Test usage")
    }
}
