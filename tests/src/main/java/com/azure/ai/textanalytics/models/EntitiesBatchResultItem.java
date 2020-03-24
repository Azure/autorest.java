package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The EntitiesBatchResultItem model.
 */
@Fluent
public final class EntitiesBatchResultItem {
    /*
     * Unique, non-empty document identifier.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * Recognized entities in the document.
     */
    @JsonProperty(value = "entities", access = JsonProperty.Access.WRITE_ONLY)
    private List<EntityRecord> entities;

    /*
     * (Optional) if showStats=true was specified in the request this field
     * will contain information about the document payload.
     */
    @JsonProperty(value = "statistics")
    private DocumentStatistics statistics;

    /**
     * Get the id property: Unique, non-empty document identifier.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Unique, non-empty document identifier.
     * 
     * @param id the id value to set.
     * @return the EntitiesBatchResultItem object itself.
     */
    public EntitiesBatchResultItem setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the entities property: Recognized entities in the document.
     * 
     * @return the entities value.
     */
    public List<EntityRecord> getEntities() {
        return this.entities;
    }

    /**
     * Get the statistics property: (Optional) if showStats=true was specified
     * in the request this field will contain information about the document
     * payload.
     * 
     * @return the statistics value.
     */
    public DocumentStatistics getStatistics() {
        return this.statistics;
    }

    /**
     * Set the statistics property: (Optional) if showStats=true was specified
     * in the request this field will contain information about the document
     * payload.
     * 
     * @param statistics the statistics value to set.
     * @return the EntitiesBatchResultItem object itself.
     */
    public EntitiesBatchResultItem setStatistics(DocumentStatistics statistics) {
        this.statistics = statistics;
        return this;
    }

    public void validate() {
        if (getEntities() != null) {
            getEntities().forEach(e -> e.validate());
        }
        if (getStatistics() != null) {
            getStatistics().validate();
        }
    }
}
