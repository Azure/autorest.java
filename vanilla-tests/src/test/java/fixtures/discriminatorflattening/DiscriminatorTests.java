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

public class DiscriminatorTests {

    @Test
    public void serializationOnDiscriminator() throws IOException {
        SerializerAdapter adapter = JacksonAdapter.createDefaultSerializerAdapter();

        MetricAlertResource metricAlert = new MetricAlertResource();
        metricAlert.setCriteria(new MetricAlertSingleResourceMultipleMetricCriteria());
        String metricAlertJson = adapter.serialize(metricAlert, SerializerEncoding.JSON);
        checkOdatatypeJson(metricAlertJson);

        MetricAlertResource metricAlert2 = adapter.deserialize(metricAlertJson, MetricAlertResource.class, SerializerEncoding.JSON);
        Assert.assertTrue(metricAlert2.getCriteria() instanceof MetricAlertSingleResourceMultipleMetricCriteria);
    }

    private void checkOdatatypeJson(String json) {
        final String odataTypeDiscriminatorSignature = "\"odata.type\":";
        final String incorrectOdataTypeDiscriminatorSignature = "\"odata\":";
        Assert.assertTrue(json.contains(odataTypeDiscriminatorSignature));
        Assert.assertFalse(json.contains(incorrectOdataTypeDiscriminatorSignature));
    }
}
