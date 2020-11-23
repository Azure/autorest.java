package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.util.Base64Url;
import com.azure.androidtest.fixtures.url.PathsClient;
import com.azure.androidtest.fixtures.url.models.UriColor;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.OffsetDateTime;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class UrlPathsSyncClientTests {
    private static PathsClient client;

    @BeforeClass
    public static void setup() {
        client = new PathsClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getBooleanTrue() {
        Response<Void> getResponse = client.getBooleanTrueWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getBooleanFalse() {
        Response<Void> getResponse = client.getBooleanFalseWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getIntOneMillion() {
        Response<Void> getResponse = client.getIntOneMillionWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getIntNegativeOneMillion() {
        Response<Void> getResponse = client.getIntNegativeOneMillionWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getTenBillion() {
        Response<Void> getResponse = client.getTenBillionWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getNegativeTenBillion() {
        Response<Void> getResponse = client.getNegativeTenBillionWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void floatScientificPositive() {
        Response<Void> getResponse = client.floatScientificPositiveWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void floatScientificNegative() {
        Response<Void> getResponse = client.floatScientificNegativeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void doubleDecimalPositive() {
        Response<Void> getResponse = client.doubleDecimalPositiveWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void doubleDecimalNegative() {
        Response<Void> getResponse = client.doubleDecimalNegativeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void stringUnicode() {
        Response<Void> getResponse = client.stringUnicodeWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void stringUrlEncoded() {
        Response<Void> getResponse = client.stringUrlEncodedWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void stringUrlNonEncoded() {
        Response<Void> getResponse = client.stringUrlNonEncodedWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void stringEmpty() {
        Response<Void> getResponse = client.stringEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void stringNull() {
        try {
            Response<Void> getResponse = client.stringNullWithRestResponse(null);
            Assert.fail();
        }
        catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("value must not be null"));
        }
    }

    @Test
    public void enumValid() {
        Response<Void> getResponse = client.enumValidWithRestResponse(UriColor.GREEN_COLOR);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void enumNull() {
        try {
            Response<Void> getResponse = client.enumNullWithRestResponse(null);
            Assert.fail();
        }
        catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("value must not be null"));
        }
    }

    /** TODO: Server returned 500. Fix Base64Util
     * The reason is in Azure core Base64Util.encodeToString function appended "%0A" at the end.
    *
    @Test
    public void byteMultiByte() throws UnsupportedEncodingException {
        Response<Void> getResponse = client.byteMultiByteWithRestResponse("啊齄丂狛狜隣郎隣兀﨩".getBytes("UTF-8"));
        assertEquals(200, getResponse.getStatusCode());
    }
     */

    @Test
    public void byteEmpty() {
        Response<Void> getResponse = client.byteEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void byteNull() {
        try {
            Response<Void> getResponse = client.byteNullWithRestResponse(null);
            Assert.fail();
        }
        catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("value must not be null"));
        }
    }

    @Test
    public void dateValid() {
        Response<Void> getResponse = client.dateValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void dateNull() {
        try {
            Response<Void> getResponse = client.dateNullWithRestResponse(null);
        }
        catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("value must not be null"));
        }
    }

    @Test
    public void dateTimeValid() {
        Response<Void> getResponse = client.dateTimeValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void dateTimeNull() {
        try {
            Response<Void> getResponse = client.dateTimeNullWithRestResponse(null);
            Assert.fail();
        }
        catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("value must not be null"));
        }
    }

    /** TODO: Server returned 400. Fix Base64Url
     * In Base64Url encoding of the url doesn't match behavior in other language.
    @Test
    public void base64Url() {
        Response<Void> getResponse = client.base64UrlWithRestResponse(new Base64Url("lorem"));
        assertEquals(200, getResponse.getStatusCode());
    }
     */

    @Test
    public void arrayCsvInPath() {
        List<String> arrayPath = new ArrayList<>();
        arrayPath.add("ArrayPath1");
        arrayPath.add("begin!*'();:@ &=+$,/?#[]end");
        arrayPath.add(null);
        arrayPath.add("");
        Response<Void> getResponse = client.arrayCsvInPathWithRestResponse(arrayPath);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void unixTimeUrl() {
        Response<Void> getResponse = client.unixTimeUrlWithRestResponse(OffsetDateTime.parse("2016-04-13T00:00:00Z"));
        assertEquals(200, getResponse.getStatusCode());
    }
}
