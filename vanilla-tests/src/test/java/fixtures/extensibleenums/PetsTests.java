package fixtures.extensibleenums;

import fixtures.extensibleenums.models.DaysOfWeekExtensibleEnum;
import fixtures.extensibleenums.models.IntEnum;
import fixtures.extensibleenums.models.Pet;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PetsTests {
    private static PetStoreInc client;

    @BeforeClass
    public static void setup() {
        client = new PetStoreIncBuilder().buildClient();
    }

    @Test
    public void getByPetId() throws Exception {
        Pet tommy = client.pets().getByPetId("tommy");
        Assert.assertNotNull(tommy);
        Assert.assertEquals(DaysOfWeekExtensibleEnum.MONDAY, tommy.getDaysOfWeek());
        Assert.assertEquals(IntEnum.ONE, tommy.getIntEnum());
        Assert.assertEquals("Tommy Tomson", tommy.getName());

        Pet casper = client.pets().getByPetId("casper");
        Assert.assertNotNull(casper);
        Assert.assertEquals(DaysOfWeekExtensibleEnum.fromString("Weekend"), casper.getDaysOfWeek());
        Assert.assertEquals(IntEnum.TWO, casper.getIntEnum());
        Assert.assertEquals("Casper Ghosty", casper.getName());

        Pet scooby = client.pets().getByPetId("scooby");
        Assert.assertNotNull(scooby);
        Assert.assertEquals(DaysOfWeekExtensibleEnum.THURSDAY, scooby.getDaysOfWeek());
        Assert.assertEquals(IntEnum.fromString("2.1"), scooby.getIntEnum());
        Assert.assertEquals("Scooby Scarface", scooby.getName());
    }

    @Test
    public void addPet() throws Exception {
        Pet pet = new Pet()
                .setName("Retriever")
                .setIntEnum(IntEnum.ONE)
                .setDaysOfWeek(DaysOfWeekExtensibleEnum.MONDAY);
        Pet res = client.pets().addPet(pet);
        Assert.assertEquals(pet.getName(), res.getName());
        Assert.assertEquals(DaysOfWeekExtensibleEnum.MONDAY, res.getDaysOfWeek());
        Assert.assertEquals(IntEnum.ONE, res.getIntEnum());
    }
}
