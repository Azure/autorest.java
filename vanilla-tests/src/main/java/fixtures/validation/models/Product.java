package fixtures.validation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The Product model. */
@Fluent
public final class Product {
    /*
     * Non required array of unique items from 0 to 6 elements.
     */
    @JsonProperty(value = "display_names")
    private List<String> displayNames;

    /*
     * Non required int betwen 0 and 100 exclusive.
     */
    @JsonProperty(value = "capacity")
    private Integer capacity;

    /*
     * Image URL representing the product.
     */
    @JsonProperty(value = "image")
    private String image;

    /*
     * The product documentation.
     */
    @JsonProperty(value = "child", required = true)
    private ChildProduct child;

    /*
     * The product documentation.
     */
    @JsonProperty(value = "constChild", required = true)
    private ConstantProduct constChild;

    /*
     * Constant int
     */
    @JsonProperty(value = "constInt", required = true)
    private int constInt;

    /*
     * Constant string
     */
    @JsonProperty(value = "constString", required = true)
    private String constString;

    /*
     * Constant string as Enum
     */
    @JsonProperty(value = "constStringAsEnum")
    private String constStringAsEnum;

    /** Creates an instance of Product class. */
    public Product() {
        constInt = 0;
        constString = "constant";
        constStringAsEnum = "constant_string_as_enum";
    }

    /**
     * Get the displayNames property: Non required array of unique items from 0 to 6 elements.
     *
     * @return the displayNames value.
     */
    public List<String> getDisplayNames() {
        return this.displayNames;
    }

    /**
     * Set the displayNames property: Non required array of unique items from 0 to 6 elements.
     *
     * @param displayNames the displayNames value to set.
     * @return the Product object itself.
     */
    public Product setDisplayNames(List<String> displayNames) {
        this.displayNames = displayNames;
        return this;
    }

    /**
     * Get the capacity property: Non required int betwen 0 and 100 exclusive.
     *
     * @return the capacity value.
     */
    public Integer getCapacity() {
        return this.capacity;
    }

    /**
     * Set the capacity property: Non required int betwen 0 and 100 exclusive.
     *
     * @param capacity the capacity value to set.
     * @return the Product object itself.
     */
    public Product setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    /**
     * Get the image property: Image URL representing the product.
     *
     * @return the image value.
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Set the image property: Image URL representing the product.
     *
     * @param image the image value to set.
     * @return the Product object itself.
     */
    public Product setImage(String image) {
        this.image = image;
        return this;
    }

    /**
     * Get the child property: The product documentation.
     *
     * @return the child value.
     */
    public ChildProduct getChild() {
        return this.child;
    }

    /**
     * Set the child property: The product documentation.
     *
     * @param child the child value to set.
     * @return the Product object itself.
     */
    public Product setChild(ChildProduct child) {
        this.child = child;
        return this;
    }

    /**
     * Get the constChild property: The product documentation.
     *
     * @return the constChild value.
     */
    public ConstantProduct getConstChild() {
        return this.constChild;
    }

    /**
     * Set the constChild property: The product documentation.
     *
     * @param constChild the constChild value to set.
     * @return the Product object itself.
     */
    public Product setConstChild(ConstantProduct constChild) {
        this.constChild = constChild;
        return this;
    }

    /**
     * Get the constInt property: Constant int.
     *
     * @return the constInt value.
     */
    public int getConstInt() {
        return this.constInt;
    }

    /**
     * Set the constInt property: Constant int.
     *
     * @param constInt the constInt value to set.
     * @return the Product object itself.
     */
    public Product setConstInt(int constInt) {
        this.constInt = constInt;
        return this;
    }

    /**
     * Get the constString property: Constant string.
     *
     * @return the constString value.
     */
    public String getConstString() {
        return this.constString;
    }

    /**
     * Set the constString property: Constant string.
     *
     * @param constString the constString value to set.
     * @return the Product object itself.
     */
    public Product setConstString(String constString) {
        this.constString = constString;
        return this;
    }

    /**
     * Get the constStringAsEnum property: Constant string as Enum.
     *
     * @return the constStringAsEnum value.
     */
    public String getConstStringAsEnum() {
        return this.constStringAsEnum;
    }

    /**
     * Set the constStringAsEnum property: Constant string as Enum.
     *
     * @param constStringAsEnum the constStringAsEnum value to set.
     * @return the Product object itself.
     */
    public Product setConstStringAsEnum(String constStringAsEnum) {
        this.constStringAsEnum = constStringAsEnum;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getChild() == null) {
            throw new IllegalArgumentException("Missing required property child in model Product");
        } else {
            getChild().validate();
        }
        if (getConstChild() == null) {
            throw new IllegalArgumentException("Missing required property constChild in model Product");
        } else {
            getConstChild().validate();
        }
    }
}
