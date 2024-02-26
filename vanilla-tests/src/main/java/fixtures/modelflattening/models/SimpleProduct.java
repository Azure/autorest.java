// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The product documentation.
 */
@Fluent
public class SimpleProduct extends BaseProduct {
    /*
     * Display name of product.
     */
    private String maxProductDisplayName;

    /*
     * Capacity of product. For example, 4 people.
     */
    private SimpleProductPropertiesMaxProductCapacity capacity;

    /*
     * Generic URL value.
     */
    private String genericValue;

    /*
     * URL value.
     */
    private String odataValue;

    /**
     * Creates an instance of SimpleProduct class.
     */
    public SimpleProduct() {
    }

    /**
     * Get the maxProductDisplayName property: Display name of product.
     * 
     * @return the maxProductDisplayName value.
     */
    public String getMaxProductDisplayName() {
        return this.maxProductDisplayName;
    }

    /**
     * Set the maxProductDisplayName property: Display name of product.
     * 
     * @param maxProductDisplayName the maxProductDisplayName value to set.
     * @return the SimpleProduct object itself.
     */
    public SimpleProduct setMaxProductDisplayName(String maxProductDisplayName) {
        this.maxProductDisplayName = maxProductDisplayName;
        return this;
    }

    /**
     * Get the capacity property: Capacity of product. For example, 4 people.
     * 
     * @return the capacity value.
     */
    public SimpleProductPropertiesMaxProductCapacity getCapacity() {
        return this.capacity;
    }

    /**
     * Set the capacity property: Capacity of product. For example, 4 people.
     * 
     * @param capacity the capacity value to set.
     * @return the SimpleProduct object itself.
     */
    public SimpleProduct setCapacity(SimpleProductPropertiesMaxProductCapacity capacity) {
        this.capacity = capacity;
        return this;
    }

    /**
     * Get the genericValue property: Generic URL value.
     * 
     * @return the genericValue value.
     */
    public String getGenericValue() {
        return this.genericValue;
    }

    /**
     * Set the genericValue property: Generic URL value.
     * 
     * @param genericValue the genericValue value to set.
     * @return the SimpleProduct object itself.
     */
    public SimpleProduct setGenericValue(String genericValue) {
        this.genericValue = genericValue;
        return this;
    }

    /**
     * Get the odataValue property: URL value.
     * 
     * @return the odataValue value.
     */
    public String getOdataValue() {
        return this.odataValue;
    }

    /**
     * Set the odataValue property: URL value.
     * 
     * @param odataValue the odataValue value to set.
     * @return the SimpleProduct object itself.
     */
    public SimpleProduct setOdataValue(String odataValue) {
        this.odataValue = odataValue;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleProduct setProductId(String productId) {
        super.setProductId(productId);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleProduct setDescription(String description) {
        super.setDescription(description);
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("base_product_id", getProductId());
        jsonWriter.writeStringField("base_product_description", getDescription());
        if (maxProductDisplayName != null || capacity != null || genericValue != null || odataValue != null) {
            jsonWriter.writeStartObject("details");
            jsonWriter.writeStringField("max_product_display_name", this.maxProductDisplayName);
            jsonWriter.writeStringField("max_product_capacity",
                this.capacity == null ? null : this.capacity.toString());
            if (genericValue != null || odataValue != null) {
                jsonWriter.writeStartObject("max_product_image");
                jsonWriter.writeStringField("generic_value", this.genericValue);
                jsonWriter.writeStringField("@odata\\.value", this.odataValue);
                jsonWriter.writeEndObject();
            }
            jsonWriter.writeEndObject();
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SimpleProduct from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SimpleProduct if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SimpleProduct.
     */
    public static SimpleProduct fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            SimpleProduct deserializedSimpleProduct = new SimpleProduct();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("base_product_id".equals(fieldName)) {
                    deserializedSimpleProduct.setProductId(reader.getString());
                } else if ("base_product_description".equals(fieldName)) {
                    deserializedSimpleProduct.setDescription(reader.getString());
                } else if ("details".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("max_product_display_name".equals(fieldName)) {
                            deserializedSimpleProduct.maxProductDisplayName = reader.getString();
                        } else if ("max_product_capacity".equals(fieldName)) {
                            deserializedSimpleProduct.capacity
                                = SimpleProductPropertiesMaxProductCapacity.fromString(reader.getString());
                        } else if ("max_product_image".equals(fieldName)
                            && reader.currentToken() == JsonToken.START_OBJECT) {
                            while (reader.nextToken() != JsonToken.END_OBJECT) {
                                fieldName = reader.getFieldName();
                                reader.nextToken();

                                if ("generic_value".equals(fieldName)) {
                                    deserializedSimpleProduct.genericValue = reader.getString();
                                } else if ("@odata\\.value".equals(fieldName)) {
                                    deserializedSimpleProduct.odataValue = reader.getString();
                                } else {
                                    reader.skipChildren();
                                }
                            }
                        } else {
                            reader.skipChildren();
                        }
                    }
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedSimpleProduct;
        });
    }
}
