// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.mgmttest;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.Region;
import com.azure.core.management.ResourceAuthorIdentityType;
import com.azure.core.management.exception.ManagementError;
import com.azure.core.management.exception.ManagementException;
import com.azure.core.management.http.policy.ArmChallengeAuthenticationPolicy;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.management.serializer.SerializerFactory;
import com.azure.core.util.Context;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.SerializerEncoding;
import com.azure.identity.EnvironmentCredentialBuilder;
import com.azure.mgmtlitetest.advisor.AdvisorManager;
import com.azure.mgmtlitetest.advisor.models.ResourceRecommendationBase;
import com.azure.mgmtlitetest.advisor.models.SuppressionContract;
import com.azure.mgmtlitetest.botservice.models.Site;
import com.azure.mgmtlitetest.managednetworkfabric.fluent.models.CommonPostActionResponseForDeviceUpdateInner;
import com.azure.mgmtlitetest.managednetworkfabric.models.ConfigurationState;
import com.azure.mgmtlitetest.mediaservices.MediaServicesManager;
import com.azure.mgmtlitetest.mediaservices.models.MediaService;
import com.azure.mgmtlitetest.mediaservices.models.StorageAccountType;
import com.azure.mgmtlitetest.resources.ResourceManager;
import com.azure.mgmtlitetest.resources.models.ResourceGroup;
import com.azure.mgmtlitetest.storage.StorageManager;
import com.azure.mgmtlitetest.storage.models.AccessTier;
import com.azure.mgmtlitetest.storage.models.BlobContainer;
import com.azure.mgmtlitetest.storage.models.BlobContainers;
import com.azure.mgmtlitetest.storage.models.BlobServiceProperties;
import com.azure.mgmtlitetest.storage.models.DeleteRetentionPolicy;
import com.azure.mgmtlitetest.storage.models.Kind;
import com.azure.mgmtlitetest.storage.models.MinimumTlsVersion;
import com.azure.mgmtlitetest.storage.models.PublicAccess;
import com.azure.mgmtlitetest.storage.models.Sku;
import com.azure.mgmtlitetest.storage.models.SkuName;
import com.azure.mgmtlitetest.storage.models.StorageAccount;
import com.azure.mgmtlitetest.storage.models.StorageAccounts;
import com.azure.mgmtlitetest.streamstyleserialization.fluent.models.CommunityGalleryInner;
import com.azure.mgmtlitetest.streamstyleserialization.models.PirCommunityGalleryResource;
import com.azure.mgmttest.appservice.models.DefaultErrorResponseError;
import com.azure.mgmttest.authorization.models.GraphErrorException;
import com.azure.mgmttest.networkwatcher.models.PacketCapture;
import com.azure.mgmttest.networkwatcher.models.PacketCaptureStorageLocation;
import com.azure.mgmttest.storage.fluent.StorageAccountsClient;
import com.azure.mgmttest.storage.implementation.StorageAccountsClientImpl;
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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class RuntimeTests {

    private static final String MOCK_SUBSCRIPTION_ID = "00000000-0000-0000-0000-000000000000";

    @Test
    public void testFlattenedModel() throws IOException {
        SerializerAdapter serializerAdapter = SerializerFactory.createDefaultManagementSerializerAdapter();

        PacketCapture packetCapture = new PacketCapture();
        String jsonStr = serializerAdapter.serialize(packetCapture, SerializerEncoding.JSON);
        Assertions.assertTrue(jsonStr.contains("\"properties\":"));

        packetCapture.withTarget("target");
        packetCapture.withStorageLocation(new PacketCaptureStorageLocation().withStorageId("id"));
        jsonStr = serializerAdapter.serialize(packetCapture, SerializerEncoding.JSON);
        PacketCapture packetCaptureFromJson = serializerAdapter.deserialize(jsonStr, PacketCapture.class, SerializerEncoding.JSON);
        Assertions.assertEquals("target", packetCaptureFromJson.target());
        Assertions.assertEquals("id", packetCaptureFromJson.storageLocation().storageId());
    }

    @Test
    public void testManagementClient() {
        StorageManagementClient storageManagementClient = new StorageManagementClientBuilder()
                .pipeline(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build())
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

        // verify pom basic
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

        // verify x-version-update tag used in Azure Java repo
        String content = new String(Files.readAllBytes(pomFile.toPath()));
        Assertions.assertFalse(content.contains("<artifactId>mockito-core</artifactId>"));
        Assertions.assertFalse(content.contains("<artifactId>byte-buddy</artifactId>"));
        Assertions.assertFalse(content.contains("<artifactId>byte-buddy-agent</artifactId>"));
        Assertions.assertFalse(content.contains("<!-- {x-version-update;org.mockito:mockito-core;external_dependency} -->"));
        Assertions.assertFalse(content.contains("<!-- {x-version-update;testdep_net.bytebuddy:byte-buddy;external_dependency} -->"));
        Assertions.assertFalse(content.contains("<!-- {x-version-update;testdep_net.bytebuddy:byte-buddy-agent;external_dependency} -->"));
    }

    @Test
    public void testBotservice() throws IOException {
        String siteName = "testSiteName";
        Site site = new Site().withSiteName(siteName);
        Assertions.assertEquals(siteName, site.siteName());

        SerializerAdapter serializerAdapter = SerializerFactory.createDefaultManagementSerializerAdapter();
        String json = serializerAdapter.serialize(site, SerializerEncoding.JSON);
        Site siteFromJson = serializerAdapter.deserialize(json, Site.class, SerializerEncoding.JSON);
        Assertions.assertEquals(siteName, siteFromJson.siteName());
    }

    @Test
    public void testSchemaCleanUp() {
        Assertions.assertThrows(ClassNotFoundException.class, () -> Class.forName("com.azure.mgmtlitetest.schemacleanup.models.LinkedServiceReference"));
        Assertions.assertThrows(ClassNotFoundException.class, () -> Class.forName("com.azure.mgmtlitetest.schemacleanup.models.Type"));

        Assertions.assertThrows(ClassNotFoundException.class, () -> Class.forName("com.azure.mgmtlitetest.schemacleanup.models.CloudErrorBodyNoRecursive"));

        Assertions.assertThrows(ClassNotFoundException.class, () -> Class.forName("com.azure.mgmtlitetest.schemacleanup.models.CloudError"));
        Assertions.assertThrows(ClassNotFoundException.class, () -> Class.forName("com.azure.mgmtlitetest.schemacleanup.models.CloudErrorBody"));
    }

    @Test
    public void testModelInheritErrorResponse() throws IOException {
        SerializerAdapter serializerAdapter = SerializerFactory.createDefaultManagementSerializerAdapter();
        String json = "{\n" +
                "  \"configurationState\": \"Succeeded\",\n" +
                "  \"error\": {\n" +
                "    \"code\": \"CODE\",\n" +
                "    \"message\": \"MESSAGE\"\n" +
                "  }\n" +
                "}";

        CommonPostActionResponseForDeviceUpdateInner model = serializerAdapter.deserialize(json, CommonPostActionResponseForDeviceUpdateInner.class, SerializerEncoding.JSON);
        Assertions.assertEquals(ConfigurationState.SUCCEEDED, model.configurationState());
        Assertions.assertEquals("CODE", model.error().getCode());
        Assertions.assertEquals("MESSAGE", model.error().getMessage());
    }

    @Test
    @Disabled("live test")
    public void testStorage() {
        ResourceManager resourceManager = authenticateResourceManager();
        StorageManager storageManager = authenticateStorageManager();

        String rgName = "rg1-weidxu-fluentlite";
        String saName = "sa1weidxulite";
        String blobContainerName = "container1";
        Region region = Region.US_EAST;

        ResourceGroup rg = resourceManager.resourceGroups().define(rgName)
                .withRegion(region)
                .create();

        try {
            // storage account
            StorageAccount storageAccount = storageManager.storageAccounts().define(saName)
                    .withRegion(rg.region())
                    .withExistingResourceGroup(rgName)
                    .withSku(new Sku().withName(SkuName.STANDARD_LRS))
                    .withKind(Kind.STORAGE_V2)
                    .withAccessTier(AccessTier.HOT)
                    .withEnableHttpsTrafficOnly(true)
                    .withMinimumTlsVersion(MinimumTlsVersion.TLS1_2)
                    .create();

            Assertions.assertEquals(saName, storageAccount.name());
            Assertions.assertEquals(rgName, storageAccount.resourceGroupName());

            storageAccount.refresh();

            Assertions.assertEquals(saName, storageAccount.name());
            Assertions.assertEquals(rgName, storageAccount.resourceGroupName());

            StorageAccount storageAccount2 = storageManager.storageAccounts().getById(storageAccount.id());
            storageAccount2.update()
                    .withAccessTier(AccessTier.COOL)
                    .apply();

            Assertions.assertEquals(1, storageManager.storageAccounts().listByResourceGroup(rgName).stream().count());

            // container
            BlobContainer blobContainer = storageManager.blobContainers().define(blobContainerName)
                    .withExistingStorageAccount(storageAccount.resourceGroupName(), storageAccount.name())
                    .withPublicAccess(PublicAccess.BLOB)
                    .create(new Context("key", "value"));

            Assertions.assertEquals(blobContainerName, blobContainer.name());
            Assertions.assertEquals(rgName, blobContainer.resourceGroupName());

            blobContainer.refresh();

            Assertions.assertEquals(blobContainerName, blobContainer.name());
            Assertions.assertEquals(rgName, blobContainer.resourceGroupName());

            BlobContainer blobContainer2 = storageManager.blobContainers().getById(blobContainer.id());
            blobContainer2.update()
                    .withPublicAccess(PublicAccess.NONE)
                    .apply(new Context("key", "value"));

            Assertions.assertEquals(1, storageManager.blobContainers().list(storageAccount.resourceGroupName(), storageAccount.name()).stream().count());

            storageManager.blobContainers().deleteById(blobContainer.id());

            // container blob service properties
            BlobServiceProperties blobService = storageManager.blobServices().define()
                    .withExistingStorageAccount(storageAccount.resourceGroupName(), storageAccount.name())
                    .create();

            blobService.update()
                    .withDeleteRetentionPolicy(new DeleteRetentionPolicy().withEnabled(true).withDays(3))
                    .apply();
            Assertions.assertTrue(blobService.deleteRetentionPolicy().enabled());
            Assertions.assertEquals(3, blobService.deleteRetentionPolicy().days());

            // test media services for SystemData
            testMediaServices(storageAccount);

            // test advisor as it is an extension, which requires a base resource
            // disabled as generate is async and it takes too long for a new resource
            //testAdvisor(storageAccount);

            storageManager.storageAccounts().deleteById(storageAccount.id());
        } finally {
            resourceManager.resourceGroups().deleteByResourceGroup(rgName);
        }
    }

    private void testMediaServices(StorageAccount storageAccount) {
        MediaServicesManager mediaservicesManager = authenticateMediaServicesManager();

        String rgName = "rg1-weidxu-fluentlite";
        String msName = "ms1weidxulite";

        MediaService mediaService = mediaservicesManager.mediaservices().define(msName)
                .withRegion(Region.US_EAST)
                .withExistingResourceGroup(rgName)
                .withStorageAccounts(Collections.singletonList(
                        new com.azure.mgmtlitetest.mediaservices.models.StorageAccount()
                                .withId(storageAccount.id())
                                .withType(StorageAccountType.PRIMARY)))
                .create();

        Assertions.assertNotNull(mediaService.systemData());
        Assertions.assertNotNull(mediaService.systemData().createdBy());
        Assertions.assertNotNull(mediaService.systemData().createdAt());
        Assertions.assertEquals(ResourceAuthorIdentityType.APPLICATION, mediaService.systemData().createdByType());

        mediaservicesManager.mediaservices().deleteById(mediaService.id());
    }

    private void testAdvisor(StorageAccount storageAccount) {
        AdvisorManager advisorManager = authenticateAdvisorManager();

        // generate is async
        advisorManager.recommendations().generate();

        PagedIterable<ResourceRecommendationBase> recommendations = advisorManager.recommendations().list();
        List<ResourceRecommendationBase> recommendationsForStorageAccount = recommendations.stream()
                .filter(recommendation -> recommendation.resourceMetadata().resourceId().equals(storageAccount.id()))
                .collect(Collectors.toList());

        Assertions.assertFalse(recommendationsForStorageAccount.isEmpty());
        long countBeforeSuppress = recommendationsForStorageAccount.size();

        String recommendationId = recommendationsForStorageAccount.iterator().next().name();
        SuppressionContract suppression = advisorManager.suppressions().define("HardcodedSuppressionName")
                .withExistingRecommendation(storageAccount.id(), recommendationId)
                .withTtl("1:00:00:00")
                .create();

        UUID suppressionId = UUID.fromString(suppression.suppressionId());

        ResourceRecommendationBase recommendationForStorageAccount = advisorManager.recommendations().get(storageAccount.id(), recommendationId);
        Assertions.assertTrue(recommendationForStorageAccount.suppressionIds().contains(suppressionId));

        recommendations = advisorManager.recommendations().list();
        long countAfterSuppress = recommendations.stream()
                .filter(recommendation -> recommendation.resourceMetadata().resourceId().equals(storageAccount.id()) && !recommendation.suppressionIds().contains(suppressionId))
                .count();
        Assertions.assertEquals(countBeforeSuppress - 1, countAfterSuppress);

        suppression.refresh();

        SuppressionContract suppression2 = advisorManager.suppressions().getById(suppression.id());

        advisorManager.suppressions().deleteById(suppression2.id());

        long countAfterDelete = recommendations.stream()
                .filter(recommendation -> recommendation.resourceMetadata().resourceId().equals(storageAccount.id()) && !recommendation.suppressionIds().contains(suppressionId))
                .count();
        Assertions.assertEquals(countBeforeSuppress, countAfterDelete);
    }

    @Test
    @Disabled("live test")
    public void testStorageWithPipeline() {
        AzureProfile profile = new AzureProfile(AzureEnvironment.AZURE);
        HttpClient httpClient = HttpClient.createDefault();
        HttpPipeline httpPipeline = new HttpPipelineBuilder()
                .httpClient(httpClient)
                .policies(new ArmChallengeAuthenticationPolicy(
                        new EnvironmentCredentialBuilder().build(),
                        profile.getEnvironment().getManagementEndpoint() + "/.default"),
                        new HttpLoggingPolicy(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS)))
                .build();
        StorageManager storageManager = StorageManager.authenticate(httpPipeline, profile);
        List<StorageAccount> storageAccounts = storageManager.storageAccounts().list().stream().collect(Collectors.toList());
    }

    private ResourceManager authenticateResourceManager() {
        return ResourceManager.configure()
                .withLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
                .authenticate(new EnvironmentCredentialBuilder().build(), new AzureProfile(AzureEnvironment.AZURE));
    }

    private StorageManager authenticateStorageManager() {
        return StorageManager.configure()
                .withLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
                .authenticate(new EnvironmentCredentialBuilder().build(), new AzureProfile(AzureEnvironment.AZURE));
    }

    private AdvisorManager authenticateAdvisorManager() {
        return AdvisorManager.configure()
                .withLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
                .authenticate(new EnvironmentCredentialBuilder().build(), new AzureProfile(AzureEnvironment.AZURE));
    }

    private MediaServicesManager authenticateMediaServicesManager() {
        return MediaServicesManager.configure()
                .withLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
                .authenticate(new EnvironmentCredentialBuilder().build(), new AzureProfile(AzureEnvironment.AZURE));
    }

    @Test
    public void testOverload() {
        // simple API
        assertMethodExist(StorageAccounts.class, "getByResourceGroup", "String", "String");
        assertMethodNotExist(StorageAccounts.class, "getByResourceGroup", "String", "String", "StorageAccountExpand");
        assertMethodNotExist(StorageAccounts.class, "getByResourceGroup", "String", "String", "StorageAccountExpand", "Context");
        assertMethodNotExist(StorageAccounts.class, "getByResourceGroupWithResponse", "String", "String", "StorageAccountExpand");
        assertMethodExist(StorageAccounts.class, "getByResourceGroupWithResponse", "String", "String", "StorageAccountExpand", "Context");

        // pageable API
        assertMethodExist(BlobContainers.class, "list", "String", "String");
        assertMethodNotExist(BlobContainers.class, "list", "String", "String", "String", "String", "ListContainersInclude");
        assertMethodExist(BlobContainers.class, "list", "String", "String", "String", "String", "ListContainersInclude", "Context");

        // LRO API
        // activation method
        assertMethodExist(StorageAccountsClientImpl.class, "createWithResponseAsync", "String", "String", "StorageAccountCreateParameters");
        // begin LRO method
        assertMethodExist(StorageAccountsClientImpl.class, "beginCreateAsync", "String", "String", "StorageAccountCreateParameters");
        assertMethodNotExist(StorageAccountsClientImpl.class, "beginCreateAsync", "String", "String", "StorageAccountCreateParameters", "Context");
        assertMethodExist(StorageAccountsClientImpl.class, "beginCreate", "String", "String", "StorageAccountCreateParameters");
        assertMethodExist(StorageAccountsClientImpl.class, "beginCreate", "String", "String", "StorageAccountCreateParameters", "Context");
        // wrapper method for begin LRO method
        assertMethodExist(StorageAccountsClientImpl.class, "createAsync", "String", "String", "StorageAccountCreateParameters");
        assertMethodNotExist(StorageAccountsClientImpl.class, "createAsync", "String", "String", "StorageAccountCreateParameters", "Context");
        assertMethodExist(StorageAccountsClientImpl.class, "create", "String", "String", "StorageAccountCreateParameters");
        assertMethodExist(StorageAccountsClientImpl.class, "create", "String", "String", "StorageAccountCreateParameters", "Context");

        // sync API in premium
        assertMethodExist(StorageAccountsClient.class, "getByResourceGroup", "String", "String");
        assertMethodNotExist(StorageAccountsClient.class, "getByResourceGroup", "String", "String", "StorageAccountExpand");
        assertMethodNotExist(StorageAccountsClient.class, "getByResourceGroupWithResponse", "String", "String", "StorageAccountExpand");
        assertMethodExist(StorageAccountsClient.class, "getByResourceGroupWithResponse", "String", "String", "StorageAccountExpand", "Context");

        // async API in premium
        assertMethodExist(StorageAccountsClient.class, "getByResourceGroupAsync", "String", "String");
        assertMethodNotExist(StorageAccountsClient.class, "getByResourceGroupAsync", "String", "String", "StorageAccountExpand");
        assertMethodExist(StorageAccountsClient.class, "getByResourceGroupWithResponseAsync", "String", "String", "StorageAccountExpand");
        assertMethodNotExist(StorageAccountsClient.class, "getByResourceGroupWithResponseAsync", "String", "String", "StorageAccountExpand", "Context");
    }

    private static <T> void assertMethodNotExist(Class<T> clazz, String methodName, String... parameterTypeSimpleNames) {
        String parametersSignature = String.join(",", parameterTypeSimpleNames);
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods) {
            if (methodName.equals(method.getName()) && Modifier.isPublic(method.getModifiers())) {
                if (method.getParameterTypes().length == parameterTypeSimpleNames.length) {
                    if (parametersSignature.equals(Arrays.stream(method.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(",")))) {
                        Assertions.fail("Method should not exist: " + method);
                    }
                }
            }
        }
    }

    private static <T> void assertMethodExist(Class<T> clazz, String methodName, String... parameterTypeSimpleNames) {
        boolean found = false;
        String parametersSignature = String.join(",", parameterTypeSimpleNames);
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods) {
            if (methodName.equals(method.getName()) && Modifier.isPublic(method.getModifiers())) {
                if (method.getParameterTypes().length == parameterTypeSimpleNames.length) {
                    if (parametersSignature.equals(Arrays.stream(method.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(",")))) {
                        found = true;
                    }
                }
            }
        }

        if (!found) {
            Assertions.fail("Method should exist: " + clazz.getName() + " " + methodName + "(" + parametersSignature + ")");
        }
    }

    @Test
    public void testStreamStyleSerialization() throws IOException {
        SerializerAdapter serializerAdapter = SerializerFactory.createDefaultManagementSerializerAdapter();
        Map<String, Object> pirCommunityGalleryResourceMap = new HashMap<>() {{
            this.put("name", "myName");
            this.put("location", "myLocation");
            this.put("type", "myType");
        }};
        String pirCommunityGalleryResourceJson = serializerAdapter.serialize(pirCommunityGalleryResourceMap, SerializerEncoding.JSON);

        PirCommunityGalleryResource pirCommunityGalleryResource = serializerAdapter.deserialize(pirCommunityGalleryResourceJson, PirCommunityGalleryResource.class, SerializerEncoding.JSON);
        Assertions.assertEquals("myName", pirCommunityGalleryResource.name());

        Map<String, Object> communityGalleryResourceMap = new HashMap<>() {{
            this.put("name", "myName");
            this.put("location", "myLocation");
            this.put("type", "myType");
            this.put("disclaimer", "myDisclaimer");
        }};

        String galleryJson = serializerAdapter.serialize(communityGalleryResourceMap, SerializerEncoding.JSON);
        CommunityGalleryInner galleryInner = serializerAdapter.deserialize(galleryJson, CommunityGalleryInner.class, SerializerEncoding.JSON);
        Assertions.assertEquals("myName", galleryInner.name());
        Assertions.assertEquals("myDisclaimer", galleryInner.disclaimer());
    }
}
