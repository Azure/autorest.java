package fixtures.url;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.Base64Url;
import com.azure.core.util.Base64Util;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PathsTests {
    private static PathsAsyncClient asyncClient;

    private static PathsClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestUrlTestServiceBuilder().buildPathsAsyncClient();
        client = new AutoRestUrlTestServiceBuilder().buildPathsClient();
    }

    @Test
    public void getBooleanTrue() {
        client.getBooleanTrueWithResponse(null);
    }

    @Test
    public void getBooleanFalse() {
        client.getBooleanTrueWithResponse(null);
    }

    @Test
    public void getIntOneMillion() {
        client.getIntOneMillionWithResponse(null);
    }

    @Test
    public void getIntNegativeOneMillion() {
        client.getIntNegativeOneMillionWithResponse(null).getValue();
    }

    @Test
    public void getTenBillion() {
        client.getTenBillionWithResponse(null);
    }

    @Test
    public void getNegativeTenBillion() {
        client.getNegativeTenBillionWithResponse(null);
    }

    @Test
    public void floatScientificPositive() {
        client.floatScientificPositiveWithResponse(null);
    }

    @Test
    public void floatScientificNegative() {
        client.floatScientificNegativeWithResponse(null);
    }

    @Test
    public void doubleDecimalPositive() {
        client.doubleDecimalPositiveWithResponse(null);
    }

    @Test
    public void doubleDecimalNegative() {
        client.doubleDecimalNegativeWithResponse(null);
    }

    @Test
    public void stringUnicode() {
        client.stringUnicodeWithResponse(null);
    }

    @Test
    public void stringUrlEncoded() {
        client.stringUrlEncodedWithResponse(null);
    }

    @Test
    public void stringUrlNonEncoded() {
        client.stringUrlNonEncodedWithResponse(null);
    }

    @Test
    public void stringEmpty() {
        client.stringEmptyWithResponse(null);
    }

    @Test
    public void stringNull() {
        try {
            client.stringNullWithResponse(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void enumValid() {
        client.enumValidWithResponse("green color", null);
    }

    @Test
    public void enumNull() {
        try {
            client.enumNullWithResponse(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void byteMultiByte() {
        client.byteMultiByteWithResponse(Base64Util.encodeToString("啊齄丂狛狜隣郎隣兀﨩".getBytes(StandardCharsets.UTF_8)), null);
    }

    @Test
    public void byteEmpty() {
        client.byteEmptyWithResponse(null).getValue();
    }

    @Test
    public void byteNull() {
        try {
            client.byteNullWithResponse(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void dateValid() {
        client.dateValidWithResponse(null).getValue();
    }

    @Test
    public void dateNull() {
        try {
            client.dateNullWithResponse(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void dateTimeValid() {
        client.dateTimeValidWithResponse(null).getValue();
    }

    @Test
    public void dateTimeNull() {
        try {
            client.dateTimeNullWithResponse(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void base64Url() {
        client.base64UrlWithResponse(Base64Url.encode("lorem".getBytes(StandardCharsets.UTF_8)).toString(), null);
    }

    @Test
    public void arrayCsvInPath() {
        RequestOptions requestOptions = new RequestOptions();
        List<String> list = new ArrayList<>();
        list.add("ArrayPath1");
        list.add("begin!*'();:@ &=+$,/?#[]end");
        list.add(null);
        list.add("");
        String path = JacksonAdapter.createDefaultSerializerAdapter().serializeList(list, CollectionFormat.CSV);
        client.arrayCsvInPathWithResponse(path, null);
    }

    @Test
    public void unixTimeUrl() {
        client.unixTimeUrlWithResponse(1460505600, null);
    }
}
