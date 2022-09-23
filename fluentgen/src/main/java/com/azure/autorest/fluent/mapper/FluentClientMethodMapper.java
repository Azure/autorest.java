// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.javamodel.JavaVisibility;

public class FluentClientMethodMapper extends ClientMethodMapper {

    private static final FluentClientMethodMapper INSTANCE = new FluentClientMethodMapper();

    public static FluentClientMethodMapper getInstance() {
        return INSTANCE;
    }

    @Override
    protected JavaVisibility methodVisibility(ClientMethodType methodType, boolean hasContextParameter, boolean isProtocolMethod) {
        JavaVisibility visibility;
        if (hasContextParameter) {
            switch (methodType) {
                case PagingAsyncSinglePage:
                case PagingAsync:
                case LongRunningBeginAsync:
                case LongRunningAsync:
                case SimpleAsyncRestResponse:
                    visibility = NOT_VISIBLE;
                    break;

                case SimpleAsync:
                case SimpleSync:
                case PagingSyncSinglePage:
                    visibility = NOT_GENERATE;
                    break;

                default:
                    visibility = super.methodVisibility(methodType, true, isProtocolMethod);
                    break;
            }
        } else {
            switch (methodType) {
                case PagingAsyncSinglePage:
                    visibility = NOT_VISIBLE;
                    break;

                case SimpleSyncRestResponse:
                case PagingSyncSinglePage:
                    visibility = NOT_GENERATE;
                    break;

                default:
                    visibility = super.methodVisibility(methodType, false, isProtocolMethod);
                    break;
            }
        }
        if (JavaSettings.getInstance().isFluentLite() && !FluentStatic.getFluentJavaSettings().isGenerateAsyncMethods()) {
            // by default, Fluent lite disable all async method
            if (visibility == JavaVisibility.Public && methodType.name().contains("Async")) {
                visibility = JavaVisibility.Private;
            }
        }
        return visibility;
    }
}
