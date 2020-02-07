package com.saantiaguilera.featureflags

import org.junit.Assert
import org.junit.Test

class FeatureFlagResultTest {

    @Test
    fun `Test given an existing result, when running a missing block, then nothing happens`() {
        var called = false
        val result = FeatureFlagResult.Enabled.Existing

        result.onMissing { called = true }

        Assert.assertFalse(called)
    }

    @Test
    fun `Test given a missing result, when running a missing block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.Enabled.Missing

        result.onMissing { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given an existing enabled result, when running an enabled block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.Enabled.Existing

        result.onEnabled { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given an missing enabled result, when running an enabled block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.Enabled.Missing

        result.onEnabled { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given an disabled result, when running an enabled block, then nothing happens`() {
        var called = false
        val result = FeatureFlagResult.Disabled.Existing

        result.onEnabled { called = true }

        Assert.assertFalse(called)
    }

    @Test
    fun `Test given an existing disabled result, when running an disabled block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.Disabled.Existing

        result.onDisabled { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given an missing disabled result, when running an disabled block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.Disabled.Missing

        result.onDisabled { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given an enabled result, when running an disabled block, then nothing happens`() {
        var called = false
        val result = FeatureFlagResult.Enabled.Existing

        result.onDisabled { called = true }

        Assert.assertFalse(called)
    }
}