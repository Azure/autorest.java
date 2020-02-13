package fixtures.xmlservice;

import fixtures.xmlservice.models.AppleBarrel;
import fixtures.xmlservice.models.Banana;
import fixtures.xmlservice.models.Slideshow;
import fixtures.xmlservice.models.XmlsGetHeadersHeaders;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlServiceTests {
  private static AutoRestSwaggerBATXMLService client;

  @BeforeClass
  public static void setup() {
    client = new AutoRestSwaggerBATXMLServiceBuilder().build();
  }

  @Test
  public void getSimpleDocument() {
    Slideshow slideshow = client.xmls().getSimpleAsync().block();
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
  public void getEmptyList() {
    Slideshow slideshow = client.xmls().getEmptyListAsync().block();
    assertNotNull(slideshow);
    assertNotNull(slideshow.getSlides());
    assertEquals(null, slideshow.getTitle());
    assertEquals(null, slideshow.getAuthor());
    assertEquals(null, slideshow.getDate());
    assertEquals(0, slideshow.getSlides().size());
  }

  @Test
  public void getEmptyWrappedLists() {
    AppleBarrel barrel = client.xmls().getEmptyWrappedListsAsync().block();
    assertNotNull(barrel);
    assertNotNull(barrel.getBadApples());
    assertEquals(0, barrel.getBadApples().size());

    assertNotNull(barrel.getGoodApples());
    assertEquals(0, barrel.getGoodApples().size());
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

    assertNotNull(barrel.getGoodApples());
    assertEquals("Fuji", barrel.getGoodApples().get(0));
    assertEquals("Gala", barrel.getGoodApples().get(1));

    assertNotNull(barrel.getBadApples());
    assertEquals("Red Delicious", barrel.getBadApples().get(0));
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

    assertEquals("Cavendish", bananas.get(0).getName());
    assertEquals("Sweet", bananas.get(0).getFlavor());
    assertEquals("2018-02-28T00:40:00.123Z", bananas.get(0).getExpiration().toString());

    assertEquals("Plantain", bananas.get(1).getName());
    assertEquals("Savory", bananas.get(1).getFlavor());
    assertEquals("2018-02-28T00:40:00.123Z", bananas.get(1).getExpiration().toString());
  }

  @Test
  public void getItemWithEmptyChildElement() {
    Banana banana = client.xmls().getEmptyChildElementAsync().block();
    assertNotNull(banana);

    assertEquals("Unknown Banana", banana.getName());
    assertEquals("", banana.getFlavor());
    assertEquals("2012-02-24T00:53:52.789Z", banana.getExpiration().toString());
  }

  @Test
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
    XmlsGetHeadersHeaders headers = client.xmls().getHeadersWithResponseAsync().block().getDeserializedHeaders();
    assertEquals("custom-value", headers.getCustomHeader());
  }
}
