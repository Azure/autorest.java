// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.inheritance.donotpassdiscriminator;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fixtures.inheritance.donotpassdiscriminator.models.MetricAlertCriteria;
import fixtures.inheritance.donotpassdiscriminator.models.MetricAlertSingleResourceMultipleMetricCriteria;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ValidateDiscriminatorIsNotPassedTests {
    @Test
    public void superClassDoesNotPassDiscriminator() {
        JsonTypeInfo jsonTypeInfo = MetricAlertCriteria.class.getAnnotation(JsonTypeInfo.class);
        assertNotNull(jsonTypeInfo);
        assertFalse(jsonTypeInfo.visible());
        assertEquals(JsonTypeInfo.As.PROPERTY, jsonTypeInfo.include());
    }

    @Test
    public void subClassDoesNotAcceptDiscriminator() {
        JsonTypeInfo jsonTypeInfo = MetricAlertSingleResourceMultipleMetricCriteria.class
            .getAnnotation(JsonTypeInfo.class);
        assertNotNull(jsonTypeInfo);
        assertFalse(jsonTypeInfo.visible());
        assertEquals(JsonTypeInfo.As.PROPERTY, jsonTypeInfo.include());
    }
}
