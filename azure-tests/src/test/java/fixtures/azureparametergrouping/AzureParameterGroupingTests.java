/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.azureparametergrouping;

import fixtures.azureparametergrouping.models.FirstParameterGroup;
import fixtures.azureparametergrouping.models.ParameterGroupingPostMultiParamGroupsSecondParamGroup;
import fixtures.azureparametergrouping.models.ParameterGroupingPostOptionalParameters;
import fixtures.azureparametergrouping.models.ParameterGroupingPostRequiredParameters;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AzureParameterGroupingTests {

    private static AutoRestParameterGroupingTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestParameterGroupingTestServiceBuilder()
                .buildClient();
    }

    @Test
    public void postRequired() {
        client.getParameterGroupings().postRequired(new ParameterGroupingPostRequiredParameters()
                .setBody(1234)
                .setPath("path")
                .setCustomHeader("header")
                .setQuery(21)
        );
    }

    @Test
    public void postOptional() {
        client.getParameterGroupings().postOptional(new ParameterGroupingPostOptionalParameters()
                .setCustomHeader("header")
                .setQuery(21)
        );
    }

    @Test
    public void postMultiParamGroups() {
        client.getParameterGroupings().postMultiParamGroups(new FirstParameterGroup()
                        .setHeaderOne("header")
                        .setQueryOne(21),
                new ParameterGroupingPostMultiParamGroupsSecondParamGroup()
                        .setHeaderTwo("header2")
                        .setQueryTwo(42)
        );
    }

    @Test
    public void postSharedParameterGroupObject() {
        client.getParameterGroupings().postSharedParameterGroupObject(new FirstParameterGroup()
                .setHeaderOne("header")
                .setQueryOne(21)
        );
    }
}
