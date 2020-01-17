package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * The Blobs model.
 */
@JacksonXmlRootElement(localName = "Blobs")
@Fluent
public final class Blobs {
    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty("null")
    private List<BlobPrefix> blobPrefix = new ArrayList<>();

    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty("Blob")
    private List<Blob> blob = new ArrayList<>();

    /**
     * Get the blobPrefix property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the blobPrefix value.
     */
    public List<BlobPrefix> getBlobPrefix() {
        return this.blobPrefix;
    }

    /**
     * Set the blobPrefix property.
     * 
     * @param blobPrefix the blobPrefix value to set.
     * @return the Blobs object itself.
     */
    public Blobs setBlobPrefix(List<BlobPrefix> blobPrefix) {
        this.blobPrefix = blobPrefix;
        return this;
    }

    /**
     * Get the blob property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the blob value.
     */
    public List<Blob> getBlob() {
        return this.blob;
    }

    /**
     * Set the blob property.
     * 
     * @param blob the blob value to set.
     * @return the Blobs object itself.
     */
    public Blobs setBlob(List<Blob> blob) {
        this.blob = blob;
        return this;
    }

    public void validate() {
        if (getBlobPrefix() != null) {
            getBlobPrefix().forEach(e -> e.validate());
        }
        if (getBlob() != null) {
            getBlob().forEach(e -> e.validate());
        }
    }
}
