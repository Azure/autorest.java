// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.annotatedgettersandsetters;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import fixtures.annotatedgettersandsetters.models.Sku;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests that when {@code --annotate-getters-and-setters-for-serialization=true} is passed as a configuration to the
 * Java generator Fields and getter and setter Methods are properly annotated with Jackson annotations.
 */
public class GettersAndSettersTests {
    /**
     * Tests that Fields continue to be annotated with JsonProperty.
     */
    @Test
    public void validateFieldsAreAnnotated() {
        assertTrue(Arrays.stream(Sku.class.getDeclaredFields())
            .anyMatch(field -> field.isAnnotationPresent(JsonProperty.class)));
    }

    /**
     * Tests that getter and setter Methods are properly annotated with JsonGetter and JsonSetter.
     */
    @Test
    public void validateGettersAndSettersAreAnnotated() {
        Map<String, JsonProperty> jsonPropertyFieldMap = new HashMap<>();
        for (Field declaredField : Sku.class.getDeclaredFields()) {
            if (!declaredField.isAnnotationPresent(JsonProperty.class)) {
                continue;
            }

            jsonPropertyFieldMap.put(declaredField.getName(), declaredField.getAnnotation(JsonProperty.class));
        }

        for (Method declaredMethod : Sku.class.getDeclaredMethods()) {
            JsonGetter jsonGetter = declaredMethod.getAnnotation(JsonGetter.class);
            JsonSetter jsonSetter = declaredMethod.getAnnotation(JsonSetter.class);

            // Skip non-getter and non-setter methods.
            if (jsonGetter == null && jsonSetter == null) {
                continue;
            }

            if (jsonGetter != null && jsonSetter != null) {
                fail("Method cannot be annotated with both 'JsonGetter' and 'JsonSetter'.");
            }

            String expectedFieldName = getExpectedFieldName(declaredMethod);
            JsonProperty correspondingJsonProperty = jsonPropertyFieldMap.get(expectedFieldName);
            assertNotNull("'JsonProperty' annotated Field wasn't found for Method '" + declaredMethod.getName() + "'.",
                correspondingJsonProperty);

            if (jsonGetter != null) {
                if (correspondingJsonProperty.access() == JsonProperty.Access.WRITE_ONLY) {
                    fail("'JsonGetter' was added to getter method with a property that is write-only.");
                }

                assertEquals(correspondingJsonProperty.value(), jsonGetter.value());
            } else {
                assertEquals(correspondingJsonProperty.value(), jsonSetter.value());
            }
        }
    }

    private static String getExpectedFieldName(Method method) {
        Class<?> returnType = method.getReturnType();

        // 'get*' and 'set*' are both 3 characters, if the method is a boolean getter then it's 'is*' so 2 characters.
        String expectedFieldName = (returnType == boolean.class || returnType == Boolean.class)
            ? method.getName().substring(2)
            : method.getName().substring(3);

        return Character.toLowerCase(expectedFieldName.charAt(0)) + expectedFieldName.substring(1);
    }
}
