package fixtures.bodycomplex;

import fixtures.bodycomplex.models.DictionaryWrapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DictionaryTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().build();
    }

    @Test
    public void getValid() throws Exception {
        DictionaryWrapper result = client.dictionarys().getValid();
        Assert.assertEquals(5, result.getDefaultProgram().size());
        Assert.assertEquals("", result.getDefaultProgram().get("exe"));
        Assert.assertNull(result.getDefaultProgram().get(""));
    }

    @Ignore("Jackson doesn't serialize null valued map entries")
    public void putValid() throws Exception {
        DictionaryWrapper body = new DictionaryWrapper();
        Map<String, String> programs = new HashMap<String, String>();
        programs.put("txt", "notepad");
        programs.put("bmp", "mspaint");
        programs.put("xls", "excel");
        programs.put("exe", "");
        programs.put("", null);
        body.setDefaultProgram(programs);
        client.dictionarys().putValidWithResponseAsync(body).block();
    }

    @Test
    public void getEmpty() throws Exception {
        DictionaryWrapper result = client.dictionarys().getEmpty();
        Assert.assertEquals(0, result.getDefaultProgram().size());
    }

    @Test
    public void putEmpty() throws Exception {
        DictionaryWrapper body = new DictionaryWrapper();
        body.setDefaultProgram(new HashMap<String, String>());
        client.dictionarys().putEmptyWithResponseAsync(body).block();
    }

    @Test
    public void getNull() throws Exception {
        DictionaryWrapper result = client.dictionarys().getNull();
        Assert.assertNull(result.getDefaultProgram());
    }

    @Test
    public void getNotProvided() throws Exception {
        DictionaryWrapper result = client.dictionarys().getNotProvided();
        Assert.assertNull(result.getDefaultProgram());
    }
}