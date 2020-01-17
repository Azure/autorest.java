package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The ArrayWrapper model.
 */
@Fluent
public final class ArrayWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "array")
    private List<String> array;

    /**
     * Get the array property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the array value.
     */
    public List<String> getArray() {
        return this.array;
    }

    /**
     * Set the array property.
     * 
     * @param array the array value to set.
     * @return the ArrayWrapper object itself.
     */
    public ArrayWrapper setArray(List<String> array) {
        this.array = array;
        return this;
    }

    public void validate() {
    }
}
