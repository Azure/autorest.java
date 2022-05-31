// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.DotSalmon;
import org.junit.jupiter.api.Test;

public final class DotSalmonTests {
    @Test
    public void testDeserialize() {
        DotSalmon model =
                BinaryData.fromString(
                                "{\"fish.type\":\"DotSalmon\",\"location\":\"fdfdosygexpa\",\"iswild\":true,\"species\":\"hmsbzjhcrzevdp\"}")
                        .toObject(DotSalmon.class);
    }

    @Test
    public void testSerialize() {
        DotSalmon model = new DotSalmon();
        model = BinaryData.fromObject(model).toObject(DotSalmon.class);
    }
}
