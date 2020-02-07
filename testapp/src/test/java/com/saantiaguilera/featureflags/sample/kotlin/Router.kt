package com.saantiaguilera.featureflags.sample.kotlin

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.saantiaguilera.featureflags.FeatureFlagProvider
import com.saantiaguilera.featureflags.feature.kotlin.FeatureCatalog
import com.saantiaguilera.featureflags.onDisabled
import com.saantiaguilera.featureflags.onEnabled
import com.saantiaguilera.featureflags.onMissing
import java.lang.ref.WeakReference

/**
 * Sample of an activity router to navigate to the available options (thus not allowing illegal navigations)
 *
 * In this sample we could have an AB testing checking which of HomeActivity or HomeNewActivity performs
 * better. For knowing so, we use a provider that will be injected to us from our creator.
 *
 * This could be used at a [presenter] which is created by an [activity][Activity].
 *
 * The activity will either create us, providing the most suitable provider it thinks so (eg. a repository one because it's
 * decided by a backend service) or it will obtain us through injection (eg. using dagger / koin / whatever).
 */
class ActivityRouter(activity: Activity, private val featureFlagProvider: FeatureFlagProvider) :
    Router {

    private val activityRef = WeakReference(activity)

    override fun navigateBackwards() {
        activityRef.get()?.onBackPressed()
    }

    /**
     * This sample uses a functional approach.
     *
     * If you want to see a more sealed-class one, see the [ImageLoadingInitializer] sample
     */
    override fun navigateHome() {
        featureFlagProvider.isFeatureEnabled(FeatureCatalog.HomeV2)
            .onEnabled {
                activityRef.get()?.also {
                    val intent = Intent(it, it::class.java) // Imagine we go here to Home V2
                    it.startActivity(intent)
                }
            }
            .onDisabled {
                activityRef.get()?.also {
                    val intent = Intent(it, it::class.java) // Imagine we go here to the old Home
                    it.startActivity(intent)
                }
            }
            .onMissing {
                // Do something? It wasn't at the provider, you may want to log it somewhere
                // so you get notice of it.
                // Don't worry though, the default feature value will still be executed so it's bug free
                Log.w("Missing", "flag home v2 is missing at provider")
            }
    }

}

interface Router {

    fun navigateBackwards()

    fun navigateHome()

}