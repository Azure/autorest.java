package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodycomplex.PolymorphicrecursiveClient;
import com.azure.androidtest.fixtures.bodycomplex.models.Fish;
import com.azure.androidtest.fixtures.bodycomplex.models.Salmon;
import com.azure.androidtest.fixtures.bodycomplex.models.Sawshark;
import com.azure.androidtest.fixtures.bodycomplex.models.Shark;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class BodyComplexPolymorphicRecursiveSyncClientTests {
    private static PolymorphicrecursiveClient client;

    @BeforeClass
    public static void setup() {
        client = new PolymorphicrecursiveClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getValid() {
        final Response<Fish> getResponse = client.getValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Fish result = getResponse.getValue();
        Salmon salmon = (Salmon) result;
        Shark sib1 = (Shark) (salmon.getSiblings().get(0));
        Salmon sib2 = (Salmon) (sib1.getSiblings().get(0));
        Shark sib3 = (Shark) (sib2.getSiblings().get(0));
        assertEquals(
                OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC),
                sib3.getBirthday());
    }

    @Test
    public void putValid() {
        Salmon body = new Salmon();
        body.setLength(1.0f);
        body.setLocation("alaska")
                .setIswild(true)
                .setSpecies("king")
                .setSiblings(new ArrayList<Fish>());

        Shark sib1 = new Shark();
        sib1.setLength(20.0f);
        sib1.setBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib1.setAge(6);
        sib1.setSpecies("predator");
        sib1.setSiblings(new ArrayList<Fish>());
        body.getSiblings().add(sib1);

        Sawshark sib2 = new Sawshark();
        sib2.setLength(10.0f);
        sib2.setBirthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib2.setAge(105);
        sib2.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib2.setSpecies("dangerous");
        sib2.setSiblings(new ArrayList<Fish>());
        body.getSiblings().add(sib2);

        Salmon sib11 = new Salmon();
        sib11.setLength(2);
        sib11.setIswild(true);
        sib11.setLocation("atlantic");
        sib11.setSpecies("coho");
        sib11.setSiblings(new ArrayList<Fish>());
        sib1.getSiblings().add(sib11);
        sib1.getSiblings().add(sib2);

        Shark sib111 = new Shark();
        sib111.setLength(20);
        sib111.setBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib111.setAge(6);
        sib111.setSpecies("predator");
        sib11.getSiblings().add(sib111);

        Sawshark sib112 = new Sawshark();
        sib112.setLength(10.0f);
        sib112.setBirthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib112.setAge(105);
        sib112.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib112.setSpecies("dangerous");
        sib11.getSiblings().add(sib112);

        final Response<Void> putResponse = client.putValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }
}
