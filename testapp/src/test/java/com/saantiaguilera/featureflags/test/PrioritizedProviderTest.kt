package com.saantiaguilera.featureflags.test

import androidx.appcompat.app.AppCompatActivity
import com.saantiaguilera.featureflags.FeatureFlag
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.FeatureFlagResult
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
    private fun createProvider(): FeatureFlagProvider = PriorityFeatureFlagProvider(
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
                    object :
                        CacheRepository {
                        override fun getFeatures(): List<Pair<String, Boolean>> {
                            // Here a cache look-up should be performed. Imagine this 3 are the result of an execution.
                            return listOf(
                                Pair(FeatureCatalog.HorizontalSignIn.key, true)
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
                                    FeatureCatalog.HorizontalSignIn.key,
                                    false,
                                    FeatureCatalog.HorizontalSignIn.usage
                                ),
                                FeatureFlag(
                                    FeatureCatalog.Cache2K.key,
                                    false,
                                    FeatureCatalog.Cache2K.usage
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
    fun `Test getting horizontal sign in is true, because ultra high priority goes first and has it true`() {
        val result = createProvider().isFeatureEnabled(FeatureCatalog.HorizontalSignIn)

        Assert.assertTrue(result is FeatureFlagResult.Enabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test getting cache 2k is false, because the low priority one has it as such`() {
        val result = createProvider().isFeatureEnabled(FeatureCatalog.Cache2K)

        Assert.assertTrue(result is FeatureFlagResult.Disabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test getting visa is false, because none of the providers has it`() {
        val result = createProvider().isFeatureEnabled(FeatureCatalog.CardPaymentsWithVisa)

        Assert.assertTrue(result is FeatureFlagResult.Disabled)
        Assert.assertFalse(result.exists)
    }

}