// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.projectedname;

import com.projectedname.models.Project;
import org.junit.jupiter.api.Test;

class ProjectedNameClientTest {

    ProjectedNameClient client = new ProjectedNameClientBuilder().buildClient();

    @Test
    void jsonProjection() {
        Project project = new Project();
        project.setProducedBy("DPG");
        client.jsonProjection(project);
    }

    @Test
    void clientProjection() {
        Project project = new Project();
        project.setCreatedBy("DPG");
        client.clientProjection(project);
    }

    @Test
    void languageProjection() {
        Project project = new Project();
        project.setMadeForJava("customers");
        client.languageProjection(project);
    }
}