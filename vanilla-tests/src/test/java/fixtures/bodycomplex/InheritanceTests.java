package fixtures.bodycomplex;

import fixtures.bodycomplex.models.Dog;
import fixtures.bodycomplex.models.Siamese;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class InheritanceTests {
    private static AutoRestComplexTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void getValid() throws Exception {
        Siamese result = client.getInheritances().getValid();
        Assert.assertEquals("persian", result.getBreed());
        Assert.assertEquals("green", result.getColor());
        Assert.assertEquals(2, result.getId().intValue());
        Assert.assertEquals("Siameeee", result.getName());
        Assert.assertEquals("french fries", result.getHates().get(1).getFood());
    }

    @Test
    public void putValid() throws Exception {
        Siamese body = new Siamese();
        body.setBreed("persian");
        body.setColor("green");
        body.setId(2);
        body.setName("Siameeee");
        body.setHates(new ArrayList<Dog>());
        Dog dog1 = new Dog();
        dog1.setName("Potato");
        dog1.setId(1);
        dog1.setFood("tomato");
        body.getHates().add(dog1);
        Dog dog2 = new Dog();
        dog2.setFood("french fries");
        dog2.setId(-1);
        dog2.setName("Tomato");
        body.getHates().add(dog2);
        client.getInheritances().putValidWithResponseAsync(body).block();
    }
}
