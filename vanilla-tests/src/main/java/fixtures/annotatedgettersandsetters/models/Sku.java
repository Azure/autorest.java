package fixtures.annotatedgettersandsetters.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/** SKU details. */
@Fluent
public final class Sku {
    /*
     * SKU family name
     */
    @JsonProperty(value = "family", required = true)
    private SkuFamily family = SkuFamily.A;

    /*
     * SKU name to specify whether the key vault is a standard vault or a
     * premium vault.
     */
    @JsonProperty(value = "name", required = true)
    private SkuName name = SkuName.STANDARD;

    /*
     * Property to specify whether Azure Virtual Machines are permitted to
     * retrieve certificates stored as secrets from the key vault.
     */
    @JsonProperty(value = "enabledForDeployment")
    private Boolean enabledForDeployment = true;

    /*
     * softDelete data retention days. It accepts >=7 and <=90.
     */
    @JsonProperty(value = "softDeleteRetentionInDays")
    private Integer softDeleteRetentionInDays = 90;

    /*
     * test string description.
     */
    @JsonProperty(value = "testString", access = JsonProperty.Access.WRITE_ONLY)
    private String testString = "test string";

    /**
     * Get the family property: SKU family name.
     *
     * @return the family value.
     */
    @JsonGetter("family")
    public SkuFamily getFamily() {
        return this.family;
    }

    /**
     * Set the family property: SKU family name.
     *
     * @param family the family value to set.
     * @return the Sku object itself.
     */
    @JsonSetter("family")
    public Sku setFamily(SkuFamily family) {
        this.family = family;
        return this;
    }

    /**
     * Get the name property: SKU name to specify whether the key vault is a standard vault or a premium vault.
     *
     * @return the name value.
     */
    @JsonGetter("name")
    public SkuName getName() {
        return this.name;
    }

    /**
     * Set the name property: SKU name to specify whether the key vault is a standard vault or a premium vault.
     *
     * @param name the name value to set.
     * @return the Sku object itself.
     */
    @JsonSetter("name")
    public Sku setName(SkuName name) {
        this.name = name;
        return this;
    }

    /**
     * Get the enabledForDeployment property: Property to specify whether Azure Virtual Machines are permitted to
     * retrieve certificates stored as secrets from the key vault.
     *
     * @return the enabledForDeployment value.
     */
    @JsonGetter("enabledForDeployment")
    public Boolean isEnabledForDeployment() {
        return this.enabledForDeployment;
    }

    /**
     * Set the enabledForDeployment property: Property to specify whether Azure Virtual Machines are permitted to
     * retrieve certificates stored as secrets from the key vault.
     *
     * @param enabledForDeployment the enabledForDeployment value to set.
     * @return the Sku object itself.
     */
    @JsonSetter("enabledForDeployment")
    public Sku setEnabledForDeployment(Boolean enabledForDeployment) {
        this.enabledForDeployment = enabledForDeployment;
        return this;
    }

    /**
     * Get the softDeleteRetentionInDays property: softDelete data retention days. It accepts &gt;=7 and &lt;=90.
     *
     * @return the softDeleteRetentionInDays value.
     */
    @JsonGetter("softDeleteRetentionInDays")
    public Integer getSoftDeleteRetentionInDays() {
        return this.softDeleteRetentionInDays;
    }

    /**
     * Set the softDeleteRetentionInDays property: softDelete data retention days. It accepts &gt;=7 and &lt;=90.
     *
     * @param softDeleteRetentionInDays the softDeleteRetentionInDays value to set.
     * @return the Sku object itself.
     */
    @JsonSetter("softDeleteRetentionInDays")
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
}
