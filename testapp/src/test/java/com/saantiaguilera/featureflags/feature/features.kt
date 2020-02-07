package com.saantiaguilera.featureflags.feature

import com.saantiaguilera.featureflags.FeatureFlag

/**
 * We use a catalog as sample, because we don't want feature keys to mutate on runtime.
 *
 * This same scenario can be created with enums.
 */
sealed class FeatureCatalog(
    key: String,
    value: Boolean,
    usage: String
) : FeatureFlag(key, value, usage) {

    object HomeV2 : FeatureCatalog("feature.home.new_design_v2", false, "Shows the newly designed Home V2")
    object HorizontalSignIn : FeatureCatalog("feature.login.horizontal_sign_in", false, "Shows the horizontal sign in modal")
    object FrescoImages : FeatureCatalog("feature.arch.fresco_images", true, "Enables fresco API for loading images")
    object Cache2K : FeatureCatalog("feature.arch.cache_2k", false, "Enables cache 2K for something")
    object CardPaymentsWithVisa : FeatureCatalog("feature.checkout.card_payments_visa", false, "Enables card payments with VISA")

}
