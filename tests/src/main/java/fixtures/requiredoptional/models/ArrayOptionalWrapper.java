package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The ArrayOptionalWrapper model.
 */
@Fluent
public final class ArrayOptionalWrapper {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private List<String> value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public List<String> getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the ArrayOptionalWrapper object itself.
     */
    public ArrayOptionalWrapper setValue(List<String> value) {
        this.value = value;
        return this;
    }

    public void validate() {
    }
}
