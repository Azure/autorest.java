package fixtures.bodycomplex;

import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PrimitiveTests {
    private static PrimitiveAsyncClient asyncClient;

    private static PrimitiveClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new PrimitiveClientBuilder().buildAsyncClient();
        client = new PrimitiveClientBuilder().buildClient();
    }

    @Test
    public void getInt() throws Exception {
        BinaryData binaryData = client.getIntWithResponse(null).getValue();
        Map map = binaryData.toObject(Map.class);
        Assertions.assertEquals(-1, map.get("field1"));
        Assertions.assertEquals(2, map.get("field2"));
    }

    @Test
    public void putInt() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"field1\": -1, \"field2\": 2}");
        asyncClient.putIntWithResponse(binaryData, null).block();
    }

    @Test
    public void getLong() throws Exception {
        BinaryData binaryData = client.getLongWithResponse(null).getValue();
        Map map = binaryData.toObject(Map.class);
        Assertions.assertEquals(1099511627775L, map.get("field1"));
        Assertions.assertEquals(-999511627788L, map.get("field2"));
    }

    @Test
    public void putLong() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"field1\": 1099511627775, \"field2\": -999511627788}");
        asyncClient.putLongWithResponse(binaryData, null).block();
    }

    @Test
    public void getFloat() throws Exception {
        BinaryData binaryData = client.getFloatWithResponse(null).getValue();
        Map map = binaryData.toObject(Map.class);
        Assertions.assertEquals(1.05, map.get("field1"));
        Assertions.assertEquals(-0.003, map.get("field2"));
    }

    @Test
    public void putFloat() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"field1\": 1.05, \"field2\": -0.003}");
        asyncClient.putFloatWithResponse(binaryData, null).block();
    }

    @Test
    public void getDouble() throws Exception {
        BinaryData binaryData = client.getDoubleWithResponse(null).getValue();
        Map map = binaryData.toObject(Map.class);
        Assertions.assertEquals(3e-100, map.get("field1"));
        Assertions.assertEquals(-0.000000000000000000000000000000000000000000000000000000005,
                map.get("field_56_zeros_after_the_dot_and_negative_zero_before_dot_and_this_is_a_long_field_name_on_purpose"));
    }

    @Test
    public void putDouble() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"field1\": 3e-100, \"field_56_zeros_after_the_dot_and_negative_zero_before_dot_and_this_is_a_long_field_name_on_purpose\": -5e-57}");
        asyncClient.putDoubleWithResponse(binaryData, null).block();
    }

    @Test
    public void getBool() throws Exception {
        BinaryData binaryData = client.getBoolWithResponse(null).getValue();
        Map map = binaryData.toObject(Map.class);
        Assertions.assertEquals(true, map.get("field_true"));
        Assertions.assertEquals(false, map.get("field_false"));
    }

    @Test
    public void putBool() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"field_true\": true, \"field_false\": false}");
        asyncClient.putBoolWithResponse(binaryData, null);
    }

    @Test
    public void getString() throws Exception {
        BinaryData binaryData = client.getStringWithResponse(null).getValue();
        Map map = binaryData.toObject(Map.class);
        Assertions.assertEquals("goodrequest", map.get("field"));
        Assertions.assertEquals("", map.get("empty"));
        Assertions.assertEquals(null, map.get("null"));
    }

    @Test
    public void putString() throws Exception {
        BinaryData binaryData = BinaryData.fromString("{\"field\": \"goodrequest\", \"empty\": \"\", \"null\": null}");
        asyncClient.putStringWithResponse(binaryData, null).block();
    }
}
