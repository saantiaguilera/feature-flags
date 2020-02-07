# Feature Toggles (aka Feature Flags) in Kotlin

This project is based completely on [Feature Toggles by Martin Fowler](https://martinfowler.com/articles/feature-toggles.html), it's available for any JVM environment

Feature Toggles (often also referred to as Feature Flags) are a powerful technique, allowing teams to modify system behavior without changing code. They fall into various usage categories, and it's important to take that categorization into account when implementing and managing toggles. Toggles introduce complexity. We can keep that complexity in check by using smart toggle implementation practices and appropriate tools to manage our toggle configuration, but we should also aim to constrain the number of toggles in our system.

The API was heavily inspired by the following contents:
- [Feature Toggles by Martin Fowler](https://martinfowler.com/articles/feature-toggles.html)
- [Go flag package](https://golang.org/pkg/flag/)
- [Feature flags architecture](https://jeroenmols.com/blog/2019/09/12/featureflagsarchitecture/)

## Set Up

We are using sem-ver. You can find the latest stable version under the Github Releases tab.
```gradle
implementation "com.saantiaguilera.featureflags:feature-flags:<version>"
```

No proguard / R8 rules needed, this works transparently with it :)

## Usage

Usage is very diverse, since it can fit most of the developers needs. 

Consider to:
- Refer to the documentation all around the code for knowing flows and responsibilities.
- Refer to the testapp to see samples of feature-flags, providers, usages and tests.
- Refer to the tests to understand what's the desired behaviour of each method / class.

The basics should be:

### Creating a feature-flag

Features are easily declared through a `FeatureFlag` class. A feature flag contains only 3 properties:
- key: Used for identifying the feature you are referring. This key can easily be used as an index in a database, as an ID from a backend response, etc.
- defaultValue: Used as a default when no feature is present at the providers for the given key. This is to avoid possible bugs or undesired behaviours. Consumer safety first.
- usage: String specifying the usage / description / explanation of this feature.

When defining them, you can keep them centralized under a catalog or scattered around your project, it's up to you.

If you have a completely modular app, you may probably want a finite number of catalogs (grouped by modules)

#### Single feature
```kotlin
object FrescoImages : FeatureCatalog("feature.home.fresco_images", true, "Enables fresco API for loading images")
```

#### Catalog features
```kotlin
sealed class HomeFeatureCatalog(
    key: String,
    value: Boolean,
    usage: String
) : FeatureFlag(key, value, usage) {
    object HomeV2 : FeatureCatalog("feature.home.new_design_v2", false, "Shows the newly designed Home V2")
    object GridElements : FeatureCatalog("feature.home.grid_elements", false, "Shows the elements as a grid instead of cards")
    object FrescoImages : FeatureCatalog("feature.home.fresco_images", true, "Enables fresco API for loading images")
    object Cache2K : FeatureCatalog("feature.home.cache_2k", false, "Enables cache 2K for caching the home API results")
}
```

### Creating a provider

You can find some implemented providers to use as examples under the [testapp directory](testapp/src/test/java/com/saantiaguilera/featureflags/provider)

A provider is in charge of deciding if a given feature-flag is enabled or disabled. It can be through a cache, api-call, shared-preference, or any other strategy. If it doesn't has a requested feature-flag, it should return the default feature value marking it as missing.

Also, an implementation can contain specific states to enrich its behaviours, eg. it can have a `User` to request specific user flags (or have more complex decisions depending on the user's data). it could also use this same `User` to perform AB Testings if it wanted to.

An example of a simple provider implementation using an external service (backend) as a feature-flag storage could be:

```kotlin
class RepositoryProvider(private val repository: RepositoryApi) : FeatureFlagProvider {

    // Your API returns String:Boolean pairs denoting a feature's key and if it's enabled or not.
    // Eg: Pair("feature.home.new_design_v2", true)
    private var features: List<Pair<String, Boolean>> = emptyList()

    init {
        // Consider doing it in another thread or coroutine ;)
        features = repository.getFeatures()
    }

    override fun isFeatureEnabled(feature: FeatureFlag): FeatureFlagResult {
        return features.find {
                it.first == feature.key // We try finding the same requested feature in our features
            }
            ?.second?.let { createExistingResult(it) } // If found, we return an existing result with it's value
            ?: createMissingResult(feature.defaultValue) // If not, we return the default value
    }
}
```

You can easily create other types such as:
- Cache providers
- Repository providers
- Firebase Remote Config providers
- SharedPreferences providers
- Entity specific providers (eg. that depends on `User` or any DTO)
- AB Testing providers
- Mock providers (for testing or debug builds)

Since it's just a contract, you can create them with whatever you want to. You can even make them refreshable if you'd like (by creating a `Refreshable` interface and calling it whenever you want to)

All of this can be found under the [testapp directory](testapp/src/test/java/com/saantiaguilera/featureflags/provider)

#### Priority Providers

If you wish to have provider groupings, we provide by default a `PriorityFeatureFlagProvider` that groups providers and through a `Comparator` you can decide the priority in which a key will be looked up.

You can find an example of usage under the [testapp directory](testapp/src/test/java/com/saantiaguilera/featureflags/provider) or in the tests.

### Consuming a provider

Once you have your providers and feature-flags. You can start using them anywhere.

#### Sealed-class usage
```kotlin
fun navigateHome(featureFlagProvider: FeatureFlagProvider) {
    val result = featureFlagProvider.isFeatureEnabled(FeatureCatalog.HomeV2)

    if (!result.exists) {
        // Do something? It wasn't at the provider, you may want to log it somewhere
        // so you get notice of it.
        // Don't worry though, the default feature value will still be used so it's bug free
    }

    when (result) {
        is FeatureFlagResult.Enabled -> {
            // Navigate to home v2
        }
        is FeatureFlagResult.Disabled -> {
            // Navigate to home v1
        }
    }
}
```
#### Functional usage
```kotlin
fun navigateHome(featureFlagProvider: FeatureFlagProvider) {
    featureFlagProvider.isFeatureEnabled(FeatureCatalog.HomeV2)
        .onEnabled {
            // Navigate to home v2
        }
        .onDisabled {
            // Navigate to home v1
        }
        .onMissing {
            // Do something? It wasn't found at the provider, you may want to log it somewhere
            // so you get notice of it.
            // Don't worry though, the default feature value will still be executed so it's bug free
        }
}
```
