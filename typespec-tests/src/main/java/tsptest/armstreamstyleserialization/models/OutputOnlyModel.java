// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package tsptest.armstreamstyleserialization.models;

import tsptest.armstreamstyleserialization.fluent.models.OutputOnlyModelInner;

/**
 * An immutable client-side representation of OutputOnlyModel.
 */
public interface OutputOnlyModel {
    /**
     * Gets the kind property: Discriminator property for OutputOnlyModel.
     * 
     * @return the kind value.
     */
    String kind();

    /**
     * Gets the name property: The name property.
     * 
     * @return the name value.
     */
    String name();

    /**
     * Gets the id property: The id property.
     * 
     * @return the id value.
     */
    String id();

    /**
     * Gets the title property: The title property.
     * 
     * @return the title value.
     */
    String title();

    /**
     * Gets the dog property: The dog property.
     * 
     * @return the dog value.
     */
    Golden dog();

    /**
     * Gets the inner tsptest.armstreamstyleserialization.fluent.models.OutputOnlyModelInner object.
     * 
     * @return the inner object.
     */
    OutputOnlyModelInner innerModel();
}
