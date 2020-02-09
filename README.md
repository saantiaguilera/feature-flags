<p align="center">
    <img width="175" align="center"  src="https://github.com/saantiaguilera/feature-flags/raw/master/images/icon.jpeg" /><br>
    <br>
    <b>Feature Toggles (aka Feature Flags) in Kotlin</b>
</p>

![Build](https://github.com/saantiaguilera/feature-flags/workflows/CI/badge.svg) [![Coverage](https://codecov.io/gh/saantiaguilera/feature-flags/branch/master/graph/badge.svg)](https://codecov.io/gh/saantiaguilera/feature-flags)

This project is based completely on [Feature Toggles by Martin Fowler](https://martinfowler.com/articles/feature-toggles.html), it's available for any JVM environment

_Feature Toggles (often also referred to as Feature Flags) are a powerful technique, allowing teams to modify system behavior without changing code. They fall into various usage categories, and it's important to take that categorization into account when implementing and managing toggles. Toggles introduce complexity. We can keep that complexity in check by using smart toggle implementation practices and appropriate tools to manage our toggle configuration, but we should also aim to constrain the number of toggles in our system._

## Set Up

We are using sem-ver. You can find the latest stable version under the Github Releases tab.
```gradle
implementation "com.saantiaguilera.featureflags:feature-flags:<version>"
```

No proguard / R8 rules needed, this works transparently with it :)

## Usage

Usage is very diverse, since it can fit most of the developers needs. The basics should be:

### Creating a feature-flag

Features are easily declared through a `FeatureFlag` class. A feature flag contains only 3 properties:
- key: Used for identifying the feature you are referring. This key can easily be used as an index in a database, as an ID from a backend response, etc.
- defaultValue: Used as a default when no feature is present at the providers for the given key. This is to avoid possible bugs or undesired behaviours. Consumer safety first.
- usage: String specifying the usage / description / explanation of this feature.

When defining them, you can keep them centralized under a catalog or scattered around your project, it's up to you.

If you have a completely modular app, you may probably want a finite number of catalogs (grouped by modules)

#### Single feature
```kotlin
val frescoImages = FeatureFlag("feature.home.fresco_images", true, "Enables fresco API for loading images")
```

#### Catalog features
```kotlin
object FeatureCatalog {
    val homeV2 = FeatureFlag("feature.home.new_design_v2", false, "Shows the newly designed Home V2")
    val horizontalSignIn = FeatureFlag("feature.login.horizontal_sign_in", false, "Shows the horizontal sign in modal")
    val frescoImages = FeatureFlag("feature.home.fresco_images", true, "Enables fresco API for loading images")
    val cache2K = FeatureFlag("feature.arch.cache_2k", false, "Enables cache 2K for something")
    val cardPaymentsWithVisa = FeatureFlag("feature.checkout.card_payments_visa", false, "Enables card payments with VISA")
}
```

### Creating a provider

You can find some implemented providers to use as examples under the [testapp directory](testapp/src/main/java/com/saantiaguilera/featureflags)

A provider is in charge of deciding if a given feature-flag is enabled or disabled. It can be through a cache, api-call, shared-preference, or any other strategy. If it doesn't has a requested feature-flag, it should return the default feature value marking it as missing. An implementation can contain any specific states to enrich its behaviours, eg. it can have a `User` to request specific user flags (or have more complex decisions depending on the user's data). It could also use this same `User` to perform AB Testings if it wanted to.

An example of a simple provider implementation using an external service (backend) as a feature-flag storage could be:

