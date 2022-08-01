// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.testserver.dpgupdate;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.cadl.testserver.DPGAsyncClient;
import com.cadl.testserver.DPGClient;
import com.cadl.testserver.DPGClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DpgUpdateTests {
    private static DPGAsyncClient asyncClient;

    private static DPGClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new DPGClientBuilder().buildAsyncClient();
        client = new DPGClientBuilder().buildClient();
    }

    @Test
    public void headNoParams() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("new_parameter", "new_parameter");

        Response<Void> response = client.headNoParamsWithResponse(requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void getRequired() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("new_parameter", "new_parameter");

        Response<BinaryData> response = client.getRequiredWithResponse("parameter", requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void putRequiredOptional() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("optionalParam", "optionalParam");
        requestOptions.addQueryParam("new_parameter", "new_parameter");
        requestOptions.setBody(BinaryData.fromString("{}"));

        client.putRequiredOptionalWithResponse("requiredParam", requestOptions);
    }

    @Test
    public void postParameters() {
        RequestOptions requestOptions = new RequestOptions();
        BinaryData parameter = BinaryData.fromString("{\"url\":\"http://example.org/myimage.jpeg\"}");

        Response<BinaryData> response = client.postParametersWithResponse(parameter, requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void getOptional() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("optionalParam", "optionalParam");
        requestOptions.addQueryParam("new_parameter", "new_parameter");

        client.getOptionalWithResponse(requestOptions);
    }

    @Test
    public void deleteParameters() {
        RequestOptions requestOptions = new RequestOptions();

        Response<Void> response = client.deleteParametersWithResponse(requestOptions);
        Assertions.assertEquals(204, response.getStatusCode());
    }


    @Test
    public void getNewOperation() {
        RequestOptions requestOptions = new RequestOptions();

        Response<BinaryData> response = client.getNewOperationWithResponse(requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
    }


//    @Test
//    public void testSendRequestMethod() {
//        HttpRequest request = new HttpRequest(HttpMethod.DELETE, "http://localhost:3000/serviceDriven/parameters");
//        Response<BinaryData> response = client2.sendRequest(request, Context.NONE);
//        Assertions.assertEquals(204, response.getStatusCode());
//        Assertions.assertEquals(0, response.getValue().getLength());
//
//        response = asyncClient2.sendRequest(request).block();
//        Assertions.assertEquals(204, response.getStatusCode());
//        Assertions.assertEquals(0, response.getValue().getLength());
//    }
}
