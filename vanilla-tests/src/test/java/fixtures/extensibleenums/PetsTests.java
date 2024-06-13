package fixtures.extensibleenums;

import fixtures.extensibleenums.models.DaysOfWeekExtensibleEnum;
import fixtures.extensibleenums.models.IntEnum;
import fixtures.extensibleenums.models.Pet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PetsTests {
    private static PetStoreInc client;

    @BeforeAll
    public static void setup() {
        client = new PetStoreIncBuilder().buildClient();
    }

    @Test
    public void getByPetId() {
        Pet tommy = client.getPets().getByPetId("tommy");
        assertNotNull(tommy);
        assertEquals(DaysOfWeekExtensibleEnum.MONDAY, tommy.getDaysOfWeek());
        assertEquals(IntEnum.ONE, tommy.getIntEnum());
        assertEquals("Tommy Tomson", tommy.getName());

        Pet casper = client.getPets().getByPetId("casper");
        assertNotNull(casper);
        assertEquals(DaysOfWeekExtensibleEnum.fromString("Weekend"), casper.getDaysOfWeek());
        assertEquals(IntEnum.TWO, casper.getIntEnum());
        assertEquals("Casper Ghosty", casper.getName());

        Pet scooby = client.getPets().getByPetId("scooby");
        assertNotNull(scooby);
        assertEquals(DaysOfWeekExtensibleEnum.THURSDAY, scooby.getDaysOfWeek());
        assertEquals(IntEnum.fromString("2.1"), scooby.getIntEnum());
        assertEquals("Scooby Scarface", scooby.getName());
    }

    @Test
    public void addPet() {
        Pet pet = new Pet()
                .setName("Retriever")
                .setIntEnum(IntEnum.ONE)
                .setDaysOfWeek(DaysOfWeekExtensibleEnum.MONDAY);
        Pet res = client.getPets().addPet(pet);
        assertEquals(pet.getName(), res.getName());
        assertEquals(DaysOfWeekExtensibleEnum.MONDAY, res.getDaysOfWeek());
        assertEquals(IntEnum.ONE, res.getIntEnum());
    }
}
