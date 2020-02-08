package com.saantiaguilera.featureflags

import org.junit.Assert
import org.junit.Test

class FeatureFlagResultTest {

    @Test
    fun `Test when creating an existing enabled result, then it exists and is enabled`() {
        val result = FeatureFlagResult.create(value = true)

        Assert.assertTrue(result is FeatureFlagResult.Enabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test when creating an existing disabled result, then it exists and is disabled`() {
        val result = FeatureFlagResult.create(value = false)

        Assert.assertTrue(result is FeatureFlagResult.Disabled)
        Assert.assertTrue(result.exists)
    }

    @Test
    fun `Test when creating a non existing enabled result, then it doesn't exist and is enabled`() {
        val result = FeatureFlagResult.create(value = true, exists = false)

        Assert.assertTrue(result is FeatureFlagResult.Enabled)
        Assert.assertFalse(result.exists)
    }

    @Test
    fun `Test when creating a non existing disabled result, then it doesn't exists and is disabled`() {
        val result = FeatureFlagResult.create(value = false, exists = false)

        Assert.assertTrue(result is FeatureFlagResult.Disabled)
        Assert.assertFalse(result.exists)
    }

    @Test
    fun `Test given an existing result, when running a missing block, then nothing happens`() {
        var called = false
        val result = FeatureFlagResult.create(value = true)

        result.onMissing { called = true }

        Assert.assertFalse(called)
    }

    @Test
    fun `Test given a missing result, when running a missing block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.create(value = true, exists = false)

        result.onMissing { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given an existing enabled result, when running an enabled block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.create(value = true)

        result.onEnabled { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given a missing enabled result, when running an enabled block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.create(value = true, exists = false)

        result.onEnabled { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given a disabled result, when running an enabled block, then nothing happens`() {
        var called = false
        val result = FeatureFlagResult.create(value = false)

        result.onEnabled { called = true }

        Assert.assertFalse(called)
    }

    @Test
    fun `Test given an existing disabled result, when running a disabled block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.create(value = false)

        result.onDisabled { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given a missing disabled result, when running a disabled block, then it's called`() {
        var called = false
        val result = FeatureFlagResult.create(value = false, exists = false)

        result.onDisabled { called = true }

        Assert.assertTrue(called)
    }

    @Test
    fun `Test given an enabled result, when running a disabled block, then nothing happens`() {
        var called = false
        val result = FeatureFlagResult.create(value = true)

        result.onDisabled { called = true }

        Assert.assertFalse(called)
    }

    @Test
    fun `Test given an existing enabled result, when calling is enabled, then returns true`() {
        val result = FeatureFlagResult.create(value = true)

        Assert.assertTrue(result.enabled)
    }

    @Test
    fun `Test given a missing existing result, when calling is enabled, then returns true`() {
        val result = FeatureFlagResult.create(value = true, exists = false)

        Assert.assertTrue(result.enabled)
    }

    @Test
    fun `Test given an existing disabled result, when calling is enabled, then returns true`() {
        val result = FeatureFlagResult.create(value = false)

        Assert.assertFalse(result.enabled)
    }

    @Test
    fun `Test given a missing disabled result, when calling is enabled, then returns true`() {
        val result = FeatureFlagResult.create(value = false, exists = false)

        Assert.assertFalse(result.enabled)
    }
}
