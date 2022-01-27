// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.fluent.mapper.FluentMapper;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.javamodel.FluentJavaPackage;
import com.azure.autorest.model.clientmodel.Client;

public class FluentGenAccessor {

    private final FluentGen fluentgen;

    public FluentGenAccessor(FluentGen fluentgen) {
        this.fluentgen = fluentgen;
    }

    public CodeModel handleYaml(String yamlContent) {
        return fluentgen.handleYaml(yamlContent);
    }

    public Client handleMap(CodeModel codeModel) {
        return fluentgen.handleMap(codeModel);
    }

    public FluentJavaPackage handleTemplate(Client client) {
        return fluentgen.handleTemplate(client);
    }

    public FluentClient handleFluentLite(CodeModel codeModel, Client client, FluentJavaPackage javaPackage) {
        return fluentgen.handleFluentLite(codeModel, client, javaPackage);
    }

    public FluentMapper getFluentMapper() {
        return fluentgen.getFluentMapper();
    }
}
