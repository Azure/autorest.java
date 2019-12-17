package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
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
@Fluent
public class MyBaseType {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "propB1")
    private String propB1;

    /*
     * MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA
     */
    @JsonProperty(value = "helper")
    private MyBaseHelperType helper;

    /**
     * Get the propB1 property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the propB1 value.
     */
    public String getPropB1() {
        return this.propB1;
    }

    /**
     * Set the propB1 property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param propB1 the propB1 value to set.
     * @return the MyBaseType object itself.
     */
    public MyBaseType setPropB1(String propB1) {
        this.propB1 = propB1;
        return this;
    }

    /**
     * Get the helper property: MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * 
     * @return the helper value.
     */
    public MyBaseHelperType getHelper() {
        return this.helper;
    }

    /**
     * Set the helper property: MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * 
     * @param helper the helper value to set.
     * @return the MyBaseType object itself.
     */
    public MyBaseType setHelper(MyBaseHelperType helper) {
        this.helper = helper;
        return this;
    }

    public void validate() {
        if (getHelper() != null) {
            getHelper().validate();
        }
    }
}
