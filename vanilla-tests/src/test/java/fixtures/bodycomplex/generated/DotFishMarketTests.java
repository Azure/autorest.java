// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;

import fixtures.bodycomplex.models.DotFishMarket;

import org.junit.jupiter.api.Assertions;

public final class DotFishMarketTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        DotFishMarket model = BinaryData.fromString(
            "{\"sampleSalmon\":{\"fish.type\":\"DotSalmon\",\"location\":\"paojakhmsbzjh\",\"iswild\":false,\"species\":\"vdphlxaolthqtr\"},\"salmons\":[{\"fish.type\":\"DotSalmon\",\"location\":\"pfzfsinzgvfc\",\"iswild\":false,\"species\":\"oxxjtfelluwf\"}],\"sampleFish\":{\"fish.type\":\"DotFish\",\"species\":\"onpeqfpjkjlxofp\"},\"fishes\":[{\"fish.type\":\"DotFish\",\"species\":\"fxxypininmayhuy\"}]}")
            .toObject(DotFishMarket.class);
        Assertions.assertEquals("vdphlxaolthqtr", model.getSampleSalmon().getSpecies());
        Assertions.assertEquals("paojakhmsbzjh", model.getSampleSalmon().getLocation());
        Assertions.assertEquals(false, model.getSampleSalmon().iswild());
        Assertions.assertEquals("oxxjtfelluwf", model.getSalmons().get(0).getSpecies());
        Assertions.assertEquals("pfzfsinzgvfc", model.getSalmons().get(0).getLocation());
        Assertions.assertEquals(false, model.getSalmons().get(0).iswild());
        Assertions.assertEquals("onpeqfpjkjlxofp", model.getSampleFish().getSpecies());
        Assertions.assertEquals("fxxypininmayhuy", model.getFishes().get(0).getSpecies());
    }
}
