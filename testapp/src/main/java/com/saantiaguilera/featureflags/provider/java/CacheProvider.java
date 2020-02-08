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
    public FeatureFlagResult provide(@NotNull final FeatureFlag feature) {
        final Boolean result = repository.getFeatures().getOrDefault(feature.getKey(), null);

        if (result == null) {
            return FeatureFlagResultExtension.createMissingResult(feature.getValue());
        } else {
            return FeatureFlagResultExtension.createExistingResult(result);
        }
    }

}
