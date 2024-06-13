package fixtures.bodycomplex;

import fixtures.bodycomplex.models.ArrayWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ArrayTests {
    private static AutoRestComplexTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void getValid() {
        ArrayWrapper result = client.getArrays().getValid();
        assertEquals(5, result.getArray().size());
        assertEquals("&S#$(*Y", result.getArray().get(3));
    }

    @Test
    public void putValid() {
        ArrayWrapper body = new ArrayWrapper();
        body.setArray(Arrays.asList("1, 2, 3, 4", "", null, "&S#$(*Y", "The quick brown fox jumps over the lazy dog"));
        client.getArrays().putValidWithResponseAsync(body).block();
    }

    @Test
    public void getEmpty() throws Exception {
        ArrayWrapper result = client.getArrays().getEmpty();
        assertEquals(0, result.getArray().size());
    }

    @Test
    public void putEmpty() {
        ArrayWrapper body = new ArrayWrapper();
        body.setArray(new ArrayList<>());
        client.getArrays().putEmptyWithResponseAsync(body).block();
    }

    @Test
    public void getNotProvided() {
        ArrayWrapper result = client.getArrays().getNotProvided();
        assertNull(result.getArray());
    }
}
