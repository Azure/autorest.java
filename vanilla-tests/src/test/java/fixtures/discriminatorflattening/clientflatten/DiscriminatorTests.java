/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.discriminatorflattening.clientflatten;

import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.SerializerEncoding;
import fixtures.discriminatorflattening.clientflatten.models.BackendAddressPool;
import fixtures.discriminatorflattening.clientflatten.models.LoadBalancer;
import fixtures.discriminatorflattening.clientflatten.models.MetricAlertResource;
import fixtures.discriminatorflattening.clientflatten.models.MetricAlertSingleResourceMultipleMetricCriteria;
import fixtures.discriminatorflattening.clientflatten.models.VirtualMachineScaleSet;
import fixtures.discriminatorflattening.clientflatten.models.VirtualMachineScaleSetNetworkConfiguration;
import fixtures.discriminatorflattening.clientflatten.models.VirtualMachineScaleSetNetworkProfile;
import fixtures.discriminatorflattening.clientflatten.models.VirtualMachineScaleSetVMProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscriminatorTests {

    @Test
    public void serializationOnDiscriminator() throws IOException {
        SerializerAdapter adapter = JacksonAdapter.createDefaultSerializerAdapter();

        MetricAlertResource metricAlert = new MetricAlertResource();
        metricAlert.setCriteria(new MetricAlertSingleResourceMultipleMetricCriteria().setAdditionalProperties(
            Collections.singletonMap("key.1", "value.1")));
        String metricAlertJson = adapter.serialize(metricAlert, SerializerEncoding.JSON);
        verifyODataTypeInJson(metricAlertJson);

        MetricAlertResource metricAlert2 = adapter.deserialize(metricAlertJson, MetricAlertResource.class,
            SerializerEncoding.JSON);
        assertInstanceOf(MetricAlertSingleResourceMultipleMetricCriteria.class, metricAlert2.getCriteria());
        assertTrue(metricAlert2.getCriteria().getAdditionalProperties().containsKey("key.1"));
    }

    @Test
    public void serializationOnNestedFlatten() throws IOException {
        SerializerAdapter adapter = JacksonAdapter.createDefaultSerializerAdapter();

        VirtualMachineScaleSet vmss = new VirtualMachineScaleSet().setVirtualMachineProfile(
            new VirtualMachineScaleSetVMProfile().setNetworkProfile(
                new VirtualMachineScaleSetNetworkProfile().setNetworkInterfaceConfigurations(Collections.singletonList(
                    new VirtualMachineScaleSetNetworkConfiguration().setName("name").setPrimary(true)))));

        String json = adapter.serialize(vmss, SerializerEncoding.JSON);
        assertFalse(json.contains("\"properties.primary\""));

        VirtualMachineScaleSet vmss2 = adapter.deserialize(json, VirtualMachineScaleSet.class, SerializerEncoding.JSON);
        assertEquals("name", vmss2.getVirtualMachineProfile()
            .getNetworkProfile()
            .getNetworkInterfaceConfigurations()
            .iterator()
            .next()
            .getName());
        assertTrue(vmss2.getVirtualMachineProfile()
            .getNetworkProfile()
            .getNetworkInterfaceConfigurations()
            .iterator()
            .next()
            .isPrimary());
    }

    @Test
    public void serializationOnNestedArrayFlatten() throws IOException {
        SerializerAdapter adapter = JacksonAdapter.createDefaultSerializerAdapter();

        LoadBalancer lb = new LoadBalancer().setBackendAddressPools(Arrays.asList(new BackendAddressPool()));

        String json = adapter.serialize(lb, SerializerEncoding.JSON);
        assertFalse(json.contains("\"properties.backendAddressPools\""));
        assertFalse(json.contains("\"properties.location\""));
        // verify that null value is ignored
        assertFalse(json.contains("\"location\":null"));
    }

    private void verifyODataTypeInJson(String json) {
        final String odataTypeDiscriminatorSignature = "\"odata.type\":";
        final String incorrectOdataTypeDiscriminatorSignature = "\"odata\":";
        assertTrue(json.contains(odataTypeDiscriminatorSignature));
        assertFalse(json.contains(incorrectOdataTypeDiscriminatorSignature));

        assertTrue(json.contains("\"key.1\""));
        assertTrue(json.contains("\"value.1\""));
    }
}
