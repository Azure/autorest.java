/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.IJavaTemplate;

public class FluentResourceCollectionImplementationTemplate implements IJavaTemplate<FluentResourceCollection, JavaFile> {

    private static final FluentResourceCollectionImplementationTemplate INSTANCE = new FluentResourceCollectionImplementationTemplate();

    public static FluentResourceCollectionImplementationTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(FluentResourceCollection collection, JavaFile javaFile) {

    }
}
