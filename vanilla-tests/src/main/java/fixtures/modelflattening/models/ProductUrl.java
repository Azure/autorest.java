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
 * The product URL.
 */
@Fluent
public final class ProductUrl extends GenericUrl {
    /*
     * URL value.
     */
    private String odataValue;

    /**
     * Creates an instance of ProductUrl class.
     */
    public ProductUrl() {
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
     * @return the ProductUrl object itself.
     */
    public ProductUrl setOdataValue(String odataValue) {
        this.odataValue = odataValue;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductUrl setGenericValue(String genericValue) {
        super.setGenericValue(genericValue);
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
        jsonWriter.writeStringField("generic_value", getGenericValue());
        jsonWriter.writeStringField("@odata.value", this.odataValue);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProductUrl from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProductUrl if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProductUrl.
     */
    public static ProductUrl fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProductUrl deserializedProductUrl = new ProductUrl();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("generic_value".equals(fieldName)) {
                    deserializedProductUrl.setGenericValue(reader.getString());
                } else if ("@odata.value".equals(fieldName)) {
                    deserializedProductUrl.odataValue = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProductUrl;
        });
    }
}
