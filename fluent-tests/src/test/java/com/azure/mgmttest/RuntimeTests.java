// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.mgmttest;

import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.Region;
import com.azure.core.management.exception.ManagementError;
import com.azure.core.management.exception.ManagementException;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.management.serializer.SerializerFactory;
import com.azure.core.util.Configuration;
import com.azure.core.util.Context;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.SerializerEncoding;
import com.azure.identity.EnvironmentCredentialBuilder;
import com.azure.mgmtlitetest.resources.ResourceManager;
import com.azure.mgmtlitetest.resources.models.ResourceGroup;
import com.azure.mgmtlitetest.storage.StorageManager;
import com.azure.mgmtlitetest.storage.models.AccessTier;
import com.azure.mgmtlitetest.storage.models.BlobContainer;
import com.azure.mgmtlitetest.storage.models.Kind;
import com.azure.mgmtlitetest.storage.models.PublicAccess;
import com.azure.mgmtlitetest.storage.models.Sku;
import com.azure.mgmtlitetest.storage.models.SkuName;
import com.azure.mgmtlitetest.storage.models.StorageAccount;
import com.azure.mgmttest.appservice.models.DefaultErrorResponseError;
import com.azure.mgmttest.authorization.models.GraphErrorException;
import com.azure.mgmttest.storage.implementation.StorageManagementClientBuilder;
import com.azure.mgmttest.storage.fluent.StorageManagementClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RuntimeTests {

    private static final String MOCK_SUBSCRIPTION_ID = "00000000-0000-0000-0000-000000000000";

    @Test
    public void testManagementClient() {
        StorageManagementClient storageManagementClient = new StorageManagementClientBuilder()
                .pipeline(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build())
                .endpoint(AzureEnvironment.AZURE.getResourceManagerEndpoint())
                .subscriptionId(MOCK_SUBSCRIPTION_ID)
                .buildClient();
        Assertions.assertNotNull(storageManagementClient.getHttpPipeline());
        Assertions.assertEquals(MOCK_SUBSCRIPTION_ID, storageManagementClient.getSubscriptionId());
        Assertions.assertNotNull(storageManagementClient.getStorageAccounts());
    }

    @Test
    public void testWebException() throws IOException {
        final String errorBody = "{\"error\":{\"code\":\"WepAppError\",\"message\":\"Web app error.\",\"innererror\":\"Deployment error.\",\"details\":[{\"code\":\"InnerError\"}]}}";

        SerializerAdapter serializerAdapter = SerializerFactory.createDefaultManagementSerializerAdapter();
        DefaultErrorResponseError webError = serializerAdapter.deserialize(errorBody, DefaultErrorResponseError.class, SerializerEncoding.JSON);
        Assertions.assertEquals("WepAppError", webError.getCode());
        Assertions.assertNotNull(webError.getDetails());
        Assertions.assertEquals(1, webError.getDetails().size());
        Assertions.assertEquals("InnerError", webError.getDetails().get(0).getCode());

        GraphErrorException graphException = new GraphErrorException("mock graph error", null);
        Assertions.assertFalse((Object) graphException instanceof ManagementException);
        Assertions.assertFalse((Object) graphException.getValue() instanceof ManagementError);
    }

    @Test
    public void testStorageManager() {
        StorageManager storageManager = authenticateStorageManager();
        PagedIterable<StorageAccount> storageAccounts = storageManager.storageAccounts().list();
        //List<StorageAccount> storageAccountList = storageManager.storageAccounts().list().stream().collect(Collectors.toList());
    }

    @Test
    public void testPom() throws ParserConfigurationException, IOException, SAXException {
        File pomFile = new File("pom_generated_resources.xml");

        Map<String, String> rootTags = new HashMap<>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(pomFile);
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elementNode = (Element) node;
                if (elementNode.getChildNodes().getLength() == 1 && elementNode.getChildNodes().item(0).getNodeType() == Node.TEXT_NODE) {
                    Text textNode = (Text) elementNode.getChildNodes().item(0);
                    rootTags.put(elementNode.getTagName(), textNode.getWholeText());
                }
            }
        }

        Assertions.assertTrue(rootTags.containsKey("name"));
        Assertions.assertTrue(rootTags.get("name").contains("Azure SDK"));
    }

    @Test
    @Disabled("live test")
    public void testStorage() {
        ResourceManager resourceManager = authenticateResourceManager();
        StorageManager storageManager = authenticateStorageManager();

        String rgName = "rg1-weidxu-fluentlite";
        String saName = "sa1weidxulite";
        String blobContainerName = "container1";
        Region region = Region.US_WEST;

        ResourceGroup rg = resourceManager.resourceGroups().define(rgName)
                .withRegion(region)
                .create();

        try {
            StorageAccount storageAccount = storageManager.storageAccounts().define(saName)
                    .withRegion(region)
                    .withExistingResourceGroup(rgName)
                    .withSku(new Sku().withName(SkuName.STANDARD_LRS))
                    .withKind(Kind.STORAGE_V2)
                    .withEnableHttpsTrafficOnly(true)
                    .create();

            storageAccount.refresh();

            StorageAccount storageAccount2 = storageManager.storageAccounts().getByResourceGroup(rgName, saName);
            storageAccount2.update()
                    .withAccessTier(AccessTier.COOL)
                    .apply();

            BlobContainer blobContainer = storageManager.blobContainers().defineContainer(blobContainerName)
                    .withExistingStorageAccount(rgName, saName)
                    .withPublicAccess(PublicAccess.BLOB)
                    .create(new Context("key", "value"));

            blobContainer.refresh();

            BlobContainer blobContainer2 = storageManager.blobContainers().get(rgName, saName, blobContainerName);
            blobContainer2.update()
                    .withPublicAccess(PublicAccess.NONE)
                    .apply(new Context("key", "value"));
        } finally {
            resourceManager.resourceGroups().delete(rgName);
        }
    }

    private ResourceManager authenticateResourceManager() {
        String subscriptionId = Configuration.getGlobalConfiguration().get(Configuration.PROPERTY_AZURE_SUBSCRIPTION_ID);
        return ResourceManager.authenticate(new EnvironmentCredentialBuilder().build(), new AzureProfile(null, subscriptionId, AzureEnvironment.AZURE));
    }

    private StorageManager authenticateStorageManager() {
        String subscriptionId = Configuration.getGlobalConfiguration().get(Configuration.PROPERTY_AZURE_SUBSCRIPTION_ID);
        if (subscriptionId == null) {
            subscriptionId = "";
        }
        return StorageManager.authenticate(new EnvironmentCredentialBuilder().build(), new AzureProfile(null, subscriptionId, AzureEnvironment.AZURE));
    }
}
