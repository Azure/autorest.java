package fixtures.xmlservice.implementation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fixtures.xmlservice.models.Banana;
import java.util.List;

/**
 * A wrapper around List&lt;Banana&gt; which provides top-level metadata for serialization.
 */
@JacksonXmlRootElement(localName = "bananas")
public final class BananasWrapper {
    @JacksonXmlProperty(localName = "banana")
    private final List<Banana> bananas;

    /**
     * Creates an instance of BananasWrapper.
     * 
     * @param bananas the list.
     */
    @JsonCreator
    public BananasWrapper(@JsonProperty("banana") List<Banana> bananas) {
        this.bananas = bananas;
    }

    /**
     * Get the List&lt;Banana&gt; contained in this wrapper.
     * 
     * @return the List&lt;Banana&gt;.
     */
    public List<Banana> items() {
        return bananas;
    }
}
