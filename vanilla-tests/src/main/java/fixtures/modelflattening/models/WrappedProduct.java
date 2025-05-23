// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The wrapped produc.
 */
@Fluent
public final class WrappedProduct implements JsonSerializable<WrappedProduct> {
    /*
     * the product value
     */
    @Generated
    private String value;

    /**
     * Creates an instance of WrappedProduct class.
     */
    @Generated
    public WrappedProduct() {
    }

    /**
     * Get the value property: the product value.
     * 
     * @return the value value.
     */
    @Generated
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: the product value.
     * 
     * @param value the value value to set.
     * @return the WrappedProduct object itself.
     */
    @Generated
    public WrappedProduct setValue(String value) {
        this.value = value;
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
        jsonWriter.writeStringField("value", this.value);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WrappedProduct from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of WrappedProduct if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the WrappedProduct.
     */
    @Generated
    public static WrappedProduct fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WrappedProduct deserializedWrappedProduct = new WrappedProduct();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    deserializedWrappedProduct.value = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedWrappedProduct;
        });
    }
}
