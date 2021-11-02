package fixtures.bodycomplex;

import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class DictionaryTests {
    private static DictionaryAsyncClient asyncClient;

    private static DictionaryClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestComplexTestServiceBuilder().buildDictionaryAsyncClient();
        client = new AutoRestComplexTestServiceBuilder().buildDictionaryClient();
    }

    @Test
    public void getValid() throws Exception {
        BinaryData binaryData = client.getValidWithResponse(null).getValue();
        Map map = (Map)binaryData.toObject(Map.class).get("defaultProgram");
        Assertions.assertEquals(5, map.size());
        Assertions.assertEquals("", map.get("exe"));
        Assertions.assertNull(map.get(""));
    }

    @Test
    public void putValid() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"defaultProgram\":{\"txt\":\"notepad\",\"bmp\":\"mspaint\",\"xls\":\"excel\",\"exe\":\"\",\"\":null}}");
        asyncClient.putValidWithResponse(binaryData, null).block();
    }

    @Test
    public void getEmpty() throws Exception {
        BinaryData binaryData = client.getEmptyWithResponse(null).getValue();
        Map map = binaryData.toObject(Map.class);
        Map program = (Map)map.get("defaultProgram");
        Assertions.assertEquals(0, program.size());
    }

    @Test
    public void putEmpty() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"defaultProgram\":{}}");
        asyncClient.putEmptyWithResponse(binaryData, null).block();
    }

    @Test
    public void getNull() throws Exception {
        BinaryData binaryData = client.getNullWithResponse(null).getValue();
        Map map = binaryData.toObject(Map.class);
        Assertions.assertNull(map.get("defaultProgram"));
    }

    @Test
    public void getNotProvided() throws Exception {
        BinaryData binaryData = client.getNotProvidedWithResponse(null).getValue();
        Map map = binaryData.toObject(Map.class);
        Assertions.assertEquals(0, map.size());
    }
}
