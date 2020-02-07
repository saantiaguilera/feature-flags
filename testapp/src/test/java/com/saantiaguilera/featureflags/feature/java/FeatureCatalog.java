package com.saantiaguilera.featureflags.feature.java;

import com.saantiaguilera.featureflags.FeatureFlag;

public final class FeatureCatalog {

    public static final FeatureFlag HOME_V2 = new FeatureFlag("feature.home.new_design_v2", false, "Shows the newly designed Home V2");
    public static final FeatureFlag HORIZONTAL_SIGN_IN = new FeatureFlag("feature.login.horizontal_sign_in", false, "Shows the horizontal sign in modal");
    public static final FeatureFlag FRESCO_IMAGES = new FeatureFlag("feature.arch.fresco_images", true, "Enables fresco API for loading images");
    public static final FeatureFlag CACHE_2K = new FeatureFlag("feature.arch.cache_2k", false, "Enables cache 2K for something");
    public static final FeatureFlag ENABLES_CARD_PAYMENTS_WITH_VISA = new FeatureFlag("feature.checkout.card_payments_visa", false, "Enables card payments with VISA");

    private FeatureCatalog() {
        // Empty
    }

}
