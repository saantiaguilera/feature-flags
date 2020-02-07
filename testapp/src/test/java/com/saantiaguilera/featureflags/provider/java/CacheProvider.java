package com.saantiaguilera.featureflags.provider.java;

import com.saantiaguilera.featureflags.FeatureFlag;
import com.saantiaguilera.featureflags.FeatureFlagProvider;
import com.saantiaguilera.featureflags.FeatureFlagResult;
import com.saantiaguilera.featureflags.FeatureFlagResultExtension;
import com.saantiaguilera.featureflags.provider.kotlin.CacheRepository;

import org.jetbrains.annotations.NotNull;

/**
 * Java example of one of the providers. Note that it's pretty much the same besides the APIs and
 * the usage of {@link FeatureFlagResultExtension} for extension functions.
 */
public class CacheProvider implements FeatureFlagProvider {

    private final CacheRepository repository;

    public CacheProvider(final CacheRepository repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public FeatureFlagResult isFeatureEnabled(@NotNull final FeatureFlag feature) {
        return repository.getFeatures()
                .stream()
                .filter(pair -> pair.getFirst().contentEquals(feature.getKey()))
                .map(pair -> FeatureFlagResultExtension.createExistingResult(pair.getSecond()))
                .findFirst()
                .orElseGet(() -> FeatureFlagResultExtension.createMissingResult(feature.getDefaultValue()));
    }

}
