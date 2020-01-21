package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The ListBlobsResponse model.
 */
@JacksonXmlRootElement(localName = "EnumerationResults")
@Fluent
public final class ListBlobsResponse {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JacksonXmlProperty(localName = "ServiceEndpoint", isAttribute = true)
    private String serviceEndpoint;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JacksonXmlProperty(localName = "ContainerName", isAttribute = true)
    private String containerName;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Prefix", required = true)
    private String prefix;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Marker", required = true)
    private String marker;

    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "MaxResults", required = true)
    private int maxResults;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Delimiter", required = true)
    private String delimiter;

    /*
     * MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA
     */
    @JsonProperty(value = "Blobs", required = true)
    private Blobs blobs;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "NextMarker", required = true)
    private String nextMarker;

    /**
     * Get the serviceEndpoint property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the serviceEndpoint value.
     */
    public String getServiceEndpoint() {
        return this.serviceEndpoint;
    }

    /**
     * Set the serviceEndpoint property.
     * 
     * @param serviceEndpoint the serviceEndpoint value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
        return this;
    }

    /**
     * Get the containerName property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the containerName value.
     */
    public String getContainerName() {
        return this.containerName;
    }

    /**
     * Set the containerName property.
     * 
     * @param containerName the containerName value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setContainerName(String containerName) {
        this.containerName = containerName;
        return this;
    }

    /**
     * Get the prefix property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the prefix value.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Set the prefix property.
     * 
     * @param prefix the prefix value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Get the marker property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the marker value.
     */
    public String getMarker() {
        return this.marker;
    }

    /**
     * Set the marker property.
     * 
     * @param marker the marker value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setMarker(String marker) {
        this.marker = marker;
        return this;
    }

    /**
     * Get the maxResults property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @return the maxResults value.
     */
    public int getMaxResults() {
        return this.maxResults;
    }

    /**
     * Set the maxResults property.
     * 
     * @param maxResults the maxResults value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * Get the delimiter property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the delimiter value.
     */
    public String getDelimiter() {
        return this.delimiter;
    }

    /**
     * Set the delimiter property.
     * 
     * @param delimiter the delimiter value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    /**
     * Get the blobs property: MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * 
     * @return the blobs value.
     */
    public Blobs getBlobs() {
        return this.blobs;
    }

    /**
     * Set the blobs property.
     * 
     * @param blobs the blobs value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setBlobs(Blobs blobs) {
        this.blobs = blobs;
        return this;
    }

    /**
     * Get the nextMarker property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the nextMarker value.
     */
    public String getNextMarker() {
        return this.nextMarker;
    }

    /**
     * Set the nextMarker property.
     * 
     * @param nextMarker the nextMarker value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
        return this;
    }

    public void validate() {
        if (getServiceEndpoint() == null) {
            throw new IllegalArgumentException("Missing required property serviceEndpoint in model ListBlobsResponse");
        }
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
