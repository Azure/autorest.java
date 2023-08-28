// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.flatten.generated;

import com.azure.core.util.Configuration;
import com.cadl.flatten.FlattenClient;
import com.cadl.flatten.FlattenClientBuilder;
import com.cadl.flatten.models.User;

public class FlattenOpSend {
    public static void main(String[] args) {
        FlattenClient flattenClient =
                new FlattenClientBuilder()
                        .endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT"))
                        .buildClient();
        // BEGIN:com.cadl.flatten.generated.send.flattenopsend
        flattenClient.send("myRequiredId", "myRequiredInput", new User("myOptionalUser"));
        // END:com.cadl.flatten.generated.send.flattenopsend
    }
}
