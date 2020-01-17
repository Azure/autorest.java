package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * The ListContainersResponse model.
 */
@JacksonXmlRootElement(localName = "EnumerationResults")
@Fluent
public final class ListContainersResponse {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JacksonXmlProperty(localName = "ServiceEndpoint", isAttribute = true)
    private String serviceEndpoint;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Prefix", required = true)
    private String prefix;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Marker")
    private String marker;

    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "MaxResults", required = true)
    private int maxResults;

    private static final class ContainersWrapper {
        @JacksonXmlProperty(localName = "null")
        private final List<Container> items;

        @JsonCreator
        private ContainersWrapper(@JacksonXmlProperty(localName = "null") List<Container> items) {
            this.items = items;
        }
    }

    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "Containers")
    private ContainersWrapper containers;

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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setPrefix(String prefix) {
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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setMarker(String marker) {
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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * Get the containers property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the containers value.
     */
    public List<Container> getContainers() {
        if (this.containers == null) {
            this.containers = new ContainersWrapper(new ArrayList<Container>());
        }
        return this.containers.items;
    }

    /**
     * Set the containers property.
     * 
     * @param containers the containers value to set.
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setContainers(List<Container> containers) {
        this.containers = new ContainersWrapper(containers);
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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
        return this;
    }

    public void validate() {
        if (getServiceEndpoint() == null) {
            throw new IllegalArgumentException("Missing required property serviceEndpoint in model ListContainersResponse");
        }
        if (getPrefix() == null) {
            throw new IllegalArgumentException("Missing required property prefix in model ListContainersResponse");
        }
        if (getContainers() != null) {
            getContainers().forEach(e -> e.validate());
        }
        if (getNextMarker() == null) {
            throw new IllegalArgumentException("Missing required property nextMarker in model ListContainersResponse");
        }
    }
}
