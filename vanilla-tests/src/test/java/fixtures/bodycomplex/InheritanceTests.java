package fixtures.bodycomplex;

import fixtures.bodycomplex.models.Dog;
import fixtures.bodycomplex.models.Siamese;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InheritanceTests {
    private static AutoRestComplexTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestComplexTestServiceBuilder().buildClient();
    }

    @Test
    public void getValid() {
        Siamese result = client.getInheritances().getValid();
        assertEquals("persian", result.getBreed());
        assertEquals("green", result.getColor());
        assertEquals(2, result.getId().intValue());
        assertEquals("Siameeee", result.getName());
        assertEquals("french fries", result.getHates().get(1).getFood());
    }

    @Test
    public void putValid() {
        Siamese body = new Siamese();
        body.setBreed("persian");
        body.setColor("green");
        body.setId(2);
        body.setName("Siameeee");
        body.setHates(new ArrayList<>());
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
