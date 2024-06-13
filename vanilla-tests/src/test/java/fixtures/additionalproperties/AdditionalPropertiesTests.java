package fixtures.additionalproperties;

import fixtures.additionalproperties.models.CatAPTrue;
import fixtures.additionalproperties.models.PetAPInProperties;
import fixtures.additionalproperties.models.PetAPInPropertiesWithAPString;
import fixtures.additionalproperties.models.PetAPObject;
import fixtures.additionalproperties.models.PetAPString;
import fixtures.additionalproperties.models.PetAPTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdditionalPropertiesTests {
    private static AdditionalPropertiesClient client;

    @BeforeAll
    public static void setup() throws Exception {
        client = new AdditionalPropertiesClientBuilder().buildClient();
    }

    @Test
    public void createAPTrue() {
        PetAPTrue petAPObject = new PetAPTrue();
        petAPObject.setId(1);
        petAPObject.setName("Puppy");
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("birthdate", OffsetDateTime.parse("2017-12-13T02:29:51Z"));
        petAPObject.getAdditionalProperties().put("complexProperty", Collections.singletonMap("color", "Red"));
        PetAPTrue response = client.getPets().createAPTrue(petAPObject);
        Assertions.assertEquals(1, response.getId());
        Assertions.assertEquals("Puppy", response.getName());
        Assertions.assertEquals(2, petAPObject.getAdditionalProperties().size());
        Assertions.assertEquals(OffsetDateTime.parse("2017-12-13T02:29:51Z"), petAPObject.getAdditionalProperties().get("birthdate"));
        Assertions.assertTrue(petAPObject.getAdditionalProperties().get("complexProperty") instanceof Map);
        Map<String, Object> complexProperty = (Map<String, Object>) petAPObject.getAdditionalProperties().get("complexProperty");
        Assertions.assertEquals(1, complexProperty.size());
        Assertions.assertEquals("Red", complexProperty.get("color"));
    }

    @Test
    public void createCatAPTrue() {
        CatAPTrue petAPObject = new CatAPTrue();
        petAPObject.setId(1);
        petAPObject.setName("Lisa");
        petAPObject.setFriendly(true);
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("birthdate", OffsetDateTime.parse("2017-12-13T02:29:51Z"));
        petAPObject.getAdditionalProperties().put("complexProperty", Collections.singletonMap("color", "Red"));
        CatAPTrue response = client.getPets().createCatAPTrue(petAPObject);
        Assertions.assertEquals(1, response.getId());
        Assertions.assertEquals("Lisa", response.getName());
        Assertions.assertTrue(response.isFriendly());
        Assertions.assertEquals(2, petAPObject.getAdditionalProperties().size());
        Assertions.assertEquals(OffsetDateTime.parse("2017-12-13T02:29:51Z"), petAPObject.getAdditionalProperties().get("birthdate"));
        Assertions.assertTrue(petAPObject.getAdditionalProperties().get("complexProperty") instanceof Map);
        Map<String, Object> complexProperty = (Map<String, Object>) petAPObject.getAdditionalProperties().get("complexProperty");
        Assertions.assertEquals(1, complexProperty.size());
        Assertions.assertEquals("Red", complexProperty.get("color"));
    }

    @Test
    public void createAPObject() {
        PetAPTrue puppy = new PetAPTrue();
        puppy.setId(1);
        puppy.setName("Puppy");
        puppy.setAdditionalProperties(new HashMap<>());
        puppy.getAdditionalProperties().put("birthdate", OffsetDateTime.parse("2017-12-13T02:29:51Z"));
        puppy.getAdditionalProperties().put("complexProperty", Collections.singletonMap("color", "Red"));

        PetAPObject petAPObject = new PetAPObject();
        petAPObject.setId(2);
        petAPObject.setName("Hira");
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("siblings", Collections.singletonList(puppy));
        petAPObject.getAdditionalProperties().put("picture", new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254});
        PetAPObject response = client.getPets().createAPObject(petAPObject);

        Assertions.assertEquals(2, response.getId());
        Assertions.assertEquals("Hira", response.getName());
        Assertions.assertEquals(2, petAPObject.getAdditionalProperties().size());
        Assertions.assertArrayEquals(new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254}, (byte[]) petAPObject.getAdditionalProperties().get("picture"));
        Assertions.assertTrue(petAPObject.getAdditionalProperties().get("siblings") instanceof List);
        List<Object> siblings = (List<Object>) petAPObject.getAdditionalProperties().get("siblings");
        Assertions.assertEquals(1, siblings.size());
    }

    @Test
    public void createAPString() {
        PetAPString petAPObject = new PetAPString();
        petAPObject.setId(3);
        petAPObject.setName("Tommy");
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("color", "red");
        petAPObject.getAdditionalProperties().put("weight", "10 kg");
        petAPObject.getAdditionalProperties().put("city", "Bombay");
        PetAPString response = client.getPets().createAPString(petAPObject);
        Assertions.assertEquals(3, response.getId());
        Assertions.assertEquals("Tommy", response.getName());
        Assertions.assertEquals(3, petAPObject.getAdditionalProperties().size());
        Assertions.assertEquals("red", petAPObject.getAdditionalProperties().get("color"));
        Assertions.assertEquals("10 kg", petAPObject.getAdditionalProperties().get("weight"));
        Assertions.assertEquals("Bombay", petAPObject.getAdditionalProperties().get("city"));
    }

    @Test
    public void createAPInProperties() {
        PetAPInProperties petAPObject = new PetAPInProperties();
        petAPObject.setId(4);
        petAPObject.setName("Bunny");
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("height", 5.61f);
        petAPObject.getAdditionalProperties().put("weight", 599f);
        petAPObject.getAdditionalProperties().put("footsize", 11.5f);
        PetAPInProperties response = client.getPets().createAPInProperties(petAPObject);
        Assertions.assertEquals(4, response.getId());
        Assertions.assertEquals("Bunny", response.getName());
        Assertions.assertEquals(3, petAPObject.getAdditionalProperties().size());
        Assertions.assertEquals(5.61f, petAPObject.getAdditionalProperties().get("height"), 0.0f);
        Assertions.assertEquals(599f, petAPObject.getAdditionalProperties().get("weight"), 0.0f);
        Assertions.assertEquals(11.5f, petAPObject.getAdditionalProperties().get("footsize"), 0.0f);
    }

    @Test
    public void createAPInPropertiesWithAPString() {
        PetAPInPropertiesWithAPString petAPObject = new PetAPInPropertiesWithAPString();
        petAPObject.setId(5);
        petAPObject.setName("Funny");
        petAPObject.setOdataLocation("westus");
        petAPObject.setAdditionalPropertiesProperty(new HashMap<>());
        petAPObject.getAdditionalPropertiesProperty().put("height", 5.61f);
        petAPObject.getAdditionalPropertiesProperty().put("weight", 599f);
        petAPObject.getAdditionalPropertiesProperty().put("footsize", 11.5f);
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("color", "red");
        petAPObject.getAdditionalProperties().put("city", "Seattle");
        petAPObject.getAdditionalProperties().put("food", "tikka masala");
        client.getPets().createAPInPropertiesWithAPString(petAPObject);
    }
}
