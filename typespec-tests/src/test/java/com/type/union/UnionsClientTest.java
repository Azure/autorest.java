// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.union;

import com.azure.core.util.BinaryData;
import com.azure.core.util.serializer.TypeReference;
import com.type.union.models.Cat;
import com.type.union.models.EnumsOnlyCases;
import com.type.union.models.LR;
import com.type.union.models.MixedLiteralsCases;
import com.type.union.models.MixedTypesCases;
import com.type.union.models.Prop;
import com.type.union.models.Prop1;
import com.type.union.models.Prop2;
import com.type.union.models.Prop3;
import com.type.union.models.StringAndArrayCases;
import com.type.union.models.StringExtensibleNamedUnion;
import com.type.union.models.UD;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class UnionsClientTest {

    private final StringsOnlyClient client1 = new UnionClientBuilder().buildStringsOnlyClient();
    private final StringExtensibleClient client2 = new UnionClientBuilder().buildStringExtensibleClient();
    private final StringExtensibleNamedClient client3 = new UnionClientBuilder().buildStringExtensibleNamedClient();
    private final IntsOnlyClient client4 = new UnionClientBuilder().buildIntsOnlyClient();
    private final FloatsOnlyClient client5 = new UnionClientBuilder().buildFloatsOnlyClient();
    private final ModelsOnlyClient client6 = new UnionClientBuilder().buildModelsOnlyClient();
    private final EnumsOnlyClient client7 = new UnionClientBuilder().buildEnumsOnlyClient();
    private final StringAndArrayClient client8 = new UnionClientBuilder().buildStringAndArrayClient();
    private final MixedLiteralsClient client9 = new UnionClientBuilder().buildMixedLiteralsClient();
    private final MixedTypesClient client10 = new UnionClientBuilder().buildMixedTypesClient();

    @Test
    public void testStringsOnlyClient() {
        Prop1 prop = client1.get().getProp();
        Assertions.assertEquals(Prop1.B, prop);
        client1.send(prop);
    }

    @Test
    public void testStringExtensibleClient() {
        Prop prop = client2.get().getProp();
        Assertions.assertEquals("custom", prop.toString());
        client2.send(prop);
    }

    @Test
    public void testStringExtensibleNamedClient() {
        StringExtensibleNamedUnion prop = client3.get().getProp();
        Assertions.assertEquals("custom", prop.toString());
        client3.send(prop);
    }

    @Test
    public void testIntsOnlyClient() {
        Prop2 prop = client4.get().getProp();
        Assertions.assertEquals(2L, prop.toLong());
        client4.send(prop);
    }

    @Test
    public void testFloatsOnlyClient() {
        Prop3 prop = client5.get().getProp();
        Assertions.assertEquals(2.2, prop.toDouble());
        client5.send(prop);
    }

    @Test
    public void testModelsOnlyClient() {
        BinaryData prop = client6.get().getProp();
        Assertions.assertEquals("test", prop.toObject(Cat.class).getName());
        client6.send(BinaryData.fromObject(new Cat("test")));
    }

    @Test
    public void testEnumsOnlyClient() {
        EnumsOnlyCases prop = client7.get().getProp();
        Assertions.assertEquals(LR.RIGHT, LR.fromString(prop.getLr().toObject(String.class)));
        Assertions.assertEquals(UD.UP, UD.fromString(prop.getUd().toObject(String.class)));
        client7.send(prop);
    }

    @Test
    public void testStringAndArrayClient() {
        StringAndArrayCases prop = client8.get().getProp();
        Assertions.assertEquals("test", prop.getString().toObject(String.class));
        Assertions.assertEquals(Arrays.asList("test1", "test2"), prop.getArray().toObject(new TypeReference<List<String>>() {}));
        client8.send(prop);
    }

    @Test
    public void testMixedLiteralsClient() {
        MixedLiteralsCases prop = client9.get().getProp();
        Assertions.assertEquals("a", prop.getStringLiteral().toObject(String.class));
        Assertions.assertEquals(2L, prop.getIntLiteral().toObject(Long.class));
        Assertions.assertEquals(3.3, prop.getFloatLiteral().toObject(Double.class));
        Assertions.assertEquals(true, prop.getBooleanLiteral().toObject(Boolean.class));
        client9.send(prop);
    }

    @Test
    public void testMixedTypesClient() {
        MixedTypesCases prop = client10.get().getProp();
        Assertions.assertEquals("test", prop.getModel().toObject(Cat.class).getName());
        Assertions.assertEquals("a", prop.getLiteral().toObject(String.class));
        Assertions.assertEquals(2L, prop.getIntProperty().toObject(Long.class));
        Assertions.assertEquals(true, prop.getBooleanProperty().toObject(Boolean.class));
        client10.send(prop);
    }
}
