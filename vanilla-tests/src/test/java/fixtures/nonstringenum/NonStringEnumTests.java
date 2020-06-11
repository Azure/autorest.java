package fixtures.nonstringenum;

import fixtures.nonstringenum.models.FloatEnum;
import fixtures.nonstringenum.models.IntEnum;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NonStringEnumTests {
    private static NonStringEnumsClient client;

    @BeforeClass
    public static void setup() {
        client = new NonStringEnumsClientBuilder().buildClient();
    }

    @Test
    public void getInt() {
        IntEnum actual = client.getInts().get();
        assertEquals(IntEnum.FOUR_TWO_NINE, actual);
    }

    @Test
    public void putInt() {
        client.getInts().put(IntEnum.TWO_ZERO_ZERO);
    }

    @Test
    public void getFloat() {
        FloatEnum actual = client.getFloatOperations().get();
        assertEquals(FloatEnum.FOUR_TWO_NINE_ONE, actual);
    }

    @Test
    public void putFloat() {
        client.getFloatOperations().put(FloatEnum.TWO_ZERO_ZERO_FOUR);
    }
}
