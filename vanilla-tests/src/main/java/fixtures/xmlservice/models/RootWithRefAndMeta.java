package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/** The RootWithRefAndMeta model. */
@JacksonXmlRootElement(localName = "RootWithRefAndMeta")
@Fluent
public final class RootWithRefAndMeta {
    /*
     * XML will use XMLComplexTypeWithMeta
     */
    @JsonProperty(value = "XMLComplexTypeWithMeta")
    private ComplexTypeWithMeta refToModel;

    /*
     * Something else (just to avoid flattening)
     */
    @JsonProperty(value = "Something")
    private String something;

    /**
     * Get the refToModel property: XML will use XMLComplexTypeWithMeta.
     *
     * @return the refToModel value.
     */
    public ComplexTypeWithMeta getRefToModel() {
        return this.refToModel;
    }

    /**
     * Set the refToModel property: XML will use XMLComplexTypeWithMeta.
     *
     * @param refToModel the refToModel value to set.
     * @return the RootWithRefAndMeta object itself.
     */
    public RootWithRefAndMeta setRefToModel(ComplexTypeWithMeta refToModel) {
        this.refToModel = refToModel;
        return this;
    }

    /**
     * Get the something property: Something else (just to avoid flattening).
     *
     * @return the something value.
     */
    public String getSomething() {
        return this.something;
    }

    /**
     * Set the something property: Something else (just to avoid flattening).
     *
     * @param something the something value to set.
     * @return the RootWithRefAndMeta object itself.
     */
    public RootWithRefAndMeta setSomething(String something) {
        this.something = something;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getRefToModel() != null) {
            getRefToModel().validate();
        }
    }
}
