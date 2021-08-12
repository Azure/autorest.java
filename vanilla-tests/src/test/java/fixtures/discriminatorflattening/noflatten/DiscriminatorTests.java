/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.discriminatorflattening.noflatten;

import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.SerializerEncoding;
import fixtures.discriminatorflattening.noflatten.models.BackendAddressPool;
import fixtures.discriminatorflattening.noflatten.models.LoadBalancer;
import fixtures.discriminatorflattening.noflatten.models.LoadBalancerPropertiesFormat;
import fixtures.discriminatorflattening.noflatten.models.MetricAlertProperties;
import fixtures.discriminatorflattening.noflatten.models.MetricAlertResource;
import fixtures.discriminatorflattening.noflatten.models.MetricAlertSingleResourceMultipleMetricCriteria;
import fixtures.discriminatorflattening.noflatten.models.VirtualMachineScaleSet;
import fixtures.discriminatorflattening.noflatten.models.VirtualMachineScaleSetNetworkConfiguration;
import fixtures.discriminatorflattening.noflatten.models.VirtualMachineScaleSetNetworkConfigurationProperties;
import fixtures.discriminatorflattening.noflatten.models.VirtualMachineScaleSetNetworkProfile;
import fixtures.discriminatorflattening.noflatten.models.VirtualMachineScaleSetProperties;
import fixtures.discriminatorflattening.noflatten.models.VirtualMachineScaleSetVMProfile;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class DiscriminatorTests {

    @Test
    public void serializationOnDiscriminator() throws IOException {
        SerializerAdapter adapter = JacksonAdapter.createDefaultSerializerAdapter();

        MetricAlertResource metricAlert = new MetricAlertResource();
        metricAlert.setProperties(new MetricAlertProperties());
        metricAlert.getProperties().setCriteria(new MetricAlertSingleResourceMultipleMetricCriteria().setAdditionalProperties(Collections.singletonMap("key.1", "value.1")));
        String metricAlertJson = adapter.serialize(metricAlert, SerializerEncoding.JSON);
        verifyODataTypeInJson(metricAlertJson);

        MetricAlertResource metricAlert2 = adapter.deserialize(metricAlertJson, MetricAlertResource.class, SerializerEncoding.JSON);
        Assert.assertTrue(metricAlert2.getProperties().getCriteria() instanceof MetricAlertSingleResourceMultipleMetricCriteria);
        Assert.assertTrue(metricAlert2.getProperties().getCriteria().getAdditionalProperties().containsKey("key.1"));
    }

    @Test
    public void serializationOnNestedFlatten() throws IOException {
        SerializerAdapter adapter = JacksonAdapter.createDefaultSerializerAdapter();

        VirtualMachineScaleSet vmss = new VirtualMachineScaleSet()
                .setProperties(new VirtualMachineScaleSetProperties()
                        .setVirtualMachineProfile(new VirtualMachineScaleSetVMProfile()
                                .setNetworkProfile(new VirtualMachineScaleSetNetworkProfile()
                                        .setNetworkInterfaceConfigurations(Collections.singletonList(new VirtualMachineScaleSetNetworkConfiguration()
                                                .setName("name")
                                                .setProperties(new VirtualMachineScaleSetNetworkConfigurationProperties()
                                                        .setPrimary(true)))))));

        String json = adapter.serialize(vmss, SerializerEncoding.JSON);
        Assert.assertFalse(json.contains("\"properties.primary\""));

        VirtualMachineScaleSet vmss2 = adapter.deserialize(json, VirtualMachineScaleSet.class, SerializerEncoding.JSON);
        Assert.assertEquals("name", vmss2.getProperties().getVirtualMachineProfile().getNetworkProfile().getNetworkInterfaceConfigurations().iterator().next().getName());
        Assert.assertTrue(vmss2.getProperties().getVirtualMachineProfile().getNetworkProfile().getNetworkInterfaceConfigurations().iterator().next().getProperties().isPrimary());
    }

    @Test
    public void serializationOnNestedArrayFlatten() throws IOException {
        SerializerAdapter adapter = JacksonAdapter.createDefaultSerializerAdapter();

        LoadBalancer lb = new LoadBalancer()
                .setProperties(new LoadBalancerPropertiesFormat()
                        .setBackendAddressPools(Arrays.asList(new BackendAddressPool())));

        String json = adapter.serialize(lb, SerializerEncoding.JSON);
        Assert.assertFalse(json.contains("\"properties.backendAddressPools\""));
        Assert.assertFalse(json.contains("\"properties.location\""));
        // verify that null value is ignored
        Assert.assertFalse(json.contains("\"location\":null"));
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
