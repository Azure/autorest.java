// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.resiliency.servicedriven;

import org.junit.jupiter.api.Test;

public class ServiceDrivenTests {

    private final ResiliencyServiceDrivenClient client1 = new ResiliencyServiceDrivenClientBuilder()
            .serviceDeploymentVersion("v2")
            .serviceVersion(ServiceDrivenServiceVersion.V1)
            .buildClient();

    private final ResiliencyServiceDrivenClient client2 = new ResiliencyServiceDrivenClientBuilder()
            .serviceDeploymentVersion("v2")
            .serviceVersion(ServiceDrivenServiceVersion.V2)
            .buildClient();

    @Test
    public void testAddOptionalParamFromNone() {
        client1.fromNone();
        client2.fromNone("new");
    }

    @Test
    public void testAddOptionalParamFromOneRequired() {
        client1.fromOneRequired("required");
        client2.fromOneRequired("required", "new");
    }

    // this case does not work without resiliency on "added"
    // at present, v2 client does not have overload "fromOneOptional(String)"
//    @Test
//    public void testAddOptionalParamFromOneOptional() {
//        client1.fromOneOptional("optional");
//        client2.fromOneOptional("optional", "new");
//    }

    @Test
    public void testAddOperation() {
        client2.addOperation();
    }
}
