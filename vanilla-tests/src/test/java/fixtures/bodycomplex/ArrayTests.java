package fixtures.bodycomplex;

import fixtures.bodycomplex.models.ArrayWrapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void getValid() throws Exception {
        ArrayWrapper result = client.getArrays().getValid();
        Assert.assertEquals(5, result.getArray().size());
        Assert.assertEquals("&S#$(*Y", result.getArray().get(3));
    }

    @Test
    public void putValid() throws Exception {
        ArrayWrapper body = new ArrayWrapper();
        body.setArray(Arrays.asList("1, 2, 3, 4", "", null, "&S#$(*Y", "The quick brown fox jumps over the lazy dog"));
        client.getArrays().putValidWithResponseAsync(body).block();
    }

    @Test
    public void getEmpty() throws Exception {
        ArrayWrapper result = client.getArrays().getEmpty();
        Assert.assertEquals(0, result.getArray().size());
    }

    @Test
    public void putEmpty() throws Exception {
        ArrayWrapper body = new ArrayWrapper();
        body.setArray(new ArrayList<>());
        client.getArrays().putEmptyWithResponseAsync(body).block();
    }

    @Test
    public void getNotProvided() throws Exception {
        ArrayWrapper result = client.getArrays().getNotProvided();
        Assert.assertNull(result.getArray());
    }
}
