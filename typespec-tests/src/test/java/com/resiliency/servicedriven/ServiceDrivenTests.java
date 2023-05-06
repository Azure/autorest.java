// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.resiliency.servicedriven;

import org.junit.jupiter.api.Test;

public class ServiceDrivenTests {

    private final com.resiliency.servicedriven.v1.ResiliencyServiceDrivenClient oldClient1 = new com.resiliency.servicedriven.v1.ResiliencyServiceDrivenClientBuilder()
            .serviceDeploymentVersion("v1")
            .buildClient();

    private final com.resiliency.servicedriven.v1.ResiliencyServiceDrivenClient oldClient2 = new com.resiliency.servicedriven.v1.ResiliencyServiceDrivenClientBuilder()
            .serviceDeploymentVersion("v2")
            .buildClient();

    private final ResiliencyServiceDrivenClient client2v1 = new ResiliencyServiceDrivenClientBuilder()
            .serviceDeploymentVersion("v2")
            .serviceVersion(ServiceDrivenServiceVersion.V1)
            .buildClient();

    private final ResiliencyServiceDrivenClient client2v2 = new ResiliencyServiceDrivenClientBuilder()
            .serviceDeploymentVersion("v2")
            .serviceVersion(ServiceDrivenServiceVersion.V2)
            .buildClient();

    @Test
    public void testAddOptionalParamFromNone() {
        oldClient1.fromNone();
        oldClient2.fromNone();

        client2v1.fromNone();
        client2v2.fromNone("new");
    }

    @Test
    public void testAddOptionalParamFromOneRequired() {
        oldClient1.fromOneRequired("required");
        oldClient2.fromOneRequired("required");
        
        client2v1.fromOneRequired("required");
        client2v2.fromOneRequired("required", "new");
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
        client2v2.addOperation();
    }
}
