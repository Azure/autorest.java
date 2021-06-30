/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;

public class FluentResourceUpdateExample extends FluentBaseExample {

    private final ResourceUpdate resourceUpdate;

    public FluentResourceUpdateExample(String name, FluentManager manager, FluentResourceCollection collection, ResourceUpdate resourceUpdate) {
        super(name, manager, collection);
        this.resourceUpdate = resourceUpdate;
    }

    public ResourceUpdate getResourceUpdate() {
        return resourceUpdate;
    }
}
