// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.PrimitiveClient;
import fixtures.bodycomplex.PrimitiveClientBuilder;

public class PrimitivePutDateTimeRfc1123 {
    public static void main(String[] args) {
        PrimitiveClient primitiveClient = new PrimitiveClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.primitive-put-date-time-rfc1123.primitive-put-date-time-rfc1123
        BinaryData complexBody = BinaryData
            .fromString("{\"field\":\"Mon, 01 Jan 0001 12:00:00 GMT\",\"now\":\"Mon, 18 May 2015 11:38:00 GMT\"}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = primitiveClient.putDateTimeRfc1123WithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.primitive-put-date-time-rfc1123.primitive-put-date-time-rfc1123
    }
}
