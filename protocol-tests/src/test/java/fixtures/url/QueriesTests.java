package fixtures.url;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.Base64Util;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueriesTests {
    private static QueriesAsyncClient asyncClient;

    private static QueriesClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestUrlTestServiceBuilder().buildQueriesAsyncClient();
        client = new AutoRestUrlTestServiceBuilder().buildQueriesClient();
    }

    @Test
    public void getBooleanTrue() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("boolQuery", "true");
        client.getBooleanTrueWithResponse(requestOptions, null);
    }

    @Test
    public void getBooleanFalse() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("boolQuery", "false");
        client.getBooleanFalseWithResponse(requestOptions, null);
    }

    @Test
    public void getBooleanNull() {
        client.getBooleanNullWithResponse(null, null);
    }

    @Test
    public void getIntOneMillion() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("intQuery", "1000000");
        client.getIntOneMillionWithResponse(requestOptions, null);
    }

    @Test
    public void getIntNegativeOneMillion() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("intQuery", "-1000000");
        client.getIntNegativeOneMillionWithResponse(requestOptions, null);
    }

    @Test
    public void getIntNull() {
        client.getIntNullWithResponse(null, null);
    }

    @Test
    public void getTenBillion() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("longQuery", "10000000000");
        client.getTenBillionWithResponse(requestOptions, null);
    }

    @Test
    public void getNegativeTenBillion() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("longQuery", "-10000000000");
        client.getNegativeTenBillionWithResponse(requestOptions, null);
    }

    @Test
    public void getLongNull() {
        client.getLongNullWithResponse(null, null);
    }

    @Test
    public void floatScientificPositive() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("floatQuery", "1.034E+20");
        client.floatScientificPositiveWithResponse(requestOptions, null);
    }

    @Test
    public void floatScientificNegative() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("floatQuery", "-1.034E-20");
        client.floatScientificNegativeWithResponse(requestOptions, null);
    }

    @Test
    public void floatNull() {
        client.floatNullWithResponse(null, null);
    }

    @Test
    public void doubleDecimalPositive() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("doubleQuery", "9999999.999");
        client.doubleDecimalPositiveWithResponse(requestOptions, null);
    }

    @Test
    public void doubleDecimalNegative() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("doubleQuery", "-9999999.999");
        client.doubleDecimalNegativeWithResponse(requestOptions, null);
    }

    @Test
    public void doubleNull() {
        client.doubleNullWithResponse(null, null);
    }

    @Test
    public void stringUnicode() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("stringQuery", "啊齄丂狛狜隣郎隣兀﨩");
        client.stringUnicodeWithResponse(requestOptions, null);
    }

    @Test
    public void stringUrlEncoded() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("stringQuery", "begin!*'();:@ &=+$,/?#[]end");
        client.stringUrlEncodedWithResponse(requestOptions, null);
    }

    @Test
    public void stringEmpty() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("stringQuery", "");
        client.stringEmptyWithResponse(requestOptions, null);
    }

    @Test
    public void stringNull() {
        client.stringNullWithResponse(null, null);
    }

    @Test
    public void enumValid() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("enumQuery", "green color");
        client.enumValidWithResponse(requestOptions, null);
    }

    @Test
    public void enumNull() {
        client.enumNullWithResponse(null, null);
    }

    @Test
    public void byteMultiByte() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("byteQuery", Base64Util.encodeToString("啊齄丂狛狜隣郎隣兀﨩".getBytes(StandardCharsets.UTF_8)));
        client.byteMultiByteWithResponse(requestOptions, null);
    }

    @Test
    public void byteEmpty() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("byteQuery", "");
        client.byteEmptyWithResponse(requestOptions, null);
    }

    @Test
    public void byteNull() {
        client.byteNullWithResponse(null, null);
    }

    @Test
    public void dateValid() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("dateQuery", "2012-01-01");
        client.dateValidWithResponse(requestOptions, null);
    }

    @Test
    public void dateNull() {
        client.dateNullWithResponse(null, null);
    }

    @Test
    public void dateTimeValid() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("dateTimeQuery", "2012-01-01T01:01:01Z");
        client.dateTimeValidWithResponse(requestOptions, null);
    }

    @Test
    public void dateTimeNull() {
        client.dateTimeNullWithResponse(null, null);
    }

    @Test
    public void arrayStringCsvValid() {
        RequestOptions requestOptions = new RequestOptions();
        List<String> list = new ArrayList<>();
        list.add("ArrayQuery1");
        list.add("begin!*'();:@ &=+$,/?#[]end");
        list.add(null);
        list.add("");
        String query = JacksonAdapter.createDefaultSerializerAdapter().serializeList(list, CollectionFormat.CSV);
        requestOptions.addQueryParam("arrayQuery", query);
        client.arrayStringCsvValidWithResponse(requestOptions, null);
    }

    @Test
    public void arrayStringCsvNull() {
        client.arrayStringCsvNullWithResponse(null, null);
    }

    @Test
    public void arrayStringCsvEmpty() {
        RequestOptions requestOptions = new RequestOptions();
        String query = JacksonAdapter.createDefaultSerializerAdapter().serializeList(Collections.emptyList(), CollectionFormat.CSV);
        requestOptions.addQueryParam("arrayQuery", query);
        client.arrayStringCsvEmptyWithResponse(requestOptions, null);
    }

    @Test
    public void arrayStringSsvValid() {
        RequestOptions requestOptions = new RequestOptions();
        List<String> list = new ArrayList<>();
        list.add("ArrayQuery1");
        list.add("begin!*'();:@ &=+$,/?#[]end");
        list.add(null);
        list.add("");
        String query = JacksonAdapter.createDefaultSerializerAdapter().serializeList(list, CollectionFormat.SSV);
        requestOptions.addQueryParam("arrayQuery", query);
        client.arrayStringSsvValidWithResponse(requestOptions, null);
    }

    @Test
    public void arrayStringTsvValid() {
        RequestOptions requestOptions = new RequestOptions();
        List<String> list = new ArrayList<>();
        list.add("ArrayQuery1");
        list.add("begin!*'();:@ &=+$,/?#[]end");
        list.add(null);
        list.add("");
        String query = JacksonAdapter.createDefaultSerializerAdapter().serializeList(list, CollectionFormat.TSV);
        requestOptions.addQueryParam("arrayQuery", query);
        client.arrayStringTsvValidWithResponse(requestOptions, null);
    }

    @Test
    public void arrayStringPipesValid() {
        RequestOptions requestOptions = new RequestOptions();
        List<String> list = new ArrayList<>();
        list.add("ArrayQuery1");
        list.add("begin!*'();:@ &=+$,/?#[]end");
        list.add(null);
        list.add("");
        String query = JacksonAdapter.createDefaultSerializerAdapter().serializeList(list, CollectionFormat.PIPES);
        requestOptions.addQueryParam("arrayQuery", query);
        client.arrayStringPipesValidWithResponse(requestOptions, null);
    }
}
