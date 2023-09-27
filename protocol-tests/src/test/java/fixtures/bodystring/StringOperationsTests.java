package fixtures.bodystring;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.util.Base64Util;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class StringOperationsTests {
    private static StringOperationClient client;
    private static StringOperationAsyncClient asyncClient;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeAll
    public static void setup() {
        client = new StringOperationClientBuilder().buildClient();
        asyncClient = new StringOperationClientBuilder().buildAsyncClient();
    }

    @Test
    public void getNull() {
        String result = client.getNullWithResponse(null).getValue().toObject(String.class);
        Assertions.assertNull(result);
    }

    @Test
    public void putNull() {
        try {
            client.putNullWithResponse(null).getValue();
        } catch (Exception ex) {
            Assertions.assertEquals(IllegalArgumentException.class, ex.getClass());
            Assertions.assertTrue(ex.getMessage().contains("Argument for @BodyParam parameter must be non-null"));
        }
    }

    @Test
    public void getEmpty() {
        String result = client.getEmptyWithResponse(null).getValue().toObject(String.class);
        Assertions.assertEquals("", result);
    }

    @Test
    public void putEmpty() {
        asyncClient.putEmptyWithResponse(null).block();
    }

    @Test
    public void getMbcs() {
        String result = client.getMbcsWithResponse(null).getValue().toObject(String.class);
        String expected = "啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑ\uE7C7ɡ〇〾⿻⺁\uE843䜣\uE864€";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void putMbcs() {
        client.putMbcsWithResponse(null).getValue();
    }

    @Test
    public void getWhitespace() {
        String result = client.getWhitespaceWithResponse(null).getValue().toObject(String.class);
        Assertions.assertEquals("    Now is the time for all good men to come to the aid of their country    ", result);
    }

    @Test
    public void putWhitespace() {
        client.putWhitespaceWithResponse(null).getValue();
    }

    @Test
    public void getNotProvided() {
        try {
            client.getNotProvidedWithResponse(null).getValue();
        } catch (Exception ex) {
            Assertions.assertEquals(HttpResponseException.class, ex.getClass());
            Assertions.assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getBase64Encoded() {
        BinaryData result = client.getBase64EncodedWithResponse(null).getValue();
        Assertions.assertEquals("a string that gets encoded with base64",
            new String(result.toObject(byte[].class), StandardCharsets.UTF_8));
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
    public void getBase64UrlEncoded() {
        String result = client.getBase64UrlEncodedWithResponse(null).getValue().toObject(String.class);
        Assertions.assertEquals("a string that gets encoded with base64url",
                new String(Base64Util.decodeURL(result.getBytes(StandardCharsets.UTF_8))));
    }

    @Test
    public void getNullBase64UrlEncoded() {
        byte[] result = client.getNullBase64UrlEncodedWithResponse(null).getValue().toBytes();
        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void putBase64UrlEncoded() {
        client.putBase64UrlEncodedWithResponse(
                BinaryData.fromObject(new String(Base64Util.encodeURLWithoutPadding(
                "a string that gets encoded with base64url".getBytes(StandardCharsets.UTF_8)))),
                null
        );
    }
}
