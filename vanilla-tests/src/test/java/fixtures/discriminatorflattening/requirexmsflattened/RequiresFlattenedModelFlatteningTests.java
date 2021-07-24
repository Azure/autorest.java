// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.discriminatorflattening.requirexmsflattened;

import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fixtures.discriminatorflattening.requirexmsflattened.models.MetricAlertCriteria;
import fixtures.discriminatorflattening.requirexmsflattened.models.MetricAlertResource;
import fixtures.discriminatorflattening.requirexmsflattened.models.MetricAlertSingleResourceMultipleMetricCriteria;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequiresFlattenedModelFlatteningTests {
    @Test
    public void validateJsonFlatten() {
        // MetricAlertCriteria has a discriminator with '.' but doesn't have 'x-ms-flattened'.
        // This class shouldn't be annotated with '@JsonFlatten' nor have it's discriminator name escaped.
        validationHelper(MetricAlertCriteria.class, false, true, property -> !property.contains("\\."));

        // MetricAlertSingleResourceMultipleMetricCriteria has a discriminator with '.' but doesn't have
        // 'x-ms-flattened'.
        // This class shouldn't be annotated with '@JsonFlatten' nor have it's discriminator name escaped.
        validationHelper(MetricAlertSingleResourceMultipleMetricCriteria.class, false, true,
            property -> !property.contains("\\."));

        // MetricAlertResource is annotated with 'x-ms-flattened' but doesn't have a discriminator.
        // This class should be annotated with '@JsonFlatten'.
        validationHelper(MetricAlertResource.class, true, false, null);
    }

    private static void validationHelper(Class<?> clazz, boolean hasJsonFlatten, boolean hasJsonTypeInfo,
        Predicate<String> jsonTypePropertyPredicate) {
        assertEquals(hasJsonFlatten, clazz.getAnnotation(JsonFlatten.class) != null);

        JsonTypeInfo jsonTypeInfo = clazz.getAnnotation(JsonTypeInfo.class);
        assertEquals(hasJsonTypeInfo, jsonTypeInfo != null);

        if (hasJsonTypeInfo) {
            assertTrue(jsonTypePropertyPredicate.test(jsonTypeInfo.property()));
        }
    }
}
