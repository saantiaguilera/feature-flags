package com.saantiaguilera.featureflags.sample.java;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.saantiaguilera.featureflags.FeatureFlagProvider;
import com.saantiaguilera.featureflags.FeatureFlagResult;
import com.saantiaguilera.featureflags.FeatureFlagResultExtension;
import com.saantiaguilera.featureflags.feature.java.FeatureCatalog;
import com.saantiaguilera.featureflags.sample.kotlin.Router;

import java.lang.ref.WeakReference;

import kotlin.Unit;

/**
 * Sample of an activity router to navigate to the available options (thus not allowing illegal navigations)
 *
 * In this sample we could have an AB testing checking which of HomeActivity or HomeNewActivity performs
 * better. For knowing so, we use a provider that will be injected to us from our creator.
 *
 * This could be used at a presenter which is created by an {@link Activity}.
 *
 * The activity will either create us, providing the most suitable provider it thinks so (eg. a repository one because it's
 * decided by a backend service) or it will obtain us through injection (eg. using dagger / koin / whatever).
 */
public class ActivityRouter implements Router {

    private final WeakReference<Activity> activityRef;
    private final FeatureFlagProvider featureFlagProvider;

    public ActivityRouter(final Activity activity,
                          final FeatureFlagProvider featureFlagProvider) {
        this.activityRef = new WeakReference<>(activity);
        this.featureFlagProvider = featureFlagProvider;
    }

    @Override
    public void navigateBackwards() {
        final Activity activity = activityRef.get();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    /**
     * This sample uses a functional approach.
     *
     * If you want to see a more sealed-class one, see the {@link ImageLoadingInitializer} sample
     */
    @Override
    public void navigateHome() {
        final Activity activity = activityRef.get();

        if (activity != null) {
            final FeatureFlagResult result = featureFlagProvider.isFeatureEnabled(FeatureCatalog.HOME_V2);

            FeatureFlagResultExtension.onEnabled(result, enabled -> {
                final Intent intent = new Intent(activity, Activity.class); // Imagine we go here to Home V2
                activity.startActivity(intent);
                return Unit.INSTANCE;
            });

            FeatureFlagResultExtension.onDisabled(result, disabled -> {
                final Intent intent = new Intent(activity, Activity.class); // Imagine we go here to the old Home
                activity.startActivity(intent);
                return Unit.INSTANCE;
            });

            FeatureFlagResultExtension.onMissing(result, featureFlagResult -> {
                // Do something? It wasn't at the provider, you may want to log it somewhere
                // so you get notice of it.
                // Don't worry though, the default feature value will still be executed so it's bug free
                Log.w("Missing", "flag home v2 is missing at provider");
                return Unit.INSTANCE;
            });
        }
    }
}
