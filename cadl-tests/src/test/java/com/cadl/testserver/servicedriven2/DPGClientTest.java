//// Copyright (c) Microsoft Corporation. All rights reserved.
//// Licensed under the MIT License.
//
//package com.cadl.testserver.servicedriven2;
//
//import com.cadl.testserver.servicedriven2.models.ContentTypePath;
//import com.cadl.testserver.servicedriven2.models.Message;
//import com.cadl.testserver.servicedriven2.models.PostInput;
//import com.azure.core.http.rest.Response;
//import com.azure.core.util.Context;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//class DPGClientTest {
//    ResiliencyServiceDriven2Client client = new ResiliencyServiceDriven2ClientBuilder().buildClient();
//
//    @Test
//    void headNoParamsWithResponse() {
//        Response<Void> response = client.headNoParamsWithResponse("new_param", Context.NONE);
//        Assertions.assertEquals(200, response.getStatusCode());
//    }
//
//    @Test
//    void getRequiredWithResponse() {
//        Response<Message> response = client.getRequiredWithResponse("required_param", "new_param", Context.NONE);
//        Assertions.assertEquals(200, response.getStatusCode());
//        Message message = response.getValue();
//        Assertions.assertEquals("An object was successfully returned", message.getMessage());
//    }
//
//    @Test
//    void putRequiredOptionalWithResponse() {
//        Response<Message> response = client.putRequiredOptionalWithResponse("required_param", null, "new_param", Context.NONE);
//        Assertions.assertEquals(200, response.getStatusCode());
//        Message message = response.getValue();
//        Assertions.assertEquals("An object was successfully returned", message.getMessage());
//    }
//
//    @Test
//    void postParametersWithResponse() {
//        PostInput postInput = new PostInput("http://example.org/myimage.jpeg");
//        Response<Message> response = client.postParametersWithResponse(ContentTypePath.JSON, postInput, Context.NONE);
//        Assertions.assertEquals(200, response.getStatusCode());
//        Message message = response.getValue();
//        Assertions.assertNull(message);
//    }
//
//    @Test
//    void getOptionalWithResponse() {
//        Response<Message> response = client.getOptionalWithResponse(null, "new_param", Context.NONE);
//        Assertions.assertEquals(200, response.getStatusCode());
//        Message message = response.getValue();
//        Assertions.assertEquals("An object was successfully returned", message.getMessage());
//    }
//
//    @Test
//    void headNoParams() {
//        client.headNoParams();
//    }
//
//    @Test
//    void getRequired() {
//        Message message = client.getRequired("required_param");
//        Assertions.assertEquals("An object was successfully returned", message.getMessage());
//    }
//
//    @Test
//    void testGetRequiredWithResponse() {
//        Response<Message> response = client.getRequiredWithResponse("required_param", "new_param", Context.NONE);
//        Assertions.assertEquals(200, response.getStatusCode());
//        Message message = response.getValue();
//        Assertions.assertEquals("An object was successfully returned", message.getMessage());
//    }
//
//    @Test
//    void putRequiredOptional() {
//        Message message = client.putRequiredOptional("required_param");
//        Assertions.assertEquals("An object was successfully returned", message.getMessage());
//    }
//
//
//    @Test
//    void postParameters() {
//        PostInput postInput = new PostInput("http://example.org/myimage.jpeg");
//        Message message = client.postParameters(ContentTypePath.JSON, postInput);
//        Assertions.assertNull(message);
//    }
//
//
//    @Test
//    void getOptional() {
//        Message message = client.getOptional();
//        Assertions.assertEquals("An object was successfully returned", message.getMessage());
//    }
//
//    @Test
//    void deleteParameters() {
//        client.deleteParameters();
//    }
//
//    @Test
//    void deleteParametersWithResponse() {
//        Response<Void> response = client.deleteParametersWithResponse(Context.NONE);
//        Assertions.assertEquals(204, response.getStatusCode());
//    }
//
//    @Test
//    void getNewOperation() {
//        Message message = client.getNewOperation();
//        Assertions.assertEquals("An object was successfully returned", message.getMessage());
//    }
//
//    @Test
//    void getNewOperationWithResponse() {
//        Response<Message> response = client.getNewOperationWithResponse(Context.NONE);
//        Assertions.assertEquals(200, response.getStatusCode());
//        Message message = response.getValue();
//        Assertions.assertEquals("An object was successfully returned", message.getMessage());
//    }
//}