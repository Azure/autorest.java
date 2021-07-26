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
        client.getBooleanTrue(null);
    }

    @Test
    public void getBooleanFalse() {
        client.getBooleanTrue(null);
    }

    @Test
    public void getIntOneMillion() {
        client.getIntOneMillion(null);
    }

    @Test
    public void getIntNegativeOneMillion() {
        client.getIntNegativeOneMillion(null);
    }

    @Test
    public void getTenBillion() {
        client.getTenBillion(null);
    }

    @Test
    public void getNegativeTenBillion() {
        client.getNegativeTenBillion(null);
    }

    @Test
    public void floatScientificPositive() {
        client.floatScientificPositive(null);
    }

    @Test
    public void floatScientificNegative() {
        client.floatScientificNegative(null);
    }

    @Test
    public void doubleDecimalPositive() {
        client.doubleDecimalPositive(null);
    }

    @Test
    public void doubleDecimalNegative() {
        client.doubleDecimalNegative(null);
    }

    @Test
    public void stringUnicode() {
        client.stringUnicode(null);
    }

    @Test
    public void stringUrlEncoded() {
        client.stringUrlEncoded(null);
    }

    @Test
    public void stringUrlNonEncoded() {
        client.stringUrlNonEncoded(null);
    }

    @Test
    public void stringEmpty() {
        client.stringEmpty(null);
    }

    @Test
    public void stringNull() {
        try {
            client.stringNull(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void enumValid() {
        client.enumValid("green color", null);
    }

    @Test
    public void enumNull() {
        try {
            client.enumNull(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void byteMultiByte() {
        client.byteMultiByte(Base64Util.encodeToString("啊齄丂狛狜隣郎隣兀﨩".getBytes(StandardCharsets.UTF_8)), null);
    }

    @Test
    public void byteEmpty() {
        client.byteEmpty(null);
    }

    @Test
    public void byteNull() {
        try {
            client.byteNull(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void dateValid() {
        client.dateValid(null);
    }

    @Test
    public void dateNull() {
        try {
            client.dateNull(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void dateTimeValid() {
        client.dateTimeValid(null);
    }

    @Test
    public void dateTimeNull() {
        try {
            client.dateTimeNull(null, null);
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void base64Url() {
        client.base64Url(Base64Url.encode("lorem".getBytes(StandardCharsets.UTF_8)).toString(), null);
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
        client.arrayCsvInPath(path, null);
    }

    @Test
    public void unixTimeUrl() {
        client.unixTimeUrl(1460505600, null);
    }
}
