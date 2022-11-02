// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;

public class FluentMethodMockUnitTest {

    // method with mock data
    private final FluentMethodExample fluentMethodExample;
    private final FluentResourceCreateExample fluentResourceCreateExample;

    private final FluentResourceCollection resourceCollection;
    private final FluentCollectionMethod collectionMethod;

    private final ProxyMethodExample.Response response;

    // for pageable, following variable and data is the first element
    private final String responseVerificationVariableName;
    private final ExampleNode responseVerificationNode;

    public FluentMethodMockUnitTest(
            FluentMethodExample fluentMethodExample,
            FluentResourceCollection resourceCollection, FluentCollectionMethod fluentCollectionMethod,
            ProxyMethodExample.Response response,
            String responseVerificationVariableName, ExampleNode responseVerificationNode) {

        this.fluentMethodExample = fluentMethodExample;
        this.fluentResourceCreateExample = null;
        this.resourceCollection = resourceCollection;
        this.collectionMethod = fluentCollectionMethod;
        this.response = response;
        this.responseVerificationVariableName = responseVerificationVariableName;
        this.responseVerificationNode = responseVerificationNode;
    }

    public FluentMethodMockUnitTest(
            FluentResourceCreateExample fluentResourceCreateExample,
            FluentResourceCollection resourceCollection, FluentCollectionMethod collectionMethod,
            ProxyMethodExample.Response response,
            String responseVerificationVariableName, ExampleNode responseVerificationNode) {

        this.fluentMethodExample = null;
        this.fluentResourceCreateExample = fluentResourceCreateExample;
        this.resourceCollection = resourceCollection;
        this.collectionMethod = collectionMethod;
        this.response = response;
        this.responseVerificationVariableName = responseVerificationVariableName;
        this.responseVerificationNode = responseVerificationNode;
    }

    /**
     * @return example of method in collection, mutually exclusive with {@link #getFluentResourceCreateExample()}.
     */
    public FluentMethodExample getFluentMethodExample() {
        return fluentMethodExample;
    }

    /**
     * @return example of resource creation, mutually exclusive with {@link #getFluentMethodExample()}.
     */
    public FluentResourceCreateExample getFluentResourceCreateExample() {
        return fluentResourceCreateExample;
    }

    public FluentResourceCollection getResourceCollection() {
        return resourceCollection;
    }

    public FluentCollectionMethod getCollectionMethod() {
        return collectionMethod;
    }

    /**
     * @return the mock data response with status code and "body" and "headers".
     */
    public ProxyMethodExample.Response getResponse() {
        return response;
    }

    /**
     * @return variable name for verification, e.g. "response", "response.iterator().next()" for pageable.
     */
    public String getResponseVerificationVariableName() {
        return responseVerificationVariableName;
    }

    /**
     * @return example node as data of the variable for verification.
     */
    public ExampleNode getResponseVerificationNode() {
        return responseVerificationNode;
    }
}