```kotlin
class RepositoryProvider(private val repository: RepositoryApi) : FeatureFlagProvider {

    // This API sample returns a map of String:Boolean denoting a feature's key and if it's enabled or not.
    // Eg: "feature.home.new_design_v2": true
    private var features: Map<String, Boolean> = emptyMap()

    init {
        // Consider doing it in another thread or coroutine ;)
        features = repository.getFeatures()
    }

    override fun provide(feature: FeatureFlag): FeatureFlagResult {
        return repository.getFeatures()[feature.key]
            ?.let { FeatureFlagResult(feature, it) } // If not null, we return an existing result with it's value 
            ?: FeatureFlagResult(feature) // If null we return the default value
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
- Free/Subscribed/Premium feature-flag provider (permission toggles)
- Mock providers (for testing or debug builds)

Since it's just a contract, you can create them with whatever you want to. You can even make them refreshable if you'd like (by creating a `Refreshable` interface and calling it whenever you want to)

All of the above can be found under the [testapp directory](testapp/src/main/java/com/saantiaguilera/featureflags)

#### Priority Providers

If you wish to have provider groupings, we provide by default a `PriorityFeatureFlagProvider` that groups providers and through a `Comparator` you can decide the priority in which a key will be looked up.

You can find an example of usage under the [testapp directory](testapp/src/main/java/com/saantiaguilera/featureflags) or in the tests.

### Consuming a provider

Once you have your providers and feature-flags you can start using them anywhere.

In the following samples I will simply show how to consume the API. _It's [recommended](https://martinfowler.com/articles/feature-toggles.html) to apply inversion of decision so you can avoid conditionals and code branches._

#### Functional usage
```kotlin
fun navigateHome(featureFlagProvider: FeatureFlagProvider) {
    featureFlagProvider.provide(FeatureCatalog.HomeV2)
        .onEnabled {
            // Navigate to home v2
        }
        .onDisabled {
            // Navigate to home v1
        }
}
```
#### Conditional usage
```kotlin
fun navigateHome(featureFlagProvider: FeatureFlagProvider) {
    val result = featureFlagProvider.provide(FeatureCatalog.HomeV2)

    if (result.enabled) {
        // Navigate to home v2
    } else {
        // Navigate to home v1
    }
}
```

### Handling missing feature-flags

Regardless of the result, a flag may have been missing at the provider (and the provided result was simply a default one).

#### Functional usage
```kotlin
fun navigateHome(featureFlagProvider: FeatureFlagProvider) {
    featureFlagProvider.provide(FeatureCatalog.HomeV2)
        .onMissing {
            // Do something? It wasn't found at the provider, you may want to log it somewhere
            // so you get notice of it.
            // Don't worry though, the default feature value will still be executed so it's bug free
        }
}
```

#### Conditional usage
```kotlin
fun navigateHome(featureFlagProvider: FeatureFlagProvider) {
    val result = featureFlagProvider.provide(FeatureCatalog.HomeV2)
    
    if (!result.exists) {
        // Do something? It wasn't at the provider, you may want to log it somewhere
        // so you get notice of it.
        // Don't worry though, the default feature value will still be used afterwards
        // for checking if it's enabled
    }
}
```

### Tech FAQ

#### Why isn't a simple interface with isFeatureEnabled and hasFeature?

Because we would need 2 accesses to the provider on any request (we want to **always** know first if 
it exists). There are no specifications on how the provider should retrieve a flag, hence we want
to make the least possible calls per request. The feature of knowing (optionally) if a flag exists
at the provider can easily be achieved with a sealed-class or a `Boolean?`.

On a side note, it looks more neat to just ask once for something.

#### Why isn't just a `Boolean` or `Boolean?` the result, instead of a sealed class

While seeing the requirements we saw that there were chances our servers didn't have a flag (because
of developers mistakes or accessing different scopes). We wanted to know about these edge cases,
but we couldn't do it if the result was a Boolean (what if `false` means it's actually `false`, and not
missing?).

Still, using a `Boolean?` would be a pain for those who don't care about missing flags. People should 
always have to validate 3 cases (`null` / `false` / `true`) instead of two. Hence, a sealed class result 
gives all these advantages (with the only downside of having to use results instead of plain booleans)  

### Mentions

The API was heavily inspired by the following contents:
- [Feature Toggles by Martin Fowler](https://martinfowler.com/articles/feature-toggles.html)
- [Go flag package](https://golang.org/pkg/flag/)
- [Feature flags architecture](https://jeroenmols.com/blog/2019/09/12/featureflagsarchitecture/)
