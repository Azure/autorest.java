package petstore;

import com.azure.core.annotation.Fluent;
import java.util.List;

/** */
@Fluent
public final class ResponsePagePet {
    /*
     * null
     */
    private List<Pet> items;

    /*
     * null
     */
    private String nextLink;

    /**
     * Get the items property: null.
     *
     * @return the items value.
     */
    public List<Pet> getItems() {
        return this.items;
    }

    /**
     * Set the items property.
     *
     * @param items the items value to set.
     * @return the ResponsePagePet object itself.
     */
    public ResponsePagePet setItems(List<Pet> items) {
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
     * @return the ResponsePagePet object itself.
     */
    public ResponsePagePet setNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }
}
