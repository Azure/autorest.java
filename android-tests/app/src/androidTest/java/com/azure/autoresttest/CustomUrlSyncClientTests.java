package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.custombaseurl.AutoRestParameterizedHostTestClient;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CustomUrlSyncClientTests {
    private static AutoRestParameterizedHostTestClient.Builder builder;

    @BeforeClass
    public static void setup() {
        builder = new AutoRestParameterizedHostTestClient.Builder();
    }

    @Test
    public void getEmptyWithValidCustomUri() {
        // Read this as "eastus" ("100.64.") and "contoso.azure.net" ("244.219:3000") 100.64.198.155
        AutoRestParameterizedHostTestClient client = builder.accountName("100.64.").host("198.155:3000").build();
        final Response<Void> getResponse = client.getEmptyWithRestResponse("useless");
        assertEquals(200, getResponse.getStatusCode());
    }

}
