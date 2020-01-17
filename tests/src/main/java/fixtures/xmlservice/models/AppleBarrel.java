package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * The AppleBarrel model.
 */
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
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
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
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "BadApples")
    private BadApplesWrapper badApples;

    /**
     * Get the goodApples property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
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
     * Set the goodApples property.
     * 
     * @param goodApples the goodApples value to set.
     * @return the AppleBarrel object itself.
     */
    public AppleBarrel setGoodApples(List<String> goodApples) {
        this.goodApples = new GoodApplesWrapper(goodApples);
        return this;
    }

    /**
     * Get the badApples property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
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
     * Set the badApples property.
     * 
     * @param badApples the badApples value to set.
     * @return the AppleBarrel object itself.
     */
    public AppleBarrel setBadApples(List<String> badApples) {
        this.badApples = new BadApplesWrapper(badApples);
        return this;
    }

    public void validate() {
    }
}
