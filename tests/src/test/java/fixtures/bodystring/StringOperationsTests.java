package fixtures.bodystring;

import com.azure.core.exception.HttpResponseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StringOperationsTests {
    private static AutoRestSwaggerBatService client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBatServiceBuilder().build();
    }

    @Test
    public void getNull() throws Exception {
        Assert.assertNull(client.strings().getNull());
    }

    @Test
    public void putNull() throws Exception {
        try {
            client.strings().putNullWithResponseAsync().block();
        } catch (Exception ex) {
            Assert.assertEquals(IllegalArgumentException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("Argument for @BodyParam parameter must be non-null"));
        }
    }

    @Test
    public void getEmpty() throws Exception {
        String result = client.strings().getEmpty();
        Assert.assertEquals("", result);
    }

    @Test
    public void putEmpty() throws Exception {
        client.strings().putEmptyWithResponseAsync().subscribe(v -> {}, t -> fail(t.getMessage()),
            () -> lock.countDown());
        assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void getMbcs() throws Exception {
        String result = client.strings().getMbcs();
        String expected = "啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑ\uE7C7ɡ〇〾⿻⺁\uE843䜣\uE864€";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void putMbcs() throws Exception {
        client.strings().putMbcsWithResponseAsync().block();
    }

    @Test
    public void getWhitespace() throws Exception {
        String result = client.strings().getWhitespace();
        Assert.assertEquals("    Now is the time for all good men to come to the aid of their country    ", result);
    }

    @Test
    public void putWhitespace() throws Exception {
        client.strings().putWhitespaceWithResponseAsync().block();
    }

    @Test
    public void getNotProvided() throws Exception {
        try {
            client.strings().getNotProvided();
        } catch (Exception ex) {
            Assert.assertEquals(HttpResponseException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getBase64Encoded() throws Exception {
        byte[] result = client.strings().getBase64Encoded();
        Assert.assertEquals("a string that gets encoded with base64", new String(result));
    }

    @Test
    public void getBase64UrlEncoded() throws Exception {
        byte[] result = client.strings().getBase64UrlEncoded();
        Assert.assertEquals("a string that gets encoded with base64url", new String(result));
    }

    @Test
    public void getNullBase64UrlEncoded() throws Exception {
        byte[] result = client.strings().getNullBase64UrlEncoded();
        Assert.assertEquals(0, result.length);
    }

    @Test
    public void putBase64UrlEncoded() throws Exception {
        client.strings().putBase64UrlEncodedWithResponseAsync("a string that gets encoded with base64url".getBytes()).block();
    }
}
