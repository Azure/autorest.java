package fixtures.multipleinheritance;

import fixtures.multipleinheritance.models.Cat;
import fixtures.multipleinheritance.models.Feline;
import fixtures.multipleinheritance.models.Horse;
import fixtures.multipleinheritance.models.Kitten;
import fixtures.multipleinheritance.models.Pet;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MultipleInheritanceTests {
  private static MultipleInheritanceServiceClient client;

  @BeforeClass
  public static void setup() {
    client = new MultipleInheritanceServiceClientBuilder().buildClient();
  }

  @Test
  public void getHorse() throws Exception {
    Horse actual = client.getHorse();
    Assert.assertEquals("Fred", actual.getName());
    Assert.assertTrue(actual.isAShowHorse());
  }

  @Test
  public void putHorse() throws Exception {
    Horse horse = new Horse().setIsAShowHorse(false);
    horse.setName("General");
    client.putHorse(horse);
  }

  @Test
  public void getPet() throws Exception {
    Pet pet = client.getPet();
    Assert.assertEquals("Peanut", pet.getName());
  }

  @Test
  public void putPet() throws Exception {
    Pet pet = new Pet().setName("Butter");
    client.putPet(pet);
  }

  @Test
  public void getFeline() throws Exception {
    Feline feline = client.getFeline();
    Assert.assertTrue(feline.isHisses());
    Assert.assertTrue(feline.isMeows());
  }

  @Test
  public void putFeline() throws Exception {
    Feline feline = new Feline().setMeows(false).setHisses(true);
    client.putFeline(feline);
  }

  @Test
  public void getCat() throws Exception {
    Cat cat = client.getCat();
    Assert.assertEquals("Whiskers", cat.getName());
    Assert.assertTrue(cat.isLikesMilk());
    Assert.assertTrue(cat.isHisses());
    Assert.assertTrue(cat.isMeows());
  }

  @Test
  public void putCat() throws Exception {
    Cat cat = new Cat().setHisses(false).setLikesMilk(false).setMeows(true);
    cat.setName("Boots");
    client.putCat(cat);
  }

  @Test
  public void getKitten() throws Exception {
    Kitten kitten = client.getKitten();
    Assert.assertEquals("Gatito", kitten.getName());
    Assert.assertTrue(kitten.isMeows());
    Assert.assertTrue(kitten.isLikesMilk());
    Assert.assertTrue(kitten.isHisses());
    Assert.assertFalse(kitten.isEatsMiceYet());
  }

  @Test
  public void putKitten() throws Exception {
    Kitten kitten = new Kitten().setEatsMiceYet(true);
    kitten.setHisses(false).setLikesMilk(false).setMeows(true).setName("Kitty");
    client.putKitten(kitten);
  }
}
