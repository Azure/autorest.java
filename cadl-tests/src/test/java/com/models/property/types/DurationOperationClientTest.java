// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.types;

import com.models.property.types.models.DurationProperty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DurationOperationClientTest {

    DurationOperationClient client = new DurationOperationClientBuilder().buildClient();

    @Disabled("cadl-ranch definition error")
    @Test
    void get() {
        DurationProperty durationProperty = client.get();
    }

    @Disabled("cadl-ranch definition error")
    @Test
    void put() {
    }
}