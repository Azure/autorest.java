package fixtures.bodycomplex;

import fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.Salmon;
import fixtures.bodycomplex.models.Sawshark;
import fixtures.bodycomplex.models.Shark;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class PolymorphismrecursiveTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceImpl();
    }

    @Test
    public void getValid() throws Exception {
        Fish result = client.polymorphicrecursives().getValid();
        Salmon salmon = (Salmon) result;
        Shark sib1 = (Shark) (salmon.siblings().get(0));
        Salmon sib2 = (Salmon) (sib1.siblings().get(0));
        Shark sib3 = (Shark) (sib2.siblings().get(0));
        Assert.assertEquals(
                OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC),
                sib3.birthday());
    }

    @Test
    public void putValid() throws Exception {
        Salmon body = new Salmon();
        body.location("alaska");
        body.iswild(true);
        body.species("king");
        body.length(1.0);
        body.siblings(new ArrayList<Fish>());

        Shark sib1 = new Shark();
        sib1.age(6);
        sib1.birthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib1.length(20.0);
        sib1.species("predator");
        sib1.siblings(new ArrayList<Fish>());
        body.siblings().add(sib1);

        Sawshark sib2 = new Sawshark();
        sib2.age(105);
        sib2.birthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib2.length(10.0);
        sib2.picture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib2.species("dangerous");
        sib2.siblings(new ArrayList<Fish>());
        body.siblings().add(sib2);

        Salmon sib11 = new Salmon();
        sib11.iswild(true);
        sib11.location("atlantic");
        sib11.species("coho");
        sib11.length(2);
        sib11.siblings(new ArrayList<Fish>());
        sib1.siblings().add(sib11);
        sib1.siblings().add(sib2);

        Shark sib111 = new Shark();
        sib111.age(6);
        sib111.birthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib111.species("predator");
        sib111.length(20);
        sib11.siblings().add(sib111);

        Sawshark sib112 = new Sawshark();
        sib112.age(105);
        sib112.birthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib112.length(10.0);
        sib112.picture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib112.species("dangerous");
        sib11.siblings().add(sib112);

        client.polymorphicrecursives().putValid(body);
    }
}
