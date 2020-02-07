package com.saantiaguilera.featureflags.provider.kotlin

import com.saantiaguilera.featureflags.FeatureFlagProvider

/**
 * Wrapper provider with a static priority as an integer. The higher the number, the bigger the priority
 */
class StaticPriorityProvider(
    private val provider: FeatureFlagProvider,
    override val priority: Int
) : FeatureFlagProvider by provider,
    StaticPriority

/**
 * Simple interface for denoting a static numbered priority
 */
interface StaticPriority {
    val priority: Int
}

/**
 * Comparator to be used for comparing static priorities ordering from highest first to lowest last
 * Note we don't depend on providers :)
 */
class StaticPriorityComparator : Comparator<StaticPriority> {
    override fun compare(o1: StaticPriority?, o2: StaticPriority?): Int {
        val x = o1?.priority ?: 0
        val y = o2?.priority ?: 0
        return if (x > y) -1 else if (x == y) 0 else 1
    }
}