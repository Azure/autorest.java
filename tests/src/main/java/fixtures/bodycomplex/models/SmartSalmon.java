package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The SmartSalmon model.
 */
@Fluent
public final class SmartSalmon extends Salmon {
    /*
     * The college_degree property.
     */
    @JsonProperty(value = "college_degree")
    private String collegeDegree;

    /**
     * Get the collegeDegree property: The college_degree property.
     * 
     * @return the collegeDegree value.
     */
    public String getCollegeDegree() {
        return this.collegeDegree;
    }

    /**
     * Set the collegeDegree property: The college_degree property.
     * 
     * @param collegeDegree the collegeDegree value to set.
     * @return the SmartSalmon object itself.
     */
    public SmartSalmon setCollegeDegree(String collegeDegree) {
        this.collegeDegree = collegeDegree;
        return this;
    }
}
