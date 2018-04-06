package fixtures.bodycomplex;

import fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.Salmon;
import fixtures.bodycomplex.models.Sawshark;
import fixtures.bodycomplex.models.Shark;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;
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
        body.withLocation("alaska");
        body.withIswild(true);
        body.withSpecies("king");
        body.withLength(1.0);
        body.withSiblings(new ArrayList<Fish>());

        Shark sib1 = new Shark();
        sib1.withAge(6);
        sib1.withBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib1.withLength(20.0);
        sib1.withSpecies("predator");
        sib1.withSiblings(new ArrayList<Fish>());
        body.siblings().add(sib1);

        Sawshark sib2 = new Sawshark();
        sib2.withAge(105);
        sib2.withBirthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib2.withLength(10.0);
        sib2.withPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib2.withSpecies("dangerous");
        sib2.withSiblings(new ArrayList<Fish>());
        body.siblings().add(sib2);

        Salmon sib11 = new Salmon();
        sib11.withIswild(true);
        sib11.withLocation("atlantic");
        sib11.withSpecies("coho");
        sib11.withLength(2);
        sib11.withSiblings(new ArrayList<Fish>());
        sib1.siblings().add(sib11);
        sib1.siblings().add(sib2);

        Shark sib111 = new Shark();
        sib111.withAge(6);
        sib111.withBirthday(OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib111.withSpecies("predator");
        sib111.withLength(20);
        sib11.siblings().add(sib111);

        Sawshark sib112 = new Sawshark();
        sib112.withAge(105);
        sib112.withBirthday(OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib112.withLength(10.0);
        sib112.withPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib112.withSpecies("dangerous");
        sib11.siblings().add(sib112);

        client.polymorphicrecursives().putValid(body);
    }
}
