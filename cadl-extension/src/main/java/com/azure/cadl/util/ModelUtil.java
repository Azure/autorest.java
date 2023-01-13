// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl.util;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.autorest.model.clientmodel.UnionModel;
import com.azure.autorest.util.ClientModelUtil;

public class ModelUtil {

    public static boolean isGeneratingModel(ClientModel model) {
        return model.getImplementationDetails() != null
                && model.getImplementationDetails().isConvenienceMethod()
                && !(isModelUsedOnlyInException(model.getImplementationDetails()))
                && !(isAnonymousModel(model.getImplementationDetails()));
    }

    public static boolean isGeneratingModel(EnumType model) {
        return model.getImplementationDetails() != null
                && model.getImplementationDetails().isConvenienceMethod()
                && !(isModelUsedOnlyInException(model.getImplementationDetails()));
    }

    public static boolean isGeneratingModel(ClientResponse response) {
        IType bodyType = response.getBodyType();
        boolean ret = ClientModelUtil.isClientModel(bodyType);
        if (ret) {
            ClassType classType = (ClassType) bodyType;
            ClientModel model = ClientModelUtil.getClientModel(classType.getName());
            if (model != null) {
                ret = isGeneratingModel(model);
            }
        }
        return ret;
    }

    public static boolean isGeneratingModel(UnionModel model) {
        return model.getImplementationDetails() != null
                && model.getImplementationDetails().isConvenienceMethod()
                && !(isModelUsedOnlyInException(model.getImplementationDetails()))
                && !(isAnonymousModel(model.getImplementationDetails()));
    }

    private static boolean isModelUsedOnlyInException(ImplementationDetails implementationDetails) {
        return (implementationDetails.isException() && !implementationDetails.isInput() && !implementationDetails.isOutput());
    }

    private static boolean isAnonymousModel(ImplementationDetails implementationDetails) {
        return (implementationDetails.getUsages() != null && implementationDetails.getUsages().contains(ImplementationDetails.Usage.ANONYMOUS));
    }
}
