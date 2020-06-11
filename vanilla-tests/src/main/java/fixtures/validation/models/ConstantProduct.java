package fixtures.validation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ConstantProduct model. */
@Fluent
public final class ConstantProduct {
    /*
     * Constant string
     */
    @JsonProperty(value = "constProperty", required = true)
    private String constProperty;

    /*
     * Constant string2
     */
    @JsonProperty(value = "constProperty2", required = true)
    private String constProperty2;

    /** Creates an instance of ConstantProduct class. */
    public ConstantProduct() {
        constProperty = "constant";
        constProperty2 = "constant2";
    }

    /**
     * Get the constProperty property: Constant string.
     *
     * @return the constProperty value.
     */
    public String getConstProperty() {
        return this.constProperty;
    }

    /**
     * Set the constProperty property: Constant string.
     *
     * @param constProperty the constProperty value to set.
     * @return the ConstantProduct object itself.
     */
    public ConstantProduct setConstProperty(String constProperty) {
        this.constProperty = constProperty;
        return this;
    }

    /**
     * Get the constProperty2 property: Constant string2.
     *
     * @return the constProperty2 value.
     */
    public String getConstProperty2() {
        return this.constProperty2;
    }

    /**
     * Set the constProperty2 property: Constant string2.
     *
     * @param constProperty2 the constProperty2 value to set.
     * @return the ConstantProduct object itself.
     */
    public ConstantProduct setConstProperty2(String constProperty2) {
        this.constProperty2 = constProperty2;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
