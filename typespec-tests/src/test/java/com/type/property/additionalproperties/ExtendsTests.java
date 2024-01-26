package com.type.property.additionalproperties;

import com.type.property.additionalproperties.models.ExtendsUnknownAdditionalPropertiesDiscriminated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.type.property.additionalproperties.models.ExtendsFloatAdditionalProperties;
import com.type.property.additionalproperties.models.ExtendsModelAdditionalProperties;
import com.type.property.additionalproperties.models.ExtendsModelArrayAdditionalProperties;
import com.type.property.additionalproperties.models.ExtendsStringAdditionalProperties;
import com.type.property.additionalproperties.models.ExtendsUnknownAdditionalProperties;
import com.type.property.additionalproperties.models.ExtendsUnknownAdditionalPropertiesDerived;
import com.type.property.additionalproperties.models.ExtendsUnknownAdditionalPropertiesDiscriminatedDerived;
import com.type.property.additionalproperties.models.ModelForRecord;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExtendsTests {
    private final ExtendsFloatClient extendsFloatClient = new AdditionalPropertiesClientBuilder().buildExtendsFloatClient();
    private final ExtendsModelArrayClient extendsModelArrayClient = new AdditionalPropertiesClientBuilder().buildExtendsModelArrayClient();
    private final ExtendsModelClient extendsModelClient = new AdditionalPropertiesClientBuilder().buildExtendsModelClient();
    private final ExtendsStringClient extendsStringClient = new AdditionalPropertiesClientBuilder().buildExtendsStringClient();
    private final ExtendsUnknownClient extendsUnknownClient = new AdditionalPropertiesClientBuilder().buildExtendsUnknownClient();
    private final ExtendsUnknownDerivedClient extendsUnknownDerivedClient = new AdditionalPropertiesClientBuilder().buildExtendsUnknownDerivedClient();
    private final ExtendsUnknownDiscriminatedClient extendsUnknownDiscriminatedClient = new AdditionalPropertiesClientBuilder().buildExtendsUnknownDiscriminatedClient();

    @Test
    public void testExtendsFloat() {
        Map<String, Double> propertyMap = new LinkedHashMap<>();
        propertyMap.put("prop", 42.42);
        ExtendsFloatAdditionalProperties body = new ExtendsFloatAdditionalProperties(42.42);
        body.setAdditionalProperties(propertyMap);
        extendsFloatClient.put(body);

        ExtendsFloatAdditionalProperties properties = extendsFloatClient.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertEquals(42.42, properties.getId());
        Assertions.assertIterableEquals(propertyMap.entrySet(), properties.getAdditionalProperties().entrySet());
    }

    @Test
    @Disabled("The 'Get' request did not respond for a long time.")
    public void testExtendsModelArrayClient() {
        Map<String, List<ModelForRecord>> propertyMap = new LinkedHashMap<>();
        propertyMap.put("prop", Arrays.asList(new ModelForRecord("ok"), new ModelForRecord("ok")));

        ExtendsModelArrayAdditionalProperties body = new ExtendsModelArrayAdditionalProperties();
        body.setAdditionalProperties(propertyMap);
        extendsModelArrayClient.put(body);

        ExtendsModelArrayAdditionalProperties properties = extendsModelArrayClient.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertNotNull(properties.getAdditionalProperties().get("prop"));
        properties.getAdditionalProperties().get("prop").forEach(modelForRecord ->
                Assertions.assertEquals("ok", modelForRecord.getState()));
    }

    @Test
    public void testExtendsModelClient() {
        ExtendsModelAdditionalProperties body = new ExtendsModelAdditionalProperties();
        Map<String, ModelForRecord> propertyMap = new LinkedHashMap<>();
        propertyMap.put("prop", new ModelForRecord("ok"));
        body.setAdditionalProperties(propertyMap);
        extendsModelClient.put(body);

        ExtendsModelAdditionalProperties properties = extendsModelClient.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertNotNull(properties.getAdditionalProperties().get("prop"));
        Assertions.assertEquals("ok", extendsModelClient.get().getAdditionalProperties().get("prop").getState());
    }

    @Test
    public void testExtendsStringClient() {
        Map<String, String> propertyMap = new LinkedHashMap<>();
        propertyMap.put("prop", "abc") ;
        ExtendsStringAdditionalProperties body =
                new ExtendsStringAdditionalProperties("ExtendsStringAdditionalProperties");
        body.setAdditionalProperties(propertyMap);
        extendsStringClient.put(body);

        ExtendsStringAdditionalProperties properties = extendsStringClient.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertEquals("ExtendsStringAdditionalProperties", properties.getName());
        Assertions.assertEquals(propertyMap, properties.getAdditionalProperties());
    }

    @Test
    public void testExtendsUnknownClient() {
        Map<String, Object> propertyMap = new LinkedHashMap<>();
        propertyMap.put("prop1", 32);
        propertyMap.put("prop2", true) ;
        propertyMap.put("prop3", "abc") ;
        ExtendsUnknownAdditionalProperties body =
                new ExtendsUnknownAdditionalProperties("ExtendsUnknownAdditionalProperties");
        body.setAdditionalProperties(propertyMap);
        extendsUnknownClient.put(body);

        ExtendsUnknownAdditionalProperties properties = extendsUnknownClient.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertEquals("ExtendsUnknownAdditionalProperties", properties.getName());
        Assertions.assertEquals(propertyMap, properties.getAdditionalProperties());
    }

    @Test
    public void testExtendsUnknownDerivedClient() {
        Map<String, Object> additionalProperty = new LinkedHashMap<>();
        additionalProperty.put("prop1", 32);
        additionalProperty.put("prop2", true) ;
        additionalProperty.put("prop3", "abc") ;
        ExtendsUnknownAdditionalPropertiesDerived body = new ExtendsUnknownAdditionalPropertiesDerived("ExtendsUnknownAdditionalProperties", 314)
                .setAge(2.71828);
        body.setAdditionalProperties(additionalProperty);
        extendsUnknownDerivedClient.put(body);

        ExtendsUnknownAdditionalPropertiesDerived properties = extendsUnknownDerivedClient.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertEquals("ExtendsUnknownAdditionalProperties", properties.getName());
        Assertions.assertEquals(additionalProperty, properties.getAdditionalProperties());
    }

    @Test
    public void testExtendsUnknownDiscriminatedClient() {
        Map<String, Object> additionalProperty = new LinkedHashMap<>();
        additionalProperty.put("prop1", 32);
        additionalProperty.put("prop2", true) ;
        additionalProperty.put("prop3", "abc") ;
        ExtendsUnknownAdditionalPropertiesDiscriminatedDerived body = new ExtendsUnknownAdditionalPropertiesDiscriminatedDerived("Derived", 314)
                .setAge(2.71828);
        body.setAdditionalProperties(additionalProperty);
        extendsUnknownDiscriminatedClient.put(body);

        ExtendsUnknownAdditionalPropertiesDiscriminated properties = extendsUnknownDiscriminatedClient.get();
        Assertions.assertNotNull(properties);
        Assertions.assertNotNull(properties.getAdditionalProperties());
        Assertions.assertEquals("Derived", properties.getName());
        Assertions.assertEquals(additionalProperty, properties.getAdditionalProperties());
    }
}
