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
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class SchemaUtilTests {
    private static ObjectSchema pet = new ObjectSchema();
    private static ObjectSchema dog = new ObjectSchema();
    private static ObjectSchema cat = new ObjectSchema();
    private static ObjectSchema corgi = new ObjectSchema();

    @BeforeClass
    public static void setup() {
        pet.set$key("pet");
        pet.setChildren(new Relations());
        pet.getChildren().setAll(Arrays.asList(dog, cat));
        pet.getChildren().setImmediate(Arrays.asList(dog, cat));
        cat.set$key("cat");
        cat.setParents(new Relations());
        cat.getParents().setImmediate(Arrays.asList(pet));
        cat.getParents().setAll(Arrays.asList(pet));
        dog.set$key("dog");
        dog.setParents(new Relations());
        dog.getParents().setImmediate(Arrays.asList(pet));
        dog.getParents().setAll(Arrays.asList(pet));
        dog.setChildren(new Relations());
        dog.getChildren().setAll(Arrays.asList(corgi));
        dog.getChildren().setImmediate(Arrays.asList(corgi));
        corgi.set$key("corgi");
        corgi.setParents(new Relations());
        corgi.getParents().setImmediate(Arrays.asList(dog));
        corgi.getParents().setAll(Arrays.asList(pet, dog));
    }

    @Test
    public void testObjectSchemaFindParent() {
        Assert.assertEquals(null, SchemaUtil.getLowestCommonParent(Arrays.asList()));
        Assert.assertEquals(pet, SchemaUtil.getLowestCommonParent(Arrays.asList(pet)));
        Assert.assertEquals(corgi, SchemaUtil.getLowestCommonParent(Arrays.asList(corgi)));
        Assert.assertEquals(pet, SchemaUtil.getLowestCommonParent(Arrays.asList(pet, dog)));
        Assert.assertEquals(pet, SchemaUtil.getLowestCommonParent(Arrays.asList(cat, dog)));
        Assert.assertEquals(dog, SchemaUtil.getLowestCommonParent(Arrays.asList(dog, corgi)));
        Assert.assertEquals(pet, SchemaUtil.getLowestCommonParent(Arrays.asList(cat, corgi)));
        Assert.assertEquals(pet, SchemaUtil.getLowestCommonParent(Arrays.asList(pet, corgi)));
        Assert.assertEquals(pet, SchemaUtil.getLowestCommonParent(Arrays.asList(cat, dog, corgi)));
        ObjectSchema dummy = new ObjectSchema();
        dummy.set$key("dummy");
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(Arrays.asList(dummy, dog)) instanceof AnySchema);
    }

    @Test
    public void testAllSchemaFindParent() {
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(Arrays.asList(new ArraySchema(), pet)) instanceof AnySchema);
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(Arrays.asList(new DictionarySchema(), pet)) instanceof AnySchema);
        StringSchema stringSchema = new StringSchema();
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(Arrays.asList(stringSchema)) instanceof StringSchema);
        Assert.assertTrue(SchemaUtil.getLowestCommonParent(Arrays.asList(stringSchema, stringSchema)) instanceof StringSchema);
    }
}
