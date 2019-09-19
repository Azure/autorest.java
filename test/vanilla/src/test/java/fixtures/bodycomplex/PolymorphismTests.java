package fixtures.bodycomplex;

import fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.GoblinSharkColor;
import fixtures.bodycomplex.models.Goblinshark;
import fixtures.bodycomplex.models.Salmon;
import fixtures.bodycomplex.models.Sawshark;
import fixtures.bodycomplex.models.Shark;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class PolymorphismTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceImpl();
    }

    @Test
    public void getValid() {
        Fish result = client.polymorphisms().getValid();
        Assert.assertEquals(Salmon.class, result.getClass());
        Salmon salmon = (Salmon) result;
        Assert.assertEquals("alaska", salmon.location());
        Assert.assertEquals(1.0, salmon.length(), 0f);
        Assert.assertEquals(3, salmon.siblings().size());
        Assert.assertEquals(Shark.class, salmon.siblings().get(0).getClass());
        Shark sib1 = (Shark) (salmon.siblings().get(0));
        Assert.assertEquals(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC), sib1.birthday());
        Assert.assertEquals(Sawshark.class, salmon.siblings().get(1).getClass());
        Sawshark sib2 = (Sawshark) (salmon.siblings().get(1));
        Assert.assertArrayEquals(
                new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254},
                sib2.picture());
        Goblinshark sib3 = (Goblinshark) (salmon.siblings().get(2));
        Assert.assertEquals(1, sib3.age().longValue());
        Assert.assertEquals(5, sib3.jawsize().longValue());
    }

    @Test
    public void putValid() {
        Salmon body = new Salmon();
        body.location("alaska");
        body.iswild(true);
        body.species("king");
        body.length(1.0);
        body.siblings(new ArrayList<>());

        Shark sib1 = new Shark();
        sib1.age(6);
        sib1.birthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib1.length(20.0);
        sib1.species("predator");
        body.siblings().add(sib1);

        Sawshark sib2 = new Sawshark();
        sib2.age(105);
        sib2.birthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib2.length(10.0);
        sib2.picture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib2.species("dangerous");
        body.siblings().add(sib2);

        Goblinshark sib3 = new Goblinshark();
        sib3.age(1);
        sib3.birthday(OffsetDateTime.of(2015, 8, 8, 0, 0, 0, 0, ZoneOffset.UTC));
        sib3.length(30.0);
        sib3.species("scary");
        sib3.jawsize(5);
        sib3.color(GoblinSharkColor.fromString("pinkish-gray"));
        body.siblings().add(sib3);

        client.polymorphisms().putValid(body);
    }

    @Test
    public void putValidMissingRequired() {
        try {
            Salmon body = new Salmon();
            body.location("alaska");
            body.iswild(true);
            body.species("king");
            body.length(1.0);
            body.siblings(new ArrayList<>());

            Shark sib1 = new Shark();
            sib1.age(6);
            sib1.birthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
            sib1.length(20.0);
            sib1.species("predator");
            body.siblings().add(sib1);

            Sawshark sib2 = new Sawshark();
            sib2.age(105);
            sib2.length(10.0);
            sib2.picture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
            sib2.species("dangerous");
            body.siblings().add(sib2);

            client.polymorphisms().putValidMissingRequired(body);
        } catch (IllegalArgumentException ex) {
            //expected
            Assert.assertTrue(ex.getMessage().contains("siblings.birthday is required and cannot be null."));
        }
    }
}
