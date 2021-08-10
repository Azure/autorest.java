/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

public interface FluentMethodExample extends FluentExample {

    /**
     * Gets the reference from entry class to the method.
     *
     * E.g. "deployments()" for collection method from Fluent Lite,
     * or "serviceClient().getDeployments()" for service client method from Fluent Premium.
     *
     * This method is used together with {@link FluentExample#getEntryType()}.
     *
     * @return the reference from entry class to the method.
     */
    String getMethodReference();

    String getMethodName();
}
