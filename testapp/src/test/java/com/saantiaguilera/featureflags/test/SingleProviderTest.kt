package com.saantiaguilera.featureflags.test

import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.feature.kotlin.FeatureCatalog
import com.saantiaguilera.featureflags.provider.kotlin.CacheProvider
import com.saantiaguilera.featureflags.provider.kotlin.CacheRepository
import org.junit.Assert
import org.junit.Test

/**
 * Basic sample with a single provider.
 */
class SingleProviderTest {

    /**
     * This is a sample.
     *
     * When using it, consider minimally injecting providers from a decoupled place (either with
     * a DI framework, or by your own hand)
     */
    private fun createProvider(): FeatureFlagProvider =
        CacheProvider(object : CacheRepository {
            override fun getFeatures(): Map<String, Boolean> {
                // Here a cache look-up should be performed. Imagine this 3 are the result of an execution.
                return mapOf(
                    FeatureCatalog.horizontalSignIn.key to true,
                    FeatureCatalog.cache2K.key to false
                )
            }
        })

    @Test
    fun `Test given a provider, when getting horizontalSignIn, then it's enabled and exists`() {
        val provider = createProvider()

        val result = provider.provide(FeatureCatalog.horizontalSignIn)

        Assert.assertTrue(result.enabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test given a provider, when getting cache2K, then it's disabled and exists`() {
        val provider = createProvider()

        val result = provider.provide(FeatureCatalog.cache2K)

        Assert.assertFalse(result.enabled)
        Assert.assertTrue(result.exists)
    }


    @Test
    fun `Test given a provider, when getting cardPaymentsWithVisa, then it's disabled and doesn't exist`() {
        val provider = createProvider()

        val result = provider.provide(FeatureCatalog.cardPaymentsWithVisa)

        Assert.assertFalse(result.enabled)
        Assert.assertFalse(result.exists)
    }

}