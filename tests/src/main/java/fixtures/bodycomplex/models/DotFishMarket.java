package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The DotFishMarket model.
 */
@Fluent
public final class DotFishMarket {
    /*
     * The sampleSalmon property.
     */
    @JsonProperty(value = "sampleSalmon")
    private DotSalmon sampleSalmon;

    /*
     * The salmons property.
     */
    @JsonProperty(value = "salmons")
    private List<DotSalmon> salmons;

    /*
     * The sampleFish property.
     */
    @JsonProperty(value = "sampleFish")
    private DotFish sampleFish;

    /*
     * The fishes property.
     */
    @JsonProperty(value = "fishes")
    private List<DotFish> fishes;

    /**
     * Get the sampleSalmon property: The sampleSalmon property.
     * 
     * @return the sampleSalmon value.
     */
    public DotSalmon getSampleSalmon() {
        return this.sampleSalmon;
    }

    /**
     * Set the sampleSalmon property: The sampleSalmon property.
     * 
     * @param sampleSalmon the sampleSalmon value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setSampleSalmon(DotSalmon sampleSalmon) {
        this.sampleSalmon = sampleSalmon;
        return this;
    }

    /**
     * Get the salmons property: The salmons property.
     * 
     * @return the salmons value.
     */
    public List<DotSalmon> getSalmons() {
        return this.salmons;
    }

    /**
     * Set the salmons property: The salmons property.
     * 
     * @param salmons the salmons value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setSalmons(List<DotSalmon> salmons) {
        this.salmons = salmons;
        return this;
    }

    /**
     * Get the sampleFish property: The sampleFish property.
     * 
     * @return the sampleFish value.
     */
    public DotFish getSampleFish() {
        return this.sampleFish;
    }

    /**
     * Set the sampleFish property: The sampleFish property.
     * 
     * @param sampleFish the sampleFish value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setSampleFish(DotFish sampleFish) {
        this.sampleFish = sampleFish;
        return this;
    }

    /**
     * Get the fishes property: The fishes property.
     * 
     * @return the fishes value.
     */
    public List<DotFish> getFishes() {
        return this.fishes;
    }

    /**
     * Set the fishes property: The fishes property.
     * 
     * @param fishes the fishes value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setFishes(List<DotFish> fishes) {
        this.fishes = fishes;
        return this;
    }
}
