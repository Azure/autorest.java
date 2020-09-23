/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.javamodel.JavaVisibility;

public class FluentClientMethodMapper extends ClientMethodMapper {

    private static final FluentClientMethodMapper instance = new FluentClientMethodMapper();

    public static FluentClientMethodMapper getInstance() {
        return instance;
    }

    @Override
    protected JavaVisibility methodVisibility(ClientMethodType methodType, boolean hasContextParameter) {
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
                    visibility = NOT_GENERATE;
                    break;

                default:
                    visibility = super.methodVisibility(methodType, hasContextParameter);
                    break;
            }
        } else {
            switch (methodType) {
                case PagingAsyncSinglePage:
                    visibility = NOT_VISIBLE;
                    break;

                default:
                    visibility = super.methodVisibility(methodType, hasContextParameter);
                    break;
            }
        }
        return visibility;
    }
}
