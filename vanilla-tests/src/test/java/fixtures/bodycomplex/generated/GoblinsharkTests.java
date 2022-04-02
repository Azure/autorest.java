// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Goblinshark;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class GoblinsharkTests {
    @Test
    public void testSerialization() {
        Goblinshark model =
                BinaryData.fromString(
                                "{\"fishtype\":\"goblin\",\"jawsize\":1661150390,\"age\":1656493513,\"birthday\":\"2021-03-30T08:32:11Z\",\"species\":\"apczwlokjy\",\"length\":0.41759807}")
                        .toObject(Goblinshark.class);
        Assertions.assertEquals("apczwlokjy", model.getSpecies());
        Assertions.assertEquals(0.41759807f, model.getLength());
        Assertions.assertEquals(1656493513, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-03-30T08:32:11Z"), model.getBirthday());
        Assertions.assertEquals(1661150390, model.getJawsize());
    }
}
