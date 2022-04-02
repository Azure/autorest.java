// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Siamese;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class SiameseTests {
    @Test
    public void testSerialization() {
        Siamese model =
                BinaryData.fromString(
                                "{\"breed\":\"eqsrdeupew\",\"color\":\"wreitjzyfl\",\"hates\":[{\"food\":\"rhmofcqhsm\",\"id\":2032289729,\"name\":\"kdtmlxheku\"},{\"food\":\"sjtxukcdmp\",\"id\":1492414283,\"name\":\"ryuanzwuxz\"},{\"food\":\"xtayrlhmwh\",\"id\":829422315,\"name\":\"rqobmtukkn\"}],\"id\":999743964,\"name\":\"tihfxtijbp\"}")
                        .toObject(Siamese.class);
        Assertions.assertEquals(999743964, model.getId());
        Assertions.assertEquals("tihfxtijbp", model.getName());
        Assertions.assertEquals("wreitjzyfl", model.getColor());
        Assertions.assertEquals(2032289729, model.getHates().get(0).getId());
        Assertions.assertEquals("kdtmlxheku", model.getHates().get(0).getName());
        Assertions.assertEquals("rhmofcqhsm", model.getHates().get(0).getFood());
        Assertions.assertEquals("eqsrdeupew", model.getBreed());
    }
}
