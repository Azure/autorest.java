// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.modelflattening.models;

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
public class BaseProduct implements JsonSerializable<BaseProduct> {
    /*
     * Unique identifier representing a specific product for a given latitude & longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     */
    private String productId;

    /*
     * Description of product.
     */
    private String description;

    /**
     * Creates an instance of BaseProduct class.
     */
    public BaseProduct() {
    }

    /**
     * Get the productId property: Unique identifier representing a specific product for a given latitude &amp;
     * longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     * 
     * @return the productId value.
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * Set the productId property: Unique identifier representing a specific product for a given latitude &amp;
     * longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     * 
     * @param productId the productId value to set.
     * @return the BaseProduct object itself.
     */
    public BaseProduct setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    /**
     * Get the description property: Description of product.
     * 
     * @return the description value.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: Description of product.
     * 
     * @param description the description value to set.
     * @return the BaseProduct object itself.
     */
    public BaseProduct setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getProductId() == null) {
            throw new IllegalArgumentException("Missing required property productId in model BaseProduct");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("base_product_id", this.productId);
        jsonWriter.writeStringField("base_product_description", this.description);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BaseProduct from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BaseProduct if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BaseProduct.
     */
    public static BaseProduct fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BaseProduct deserializedBaseProduct = new BaseProduct();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("base_product_id".equals(fieldName)) {
                    deserializedBaseProduct.productId = reader.getString();
                } else if ("base_product_description".equals(fieldName)) {
                    deserializedBaseProduct.description = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBaseProduct;
        });
    }
}
