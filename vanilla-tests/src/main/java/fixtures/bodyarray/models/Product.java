// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodyarray.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The Product model.
 */
@Fluent
public final class Product implements JsonSerializable<Product> {
    /*
     * The integer property.
     */
    @Generated
    private Integer integer;

    /*
     * The string property.
     */
    @Generated
    private String string;

    /**
     * Creates an instance of Product class.
     */
    @Generated
    public Product() {
    }

    /**
     * Get the integer property: The integer property.
     * 
     * @return the integer value.
     */
    @Generated
    public Integer getInteger() {
        return this.integer;
    }

    /**
     * Set the integer property: The integer property.
     * 
     * @param integer the integer value to set.
     * @return the Product object itself.
     */
    @Generated
    public Product setInteger(Integer integer) {
        this.integer = integer;
        return this;
    }

    /**
     * Get the string property: The string property.
     * 
     * @return the string value.
     */
    @Generated
    public String getString() {
        return this.string;
    }

    /**
     * Set the string property: The string property.
     * 
     * @param string the string value to set.
     * @return the Product object itself.
     */
    @Generated
    public Product setString(String string) {
        this.string = string;
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
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("integer", this.integer);
        jsonWriter.writeStringField("string", this.string);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Product from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Product if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IOException If an error occurs while reading the Product.
     */
    @Generated
    public static Product fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Product deserializedProduct = new Product();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("integer".equals(fieldName)) {
                    deserializedProduct.integer = reader.getNullable(JsonReader::getInt);
                } else if ("string".equals(fieldName)) {
                    deserializedProduct.string = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProduct;
        });
    }
}
