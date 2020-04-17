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
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void getValid() throws Exception {
        DictionaryWrapper result = client.getDictionarys().getValid();
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
        client.getDictionarys().putValidWithResponseAsync(body).block();
    }

    @Test
    public void getEmpty() throws Exception {
        DictionaryWrapper result = client.getDictionarys().getEmpty();
        Assert.assertEquals(0, result.getDefaultProgram().size());
    }

    @Test
    public void putEmpty() throws Exception {
        DictionaryWrapper body = new DictionaryWrapper();
        body.setDefaultProgram(new HashMap<String, String>());
        client.getDictionarys().putEmptyWithResponseAsync(body).block();
    }

    @Test
    public void getNull() throws Exception {
        DictionaryWrapper result = client.getDictionarys().getNull();
        Assert.assertNull(result.getDefaultProgram());
    }

    @Test
    public void getNotProvided() throws Exception {
        DictionaryWrapper result = client.getDictionarys().getNotProvided();
        Assert.assertNull(result.getDefaultProgram());
    }
}
