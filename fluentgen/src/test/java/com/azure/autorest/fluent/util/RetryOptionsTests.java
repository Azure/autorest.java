// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.util;

import com.azure.core.http.policy.ExponentialBackoff;
import com.azure.core.http.policy.ExponentialBackoffOptions;
import com.azure.core.http.policy.FixedDelay;
import com.azure.core.http.policy.FixedDelayOptions;
import com.azure.core.http.policy.RetryOptions;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.RetryStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class RetryOptionsTests {

    public static class Configurable {
        // see Manager_Configurable.txt

        private RetryPolicy retryPolicy;
        private RetryOptions retryOptions;

        /**
         * Sets the retry policy to the HTTP pipeline.
         *
         * @param retryPolicy the HTTP pipeline retry policy.
         * @return the configurable object itself.
         */
        public Configurable withRetryPolicy(RetryPolicy retryPolicy) {
            this.retryPolicy = Objects.requireNonNull(retryPolicy, "'retryPolicy' cannot be null.");
            return this;
        }

        /**
         * Sets the retry options for the HTTP pipeline retry policy.
         * <p>
         * This setting has no effect, if retry policy is set via {@link #withRetryPolicy(RetryPolicy)}.
         *
         * @param retryOptions the retry options for the HTTP pipeline retry policy.
         * @return the configurable object itself.
         */
        public Configurable withRetryOptions(RetryOptions retryOptions) {
            this.retryOptions = Objects.requireNonNull(retryOptions, "'retryOptions' cannot be null.");
            return this;
        }

        public RetryPolicy getRetryPolicy() {
            if (retryPolicy == null) {
                if (retryOptions != null) {
                    RetryStrategy retryStrategy = null;
                    if (retryOptions.getExponentialBackoffOptions() != null) {
                        retryStrategy = new ExponentialBackoff(retryOptions.getExponentialBackoffOptions());
                    } else if (retryOptions.getFixedDelayOptions() != null) {
                        retryStrategy = new FixedDelay(retryOptions.getFixedDelayOptions());
                    }
                    if (retryStrategy != null) {
                        retryPolicy = new RetryPolicy(retryStrategy, "Retry-After", ChronoUnit.SECONDS);
                    }
                }
            }
            if (retryPolicy == null) {
                retryPolicy = new RetryPolicy("Retry-After", ChronoUnit.SECONDS);
            }
            return retryPolicy;
        }
    }

    @Test
    public void testManagerConfigurableRetryOptions() throws Exception {
        // not configured
        Configurable configurable = new Configurable();
        validateRetryPolicy(configurable.getRetryPolicy());

        // configured as FixedDelayOptions
        configurable = new Configurable().withRetryOptions(new RetryOptions(new FixedDelayOptions(3, Duration.ofSeconds(10))));
        validateRetryPolicy(configurable.getRetryPolicy(), FixedDelay.class);

        // configured as ExponentialBackoffOptions
        configurable = new Configurable().withRetryOptions(new RetryOptions(new ExponentialBackoffOptions()));
        validateRetryPolicy(configurable.getRetryPolicy(), ExponentialBackoff.class);

        // RetryPolicy override RetryOptions
        configurable = new Configurable().withRetryPolicy(new RetryPolicy()).withRetryOptions(new RetryOptions(new FixedDelayOptions(3, Duration.ofSeconds(10))));
        Field retryAfterHeaderField = RetryPolicy.class.getDeclaredField("retryAfterHeader");
        retryAfterHeaderField.setAccessible(true);
        String retryAfterHeader = (String) retryAfterHeaderField.get(configurable.getRetryPolicy());
        Assertions.assertNull(retryAfterHeader);
    }

    private static void validateRetryPolicy(RetryPolicy retryPolicy) throws NoSuchFieldException, IllegalAccessException {
        validateRetryPolicy(retryPolicy, null);
    }

    private static void validateRetryPolicy(RetryPolicy retryPolicy, Class<?> retryStrategyClass) throws NoSuchFieldException, IllegalAccessException {
        Assertions.assertNotNull(retryPolicy);

        Field retryAfterHeaderField = RetryPolicy.class.getDeclaredField("retryAfterHeader");
        Field retryAfterTimeUnitField = RetryPolicy.class.getDeclaredField("retryAfterTimeUnit");
        Field retryStrategyField = RetryPolicy.class.getDeclaredField("retryStrategy");

        retryAfterHeaderField.setAccessible(true);
        retryAfterTimeUnitField.setAccessible(true);
        retryStrategyField.setAccessible(true);
        String retryAfterHeader = (String) retryAfterHeaderField.get(retryPolicy);
        ChronoUnit retryAfterTimeUnit = (ChronoUnit) retryAfterTimeUnitField.get(retryPolicy);
        Assertions.assertEquals("Retry-After", retryAfterHeader);
        Assertions.assertEquals(ChronoUnit.SECONDS, retryAfterTimeUnit);

        if (retryStrategyClass != null) {
            RetryStrategy retryStrategy = (RetryStrategy) retryStrategyField.get(retryPolicy);
            Assertions.assertTrue(retryStrategyClass.isAssignableFrom(retryStrategy.getClass()));
        }
    }
}
