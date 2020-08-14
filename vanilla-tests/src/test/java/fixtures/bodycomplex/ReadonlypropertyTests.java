package fixtures.bodycomplex;

import fixtures.bodycomplex.models.ReadonlyObj;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReadonlypropertyTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void putReadOnlyPropertyValid() throws Exception {
        ReadonlyObj o = client.getReadonlyproperties().getValid();
        client.getReadonlyproperties().putValid(o);
    }
}
