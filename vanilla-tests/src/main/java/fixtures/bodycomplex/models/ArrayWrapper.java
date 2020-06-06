package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The ArrayWrapper model. */
@Fluent
public final class ArrayWrapper {
    /*
     * The array property.
     */
    @JsonProperty(value = "array")
    private List<String> array;

    /**
     * Get the array property: The array property.
     *
     * @return the array value.
     */
    public List<String> getArray() {
        return this.array;
    }

    /**
     * Set the array property: The array property.
     *
     * @param array the array value to set.
     * @return the ArrayWrapper object itself.
     */
    public ArrayWrapper setArray(List<String> array) {
        this.array = array;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
