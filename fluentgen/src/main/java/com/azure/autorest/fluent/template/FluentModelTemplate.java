/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.template.ModelTemplate;

public class FluentModelTemplate extends ModelTemplate {

    private static FluentModelTemplate _instance = new FluentModelTemplate();

    protected FluentModelTemplate() {
    }

    public static FluentModelTemplate getInstance() {
        return _instance;
    }

    @Override
    protected boolean validateOnParentModel(String parentModelName) {
        return parentModelName != null && FluentType.nonResourceType(parentModelName);
    }
}
