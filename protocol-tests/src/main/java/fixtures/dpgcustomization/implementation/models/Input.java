// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.dpgcustomization.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Input model. */
@Fluent
public final class Input {
    /*
     * The hello property.
     */
    @JsonProperty(value = "hello", required = true)
    private String hello;

    /**
     * Get the hello property: The hello property.
     *
     * @return the hello value.
     */
    public String getHello() {
        return this.hello;
    }

    /**
     * Set the hello property: The hello property.
     *
     * @param hello the hello value to set.
     * @return the Input object itself.
     */
    public Input setHello(String hello) {
        this.hello = hello;
        return this;
    }
}
