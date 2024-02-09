// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;

import com.azure.core.util.BinaryData;

import fixtures.bodycomplex.ArrayClient;
import fixtures.bodycomplex.ArrayClientBuilder;

public class ArrayPutValid {
    public static void main(String[] args) {
        ArrayClient arrayClient = new ArrayClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.arrayputvalid.arrayputvalid
        BinaryData complexBody = BinaryData.fromString(
            "{\"array\":[\"1, 2, 3, 4\",\"\",null,\"&S#$(*Y\",\"The quick brown fox jumps over the lazy dog\"]}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = arrayClient.putValidWithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.arrayputvalid.arrayputvalid
    }
}
