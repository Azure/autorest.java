// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.PrimitiveClient;
import fixtures.bodycomplex.PrimitiveClientBuilder;

public class PrimitivePutInt {
    public static void main(String[] args) {
        PrimitiveClient primitiveClient = new PrimitiveClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.primitive-put-int.primitive-put-int
        BinaryData complexBody = BinaryData.fromString("{\"field1\":2,\"field2\":-1}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = primitiveClient.putIntWithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.primitive-put-int.primitive-put-int
    }
}
