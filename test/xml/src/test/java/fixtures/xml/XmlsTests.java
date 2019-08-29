package fixtures.xml;

import com.azure.core.http.HttpPipelineBuilder;
import fixtures.xml.implementation.AutoRestSwaggerBATXMLServiceImpl;
import fixtures.xml.models.AppleBarrel;
import fixtures.xml.models.Banana;
import fixtures.xml.models.Slideshow;
import fixtures.xml.models.XmlGetHeadersHeaders;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlsTests {
    private static AutoRestSwaggerBATXMLService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATXMLServiceImpl(new HttpPipelineBuilder().httpClient(new MockXMLHttpClient()).build());
    }

    @Test
    public void getSimpleDocument() {
        Slideshow slideshow = client.xmls().getSimpleAsync().block();
        assertNotNull(slideshow);
        assertEquals("Yours Truly", slideshow.author());
        assertEquals("Date of publication", slideshow.date());
        assertEquals("Sample Slide Show", slideshow.title());

        assertNotNull(slideshow.slides());
        assertEquals(2, slideshow.slides().size());
        assertEquals("all", slideshow.slides().get(0).type());
        assertEquals("Wake up to WonderWidgets!", slideshow.slides().get(0).title());
        assertNotNull(slideshow.slides().get(0).items());
        assertEquals(0, slideshow.slides().get(0).items().size());

        assertEquals("all", slideshow.slides().get(1).type());
        assertEquals("Overview", slideshow.slides().get(1).title());
        assertEquals(3, slideshow.slides().get(1).items().size());
        assertEquals("Why WonderWidgets are great", slideshow.slides().get(1).items().get(0));
        assertEquals("", slideshow.slides().get(1).items().get(1));
        assertEquals("Who buys WonderWidgets", slideshow.slides().get(1).items().get(2));
    }

    @Test
    public void getEmptyList() {
        Slideshow slideshow = client.xmls().getEmptyListAsync().block();
        assertNotNull(slideshow);
        assertNotNull(slideshow.slides());
        assertEquals(null, slideshow.title());
        assertEquals(null, slideshow.author());
        assertEquals(null, slideshow.date());
        assertEquals(0, slideshow.slides().size());
    }

    @Test
    public void getEmptyWrappedLists() {
        AppleBarrel barrel = client.xmls().getEmptyWrappedListsAsync().block();
        assertNotNull(barrel);
        assertNotNull(barrel.badApples());
        assertEquals(0, barrel.badApples().size());

        assertNotNull(barrel.goodApples());
        assertEquals(0, barrel.goodApples().size());
    }

    @Test
    public void putSimpleDocument() {
        Slideshow slideshow = client.xmls().getSimpleAsync().block();
        client.xmls().putSimpleAsync(slideshow).block();
    }

    @Test
    public void getWrappedLists() {
        AppleBarrel barrel = client.xmls().getWrappedListsAsync().block();
        assertNotNull(barrel);

        assertNotNull(barrel.goodApples());
        assertEquals("Fuji", barrel.goodApples().get(0));
        assertEquals("Gala", barrel.goodApples().get(1));

        assertNotNull(barrel.badApples());
        assertEquals("Red Delicious", barrel.badApples().get(0));
    }

    @Test
    public void putWrappedLists() {
        AppleBarrel barrel = client.xmls().getWrappedListsAsync().block();
        client.xmls().putWrappedListsAsync(barrel).block();
    }

    @Test
    public void getRootList() {
        List<Banana> bananas = client.xmls().getRootListAsync().block();
        assertNotNull(bananas);
        assertEquals(2, bananas.size());

        assertEquals("Cavendish", bananas.get(0).name());
        assertEquals("Sweet", bananas.get(0).flavor());
        assertEquals("2018-02-28T00:40Z", bananas.get(0).expiration().toString());

        assertEquals("Plantain", bananas.get(1).name());
        assertEquals("Savory", bananas.get(1).flavor());
        assertEquals("2018-02-28T00:40Z", bananas.get(1).expiration().toString());
    }

    @Test
    @Ignore("FIXME: Update to Jackson 2.9 to interpret empty element for banana.flavor() as empty string")
    public void getItemWithEmptyChildElement() {
        Banana banana = client.xmls().getEmptyChildElementAsync().block();
        assertNotNull(banana);

        assertEquals("Unknown Banana", banana.name());
        assertEquals("", banana.flavor());
        assertEquals("2012-02-24T00:53:52.780Z", banana.expiration().toString());
    }

    @Test
    @Ignore("FIXME: Update to Jackson 2.9 to roundtrip empty element")
    public void putItemWithEmptyChildElement() {
        Banana banana = client.xmls().getEmptyChildElementAsync().block();
        client.xmls().putEmptyChildElementAsync(banana).block();
    }

    @Test
    public void putRootList() {
        List<Banana> bananas = client.xmls().getRootListAsync().block();
        client.xmls().putRootListAsync(bananas).block();
    }

    @Test
    public void getEmptyRootList() {
        List<Banana> bananas = client.xmls().getEmptyRootListAsync().block();
        assertNotNull(bananas);
        assertEquals(0, bananas.size());
    }

    @Test
    public void putEmptyRootList() {
        List<Banana> bananas = client.xmls().getEmptyRootListAsync().block();
        client.xmls().putEmptyRootListAsync(bananas).block();
    }

    @Test
    public void testResponseHeaders() {
        XmlGetHeadersHeaders headers = client.xmls().getHeadersWithRestResponseAsync().block().deserializedHeaders();
        assertEquals("Custom value", headers.customHeader());
    }
}
