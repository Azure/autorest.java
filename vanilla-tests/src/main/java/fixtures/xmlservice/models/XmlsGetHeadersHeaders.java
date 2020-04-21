package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The XmlsGetHeadersHeaders model.
 */
@JacksonXmlRootElement(localName = "null")
@Fluent
public final class XmlsGetHeadersHeaders {
    /*
     * The Custom-Header property.
     */
    @JsonProperty(value = "Custom-Header")
    private String customHeader;

    /**
     * Get the customHeader property: The Custom-Header property.
     * 
     * @return the customHeader value.
     */
    public String getCustomHeader() {
        return this.customHeader;
    }

    /**
     * Set the customHeader property: The Custom-Header property.
     * 
     * @param customHeader the customHeader value to set.
     * @return the XmlsGetHeadersHeaders object itself.
     */
    public XmlsGetHeadersHeaders setCustomHeader(String customHeader) {
        this.customHeader = customHeader;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
