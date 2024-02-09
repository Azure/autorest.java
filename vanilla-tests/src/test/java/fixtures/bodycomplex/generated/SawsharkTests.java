// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;

import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.Sawshark;

import java.time.OffsetDateTime;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;

public final class SawsharkTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        Sawshark model = BinaryData.fromString(
            "{\"fishtype\":\"sawshark\",\"age\":835826608,\"birthday\":\"2021-09-01T01:28:54Z\",\"species\":\"gibma\",\"length\":41.75237,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"qsrxybzqqed\",\"length\":18.452686,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"iqfouflmmnkz\",\"length\":67.5812,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":56.270576},{\"fishtype\":\"Fish\",\"length\":47.130127}]},{\"fishtype\":\"Fish\",\"species\":\"ougpbkwt\",\"length\":88.09739,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":35.76977},{\"fishtype\":\"Fish\",\"length\":86.815414}]},{\"fishtype\":\"Fish\",\"species\":\"ta\",\"length\":94.51547,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":18.805998}]}]},{\"fishtype\":\"Fish\",\"species\":\"uertumk\",\"length\":2.3766458,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"whbmd\",\"length\":84.869194,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":0.016152859},{\"fishtype\":\"Fish\",\"length\":25.363232},{\"fishtype\":\"Fish\",\"length\":21.297592}]},{\"fishtype\":\"Fish\",\"species\":\"mbmbexppbh\",\"length\":4.9203157,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":8.5479145},{\"fishtype\":\"Fish\",\"length\":16.483826}]},{\"fishtype\":\"Fish\",\"species\":\"p\",\"length\":54.458298,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":49.08141},{\"fishtype\":\"Fish\",\"length\":49.97902},{\"fishtype\":\"Fish\",\"length\":49.90956},{\"fishtype\":\"Fish\",\"length\":17.372614}]},{\"fishtype\":\"Fish\",\"species\":\"xigjyjgzjaoyfhr\",\"length\":49.60807,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":5.565733},{\"fishtype\":\"Fish\",\"length\":37.739403},{\"fishtype\":\"Fish\",\"length\":27.193933},{\"fishtype\":\"Fish\",\"length\":79.694275}]}]},{\"fishtype\":\"Fish\",\"species\":\"jysvl\",\"length\":22.571384,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"qawrlyxwj\",\"length\":15.329713,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":99.249115},{\"fishtype\":\"Fish\",\"length\":89.70539},{\"fishtype\":\"Fish\",\"length\":40.196205},{\"fishtype\":\"Fish\",\"length\":4.2197704}]},{\"fishtype\":\"Fish\",\"species\":\"gjvtbv\",\"length\":77.91465,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":15.425348},{\"fishtype\":\"Fish\",\"length\":97.266785}]},{\"fishtype\":\"Fish\",\"species\":\"rujqg\",\"length\":84.77632,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":44.637882}]},{\"fishtype\":\"Fish\",\"species\":\"qfprwzwbn\",\"length\":48.901512,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":23.633587}]}]},{\"fishtype\":\"Fish\",\"species\":\"uizga\",\"length\":2.362436,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"zuckyfi\",\"length\":56.51085,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":68.11774}]},{\"fishtype\":\"Fish\",\"species\":\"vzwdzuhtymwis\",\"length\":66.07407,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":61.41952},{\"fishtype\":\"Fish\",\"length\":60.204166},{\"fishtype\":\"Fish\",\"length\":59.410374},{\"fishtype\":\"Fish\",\"length\":46.825348}]}]}]}")
            .toObject(Sawshark.class);
        Assertions.assertEquals("gibma", model.getSpecies());
        Assertions.assertEquals(41.75237f, model.getLength());
        Assertions.assertEquals("qsrxybzqqed", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(18.452686f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("iqfouflmmnkz", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(67.5812f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(56.270576f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(835826608, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-09-01T01:28:54Z"), model.getBirthday());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        Sawshark model
            = new Sawshark(41.75237f, OffsetDateTime.parse("2021-09-01T01:28:54Z"))
                .setSpecies(
                    "gibma")
                .setSiblings(
                    Arrays
                        .asList(
                            new Fish(18.452686f).setSpecies("qsrxybzqqed").setSiblings(Arrays.asList(
                                new Fish(67.5812f).setSpecies("iqfouflmmnkz")
                                    .setSiblings(Arrays.asList(new Fish(56.270576f), new Fish(47.130127f))),
                                new Fish(88.09739f).setSpecies("ougpbkwt")
                                    .setSiblings(Arrays.asList(new Fish(35.76977f), new Fish(86.815414f))),
                                new Fish(94.51547f).setSpecies("ta").setSiblings(Arrays.asList(new Fish(18.805998f))))),
                            new Fish(2.3766458f).setSpecies("uertumk")
                                .setSiblings(Arrays.asList(
                                    new Fish(84.869194f).setSpecies("whbmd")
                                        .setSiblings(Arrays.asList(new Fish(0.016152859f), new Fish(25.363232f),
                                            new Fish(21.297592f))),
                                    new Fish(4.9203157f).setSpecies("mbmbexppbh")
                                        .setSiblings(Arrays.asList(new Fish(8.5479145f), new Fish(16.483826f))),
                                    new Fish(54.458298f).setSpecies("p")
                                        .setSiblings(Arrays.asList(new Fish(49.08141f), new Fish(49.97902f),
                                            new Fish(49.90956f), new Fish(17.372614f))),
                                    new Fish(49.60807f).setSpecies("xigjyjgzjaoyfhr")
                                        .setSiblings(Arrays.asList(new Fish(5.565733f), new Fish(37.739403f),
                                            new Fish(27.193933f), new Fish(79.694275f))))),
                            new Fish(22.571384f).setSpecies("jysvl")
                                .setSiblings(Arrays.asList(
                                    new Fish(15.329713f).setSpecies("qawrlyxwj")
                                        .setSiblings(Arrays.asList(new Fish(99.249115f), new Fish(89.70539f),
                                            new Fish(40.196205f), new Fish(4.2197704f))),
                                    new Fish(77.91465f).setSpecies("gjvtbv")
                                        .setSiblings(Arrays.asList(new Fish(15.425348f), new Fish(97.266785f))),
                                    new Fish(84.77632f).setSpecies("rujqg")
                                        .setSiblings(Arrays.asList(new Fish(44.637882f))),
                                    new Fish(48.901512f).setSpecies("qfprwzwbn")
                                        .setSiblings(Arrays.asList(new Fish(23.633587f))))),
                            new Fish(2.362436f).setSpecies("uizga")
                                .setSiblings(Arrays.asList(
                                    new Fish(56.51085f).setSpecies("zuckyfi")
                                        .setSiblings(Arrays.asList(new Fish(68.11774f))),
                                    new Fish(66.07407f).setSpecies("vzwdzuhtymwis")
                                        .setSiblings(Arrays.asList(new Fish(61.41952f), new Fish(60.204166f),
                                            new Fish(59.410374f), new Fish(46.825348f)))))))
                .setAge(835826608);
        model = BinaryData.fromObject(model).toObject(Sawshark.class);
        Assertions.assertEquals("gibma", model.getSpecies());
        Assertions.assertEquals(41.75237f, model.getLength());
        Assertions.assertEquals("qsrxybzqqed", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(18.452686f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("iqfouflmmnkz", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(67.5812f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(56.270576f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(835826608, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-09-01T01:28:54Z"), model.getBirthday());
    }
}
