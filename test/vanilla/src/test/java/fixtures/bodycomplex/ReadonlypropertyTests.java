package fixtures.bodycomplex;

import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.PortPolicy;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
import fixtures.bodycomplex.models.ReadonlyObj;

public class ReadonlypropertyTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceImpl(HttpPipeline.build(new PortPolicy.Factory(3000)));
    }

    @Test
    public void putReadOnlyPropertyValid() throws Exception {
        ReadonlyObj o = client.readonlypropertys().getValid();
        client.readonlypropertys().putValid(o);
    }
}
