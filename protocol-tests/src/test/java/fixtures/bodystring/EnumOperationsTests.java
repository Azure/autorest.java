package fixtures.bodystring;

import com.azure.core.util.BinaryData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EnumOperationsTests {
    private static EnumClient client;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @BeforeAll
    public static void setup() {
        client = new EnumClientBuilder().buildClient();
    }

    @Test
    public void getNotExpandable() throws Exception {
        String result = client.getNotExpandableWithResponse(null).getValue();
        Assertions.assertEquals("red color", result);
    }

    @Test
    public void putNotExpandable() throws Exception {
        client.putNotExpandableWithResponse(BinaryData.fromObject("red color"), null);
    }

    @Test
    public void getReferenced() throws Exception {
        String actual = client.getReferencedWithResponse(null).getValue();
        Assertions.assertEquals("red color", actual);
    }

    @Test
    public void putReferenced() throws Exception {
        client.putReferencedWithResponse(BinaryData.fromObject("red color"), null);
    }

    @Test
    public void getReferencedConstant() throws Exception {
        BinaryData res = client.getReferencedConstantWithResponse(null).getValue();
        JsonNode jsonNode = OBJECT_MAPPER.readTree(res.toBytes());
        Assertions.assertFalse(jsonNode.has("ColorConstant"));
    }

    @Test
    public void putReferencedConstant() throws Exception {
        ObjectNode jsonNode = OBJECT_MAPPER.createObjectNode();
        jsonNode.put("ColorConstant", "green-color");
        client.putReferencedConstantWithResponse(BinaryData.fromObject(jsonNode), null);
    }
}
