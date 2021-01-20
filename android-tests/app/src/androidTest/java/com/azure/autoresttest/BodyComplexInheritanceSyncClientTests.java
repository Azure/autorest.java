package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodycomplex.InheritanceClient;
import com.azure.androidtest.fixtures.bodycomplex.models.Dog;
import com.azure.androidtest.fixtures.bodycomplex.models.Siamese;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BodyComplexInheritanceSyncClientTests {
    private static InheritanceClient client;

    @BeforeClass
    public static void setup() {
        client = new InheritanceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getValid() {
        final Response<Siamese> getResponse = client.getValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Siamese result = getResponse.getValue();
        assertEquals("persian", result.getBreed());
        assertEquals("green", result.getColor());
        assertEquals(2, result.getId().intValue());
        assertEquals("Siameeee", result.getName());
        assertEquals("french fries", result.getHates().get(1).getFood());
    }

    @Test
    public void putValid() {
        Siamese body = new Siamese();
        body.setBreed("persian");
        body.setColor("green");
        body.setId(2);
        body.setName("Siameeee");
        body.setHates(new ArrayList<Dog>());
        Dog dog1 = new Dog();
        dog1.setName("Potato");
        dog1.setId(1);
        dog1.setFood("tomato");
        body.getHates().add(dog1);
        Dog dog2 = new Dog();
        dog2.setFood("french fries");
        dog2.setId(-1);
        dog2.setName("Tomato");
        body.getHates().add(dog2);
        final Response<Void> putResponse = client.putValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }
}
