// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.SmartSalmon;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

public final class SmartSalmonTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        SmartSalmon model = BinaryData.fromString(
            "{\"fishtype\":\"smart_salmon\",\"college_degree\":\"lssai\",\"eebvmgxsab\":\"datajwnzlljfmp\",\"location\":\"qduujitcjczdz\",\"iswild\":false,\"species\":\"hkr\",\"length\":38.019188,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"p\",\"length\":43.59222,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"vwrwj\",\"length\":48.286907,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":93.839714}]},{\"fishtype\":\"Fish\",\"species\":\"utjeltmrldhugj\",\"length\":66.317825,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":68.56524},{\"fishtype\":\"Fish\",\"length\":94.724525}]},{\"fishtype\":\"Fish\",\"species\":\"hocdgeab\",\"length\":70.60696,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":91.44667},{\"fishtype\":\"Fish\",\"length\":9.046102},{\"fishtype\":\"Fish\",\"length\":89.55463}]}]}]}")
            .toObject(SmartSalmon.class);
        Assertions.assertEquals("hkr", model.getSpecies());
        Assertions.assertEquals(38.019188f, model.getLength());
        Assertions.assertEquals("p", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(43.59222f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("vwrwj", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(48.286907f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(93.839714f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals("qduujitcjczdz", model.getLocation());
        Assertions.assertFalse(model.iswild());
        Assertions.assertEquals("lssai", model.getCollegeDegree());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        SmartSalmon model = new SmartSalmon(38.019188f).setSpecies("hkr")
            .setSiblings(Arrays.asList(new Fish(43.59222f).setSpecies("p")
                .setSiblings(Arrays.asList(
                    new Fish(48.286907f).setSpecies("vwrwj").setSiblings(Arrays.asList(new Fish(93.839714f))),
                    new Fish(66.317825f).setSpecies("utjeltmrldhugj")
                        .setSiblings(Arrays.asList(new Fish(68.56524f), new Fish(94.724525f))),
                    new Fish(70.60696f).setSpecies("hocdgeab")
                        .setSiblings(Arrays.asList(new Fish(91.44667f), new Fish(9.046102f), new Fish(89.55463f)))))))
            .setLocation("qduujitcjczdz")
            .setIswild(false)
            .setCollegeDegree("lssai")
            .setAdditionalProperties(mapOf("fishtype", "smart_salmon", "eebvmgxsab", "datajwnzlljfmp"));
        model = BinaryData.fromObject(model).toObject(SmartSalmon.class);
        Assertions.assertEquals("hkr", model.getSpecies());
        Assertions.assertEquals(38.019188f, model.getLength());
        Assertions.assertEquals("p", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(43.59222f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("vwrwj", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(48.286907f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(93.839714f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals("qduujitcjczdz", model.getLocation());
        Assertions.assertFalse(model.iswild());
        Assertions.assertEquals("lssai", model.getCollegeDegree());
    }

    // Use "Map.of" if available
    @SuppressWarnings("unchecked")
    private static <T> Map<String, T> mapOf(Object... inputs) {
        Map<String, T> map = new HashMap<>();
        for (int i = 0; i < inputs.length; i += 2) {
            String key = (String) inputs[i];
            T value = (T) inputs[i + 1];
            map.put(key, value);
        }
        return map;
    }
}
