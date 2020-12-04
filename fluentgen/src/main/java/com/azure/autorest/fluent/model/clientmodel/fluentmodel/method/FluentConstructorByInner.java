/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceLocalVariables;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentConstructorByInner extends FluentMethod {

    private final List<MethodParameter> pathParameters;
    private final IType managerType;

    public FluentConstructorByInner(FluentResourceModel model, FluentMethodType type,
                                    List<MethodParameter> pathParameters, ResourceLocalVariables resourceLocalVariables,
                                    IType managerType, UrlPathSegments urlPathSegments) {
        super(model, type);

        this.pathParameters = pathParameters;
        this.managerType = managerType;

        this.implementationReturnValue = new ReturnValue("", model.getImplementationType());

        this.implementationMethodTemplate = MethodTemplate.builder()
                .visibility(JavaVisibility.PackagePrivate)
                .methodSignature(this.getImplementationMethodSignature())
                .method(block -> {
                    block.line(String.format("this.%1$s = %2$s;", ModelNaming.MODEL_PROPERTY_INNER, ModelNaming.MODEL_PROPERTY_INNER));
                    block.line(String.format("this.%1$s = %2$s;", ModelNaming.MODEL_PROPERTY_MANAGER, ModelNaming.MODEL_PROPERTY_MANAGER));

                    List<UrlPathSegments.ParameterSegment> segments = urlPathSegments.getReverseParameterSegments();
                    Collections.reverse(segments);
                    Map<String, String> urlSegmentNameByParameterName = urlPathSegments.getReverseParameterSegments().stream()
                            .collect(Collectors.toMap(UrlPathSegments.ParameterSegment::getParameterName, UrlPathSegments.ParameterSegment::getSegmentName));

                    // init from resource id
                    pathParameters.forEach(p -> {
                        String valueFromIdText = String.format("Utils.getValueFromIdByName(%1$s.id(), \"%2$s\")",
                                ModelNaming.MODEL_PROPERTY_INNER, urlSegmentNameByParameterName.get(p.getSerializedName()));
                        if (p.getClientMethodParameter().getClientType() != ClassType.String) {
                            valueFromIdText = String.format("%1$s.fromString(%2$s)", p.getClientMethodParameter().getClientType().toString(), valueFromIdText);
                        }
                        block.line(String.format("this.%1$s = %2$s;", resourceLocalVariables.getLocalVariableByMethodParameter(p.getClientMethodParameter()).getName(), valueFromIdText));
                    });
                })
                .build();
    }

    @Override
    public String getImplementationMethodSignature() {
        return String.format("%1$s(%2$s %3$s, %4$s %5$s)",
                implementationReturnValue.getType().toString(),
                fluentResourceModel.getInnerModel().getName(), ModelNaming.MODEL_PROPERTY_INNER,
                managerType.toString(), ModelNaming.MODEL_PROPERTY_MANAGER);
    }

    @Override
    protected String getBaseMethodSignature() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeJavadoc(JavaJavadocComment commentBlock) {
        // NOOP
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (includeImplementationImports) {
            pathParameters.forEach(p -> p.getClientMethodParameter().addImportsTo(imports, false));
            managerType.addImportsTo(imports, false);
        }
    }
}
