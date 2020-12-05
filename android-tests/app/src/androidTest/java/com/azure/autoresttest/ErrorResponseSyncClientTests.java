package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.androidtest.fixtures.errorresponse.XMSErrorResponseExtensionsClient;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ErrorResponseSyncClientTests {
    private static XMSErrorResponseExtensionsClient client;

    @BeforeClass
    public static void setup() {
        client = new XMSErrorResponseExtensionsClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getPetWithIdTest() {
        try {
            client.getPetByIdWithRestResponse("Dog");
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
            HttpResponseException httpException = (HttpResponseException) ex;
            assertEquals(402, httpException.getResponse().code());
            assertTrue(ex.getMessage().contains("That's all folks"));
        }
    }

    @Test
    public void getPetWithNullTest() {
        try {
            client.getPetByIdWithRestResponse(null);
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("value must not be null"));
        }
    }

    @Test
    public void getPetWithEmptyTest() {
        try {
            client.getPetByIdWithRestResponse("");
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
            HttpResponseException httpException = (HttpResponseException) ex;
            assertEquals(404, httpException.getResponse().code());
            assertTrue(ex.getMessage().contains("{\"status\":404}"));
        }
    }

    @Test
    public void doSomethingGoodTest() {
        try {
            client.doSomethingWithRestResponse("Laugh");
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
            HttpResponseException httpException = (HttpResponseException) ex;
            assertEquals(400, httpException.getResponse().code());
            assertTrue(ex.getMessage().contains("{\"message\":\"Action cannot be performed Laugh\",\"status\":400}"));
        }
    }

    @Test
    public void doSomethingEmptyTest() {
        try {
            client.doSomethingWithRestResponse("");
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
            HttpResponseException httpException = (HttpResponseException) ex;
            assertEquals(404, httpException.getResponse().code());
            assertTrue(ex.getMessage().contains("{\"status\":404}"));
        }
    }

    @Test
    public void doSomethingDumbTest() {
        try {
            client.doSomethingWithRestResponse(null);
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertTrue(ex.getMessage().contains("value must not be null"));
        }
    }

    @Test
    public void hasModelsWithExpectedArgumentTest() {
        try {
            client.hasModelsParamWithRestResponse("value1");
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
            HttpResponseException httpException = (HttpResponseException) ex;
            assertEquals(500, httpException.getResponse().code());
            assertTrue(ex.getMessage().contains("{\"actionResponse\":\"grrrr\",\"errorType\":\"PetSadError\",\"errorMessage\":\"casper aint happy\",\"reason\":\"need more treats\"}"));
        }
    }

    @Test
    public void hasModelsWithUnexpectedArgumentTest() {
        try {
            client.hasModelsParamWithRestResponse("Dog,Cat,Pet");
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
            HttpResponseException httpException = (HttpResponseException) ex;
            assertEquals(400, httpException.getResponse().code());
            assertTrue(ex.getMessage().contains("{\"message\":\"The value of input param models is Dog,Cat,Pet and not the client default value of 'value1'\",\"status\":400}"));
        }
    }

    @Test
    public void hasModelsWithEmptyTest() {
        try {
            client.hasModelsParamWithRestResponse("");
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
            HttpResponseException httpException = (HttpResponseException) ex;
            assertEquals(400, httpException.getResponse().code());
            assertTrue(ex.getMessage().contains(
                    "{\"message\":\"The value of input param models is  and not the client default value of 'value1'\",\"status\":400}"));
        }
    }

    @Test
    public void hasModelsWithNullTest() {
        try {
            client.hasModelsParamWithRestResponse(null);
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
            HttpResponseException httpException = (HttpResponseException) ex;
            assertEquals(400, httpException.getResponse().code());
            assertTrue(ex.getMessage().contains(
                    "{\"message\":\"The value of input param models is undefined and not the client default value of 'value1'\",\"status\":400}"));
        }
    }
}
