// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.inheritance.passdiscriminator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import fixtures.inheritance.passdiscriminator.models.MetricAlertCriteria;
import fixtures.inheritance.passdiscriminator.models.MetricAlertSingleResourceMultipleMetricCriteria;
import fixtures.inheritance.passdiscriminator.models.Odatatype;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Objects;

import static junit.framework.TestCase.fail;
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
    public void subClassAcceptsDiscriminator() throws IllegalAccessException {
        JsonTypeInfo jsonTypeInfo = MetricAlertSingleResourceMultipleMetricCriteria.class
            .getAnnotation(JsonTypeInfo.class);
        assertNotNull(jsonTypeInfo);
        assertTrue(jsonTypeInfo.visible());
        assertEquals(JsonTypeInfo.As.EXISTING_PROPERTY, jsonTypeInfo.include());

        String discriminatorValue = MetricAlertSingleResourceMultipleMetricCriteria.class
            .getAnnotation(JsonTypeName.class)
            .value();

        for (Field declaredField : MetricAlertSingleResourceMultipleMetricCriteria.class.getDeclaredFields()) {
            JsonProperty jsonProperty = declaredField.getAnnotation(JsonProperty.class);
            if (jsonProperty == null) {
                continue;
            }

            Odatatype propertyDefaultDiscriminatorValue = (Odatatype) declaredField
                .get(new MetricAlertSingleResourceMultipleMetricCriteria());

            if (Objects.equals(jsonTypeInfo.property(), jsonProperty.value())
                && declaredField.isAnnotationPresent(JsonTypeId.class)
                && Objects.equals(discriminatorValue, propertyDefaultDiscriminatorValue.toString())) {
                return;
            }
        }

        fail("Generation didn't match expected pattern when passing discriminator property to child classes.");
    }
}
