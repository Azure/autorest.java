// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.util.ExpandableStringEnum;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Defines values for PublicAccessType.
 */
public final class PublicAccessType extends ExpandableStringEnum<PublicAccessType>
    implements JsonSerializable<PublicAccessType> {
    /**
     * Static value container for PublicAccessType.
     */
    public static final PublicAccessType CONTAINER = fromString("container");

    /**
     * Static value blob for PublicAccessType.
     */
    public static final PublicAccessType BLOB = fromString("blob");

    /**
     * Creates a new instance of PublicAccessType value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public PublicAccessType() {
    }

    /**
     * Creates or finds a PublicAccessType from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding PublicAccessType.
     */
    public static PublicAccessType fromString(String name) {
        return fromString(name, PublicAccessType.class);
    }

    /**
     * Gets known PublicAccessType values.
     * 
     * @return known PublicAccessType values.
     */
    public static Collection<PublicAccessType> values() {
        return values(PublicAccessType.class);
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeString(toString());
    }

    /**
     * Reads a PublicAccessType from the JSON stream.
     * <p>
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The PublicAccessType that the JSON stream represented, may return null.
     * @throws java.io.IOException If a PublicAccessType fails to be read from the JsonReader.
     */
    public static PublicAccessType fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString(), PublicAccessType.class);
    }
}
