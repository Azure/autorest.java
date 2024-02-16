// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Cookiecuttershark;
import fixtures.bodycomplex.models.Fish;
import java.time.OffsetDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;

public final class CookiecuttersharkTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        Cookiecuttershark model = BinaryData.fromString(
            "{\"fishtype\":\"jzzvdud\",\"age\":1163750271,\"birthday\":\"2021-03-18T06:36:17Z\",\"species\":\"dslfhotwmcy\",\"length\":44.5553,\"siblings\":[{\"fishtype\":\"jnpg\",\"species\":\"ftadehxnltyfs\",\"length\":89.95281,\"siblings\":[{\"fishtype\":\"uesnzwdejbavo\",\"species\":\"zdmohctbqvu\",\"length\":90.22537,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":28.46756},{\"fishtype\":\"Fish\",\"length\":34.753197}]}]},{\"fishtype\":\"vo\",\"species\":\"ujjugwdkcglh\",\"length\":94.58838,\"siblings\":[{\"fishtype\":\"dyggdtjixhbku\",\"species\":\"qweykhmenev\",\"length\":34.070343,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":59.155167}]}]},{\"fishtype\":\"hybcibv\",\"species\":\"dcsi\",\"length\":33.80321,\"siblings\":[{\"fishtype\":\"amdecte\",\"species\":\"iqscjeypv\",\"length\":78.81726,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":71.00282},{\"fishtype\":\"Fish\",\"length\":64.86957},{\"fishtype\":\"Fish\",\"length\":4.124391}]},{\"fishtype\":\"c\",\"species\":\"efovgmk\",\"length\":12.696767,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":92.70471},{\"fishtype\":\"Fish\",\"length\":38.220562},{\"fishtype\":\"Fish\",\"length\":97.29868},{\"fishtype\":\"Fish\",\"length\":24.898804}]}]},{\"fishtype\":\"qjpkcattpngjcrc\",\"species\":\"sqpjhvmdajvn\",\"length\":78.49014,\"siblings\":[{\"fishtype\":\"q\",\"species\":\"a\",\"length\":89.8033,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":23.191893},{\"fishtype\":\"Fish\",\"length\":31.31023},{\"fishtype\":\"Fish\",\"length\":94.267494}]},{\"fishtype\":\"yhltrpmopjmcm\",\"species\":\"u\",\"length\":37.39239,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":12.270075},{\"fishtype\":\"Fish\",\"length\":98.63335},{\"fishtype\":\"Fish\",\"length\":26.569813}]},{\"fishtype\":\"aodsfcpkv\",\"species\":\"dpuozmyz\",\"length\":89.91945,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":34.15059}]}]}]}")
            .toObject(Cookiecuttershark.class);
        Assertions.assertEquals("dslfhotwmcy", model.getSpecies());
        Assertions.assertEquals(44.5553f, model.getLength());
        Assertions.assertEquals("ftadehxnltyfs", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(89.95281f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("zdmohctbqvu", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(90.22537f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(28.46756f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(1163750271, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-03-18T06:36:17Z"), model.getBirthday());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        Cookiecuttershark model = new Cookiecuttershark(44.5553f, OffsetDateTime.parse("2021-03-18T06:36:17Z"))
            .setSpecies("dslfhotwmcy")
            .setSiblings(Arrays
                .asList(
                    new Fish(89.95281f).setSpecies("ftadehxnltyfs")
                        .setSiblings(Arrays.asList(new Fish(90.22537f).setSpecies("zdmohctbqvu").setSiblings(
                            Arrays.asList(new Fish(28.46756f), new Fish(34.753197f))))),
                    new Fish(94.58838f).setSpecies("ujjugwdkcglh")
                        .setSiblings(
                            Arrays.asList(new Fish(34.070343f).setSpecies("qweykhmenev")
                                .setSiblings(Arrays.asList(new Fish(59.155167f))))),
                    new Fish(33.80321f).setSpecies("dcsi")
                        .setSiblings(Arrays.asList(
                            new Fish(78.81726f).setSpecies("iqscjeypv").setSiblings(
                                Arrays.asList(new Fish(71.00282f), new Fish(64.86957f), new Fish(4.124391f))),
                            new Fish(12.696767f).setSpecies("efovgmk")
                                .setSiblings(Arrays.asList(new Fish(92.70471f), new Fish(38.220562f),
                                    new Fish(97.29868f), new Fish(24.898804f))))),
                    new Fish(78.49014f).setSpecies("sqpjhvmdajvn").setSiblings(Arrays.asList(
                        new Fish(89.8033f).setSpecies("a").setSiblings(
                            Arrays.asList(new Fish(23.191893f), new Fish(31.31023f), new Fish(94.267494f))),
                        new Fish(37.39239f).setSpecies("u").setSiblings(
                            Arrays.asList(new Fish(12.270075f), new Fish(98.63335f), new Fish(26.569813f))),
                        new Fish(89.91945f).setSpecies("dpuozmyz").setSiblings(Arrays.asList(new Fish(34.15059f)))))))
            .setAge(1163750271);
        model = BinaryData.fromObject(model).toObject(Cookiecuttershark.class);
        Assertions.assertEquals("dslfhotwmcy", model.getSpecies());
        Assertions.assertEquals(44.5553f, model.getLength());
        Assertions.assertEquals("ftadehxnltyfs", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(89.95281f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("zdmohctbqvu", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(90.22537f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(28.46756f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(1163750271, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-03-18T06:36:17Z"), model.getBirthday());
    }
}
