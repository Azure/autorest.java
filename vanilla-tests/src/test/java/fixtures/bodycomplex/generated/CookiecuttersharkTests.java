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
            "{\"fishtype\":\"cookiecuttershark\",\"age\":1085002799,\"birthday\":\"2021-07-24T18:44:29Z\",\"species\":\"pbtoqcjmkl\",\"length\":5.423278,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"idtqajzyu\",\"length\":17.32046,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"jkrlkhbzhfepg\",\"length\":58.94148,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":53.090946}]}]},{\"fishtype\":\"Fish\",\"species\":\"locx\",\"length\":3.4862816,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"erhhbcsglumm\",\"length\":91.597664,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":29.64415}]},{\"fishtype\":\"Fish\",\"species\":\"dxob\",\"length\":62.736557,\"siblings\":[{\"fishtype\":\"Fish\",\"length\":1.4938772},{\"fishtype\":\"Fish\",\"length\":12.01387}]}]}]}")
            .toObject(Cookiecuttershark.class);
        Assertions.assertEquals("pbtoqcjmkl", model.getSpecies());
        Assertions.assertEquals(5.423278f, model.getLength());
        Assertions.assertEquals("idtqajzyu", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(17.32046f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("jkrlkhbzhfepg", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(58.94148f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(53.090946f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(1085002799, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-07-24T18:44:29Z", ISO_8601), model.getBirthday());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        Cookiecuttershark model
            = new Cookiecuttershark(5.423278f, OffsetDateTime.parse("2021-07-24T18:44:29Z", ISO_8601))
                .setSpecies("pbtoqcjmkl")
                .setSiblings(Arrays.asList(
                    new Fish(17.32046f).setSpecies("idtqajzyu")
                        .setSiblings(Arrays.asList(new Fish(58.94148f).setSpecies("jkrlkhbzhfepg")
                            .setSiblings(Arrays.asList(new Fish(53.090946f))))),
                    new Fish(3.4862816f).setSpecies("locx").setSiblings(Arrays.asList(
                        new Fish(91.597664f).setSpecies("erhhbcsglumm").setSiblings(Arrays.asList(new Fish(29.64415f))),
                        new Fish(62.736557f).setSpecies("dxob")
                            .setSiblings(Arrays.asList(new Fish(1.4938772f), new Fish(12.01387f)))))))
                .setAge(1085002799);
        model = BinaryData.fromObject(model).toObject(Cookiecuttershark.class);
        Assertions.assertEquals("pbtoqcjmkl", model.getSpecies());
        Assertions.assertEquals(5.423278f, model.getLength());
        Assertions.assertEquals("idtqajzyu", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(17.32046f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("jkrlkhbzhfepg", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(58.94148f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(53.090946f,
            model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(1085002799, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-07-24T18:44:29Z", ISO_8601), model.getBirthday());
    }
}
