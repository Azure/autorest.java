// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.encode.duration;

import com.encode.duration.models.DefaultDurationProperty;
import com.encode.duration.models.FloatSecondsDurationProperty;
import com.encode.duration.models.Int32SecondsDurationProperty;
import com.encode.duration.models.Iso8601DurationProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class EncodeDurationTests {

    private final QueryClient queryClient = new DurationClientBuilder().buildQueryClient();
    private final HeaderClient headerClient = new DurationClientBuilder().buildHeaderClient();
    private final PropertyClient propertyClient = new DurationClientBuilder().buildPropertyClient();

    private static final Duration DAY40 = Duration.ofDays(40);
    private static final Duration SECOND35 = Duration.ofSeconds(35, 621_000_000);
    private static final Duration SECOND36 = Duration.ofSeconds(36);

    @Test
    public void testQuery() {
        queryClient.defaultMethod(DAY40);

        queryClient.floatSeconds(SECOND35);

        queryClient.int32Seconds(SECOND36);

        queryClient.iso8601(DAY40);
    }

    @Test
    public void testHeader() {
        headerClient.defaultMethod(DAY40);

        headerClient.floatSeconds(SECOND35);

        headerClient.int32Seconds(SECOND36);

        headerClient.iso8601(DAY40);
    }

    @Test
    public void testProperty() {
        Assertions.assertEquals(DAY40,
                propertyClient.defaultMethod(new DefaultDurationProperty(DAY40)).getValue());

        Assertions.assertEquals(SECOND35,
                propertyClient.floatSeconds(new FloatSecondsDurationProperty(SECOND35)).getValue());

        Assertions.assertEquals(SECOND36,
                propertyClient.int32Seconds(new Int32SecondsDurationProperty(SECOND36)).getValue());

        propertyClient.iso8601(new Iso8601DurationProperty(DAY40));
    }
}
