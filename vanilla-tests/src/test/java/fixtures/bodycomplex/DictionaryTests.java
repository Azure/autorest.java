package fixtures.bodycomplex;

import fixtures.bodycomplex.models.DictionaryWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DictionaryTests {
    private static AutoRestComplexTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void getValid() {
        DictionaryWrapper result = client.getDictionaries().getValid();
        assertEquals(5, result.getDefaultProgram().size());
        assertEquals("", result.getDefaultProgram().get("exe"));
        assertNull(result.getDefaultProgram().get(""));
    }

    @Disabled("Jackson doesn't serialize null valued map entries")
    @Test
    public void putValid() {
        DictionaryWrapper body = new DictionaryWrapper();
        Map<String, String> programs = new HashMap<>();
        programs.put("txt", "notepad");
        programs.put("bmp", "mspaint");
        programs.put("xls", "excel");
        programs.put("exe", "");
        programs.put("", null);
        body.setDefaultProgram(programs);
        client.getDictionaries().putValidWithResponseAsync(body).block();
    }

    @Test
    public void getEmpty() throws Exception {
        DictionaryWrapper result = client.getDictionaries().getEmpty();
        assertEquals(0, result.getDefaultProgram().size());
    }

    @Test
    public void putEmpty() {
        DictionaryWrapper body = new DictionaryWrapper();
        body.setDefaultProgram(new HashMap<>());
        client.getDictionaries().putEmptyWithResponseAsync(body).block();
    }

    @Test
    public void getNull() throws Exception {
        DictionaryWrapper result = client.getDictionaries().getNull();
        assertNull(result.getDefaultProgram());
    }

    @Test
    public void getNotProvided() {
        DictionaryWrapper result = client.getDictionaries().getNotProvided();
        assertNull(result.getDefaultProgram());
    }
}
