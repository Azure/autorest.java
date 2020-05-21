package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/** The ListBlobsResponse model. */
@JacksonXmlRootElement(localName = "EnumerationResults")
@Fluent
public final class ListBlobsResponse {
    /*
     * The ServiceEndpoint property.
     */
    @JacksonXmlProperty(localName = "ServiceEndpoint", isAttribute = true)
    private String serviceEndpoint;

    /*
     * The ContainerName property.
     */
    @JacksonXmlProperty(localName = "ContainerName", isAttribute = true)
    private String containerName;

    /*
     * The Prefix property.
     */
    @JsonProperty(value = "Prefix", required = true)
    private String prefix;

    /*
     * The Marker property.
     */
    @JsonProperty(value = "Marker", required = true)
    private String marker;

    /*
     * The MaxResults property.
     */
    @JsonProperty(value = "MaxResults", required = true)
    private int maxResults;

    /*
     * The Delimiter property.
     */
    @JsonProperty(value = "Delimiter", required = true)
    private String delimiter;

    /*
     * The Blobs property.
     */
    @JsonProperty(value = "Blobs", required = true)
    private Blobs blobs;

    /*
     * The NextMarker property.
     */
    @JsonProperty(value = "NextMarker", required = true)
    private String nextMarker;

    /**
     * Get the serviceEndpoint property: The ServiceEndpoint property.
     *
     * @return the serviceEndpoint value.
     */
    public String getServiceEndpoint() {
        return this.serviceEndpoint;
    }

    /**
     * Set the serviceEndpoint property: The ServiceEndpoint property.
     *
     * @param serviceEndpoint the serviceEndpoint value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
        return this;
    }

    /**
     * Get the containerName property: The ContainerName property.
     *
     * @return the containerName value.
     */
    public String getContainerName() {
        return this.containerName;
    }

    /**
     * Set the containerName property: The ContainerName property.
     *
     * @param containerName the containerName value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setContainerName(String containerName) {
        this.containerName = containerName;
        return this;
    }

    /**
     * Get the prefix property: The Prefix property.
     *
     * @return the prefix value.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Set the prefix property: The Prefix property.
     *
     * @param prefix the prefix value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Get the marker property: The Marker property.
     *
     * @return the marker value.
     */
    public String getMarker() {
        return this.marker;
    }

    /**
     * Set the marker property: The Marker property.
     *
     * @param marker the marker value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setMarker(String marker) {
        this.marker = marker;
        return this;
    }

    /**
     * Get the maxResults property: The MaxResults property.
     *
     * @return the maxResults value.
     */
    public int getMaxResults() {
        return this.maxResults;
    }

    /**
     * Set the maxResults property: The MaxResults property.
     *
     * @param maxResults the maxResults value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * Get the delimiter property: The Delimiter property.
     *
     * @return the delimiter value.
     */
    public String getDelimiter() {
        return this.delimiter;
    }

    /**
     * Set the delimiter property: The Delimiter property.
     *
     * @param delimiter the delimiter value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    /**
     * Get the blobs property: The Blobs property.
     *
     * @return the blobs value.
     */
    public Blobs getBlobs() {
        return this.blobs;
    }

    /**
     * Set the blobs property: The Blobs property.
     *
     * @param blobs the blobs value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setBlobs(Blobs blobs) {
        this.blobs = blobs;
        return this;
    }

    /**
     * Get the nextMarker property: The NextMarker property.
     *
     * @return the nextMarker value.
     */
    public String getNextMarker() {
        return this.nextMarker;
    }

    /**
     * Set the nextMarker property: The NextMarker property.
     *
     * @param nextMarker the nextMarker value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getContainerName() == null) {
            throw new IllegalArgumentException("Missing required property containerName in model ListBlobsResponse");
        }
        if (getPrefix() == null) {
            throw new IllegalArgumentException("Missing required property prefix in model ListBlobsResponse");
        }
        if (getMarker() == null) {
            throw new IllegalArgumentException("Missing required property marker in model ListBlobsResponse");
        }
        if (getDelimiter() == null) {
            throw new IllegalArgumentException("Missing required property delimiter in model ListBlobsResponse");
        }
        if (getBlobs() == null) {
            throw new IllegalArgumentException("Missing required property blobs in model ListBlobsResponse");
        } else {
            getBlobs().validate();
        }
        if (getNextMarker() == null) {
            throw new IllegalArgumentException("Missing required property nextMarker in model ListBlobsResponse");
        }
    }
}
