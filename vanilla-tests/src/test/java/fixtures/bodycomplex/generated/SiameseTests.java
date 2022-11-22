// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Dog;
import fixtures.bodycomplex.models.Siamese;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class SiameseTests {
    @Test
    public void testDeserialize() throws Exception {
        Siamese model =
                BinaryData.fromString(
                                "{\"breed\":\"jofjd\",\"color\":\"qs\",\"hates\":[{\"food\":\"pewnw\",\"id\":853033280,\"name\":\"jzyflu\"},{\"food\":\"rh\",\"id\":614383228,\"name\":\"qhsmyurkdtml\"},{\"food\":\"ekuksjtx\",\"id\":743792160,\"name\":\"mparcryuanzw\"},{\"food\":\"zdxtayrlhmwhf\",\"id\":1944828988,\"name\":\"obmtukk\"}],\"id\":1306784267,\"name\":\"tihfx\"}")
                        .toObject(Siamese.class);
        Assertions.assertEquals(1306784267, model.getId());
        Assertions.assertEquals("tihfx", model.getName());
        Assertions.assertEquals("qs", model.getColor());
        Assertions.assertEquals(853033280, model.getHates().get(0).getId());
        Assertions.assertEquals("jzyflu", model.getHates().get(0).getName());
        Assertions.assertEquals("pewnw", model.getHates().get(0).getFood());
        Assertions.assertEquals("jofjd", model.getBreed());
    }

    @Test
    public void testSerialize() throws Exception {
        Siamese model =
                new Siamese()
                        .setId(1306784267)
                        .setName("tihfx")
                        .setColor("qs")
                        .setHates(
                                Arrays.asList(
                                        new Dog().setId(853033280).setName("jzyflu").setFood("pewnw"),
                                        new Dog().setId(614383228).setName("qhsmyurkdtml").setFood("rh"),
                                        new Dog().setId(743792160).setName("mparcryuanzw").setFood("ekuksjtx"),
                                        new Dog().setId(1944828988).setName("obmtukk").setFood("zdxtayrlhmwhf")))
                        .setBreed("jofjd");
        model = BinaryData.fromObject(model).toObject(Siamese.class);
        Assertions.assertEquals(1306784267, model.getId());
        Assertions.assertEquals("tihfx", model.getName());
        Assertions.assertEquals("qs", model.getColor());
        Assertions.assertEquals(853033280, model.getHates().get(0).getId());
        Assertions.assertEquals("jzyflu", model.getHates().get(0).getName());
        Assertions.assertEquals("pewnw", model.getHates().get(0).getFood());
        Assertions.assertEquals("jofjd", model.getBreed());
    }
}
