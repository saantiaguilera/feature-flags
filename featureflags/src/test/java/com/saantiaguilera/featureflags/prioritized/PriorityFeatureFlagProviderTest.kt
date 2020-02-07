package com.saantiaguilera.featureflags.prioritized

import com.saantiaguilera.featureflags.*
import org.junit.Assert
import org.junit.Test

class PriorityFeatureFlagProviderTest {

    @Test
    fun `Test given two providers with a same value, when queried, then the higher priority is only called`() {
        var called = false
        val provider = PriorityFeatureFlagProvider(
            listOf(
                object : TestProvider(1) {
                    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
                        called = true // Because of lowest priority, this shouldnt be called.
                        return createExistingResult(true)
                    }
                },
                object : TestProvider(100) {
                    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
                        return createExistingResult(false)
                    }

                }
            ),
            Comparator { o1, o2 -> o2.priority.compareTo(o1.priority) }
        )

        provider.isFeatureEnabled(FeatureFlag("key", false, "usage"))

        Assert.assertFalse(called)
    }

    @Test
    fun `Test given two providers with a same value, when queried, then the higher priority is used`() {
        val provider = PriorityFeatureFlagProvider(
            listOf(
                object : TestProvider(1) {
                    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
                        return createExistingResult(true)
                    }
                },
                object : TestProvider(100) {
                    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
                        return createExistingResult(false) // This should be used.
                    }

                }
            ),
            Comparator { o1, o2 -> o2.priority.compareTo(o1.priority) }
        )

        val result = provider.isFeatureEnabled(FeatureFlag("key", false, "usage"))

        Assert.assertTrue(result is FeatureFlagResult.Disabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test given two providers with different values, when queried, then both are searched`() {
        val provider = PriorityFeatureFlagProvider(
            listOf(
                object : TestProvider(1) {
                    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
                        if (feature.key == "key") {
                            return createExistingResult(true)
                        }
                        return createMissingResult(false)
                    }
                },
                object : TestProvider(100) {
                    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
                        if (feature.key == "unexpected") {
                            return createExistingResult(false)
                        }
                        return createMissingResult(false)
                    }

                }
            ),
            Comparator { o1, o2 -> o2.priority.compareTo(o1.priority) }
        )

        val result = provider.isFeatureEnabled(FeatureFlag("key", false, "usage"))

        Assert.assertTrue(result is FeatureFlagResult.Enabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test given two providers with a same value, when queried with another, then the default value is used`() {
        val provider = PriorityFeatureFlagProvider(
            listOf(
                object : TestProvider(1) {
                    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
                        if (feature.key == "key") {
                            return createExistingResult(true)
                        }
                        return createMissingResult(false)
                    }
                },
                object : TestProvider(100) {
                    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
                        if (feature.key == "key") {
                            return createExistingResult(false)
                        }
                        return createMissingResult(false)
                    }

                }
            ),
            Comparator { o1, o2 -> o2.priority.compareTo(o1.priority) }
        )

        val result = provider.isFeatureEnabled(FeatureFlag("unexpected", false, "usage"))

        Assert.assertTrue(result is FeatureFlagResult.Disabled)
        Assert.assertFalse(result.exists)
    }

    abstract class TestProvider(val priority: Int) : FeatureFlagProvider

}