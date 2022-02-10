package fixtures.bodystring;

import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.concurrent.CountDownLatch;

public class EnumOperationsTests {
    private static EnumClient client;
    private CountDownLatch lock = new CountDownLatch(1);

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
        String res = client.getReferencedConstantWithResponse(null).getValue().toString();
        JsonReader jsonReader = Json.createReader(new StringReader(res));
        JsonObject result = jsonReader.readObject();
        Assertions.assertFalse(result.containsKey("ColorConstant"));
    }

    @Test
    public void putReferencedConstant() throws Exception {
        JsonObject farm = Json.createObjectBuilder()
                .add("ColorConstant", "green-color")
                .build();
        client.putReferencedConstantWithResponse(BinaryData.fromString(farm.toString()), null);
    }
}
