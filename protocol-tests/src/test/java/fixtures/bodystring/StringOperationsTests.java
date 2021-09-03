package fixtures.bodystring;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.util.Base64Util;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class StringOperationsTests {
    private static StringOperationClient client;
    private static StringOperationAsyncClient asyncClient;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeAll
    public static void setup() {
        client = new AutoRestSwaggerBATServiceBuilder().buildStringOperationClient();
        asyncClient = new AutoRestSwaggerBATServiceBuilder().buildStringOperationAsyncClient();
    }

    @Test
    public void getNull() throws Exception {
        String result = client.getNullWithResponse(null, null).getValue().toObject(String.class);
        Assertions.assertNull(result);
    }

    @Test
    public void putNull() throws Exception {
        try {
            client.putNullWithResponse(null, null).getValue();
        } catch (Exception ex) {
            Assertions.assertEquals(IllegalArgumentException.class, ex.getClass());
            Assertions.assertTrue(ex.getMessage().contains("Argument for @BodyParam parameter must be non-null"));
        }
    }

    @Test
    public void getEmpty() throws Exception {
        String result = client.getEmptyWithResponse(null, null).getValue().toObject(String.class);
        Assertions.assertEquals("", result);
    }

    @Test
    public void putEmpty() throws Exception {
        asyncClient.putEmptyWithResponse(null).subscribe(v -> {}, t -> Assertions.fail(t.getMessage()),
            () -> lock.countDown());
        Assertions.assertTrue(lock.await(1000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void getMbcs() throws Exception {
        String result = client.getMbcsWithResponse(null, null).getValue().toObject(String.class);
        String expected = "啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑ\uE7C7ɡ〇〾⿻⺁\uE843䜣\uE864€";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void putMbcs() throws Exception {
        client.putMbcsWithResponse(null, null).getValue();
    }

    @Test
    public void getWhitespace() throws Exception {
        String result = client.getWhitespaceWithResponse(null, null).getValue().toObject(String.class);
        Assertions.assertEquals("    Now is the time for all good men to come to the aid of their country    ", result);
    }

    @Test
    public void putWhitespace() throws Exception {
        client.putWhitespaceWithResponse(null, null).getValue();
    }

    @Test
    public void getNotProvided() throws Exception {
        try {
            client.getNotProvidedWithResponse(null, null).getValue();
        } catch (Exception ex) {
            Assertions.assertEquals(HttpResponseException.class, ex.getClass());
            Assertions.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getBase64Encoded() throws Exception {
        byte[] result = client.getBase64EncodedWithResponse(null, null).getValue();
        Assertions.assertEquals("a string that gets encoded with base64",
            new String(Base64.getDecoder().decode(unquote(new String(result, StandardCharsets.UTF_8))), StandardCharsets.UTF_8));
    }

    // copied from azure-core
    private static String unquote(String string) {
        if (string != null && !string.isEmpty()) {
            final char firstCharacter = string.charAt(0);
            if (firstCharacter == '\"' || firstCharacter == '\'') {
                final int base64UrlStringLength = string.length();
                final char lastCharacter = string.charAt(base64UrlStringLength - 1);
                if (lastCharacter == firstCharacter) {
                    string = string.substring(1, base64UrlStringLength - 1);
                }
            }
        }
        return string;
    }

    @Test
    public void getBase64UrlEncoded() throws Exception {
        String result = client.getBase64UrlEncodedWithResponse(null, null).getValue().toObject(String.class);
        Assertions.assertEquals("a string that gets encoded with base64url",
                new String(Base64Util.decodeURL(result.getBytes(StandardCharsets.UTF_8))));
    }

    @Test
    public void getNullBase64UrlEncoded() throws Exception {
        byte[] result = client.getNullBase64UrlEncodedWithResponse(null, null).getValue().toBytes();
        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void putBase64UrlEncoded() throws Exception {
        client.putBase64UrlEncodedWithResponse(
                BinaryData.fromObject(new String(Base64Util.encodeURLWithoutPadding(
                "a string that gets encoded with base64url".getBytes(StandardCharsets.UTF_8)))),
                null,
                null
        );
    }
}
