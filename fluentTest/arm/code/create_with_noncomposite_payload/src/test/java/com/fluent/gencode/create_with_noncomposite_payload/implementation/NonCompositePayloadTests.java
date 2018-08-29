/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.create_with_noncomposite_payload.implementation;

import com.fluent.gencode.create_with_noncomposite_payload.ColorTypes;
import com.fluent.gencode.create_with_noncomposite_payload.Dog;
import com.fluent.gencode.create_with_noncomposite_payload.DogSku;
import com.fluent.gencode.create_with_noncomposite_payload.Dogs;
import com.fluent.gencode.create_with_noncomposite_payload.SkuNames;
import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.arm.resources.Region;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.credentials.AzureTokenCredentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class NonCompositePayloadTests {
    Create_With_NonComposite_PayloadManager manager;

    @Before
    public void initialize() {
        AzureTokenCredentials credentials = tokenCredentials();
        this.manager = Create_With_NonComposite_PayloadManager.authenticate(credentials, "subscriptionid1");
    }

    @Test
    public void ensureDefinitionHasWitherForNonCompositePayload() {
        //
        HashSet<String> expectedExtends = new HashSet<>();
        expectedExtends.add("com.microsoft.azure.arm.collection.SupportsCreating");
        expectedExtends.add("com.microsoft.azure.arm.resources.collection.SupportsDeletingByResourceGroup");
        expectedExtends.add("com.microsoft.azure.arm.resources.collection.SupportsBatchDeletion");
        expectedExtends.add("com.microsoft.azure.arm.resources.collection.SupportsGettingByResourceGroup");
        expectedExtends.add("com.microsoft.azure.arm.model.HasInner");
        //
        Class cls = Dogs.class;
        Class[] eInterfaces = cls.getInterfaces();
        HashSet<String> currentExtends = new HashSet<>();
        for (Class eInterface : eInterfaces) {
            currentExtends.add(eInterface.getName());
        }
        //
        Assert.assertEquals(expectedExtends.size(), currentExtends.size());
        //
        for (String expectedExtend : expectedExtends) {
            if (!currentExtends.contains(expectedExtend)) {
                Assert.assertTrue("Expected interface '" + expectedExtend + "' is not implemented", false);
            }
        }
        // Check the definition stages
        //
        Dog.DefinitionStages.Blank dogDef = new DogImpl("dog1", new DogInner(), this.manager);
        Dog.DefinitionStages.WithCreate creatable = dogDef.withRegion(Region.ASIA_EAST)
                .withExistingResourceGroup("rg1")
                .withDog(new HashMap<String, String>());    // NonCompositePayload

        // Check the update stages
        //
        Dog.Update dogUpdate = new DogImpl("dog1", new DogInner(), this.manager);
        dogUpdate = dogUpdate.withOsType(ColorTypes.BLACK)
                .withAnimalSizeGB(12)
                .withSku(new DogSku().withName(SkuNames.LARGE))
                .withTag("a", "b");
    }

    private AzureTokenCredentials tokenCredentials() {
        final String clientId = "<clientId>";
        final String tenantId = "<tenantId>";
        final String clientSecret = "<appSecret>";
        final String subscriptionId = "<subscriptionId>";
        return new ApplicationTokenCredentials(clientId, tenantId, clientSecret, AzureEnvironment.AZURE).withDefaultSubscriptionId(subscriptionId);
    }
}
