// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.TypeReference;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ConvenienceAsyncMethodTemplate extends ConvenienceMethodTemplateBase {

    private static final ConvenienceAsyncMethodTemplate INSTANCE = new ConvenienceAsyncMethodTemplate();

    protected ConvenienceAsyncMethodTemplate() {
    }

    public static ConvenienceAsyncMethodTemplate getInstance() {
        return INSTANCE;
    }

    public void addImports(Set<String> imports, List<ConvenienceMethod> convenienceMethods) {
        if (!CoreUtils.isNullOrEmpty(convenienceMethods)) {
            JavaSettings settings = JavaSettings.getInstance();
            convenienceMethods.stream().flatMap(m -> m.getConvenienceMethods().stream())
                    .forEach(m -> m.addImportsTo(imports, false, settings));

            ClassType.BinaryData.addImportsTo(imports, false);
            ClassType.RequestOptions.addImportsTo(imports, false);
            imports.add(Collectors.class.getName());
            imports.add(Objects.class.getName());
            imports.add(FluxUtil.class.getName());

            // collection format
            imports.add(JacksonAdapter.class.getName());
            imports.add(CollectionFormat.class.getName());
            imports.add(TypeReference.class.getName());

            // pageable
            imports.add(PagedResponse.class.getName());
            imports.add(PagedResponseBase.class.getName());
            imports.add(Flux.class.getName());
        }
    }

    @Override
    protected boolean isMethodIncluded(ClientMethod method) {
        return isMethodAsync(method) && isMethodVisible(method) && !method.isImplementationOnly();
    }

    @Override
    protected boolean isMethodIncluded(ConvenienceMethod method) {
        return isMethodAsync(method.getProtocolMethod()) && isMethodVisible(method.getProtocolMethod())
                // for LRO, we actually choose the protocol method of "WithModel"
                && (method.getProtocolMethod().getType() != ClientMethodType.LongRunningBeginAsync || (method.getProtocolMethod().getImplementationDetails() != null && method.getProtocolMethod().getImplementationDetails().isImplementationOnly()))
                && method.getProtocolMethod().getMethodParameters().stream().noneMatch(p -> p.getClientType() == ClassType.Context);
    }

    protected void writeInvocationAndConversion(
            ClientMethod convenienceMethod, ClientMethod protocolMethod,
            String invocationExpression,
            JavaBlock methodBlock) {

        ClientMethodType methodType = protocolMethod.getType();

        IType responseBodyType = getResponseBodyType(convenienceMethod);

        String returnTypeConversionExpression = expressionConvertFromBinaryData(responseBodyType);

        if (methodType == ClientMethodType.PagingAsync) {
            String expressionMapFromBinaryData = expressionMapFromBinaryData(responseBodyType);
            if (expressionMapFromBinaryData == null) {
                // no need to do the map
                methodBlock.methodReturn(String.format("%1$s(%2$s)", getMethodName(protocolMethod), invocationExpression));
            } else {
                methodBlock.line("PagedFlux<BinaryData> pagedFluxResponse = %1$s(%2$s);", getMethodName(protocolMethod), invocationExpression);

                methodBlock.methodReturn(String.format(
                        "PagedFlux.create(() -> (continuationToken, pageSize) -> {\n" +
                                "    Flux<PagedResponse<BinaryData>> flux = (continuationToken == null)\n" +
                                "        ? pagedFluxResponse.byPage().take(1)\n" +
                                "        : pagedFluxResponse.byPage(continuationToken).take(1);\n" +
                                "    return flux.map(pagedResponse -> new PagedResponseBase<Void, %1$s>(pagedResponse.getRequest(),\n" +
                                "        pagedResponse.getStatusCode(),\n" +
                                "        pagedResponse.getHeaders(),\n" +
                                "        pagedResponse.getValue().stream().map(%2$s).collect(Collectors.toList()),\n" +
                                "        pagedResponse.getContinuationToken(),\n" +
                                "        null));\n" +
                                "})",
                        responseBodyType, expressionMapFromBinaryData));
            }
        } else if (methodType == ClientMethodType.LongRunningBeginAsync) {
            String methodName = protocolMethod.getName();
            methodBlock.methodReturn(String.format("serviceClient.%1$s(%2$s)", methodName, invocationExpression));
        } else {
            methodBlock.methodReturn(
                    String.format("%1$s(%2$s).flatMap(FluxUtil::toMono)%3$s",
                            getMethodName(protocolMethod),
                            invocationExpression,
                            returnTypeConversionExpression));
        }
    }

    private IType getResponseBodyType(ClientMethod method) {
        // Mono<T>
        return ((GenericType) method.getReturnValue().getType()).getTypeArguments()[0];
    }

    private String expressionConvertFromBinaryData(IType responseBodyType) {
        String expressionMapFromBinaryData = expressionMapFromBinaryData(responseBodyType);
        if (expressionMapFromBinaryData != null) {
            return String.format(".map(%s)", expressionMapFromBinaryData);
        } else {
            // no need to do the map
            return "";
        }
    }

    private String expressionMapFromBinaryData(IType responseBodyType) {
        String mapExpression = null;
        if (responseBodyType instanceof EnumType) {
            // enum
            mapExpression = String.format("%1$s::from%2$s", responseBodyType, ((EnumType) responseBodyType).getElementType());
        } else if (responseBodyType instanceof GenericType) {
            // generic, e.g. list, map
            mapExpression = String.format("protocolMethodData -> protocolMethodData.toObject(new TypeReference<%1$s>() {})", responseBodyType);
        } else if (responseBodyType == ClassType.BinaryData) {
            // BinaryData, no need to do the map in expressionConvertFromBinaryData
            mapExpression = null;
        } else if (isModelOrBuiltin(responseBodyType)) {
            // class
            mapExpression = String.format("protocolMethodData -> protocolMethodData.toObject(%1$s.class)", responseBodyType);
        }
        return mapExpression;
    }
}
