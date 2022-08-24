// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.testserver.servicedriven1;

import com.cadl.testserver.servicedriven1.models.Message;
import com.cadl.testserver.servicedriven1.models.PostInput;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DPGClientTest {

    ResiliencyServiceDriven1Client client = new ResiliencyServiceDriven1ClientBuilder().buildClient();

    @Test
    void headNoParamsWithResponse() {
        Response<Void> response = client.headNoParamsWithResponse(Context.NONE);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    void getRequiredWithResponse() {
        Response<Message> response = client.getRequiredWithResponse("required_param", Context.NONE);
        Assertions.assertEquals(200, response.getStatusCode());
        Message message = response.getValue();
        Assertions.assertEquals("An object was successfully returned", message.getMessage());
    }

    @Test
    void putRequiredOptionalWithResponse() {
        Response<Message> response = client.putRequiredOptionalWithResponse("required_param", null, Context.NONE);
        Assertions.assertEquals(200, response.getStatusCode());
        Message message = response.getValue();
        Assertions.assertEquals("An object was successfully returned", message.getMessage());
    }

    @Test
    void postParametersWithResponse() {
        PostInput postInput = new PostInput("http://example.org/myimage.jpeg");
        Response<Message> response = client.postParametersWithResponse(postInput, Context.NONE);
        Assertions.assertEquals(200, response.getStatusCode());
        Message message = response.getValue();
        Assertions.assertNull(message);
    }

    @Test
    void getOptionalWithResponse() {
        Response<Message> response = client.getOptionalWithResponse(null, Context.NONE);
        Assertions.assertEquals(200, response.getStatusCode());
        Message message = response.getValue();
        Assertions.assertEquals("An object was successfully returned", message.getMessage());
    }

    @Test
    void headNoParams() {
        client.headNoParams();
    }

    @Test
    void testHeadNoParamsWithResponse() {
        Response<Void> response = client.headNoParamsWithResponse(Context.NONE);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    void getRequired() {
        Message message = client.getRequired("required_param");
        Assertions.assertEquals("An object was successfully returned", message.getMessage());
    }

    @Test
    void testGetRequiredWithResponse() {
        Response<Message> response = client.getRequiredWithResponse("required_param", Context.NONE);
        Assertions.assertEquals(200, response.getStatusCode());
        Message message = response.getValue();
        Assertions.assertEquals("An object was successfully returned", message.getMessage());
    }

    @Test
    void putRequiredOptional() {
        Message message = client.putRequiredOptional("required_param");
        Assertions.assertEquals("An object was successfully returned", message.getMessage());
    }


    @Test
    void postParameters() {
        PostInput postInput = new PostInput("http://example.org/myimage.jpeg");
        Message message = client.postParameters(postInput);
        Assertions.assertNull(message);
    }


    @Test
    void getOptional() {
        Message message = client.getOptional();
        Assertions.assertEquals("An object was successfully returned", message.getMessage());
    }

}