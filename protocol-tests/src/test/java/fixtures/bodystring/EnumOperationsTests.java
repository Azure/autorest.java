package fixtures.bodystring;

import com.azure.core.util.BinaryData;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.concurrent.CountDownLatch;

public class EnumOperationsTests {
    private static EnumClient client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATServiceBuilder().buildEnumClient();
    }

    @Test
    public void getNotExpandable() throws Exception {
        String result = client.getNotExpandable(null);
        Assert.assertEquals("red color", result);
    }

    @Test
    public void putNotExpandable() throws Exception {
        client.putNotExpandable(BinaryData.fromString("red color"), null);
    }

    @Test
    public void getReferenced() throws Exception {
        String actual = client.getReferenced(null);
        Assert.assertEquals("red color", actual);
    }

    @Test
    public void putReferenced() throws Exception {
        client.putReferenced(BinaryData.fromString("red color"), null);
    }

    @Test
    public void getReferencedConstant() throws Exception {
        String res = client.getReferencedConstant(null).toString();
        JsonReader jsonReader = Json.createReader(new StringReader(res));
        JsonObject result = jsonReader.readObject();
        Assert.assertTrue(result.containsKey("ColorConstant"));
        Assert.assertEquals("green-color", result.getString("ColorConstant"));
    }

    @Test
    public void putReferencedConstant() throws Exception {
        JsonObject farm = Json.createObjectBuilder()
                .add("ColorConstant", "green-color")
                .build();
        client.putReferencedConstant(BinaryData.fromString(farm.toString()), null);
    }
}
