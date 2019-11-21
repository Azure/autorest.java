package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Goblinshark model.
 */
@Fluent
public final class Goblinshark extends Shark {
    /*
     * The jawsize property.
     */
    @JsonProperty(value = "jawsize")
    private int jawsize;

    /*
     * The color property.
     */
    @JsonProperty(value = "color")
    private GoblinSharkColor color;

    /**
     * Get the jawsize property: The jawsize property.
     * 
     * @return the jawsize value.
     */
    public int getJawsize() {
        return this.jawsize;
    }

    /**
     * Set the jawsize property: The jawsize property.
     * 
     * @param jawsize the jawsize value to set.
     * @return the Goblinshark object itself.
     */
    public Goblinshark setJawsize(int jawsize) {
        this.jawsize = jawsize;
        return this;
    }

    /**
     * Get the color property: The color property.
     * 
     * @return the color value.
     */
    public GoblinSharkColor getColor() {
        return this.color;
    }

    /**
     * Set the color property: The color property.
     * 
     * @param color the color value to set.
     * @return the Goblinshark object itself.
     */
    public Goblinshark setColor(GoblinSharkColor color) {
        this.color = color;
        return this;
    }
}
