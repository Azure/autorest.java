// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.validation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The product documentation.
 */
@Fluent
public final class ChildProduct implements JsonSerializable<ChildProduct> {
    /*
     * Constant string
     */
    private final String constProperty = "constant";

    /*
     * Count
     */
    private Integer count;

    /**
     * Creates an instance of ChildProduct class.
     */
    public ChildProduct() {
    }

    /**
     * Get the constProperty property: Constant string.
     * 
     * @return the constProperty value.
     */
    public String getConstProperty() {
        return this.constProperty;
    }

    /**
     * Get the count property: Count.
     * 
     * @return the count value.
     */
    public Integer getCount() {
        return this.count;
    }

    /**
     * Set the count property: Count.
     * 
     * @param count the count value to set.
     * @return the ChildProduct object itself.
     */
    public ChildProduct setCount(Integer count) {
        this.count = count;
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
        jsonWriter.writeStringField("constProperty", this.constProperty);
        jsonWriter.writeNumberField("count", this.count);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ChildProduct from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ChildProduct if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ChildProduct.
     */
    public static ChildProduct fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ChildProduct deserializedChildProduct = new ChildProduct();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("count".equals(fieldName)) {
                    deserializedChildProduct.count = reader.getNullable(JsonReader::getInt);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedChildProduct;
        });
    }
}
