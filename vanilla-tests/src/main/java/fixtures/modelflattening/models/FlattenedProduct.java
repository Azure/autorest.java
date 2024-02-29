// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Flattened product.
 */
@Fluent
public class FlattenedProduct extends Resource {
    /*
     * The p.name property.
     */
    private String pName;

    /*
     * The type property.
     */
    private String typePropertiesType;

    /*
     * The provisioningStateValues property.
     */
    private FlattenedProductPropertiesProvisioningStateValues provisioningStateValues;

    /*
     * The provisioningState property.
     */
    private String provisioningState;

    /**
     * Creates an instance of FlattenedProduct class.
     */
    public FlattenedProduct() {
    }

    /**
     * Get the pName property: The p.name property.
     * 
     * @return the pName value.
     */
    public String getPName() {
        return this.pName;
    }

    /**
     * Set the pName property: The p.name property.
     * 
     * @param pName the pName value to set.
     * @return the FlattenedProduct object itself.
     */
    public FlattenedProduct setPName(String pName) {
        this.pName = pName;
        return this;
    }

    /**
     * Get the typePropertiesType property: The type property.
     * 
     * @return the typePropertiesType value.
     */
    public String getTypePropertiesType() {
        return this.typePropertiesType;
    }

    /**
     * Set the typePropertiesType property: The type property.
     * 
     * @param typePropertiesType the typePropertiesType value to set.
     * @return the FlattenedProduct object itself.
     */
    public FlattenedProduct setTypePropertiesType(String typePropertiesType) {
        this.typePropertiesType = typePropertiesType;
        return this;
    }

    /**
     * Get the provisioningStateValues property: The provisioningStateValues property.
     * 
     * @return the provisioningStateValues value.
     */
    public FlattenedProductPropertiesProvisioningStateValues getProvisioningStateValues() {
        return this.provisioningStateValues;
    }

    /**
     * Get the provisioningState property: The provisioningState property.
     * 
     * @return the provisioningState value.
     */
    public String getProvisioningState() {
        return this.provisioningState;
    }

    /**
     * Set the provisioningState property: The provisioningState property.
     * 
     * @param provisioningState the provisioningState value to set.
     * @return the FlattenedProduct object itself.
     */
    public FlattenedProduct setProvisioningState(String provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlattenedProduct setTags(Map<String, String> tags) {
        super.setTags(tags);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlattenedProduct setLocation(String location) {
        super.setLocation(location);
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
        jsonWriter.writeMapField("tags", getTags(), (writer, element) -> writer.writeString(element));
        jsonWriter.writeStringField("location", getLocation());
        if (pName != null
            || typePropertiesType != null
            || provisioningStateValues != null
            || provisioningState != null) {
            jsonWriter.writeStartObject("properties");
            jsonWriter.writeStringField("p\\.name", this.pName);
            jsonWriter.writeStringField("type", this.typePropertiesType);
            jsonWriter.writeStringField("provisioningState", this.provisioningState);
            jsonWriter.writeEndObject();
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of FlattenedProduct from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of FlattenedProduct if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the FlattenedProduct.
     */
    public static FlattenedProduct fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            FlattenedProduct deserializedFlattenedProduct = new FlattenedProduct();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedFlattenedProduct.setId(reader.getString());
                } else if ("type".equals(fieldName)) {
                    deserializedFlattenedProduct.setType(reader.getString());
                } else if ("tags".equals(fieldName)) {
                    Map<String, String> tags = reader.readMap(reader1 -> reader1.getString());
                    deserializedFlattenedProduct.setTags(tags);
                } else if ("location".equals(fieldName)) {
                    deserializedFlattenedProduct.setLocation(reader.getString());
                } else if ("name".equals(fieldName)) {
                    deserializedFlattenedProduct.setName(reader.getString());
                } else if ("properties".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("p\\.name".equals(fieldName)) {
                            deserializedFlattenedProduct.pName = reader.getString();
                        } else if ("type".equals(fieldName)) {
                            deserializedFlattenedProduct.typePropertiesType = reader.getString();
                        } else if ("provisioningStateValues".equals(fieldName)) {
                            deserializedFlattenedProduct.provisioningStateValues
                                = FlattenedProductPropertiesProvisioningStateValues.fromString(reader.getString());
                        } else if ("provisioningState".equals(fieldName)) {
                            deserializedFlattenedProduct.provisioningState = reader.getString();
                        } else {
                            reader.skipChildren();
                        }
                    }
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedFlattenedProduct;
        });
    }
}
