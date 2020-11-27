package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodycomplex.DictionaryClient;
import com.azure.androidtest.fixtures.bodycomplex.models.DictionaryWrapper;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class BodyComplexDictionarySyncClientTests {
    private static DictionaryClient client;

    @BeforeClass
    public static void setup() {
        client = new DictionaryClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getValid() {
        final Response<DictionaryWrapper> getResponse = client.getValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DictionaryWrapper result = getResponse.getValue();
        assertEquals(5, result.getDefaultProgram().size());
        assertEquals("", result.getDefaultProgram().get("exe"));
        assertNull(result.getDefaultProgram().get(""));
    }

    @Ignore("Jackson doesn't serialize null valued map entries")
    public void putValid() {
        Map<String, String> programs = new HashMap<String, String>();
        programs.put("txt", "notepad");
        programs.put("bmp", "mspaint");
        programs.put("xls", "excel");
        programs.put("exe", "");
        programs.put("", null);
        final Response<Void> putResponse = client.putValidWithRestResponse(programs);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getEmpty() {
        final Response<DictionaryWrapper> getResponse = client.getEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DictionaryWrapper result = getResponse.getValue();
        assertEquals(0, result.getDefaultProgram().size());
    }

    @Test
    public void putEmpty() {
        final Response<Void> putResponse = client.putEmptyWithRestResponse(new HashMap<String, String>());
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getNull() {
        final Response<DictionaryWrapper> getResponse = client.getNullWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DictionaryWrapper result = getResponse.getValue();
        assertNull(result.getDefaultProgram());
    }

    @Test
    public void getNotProvided() {
        final Response<DictionaryWrapper> getResponse = client.getNotProvidedWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        DictionaryWrapper result = getResponse.getValue();
        assertNull(result.getDefaultProgram());
    }
}
