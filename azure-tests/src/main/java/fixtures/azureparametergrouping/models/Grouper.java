// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.azureparametergrouping.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Parameter group.
 */
@Fluent
public final class Grouper implements JsonSerializable<Grouper> {
    /*
     * A grouped parameter that is a constant.
     */
    private String groupedConstant = "foo";

    /*
     * Optional parameter part of a parameter grouping.
     */
    private String groupedParameter;

    /**
     * Creates an instance of Grouper class.
     */
    public Grouper() {
    }

    /**
     * Get the groupedConstant property: A grouped parameter that is a constant.
     * 
     * @return the groupedConstant value.
     */
    public String getGroupedConstant() {
        return this.groupedConstant;
    }

    /**
     * Set the groupedConstant property: A grouped parameter that is a constant.
     * 
     * @param groupedConstant the groupedConstant value to set.
     * @return the Grouper object itself.
     */
    public Grouper setGroupedConstant(String groupedConstant) {
        this.groupedConstant = groupedConstant;
        return this;
    }

    /**
     * Get the groupedParameter property: Optional parameter part of a parameter grouping.
     * 
     * @return the groupedParameter value.
     */
    public String getGroupedParameter() {
        return this.groupedParameter;
    }

    /**
     * Set the groupedParameter property: Optional parameter part of a parameter grouping.
     * 
     * @param groupedParameter the groupedParameter value to set.
     * @return the Grouper object itself.
     */
    public Grouper setGroupedParameter(String groupedParameter) {
        this.groupedParameter = groupedParameter;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("groupedConstant", this.groupedConstant);
        jsonWriter.writeStringField("groupedParameter", this.groupedParameter);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Grouper from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Grouper if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IOException If an error occurs while reading the Grouper.
     */
    public static Grouper fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Grouper deserializedGrouper = new Grouper();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("groupedConstant".equals(fieldName)) {
                    deserializedGrouper.groupedConstant = reader.getString();
                } else if ("groupedParameter".equals(fieldName)) {
                    deserializedGrouper.groupedParameter = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedGrouper;
        });
    }
}
