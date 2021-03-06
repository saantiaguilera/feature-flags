package com.saantiaguilera.featureflags.test

import androidx.appcompat.app.AppCompatActivity
import com.saantiaguilera.featureflags.FeatureFlag
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.feature.kotlin.FeatureCatalog
import com.saantiaguilera.featureflags.prioritized.PriorityFeatureFlagProvider
import com.saantiaguilera.featureflags.provider.kotlin.*
import org.junit.Assert
import org.junit.Test

/**
 * Basic sample with prioritized providers
 */
class PrioritizedProviderTest : AppCompatActivity() {

    /**
     * This is a sample.
     *
     * When using it, consider minimally injecting providers from a decoupled place (either with
     * a DI framework, or by your own hand)
     */
    private fun createPrioritizedProvider(): FeatureFlagProvider = PriorityFeatureFlagProvider(
        getProviders(),
        StaticPriorityComparator()
    )

    /**
     * We expose 2 providers:
     * - Cache, ultra high priority. Only has horizontal-sign-in
     * - Repository, low priority. Has horizontal-sign-in and cache2k
     */
    private fun getProviders(): List<StaticPriorityProvider> {
        return listOf(
            StaticPriorityProvider(CacheProvider(
                    object : CacheRepository {
                        override fun getFeatures(): Map<String, Boolean> {
                            // Here a cache look-up should be performed. Imagine this 3 are the result of an execution.
                            return mapOf(
                                FeatureCatalog.horizontalSignIn.key to true
                            )
                        }
                    }
                ),
                100
            ),
            StaticPriorityProvider(RepositoryProvider(
                    object : Repository {
                        override suspend fun getFeatures(): List<FeatureFlag> {
                            // Here a cache look-up should be performed. Imagine this 3 are the result of an execution.
                            return listOf(
                                FeatureFlag(
                                    FeatureCatalog.horizontalSignIn.key,
                                    false,
                                    FeatureCatalog.horizontalSignIn.usage
                                ),
                                FeatureFlag(
                                    FeatureCatalog.cache2K.key,
                                    false,
                                    FeatureCatalog.cache2K.usage
                                )
                            )
                        }
                    }
                ),
                1
            )
        )
    }

    @Test
    fun `Test given a prioritized provider, when getting horizontalSignIn, then it's enabled and exists`() {
        val prioritizedProvider = createPrioritizedProvider()

        val result = prioritizedProvider.provide(FeatureCatalog.horizontalSignIn)

        Assert.assertTrue(result.enabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test given a prioritized provider, when getting cache2K, then it's disabled and exists`() {
        val prioritizedProvider = createPrioritizedProvider()

        val result = prioritizedProvider.provide(FeatureCatalog.cache2K)

        Assert.assertFalse(result.enabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test given a prioritized provider, when getting cardPaymentsWithVisa, then it's disabled and doesn't exist`() {
        val prioritizedProvider = createPrioritizedProvider()

        val result = prioritizedProvider.provide(FeatureCatalog.cardPaymentsWithVisa)

        Assert.assertFalse(result.enabled)
        Assert.assertFalse(result.exists)
    }

}