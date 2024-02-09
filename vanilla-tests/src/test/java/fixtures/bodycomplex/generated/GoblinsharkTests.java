// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;

import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.Goblinshark;
import fixtures.bodycomplex.models.GoblinSharkColor;

import java.time.OffsetDateTime;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;

public final class GoblinsharkTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        Goblinshark model = BinaryData.fromString(
            "{\"fishtype\":\"goblin\",\"jawsize\":61317803,\"color\":\"red\",\"age\":726085925,\"birthday\":\"2021-02-01T09:39:18Z\",\"species\":\"vkmijcmmxdcuf\",\"length\":74.31171,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"ymzidn\",\"length\":95.387184,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"tbzsgfyccs\",\"length\":29.16742,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":54.53775},{\"fishtype\":\"Fish\",\"length\":49.81202},{\"fishtype\":\"Fish\",\"length\":1.0352552},{\"fishtype\":\"Fish\",\"length\":70.25995}]},{\"fishtype\":\"Fish\",\"species\":\"iachbo\",\"length\":57.38645,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":55.106743},{\"fishtype\":\"Fish\",\"length\":8.411604}]}]},{\"fishtype\":\"Fish\",\"species\":\"sfqpteehz\",\"length\":76.311905,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"qrimzinpv\",\"length\":23.06351,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":62.599922},{\"fishtype\":\"Fish\",\"length\":96.52387},{\"fishtype\":\"Fish\",\"length\":86.71942},{\"fishtype\":\"Fish\",\"length\":78.64408}]}]},{\"fishtype\":\"Fish\",\"species\":\"od\",\"length\":20.800983,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"mnoh\",\"length\":1.3219118,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":65.91078}]},{\"fishtype\":\"Fish\",\"species\":\"dsoifiyipj\",\"length\":44.730534,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":33.986122},{\"fishtype\":\"Fish\",\"length\":7.124424},{\"fishtype\":\"Fish\",\"length\":14.659691}]},{\"fishtype\":\"Fish\",\"species\":\"bznorcjxvsnby\",\"length\":39.604675,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":22.291475}]},{\"fishtype\":\"Fish\",\"species\":\"ocpcy\",\"length\":51.126534,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":19.046957},{\"fishtype\":\"Fish\",\"length\":97.11193},{\"fishtype\":\"Fish\",\"length\":22.825485},{\"fishtype\":\"Fish\",\"length\":8.593702}]}]}]}")
            .toObject(Goblinshark.class);
        Assertions.assertEquals("vkmijcmmxdcuf", model.getSpecies());
        Assertions.assertEquals(74.31171f, model.getLength());
        Assertions.assertEquals("ymzidn", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(95.387184f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("tbzsgfyccs", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(29.16742f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(54.53775f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(726085925, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-02-01T09:39:18Z"), model.getBirthday());
        Assertions.assertEquals(61317803, model.getJawsize());
        Assertions.assertEquals(GoblinSharkColor.LOWER_RED, model.getColor());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        Goblinshark model
            = new Goblinshark(74.31171f, OffsetDateTime.parse("2021-02-01T09:39:18Z")).setSpecies("vkmijcmmxdcuf")
                .setSiblings(Arrays.asList(
                    new Fish(95.387184f).setSpecies("ymzidn")
                        .setSiblings(Arrays.asList(
                            new Fish(29.16742f).setSpecies("tbzsgfyccs")
                                .setSiblings(Arrays.asList(new Fish(54.53775f), new Fish(49.81202f),
                                    new Fish(1.0352552f), new Fish(70.25995f))),
                            new Fish(57.38645f)
                                .setSpecies("iachbo")
                                .setSiblings(Arrays.asList(new Fish(55.106743f), new Fish(8.411604f))))),
                    new Fish(76.311905f).setSpecies("sfqpteehz")
                        .setSiblings(Arrays.asList(new Fish(23.06351f).setSpecies("qrimzinpv")
                            .setSiblings(Arrays.asList(new Fish(62.599922f), new Fish(96.52387f), new Fish(86.71942f),
                                new Fish(78.64408f))))),
                    new Fish(20.800983f).setSpecies("od")
                        .setSiblings(Arrays.asList(
                            new Fish(1.3219118f).setSpecies("mnoh").setSiblings(Arrays.asList(new Fish(65.91078f))),
                            new Fish(44.730534f).setSpecies("dsoifiyipj").setSiblings(
                                Arrays.asList(new Fish(33.986122f), new Fish(7.124424f), new Fish(14.659691f))),
                            new Fish(39.604675f).setSpecies("bznorcjxvsnby")
                                .setSiblings(Arrays.asList(new Fish(22.291475f))),
                            new Fish(51.126534f).setSpecies("ocpcy")
                                .setSiblings(Arrays.asList(new Fish(19.046957f), new Fish(97.11193f),
                                    new Fish(22.825485f), new Fish(8.593702f)))))))
                .setAge(726085925).setJawsize(61317803).setColor(GoblinSharkColor.LOWER_RED);
        model = BinaryData.fromObject(model).toObject(Goblinshark.class);
        Assertions.assertEquals("vkmijcmmxdcuf", model.getSpecies());
        Assertions.assertEquals(74.31171f, model.getLength());
        Assertions.assertEquals("ymzidn", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(95.387184f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("tbzsgfyccs", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(29.16742f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(54.53775f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(726085925, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-02-01T09:39:18Z"), model.getBirthday());
        Assertions.assertEquals(61317803, model.getJawsize());
        Assertions.assertEquals(GoblinSharkColor.LOWER_RED, model.getColor());
    }
}
