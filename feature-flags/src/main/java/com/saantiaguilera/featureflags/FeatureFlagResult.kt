@file:JvmName("FeatureFlagResultExtension")
package com.saantiaguilera.featureflags

import com.saantiaguilera.featureflags.FeatureFlagResult.Disabled
import com.saantiaguilera.featureflags.FeatureFlagResult.Enabled
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Binary result of a feature-flag provision. It can be either [Enabled] or [Disabled].
 *
 * On advanced usages, you can also check if it was found at a provider through a depth 2 lookup of
 * [Enabled.Existing] / [Enabled.Missing] or the opposing [Disabled.Existing] / [Disabled.Missing]
 */
sealed class FeatureFlagResult(
    @get:JvmName("enabled") val enabled: Boolean,
    @get:JvmName("exists") open val exists: Boolean
) {
    /**
     * Enabled result means the flag is enabled.
     * Regardless of this operation, the flag might've not been found (and this was a default value)
     */
    sealed class Enabled(override val exists: Boolean) : FeatureFlagResult(true, exists) {

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
    sealed class Disabled(override val exists: Boolean) : FeatureFlagResult(false, exists) {

        /**
         * Disabled and existing result. This means it was found by the provider.
         */
        object Existing : Disabled(true)

        /**
         * Disabled and missing result. This means it wasn't found by the provider
         */
        object Missing : Disabled(false)
    }

    companion object {
        /**
         * Create a result from a given value.
         *
         * This is a shortcut to using [Enabled.Existing] or [Disabled.Existing] depending on the
         * input.
         *
         * By default, the result will be existing. You can specify as a second optional parameter
         * if it wasn't found (hence, it was missing at the provider)
         * This will also be a shortcut to using [Enabled.Missing] or [Disabled.Missing] if a
         * second parameter is specified
         */
        @JvmStatic
        @JvmOverloads
        fun create(value: Boolean, exists: Boolean = true): FeatureFlagResult {
            /*
             * Using a 2 ifs implementation because this case is a simple binary-tree
             * representation of depth 2.
             *
             * Since all instances are singletons, it's way cheaper to make two branch instructions
             * than to keep at the heap a binary tree map of this representation and accessing it:
             * decisionTree[value]!![exists]!! -> 2 O(1) GET + 2 null-checks branchings.
             * (Not even looking at a strategy pattern for this simple thing)
             *
             * Both are equally readable so we opted for this.
             */
            return if (value) {
                if (exists) {
                    Enabled.Existing
                } else {
                    Enabled.Missing
                }
            } else {
                if (exists) {
                    Disabled.Existing
                } else {
                    Disabled.Missing
                }
            }
        }
    }
}

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
