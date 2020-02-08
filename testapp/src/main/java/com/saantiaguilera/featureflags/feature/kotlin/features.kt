package com.saantiaguilera.featureflags.feature.kotlin

import com.saantiaguilera.featureflags.FeatureFlag

/**
 * We use a catalog as sample, because we don't want feature keys to mutate on runtime.
 *
 * This same scenario can be created with enums.
 */
object FeatureCatalog {
    val homeV2 = FeatureFlag("feature.home.new_design_v2", false, "Shows the newly designed Home V2")
    val horizontalSignIn = FeatureFlag("feature.login.horizontal_sign_in", false, "Shows the horizontal sign in modal")
    val frescoImages = FeatureFlag("feature.arch.fresco_images", true, "Enables fresco API for loading images")
    val cache2K = FeatureFlag("feature.arch.cache_2k", false, "Enables cache 2K for something")
    val cardPaymentsWithVisa = FeatureFlag("feature.checkout.card_payments_visa", false, "Enables card payments with VISA")
}
