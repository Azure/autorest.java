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
        Salmon model = BinaryData.fromString(
            "{\"fishtype\":\"likwyqkgfgib\",\"location\":\"hejkotynqgou\",\"iswild\":false,\"species\":\"dgak\",\"length\":66.59647,\"siblings\":[{\"fishtype\":\"yb\",\"species\":\"qedqytbciqfoufl\",\"length\":48.112488,\"siblings\":[{\"fishtype\":\"smodmgloug\",\"species\":\"kwtmutduqktapspw\",\"length\":37.104095,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":56.303608},{\"fishtype\":\"Fish\",\"length\":78.37993}]},{\"fishtype\":\"mkdo\",\"species\":\"qw\",\"length\":22.323168,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":77.20989}]}]},{\"fishtype\":\"bjf\",\"species\":\"gmbmbexppbh\",\"length\":4.9203157,\"siblings\":[{\"fishtype\":\"lfp\",\"species\":\"s\",\"length\":49.08141,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":17.372614},{\"fishtype\":\"Fish\",\"length\":20.176643}]},{\"fishtype\":\"igjyjg\",\"species\":\"aoyfhrtxilnerkuj\",\"length\":1.0115743,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":16.52248}]}]}]}")
            .toObject(Salmon.class);
        Assertions.assertEquals("dgak", model.getSpecies());
        Assertions.assertEquals(66.59647f, model.getLength());
        Assertions.assertEquals("qedqytbciqfoufl", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(48.112488f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("kwtmutduqktapspw", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(37.104095f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(56.303608f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals("hejkotynqgou", model.getLocation());
        Assertions.assertEquals(false, model.iswild());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        Salmon model = new Salmon(66.59647f).setSpecies("dgak")
            .setSiblings(Arrays.asList(
                new Fish(48.112488f).setSpecies("qedqytbciqfoufl")
                    .setSiblings(Arrays.asList(
                        new Fish(37.104095f).setSpecies("kwtmutduqktapspw")
                            .setSiblings(Arrays.asList(new Fish(56.303608f), new Fish(78.37993f))),
                        new Fish(22.323168f).setSpecies("qw").setSiblings(Arrays.asList(new Fish(77.20989f))))),
                new Fish(4.9203157f).setSpecies("gmbmbexppbh")
                    .setSiblings(Arrays.asList(
                        new Fish(49.08141f).setSpecies("s")
                            .setSiblings(Arrays.asList(new Fish(17.372614f), new Fish(20.176643f))),
                        new Fish(1.0115743f).setSpecies("aoyfhrtxilnerkuj")
                            .setSiblings(Arrays.asList(new Fish(16.52248f)))))))
            .setLocation("hejkotynqgou")
            .setIswild(false);
        model = BinaryData.fromObject(model).toObject(Salmon.class);
        Assertions.assertEquals("dgak", model.getSpecies());
        Assertions.assertEquals(66.59647f, model.getLength());
        Assertions.assertEquals("qedqytbciqfoufl", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(48.112488f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("kwtmutduqktapspw", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(37.104095f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(56.303608f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals("hejkotynqgou", model.getLocation());
        Assertions.assertEquals(false, model.iswild());
    }
}
