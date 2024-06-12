package fixtures.bodystring;

import fixtures.bodystring.implementation.AutoRestSwaggerBATServiceImplBuilder;
import fixtures.bodystring.models.Colors;
import fixtures.bodystring.models.RefColorConstant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumOperationsTests {
    private static AutoRestSwaggerBATService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestSwaggerBATServiceImplBuilder().buildClient();
    }

    @Disabled("Bug with direct usage of enum values without Jackson annotation")
    @Test
    public void getNotExpandable() {
        Colors result = client.getEnums().getNotExpandable();
        assertEquals(Colors.RED_COLOR, result);
    }

    @Disabled("Bug with direct usage of enum values without Jackson annotation")
    @Test
    public void putNotExpandable() {
        client.getEnums().putNotExpandableWithResponseAsync(Colors.RED_COLOR).block();
    }

    @Disabled("Bug with direct usage of enum values without Jackson annotation")
    @Test
    public void getReferenced() {
        Colors actual = client.getEnums().getReferenced();
        assertEquals(Colors.RED_COLOR, actual);
    }

    @Disabled("Bug with direct usage of enum values without Jackson annotation")
    @Test
    public void putReferenced() {
        client.getEnums().putReferenced(Colors.RED_COLOR);
    }

    @Test
    public void getReferencedConstant() {
        RefColorConstant actual = client.getEnums().getReferencedConstant();
        assertEquals("green-color", actual.getColorConstant());
    }

    @Test
    public void putReferencedConstant() {
        client.getEnums().putReferencedConstant(new RefColorConstant());
    }
}
