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
                case PagingSyncSinglePage:
                case PagingAsync:
                case PagingSync:
                case LongRunningBeginAsync:
                case LongRunningBeginSync:
                case LongRunningAsync:
                case LongRunningSync:
                case SimpleAsyncRestResponse:
                case SimpleSyncRestResponse:
                    visibility = NOT_VISIBLE;
                    break;

                case SimpleAsync:
                case SimpleSync:
                    visibility = NOT_GENERATE;
                    break;

                default:
                    visibility = super.methodVisibility(methodType, true, isProtocolMethod);
                    break;
            }
        } else {
            if (methodType == ClientMethodType.PagingAsyncSinglePage
                || methodType == ClientMethodType.SimpleSyncRestResponse
                || methodType == ClientMethodType.PagingSyncSinglePage) {
                visibility = NOT_VISIBLE;
            } else {
                visibility = super.methodVisibility(methodType, false, isProtocolMethod);
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
