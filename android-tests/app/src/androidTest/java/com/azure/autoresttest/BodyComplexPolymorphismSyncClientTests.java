package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.bodycomplex.PolymorphismClient;
import com.azure.androidtest.fixtures.bodycomplex.models.Fish;
import com.azure.androidtest.fixtures.bodycomplex.models.GoblinSharkColor;
import com.azure.androidtest.fixtures.bodycomplex.models.Goblinshark;
import com.azure.androidtest.fixtures.bodycomplex.models.Salmon;
import com.azure.androidtest.fixtures.bodycomplex.models.Sawshark;
import com.azure.androidtest.fixtures.bodycomplex.models.Shark;
import com.azure.androidtest.fixtures.bodycomplex.models.SmartSalmon;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class BodyComplexPolymorphismSyncClientTests {
    private static PolymorphismClient client;

    @BeforeClass
    public static void setup() {
        client = new PolymorphismClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getValid() {
        final Response<Fish> getResponse = client.getValidWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Fish result = getResponse.getValue();
        assertEquals(Salmon.class, result.getClass());
        Salmon salmon = (Salmon) result;
        assertEquals("alaska", salmon.getLocation());
        assertEquals(1.0, salmon.getLength(), 0f);
        assertEquals(3, salmon.getSiblings().size());
        assertEquals(Shark.class, salmon.getSiblings().get(0).getClass());
        Shark sib1 = (Shark) (salmon.getSiblings().get(0));
        assertEquals(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC), sib1.getBirthday());
        assertEquals(Sawshark.class, salmon.getSiblings().get(1).getClass());
        Sawshark sib2 = (Sawshark) (salmon.getSiblings().get(1));
        Assert.assertArrayEquals(
                new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254},
                sib2.getPicture());
        Goblinshark sib3 = (Goblinshark) (salmon.getSiblings().get(2));
        assertEquals(1, sib3.getAge().intValue());
        assertEquals(5, sib3.getJawsize().intValue());
    }

    @Test
    public void putValid() {
        Salmon body = new Salmon();
        body.setLength(1.0f);
        body.setLocation("alaska");
        body.setIswild(true);
        body.setSpecies("king");
        body.setSiblings(new ArrayList<>());

        Shark sib1 = new Shark();
        sib1.setLength(20.0f);
        sib1.setBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib1.setAge(6);
        sib1.setSpecies("predator");
        body.getSiblings().add(sib1);

        Sawshark sib2 = new Sawshark();
        sib2.setLength(10.0f);
        sib2.setBirthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib2.setAge(105);
        sib2.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib2.setSpecies("dangerous");
        body.getSiblings().add(sib2);

        Goblinshark sib3 = new Goblinshark();
        sib3.setLength(30.0f);
        sib3.setBirthday(OffsetDateTime.of(2015, 8, 8, 0, 0, 0, 0, ZoneOffset.UTC));
        sib3.setAge(1);
        sib3.setSpecies("scary");
        sib3.setJawsize(5);
        sib3.setColor(GoblinSharkColor.fromString("pinkish-gray"));
        body.getSiblings().add(sib3);

        final Response<Void> putResponse = client.putValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }

    @Ignore("Should have failed during serialization but hit server. Fix serialization")
    public void putValidMissingRequired() {
        try {
            Salmon body = new Salmon();
            body.setLength(1.0f);
            body.setLocation("alaska");
            body.setIswild(true);
            body.setSpecies("king");
            body.setSiblings(new ArrayList<>());

            Shark sib1 = new Shark();
            sib1.setLength(20.0f);
            sib1.setBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
            sib1.setAge(6);
            sib1.setSpecies("predator");
            body.getSiblings().add(sib1);

            Sawshark sib2 = new Sawshark();
            sib2.setLength(10.0f);
            sib2.setBirthday(null);
            sib2.setAge(105);
            sib2.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
            sib2.setSpecies("dangerous");
            body.getSiblings().add(sib2);

            final Response<Void> putResponse = client.putValidMissingRequiredWithRestResponse(body);
            fail();
        } catch (Exception ex) {
            //expected
            assertTrue(ex.getMessage().contains("Missing required property birthday in model Shark"));
        }
    }


    @Test
    public void getComplicated() {
        final Response<Salmon> getResponse = client.getComplicatedWithRestResponse();
        assertEquals(200, getResponse.getStatusCode());
        Salmon result = getResponse.getValue();
        assertEquals(SmartSalmon.class, result.getClass());
        SmartSalmon salmon = (SmartSalmon) result;
        assertEquals("alaska", salmon.getLocation());
        assertEquals(1.0, salmon.getLength(), 0f);
        assertEquals(3, salmon.getSiblings().size());
        assertEquals(Shark.class, salmon.getSiblings().get(0).getClass());
        Shark sib1 = (Shark) (salmon.getSiblings().get(0));
        assertEquals(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC), sib1.getBirthday());
        assertEquals(Sawshark.class, salmon.getSiblings().get(1).getClass());
        Sawshark sib2 = (Sawshark) (salmon.getSiblings().get(1));
        Assert.assertArrayEquals(
                new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254},
                sib2.getPicture());
        Goblinshark sib3 = (Goblinshark) (salmon.getSiblings().get(2));
        assertEquals(1, sib3.getAge().intValue());
        assertEquals(5, sib3.getJawsize().intValue());
        assertEquals(5, salmon.getAdditionalProperties().size());
        assertEquals(1, salmon.getAdditionalProperties().get("additionalProperty1"));
        assertEquals(false, salmon.getAdditionalProperties().get("additionalProperty2"));
        assertEquals("hello", salmon.getAdditionalProperties().get("additionalProperty3"));
        Object ad4 = salmon.getAdditionalProperties().get("additionalProperty4");
        assertTrue(Map.class.isAssignableFrom(ad4.getClass()));
        Map<String, Object> additionalProperty4 = (Map<String, Object>)ad4;
        assertEquals(2, additionalProperty4.size());
        assertEquals(1, additionalProperty4.get("a"));
        assertEquals(2, additionalProperty4.get("b"));
        Object ad5 = salmon.getAdditionalProperties().get("additionalProperty5");
        assertTrue(List.class.isAssignableFrom(ad5.getClass()));
        List<Integer> additionalProperty5 = (List<Integer>)ad5;
        assertEquals(2, additionalProperty5.size());
        assertEquals(1, additionalProperty5.get(0).intValue());
        assertEquals(3, additionalProperty5.get(1).intValue());
    }

    @Ignore("Serialization rejected by server. Fix serialization")
    public void putComplicated() {
        SmartSalmon body = new SmartSalmon();
        body.setLength(1.0f);
        body.setLocation("alaska");
        body.setIswild(true);
        body.setSpecies("king");
        body.setSiblings(new ArrayList<>());
        body.setAdditionalProperties(new HashMap<>());
        body.getAdditionalProperties().put("additionalProperty1", 1);
        body.getAdditionalProperties().put("additionalProperty2", false);
        body.getAdditionalProperties().put("additionalProperty3", "hello");
        Map<String, Integer> additionalProperty4 = new HashMap<>();
        additionalProperty4.put("a", 1);
        additionalProperty4.put("b", 2);
        body.getAdditionalProperties().put("additionalProperty4", additionalProperty4);
        body.getAdditionalProperties().put("additionalProperty5", Arrays.asList(1, 3));

        Shark sib1 = new Shark();
        sib1.setLength(20.0f);
        sib1.setBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib1.setAge(6);
        sib1.setSpecies("predator");
        body.getSiblings().add(sib1);

        Sawshark sib2 = new Sawshark();
        sib2.setLength(10.0f);
        sib2.setBirthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib2.setAge(105);
        sib2.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib2.setSpecies("dangerous");
        body.getSiblings().add(sib2);

        Goblinshark sib3 = new Goblinshark();
        sib3.setLength(30.0f);
        sib3.setBirthday(OffsetDateTime.of(2015, 8, 8, 0, 0, 0, 0, ZoneOffset.UTC));
        sib3.setAge(1);
        sib3.setSpecies("scary");
        sib3.setJawsize(5);
        sib3.setColor(GoblinSharkColor.fromString("pinkish-gray"));
        body.getSiblings().add(sib3);

        final Response<Void> putResponse = client.putValidWithRestResponse(body);
        assertEquals(200, putResponse.getStatusCode());
    }
}
