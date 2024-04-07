// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.extension.base.model.codemodel.AnySchema;
import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Relations;
import com.azure.autorest.extension.base.model.codemodel.StringSchema;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertNull(SchemaUtil.getLowestCommonParent(Collections.emptyIterator()));
        Assert.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(PET)));
        Assert.assertEquals(CORGI, SchemaUtil.getLowestCommonParent(List.of(CORGI)));
        Assert.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(PET, DOG)));
        Assert.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(CAT, DOG)));
        Assert.assertEquals(DOG, SchemaUtil.getLowestCommonParent(List.of(DOG, CORGI)));
        Assert.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(CAT, CORGI)));
        Assert.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(PET, CORGI)));
        Assert.assertEquals(PET, SchemaUtil.getLowestCommonParent(List.of(CAT, DOG, CORGI)));
        ObjectSchema dummy = new ObjectSchema();
        dummy.set$key("dummy");
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(List.of(dummy, DOG)) instanceof AnySchema);
    }

    @Test
    public void testAllSchemaFindParent() {
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(List.of(new ArraySchema(), PET)) instanceof AnySchema);
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(List.of(new DictionarySchema(), PET)) instanceof AnySchema);
        StringSchema stringSchema = new StringSchema();
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(List.of(stringSchema)) instanceof StringSchema);
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(List.of(stringSchema, stringSchema)) instanceof StringSchema);
    }
}
