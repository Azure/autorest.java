// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.Salmon;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;

public final class SalmonTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        Salmon model =
                BinaryData.fromString(
                                "{\"fishtype\":\"salmon\",\"location\":\"xzxtheo\",\"iswild\":false,\"species\":\"vyevcciqi\",\"length\":38.31058,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"gbwjzrnf\",\"length\":15.608067,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"spemvtzfk\",\"length\":30.775976,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":93.34901},{\"fishtype\":\"Fish\",\"length\":33.460037},{\"fishtype\":\"Fish\",\"length\":16.258835}]},{\"fishtype\":\"Fish\",\"species\":\"xqeofjaeqjhqjba\",\"length\":2.1371305,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":53.99528},{\"fishtype\":\"Fish\",\"length\":21.614403},{\"fishtype\":\"Fish\",\"length\":95.46702},{\"fishtype\":\"Fish\",\"length\":48.216724}]},{\"fishtype\":\"Fish\",\"species\":\"ngsntnbybk\",\"length\":9.4591675,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":70.90245},{\"fishtype\":\"Fish\",\"length\":49.255276},{\"fishtype\":\"Fish\",\"length\":57.128227}]},{\"fishtype\":\"Fish\",\"species\":\"xxwr\",\"length\":31.060987,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":72.06065},{\"fishtype\":\"Fish\",\"length\":70.73601},{\"fishtype\":\"Fish\",\"length\":50.07295}]}]}]}")
                        .toObject(Salmon.class);
        Assertions.assertEquals("vyevcciqi", model.getSpecies());
        Assertions.assertEquals(38.31058f, model.getLength());
        Assertions.assertEquals("gbwjzrnf", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(15.608067f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("spemvtzfk", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(30.775976f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(
                93.34901f, model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals("xzxtheo", model.getLocation());
        Assertions.assertEquals(false, model.iswild());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        Salmon model =
                new Salmon(38.31058f)
                        .setSpecies("vyevcciqi")
                        .setSiblings(
                                Arrays.asList(
                                        new Fish(15.608067f)
                                                .setSpecies("gbwjzrnf")
                                                .setSiblings(
                                                        Arrays.asList(
                                                                new Fish(30.775976f)
                                                                        .setSpecies("spemvtzfk")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(93.34901f),
                                                                                        new Fish(33.460037f),
                                                                                        new Fish(16.258835f))),
                                                                new Fish(2.1371305f)
                                                                        .setSpecies("xqeofjaeqjhqjba")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(53.99528f),
                                                                                        new Fish(21.614403f),
                                                                                        new Fish(95.46702f),
                                                                                        new Fish(48.216724f))),
                                                                new Fish(9.4591675f)
                                                                        .setSpecies("ngsntnbybk")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(70.90245f),
                                                                                        new Fish(49.255276f),
                                                                                        new Fish(57.128227f))),
                                                                new Fish(31.060987f)
                                                                        .setSpecies("xxwr")
                                                                        .setSiblings(
                                                                                Arrays.asList(
                                                                                        new Fish(72.06065f),
                                                                                        new Fish(70.73601f),
                                                                                        new Fish(50.07295f)))))))
                        .setLocation("xzxtheo")
                        .setIswild(false);
        model = BinaryData.fromObject(model).toObject(Salmon.class);
        Assertions.assertEquals("vyevcciqi", model.getSpecies());
        Assertions.assertEquals(38.31058f, model.getLength());
        Assertions.assertEquals("gbwjzrnf", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(15.608067f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("spemvtzfk", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(30.775976f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(
                93.34901f, model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals("xzxtheo", model.getLocation());
        Assertions.assertEquals(false, model.iswild());
    }
}
