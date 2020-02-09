package com.saantiaguilera.featureflags

import java.io.Serializable

/**
 * Simple data class for defining feature flags.
 *
 * This definition is based on the [flag](https://golang.org/pkg/flag) package definition.
 *
 * Example:
 * ```kotlin
 * object PaymentsFeatureCatalog {
 *     val enableVisaPayments = FeatureFlag(
 *         "feature.payments.cards.visa",
 *         false,
 *         "Denotes the user should be able to make payments using VISA cards"
 *     )
 * }
 * ```
 */
data class FeatureFlag(val key: String, val value: Boolean, val usage: String) : Serializable
