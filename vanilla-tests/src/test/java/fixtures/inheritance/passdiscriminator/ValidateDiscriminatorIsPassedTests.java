// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.inheritance.passdiscriminator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fixtures.inheritance.passdiscriminator.models.MetricAlertCriteria;
import fixtures.inheritance.passdiscriminator.models.MetricAlertSingleResourceMultipleMetricCriteria;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidateDiscriminatorIsPassedTests {
    @Test
    public void superClassPassesDiscriminator() {
        JsonTypeInfo jsonTypeInfo = MetricAlertCriteria.class.getAnnotation(JsonTypeInfo.class);
        assertNotNull(jsonTypeInfo);
        assertTrue(jsonTypeInfo.visible());
        assertEquals(JsonTypeInfo.As.PROPERTY, jsonTypeInfo.include());
    }

    @Test
    public void subClassAcceptsDiscriminator() {
        JsonTypeInfo jsonTypeInfo = MetricAlertSingleResourceMultipleMetricCriteria.class
            .getAnnotation(JsonTypeInfo.class);
        assertNotNull(jsonTypeInfo);
        assertTrue(jsonTypeInfo.visible());
        assertEquals(JsonTypeInfo.As.EXISTING_PROPERTY, jsonTypeInfo.include());

        assertTrue(Arrays.stream(MetricAlertSingleResourceMultipleMetricCriteria.class.getDeclaredFields())
            .anyMatch(field -> {
                JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
                if (jsonProperty == null) {
                    return false;
                }

                return Objects.equals(jsonTypeInfo.property(), jsonProperty.value());
            }));
    }
}
