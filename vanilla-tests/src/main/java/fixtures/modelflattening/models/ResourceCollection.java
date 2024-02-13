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
import java.util.List;
import java.util.Map;

/**
 * The ResourceCollection model.
 */
@Fluent
public final class ResourceCollection implements JsonSerializable<ResourceCollection> {
    /*
     * Flattened product.
     */
    private FlattenedProduct productresource;

    /*
     * The arrayofresources property.
     */
    private List<FlattenedProduct> arrayofresources;

    /*
     * Dictionary of <FlattenedProduct>
     */
    private Map<String, FlattenedProduct> dictionaryofresources;

    /**
     * Creates an instance of ResourceCollection class.
     */
    public ResourceCollection() {
    }

    /**
     * Get the productresource property: Flattened product.
     * 
     * @return the productresource value.
     */
    public FlattenedProduct getProductresource() {
        return this.productresource;
    }

    /**
     * Set the productresource property: Flattened product.
     * 
     * @param productresource the productresource value to set.
     * @return the ResourceCollection object itself.
     */
    public ResourceCollection setProductresource(FlattenedProduct productresource) {
        this.productresource = productresource;
        return this;
    }

    /**
     * Get the arrayofresources property: The arrayofresources property.
     * 
     * @return the arrayofresources value.
     */
    public List<FlattenedProduct> getArrayofresources() {
        return this.arrayofresources;
    }

    /**
     * Set the arrayofresources property: The arrayofresources property.
     * 
     * @param arrayofresources the arrayofresources value to set.
     * @return the ResourceCollection object itself.
     */
    public ResourceCollection setArrayofresources(List<FlattenedProduct> arrayofresources) {
        this.arrayofresources = arrayofresources;
        return this;
    }

    /**
     * Get the dictionaryofresources property: Dictionary of &lt;FlattenedProduct&gt;.
     * 
     * @return the dictionaryofresources value.
     */
    public Map<String, FlattenedProduct> getDictionaryofresources() {
        return this.dictionaryofresources;
    }

    /**
     * Set the dictionaryofresources property: Dictionary of &lt;FlattenedProduct&gt;.
     * 
     * @param dictionaryofresources the dictionaryofresources value to set.
     * @return the ResourceCollection object itself.
     */
    public ResourceCollection setDictionaryofresources(Map<String, FlattenedProduct> dictionaryofresources) {
        this.dictionaryofresources = dictionaryofresources;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getProductresource() != null) {
            getProductresource().validate();
        }
        if (getArrayofresources() != null) {
            getArrayofresources().forEach(e -> e.validate());
        }
        if (getDictionaryofresources() != null) {
            getDictionaryofresources().values().forEach(e -> {
                if (e != null) {
                    e.validate();
                }
            });
        }
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("productresource", this.productresource);
        jsonWriter.writeArrayField("arrayofresources", this.arrayofresources,
            (writer, element) -> writer.writeJson(element));
        jsonWriter.writeMapField("dictionaryofresources", this.dictionaryofresources,
            (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ResourceCollection from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ResourceCollection if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ResourceCollection.
     */
    public static ResourceCollection fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ResourceCollection deserializedResourceCollection = new ResourceCollection();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("productresource".equals(fieldName)) {
                    deserializedResourceCollection.productresource = FlattenedProduct.fromJson(reader);
                } else if ("arrayofresources".equals(fieldName)) {
                    List<FlattenedProduct> arrayofresources
                        = reader.readArray(reader1 -> FlattenedProduct.fromJson(reader1));
                    deserializedResourceCollection.arrayofresources = arrayofresources;
                } else if ("dictionaryofresources".equals(fieldName)) {
                    Map<String, FlattenedProduct> dictionaryofresources
                        = reader.readMap(reader1 -> FlattenedProduct.fromJson(reader1));
                    deserializedResourceCollection.dictionaryofresources = dictionaryofresources;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedResourceCollection;
        });
    }
}
