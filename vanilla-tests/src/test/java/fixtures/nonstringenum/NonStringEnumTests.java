package fixtures.nonstringenum;

import fixtures.nonstringenum.implementation.NonStringEnumsClientImpl;
import fixtures.nonstringenum.models.FloatEnum;
import fixtures.nonstringenum.models.IntEnum;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NonStringEnumTests {
    private static IntClient intClient;
    private static FloatOperationClient floatClient;

    @BeforeClass
    public static void setup() {
        NonStringEnumsClientImpl implClient = new NonStringEnumsClientImpl("http://localhost:3000");
        intClient = new IntClient(implClient.getInts());
        floatClient = new FloatOperationClient(implClient.getFloatOperations());
    }

    @Test
    public void getInt() {
        IntEnum actual = intClient.get();
        assertEquals(IntEnum.FOUR_HUNDRED_TWENTY_NINE, actual);
    }

    @Test
    public void putInt() {
        intClient.put(IntEnum.TWO_HUNDRED);
    }

    @Test
    public void getFloat() {
        FloatEnum actual = floatClient.get();
        assertEquals(FloatEnum.FOUR_HUNDRED_TWENTY_NINE1, actual);
    }

    @Test
    public void putFloat() {
        floatClient.put(FloatEnum.TWO_HUNDRED4);
    }
}
