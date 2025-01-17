package fixtures.nonstringenum;

import fixtures.nonstringenum.implementation.NonStringEnumsClientImpl;
import fixtures.nonstringenum.models.FloatEnum;
import fixtures.nonstringenum.models.IntEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NonStringEnumTests {
    private static IntClient intClient;
    private static FloatOperationClient floatClient;

    @BeforeAll
    public static void setup() {
        NonStringEnumsClientImpl implClient = new NonStringEnumsClientImpl("http://localhost:3000");
        intClient = new IntClient(implClient.getInts());
        floatClient = new FloatOperationClient(implClient.getFloatOperations());
    }

    @Disabled("Bug with direct usage of enum values without Jackson annotation")
    @Test
    public void getInt() {
        IntEnum actual = intClient.get();
        assertEquals(IntEnum.FOUR_HUNDRED_TWENTY_NINE, actual);
    }

    @Disabled("wire type changed for Swagger extensible non-string enum")
    @Test
    public void putInt() {
        intClient.put(IntEnum.TWO_HUNDRED);
    }

    @Disabled("Bug with direct usage of enum values without Jackson annotation")
    @Test
    public void getFloat() {
        FloatEnum actual = floatClient.get();
        assertEquals(FloatEnum.FOUR_HUNDRED_TWENTY_NINE1, actual);
    }

    @Disabled("wire type changed for Swagger extensible non-string enum")
    @Test
    public void putFloat() {
        floatClient.put(FloatEnum.TWO_HUNDRED4);
    }
}
