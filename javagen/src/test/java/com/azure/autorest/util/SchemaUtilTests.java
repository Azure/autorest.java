// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.extension.base.model.codemodel.AnySchema;
import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Relations;
import com.azure.autorest.extension.base.model.codemodel.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SchemaUtilTests {
    private static final ObjectSchema PET;
    private static final ObjectSchema DOG;
    private static final ObjectSchema CAT;
    private static final ObjectSchema CORGI;

    static {
        PET = new ObjectSchema();
        PET.set$key("pet");
        PET.setChildren(new Relations());
        
        CAT = new ObjectSchema();
        CAT.set$key("cat");
        CAT.setParents(new Relations());
        CAT.getParents().setImmediate(List.of(PET));
        CAT.getParents().setAll(List.of(PET));

        DOG = new ObjectSchema();
        DOG.set$key("dog");
        DOG.setParents(new Relations());
        DOG.getParents().setImmediate(List.of(PET));
        DOG.getParents().setAll(List.of(PET));
        DOG.setChildren(new Relations());

        CORGI = new ObjectSchema();
        CORGI.set$key("corgi");
        CORGI.setParents(new Relations());

        PET.getChildren().setAll(Arrays.asList(DOG, CAT));
        PET.getChildren().setImmediate(Arrays.asList(DOG, CAT));

        DOG.getChildren().setAll(List.of(CORGI));
        DOG.getChildren().setImmediate(List.of(CORGI));

        CORGI.getParents().setImmediate(List.of(DOG));
        CORGI.getParents().setAll(List.of(PET, DOG));
    }

    @Test
    public void testObjectSchemaFindParent() {
        Assertions.assertNull(SchemaUtil.getLowestCommonParent(Collections.emptyIterator()));
        Assertions.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(PET)));
        Assertions.assertEquals(CORGI, SchemaUtil.getLowestCommonParent(List.of(CORGI)));
        Assertions.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(PET, DOG)));
        Assertions.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(CAT, DOG)));
        Assertions.assertEquals(DOG, SchemaUtil.getLowestCommonParent(List.of(DOG, CORGI)));
        Assertions.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(CAT, CORGI)));
        Assertions.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(PET, CORGI)));
        Assertions.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(CAT, DOG, CORGI)));
        ObjectSchema dummy = new ObjectSchema();
        dummy.set$key("dummy");
        Assertions.assertTrue(SchemaUtil.getLowestCommonParent(List.of(dummy, DOG)) instanceof AnySchema);
    }

    @Test
    public void testAllSchemaFindParent() {
        Assertions.assertTrue(SchemaUtil.getLowestCommonParent(List.of(new ArraySchema(), PET)) instanceof AnySchema);
        Assertions.assertTrue(SchemaUtil.getLowestCommonParent(List.of(new DictionarySchema(), PET)) instanceof AnySchema);
        StringSchema stringSchema = new StringSchema();
        Assertions.assertTrue(SchemaUtil.getLowestCommonParent(List.of(stringSchema)) instanceof StringSchema);
        Assertions.assertTrue(SchemaUtil.getLowestCommonParent(List.of(stringSchema, stringSchema)) instanceof StringSchema);
    }
}
