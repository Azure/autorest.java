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
        client.getBooleanTrue(requestOptions);
    }

    @Test
    public void getBooleanFalse() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("boolQuery", "false");
        client.getBooleanFalse(requestOptions);
    }

    @Test
    public void getBooleanNull() {
        client.getBooleanNull(null);
    }

    @Test
    public void getIntOneMillion() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("intQuery", "1000000");
        client.getIntOneMillion(requestOptions);
    }

    @Test
    public void getIntNegativeOneMillion() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("intQuery", "-1000000");
        client.getIntNegativeOneMillion(requestOptions);
    }

    @Test
    public void getIntNull() {
        client.getIntNull(null);
    }

    @Test
    public void getTenBillion() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("longQuery", "10000000000");
        client.getTenBillion(requestOptions);
    }

    @Test
    public void getNegativeTenBillion() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("longQuery", "-10000000000");
        client.getNegativeTenBillion(requestOptions);
    }

    @Test
    public void getLongNull() {
        client.getLongNull(null);
    }

    @Test
    public void floatScientificPositive() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("floatQuery", "1.034E+20");
        client.floatScientificPositive(requestOptions);
    }

    @Test
    public void floatScientificNegative() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("floatQuery", "-1.034E-20");
        client.floatScientificNegative(requestOptions);
    }

    @Test
    public void floatNull() {
        client.floatNull(null);
    }

    @Test
    public void doubleDecimalPositive() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("doubleQuery", "9999999.999");
        client.doubleDecimalPositive(requestOptions);
    }

    @Test
    public void doubleDecimalNegative() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("doubleQuery", "-9999999.999");
        client.doubleDecimalNegative(requestOptions);
    }

    @Test
    public void doubleNull() {
        client.doubleNull(null);
    }

    @Test
    public void stringUnicode() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("stringQuery", "啊齄丂狛狜隣郎隣兀﨩");
        client.stringUnicode(requestOptions);
    }

    @Test
    public void stringUrlEncoded() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("stringQuery", "begin!*'();:@ &=+$,/?#[]end");
        client.stringUrlEncoded(requestOptions);
    }

    @Test
    public void stringEmpty() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("stringQuery", "");
        client.stringEmpty(requestOptions);
    }

    @Test
    public void stringNull() {
        client.stringNull(null);
    }

    @Test
    public void enumValid() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("enumQuery", "green color");
        client.enumValid(requestOptions);
    }

    @Test
    public void enumNull() {
        client.enumNull(null);
    }

    @Test
    public void byteMultiByte() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("byteQuery", Base64Util.encodeToString("啊齄丂狛狜隣郎隣兀﨩".getBytes(StandardCharsets.UTF_8)));
        client.byteMultiByte(requestOptions);
    }

    @Test
    public void byteEmpty() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("byteQuery", "");
        client.byteEmpty(requestOptions);
    }

    @Test
    public void byteNull() {
        client.byteNull(null);
    }

    @Test
    public void dateValid() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("dateQuery", "2012-01-01");
        client.dateValid(requestOptions);
    }

    @Test
    public void dateNull() {
        client.dateNull(null);
    }

    @Test
    public void dateTimeValid() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("dateTimeQuery", "2012-01-01T01:01:01Z");
        client.dateTimeValid(requestOptions);
    }

    @Test
    public void dateTimeNull() {
        client.dateTimeNull(null);
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
        client.arrayStringCsvValid(requestOptions);
    }

    @Test
    public void arrayStringCsvNull() {
        client.arrayStringCsvNull(null);
    }

    @Test
    public void arrayStringCsvEmpty() {
        RequestOptions requestOptions = new RequestOptions();
        String query = JacksonAdapter.createDefaultSerializerAdapter().serializeList(Collections.emptyList(), CollectionFormat.CSV);
        requestOptions.addQueryParam("arrayQuery", query);
        client.arrayStringCsvEmpty(requestOptions);
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
        client.arrayStringSsvValid(requestOptions);
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
        client.arrayStringTsvValid(requestOptions);
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
        client.arrayStringPipesValid(requestOptions);
    }
}
