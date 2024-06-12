// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.template.StreamSerializationModelTemplate;

public class FluentStreamStyleSerializationModelTemplate extends StreamSerializationModelTemplate {
    private static final FluentModelTemplate FLUENT_MODEL_TEMPLATE = FluentModelTemplate.getInstance();

    public static FluentStreamStyleSerializationModelTemplate getInstance() {
        return new FluentStreamStyleSerializationModelTemplate();
    }

    @Override
    protected String getGetterName(ClientModel model, ClientModelProperty property) {
        return FLUENT_MODEL_TEMPLATE.getGetterName(model, property);
    }

    @Override
    protected boolean validateOnParentModel(String parentModelName) {
        return FLUENT_MODEL_TEMPLATE.validateOnParentModel(parentModelName);
    }
}
