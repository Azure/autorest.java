// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.inheritance.passdiscriminator;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fixtures.inheritance.donotpassdiscriminator.MetricAlerts;
import fixtures.inheritance.passdiscriminator.models.MetricAlertSingleResourceMultipleMetricCriteria;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidateDiscriminatorIsPassedTests {
    @Test
    public void superClassPassesDiscriminator() {
        JsonTypeInfo jsonTypeInfo = MetricAlerts.class.getAnnotation(JsonTypeInfo.class);
        assertNotNull(jsonTypeInfo);
        assertTrue(jsonTypeInfo.visible());
        assertEquals(JsonTypeInfo.As.EXISTING_PROPERTY, jsonTypeInfo.include());
    }

    @Test
    public void subClassAcceptsDiscriminator() {
        JsonTypeInfo jsonTypeInfo = MetricAlertSingleResourceMultipleMetricCriteria.class
            .getAnnotation(JsonTypeInfo.class);
        assertNotNull(jsonTypeInfo);
        assertTrue(jsonTypeInfo.visible());
        assertEquals(JsonTypeInfo.As.EXISTING_PROPERTY, jsonTypeInfo.include());
    }
}
