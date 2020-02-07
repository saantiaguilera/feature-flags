package com.saantiaguilera.featureflags

import org.junit.Assert
import org.junit.Test

class FeatureFlagTest {

    @Test
    fun `Test given a feature flag with false default, when converted to a result, then its false`() {
        val flag = FeatureFlag("key", false, "Some usage")

        val result = flag.toResult()

        Assert.assertTrue(result is FeatureFlagResult.Disabled)
    }

    @Test
    fun `Test given a feature flag with true default, when converted to a result, then its true`() {
        val flag = FeatureFlag("key", true, "Some usage")

        val result = flag.toResult()

        Assert.assertTrue(result is FeatureFlagResult.Enabled)
    }

    @Test
    fun `Test given a feature flag, when converted to a result, then its missing`() {
        val flag = FeatureFlag("key", false, "Some usage")

        val result = flag.toResult()

        Assert.assertFalse(result.exists)
    }

}