// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.projection.projectedname;

import com.projection.projectedname.models.ClientProjectedNameModel;
import com.projection.projectedname.models.JsonProjectedNameModel;
import com.projection.projectedname.models.LanguageProjectedNameModel;
import org.junit.jupiter.api.Test;

class ProjectedNameClientTest {

    private final PropertyClient propertyClient = new ProjectedNameClientBuilder().buildPropertyClient();
    private final ProjectedNameClient client = new ProjectedNameClientBuilder().buildClient();

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
    void languageProjection() {
        LanguageProjectedNameModel project = new LanguageProjectedNameModel(true);
        propertyClient.language(project);
    }
}