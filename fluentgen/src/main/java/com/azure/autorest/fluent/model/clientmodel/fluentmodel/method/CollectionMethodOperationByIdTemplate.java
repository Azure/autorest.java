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

        final boolean removeResponse = !includeContextParameter;
        final IType returnType = getReturnType(removeResponse, collectionMethod.getFluentReturnType());

        final List<ClientMethodParameter> parameters = new ArrayList<>();
        // id parameter
        parameters.add(new ClientMethodParameter.Builder()
                .name("id")
                .description("the id of the resource.")
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
            parameters.addAll(collectionMethod.getInnerClientMethod().getParameters().stream()
                    .filter(p -> !pathParameterNames.contains(p.getName()))
                    .collect(Collectors.toList()));
        }

        // method invocation
        Set<ClientMethodParameter> parametersSet = new HashSet<>(parameters);
        List<ClientMethodParameter> methodParameters = collectionMethod.getInnerClientMethod().getMethodParameters();
        String argumentsLine = methodParameters.stream()
                .map(p -> {
                    if (parametersSet.contains(p)) {
                        return p.getName();
                    } else if (model.getInnerModel().getName().equals(p.getClientType().toString())) {
                        return ModelNaming.MODEL_PROPERTY_INNER;
                    } else if (ClassType.Context == p.getClientType()) {
                        return "Context.NONE";
                    } else {
                        LocalVariable localVariable = resourceLocalVariables.getLocalVariableByMethodParameter(p);
                        if (localVariable == null) {
                            throw new IllegalStateException(String.format("local variable not found for method %1$s, model %2$s, parameter %3$s, available local variables %4$s",
                                    collectionMethod.getInnerClientMethod().getName(),
                                    model.getName(),
                                    p.getName(),
                                    resourceLocalVariables.getLocalVariablesMap().entrySet().stream().collect(Collectors.toMap(e -> e.getKey().getName(), e -> e.getValue().getName()))));
                        }
                        return localVariable.getName();
                    }
                })
                .collect(Collectors.joining(", "));
        String methodInvocation = String.format("%1$s(%2$s)", collectionMethod.getInnerClientMethod().getName(), argumentsLine);

        List<UrlPathSegments.ParameterSegment> segments = urlPathSegments.getReverseParameterSegments();
        Collections.reverse(segments);
        Map<String, String> urlSegmentNameByParameterName = urlPathSegments.getReverseParameterSegments().stream()
                .collect(Collectors.toMap(UrlPathSegments.ParameterSegment::getParameterName, UrlPathSegments.ParameterSegment::getSegmentName));

        String afterInvocationCode = removeResponse ? ".getValue()" : "";

        // a dummy client method only for generating javadoc
        ClientMethod dummyClientMethodForJavadoc = new ClientMethod.Builder()
                .proxyMethod(collectionMethod.getInnerProxyMethod())
                .name(name)
                .returnValue(new ReturnValue(collectionMethod.getInnerClientMethod().getReturnValue().getDescription(), returnType))
                .parameters(parameters)
                .description(collectionMethod.getInnerClientMethod().getDescription())
                .build();

        methodTemplate = MethodTemplate.builder()
                .comment(commentBlock -> ClientMethodTemplate.generateJavadoc(dummyClientMethodForJavadoc, commentBlock, dummyClientMethodForJavadoc.getProxyMethod(), true))
                .methodSignature(this.getMethodSignature(returnType, parameters))
                .method(block -> {
                    // init path parameters from resource id
                    pathParameters.forEach(p -> {
                        String valueFromIdText = String.format("Utils.getValueFromIdByName(id, \"%1$s\")", urlSegmentNameByParameterName.get(p.getSerializedName()));
                        if (p.getClientMethodParameter().getClientType() != ClassType.String) {
                            valueFromIdText = String.format("%1$s.fromString(%2$s)", p.getClientMethodParameter().getClientType().toString(), valueFromIdText);
                        }
                        LocalVariable var = resourceLocalVariables.getLocalVariableByMethodParameter(p.getClientMethodParameter());
                        block.line(String.format("%1$s %2$s = %3$s;", var.getVariableType().toString(), var.getName(), valueFromIdText));
                    });

                    if (!includeContextParameter) {
                        // init local variables to default value
                        for (LocalVariable var : resourceLocalVariables.getLocalVariablesMap().values()) {
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

    private static IType getReturnType(boolean removeResponse, IType collectionMethodReturnType) {
        IType returnType;
        if (removeResponse) {
            if (collectionMethodReturnType instanceof GenericType && Response.class.getSimpleName().equals(((GenericType) collectionMethodReturnType).getName())) {
                returnType = ((GenericType) collectionMethodReturnType).getTypeArguments()[0];
                if (returnType == ClassType.Void) {
                    returnType = PrimitiveType.Void;
                }
            } else {
                throw new IllegalStateException("type error, return type should be of type Response<T>");
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
