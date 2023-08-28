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
        Fish model =
                BinaryData.fromString(
                                "{\"fishtype\":\"Fish\",\"species\":\"k\",\"length\":32.04869,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"ccsnhsjc\",\"length\":26.373684,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"kryhtnapczwlokj\",\"length\":68.2114,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":52.976006},{\"fishtype\":\"Fish\",\"length\":38.78496},{\"fishtype\":\"Fish\",\"length\":10.636175}]}]},{\"fishtype\":\"Fish\",\"species\":\"pjoxzjnch\",\"length\":62.1013,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"odmailzyd\",\"length\":2.2013366,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":81.72188},{\"fishtype\":\"Fish\",\"length\":29.215246},{\"fishtype\":\"Fish\",\"length\":46.80177},{\"fishtype\":\"Fish\",\"length\":57.05146}]},{\"fishtype\":\"Fish\",\"species\":\"xinpmqnjaq\",\"length\":93.690834,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":58.277805},{\"fishtype\":\"Fish\",\"length\":97.03771},{\"fishtype\":\"Fish\",\"length\":1.6464472}]}]},{\"fishtype\":\"Fish\",\"species\":\"zvcputegjvwmfda\",\"length\":77.05936,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"vpjhulsuuv\",\"length\":60.36252,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":35.477917},{\"fishtype\":\"Fish\",\"length\":0.87149143},{\"fishtype\":\"Fish\",\"length\":45.9383},{\"fishtype\":\"Fish\",\"length\":67.264824}]},{\"fishtype\":\"Fish\",\"species\":\"ndiodjpslwejdpv\",\"length\":15.661436,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":74.66286},{\"fishtype\":\"Fish\",\"length\":85.63972}]},{\"fishtype\":\"Fish\",\"species\":\"oacctaza\",\"length\":62.07999,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":24.760372},{\"fishtype\":\"Fish\",\"length\":87.91807},{\"fishtype\":\"Fish\",\"length\":52.05834}]}]}]}")
                        .toObject(Fish.class);
        Assertions.assertEquals("k", model.getSpecies());
        Assertions.assertEquals(32.04869f, model.getLength());
        Assertions.assertEquals("ccsnhsjc", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(26.373684f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("kryhtnapczwlokj", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(68.2114f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(
                52.976006f, model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        Fish model =
                new Fish(32.04869f)
                        .setSpecies("k")
                        .setSiblings(
                                Arrays.asList(
                                        new Fish(26.373684f)
                                                .setSpecies("ccsnhsjc")
                                                .setSiblings(
                                                        Arrays.asList(
                                                                new Fish(68.2114f)
                                                                        .setSpecies("kryhtnapczwlokj")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(52.976006f),
                                                                                        new Fish(38.78496f),
                                                                                        new Fish(10.636175f))))),
                                        new Fish(62.1013f)
                                                .setSpecies("pjoxzjnch")
                                                .setSiblings(
                                                        Arrays.asList(
                                                                new Fish(2.2013366f)
                                                                        .setSpecies("odmailzyd")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(81.72188f),
                                                                                        new Fish(29.215246f),
                                                                                        new Fish(46.80177f),
                                                                                        new Fish(57.05146f))),
                                                                new Fish(93.690834f)
                                                                        .setSpecies("xinpmqnjaq")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(58.277805f),
                                                                                        new Fish(97.03771f),
                                                                                        new Fish(1.6464472f))))),
                                        new Fish(77.05936f)
                                                .setSpecies("zvcputegjvwmfda")
                                                .setSiblings(
                                                        Arrays.asList(
                                                                new Fish(60.36252f)
                                                                        .setSpecies("vpjhulsuuv")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(35.477917f),
                                                                                        new Fish(0.87149143f),
                                                                                        new Fish(45.9383f),
                                                                                        new Fish(67.264824f))),
                                                                new Fish(15.661436f)
                                                                        .setSpecies("ndiodjpslwejdpv")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(74.66286f),
                                                                                        new Fish(85.63972f))),
                                                                new Fish(62.07999f)
                                                                        .setSpecies("oacctaza")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(24.760372f),
                                                                                        new Fish(87.91807f),
                                                                                        new Fish(52.05834f)))))));
        model = BinaryData.fromObject(model).toObject(Fish.class);
        Assertions.assertEquals("k", model.getSpecies());
        Assertions.assertEquals(32.04869f, model.getLength());
        Assertions.assertEquals("ccsnhsjc", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(26.373684f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("kryhtnapczwlokj", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(68.2114f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(
                52.976006f, model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
    }
}
