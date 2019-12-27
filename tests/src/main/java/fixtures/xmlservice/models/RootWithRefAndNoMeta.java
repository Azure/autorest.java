package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The RootWithRefAndNoMeta model.
 */
@JacksonXmlRootElement(localName = "RootWithRefAndNoMeta")
@Fluent
public final class RootWithRefAndNoMeta {
    /*
     * I am a complex type with no XML node
     */
    @JsonProperty(value = "RefToModel")
    private ComplexTypeNoMeta refToModel;

    /*
     * Something else (just to avoid flattening)
     */
    @JsonProperty(value = "Something")
    private String something;

    /**
     * Get the refToModel property: I am a complex type with no XML node.
     * 
     * @return the refToModel value.
     */
    public ComplexTypeNoMeta getRefToModel() {
        return this.refToModel;
    }

    /**
     * Set the refToModel property: I am a complex type with no XML node.
     * 
     * @param refToModel the refToModel value to set.
     * @return the RootWithRefAndNoMeta object itself.
     */
    public RootWithRefAndNoMeta setRefToModel(ComplexTypeNoMeta refToModel) {
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
     * @return the RootWithRefAndNoMeta object itself.
     */
    public RootWithRefAndNoMeta setSomething(String something) {
        this.something = something;
        return this;
    }

    public void validate() {
        if (getRefToModel() != null) {
            getRefToModel().validate();
        }
    }
}
