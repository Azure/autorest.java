/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.update;

import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ResourceUpdate extends ResourceOperation {

    private static final Logger logger = LoggerFactory.getLogger(ResourceUpdate.class);

    private List<UpdateStage> updateStages;

    public ResourceUpdate(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                          UrlPathSegments urlPathSegments, String methodName, ClientModel bodyParameterModel) {
        super(resourceModel, resourceCollection, urlPathSegments, methodName, bodyParameterModel);
    }

    public List<UpdateStage> getUpdateStages() {
        if (updateStages != null) {
            return updateStages;
        }

        updateStages = new ArrayList<>();

        UpdateStage updateStageApply = new UpdateStageApply();

        List<ClientModelProperty> properties = this.getProperties();
        for (ClientModelProperty property : properties) {
            UpdateStage stage = new UpdateStage("With" + CodeNamer.toPascalCase(property.getName()), property);
            stage.setNextStage(updateStageApply);

            updateStages.add(stage);
        }

        return updateStages;
    }
}
