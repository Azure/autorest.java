package fixtures.additionalproperties;

import fixtures.additionalproperties.models.CatAPTrue;
import fixtures.additionalproperties.models.PetAPInProperties;
import fixtures.additionalproperties.models.PetAPInPropertiesWithAPString;
import fixtures.additionalproperties.models.PetAPObject;
import fixtures.additionalproperties.models.PetAPString;
import fixtures.additionalproperties.models.PetAPTrue;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdditionalPropertiesTests {
    private static AdditionalPropertiesClient client;

    @BeforeClass
    public static void setup() throws Exception {
        client = new AdditionalPropertiesClientBuilder().build();
    }

    @Test
    public void createAPTrue() throws Exception {
        PetAPTrue petAPObject = new PetAPTrue();
        petAPObject.setId(1);
        petAPObject.setName("Puppy");
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("birthdate", OffsetDateTime.parse("2017-12-13T02:29:51Z"));
        petAPObject.getAdditionalProperties().put("complexProperty", Collections.singletonMap("color", "Red"));
        PetAPTrue response = client.pets().createAPTrue(petAPObject);
        Assert.assertEquals(1, response.getId());
        Assert.assertEquals("Puppy", response.getName());
        Assert.assertEquals(2, petAPObject.getAdditionalProperties().size());
        Assert.assertEquals(OffsetDateTime.parse("2017-12-13T02:29:51Z"), petAPObject.getAdditionalProperties().get("birthdate"));
        Assert.assertTrue(petAPObject.getAdditionalProperties().get("complexProperty") instanceof Map);
        Map<String, Object> complexProperty = (Map<String, Object>) petAPObject.getAdditionalProperties().get("complexProperty");
        Assert.assertEquals(1, complexProperty.size());
        Assert.assertEquals("Red", complexProperty.get("color"));
    }

    @Test
    public void createCatAPTrue() throws Exception {
        CatAPTrue petAPObject = new CatAPTrue();
        petAPObject.setId(1);
        petAPObject.setName("Lisa");
        petAPObject.setFriendly(true);
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("birthdate", OffsetDateTime.parse("2017-12-13T02:29:51Z"));
        petAPObject.getAdditionalProperties().put("complexProperty", Collections.singletonMap("color", "Red"));
        CatAPTrue response = client.pets().createCatAPTrue(petAPObject);
        Assert.assertEquals(1, response.getId());
        Assert.assertEquals("Lisa", response.getName());
        Assert.assertTrue(response.isFriendly());
        Assert.assertEquals(2, petAPObject.getAdditionalProperties().size());
        Assert.assertEquals(OffsetDateTime.parse("2017-12-13T02:29:51Z"), petAPObject.getAdditionalProperties().get("birthdate"));
        Assert.assertTrue(petAPObject.getAdditionalProperties().get("complexProperty") instanceof Map);
        Map<String, Object> complexProperty = (Map<String, Object>) petAPObject.getAdditionalProperties().get("complexProperty");
        Assert.assertEquals(1, complexProperty.size());
        Assert.assertEquals("Red", complexProperty.get("color"));
    }

    @Test
    public void createAPObject() throws Exception {
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
        PetAPObject response = client.pets().createAPObject(petAPObject);

        Assert.assertEquals(2, response.getId());
        Assert.assertEquals("Hira", response.getName());
        Assert.assertEquals(2, petAPObject.getAdditionalProperties().size());
        Assert.assertArrayEquals(new byte[]{(byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 254}, (byte[]) petAPObject.getAdditionalProperties().get("picture"));
        Assert.assertTrue(petAPObject.getAdditionalProperties().get("siblings") instanceof List);
        List<Object> siblings = (List<Object>) petAPObject.getAdditionalProperties().get("siblings");
        Assert.assertEquals(1, siblings.size());
    }

    @Test
    public void createAPString() throws Exception {
        PetAPString petAPObject = new PetAPString();
        petAPObject.setId(3);
        petAPObject.setName("Tommy");
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("color", "red");
        petAPObject.getAdditionalProperties().put("weight", "10 kg");
        petAPObject.getAdditionalProperties().put("city", "Bombay");
        PetAPString response = client.pets().createAPString(petAPObject);
        Assert.assertEquals(3, response.getId());
        Assert.assertEquals("Tommy", response.getName());
        Assert.assertEquals(3, petAPObject.getAdditionalProperties().size());
        Assert.assertEquals("red", petAPObject.getAdditionalProperties().get("color"));
        Assert.assertEquals("10 kg", petAPObject.getAdditionalProperties().get("weight"));
        Assert.assertEquals("Bombay", petAPObject.getAdditionalProperties().get("city"));
    }

    @Test
    public void createAPInProperties() throws Exception {
        PetAPInProperties petAPObject = new PetAPInProperties();
        petAPObject.setId(4);
        petAPObject.setName("Bunny");
        petAPObject.setAdditionalProperties(new HashMap<>());
        petAPObject.getAdditionalProperties().put("height", 5.61f);
        petAPObject.getAdditionalProperties().put("weight", 599f);
        petAPObject.getAdditionalProperties().put("footsize", 11.5f);
        PetAPInProperties response = client.pets().createAPInProperties(petAPObject);
        Assert.assertEquals(4, response.getId());
        Assert.assertEquals("Bunny", response.getName());
        Assert.assertEquals(3, petAPObject.getAdditionalProperties().size());
        Assert.assertEquals(5.61f, petAPObject.getAdditionalProperties().get("height"), 0.0f);
        Assert.assertEquals(599f, petAPObject.getAdditionalProperties().get("weight"), 0.0f);
        Assert.assertEquals(11.5f, petAPObject.getAdditionalProperties().get("footsize"), 0.0f);
    }

    @Test
    public void createAPInPropertiesWithAPString() throws Exception {
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
        client.pets().createAPInPropertiesWithAPString(petAPObject);
    }
}