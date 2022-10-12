// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.complexstreamstylexmlserialization;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import fixtures.complexstreamstylexmlserialization.models.BlobName;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertFalse;

public class ComplexXmlTagTests {
    @Test
    public void xmlPropertyIsProperlyAnnotated() throws NoSuchFieldException {
        Field field = BlobName.class.getDeclaredField("content");
        assertFalse("Expected 'content' field to not be annotated with 'JacksonXmlText' but it was.",
            field.isAnnotationPresent(JacksonXmlText.class));
    }
}
