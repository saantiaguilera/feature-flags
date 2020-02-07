package com.saantiaguilera.featureflags

import com.saantiaguilera.featureflags.FeatureFlagResult.Disabled
import com.saantiaguilera.featureflags.FeatureFlagResult.Enabled
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Result of a feature-flag fetch.
 *
 * The result can only be either [Enabled] or [Disabled].
 * Still, both could've been found or not, which is denoted through [exists].
 */
sealed class FeatureFlagResult(open val exists: Boolean) {

    /**
     * Enabled result means the flag is enabled.
     * Regardless of this operation, the flag might've not been found (and this was a default value)
     */
    sealed class Enabled(override val exists: Boolean) : FeatureFlagResult(exists) {
        /**
         * Enabled and existing result. This means it was found by the provider.
         */
        object Existing : Enabled(true)

        /**
         * Enabled and missing result. This means it wasn't found by the provider
         */
        object Missing : Enabled(false)
    }

    /**
     * Disabled result means the flag is disabled.
     * Regardless of this operation, the flag might've not been found (and this was a default value)
     */
    sealed class Disabled(override val exists: Boolean) : FeatureFlagResult(exists) {
        /**
         * Disabled and existing result. This means it was found by the provider.
         */
        object Existing : Disabled(true)

        /**
         * Disabled and missing result. This means it wasn't found by the provider
         */
        object Missing : Disabled(false)
    }
}

/**
 * Create a result from a given value and availability.
 * This is for internal purposes
 */
internal fun create(value: Boolean, available: Boolean): FeatureFlagResult {
    return if (value) {
        if (available) {
            Enabled.Existing
        } else {
            Enabled.Missing
        }
    } else {
        if (available) {
            Disabled.Existing
        } else {
            Disabled.Missing
        }
    }
}

/**
 * Create a result that was found, representing a given value.
 */
fun createExistingResult(value: Boolean): FeatureFlagResult = create(value, true)

/**
 * Create a result that wasn't found, representing a given value.
 */
fun createMissingResult(value: Boolean): FeatureFlagResult = create(value, false)

/**
 * Performs the given [action] if this instance represents [disable][FeatureFlagResult.Disabled].
 * Returns the original `FeatureFlagResult` unchanged.
 */
@UseExperimental(ExperimentalContracts::class)
inline fun FeatureFlagResult.onDisabled(action: (result: Disabled) -> Unit): FeatureFlagResult {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is Disabled) {
        action(this)
    }
    return this
}

/**
 * Performs the given [action] if this instance represents [enabled][FeatureFlagResult.Enabled].
 * Returns the original `FeatureFlagResult` unchanged.
 */
@UseExperimental(ExperimentalContracts::class)
inline fun FeatureFlagResult.onEnabled(action: (result: Enabled) -> Unit): FeatureFlagResult {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is Enabled) {
        action(this)
    }
    return this
}

/**
 * Performs the given [action] if this instance is a not existing queried feature.
 * Returns the original `FeatureFlagResult` unchanged.
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