package com.saantiaguilera.featureflags

import java.io.Serializable

/**
 * data class for defining feature flags. The reason it's not an interface is because a
 * feature flag shouldn't contain behaviours nor richer state, as it's solely reason of existence
 * is to transfer data.
 *
 * This definition is based on the [flag](https://golang.org/pkg/flag) package definition.
 */
data class FeatureFlag(val key: String, val value: Boolean, val usage: String) : Serializable
