package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodystring.EnumClient;
import com.azure.androidtest.fixtures.bodystring.models.Colors;
import com.azure.androidtest.fixtures.bodystring.models.RefColorConstant;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BodyStringEnumSyncClientTests {
    private static EnumClient client;

    @BeforeClass
    public static void setup() {
        client = new EnumClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNotExpandable() throws Exception {
        Colors result = client.getNotExpandableWithRestResponse().getValue();
        assertEquals(Colors.RED_COLOR, result);
    }

    @Test
    public void putNotExpandable() {
        Response<Void> putResponse = client.putNotExpandableWithRestResponse(Colors.RED_COLOR);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getReferenced() throws Exception {
        Colors actual = client.getReferencedWithRestResponse().getValue();
        assertEquals(Colors.RED_COLOR, actual);
    }

    @Test
    public void putReferenced() throws Exception {
        Response<Void> putResponse = client.putReferencedWithRestResponse(Colors.RED_COLOR);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Test
    public void getReferencedConstant() throws Exception {
        RefColorConstant actual = client.getReferencedConstantWithRestResponse().getValue();
        assertEquals("green-color", actual.getColorConstant());
    }

    @Test
    public void putReferencedConstant() throws Exception {
        Response<Void> putResponse = client.putReferencedConstantWithRestResponse("name");
        assertEquals(200, putResponse.getStatusCode());
    }
}
