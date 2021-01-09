/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.LocalVariable;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceLocalVariables;
import com.azure.autorest.fluent.model.clientmodel.immutablemodel.ImmutableMethod;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.core.http.rest.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionMethodOperationByIdTemplate implements ImmutableMethod {

    private final MethodTemplate methodTemplate;
    private final String name;

    public CollectionMethodOperationByIdTemplate(FluentResourceModel model, String name,
                                                 List<MethodParameter> pathParameters, UrlPathSegments urlPathSegments, boolean includeContextParameter,
                                                 ResourceLocalVariables resourceLocalVariables,
                                                 FluentCollectionMethod collectionMethod) {
        if (includeContextParameter) {
            name += Utils.METHOD_POSTFIX_WITH_RESPONSE;
        }
        this.name = name;

        final ResourceLocalVariables localVariables = resourceLocalVariables.getDeduplicatedLocalVariables(new HashSet<>(Collections.singleton(ModelNaming.METHOD_PARAMETER_NAME_ID)));
        final boolean removeResponseInReturnType = !includeContextParameter;
        final IType returnType = getReturnType(collectionMethod.getFluentReturnType(), removeResponseInReturnType);
        final boolean responseInReturnTypeRemoved = returnType != collectionMethod.getFluentReturnType();

        final List<ClientMethodParameter> parameters = new ArrayList<>();
        // id parameter
        parameters.add(new ClientMethodParameter.Builder()
                .name(ModelNaming.METHOD_PARAMETER_NAME_ID)
                .description("the resource ID.")
                .wireType(ClassType.String)
                .annotations(new ArrayList<>())
                .isConstant(false)
                .defaultValue(null)
                .fromClient(false)
                .isFinal(false)
                .isRequired(true)
                .build());
        if (includeContextParameter) {
            // optional parameters
            Set<String> pathParameterNames = pathParameters.stream()
                    .map(p -> p.getClientMethodParameter().getName())
                    .collect(Collectors.toSet());
            parameters.addAll(collectionMethod.getInnerClientMethod().getMethodParameters().stream()
                    .filter(p -> !pathParameterNames.contains(p.getName()))
                    .collect(Collectors.toList()));
        }

        // method invocation
        Set<ClientMethodParameter> parametersSet = new HashSet<>(parameters);
        List<ClientMethodParameter> methodParameters = collectionMethod.getInnerClientMethod().getMethodParameters();
        String argumentsLine = methodParameters.stream()
                .map(p -> FluentUtils.getLocalMethodArgument(p, parametersSet, localVariables, model, collectionMethod))
                .collect(Collectors.joining(", "));
        String methodInvocation = String.format("%1$s(%2$s)", collectionMethod.getMethodName(), argumentsLine);

        List<UrlPathSegments.ParameterSegment> segments = urlPathSegments.getReverseParameterSegments();
        Collections.reverse(segments);
        Map<String, String> urlSegmentNameByParameterName = urlPathSegments.getReverseParameterSegments().stream()
                .collect(Collectors.toMap(UrlPathSegments.ParameterSegment::getParameterName, UrlPathSegments.ParameterSegment::getSegmentName));

        String afterInvocationCode = responseInReturnTypeRemoved ? ".getValue()" : "";

        // a dummy client method only for generating javadoc
        ClientMethod dummyClientMethodForJavadoc = new ClientMethod.Builder()
                .proxyMethod(collectionMethod.getInnerProxyMethod())
                .name(name)
                .returnValue(new ReturnValue(returnType == PrimitiveType.Void ? "" : collectionMethod.getInnerClientMethod().getReturnValue().getDescription(), returnType))
                .parameters(parameters)
                .description(collectionMethod.getInnerClientMethod().getDescription())
                .build();

        methodTemplate = MethodTemplate.builder()
                .comment(commentBlock -> ClientMethodTemplate.generateJavadoc(dummyClientMethodForJavadoc, commentBlock, dummyClientMethodForJavadoc.getProxyMethod(), true))
                .methodSignature(this.getMethodSignature(returnType, parameters))
                .method(block -> {
                    // init path parameters from resource id
                    pathParameters.forEach(p -> {
                        String urlSegmentName = urlSegmentNameByParameterName.get(p.getSerializedName());
                        String valueFromIdText;
                        if (urlPathSegments.hasScope()) {
                            valueFromIdText = String.format("Utils.getValueFromIdByParameterName(%1$s, \"%2$s\", \"%3$s\")",
                                    ModelNaming.METHOD_PARAMETER_NAME_ID, urlPathSegments.getPath(), p.getSerializedName());
                        } else {
                            valueFromIdText = String.format("Utils.getValueFromIdByName(%1$s, \"%2$s\")", ModelNaming.METHOD_PARAMETER_NAME_ID, urlSegmentName);
                        }
                        if (p.getClientMethodParameter().getClientType() != ClassType.String) {
                            valueFromIdText = String.format("%1$s.fromString(%2$s)", p.getClientMethodParameter().getClientType().toString(), valueFromIdText);
                        }
                        LocalVariable var = localVariables.getLocalVariableByMethodParameter(p.getClientMethodParameter());
                        block.line(String.format("%1$s %2$s = %3$s;", var.getVariableType().toString(), var.getName(), valueFromIdText));

                        String segmentNameForErrorPrompt = urlSegmentName.isEmpty() ? p.getSerializedName() : urlSegmentName;
                        block.ifBlock(String.format("%1$s == null", var.getName()), ifBlock -> {
                            String errorMessageExpr = String.format("String.format(\"The resource ID '%%s' is not valid. Missing path segment '%1$s'.\", %2$s)",
                                    segmentNameForErrorPrompt, ModelNaming.METHOD_PARAMETER_NAME_ID);
                            ifBlock.line(String.format(
                                    "throw logger.logExceptionAsError(new IllegalArgumentException(%1$s));",
                                    errorMessageExpr));
                        });
                    });

                    if (!includeContextParameter) {
                        // init local variables to default value
                        for (LocalVariable var : localVariables.getLocalVariablesMap().values()) {
                            if (var.getParameterLocation() == RequestParameterLocation.Query) {
                                block.line(String.format("%1$s %2$s = %3$s;", var.getVariableType().toString(), var.getName(), var.getInitializeExpression()));
                            }
                        }
                    }

                    if (returnType == PrimitiveType.Void) {
                        block.line(String.format("this.%1$s%2$s;",
                                methodInvocation,
                                afterInvocationCode));
                    } else {
                        block.methodReturn(String.format("this.%1$s%2$s",
                                methodInvocation,
                                afterInvocationCode));
                    }
                })
                .build();
    }

    @Override
    public MethodTemplate getMethodTemplate() {
        return methodTemplate;
    }

    private static IType getReturnType(IType collectionMethodReturnType, boolean removeResponse) {
        IType returnType;
        if (removeResponse) {
            if (collectionMethodReturnType instanceof GenericType && Response.class.getSimpleName().equals(((GenericType) collectionMethodReturnType).getName())) {
                returnType = ((GenericType) collectionMethodReturnType).getTypeArguments()[0];
                if (returnType == ClassType.Void) {
                    returnType = PrimitiveType.Void;
                }
            } else {
                // LRO would not have Response<T> for method takes Context, usually happens to delete method
                returnType = collectionMethodReturnType;
            }
        } else {
            returnType = collectionMethodReturnType;
        }
        return returnType;
    }

    private String getMethodSignature(IType returnType, List<ClientMethodParameter> parameters) {
        String parameterText = parameters.stream()
                .map(p -> String.format("%1$s %2$s", p.getClientType().toString(), p.getName()))
                .collect(Collectors.joining(", "));
        return String.format("%1$s %2$s(%3$s)",
                returnType.toString(), this.name, parameterText);
    }
}
