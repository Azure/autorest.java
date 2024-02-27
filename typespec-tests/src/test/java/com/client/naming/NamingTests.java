// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.client.naming;

import com.client.naming.models.ClientModel;
import com.client.naming.models.ClientNameAndJsonEncodedNameModel;
import com.client.naming.models.ClientNameModel;
import com.client.naming.models.JavaModel;
import com.client.naming.models.LanguageClientNameModel;
import org.junit.jupiter.api.Test;

public class NamingTests {

    private final NamingClient client = new NamingClientBuilder().buildClient();
    private final ModelClient modelClient = new NamingClientBuilder().buildModelClient();

    @Test
    public void testNaming() {
        // operation
        client.clientName();

        // parameter
        client.parameter("true");

        // property
        client.client(new ClientNameModel(true));
        client.language(new LanguageClientNameModel(true));
        client.compatibleWithEncodedName(new ClientNameAndJsonEncodedNameModel(true));

        // header
        client.request("true");
//        client.response();  // no client name in response header at present

        // model
        modelClient.client(new ClientModel(true));
        modelClient.language(new JavaModel(true));
    }
}
