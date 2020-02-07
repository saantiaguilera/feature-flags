package com.saantiaguilera.featureflags

import java.io.Serializable

/**
 * Open class for defining feature flags.
 *
 * A feature flag shouldn't contain behaviours nor richer state, as it's solely reason of existence
 * is to transfer data.
 *
 * This definition is based on the [flag](https://golang.org/pkg/flag) package definition.
 */
open class FeatureFlag(val key: String, val defaultValue: Boolean, val usage: String) : Serializable {

    /**
     * Convert the default value to a result.
     *
     * This method is for internal usage.
     */
    internal fun toResult(): FeatureFlagResult {
        return if (defaultValue) {
            FeatureFlagResult.Enabled.Missing
        } else {
            FeatureFlagResult.Disabled.Missing
        }
    }
}
