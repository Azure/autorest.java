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
     * MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA
     */
    @JsonProperty(value = "sampleSalmon")
    private DotSalmon sampleSalmon;

    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "salmons")
    private List<DotSalmon> salmons;

    /*
     * MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA
     */
    @JsonProperty(value = "sampleFish")
    private DotFish sampleFish;

    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "fishes")
    private List<DotFish> fishes;

    /**
     * Get the sampleSalmon property: MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * 
     * @return the sampleSalmon value.
     */
    public DotSalmon getSampleSalmon() {
        return this.sampleSalmon;
    }

    /**
     * Set the sampleSalmon property.
     * 
     * @param sampleSalmon the sampleSalmon value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setSampleSalmon(DotSalmon sampleSalmon) {
        this.sampleSalmon = sampleSalmon;
        return this;
    }

    /**
     * Get the salmons property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the salmons value.
     */
    public List<DotSalmon> getSalmons() {
        return this.salmons;
    }

    /**
     * Set the salmons property.
     * 
     * @param salmons the salmons value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setSalmons(List<DotSalmon> salmons) {
        this.salmons = salmons;
        return this;
    }

    /**
     * Get the sampleFish property: MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * 
     * @return the sampleFish value.
     */
    public DotFish getSampleFish() {
        return this.sampleFish;
    }

    /**
     * Set the sampleFish property.
     * 
     * @param sampleFish the sampleFish value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setSampleFish(DotFish sampleFish) {
        this.sampleFish = sampleFish;
        return this;
    }

    /**
     * Get the fishes property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the fishes value.
     */
    public List<DotFish> getFishes() {
        return this.fishes;
    }

    /**
     * Set the fishes property.
     * 
     * @param fishes the fishes value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setFishes(List<DotFish> fishes) {
        this.fishes = fishes;
        return this;
    }

    public void validate() {
        if (getSampleSalmon() != null) {
            getSampleSalmon().validate();
        }
        if (getSalmons() != null) {
            getSalmons().forEach(e -> e.validate());
        }
        if (getSampleFish() != null) {
            getSampleFish().validate();
        }
        if (getFishes() != null) {
            getFishes().forEach(e -> e.validate());
        }
    }
}
