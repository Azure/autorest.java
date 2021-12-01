package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/** A barrel of apples. */
@JacksonXmlRootElement(localName = "AppleBarrel")
@Fluent
public final class AppleBarrel {
    /*
     * The GoodApples property.
     */
    @JacksonXmlElementWrapper(localName = "GoodApples")
    private List<String> goodApples = new ArrayList<>();

    /*
     * The BadApples property.
     */
    @JacksonXmlElementWrapper(localName = "BadApples")
    private List<String> badApples = new ArrayList<>();

    /**
     * Get the goodApples property: The GoodApples property.
     *
     * @return the goodApples value.
     */
    public List<String> getGoodApples() {
        return this.goodApples;
    }

    /**
     * Set the goodApples property: The GoodApples property.
     *
     * @param goodApples the goodApples value to set.
     * @return the AppleBarrel object itself.
     */
    public AppleBarrel setGoodApples(List<String> goodApples) {
        this.goodApples = goodApples;
        return this;
    }

    /**
     * Get the badApples property: The BadApples property.
     *
     * @return the badApples value.
     */
    public List<String> getBadApples() {
        return this.badApples;
    }

    /**
     * Set the badApples property: The BadApples property.
     *
     * @param badApples the badApples value to set.
     * @return the AppleBarrel object itself.
     */
    public AppleBarrel setBadApples(List<String> badApples) {
        this.badApples = badApples;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
