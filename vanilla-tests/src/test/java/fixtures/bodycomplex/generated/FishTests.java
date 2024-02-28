// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Fish;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;

public final class FishTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        Fish model = BinaryData.fromString(
            "{\"fishtype\":\"k\",\"species\":\"audccsnhs\",\"length\":97.627846,\"siblings\":[{\"fishtype\":\"j\",\"species\":\"ryhtnapczwlokjy\",\"length\":41.759808,\"siblings\":[{\"fishtype\":\"ni\",\"species\":\"oxzjnchgejspod\",\"length\":92.009636,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":20.998896},{\"fishtype\":\"Fish\",\"length\":31.328451},{\"fishtype\":\"Fish\",\"length\":14.870871},{\"fishtype\":\"Fish\",\"length\":18.151981}]},{\"fishtype\":\"o\",\"species\":\"yahux\",\"length\":97.81198,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":48.674263},{\"fishtype\":\"Fish\",\"length\":69.22819}]},{\"fishtype\":\"aqwi\",\"species\":\"sprozvcput\",\"length\":19.268911,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":94.32142}]}]},{\"fishtype\":\"fdatsc\",\"species\":\"vpjhulsuuv\",\"length\":60.36252,\"siblings\":[{\"fishtype\":\"k\",\"species\":\"f\",\"length\":13.198918,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":36.488388},{\"fishtype\":\"Fish\",\"length\":47.16013},{\"fishtype\":\"Fish\",\"length\":59.751354}]},{\"fishtype\":\"lwejdpv\",\"species\":\"yoqpsoaccta\",\"length\":17.080189,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":2.8880775},{\"fishtype\":\"Fish\",\"length\":55.748093},{\"fishtype\":\"Fish\",\"length\":24.760372}]},{\"fishtype\":\"bcryffdfd\",\"species\":\"ygexpaojakhmsb\",\"length\":25.13494,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":74.25797},{\"fishtype\":\"Fish\",\"length\":45.85249},{\"fishtype\":\"Fish\",\"length\":59.630096}]},{\"fishtype\":\"dphlxaolt\",\"species\":\"trg\",\"length\":14.1341095,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":3.2438397},{\"fishtype\":\"Fish\",\"length\":55.120502},{\"fishtype\":\"Fish\",\"length\":95.85514}]}]}]}")
            .toObject(Fish.class);
        Assertions.assertEquals("audccsnhs", model.getSpecies());
        Assertions.assertEquals(97.627846f, model.getLength());
        Assertions.assertEquals("ryhtnapczwlokjy", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(41.759808f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("oxzjnchgejspod", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(92.009636f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(20.998896f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        Fish model = new Fish(97.627846f).setSpecies("audccsnhs")
            .setSiblings(Arrays.asList(
                new Fish(41.759808f).setSpecies("ryhtnapczwlokjy")
                    .setSiblings(Arrays.asList(
                        new Fish(92.009636f).setSpecies("oxzjnchgejspod")
                            .setSiblings(Arrays.asList(new Fish(20.998896f), new Fish(31.328451f), new Fish(14.870871f),
                                new Fish(18.151981f))),
                        new Fish(97.81198f).setSpecies("yahux")
                            .setSiblings(Arrays.asList(new Fish(48.674263f), new Fish(69.22819f))),
                        new Fish(19.268911f).setSpecies("sprozvcput").setSiblings(Arrays.asList(new Fish(94.32142f))))),
                new Fish(60.36252f).setSpecies("vpjhulsuuv")
                    .setSiblings(Arrays.asList(new Fish(13.198918f).setSpecies("f")
                        .setSiblings(Arrays.asList(new Fish(36.488388f), new Fish(47.16013f), new Fish(59.751354f))),
                        new Fish(17.080189f).setSpecies("yoqpsoaccta")
                            .setSiblings(
                                Arrays.asList(new Fish(2.8880775f), new Fish(55.748093f), new Fish(24.760372f))),
                        new Fish(25.13494f).setSpecies("ygexpaojakhmsb")
                            .setSiblings(Arrays.asList(new Fish(74.25797f), new Fish(45.85249f), new Fish(59.630096f))),
                        new Fish(14.1341095f).setSpecies("trg")
                            .setSiblings(
                                Arrays.asList(new Fish(3.2438397f), new Fish(55.120502f), new Fish(95.85514f)))))));
        model = BinaryData.fromObject(model).toObject(Fish.class);
        Assertions.assertEquals("audccsnhs", model.getSpecies());
        Assertions.assertEquals(97.627846f, model.getLength());
        Assertions.assertEquals("ryhtnapczwlokjy", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(41.759808f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("oxzjnchgejspod", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(92.009636f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(20.998896f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
    }
}
