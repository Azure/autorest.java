package fixtures.bodycomplex;

import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class BasicTests {
    private static BasicAsyncClient asyncClient;

    private static BasicClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestComplexTestServiceClientBuilder().buildBasicAsyncClient();
        client = new AutoRestComplexTestServiceClientBuilder().buildBasicClient();
    }

    @Test
    public void getValid() throws Exception {
        BinaryData binaryData = client.getValidWithResponse(null).getValue();
        Map<?, ?> map = binaryData.toObject(Map.class);
        Assertions.assertEquals(2, map.get("id"));
        Assertions.assertEquals("abc", map.get("name"));
        Assertions.assertEquals("YELLOW", map.get("color"));
    }

    @Test
    public void putValid() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"id\": 2, \"name\": \"abc\", \"color\": \"Magenta\"}");
        asyncClient.putValidWithResponse(binaryData, null).block();
    }

    @Test
    public void getEmpty() throws Exception {
        BinaryData binaryData = client.getEmptyWithResponse(null).getValue();
        Map<?, ?> map = binaryData.toObject(Map.class);
        Assertions.assertTrue(map.isEmpty());
    }

    @Test
    public void getNull() throws Exception {
        BinaryData binaryData = client.getNullWithResponse(null).getValue();
        Map<?, ?> map = binaryData.toObject(Map.class);
        Assertions.assertNull(map.get("id"));
        Assertions.assertNull(map.get("name"));
    }

    @Test
    public void getNotProvided() throws Exception {
        Assertions.assertEquals("", client.getNotProvidedWithResponse(null).getValue().toString());
    }
}
