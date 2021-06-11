/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.discriminatorflattening;

import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.SerializerEncoding;
import fixtures.discriminatorflattening.models.MetricAlertResource;
import fixtures.discriminatorflattening.models.MetricAlertSingleResourceMultipleMetricCriteria;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

public class DiscriminatorTests {

    @Test
    public void serializationOnDiscriminator() throws IOException {
        SerializerAdapter adapter = JacksonAdapter.createDefaultSerializerAdapter();

        MetricAlertResource metricAlert = new MetricAlertResource();
        metricAlert.setCriteria(new MetricAlertSingleResourceMultipleMetricCriteria().setAdditionalProperties(Collections.singletonMap("key.1", "value.1")));
        String metricAlertJson = adapter.serialize(metricAlert, SerializerEncoding.JSON);
        verifyODataTypeInJson(metricAlertJson);

        MetricAlertResource metricAlert2 = adapter.deserialize(metricAlertJson, MetricAlertResource.class, SerializerEncoding.JSON);
        Assert.assertTrue(metricAlert2.getCriteria() instanceof MetricAlertSingleResourceMultipleMetricCriteria);
        Assert.assertTrue(metricAlert2.getCriteria().getAdditionalProperties().containsKey("key.1"));
    }

    private void verifyODataTypeInJson(String json) {
        final String odataTypeDiscriminatorSignature = "\"odata.type\":";
        final String incorrectOdataTypeDiscriminatorSignature = "\"odata\":";
        Assert.assertTrue(json.contains(odataTypeDiscriminatorSignature));
        Assert.assertFalse(json.contains(incorrectOdataTypeDiscriminatorSignature));

        Assert.assertTrue(json.contains("\"key.1\""));
        Assert.assertTrue(json.contains("\"value.1\""));
    }
}
