// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.inheritance;

import com.azure.core.util.BinaryData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fixtures.discriminatorflattening.models.MetricAlertCriteria;
import fixtures.discriminatorflattening.models.MetricAlertSingleResourceMultipleMetricCriteria;
import fixtures.discriminatorflattening.models.Odatatype;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateDiscriminatorTests {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void testAzureJsonSerialization() throws IOException {
        MetricAlertCriteria superclass = new MetricAlertCriteria();
        String superclassJson = BinaryData.fromObject(superclass).toString();
        JsonNode jsonNode = OBJECT_MAPPER.readTree(superclassJson);
        assertEquals(1, jsonNode.size());
        assertEquals("MetricAlertCriteria", jsonNode.get("odata.type").asText());

        MetricAlertCriteria subclass = new MetricAlertSingleResourceMultipleMetricCriteria();
        String subclassJson = BinaryData.fromObject(subclass).toString();
        jsonNode = OBJECT_MAPPER.readTree(subclassJson);
        assertEquals(1, jsonNode.size());
        assertEquals(Odatatype.MICROSOFT_AZURE_MONITOR_SINGLE_RESOURCE_MULTIPLE_METRIC_CRITERIA.toString(),
            jsonNode.get("odata.type").asText());

        // de-serialization of unknown type
        String unknownJson = "{\"odata.type\": \"invalid\"}";
        MetricAlertCriteria unknown = BinaryData.fromString(unknownJson).toObject(MetricAlertCriteria.class);
        assertEquals("invalid", unknown.getOdataType().toString());
        assertEquals(MetricAlertCriteria.class, unknown.getClass());
        // serialization keeps the unknown type
        unknownJson = BinaryData.fromObject(unknown).toString();
        jsonNode = OBJECT_MAPPER.readTree(unknownJson);
        assertEquals(1, jsonNode.size());
        assertEquals("invalid", jsonNode.get("odata.type").asText());
    }
}
