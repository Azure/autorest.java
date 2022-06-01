// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

/** The MyDerivedType model. */
@Fluent
public final class MyDerivedType extends MyBaseType {
    private String propD1;

    /**
     * Get the propD1 property: The propD1 property.
     *
     * @return the propD1 value.
     */
    public String getPropD1() {
        return this.propD1;
    }

    /**
     * Set the propD1 property: The propD1 property.
     *
     * @param propD1 the propD1 value to set.
     * @return the MyDerivedType object itself.
     */
    public MyDerivedType setPropD1(String propD1) {
        this.propD1 = propD1;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public MyDerivedType setPropB1(String propB1) {
        super.setPropB1(propB1);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public MyDerivedType setPropBH1(String propBH1) {
        super.setPropBH1(propBH1);
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

    public static MyDerivedType fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    String propB1 = null;
                    String propBH1 = null;
                    String propD1 = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("propB1".equals(fieldName)) {
                            propB1 = reader.getStringValue();
                        } else if ("helper.propBH1".equals(fieldName)) {
                            propBH1 = reader.getStringValue();
                        } else if ("propD1".equals(fieldName)) {
                            propD1 = reader.getStringValue();
                        } else {
                            reader.skipChildren();
                        }
                    }
                    MyDerivedType deserializedValue = new MyDerivedType();
                    deserializedValue.setPropB1(propB1);
                    deserializedValue.setPropBH1(propBH1);
                    deserializedValue.setPropD1(propD1);

                    return deserializedValue;
                });
    }
}
