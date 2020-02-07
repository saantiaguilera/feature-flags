package com.saantiaguilera.featureflags

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
    data class Enabled(override val exists: Boolean) : FeatureFlagResult(exists)

    /**
     * Disabled result means the flag is disabled.
     * Regardless of this operation, the flag might've not been found (and this was a default value)
     */
    data class Disabled(override val exists: Boolean) : FeatureFlagResult(exists)
}

/**
 * Create a result from a given value and availability.
 * This is for internal purposes
 */
internal fun create(value: Boolean, available: Boolean): FeatureFlagResult {
    return when (value) {
        true -> FeatureFlagResult.Enabled(available)
        false -> FeatureFlagResult.Disabled(available)
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
inline fun FeatureFlagResult.onDisabled(action: (result: FeatureFlagResult.Disabled) -> Unit): FeatureFlagResult {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is FeatureFlagResult.Disabled) {
        action(this)
    }
    return this
}

/**
 * Performs the given [action] if this instance represents [enabled][FeatureFlagResult.Enabled].
 * Returns the original `FeatureFlagResult` unchanged.
 */
@UseExperimental(ExperimentalContracts::class)
inline fun FeatureFlagResult.onEnabled(action: (result: FeatureFlagResult.Enabled) -> Unit): FeatureFlagResult {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is FeatureFlagResult.Enabled) {
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