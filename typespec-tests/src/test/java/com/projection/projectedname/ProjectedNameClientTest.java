// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.projection.projectedname;

import com.projection.projectedname.models.ClientModel;
import com.projection.projectedname.models.ClientProjectedNameModel;
import com.projection.projectedname.models.JsonAndClientProjectedNameModel;
import com.projection.projectedname.models.JsonProjectedNameModel;
import com.projection.projectedname.models.LanguageProjectedNameModel;
import com.projection.projectedname.models.JavaModel;
import org.junit.jupiter.api.Test;

class ProjectedNameClientTest {

    private final PropertyClient propertyClient = new ProjectedNameClientBuilder().buildPropertyClient();
    private final ProjectedNameClient client = new ProjectedNameClientBuilder().buildClient();
    private final ModelClient modelClient = new ProjectedNameClientBuilder().buildModelClient();

    @Test
    void jsonProjection() {
        JsonProjectedNameModel project = new JsonProjectedNameModel(true);
        propertyClient.json(project);
    }

    @Test
    void clientProjection() {
        ClientProjectedNameModel project = new ClientProjectedNameModel(true);
        propertyClient.client(project);
    }

    @Test
    void jsonAndClientProjection() {
        JsonAndClientProjectedNameModel project = new JsonAndClientProjectedNameModel(true);
        propertyClient.jsonAndClient(project);
    }

    @Test
    void languageProjection() {
        LanguageProjectedNameModel project = new LanguageProjectedNameModel(true);
        propertyClient.language(project);
    }

    @Test
    void clientModel() {
        ClientModel clientModel = new ClientModel(true);
        modelClient.client(clientModel);
    }

    @Test
    void languageModel() {
        JavaModel javaModel = new JavaModel(true);
        modelClient.language(javaModel);
    }

    @Test
    public void testClient() {

        // method name be clientName
        client.clientName();

        // parameter name be clientName
        client.parameter("true");
    }
}