// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.annotatedgettersandsetters.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * SKU details.
 */
@Fluent
public final class Sku implements JsonSerializable<Sku> {
    /*
     * SKU family name
     */
    private SkuFamily family = SkuFamily.A;

    /*
     * SKU name to specify whether the key vault is a standard vault or a premium vault.
     */
    private SkuName name = SkuName.STANDARD;

    /*
     * Property to specify whether Azure Virtual Machines are permitted to retrieve certificates stored as secrets from the key vault.
     */
    private Boolean enabledForDeployment = true;

    /*
     * softDelete data retention days. It accepts >=7 and <=90.
     */
    private Integer softDeleteRetentionInDays = 90;

    /*
     * test string description.
     */
    private String testString = "test string";

    /**
     * Creates an instance of Sku class.
     */
    public Sku() {
    }

    /**
     * Get the family property: SKU family name.
     * 
     * @return the family value.
     */
    public SkuFamily getFamily() {
        return this.family;
    }

    /**
     * Set the family property: SKU family name.
     * 
     * @param family the family value to set.
     * @return the Sku object itself.
     */
    public Sku setFamily(SkuFamily family) {
        this.family = family;
        return this;
    }

    /**
     * Get the name property: SKU name to specify whether the key vault is a standard vault or a premium vault.
     * 
     * @return the name value.
     */
    public SkuName getName() {
        return this.name;
    }

    /**
     * Set the name property: SKU name to specify whether the key vault is a standard vault or a premium vault.
     * 
     * @param name the name value to set.
     * @return the Sku object itself.
     */
    public Sku setName(SkuName name) {
        this.name = name;
        return this;
    }

    /**
     * Get the enabledForDeployment property: Property to specify whether Azure Virtual Machines are permitted to retrieve certificates stored as secrets from the key vault.
     * 
     * @return the enabledForDeployment value.
     */
    public Boolean isEnabledForDeployment() {
        return this.enabledForDeployment;
    }

    /**
     * Set the enabledForDeployment property: Property to specify whether Azure Virtual Machines are permitted to retrieve certificates stored as secrets from the key vault.
     * 
     * @param enabledForDeployment the enabledForDeployment value to set.
     * @return the Sku object itself.
     */
    public Sku setEnabledForDeployment(Boolean enabledForDeployment) {
        this.enabledForDeployment = enabledForDeployment;
        return this;
    }

    /**
     * Get the softDeleteRetentionInDays property: softDelete data retention days. It accepts &gt;=7 and &lt;=90.
     * 
     * @return the softDeleteRetentionInDays value.
     */
    public Integer getSoftDeleteRetentionInDays() {
        return this.softDeleteRetentionInDays;
    }

    /**
     * Set the softDeleteRetentionInDays property: softDelete data retention days. It accepts &gt;=7 and &lt;=90.
     * 
     * @param softDeleteRetentionInDays the softDeleteRetentionInDays value to set.
     * @return the Sku object itself.
     */
    public Sku setSoftDeleteRetentionInDays(Integer softDeleteRetentionInDays) {
        this.softDeleteRetentionInDays = softDeleteRetentionInDays;
        return this;
    }

    /**
     * Get the testString property: test string description.
     * 
     * @return the testString value.
     */
    public String getTestString() {
        return this.testString;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getFamily() == null) {
            throw new IllegalArgumentException("Missing required property family in model Sku");
        }
        if (getName() == null) {
            throw new IllegalArgumentException("Missing required property name in model Sku");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("family", this.family == null ? null : this.family.toString());
        jsonWriter.writeStringField("name", this.name == null ? null : this.name.toString());
        jsonWriter.writeBooleanField("enabledForDeployment", this.enabledForDeployment);
        jsonWriter.writeNumberField("softDeleteRetentionInDays", this.softDeleteRetentionInDays);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Sku from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Sku if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Sku.
     */
    public static Sku fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Sku deserializedSku = new Sku();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("family".equals(fieldName)) {
                    deserializedSku.family = SkuFamily.fromString(reader.getString());
                } else if ("name".equals(fieldName)) {
                    deserializedSku.name = SkuName.fromString(reader.getString());
                } else if ("enabledForDeployment".equals(fieldName)) {
                    deserializedSku.enabledForDeployment = reader.getNullable(JsonReader::getBoolean);
                } else if ("softDeleteRetentionInDays".equals(fieldName)) {
                    deserializedSku.softDeleteRetentionInDays = reader.getNullable(JsonReader::getInt);
                } else if ("testString".equals(fieldName)) {
                    deserializedSku.testString = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedSku;
        });
    }
}
