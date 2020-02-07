package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The MyBaseType model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind", defaultImpl = MyBaseType.class)
@JsonTypeName("MyBaseType")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Kind1", value = MyDerivedType.class)
})
@JsonFlatten
@Fluent
public class MyBaseType {
    /*
     * The propB1 property.
     */
    @JsonProperty(value = "propB1")
    private String propB1;

    /*
     * The propBH1 property.
     */
    @JsonProperty(value = "helper.propBH1")
    private String propBh1;

    /**
     * Get the propB1 property: The propB1 property.
     * 
     * @return the propB1 value.
     */
    public String getPropB1() {
        return this.propB1;
    }

    /**
     * Set the propB1 property: The propB1 property.
     * 
     * @param propB1 the propB1 value to set.
     * @return the MyBaseType object itself.
     */
    public MyBaseType setPropB1(String propB1) {
        this.propB1 = propB1;
        return this;
    }

    /**
     * Get the propBh1 property: The propBH1 property.
     * 
     * @return the propBh1 value.
     */
    public String getPropBh1() {
        return this.propBh1;
    }

    /**
     * Set the propBh1 property: The propBH1 property.
     * 
     * @param propBh1 the propBh1 value to set.
     * @return the MyBaseType object itself.
     */
    public MyBaseType setPropBh1(String propBh1) {
        this.propBh1 = propBh1;
        return this;
    }

    public void validate() {
    }
}
