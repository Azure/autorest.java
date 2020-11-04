package com.azure.autoresttest;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.util.Base64Url;
import com.azure.androidtest.fixtures.bodystring.StringOperationClient;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class BodyStringSyncClientTests {
    private static StringOperationClient client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new StringOperationClient.Builder().host(TestConstants.TestServerRootUrl).build();
    }

    @Test
    public void getNull() throws Exception {
        Assert.assertNull(client.getNullWithRestResponse().getValue());
    }

    @Test
    public void putNull() throws Exception {
        try {
            Response<Void> putResult = client.putNullWithRestResponse(null);
            assertEquals(200, putResult.getStatusCode());
        } catch (Exception ex) {
            Assert.assertEquals(NullPointerException.class, ex.getClass());
        }
    }

    @Test
    public void getEmpty() throws Exception {
        String result = client.getEmptyWithRestResponse().getValue();
        Assert.assertEquals("", result);
    }

    @Test
    public void putEmpty() throws Exception {
        Response<Void> putResult = client.putEmptyWithRestResponse();
        assertEquals(200, putResult.getStatusCode());
    }

    @Test
    public void getMbcs() throws Exception {
        String result = client.getMbcsWithRestResponse().getValue();
        String expected = "啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑ\uE7C7ɡ〇〾⿻⺁\uE843䜣\uE864€";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void putMbcs() throws Exception {
        Response<Void> putResult = client.putMbcsWithRestResponse();
        assertEquals(200, putResult.getStatusCode());
    }

    @Test
    public void getWhitespace() throws Exception {
        String result = client.getWhitespaceWithRestResponse().getValue();
        Assert.assertEquals("    Now is the time for all good men to come to the aid of their country    ", result);
    }

    @Test
    public void putWhitespace() throws Exception {
        Response<Void> putResult = client.putWhitespaceWithRestResponse();
        assertEquals(200, putResult.getStatusCode());
    }

    @Test
    public void getNotProvided() throws Exception {
        try {
            client.getNotProvidedWithRestResponse();
        } catch (Exception ex) {
            Assert.assertEquals(HttpResponseException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("JsonMappingException"));
        }
    }

    @Test
    public void getBase64Encoded() throws Exception {
        byte[] result = client.getBase64EncodedWithRestResponse().getValue();
        Assert.assertEquals("a string that gets encoded with base64", new String(result, StandardCharsets.UTF_8));
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
        byte[] result = client.getBase64EncodedWithRestResponse().getValue();
        String resultString = new String(result);
        Assert.assertEquals("a string that gets encoded with base64", resultString);
    }

    @Test
    public void getNullBase64UrlEncoded() throws Exception {
        Base64Url result = client.getNullBase64UrlEncodedWithRestResponse().getValue();
        Assert.assertNull(result);
    }

    @Test
    public void putBase64UrlEncoded() throws Exception {
        try {
            Response<Void> putResult = client.putBase64UrlEncodedWithRestResponse(new Base64Url("http://myhost.com/path?email=a.b@c.com&value=a%20B"));
            assertEquals(200, putResult.getStatusCode());
        }
        catch (Exception ex){
            Assert.assertEquals(HttpResponseException.class, ex.getClass());
        }
    }
}
