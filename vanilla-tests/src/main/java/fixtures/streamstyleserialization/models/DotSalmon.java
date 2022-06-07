// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

/** The DotSalmon model. */
@Fluent
public class DotSalmon extends DotFish {
    private String location;

    private Boolean iswild;

    /**
     * Get the location property: The location property.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The location property.
     *
     * @param location the location value to set.
     * @return the DotSalmon object itself.
     */
    public DotSalmon setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get the iswild property: The iswild property.
     *
     * @return the iswild value.
     */
    public Boolean iswild() {
        return this.iswild;
    }

    /**
     * Set the iswild property: The iswild property.
     *
     * @param iswild the iswild value to set.
     * @return the DotSalmon object itself.
     */
    public DotSalmon setIswild(Boolean iswild) {
        this.iswild = iswild;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public DotSalmon setSpecies(String species) {
        super.setSpecies(species);
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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) {
        return jsonWriter.flush();
    }

    public static DotSalmon fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    String species = null;
                    String location = null;
                    Boolean iswild = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("species".equals(fieldName)) {
                            species = reader.getStringValue();
                        } else if ("location".equals(fieldName)) {
                            location = reader.getStringValue();
                        } else if ("iswild".equals(fieldName)) {
                            iswild = JsonUtils.getNullableProperty(reader, r -> reader.getBooleanValue());
                        } else {
                            reader.skipChildren();
                        }
                    }
                    DotSalmon deserializedValue = new DotSalmon();
                    deserializedValue.setSpecies(species);
                    deserializedValue.setLocation(location);
                    deserializedValue.setIswild(iswild);

                    return deserializedValue;
                });
    }
}
