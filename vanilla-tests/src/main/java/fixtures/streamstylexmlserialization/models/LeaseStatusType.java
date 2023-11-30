// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Defines values for LeaseStatusType.
 */
public enum LeaseStatusType implements JsonSerializable<LeaseStatusType> {
    /**
     * Enum value locked.
     */
    LOCKED("locked"),

    /**
     * Enum value unlocked.
     */
    UNLOCKED("unlocked");

    /**
     * The actual serialized value for a LeaseStatusType instance.
     */
    private final String value;

    LeaseStatusType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a LeaseStatusType instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed LeaseStatusType object, or null if unable to parse.
     */
    public static LeaseStatusType fromString(String value) {
        if (value == null) {
            return null;
        }
        LeaseStatusType[] items = LeaseStatusType.values();
        for (LeaseStatusType item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeString(value);
    }

    /**
     * Reads a LeaseStatusType from the JSON stream.
     * <p>
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The LeaseStatusType that the JSON stream represented, may return null.
     * @throws java.io.IOException If a LeaseStatusType fails to be read from the JsonReader.
     */
    public static LeaseStatusType fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString());
    }
}
