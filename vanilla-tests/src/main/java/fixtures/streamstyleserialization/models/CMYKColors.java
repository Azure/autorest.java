// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.util.ExpandableStringEnum;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Defines values for CMYKColors.
 */
public final class CMYKColors extends ExpandableStringEnum<CMYKColors> implements JsonSerializable<CMYKColors> {
    /**
     * Static value cyan for CMYKColors.
     */
    public static final CMYKColors CYAN = fromString("cyan");

    /**
     * Static value Magenta for CMYKColors.
     */
    public static final CMYKColors MAGENTA = fromString("Magenta");

    /**
     * Static value YELLOW for CMYKColors.
     */
    public static final CMYKColors YELLOW = fromString("YELLOW");

    /**
     * Static value blacK for CMYKColors.
     */
    public static final CMYKColors BLACK = fromString("blacK");

    /**
     * Creates a new instance of CMYKColors value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public CMYKColors() {
    }

    /**
     * Creates or finds a CMYKColors from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding CMYKColors.
     */
    public static CMYKColors fromString(String name) {
        return fromString(name, CMYKColors.class);
    }

    /**
     * Gets known CMYKColors values.
     * 
     * @return known CMYKColors values.
     */
    public static Collection<CMYKColors> values() {
        return values(CMYKColors.class);
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeString(toString());
    }

    /**
     * Reads a CMYKColors from the JSON stream.
     * <p>
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The CMYKColors that the JSON stream represented, may return null.
     * @throws java.io.IOException If a CMYKColors fails to be read from the JsonReader.
     */
    public static CMYKColors fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString(), CMYKColors.class);
    }
}
