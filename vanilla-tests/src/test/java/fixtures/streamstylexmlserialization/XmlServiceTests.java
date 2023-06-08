// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.streamstylexmlserialization;

import fixtures.streamstylexmlserialization.models.AppleBarrel;
import fixtures.streamstylexmlserialization.models.Banana;
import fixtures.streamstylexmlserialization.models.ComplexTypeNoMeta;
import fixtures.streamstylexmlserialization.models.ComplexTypeWithMeta;
import fixtures.streamstylexmlserialization.models.JsonInput;
import fixtures.streamstylexmlserialization.models.JsonOutput;
import fixtures.streamstylexmlserialization.models.ListBlobsResponse;
import fixtures.streamstylexmlserialization.models.ListContainersResponse;
import fixtures.streamstylexmlserialization.models.RootWithRefAndMeta;
import fixtures.streamstylexmlserialization.models.RootWithRefAndNoMeta;
import fixtures.streamstylexmlserialization.models.SignedIdentifier;
import fixtures.streamstylexmlserialization.models.Slideshow;
import fixtures.streamstylexmlserialization.models.StorageServiceProperties;
import fixtures.streamstylexmlserialization.models.XmlsGetHeadersHeaders;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class XmlServiceTests {
    private static AutoRestSwaggerBATXMLService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATXMLServiceBuilder().buildClient();
    }

    @Test
    public void getComplexTypeRefNoMeta() {
        RootWithRefAndNoMeta root = client.getXmls().getComplexTypeRefNoMeta();
        assertNotNull(root);
        assertNotNull(root.getRefToModel());
        assertEquals("else", root.getSomething());
        assertEquals("myid", root.getRefToModel().getId());
    }

    @Test
    public void putComplexTypeRefNoMeta() {
        RootWithRefAndNoMeta root = new RootWithRefAndNoMeta();
        root.setRefToModel(new ComplexTypeNoMeta());
        root.getRefToModel().setId("myid");
        root.setSomething("else");
        client.getXmls().putComplexTypeRefNoMeta(root);
    }
    @Test
    public void getComplexTypeRefWithMeta() {
        RootWithRefAndMeta root = client.getXmls().getComplexTypeRefWithMeta();
        assertNotNull(root);
        assertNotNull(root.getRefToModel());
        assertEquals("else", root.getSomething());
        assertEquals("myid", root.getRefToModel().getId());
    }

    @Test
    public void putComplexTypeRefWithMeta() {
        RootWithRefAndMeta root = new RootWithRefAndMeta();
        root.setRefToModel(new ComplexTypeWithMeta());
        root.getRefToModel().setId("myid");
        root.setSomething("else");
        client.getXmls().putComplexTypeRefWithMeta(root);
    }

    @Test
    public void getSimple() {
        Slideshow slideshow = client.getXmls().getSimpleAsync().block();
        assertNotNull(slideshow);
        assertEquals("Yours Truly", slideshow.getAuthor());
        assertEquals("Date of publication", slideshow.getDate());
        assertEquals("Sample Slide Show", slideshow.getTitle());

        assertNotNull(slideshow.getSlides());
        assertEquals(2, slideshow.getSlides().size());
        assertEquals("all", slideshow.getSlides().get(0).getType());
        assertEquals("Wake up to WonderWidgets!", slideshow.getSlides().get(0).getTitle());
        assertNotNull(slideshow.getSlides().get(0).getItems());
        assertEquals(0, slideshow.getSlides().get(0).getItems().size());

        assertEquals("all", slideshow.getSlides().get(1).getType());
        assertEquals("Overview", slideshow.getSlides().get(1).getTitle());
        assertEquals(3, slideshow.getSlides().get(1).getItems().size());
        assertEquals("Why WonderWidgets are great", slideshow.getSlides().get(1).getItems().get(0));
        assertEquals("", slideshow.getSlides().get(1).getItems().get(1));
        assertEquals("Who buys WonderWidgets", slideshow.getSlides().get(1).getItems().get(2));
    }

    @Test
    public void putSimple() {
        Slideshow slideshow = client.getXmls().getSimpleAsync().block();
        client.getXmls().putSimpleAsync(slideshow).block();
    }

    @Test
    public void getWrappedLists() {
        AppleBarrel barrel = client.getXmls().getWrappedListsAsync().block();
        assertNotNull(barrel);

        assertNotNull(barrel.getGoodApples());
        assertEquals("Fuji", barrel.getGoodApples().get(0));
        assertEquals("Gala", barrel.getGoodApples().get(1));

        assertNotNull(barrel.getBadApples());
        assertEquals("Red Delicious", barrel.getBadApples().get(0));
    }

    @Test
    public void putWrappedLists() {
        AppleBarrel barrel = client.getXmls().getWrappedListsAsync().block();
        client.getXmls().putWrappedListsAsync(barrel).block();
    }

    @Test
    public void getHeaders() {
        XmlsGetHeadersHeaders headers = client.getXmls().getHeadersWithResponseAsync().block().getDeserializedHeaders();
        assertEquals("custom-value", headers.getCustomHeader());
    }

    @Test
    public void getEmptyList() {
        Slideshow slideshow = client.getXmls().getEmptyListAsync().block();
        assertNull(slideshow);
    }

    @Test
    public void putEmptyList() {
        Slideshow slideshow = new Slideshow();
        slideshow.setSlides(new ArrayList<>());
        client.getXmls().putEmptyList(slideshow);
    }

    @Test
    public void getEmptyWrappedLists() {
        AppleBarrel barrel = client.getXmls().getEmptyWrappedListsAsync().block();
        assertNotNull(barrel);
        assertNotNull(barrel.getBadApples());
        assertEquals(0, barrel.getBadApples().size());

        assertNotNull(barrel.getGoodApples());
        assertEquals(0, barrel.getGoodApples().size());
    }

    @Test
    public void putEmptyWrappedLists() {
        AppleBarrel barrel = new AppleBarrel();
        barrel.setBadApples(new ArrayList<>());
        barrel.setGoodApples(new ArrayList<>());
        client.getXmls().putEmptyWrappedLists(barrel);
    }

    @Test
    public void getRootList() {
        List<Banana> bananas = client.getXmls().getRootListAsync().block();
        assertNotNull(bananas);
        assertEquals(2, bananas.size());

        assertEquals("Cavendish", bananas.get(0).getName());
        assertEquals("Sweet", bananas.get(0).getFlavor());
        assertEquals("2018-02-28T00:40:00.123Z", bananas.get(0).getExpiration().toString());

        assertEquals("Plantain", bananas.get(1).getName());
        assertEquals("Savory", bananas.get(1).getFlavor());
        assertEquals("2018-02-28T00:40:00.123Z", bananas.get(1).getExpiration().toString());
    }

    @Test
    public void putRootList() {
        List<Banana> bananas = client.getXmls().getRootListAsync().block();
        client.getXmls().putRootListAsync(bananas).block();
    }

    @Test
    public void getRootListSingleItem() {
        List<Banana> bananas = client.getXmls().getRootListSingleItem();

        assertNotNull(bananas);
        assertEquals(1, bananas.size());

        assertEquals("Cavendish", bananas.get(0).getName());
        assertEquals("Sweet", bananas.get(0).getFlavor());
        assertEquals("2018-02-28T00:40:00.123Z", bananas.get(0).getExpiration().toString());
    }

    @Test
    public void putRootListSingleItem() {
        List<Banana> bananas = client.getXmls().getRootListSingleItem();
        client.getXmls().putRootListSingleItem(bananas);
    }

    @Test
    public void getEmptyRootList() {
        List<Banana> bananas = client.getXmls().getEmptyRootListAsync().block();
        assertNull(bananas);
    }

    @Test
    public void putEmptyRootList() {
        client.getXmls().putEmptyRootListAsync(new ArrayList<>()).block();
    }

    @Test
    public void getEmptyChildElement() {
        Banana banana = client.getXmls().getEmptyChildElementAsync().block();
        assertNotNull(banana);

        assertEquals("Unknown Banana", banana.getName());
        assertEquals("", banana.getFlavor());
        assertEquals("2012-02-24T00:53:52.789Z", banana.getExpiration().toString());
    }

    @Test
    public void putEmptyChildElement() {
        Banana banana = client.getXmls().getEmptyChildElementAsync().block();
        client.getXmls().putEmptyChildElementAsync(banana).block();
    }

    @Test
    public void listContainers() {
        ListContainersResponse response = client.getXmls().listContainers();
        assertEquals(3, response.getContainers().size());
    }

    @Test
    public void getServiceProperties() {
        StorageServiceProperties response = client.getXmls().getServiceProperties();
        assertNotNull(response.getLogging());
        assertNotNull(response.getHourMetrics());
        assertNotNull(response.getMinuteMetrics());
    }

    @Test
    public void getAcls() {
        List<SignedIdentifier> response = client.getXmls().getAcls().items();
        assertEquals(1, response.size());
        assertEquals("MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI=", response.get(0).getId());
        assertNotNull(response.get(0).getAccessPolicy());
        assertEquals("2009-09-28T08:49:37.123Z", response.get(0).getAccessPolicy().getStart().toString());
        assertEquals("2009-09-29T08:49:37.123Z", response.get(0).getAccessPolicy().getExpiry().toString());
        assertEquals("rwd", response.get(0).getAccessPolicy().getPermission());
    }

    @Test
    public void putAcls() {
        List<SignedIdentifier> response = client.getXmls().getAcls().items();
        client.getXmls().putAcls(response);
    }

    @Test
    public void listBlobs() {
        ListBlobsResponse response = client.getXmls().listBlobs();
        assertEquals(5, response.getBlobs().getBlob().size());
    }

    @Test
    public void jsonInput() {
        JsonInput jsonInput = new JsonInput();
        jsonInput.setId(42);
        client.getXmls().jsonInput(jsonInput);
    }

    @Test
    public void jsonOutput() {
        JsonOutput output = client.getXmls().jsonOutput();
        assertEquals(42, output.getId().intValue());
    }
}
