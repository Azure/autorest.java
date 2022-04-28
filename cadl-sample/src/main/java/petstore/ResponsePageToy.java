package petstore;

import com.azure.core.annotation.Fluent;
import java.util.List;

/** */
@Fluent
public final class ResponsePageToy {
    /*
     * null
     */
    private List<Toy> items;

    /*
     * null
     */
    private String nextLink;

    /**
     * Get the items property: null.
     *
     * @return the items value.
     */
    public List<Toy> getItems() {
        return this.items;
    }

    /**
     * Set the items property.
     *
     * @param items the items value to set.
     * @return the ResponsePageToy object itself.
     */
    public ResponsePageToy setItems(List<Toy> items) {
        this.items = items;
        return this;
    }

    /**
     * Get the nextLink property: null.
     *
     * @return the nextLink value.
     */
    public String getNextLink() {
        return this.nextLink;
    }

    /**
     * Set the nextLink property.
     *
     * @param nextLink the nextLink value to set.
     * @return the ResponsePageToy object itself.
     */
    public ResponsePageToy setNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }
}
