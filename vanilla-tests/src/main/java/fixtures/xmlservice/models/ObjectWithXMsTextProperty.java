package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/** Contans property. */
@JacksonXmlRootElement(localName = "Data")
@Fluent
public final class ObjectWithXMsTextProperty {
    /*
     * Returned value should be 'english'
     */
    @JacksonXmlProperty(localName = "language", isAttribute = true)
    private String language;

    /*
     * Returned value should be 'I am text'
     */
    @JacksonXmlText private String content;

    /**
     * Get the language property: Returned value should be 'english'.
     *
     * @return the language value.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Set the language property: Returned value should be 'english'.
     *
     * @param language the language value to set.
     * @return the ObjectWithXMsTextProperty object itself.
     */
    public ObjectWithXMsTextProperty setLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * Get the content property: Returned value should be 'I am text'.
     *
     * @return the content value.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Set the content property: Returned value should be 'I am text'.
     *
     * @param content the content value to set.
     * @return the ObjectWithXMsTextProperty object itself.
     */
    public ObjectWithXMsTextProperty setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
