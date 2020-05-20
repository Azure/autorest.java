package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/** The DictionaryWrapper model. */
@Fluent
public final class DictionaryWrapper {
    /*
     * Dictionary of <string>
     */
    @JsonProperty(value = "defaultProgram")
    private Map<String, String> defaultProgram;

    /**
     * Get the defaultProgram property: Dictionary of &lt;string&gt;.
     *
     * @return the defaultProgram value.
     */
    public Map<String, String> getDefaultProgram() {
        return this.defaultProgram;
    }

    /**
     * Set the defaultProgram property: Dictionary of &lt;string&gt;.
     *
     * @param defaultProgram the defaultProgram value to set.
     * @return the DictionaryWrapper object itself.
     */
    public DictionaryWrapper setDefaultProgram(Map<String, String> defaultProgram) {
        this.defaultProgram = defaultProgram;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
