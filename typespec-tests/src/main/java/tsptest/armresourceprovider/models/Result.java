// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package tsptest.armresourceprovider.models;

import tsptest.armresourceprovider.fluent.models.ResultInner;

/**
 * An immutable client-side representation of Result.
 */
public interface Result {
    /**
     * Gets the reason property: The reason property.
     * 
     * @return the reason value.
     */
    String reason();

    /**
     * Gets the inner tsptest.armresourceprovider.fluent.models.ResultInner object.
     * 
     * @return the inner object.
     */
    ResultInner innerModel();
}
