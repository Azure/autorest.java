package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The EntityRecord model.
 */
@Fluent
public final class EntityRecord {
    /*
     * Entity formal name.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * List of instances this entity appears in the text.
     */
    @JsonProperty(value = "matches")
    private List<MatchRecord> matches;

    /*
     * Wikipedia language for which the WikipediaId and WikipediaUrl refers to.
     */
    @JsonProperty(value = "wikipediaLanguage")
    private String wikipediaLanguage;

    /*
     * Wikipedia unique identifier of the recognized entity.
     */
    @JsonProperty(value = "wikipediaId")
    private String wikipediaId;

    /*
     * URL for the entity's Wikipedia page.
     */
    @JsonProperty(value = "wikipediaUrl", access = JsonProperty.Access.WRITE_ONLY)
    private String wikipediaUrl;

    /*
     * Bing unique identifier of the recognized entity. Use in conjunction with
     * the Bing Entity Search API to fetch additional relevant information.
     */
    @JsonProperty(value = "bingId")
    private String bingId;

    /*
     * Entity type from Named Entity Recognition model
     */
    @JsonProperty(value = "type")
    private String type;

    /*
     * Entity sub type from Named Entity Recognition model
     */
    @JsonProperty(value = "subType")
    private String subType;

    /**
     * Get the name property: Entity formal name.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Entity formal name.
     * 
     * @param name the name value to set.
     * @return the EntityRecord object itself.
     */
    public EntityRecord setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the matches property: List of instances this entity appears in the
     * text.
     * 
     * @return the matches value.
     */
    public List<MatchRecord> getMatches() {
        return this.matches;
    }

    /**
     * Set the matches property: List of instances this entity appears in the
     * text.
     * 
     * @param matches the matches value to set.
     * @return the EntityRecord object itself.
     */
    public EntityRecord setMatches(List<MatchRecord> matches) {
        this.matches = matches;
        return this;
    }

    /**
     * Get the wikipediaLanguage property: Wikipedia language for which the
     * WikipediaId and WikipediaUrl refers to.
     * 
     * @return the wikipediaLanguage value.
     */
    public String getWikipediaLanguage() {
        return this.wikipediaLanguage;
    }

    /**
     * Set the wikipediaLanguage property: Wikipedia language for which the
     * WikipediaId and WikipediaUrl refers to.
     * 
     * @param wikipediaLanguage the wikipediaLanguage value to set.
     * @return the EntityRecord object itself.
     */
    public EntityRecord setWikipediaLanguage(String wikipediaLanguage) {
        this.wikipediaLanguage = wikipediaLanguage;
        return this;
    }

    /**
     * Get the wikipediaId property: Wikipedia unique identifier of the
     * recognized entity.
     * 
     * @return the wikipediaId value.
     */
    public String getWikipediaId() {
        return this.wikipediaId;
    }

    /**
     * Set the wikipediaId property: Wikipedia unique identifier of the
     * recognized entity.
     * 
     * @param wikipediaId the wikipediaId value to set.
     * @return the EntityRecord object itself.
     */
    public EntityRecord setWikipediaId(String wikipediaId) {
        this.wikipediaId = wikipediaId;
        return this;
    }

    /**
     * Get the wikipediaUrl property: URL for the entity's Wikipedia page.
     * 
     * @return the wikipediaUrl value.
     */
    public String getWikipediaUrl() {
        return this.wikipediaUrl;
    }

    /**
     * Get the bingId property: Bing unique identifier of the recognized
     * entity. Use in conjunction with the Bing Entity Search API to fetch
     * additional relevant information.
     * 
     * @return the bingId value.
     */
    public String getBingId() {
        return this.bingId;
    }

    /**
     * Set the bingId property: Bing unique identifier of the recognized
     * entity. Use in conjunction with the Bing Entity Search API to fetch
     * additional relevant information.
     * 
     * @param bingId the bingId value to set.
     * @return the EntityRecord object itself.
     */
    public EntityRecord setBingId(String bingId) {
        this.bingId = bingId;
        return this;
    }

    /**
     * Get the type property: Entity type from Named Entity Recognition model.
     * 
     * @return the type value.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Set the type property: Entity type from Named Entity Recognition model.
     * 
     * @param type the type value to set.
     * @return the EntityRecord object itself.
     */
    public EntityRecord setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get the subType property: Entity sub type from Named Entity Recognition
     * model.
     * 
     * @return the subType value.
     */
    public String getSubType() {
        return this.subType;
    }

    /**
     * Set the subType property: Entity sub type from Named Entity Recognition
     * model.
     * 
     * @param subType the subType value to set.
     * @return the EntityRecord object itself.
     */
    public EntityRecord setSubType(String subType) {
        this.subType = subType;
        return this;
    }

    public void validate() {
        if (getMatches() != null) {
            getMatches().forEach(e -> e.validate());
        }
    }
}
