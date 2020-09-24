package fixtures.bodystring;

import fixtures.bodystring.implementation.AutoRestSwaggerBATServiceImplBuilder;
import fixtures.bodystring.models.Colors;
import fixtures.bodystring.models.RefColorConstant;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class EnumOperationsTests {
    private static AutoRestSwaggerBATService client;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setup() {
        client = new AutoRestSwaggerBATServiceImplBuilder().buildClient();
    }

    @Test
    public void getNotExpandable() throws Exception {
        Colors result = client.getEnums().getNotExpandable();
        Assert.assertEquals(Colors.RED_COLOR, result);
    }

    @Test
    public void putNotExpandable() throws Exception {
        client.getEnums().putNotExpandableWithResponseAsync(Colors.RED_COLOR).block();
    }

    @Test
    public void getReferenced() throws Exception {
        Colors actual = client.getEnums().getReferenced();
        Assert.assertEquals(Colors.RED_COLOR, actual);
    }

    @Test
    public void putReferenced() throws Exception {
        client.getEnums().putReferenced(Colors.RED_COLOR);
    }

    @Test
    public void getReferencedConstant() throws Exception {
        RefColorConstant actual = client.getEnums().getReferencedConstant();
        Assert.assertEquals("green-color", actual.getColorConstant());
    }

    @Test
    public void putReferencedConstant() throws Exception {
        client.getEnums().putReferencedConstant(new RefColorConstant());
    }
}
