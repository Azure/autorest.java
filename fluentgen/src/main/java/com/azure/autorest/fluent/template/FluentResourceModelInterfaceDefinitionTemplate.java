/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceCreate;
import com.azure.autorest.model.javamodel.JavaFileContents;
import com.azure.autorest.template.IJavaTemplate;

public class FluentResourceModelInterfaceDefinitionTemplate implements IJavaTemplate<ResourceCreate, JavaFileContents> {

    @Override
    public void write(ResourceCreate resourceCreate, JavaFileContents context) {
        if (resourceCreate.isBodyParameterSameAsFluentModel()) {

        } else {
            // TODO
        }
    }
}
