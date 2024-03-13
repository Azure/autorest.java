// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.inheritance.passdiscriminator;

import com.azure.core.util.BinaryData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fixtures.inheritance.passdiscriminator.models.MetricAlertCriteria;
import fixtures.inheritance.passdiscriminator.models.MetricAlertSingleResourceMultipleMetricCriteria;
import fixtures.inheritance.passdiscriminator.models.Odatatype;
import org.junit.Test;

import java.io.IOException;
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

        String propertyDefaultDiscriminatorValue = new MetricAlertSingleResourceMultipleMetricCriteria()
            .getOdataType()
            .toString();

        for (Field declaredField : MetricAlertSingleResourceMultipleMetricCriteria.class.getDeclaredFields()) {
            JsonProperty jsonProperty = declaredField.getAnnotation(JsonProperty.class);
            if (jsonProperty == null) {
                continue;
            }

            if (Objects.equals(jsonTypeInfo.property(), jsonProperty.value())
                && declaredField.isAnnotationPresent(JsonTypeId.class)
                && Objects.equals(discriminatorValue, propertyDefaultDiscriminatorValue)) {
                return;
            }
        }

        fail("Generation didn't match expected pattern when passing discriminator property to child classes.");
    }

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void testJacksonSerialization() throws IOException {
        MetricAlertCriteria superclass = new MetricAlertCriteria();
        String superclassJson = BinaryData.fromObject(superclass).toString();
        JsonNode jsonNode = OBJECT_MAPPER.readTree(superclassJson);
        assertEquals("MetricAlertCriteria", jsonNode.get("odata.type").asText());

        MetricAlertCriteria subclass = new MetricAlertSingleResourceMultipleMetricCriteria();
        String subclassJson = BinaryData.fromObject(subclass).toString();
        jsonNode = OBJECT_MAPPER.readTree(subclassJson);
        assertEquals(Odatatype.MICROSOFT_AZURE_MONITOR_SINGLE_RESOURCE_MULTIPLE_METRIC_CRITERIA.toString(), jsonNode.get("odata.type").asText());
    }
}
