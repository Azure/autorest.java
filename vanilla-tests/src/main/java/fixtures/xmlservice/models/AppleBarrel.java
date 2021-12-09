package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/** A barrel of apples. */
@JacksonXmlRootElement(localName = "AppleBarrel")
@Fluent
public final class AppleBarrel {
    private static final class GoodApplesWrapper {
        @JacksonXmlProperty(localName = "Apple")
        private final List<String> items;

        @JsonCreator
        private GoodApplesWrapper(@JacksonXmlProperty(localName = "Apple") List<String> items) {
            this.items = items;
        }
    }

    /*
     * The GoodApples property.
     */
    @JsonProperty(value = "GoodApples")
    private GoodApplesWrapper goodApples;

    private static final class BadApplesWrapper {
        @JacksonXmlProperty(localName = "Apple")
        private final List<String> items;

        @JsonCreator
        private BadApplesWrapper(@JacksonXmlProperty(localName = "Apple") List<String> items) {
            this.items = items;
        }
    }

    /*
     * The BadApples property.
     */
    @JsonProperty(value = "BadApples")
    private BadApplesWrapper badApples;

    /**
     * Get the goodApples property: The GoodApples property.
     *
     * @return the goodApples value.
     */
    public List<String> getGoodApples() {
        if (this.goodApples == null) {
            this.goodApples = new GoodApplesWrapper(new ArrayList<String>());
        }
        return this.goodApples.items;
    }

    /**
     * Set the goodApples property: The GoodApples property.
     *
     * @param goodApples the goodApples value to set.
     * @return the AppleBarrel object itself.
     */
    public AppleBarrel setGoodApples(List<String> goodApples) {
        this.goodApples = new GoodApplesWrapper(goodApples);
        return this;
    }

    /**
     * Get the badApples property: The BadApples property.
     *
     * @return the badApples value.
     */
    public List<String> getBadApples() {
        if (this.badApples == null) {
            this.badApples = new BadApplesWrapper(new ArrayList<String>());
        }
        return this.badApples.items;
    }

    /**
     * Set the badApples property: The BadApples property.
     *
     * @param badApples the badApples value to set.
     * @return the AppleBarrel object itself.
     */
    public AppleBarrel setBadApples(List<String> badApples) {
        this.badApples = new BadApplesWrapper(badApples);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
