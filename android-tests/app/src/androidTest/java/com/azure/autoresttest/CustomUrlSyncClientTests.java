package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.custombaseurl.AutoRestParameterizedHostTestClient;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CustomUrlSyncClientTests {

    @Test
    public void getEmptyWithValidCustomUri() {
        AutoRestParameterizedHostTestClient.Builder builder = new AutoRestParameterizedHostTestClient.Builder();
        // Read this as "eastus" ("100.64.") and "contoso.azure.net" ("244.219:3000")
        AutoRestParameterizedHostTestClient client = builder.accountName("100.64.").host("202.121:3000").build();
        final Response<Void> getResponse = client.getEmptyWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getEmptyWithInvalidCustomUriAccountName() {
        try {
            AutoRestParameterizedHostTestClient.Builder builder = new AutoRestParameterizedHostTestClient.Builder();
            builder.host("198.155:3000").build().getEmptyWithRestResponse();
            Assert.fail();
        }
        catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    public void getEmptyWithInvalidCustomUriHost() {
        try {
            AutoRestParameterizedHostTestClient.Builder builder = new AutoRestParameterizedHostTestClient.Builder();
            builder.accountName("100.64.").build().getEmptyWithRestResponse();
            Assert.fail();
        }
        catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }
}
