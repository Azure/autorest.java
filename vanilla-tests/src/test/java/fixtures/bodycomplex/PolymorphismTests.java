package fixtures.bodycomplex;

import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.GoblinSharkColor;
import fixtures.bodycomplex.models.Goblinshark;
import fixtures.bodycomplex.models.Salmon;
import fixtures.bodycomplex.models.Sawshark;
import fixtures.bodycomplex.models.Shark;
import fixtures.bodycomplex.models.SmartSalmon;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolymorphismTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void getValid() {
        Fish result = client.polymorphisms().getValid();
        Assert.assertEquals(Salmon.class, result.getClass());
        Salmon salmon = (Salmon) result;
        Assert.assertEquals("alaska", salmon.getLocation());
        Assert.assertEquals(1.0, salmon.getLength(), 0f);
        Assert.assertEquals(3, salmon.getSiblings().size());
        Assert.assertEquals(Shark.class, salmon.getSiblings().get(0).getClass());
        Shark sib1 = (Shark) (salmon.getSiblings().get(0));
        Assert.assertEquals(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC), sib1.getBirthday());
        Assert.assertEquals(Sawshark.class, salmon.getSiblings().get(1).getClass());
        Sawshark sib2 = (Sawshark) (salmon.getSiblings().get(1));
        Assert.assertArrayEquals(
                new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254},
                sib2.getPicture());
        Goblinshark sib3 = (Goblinshark) (salmon.getSiblings().get(2));
        Assert.assertEquals(1, sib3.getAge().intValue());
        Assert.assertEquals(5, sib3.getJawsize().intValue());
    }

    @Test
    public void putValid() {
        Salmon body = new Salmon();
        body.setLocation("alaska");
        body.setIswild(true);
        body.setSpecies("king");
        body.setLength(1.0f);
        body.setSiblings(new ArrayList<>());

        Shark sib1 = new Shark();
        sib1.setAge(6);
        sib1.setBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib1.setLength(20.0f);
        sib1.setSpecies("predator");
        body.getSiblings().add(sib1);

        Sawshark sib2 = new Sawshark();
        sib2.setAge(105);
        sib2.setBirthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib2.setLength(10.0f);
        sib2.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib2.setSpecies("dangerous");
        body.getSiblings().add(sib2);

        Goblinshark sib3 = new Goblinshark();
        sib3.setAge(1);
        sib3.setBirthday(OffsetDateTime.of(2015, 8, 8, 0, 0, 0, 0, ZoneOffset.UTC));
        sib3.setLength(30.0f);
        sib3.setSpecies("scary");
        sib3.setJawsize(5);
        sib3.setColor(GoblinSharkColor.fromString("pinkish-gray"));
        body.getSiblings().add(sib3);

        client.polymorphisms().putValidWithResponseAsync(body).block();
    }

    @Test
    public void putValidMissingRequired() {
        try {
            Salmon body = new Salmon();
            body.setLocation("alaska");
            body.setIswild(true);
            body.setSpecies("king");
            body.setLength(1.0f);
            body.setSiblings(new ArrayList<>());

            Shark sib1 = new Shark();
            sib1.setAge(6);
            sib1.setBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
            sib1.setLength(20.0f);
            sib1.setSpecies("predator");
            body.getSiblings().add(sib1);

            Sawshark sib2 = new Sawshark();
            sib2.setAge(105);
            sib2.setLength(10.0f);
            sib2.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
            sib2.setSpecies("dangerous");
            body.getSiblings().add(sib2);

            client.polymorphisms().putValidMissingRequiredWithResponseAsync(body).block();
        } catch (IllegalArgumentException ex) {
            //expected
            Assert.assertTrue(ex.getMessage().contains("Missing required property birthday in model Shark"));
        }
    }


    @Test
    public void getComplicated() {
        Salmon result = client.polymorphisms().getComplicated();
        Assert.assertEquals(SmartSalmon.class, result.getClass());
        SmartSalmon salmon = (SmartSalmon) result;
        Assert.assertEquals("alaska", salmon.getLocation());
        Assert.assertEquals(1.0, salmon.getLength(), 0f);
        Assert.assertEquals(3, salmon.getSiblings().size());
        Assert.assertEquals(Shark.class, salmon.getSiblings().get(0).getClass());
        Shark sib1 = (Shark) (salmon.getSiblings().get(0));
        Assert.assertEquals(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC), sib1.getBirthday());
        Assert.assertEquals(Sawshark.class, salmon.getSiblings().get(1).getClass());
        Sawshark sib2 = (Sawshark) (salmon.getSiblings().get(1));
        Assert.assertArrayEquals(
                new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254},
                sib2.getPicture());
        Goblinshark sib3 = (Goblinshark) (salmon.getSiblings().get(2));
        Assert.assertEquals(1, sib3.getAge().intValue());
        Assert.assertEquals(5, sib3.getJawsize().intValue());
        Assert.assertEquals(5, salmon.getAdditionalProperties().size());
        Assert.assertEquals(1, salmon.getAdditionalProperties().get("additionalProperty1"));
        Assert.assertEquals(false, salmon.getAdditionalProperties().get("additionalProperty2"));
        Assert.assertEquals("hello", salmon.getAdditionalProperties().get("additionalProperty3"));
        Object ad4 = salmon.getAdditionalProperties().get("additionalProperty4");
        Assert.assertTrue(Map.class.isAssignableFrom(ad4.getClass()));
        Map<String, Object> additionalProperty4 = (Map<String, Object>)ad4;
        Assert.assertEquals(2, additionalProperty4.size());
        Assert.assertEquals(1, additionalProperty4.get("a"));
        Assert.assertEquals(2, additionalProperty4.get("b"));
        Object ad5 = salmon.getAdditionalProperties().get("additionalProperty5");
        Assert.assertTrue(List.class.isAssignableFrom(ad5.getClass()));
        List<Integer> additionalProperty5 = (List<Integer>)ad5;
        Assert.assertEquals(2, additionalProperty5.size());
        Assert.assertEquals(1, additionalProperty5.get(0).intValue());
        Assert.assertEquals(3, additionalProperty5.get(1).intValue());
    }

    @Test
    public void putComplicated() {
        SmartSalmon body = new SmartSalmon();
        body.setLocation("alaska");
        body.setIswild(true);
        body.setSpecies("king");
        body.setLength(1.0f);
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
        sib1.setAge(6);
        sib1.setBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib1.setLength(20.0f);
        sib1.setSpecies("predator");
        body.getSiblings().add(sib1);

        Sawshark sib2 = new Sawshark();
        sib2.setAge(105);
        sib2.setBirthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib2.setLength(10.0f);
        sib2.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib2.setSpecies("dangerous");
        body.getSiblings().add(sib2);

        Goblinshark sib3 = new Goblinshark();
        sib3.setAge(1);
        sib3.setBirthday(OffsetDateTime.of(2015, 8, 8, 0, 0, 0, 0, ZoneOffset.UTC));
        sib3.setLength(30.0f);
        sib3.setSpecies("scary");
        sib3.setJawsize(5);
        sib3.setColor(GoblinSharkColor.fromString("pinkish-gray"));
        body.getSiblings().add(sib3);

        client.polymorphisms().putComplicated(body);
    }
}
