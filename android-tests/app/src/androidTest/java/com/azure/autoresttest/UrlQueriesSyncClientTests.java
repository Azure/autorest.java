package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.url.QueriesClient;
import com.azure.androidtest.fixtures.url.models.UriColor;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.LocalDate;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UrlQueriesSyncClientTests {

    private static QueriesClient client;

    @BeforeClass
    public static void setup() {
        client = new QueriesClient.Builder().host(TestConstants.TestServerRootUrl).build();
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
    public void getBooleanNull() {
        Response<Void> getResponse = client.getBooleanNullWithRestResponse(null);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getIntOneMillion() {
        Response<Void> getResponse = client.getIntOneMillionWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getIntNegativeOneMillion() {
        Response<Void> getResponse = client.getNegativeTenBillionWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getIntNull() {
        Response<Void> getResponse = client.getIntNullWithRestResponse(null);
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
    public void getLongNull() {
        Response<Void> getResponse = client.getLongNullWithRestResponse(null);
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
    public void floatNull() {
        Response<Void> getResponse = client.floatNullWithRestResponse(null);
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
    public void doubleNull() {
        Response<Void> getResponse = client.doubleNullWithRestResponse(null);
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
    public void stringEmpty() {
        Response<Void> getResponse = client.stringEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void stringNull() {
        Response<Void> getResponse = client.stringNullWithRestResponse(null);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void enumValid() {
        Response<Void> getResponse = client.enumValidWithRestResponse(UriColor.GREEN_COLOR);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void enumNull() {
        Response<Void> getResponse = client.enumNullWithRestResponse(null);
        assertEquals(200, getResponse.getStatusCode());
    }

    /**
     * TODO: TestServer returned 500. Fix Base64Util
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
    public void byteNull() throws UnsupportedEncodingException {
        Response<Void> getResponse = client.byteNullWithRestResponse(null);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void dateValid() {
        Response<Void> getResponse = client.dateValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void dateNull() {
        Response<Void> getResponse = client.dateNullWithRestResponse(null);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void dateTimeValid() {
        Response<Void> getResponse = client.dateTimeValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void dateTimeNull() {
        Response<Void> getResponse = client.dateNullWithRestResponse(null);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void arrayStringCsvValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        Response<Void> getResponse = client.arrayStringCsvValidWithRestResponse(query);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void arrayStringCsvNull() {
        Response<Void> getResponse = client.arrayStringCsvNullWithRestResponse(null);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void arrayStringCsvEmpty() {
        Response<Void> getResponse = client.arrayStringCsvEmptyWithRestResponse(new ArrayList<String>());
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void arrayStringSsvValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        Response<Void> getResponse = client.arrayStringSsvValidWithRestResponse(query);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void arrayStringTsvValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        Response<Void> getResponse = client.arrayStringTsvValidWithRestResponse(query);
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void arrayStringPipesValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        Response<Void> getResponse = client.arrayStringPipesValidWithRestResponse(query);
        assertEquals(200, getResponse.getStatusCode());
    }
}
