// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armstreamstyleserialization.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The second level model in polymorphic multiple levels inheritance which contains references to other polymorphic
 * instances.
 */
@Fluent
public final class SalmonInner extends FishInner {
    /*
     * Discriminator property for Fish.
     */
    private String kind = "salmon";

    /*
     * The friends property.
     */
    private List<FishInner> friends;

    /*
     * The hate property.
     */
    private Map<String, FishInner> hate;

    /*
     * The partner property.
     */
    private FishInner partner;

    /*
     * The dna property.
     */
    private String dna;

    /*
     * The properties property.
     */
    private FishProperties innerProperties = new FishProperties();

    /*
     * The anotherProperties property.
     */
    private AnotherFishProperties innerAnotherProperties = new AnotherFishProperties();

    /**
     * Creates an instance of SalmonInner class.
     */
    public SalmonInner() {
    }

    /**
     * Get the kind property: Discriminator property for Fish.
     * 
     * @return the kind value.
     */
    @Override
    public String kind() {
        return this.kind;
    }

    /**
     * Get the friends property: The friends property.
     * 
     * @return the friends value.
     */
    public List<FishInner> friends() {
        return this.friends;
    }

    /**
     * Set the friends property: The friends property.
     * 
     * @param friends the friends value to set.
     * @return the SalmonInner object itself.
     */
    public SalmonInner withFriends(List<FishInner> friends) {
        this.friends = friends;
        return this;
    }

    /**
     * Get the hate property: The hate property.
     * 
     * @return the hate value.
     */
    public Map<String, FishInner> hate() {
        return this.hate;
    }

    /**
     * Set the hate property: The hate property.
     * 
     * @param hate the hate value to set.
     * @return the SalmonInner object itself.
     */
    public SalmonInner withHate(Map<String, FishInner> hate) {
        this.hate = hate;
        return this;
    }

    /**
     * Get the partner property: The partner property.
     * 
     * @return the partner value.
     */
    public FishInner partner() {
        return this.partner;
    }

    /**
     * Set the partner property: The partner property.
     * 
     * @param partner the partner value to set.
     * @return the SalmonInner object itself.
     */
    public SalmonInner withPartner(FishInner partner) {
        this.partner = partner;
        return this;
    }

    /**
     * Get the dna property: The dna property.
     * 
     * @return the dna value.
     */
    @Override
    public String dna() {
        return this.dna;
    }

    /**
     * Get the innerProperties property: The properties property.
     * 
     * @return the innerProperties value.
     */
    private FishProperties innerProperties() {
        return this.innerProperties;
    }

