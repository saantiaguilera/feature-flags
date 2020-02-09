@file:JvmName("FeatureFlagResultExtension")
package com.saantiaguilera.featureflags

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Binary result of a feature-flag provision. It can be [enabled] and should denote if it was
 * found at the requested provider through [exists]
 */
data class FeatureFlagResult(
    @get:JvmName("enabled") val enabled: Boolean,
    @get:JvmName("exists") val exists: Boolean
) {
    /**
     * Convenience constructor for creating existing results
     *
     * This is useful if you already know all your flags will exist.
     */
    constructor(enabled: Boolean) : this(enabled, true)
}

/**
 * Performs the given [action] if this instance represents not [FeatureFlagResult.enabled].
 * Returns the original [FeatureFlagResult] unchanged.
 */
@UseExperimental(ExperimentalContracts::class)
inline fun FeatureFlagResult.onDisabled(action: (result: FeatureFlagResult) -> Unit): FeatureFlagResult {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (!enabled) {
        action(this)
    }
    return this
}

/**
 * Performs the given [action] if this instance represents [FeatureFlagResult.enabled].
 * Returns the original [FeatureFlagResult] unchanged.
 */
@UseExperimental(ExperimentalContracts::class)
inline fun FeatureFlagResult.onEnabled(action: (result: FeatureFlagResult) -> Unit): FeatureFlagResult {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (enabled) {
        action(this)
    }
    return this
}

/**
 * Performs the given [action] if this instance is a not [FeatureFlagResult.exists] queried feature.
 * Returns the original [FeatureFlagResult] unchanged.
 *
 * Note that this method is not mutually-exclusive with [onEnabled] and [onDisabled]. A feature-flag
 * can be missing **and** enabled/disabled (depending on its default value)
 */
@UseExperimental(ExperimentalContracts::class)
inline fun FeatureFlagResult.onMissing(action: (result: FeatureFlagResult) -> Unit): FeatureFlagResult {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (!exists) {
        action(this)
    }
    return this
}
