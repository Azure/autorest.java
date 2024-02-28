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
public final class ConstantProduct implements JsonSerializable<ConstantProduct> {
    /*
     * Constant string
     */
    private String constProperty = "constant";

    /*
     * Constant string2
     */
    private String constProperty2 = "constant2";

    /**
     * Creates an instance of ConstantProduct class.
     */
    public ConstantProduct() {
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
     * Set the constProperty property: Constant string.
     * 
     * @param constProperty the constProperty value to set.
     * @return the ConstantProduct object itself.
     */
    public ConstantProduct setConstProperty(String constProperty) {
        this.constProperty = constProperty;
        return this;
    }

    /**
     * Get the constProperty2 property: Constant string2.
     * 
     * @return the constProperty2 value.
     */
    public String getConstProperty2() {
        return this.constProperty2;
    }

    /**
     * Set the constProperty2 property: Constant string2.
     * 
     * @param constProperty2 the constProperty2 value to set.
     * @return the ConstantProduct object itself.
     */
    public ConstantProduct setConstProperty2(String constProperty2) {
        this.constProperty2 = constProperty2;
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
        jsonWriter.writeStringField("constProperty2", this.constProperty2);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ConstantProduct from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ConstantProduct if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ConstantProduct.
     */
    public static ConstantProduct fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ConstantProduct deserializedConstantProduct = new ConstantProduct();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                reader.skipChildren();
            }

            return deserializedConstantProduct;
        });
    }
}
