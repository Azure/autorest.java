package fixtures.xml;

import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.DecodingPolicyFactory;
import fixtures.xml.implementation.AutoRestSwaggerBATXMLServiceImpl;
import fixtures.xml.models.AppleBarrel;
import fixtures.xml.models.Banana;
import fixtures.xml.models.Slideshow;
import fixtures.xml.models.XmlGetHeadersHeaders;
import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class XmlsTests {
    private static AutoRestSwaggerBATXMLService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATXMLServiceImpl(HttpPipeline.build(new MockXMLHttpClient(), new DecodingPolicyFactory()));
    }

    @Test
    public void getSimpleDocument() {
        Slideshow slideshow = client.xmls().getSimple();
        assertNotNull(slideshow);
        assertEquals("Yours Truly", slideshow.author());
        assertEquals("Date of publication", slideshow.dateProperty());
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
        Slideshow slideshow = client.xmls().getEmptyList();
        assertNotNull(slideshow);
        assertNotNull(slideshow.slides());
        assertEquals(null, slideshow.title());
        assertEquals(null, slideshow.author());
        assertEquals(null, slideshow.dateProperty());
        assertEquals(0, slideshow.slides().size());
    }

    @Test
    public void getEmptyWrappedLists() {
        AppleBarrel barrel = client.xmls().getEmptyWrappedLists();
        assertNotNull(barrel);
        assertNotNull(barrel.badApples());
        assertEquals(0, barrel.badApples().size());

        assertNotNull(barrel.goodApples());
        assertEquals(0, barrel.goodApples().size());
    }

    @Test
    public void putSimpleDocument() {
        Slideshow slideshow = client.xmls().getSimple();
        client.xmls().putSimple(slideshow);
    }

    @Test
    public void getWrappedLists() {
        AppleBarrel barrel = client.xmls().getWrappedLists();
        assertNotNull(barrel);

        assertNotNull(barrel.goodApples());
        assertEquals("Fuji", barrel.goodApples().get(0));
        assertEquals("Gala", barrel.goodApples().get(1));

        assertNotNull(barrel.badApples());
        assertEquals("Red Delicious", barrel.badApples().get(0));
    }

    @Test
    public void putWrappedLists() {
        AppleBarrel barrel = client.xmls().getWrappedLists();
        client.xmls().putWrappedLists(barrel);
    }

    @Test
    public void getRootList() {
        List<Banana> bananas = client.xmls().getRootList();
        assertNotNull(bananas);
        assertEquals(3, bananas.size());

        assertEquals("Cavendish", bananas.get(0).name());
        assertEquals("Sweet", bananas.get(0).flavor());
        assertEquals("2018-02-28T00:40:00.000Z", bananas.get(0).expiration().toString());

        assertEquals("Plantain", bananas.get(1).name());
        assertEquals("Savory", bananas.get(1).flavor());
        assertEquals("2018-02-28T00:40:00.000Z", bananas.get(1).expiration().toString());

        assertEquals("Unknown Banana", bananas.get(2).name());
        assertEquals("", bananas.get(2).flavor());
        assertEquals("2012-02-24T00:53:52.780Z", bananas.get(2).expiration().toString());
    }

    @Test
    public void putRootList() {
        List<Banana> bananas = client.xmls().getRootList();
        client.xmls().putRootList(bananas);
    }

    @Test
    public void getEmptyRootList() {
        List<Banana> bananas = client.xmls().getEmptyRootList();
        assertNotNull(bananas);
        assertEquals(0, bananas.size());
    }

    @Test
    public void putEmptyRootList() {
        List<Banana> bananas = client.xmls().getEmptyRootList();
        client.xmls().putEmptyRootList(bananas);
    }

    @Test
    public void testResponseHeaders() {
        XmlGetHeadersHeaders headers = client.xmls().getHeadersWithRestResponseAsync().blockingGet().headers();
        assertEquals("Custom value", headers.customHeader());
    }
}
