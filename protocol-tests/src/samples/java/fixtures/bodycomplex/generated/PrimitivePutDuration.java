// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;

import com.azure.core.util.BinaryData;

import fixtures.bodycomplex.PrimitiveClient;
import fixtures.bodycomplex.PrimitiveClientBuilder;

public class PrimitivePutDuration {
    public static void main(String[] args) {
        PrimitiveClient primitiveClient = new PrimitiveClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.primitiveputduration.primitiveputduration
        BinaryData complexBody = BinaryData.fromString("{\"field\":\"P123DT22H14M12.011S\"}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = primitiveClient.putDurationWithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.primitiveputduration.primitiveputduration
    }
}
