package com.azure.autoresttest;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BodyStringSyncClientTests {
    private static StringOperationClient client;

    @BeforeClass
    public static void setup() {
        client = new StringOperationClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNull() throws Exception {
        Assert.assertNull(client.getNullWithRestResponse().getValue());
    }

    @Test
    public void putNull() throws Exception {
        try {
            Response<Void> putResult = client.putNullWithRestResponse(null);
            Assert.fail();
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
        Response<String> notProvidedResponse = client.getNotProvidedWithRestResponse();
        assertEquals(200, notProvidedResponse.getStatusCode());
        assertNull(notProvidedResponse.getValue());
    }

    @Test
    public void getBase64Encoded() throws Exception {
        byte[] result = client.getBase64EncodedWithRestResponse().getValue();
        Assert.assertEquals("a string that gets encoded with base64", byteToString(result));
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

    public static String byteToString(byte[] bytes) {
        return new String(Base64.getDecoder().decode(unquote(new String(bytes, StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
    }

    @Test
    public void getBase64UrlEncoded() throws Exception {
        byte[] result = client.getBase64EncodedWithRestResponse().getValue();
        Assert.assertEquals("a string that gets encoded with base64", byteToString(result));
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
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertEquals(HttpResponseException.class, ex.getClass());
        }
    }
}
