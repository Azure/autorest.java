/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.template.ModelTemplate;
import com.azure.autorest.util.ModelNamer;

public class FluentModelTemplate extends ModelTemplate {

    private static FluentModelTemplate _instance = new FluentModelTemplate();

    private static ModelNamer modelNamer;

    protected FluentModelTemplate() {
    }

    public static FluentModelTemplate getInstance() {
        return _instance;
    }

    @Override
    protected boolean validateOnParentModel(String parentModelName) {
        return parentModelName != null
                && FluentType.nonResourceType(parentModelName)
                && FluentType.nonManagementError(parentModelName);
    }

    @Override
    protected String getGetterName(ClientModel model, ClientModelProperty property) {
        if (FluentType.ManagementError.getName().equals(model.getParentModelName())) {
            // subclass of ManagementError

            if (modelNamer == null) {
                modelNamer = new ModelNamer();
            }
            return modelNamer.modelPropertyGetterName(property);
        } else {
            return super.getGetterName(model, property);
        }
    }
}
