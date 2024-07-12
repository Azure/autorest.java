// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

public class SchemaUtilAdapter {
    public static void adaptForTypeSpec(){
        // In TypeSpec, we generate empty class for named/anonymous models, not plain Object class.
        // Equivalence for `{"type": "object"}` in TypeSpec is `Record<unknown>`.
        SchemaUtil.setPlainObjectPredicate(objectSchema -> false);
    }
}
