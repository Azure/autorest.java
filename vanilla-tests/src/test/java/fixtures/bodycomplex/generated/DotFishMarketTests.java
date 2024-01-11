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
            "{\"sampleSalmon\":{\"fish.type\":\"xypininmayhuybbk\",\"location\":\"pjkjlxofpdv\",\"iswild\":true,\"species\":\"depoog\"},\"salmons\":[{\"fish.type\":\"ciqihnhung\",\"location\":\"heotusiv\",\"iswild\":false,\"species\":\"jzrnf\"},{\"fish.type\":\"e\",\"location\":\"jofxqe\",\"iswild\":false,\"species\":\"hqjbasvmsmj\"},{\"fish.type\":\"cr\",\"location\":\"lxxwrljdouskc\",\"iswild\":true,\"species\":\"dkwt\"},{\"fish.type\":\"wnzlljfmppeeb\",\"location\":\"lssai\",\"iswild\":false,\"species\":\"gxsabkyq\"}],\"sampleFish\":{\"fish.type\":\"jitcjczdzevn\",\"species\":\"krwpdap\"},\"fishes\":[{\"fish.type\":\"dkvwrwjfe\",\"species\":\"nhutjeltmrldhugj\"},{\"fish.type\":\"datqxhocdgeabl\",\"species\":\"huticndvkao\"}]}")
            .toObject(DotFishMarket.class);
        Assertions.assertEquals("depoog", model.getSampleSalmon().getSpecies());
        Assertions.assertEquals("pjkjlxofpdv", model.getSampleSalmon().getLocation());
        Assertions.assertEquals(true, model.getSampleSalmon().iswild());
        Assertions.assertEquals("jzrnf", model.getSalmons().get(0).getSpecies());
        Assertions.assertEquals("heotusiv", model.getSalmons().get(0).getLocation());
        Assertions.assertEquals(false, model.getSalmons().get(0).iswild());
        Assertions.assertEquals("krwpdap", model.getSampleFish().getSpecies());
        Assertions.assertEquals("nhutjeltmrldhugj", model.getFishes().get(0).getSpecies());
    }
}
