package com.saantiaguilera.featureflags.prioritized

import com.saantiaguilera.featureflags.FeatureFlag
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.FeatureFlagResult
import org.junit.Assert
import org.junit.Test

class PriorityFeatureFlagProviderTest {

    @Test
    fun `Test given two providers with a same value, when queried, then the higher priority is only called`() {
        var called = false
        val provider = PriorityFeatureFlagProvider(
            listOf(
                object : TestProvider(1) {
                    override fun provide(feature: FeatureFlag): FeatureFlagResult {
                        called = true // Because of lowest priority, this shouldnt be called.
                        return FeatureFlagResult(feature, enabled = true, exists = true)
                    }
                },
                object : TestProvider(100) {
                    override fun provide(feature: FeatureFlag): FeatureFlagResult {
                        return FeatureFlagResult(feature, enabled = false, exists = true)
                    }
                }
            ),
            Comparator { o1, o2 -> o2.priority.compareTo(o1.priority) }
        )

        provider.provide(FeatureFlag("key", false, "usage"))

        Assert.assertFalse(called)
    }

    @Test
    fun `Test given two providers with a same value, when queried, then the higher priority is used`() {
        val provider = PriorityFeatureFlagProvider(
            listOf(
                object : TestProvider(1) {
                    override fun provide(feature: FeatureFlag): FeatureFlagResult {
                        return FeatureFlagResult(feature, enabled = true, exists = true)
                    }
                },
                object : TestProvider(100) {
                    override fun provide(feature: FeatureFlag): FeatureFlagResult {
                        return FeatureFlagResult(feature, enabled = false, exists = true) // This should be used.
                    }
                }
            ),
            Comparator { o1, o2 -> o2.priority.compareTo(o1.priority) }
        )

        val result = provider.provide(FeatureFlag("key", false, "usage"))

        Assert.assertFalse(result.enabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test given two providers with different values, when queried, then both are searched`() {
        val provider = PriorityFeatureFlagProvider(
            listOf(
                object : TestProvider(1) {
                    override fun provide(feature: FeatureFlag): FeatureFlagResult {
                        if (feature.key == "key") {
                            return FeatureFlagResult(feature, enabled = true, exists = true)
                        }
                        return FeatureFlagResult(feature, enabled = false, exists = false)
                    }
                },
                object : TestProvider(100) {
                    override fun provide(feature: FeatureFlag): FeatureFlagResult {
                        if (feature.key == "unexpected") {
                            return FeatureFlagResult(feature, enabled = false, exists = true)
                        }
                        return FeatureFlagResult(feature, enabled = false, exists = false)
                    }
                }
            ),
            Comparator { o1, o2 -> o2.priority.compareTo(o1.priority) }
        )

        val result = provider.provide(FeatureFlag("key", false, "usage"))

        Assert.assertTrue(result.enabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test given two providers with a same value, when queried with another, then the default value is used`() {
        val provider = PriorityFeatureFlagProvider(
            listOf(
                object : TestProvider(1) {
                    override fun provide(feature: FeatureFlag): FeatureFlagResult {
                        if (feature.key == "key") {
                            return FeatureFlagResult(feature, enabled = true, exists = true)
                        }
                        return FeatureFlagResult(feature, enabled = false, exists = false)
                    }
                },
                object : TestProvider(100) {
                    override fun provide(feature: FeatureFlag): FeatureFlagResult {
                        if (feature.key == "key") {
                            return FeatureFlagResult(feature, enabled = false, exists = true)
                        }
                        return FeatureFlagResult(feature, enabled = false, exists = false)
                    }
                }
            ),
            Comparator { o1, o2 -> o2.priority.compareTo(o1.priority) }
        )

        val result = provider.provide(FeatureFlag("unexpected", false, "usage"))

        Assert.assertFalse(result.enabled)
        Assert.assertFalse(result.exists)
    }

    @Test
    fun `Test given a list of providers, when creating and adding another one, then it's not added for security reasons`() {
        val providers = mutableListOf(
            object : TestProvider(1) {
                override fun provide(feature: FeatureFlag): FeatureFlagResult {
                    return FeatureFlagResult(feature, enabled = false, exists = false)
                }
            },
            object : TestProvider(100) {
                override fun provide(feature: FeatureFlag): FeatureFlagResult {
                    return FeatureFlagResult(feature, enabled = false, exists = false)
                }
            }
        )
        val provider = PriorityFeatureFlagProvider(
            providers,
            Comparator { o1, o2 -> o2.priority.compareTo(o1.priority) }
        )

        providers.add(object : TestProvider(500) {
            override fun provide(feature: FeatureFlag): FeatureFlagResult {
                return FeatureFlagResult(feature, enabled = true, exists = true)
            }
        })
        val result = provider.provide(FeatureFlag("", false, ""))

        Assert.assertFalse(result.enabled)
        Assert.assertFalse(result.exists)
    }

    abstract class TestProvider(val priority: Int) : FeatureFlagProvider

    companion object TestCatalog {
        val feature = FeatureFlag("test.key", false, "Test usage")
    }
}
