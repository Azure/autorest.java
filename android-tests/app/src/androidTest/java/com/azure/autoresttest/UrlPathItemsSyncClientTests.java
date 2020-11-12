package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.url.PathItemsClient;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UrlPathItemsSyncClientTests {

    private static PathItemsClient client;

    @Test
    public void getAllWithValues() {
        client = new PathItemsClient.Builder()
                .host(TestConstants.TestServerRootUrl)
                .globalStringPath("globalStringPath")
                .globalStringQuery("globalStringQuery")
                .build();
        Response<Void> getResponse = client.getAllWithValuesWithRestResponse("pathItemStringPath",
                "localStringPath",
                "pathItemStringQuery",
                "localStringQuery" );
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getGlobalQueryNull() {
        client = new PathItemsClient.Builder()
                .host(TestConstants.TestServerRootUrl)
                .globalStringPath("globalStringPath")
                .globalStringQuery(null)
                .build();
        Response<Void> getResponse = client.getGlobalQueryNullWithRestResponse("pathItemStringPath",
                "localStringPath",
                "pathItemStringQuery",
                "localStringQuery" );
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getGlobalAndLocalQueryNull() {
        client = new PathItemsClient.Builder()
                .host(TestConstants.TestServerRootUrl)
                .globalStringPath("globalStringPath")
                .globalStringQuery(null)
                .build();
        Response<Void> getResponse = client.getGlobalAndLocalQueryNullWithRestResponse("pathItemStringPath",
                "localStringPath",
                "pathItemStringQuery",
                null );
        assertEquals(200, getResponse.getStatusCode());
    }

    @Test
    public void getLocalPathItemQueryNull() {
        client = new PathItemsClient.Builder()
                .host(TestConstants.TestServerRootUrl)
                .globalStringPath("globalStringPath")
                .globalStringQuery("globalStringQuery")
                .build();
        Response<Void> getResponse = client.getLocalPathItemQueryNullWithRestResponse("pathItemStringPath",
                "localStringPath",
                null,
                null );
        assertEquals(200, getResponse.getStatusCode());
    }
}
