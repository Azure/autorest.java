package fixtures.multipleinheritance;

import fixtures.multipleinheritance.models.Cat;
import fixtures.multipleinheritance.models.Feline;
import fixtures.multipleinheritance.models.Horse;
import fixtures.multipleinheritance.models.Kitten;
import fixtures.multipleinheritance.models.Pet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleInheritanceTests {
    private static MultipleInheritanceServiceClient client;

    @BeforeAll
    public static void setup() {
        client = new MultipleInheritanceServiceClientBuilder().buildClient();
    }

    @Test
    public void getHorse() {
        Horse actual = client.getHorse();
        assertEquals("Fred", actual.getName());
        assertTrue(actual.isAShowHorse());
    }

    @Test
    public void putHorse() {
        Horse horse = new Horse().setIsAShowHorse(false);
        horse.setName("General");
        client.putHorse(horse);
    }

    @Test
    public void getPet() {
        Pet pet = client.getPet();
        assertEquals("Peanut", pet.getName());
    }

    @Test
    public void putPet() {
        Pet pet = new Pet().setName("Butter");
        client.putPet(pet);
    }

    @Test
    public void getFeline() {
        Feline feline = client.getFeline();
        assertTrue(feline.isHisses());
        assertTrue(feline.isMeows());
    }

    @Test
    public void putFeline() {
        Feline feline = new Feline().setMeows(false).setHisses(true);
        client.putFeline(feline);
    }

    @Test
    public void getCat() {
        Cat cat = client.getCat();
        assertEquals("Whiskers", cat.getName());
        assertTrue(cat.isLikesMilk());
        assertTrue(cat.isHisses());
        assertTrue(cat.isMeows());
    }

    @Test
    public void putCat() {
        Cat cat = new Cat().setHisses(false).setLikesMilk(false).setMeows(true);
        cat.setName("Boots");
        client.putCat(cat);
    }

    @Test
    public void getKitten() {
        Kitten kitten = client.getKitten();
        assertEquals("Gatito", kitten.getName());
        assertTrue(kitten.isMeows());
        assertTrue(kitten.isLikesMilk());
        assertTrue(kitten.isHisses());
        assertFalse(kitten.isEatsMiceYet());
    }

    @Test
    public void putKitten() {
        Kitten kitten = new Kitten().setEatsMiceYet(true);
        kitten.setHisses(false).setLikesMilk(false).setMeows(true).setName("Kitty");
        client.putKitten(kitten);
    }
}
