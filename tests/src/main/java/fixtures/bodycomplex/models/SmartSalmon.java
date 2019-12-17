package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The SmartSalmon model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype")
@JsonTypeName("smart_salmon")
@Fluent
public final class SmartSalmon extends Salmon {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "college_degree")
    private String collegeDegree;

    /**
     * Get the collegeDegree property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the collegeDegree value.
     */
    public String getCollegeDegree() {
        return this.collegeDegree;
    }

    /**
     * Set the collegeDegree property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param collegeDegree the collegeDegree value to set.
     * @return the SmartSalmon object itself.
     */
    public SmartSalmon setCollegeDegree(String collegeDegree) {
        this.collegeDegree = collegeDegree;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
    }
}
