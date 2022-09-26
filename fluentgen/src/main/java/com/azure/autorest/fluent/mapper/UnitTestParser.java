// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentMethodUnitTest;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.util.ModelExampleUtil;
import com.azure.autorest.util.ModelTestCaseUtil;
import com.azure.autorest.util.PossibleCredentialException;
import com.azure.core.http.HttpMethod;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitTestParser extends ExampleParser {

    private static final Logger LOGGER = new PluginLogger(FluentGen.getPluginInstance(), UnitTestParser.class);

    public List<FluentMethodUnitTest> parseResourceCollectionForUnitTest(FluentResourceCollection resourceCollection) {
        List<FluentMethodUnitTest> fluentMethodUnitTests = new ArrayList<>();

        resourceCollection.getMethodsForTemplate().forEach(m -> {
            FluentMethodUnitTest example = parseMethod(resourceCollection, m);
            if (example != null) {
                fluentMethodUnitTests.add(example);
            }
        });
        resourceCollection.getResourceCreates().forEach(rc -> {
            FluentMethodUnitTest example = parseResourceCreate(resourceCollection, rc);
            if (example != null) {
                fluentMethodUnitTests.add(example);
            }
        });
        return fluentMethodUnitTests;
    }

    private static FluentMethodUnitTest parseResourceCreate(FluentResourceCollection collection, ResourceCreate resourceCreate) {
        FluentMethodUnitTest unitTest = null;

        try {
            List<FluentCollectionMethod> collectionMethods = resourceCreate.getMethodReferences();
            for (FluentCollectionMethod collectionMethod : collectionMethods) {
                ClientMethod clientMethod = collectionMethod.getInnerClientMethod();
                if (requiresExample(clientMethod)) {
                    List<MethodParameter> methodParameters = getParameters(clientMethod);
                    MethodParameter requestBodyParameter = findRequestBodyParameter(methodParameters);
                    ProxyMethodExample proxyMethodExample = createProxyMethodExample(clientMethod, methodParameters);
                    FluentResourceCreateExample resourceCreateExample =
                            parseResourceCreate(collection, resourceCreate, proxyMethodExample, methodParameters, requestBodyParameter);

                    ResponseInfo responseInfo = createProxyMethodExampleResponse(clientMethod);
                    unitTest = new FluentMethodUnitTest(resourceCreateExample, collection, collectionMethod,
                            responseInfo.responseExample, responseInfo.verificationObjectName, responseInfo.verificationNode);

                    break;
                }
            }
        } catch (PossibleCredentialException e) {
            LOGGER.warn("Skip unit test for resource '{}', caused by key '{}'", resourceCreate.getResourceModel().getInnerModel().getName(), e.getKeyName());
        }
        return unitTest;
    }

    private static FluentMethodUnitTest parseMethod(FluentResourceCollection collection, FluentCollectionMethod collectionMethod) {
        FluentMethodUnitTest unitTest = null;

        try {
            ClientMethod clientMethod = collectionMethod.getInnerClientMethod();
            if (requiresExample(clientMethod)) {
                List<MethodParameter> methodParameters = getParameters(clientMethod);
                ProxyMethodExample proxyMethodExample = createProxyMethodExample(clientMethod, methodParameters);
                FluentCollectionMethodExample collectionMethodExample =
                        parseMethodForExample(collection, collectionMethod, methodParameters, proxyMethodExample.getName(), proxyMethodExample);

                ResponseInfo responseInfo = createProxyMethodExampleResponse(clientMethod);
                unitTest = new FluentMethodUnitTest(collectionMethodExample, collection, collectionMethod,
                        responseInfo.responseExample, responseInfo.verificationObjectName, responseInfo.verificationNode);
            }
        } catch (PossibleCredentialException e) {
            LOGGER.warn("Skip unit test for method '{}', caused by key '{}'", collectionMethod.getMethodName(), e.getKeyName());
        }
        return unitTest;
    }

    private static ProxyMethodExample createProxyMethodExample(ClientMethod clientMethod, List<MethodParameter> methodParameters) {
        ProxyMethodExample.Builder example = new ProxyMethodExample.Builder()
                .name(clientMethod.getName());

        for (MethodParameter methodParameter : methodParameters) {
            // create mock data for each parameter

            String serializedName = methodParameter.getSerializedName();
            if (serializedName == null && methodParameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
                serializedName = methodParameter.getProxyMethodParameter().getName();
            }

            Object jsonParam = ModelTestCaseUtil.jsonFromType(0, methodParameter.getProxyMethodParameter().getWireType());

            example.parameter(serializedName, jsonParam);
        }

        return example.build();
    }

    private static class ResponseInfo {
        private final ProxyMethodExample.Response responseExample;
        private final ExampleNode verificationNode;
        private final String verificationObjectName;

        private ResponseInfo(ProxyMethodExample.Response responseExample,
                             ExampleNode verificationNode, String verificationObjectName) {
            this.responseExample = responseExample;
            this.verificationNode = verificationNode;
            this.verificationObjectName = verificationObjectName;
        }
    }

    private static ResponseInfo createProxyMethodExampleResponse(ClientMethod clientMethod) {
        // create a mock response

        int statusCode = clientMethod.getProxyMethod().getResponseExpectedStatusCodes().iterator().next();
        Object jsonObject;
        ExampleNode verificationNode;
        String verificationObjectName;
        if (clientMethod.getType() == ClientMethodType.PagingSync) {
            // pageable
            if (clientMethod.getReturnValue().getType() instanceof GenericType) {
                IType elementType = ((GenericType) clientMethod.getReturnValue().getType()).getTypeArguments()[0];

                Object firstJsonObjectInPageable = ModelTestCaseUtil.jsonFromType(0, elementType);
                // put to first element in array
                Map<String, Object> jsonMap = new HashMap<>();
                jsonMap.put(clientMethod.getMethodPageDetails().getRawItemName(), Collections.singletonList(firstJsonObjectInPageable));

                jsonObject = jsonMap;

                // pageable will verify the first element
                verificationObjectName = "response.iterator().next()";
                verificationNode = ModelExampleUtil.parseNode(elementType, firstJsonObjectInPageable);
            } else {
                throw new IllegalStateException("Response of pageable operation must be PagedIterable<>");
            }
        } else {
            // simple or LRO
            jsonObject = ModelTestCaseUtil.jsonFromType(0, clientMethod.getReturnValue().getType());

            if (jsonObject == null) {
                jsonObject = new Object();
            }
            if (clientMethod.getType() == ClientMethodType.LongRunningSync) {
                // LRO, hack to set properties.provisioningState == Succeeded, so that LRO can stop at activation operation
                setProvisioningState(jsonObject);
            }

            verificationObjectName = "response";
            verificationNode = ModelExampleUtil.parseNode(clientMethod.getReturnValue().getType(), jsonObject);
        }
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("body", jsonObject);
        return new ResponseInfo(new ProxyMethodExample.Response(statusCode, responseObject), verificationNode, verificationObjectName);
    }

    private static boolean requiresExample(ClientMethod clientMethod) {
        if (clientMethod.getType() == ClientMethodType.SimpleSync
                || clientMethod.getType() == ClientMethodType.PagingSync
                // limit the scope of LRO to status code of 200
                || (clientMethod.getType() == ClientMethodType.LongRunningSync
                && clientMethod.getProxyMethod().getResponseExpectedStatusCodes().contains(200)
                // also azure-core-management does not support LRO from GET
                && clientMethod.getProxyMethod().getHttpMethod() != HttpMethod.GET)) {
            // generate example for the method with full parameters
            return clientMethod.getParameters().stream().anyMatch(p -> ClassType.Context.equals(p.getClientType()));
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static void setProvisioningState(Object jsonObject) {
        // properties.provisioningState = Succeeded
        if ((jsonObject instanceof Map) && ((Map<String, Object>) jsonObject).containsKey("properties")) {
            Object propertiesObject = ((Map<String, Object>) jsonObject).get("properties");
            if ((propertiesObject instanceof Map) && ((Map<String, Object>) propertiesObject).containsKey("provisioningState")) {
                Object provisioningStateObject = ((Map<String, Object>) propertiesObject).get("provisioningState");
                if (provisioningStateObject instanceof String) {
                    ((Map<String, Object>) propertiesObject).put("provisioningState", "Succeeded");
                }
            }
        }
    }
}
