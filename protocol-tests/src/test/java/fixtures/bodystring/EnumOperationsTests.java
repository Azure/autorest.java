package fixtures.bodystring;

import com.azure.core.util.BinaryData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EnumOperationsTests {
    private static EnumClient client;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @BeforeAll
    public static void setup() {
        client = new EnumClientBuilder().buildClient();
    }

    @Test
    public void getNotExpandable() {
        String result = client.getNotExpandableWithResponse(null).getValue().toObject(String.class);
        Assertions.assertEquals("red color", result);
    }

    @Test
    public void putNotExpandable() {
        client.putNotExpandableWithResponse(BinaryData.fromObject("red color"), null);
    }

    @Test
    public void getReferenced() {
        String actual = client.getReferencedWithResponse(null).getValue().toObject(String.class);
        Assertions.assertEquals("red color", actual);
    }

    @Test
    public void putReferenced() {
        client.putReferencedWithResponse(BinaryData.fromObject("red color"), null);
    }

    @Test
    public void getReferencedConstant() throws IOException {
        BinaryData res = client.getReferencedConstantWithResponse(null).getValue();
        JsonNode jsonNode = OBJECT_MAPPER.readTree(res.toBytes());
        // ColorConstant=green-color is constant in RefColorConstant, service does not respond with it in JSON
        Assertions.assertTrue(jsonNode.has("field1"));
    }

    @Test
    public void putReferencedConstant() {
        ObjectNode jsonNode = OBJECT_MAPPER.createObjectNode();
        jsonNode.put("ColorConstant", "green-color");
        client.putReferencedConstantWithResponse(BinaryData.fromObject(jsonNode), null);
    }
}
