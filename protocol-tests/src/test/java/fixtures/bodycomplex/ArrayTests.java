package fixtures.bodycomplex;

import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ArrayTests {
    private static ArrayAsyncClient asyncClient;

    private static ArrayClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestComplexTestServiceClientBuilder().buildArrayAsyncClient();
        client = new AutoRestComplexTestServiceClientBuilder().buildArrayClient();
    }

    @Test
    public void getValid() throws Exception {
        BinaryData binaryData = client.getValidWithResponse(null).getValue();
        List<String> list = (List)binaryData.toObject(Map.class).get("array");
        Assertions.assertEquals(5, list.size());
        Assertions.assertEquals("&S#$(*Y", list.get(3));
    }

    @Test
    public void putValid() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"array\":[\"1, 2, 3, 4\",\"\",null,\"&S#$(*Y\",\"The quick brown fox jumps over the lazy dog\"]}");
        client.putValidWithResponse(binaryData, null);
    }

    @Test
    public void getEmpty() throws Exception {
        BinaryData binaryData = client.getEmptyWithResponse(null).getValue();
        List<String> list = (List)binaryData.toObject(Map.class).get("array");
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void putEmpty() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"array\":[]}");
        asyncClient.putEmptyWithResponse(binaryData, null).block();
    }

    @Test
    public void getNotProvided() throws Exception {
        BinaryData binaryData = client.getNotProvidedWithResponse(null).getValue();
        List<String> list = (List)binaryData.toObject(Map.class).get("array");
        Assertions.assertNull(list);
    }
}
