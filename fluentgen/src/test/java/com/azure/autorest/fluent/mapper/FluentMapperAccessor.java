// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.model.clientmodel.Client;

public class FluentMapperAccessor {

    private final FluentMapper fluentMapper;

    public FluentMapperAccessor(FluentMapper fluentMapper) {
        this.fluentMapper = fluentMapper;
    }

    public FluentClient basicMap(CodeModel codeModel, Client client) {
        return fluentMapper.basicMap(codeModel, client);
    }
}
