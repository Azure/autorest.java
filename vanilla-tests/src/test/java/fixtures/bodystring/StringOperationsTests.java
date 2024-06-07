package fixtures.bodystring;

import com.azure.core.exception.HttpResponseException;
import fixtures.bodystring.implementation.AutoRestSwaggerBATServiceImplBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringOperationsTests {
    private static AutoRestSwaggerBATService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestSwaggerBATServiceImplBuilder().buildClient();
    }

    @Test
    public void getNull() throws Exception {
        assertNull(client.getStringOperations().getNull());
    }

    @Test
    public void putNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> client.getStringOperations().putNullWithResponseAsync(null).block());
        assertTrue(ex.getMessage().contains("Argument for @BodyParam parameter must be non-null"));
    }

    @Test
    public void getEmpty() throws Exception {
        String result = client.getStringOperations().getEmpty();
        assertEquals("", result);
    }

    @Test
    public void putEmpty() throws Exception {
        StepVerifier.create(client.getStringOperations().putEmptyWithResponseAsync())
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void getMbcs() {
        String result = client.getStringOperations().getMbcs();
        String expected = "啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑ\uE7C7ɡ〇〾⿻⺁\uE843䜣\uE864€";
        assertEquals(expected, result);
    }

    @Test
    public void putMbcs() {
        client.getStringOperations().putMbcsWithResponseAsync().block();
    }

    @Test
    public void getWhitespace() {
        String result = client.getStringOperations().getWhitespace();
        assertEquals("    Now is the time for all good men to come to the aid of their country    ", result);
    }

    @Test
    public void putWhitespace() {
        client.getStringOperations().putWhitespaceWithResponseAsync().block();
    }

    @Test
    public void getNotProvided() {
        HttpResponseException exception = assertThrows(HttpResponseException.class, () -> client.getStringOperations().getNotProvided());
        assertTrue(exception.getMessage().contains("JsonMappingException"));
    }

    @Test
    public void getBase64Encoded() {
        byte[] result = client.getStringOperations().getBase64Encoded();
        assertEquals("a string that gets encoded with base64",
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
    public void getBase64UrlEncoded() {
        byte[] result = client.getStringOperations().getBase64UrlEncoded();
        assertEquals("a string that gets encoded with base64url", new String(result));
    }

    @Test
    public void getNullBase64UrlEncoded() {
        byte[] result = client.getStringOperations().getNullBase64UrlEncoded();
        assertTrue(result == null || result.length == 0);
    }

    @Test
    public void putBase64UrlEncoded() {
        client.getStringOperations().putBase64UrlEncodedWithResponseAsync("a string that gets encoded with base64url".getBytes()).block();
    }
}
