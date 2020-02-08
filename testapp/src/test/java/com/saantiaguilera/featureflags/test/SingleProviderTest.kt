package com.saantiaguilera.featureflags.test

import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.FeatureFlagResult
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
        CacheProvider(object :
            CacheRepository {
            override fun getFeatures(): Map<String, Boolean> {
                // Here a cache look-up should be performed. Imagine this 3 are the result of an execution.
                return mapOf(
                    FeatureCatalog.horizontalSignIn.key to true,
                    FeatureCatalog.cache2K.key to false
                )
            }
        })

    @Test
    fun `Test getting horizontal sign in is true, because the cache has is as such`() {
        val result = createProvider().provide(FeatureCatalog.horizontalSignIn)

        Assert.assertTrue(result is FeatureFlagResult.Enabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test getting cache 2k is false, because the cache has is as such`() {
        val result = createProvider().provide(FeatureCatalog.cache2K)

        Assert.assertTrue(result is FeatureFlagResult.Disabled)
        Assert.assertTrue(result.exists)
    }


    @Test
    fun `Test getting visa is false, because that's the default and also it does not exist`() {
        val result = createProvider().provide(FeatureCatalog.cardPaymentsWithVisa)

        Assert.assertTrue(result is FeatureFlagResult.Disabled)
        Assert.assertFalse(result.exists)
    }

}