    /**
     * Get the innerAnotherProperties property: The anotherProperties property.
     * 
     * @return the innerAnotherProperties value.
     */
    private AnotherFishProperties innerAnotherProperties() {
        return this.innerAnotherProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SalmonInner withAge(int age) {
        super.withAge(age);
        return this;
    }

    /**
     * Get the length property: The length property.
     * 
     * @return the length value.
     */
    public double length() {
        return this.innerProperties() == null ? 0.0 : this.innerProperties().length();
    }

    /**
     * Set the length property: The length property.
     * 
     * @param length the length value to set.
     * @return the SalmonInner object itself.
     */
    public SalmonInner withLength(double length) {
        if (this.innerProperties() == null) {
            this.innerProperties = new FishProperties();
        }
        this.innerProperties().withLength(length);
        return this;
    }

    /**
     * Get the patten property: The patten property.
     * 
     * @return the patten value.
     */
    public String patten() {
        return this.innerProperties() == null ? null : this.innerProperties().patten();
    }

    /**
     * Get the requiredString property: The requiredString property.
     * 
     * @return the requiredString value.
     */
    public String requiredString() {
        return this.innerProperties() == null ? null : this.innerProperties().requiredString();
    }

    /**
     * Set the requiredString property: The requiredString property.
     * 
     * @param requiredString the requiredString value to set.
     * @return the SalmonInner object itself.
     */
    public SalmonInner withRequiredString(String requiredString) {
        if (this.innerProperties() == null) {
            this.innerProperties = new FishProperties();
        }
        this.innerProperties().withRequiredString(requiredString);
        return this;
    }

    /**
     * Get the length property: The length property.
     * 
     * @return the length value.
     */
    public double lengthAnotherPropertiesLength() {
        return this.innerAnotherProperties() == null ? 0.0 : this.innerAnotherProperties().length();
    }

    /**
     * Set the length property: The length property.
     * 
     * @param length the length value to set.
     * @return the SalmonInner object itself.
     */
    public SalmonInner withLengthAnotherPropertiesLength(double length) {
        if (this.innerAnotherProperties() == null) {
            this.innerAnotherProperties = new AnotherFishProperties();
        }
        this.innerAnotherProperties().withLength(length);
        return this;
    }

    /**
     * Get the patten property: The patten property.
     * 
     * @return the patten value.
     */
    public String pattenAnotherPropertiesPatten() {
        return this.innerAnotherProperties() == null ? null : this.innerAnotherProperties().patten();
    }

    /**
     * Get the requiredString property: The requiredString property.
     * 
     * @return the requiredString value.
     */
    public String requiredStringAnotherPropertiesRequiredString() {
        return this.innerAnotherProperties() == null ? null : this.innerAnotherProperties().requiredString();
    }

    /**
     * Set the requiredString property: The requiredString property.
     * 
     * @param requiredString the requiredString value to set.
     * @return the SalmonInner object itself.
     */
    public SalmonInner withRequiredStringAnotherPropertiesRequiredString(String requiredString) {
        if (this.innerAnotherProperties() == null) {
            this.innerAnotherProperties = new AnotherFishProperties();
        }
        this.innerAnotherProperties().withRequiredString(requiredString);
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        if (friends() != null) {
            friends().forEach(e -> e.validate());
        }
        if (hate() != null) {
            hate().values().forEach(e -> {
                if (e != null) {
                    e.validate();
                }
            });
        }
        if (partner() != null) {
            partner().validate();
        }
        if (innerProperties() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Missing required property innerProperties in model SalmonInner"));
        } else {
            innerProperties().validate();
        }
        if (innerAnotherProperties() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Missing required property innerAnotherProperties in model SalmonInner"));
        } else {
            innerAnotherProperties().validate();
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(SalmonInner.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("age", age());
        jsonWriter.writeJsonField("properties", innerProperties());
        jsonWriter.writeJsonField("anotherProperties", innerAnotherProperties());
        jsonWriter.writeStringField("kind", this.kind);
        jsonWriter.writeArrayField("friends", this.friends, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeMapField("hate", this.hate, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeJsonField("partner", this.partner);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SalmonInner from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SalmonInner if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SalmonInner.
     */
    public static SalmonInner fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            SalmonInner deserializedSalmonInner = new SalmonInner();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("age".equals(fieldName)) {
                    deserializedSalmonInner.withAge(reader.getInt());
                } else if ("dna".equals(fieldName)) {
                    deserializedSalmonInner.dna = reader.getString();
                } else if ("properties".equals(fieldName)) {
                    deserializedSalmonInner.innerProperties = FishProperties.fromJson(reader);
                } else if ("anotherProperties".equals(fieldName)) {
                    deserializedSalmonInner.innerAnotherProperties = AnotherFishProperties.fromJson(reader);
                } else if ("kind".equals(fieldName)) {
                    deserializedSalmonInner.kind = reader.getString();
                } else if ("friends".equals(fieldName)) {
                    List<FishInner> friends = reader.readArray(reader1 -> FishInner.fromJson(reader1));
                    deserializedSalmonInner.friends = friends;
                } else if ("hate".equals(fieldName)) {
                    Map<String, FishInner> hate = reader.readMap(reader1 -> FishInner.fromJson(reader1));
                    deserializedSalmonInner.hate = hate;
                } else if ("partner".equals(fieldName)) {
                    deserializedSalmonInner.partner = FishInner.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedSalmonInner;
        });
    }
}
