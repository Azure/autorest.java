package fixtures.bodycomplex;

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
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void getValid() throws Exception {
        Fish result = client.getPolymorphicrecursives().getValid();
        Salmon salmon = (Salmon) result;
        Shark sib1 = (Shark) (salmon.getSiblings().get(0));
        Salmon sib2 = (Salmon) (sib1.getSiblings().get(0));
        Shark sib3 = (Shark) (sib2.getSiblings().get(0));
        Assert.assertEquals(
                OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC),
                sib3.getBirthday());
    }

    @Test
    public void putValid() throws Exception {
        Salmon body = new Salmon(1.0f);
        body.setLocation("alaska");
        body.setIswild(true);
        body.setSpecies("king");
        body.setSiblings(new ArrayList<Fish>());

        Shark sib1 = new Shark(20.0f, OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib1.setAge(6);
        sib1.setSpecies("predator");
        sib1.setSiblings(new ArrayList<Fish>());
        body.getSiblings().add(sib1);

        Sawshark sib2 = new Sawshark(10.0f, OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib2.setAge(105);
        sib2.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib2.setSpecies("dangerous");
        sib2.setSiblings(new ArrayList<Fish>());
        body.getSiblings().add(sib2);

        Salmon sib11 = new Salmon(2);
        sib11.setIswild(true);
        sib11.setLocation("atlantic");
        sib11.setSpecies("coho");
        sib11.setSiblings(new ArrayList<Fish>());
        sib1.getSiblings().add(sib11);
        sib1.getSiblings().add(sib2);

        Shark sib111 = new Shark(20, OffsetDateTime.of(2012, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib111.setAge(6);
        sib111.setSpecies("predator");
        sib11.getSiblings().add(sib111);

        Sawshark sib112 = new Sawshark(10.0f, OffsetDateTime.of(1900, 1, 5, 1, 0, 0, 0, ZoneOffset.UTC));
        sib112.setAge(105);
        sib112.setPicture(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        sib112.setSpecies("dangerous");
        sib11.getSiblings().add(sib112);

        client.getPolymorphicrecursives().putValidWithResponseAsync(body).block();
    }
}
