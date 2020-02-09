package com.saantiaguilera.featureflags.sample.java;

import android.util.Log;

import com.saantiaguilera.featureflags.FeatureFlagProvider;
import com.saantiaguilera.featureflags.FeatureFlagResult;
import com.saantiaguilera.featureflags.feature.java.FeatureCatalog;
import com.saantiaguilera.featureflags.sample.kotlin.Initializer;

/**
 * Sample of an image loader initializer to decide how we will operate external images loading into
 * views.
 *
 * In this sample we could have an AB testing checking which of Fresco or Picasso performs
 * better. For knowing so, we use a provider that will be injected to us from our creator.
 *
 * This could be used at a MainApplication, that will either create us providing the most suitable
 * provider it thinks so (eg. a repository one because it's decided by a backend service) or it will
 * obtain us through injection (eg. koin / dagger / etc).
 */
public class ImageLoadingInitializer implements Initializer {

    private final FeatureFlagProvider featureFlagProvider;

    public ImageLoadingInitializer(final FeatureFlagProvider featureFlagProvider) {
        this.featureFlagProvider = featureFlagProvider;
    }

    /**
     * This sample uses a sealed-class operation approach (in java, which is instanceof).
     *
     * If you want to see a more functional one, see the {@link ActivityRouter} sample
     */
    @Override
    public void initialize() {
        final FeatureFlagResult result = featureFlagProvider.provide(FeatureCatalog.FRESCO_IMAGES);

        if (!result.exists()) {
            // Do something? It wasn't at the provider, you may want to log it somewhere
            // so you get notice of it.
            // Don't worry though, the default feature value will still be used so it's bug free
            Log.w("Missing", "Fresco feature isn't at the provider");
        }

        if (result.enabled()) {
            // Initialize with fresco
        } else {
            // Initialize with picasso
        }
    }

}
