// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.bodycomplex.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.PrimitiveClient;
import fixtures.bodycomplex.PrimitiveClientBuilder;

public class PrimitivePutFloat {

    public static void main(String[] args) {
        PrimitiveClient primitiveClient = new PrimitiveClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.primitiveputfloat.primitiveputfloat
        BinaryData complexBody = BinaryData.fromString("{\"field1\":1.05,\"field2\":-0.003}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = primitiveClient.putFloatWithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.primitiveputfloat.primitiveputfloat
    }
}
