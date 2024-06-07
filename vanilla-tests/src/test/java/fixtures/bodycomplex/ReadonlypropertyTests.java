package fixtures.bodycomplex;

import fixtures.bodycomplex.models.ReadonlyObj;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ReadonlypropertyTests {
    private static AutoRestComplexTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void putReadOnlyPropertyValid() {
        ReadonlyObj o = client.getReadonlyproperties().getValid();
        client.getReadonlyproperties().putValid(o);
    }
}
