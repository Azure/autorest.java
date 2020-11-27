package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

/**
 * The Goblinshark model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype")
@JsonTypeName("goblin")
@Fluent
public final class Goblinshark extends Shark {
    /*
     * The jawsize property.
     */
    @JsonProperty(value = "jawsize")
    private Integer jawsize;

    /*
     * Colors possible
     */
    @JsonProperty(value = "color")
    private GoblinSharkColor color;

    /**
     * Get the jawsize property: The jawsize property.
     * 
     * @return the jawsize value.
     */
    public Integer getJawsize() {
        return this.jawsize;
    }

    /**
     * Set the jawsize property: The jawsize property.
     * 
     * @param jawsize the jawsize value to set.
     * @return the Goblinshark object itself.
     */
    public Goblinshark setJawsize(Integer jawsize) {
        this.jawsize = jawsize;
        return this;
    }

    /**
     * Get the color property: Colors possible.
     * 
     * @return the color value.
     */
    public GoblinSharkColor getColor() {
        return this.color;
    }

    /**
     * Set the color property: Colors possible.
     * 
     * @param color the color value to set.
     * @return the Goblinshark object itself.
     */
    public Goblinshark setColor(GoblinSharkColor color) {
        this.color = color;
        return this;
    }
}
