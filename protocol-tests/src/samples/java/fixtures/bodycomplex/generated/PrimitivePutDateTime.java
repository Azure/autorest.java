// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.PrimitiveClient;
import fixtures.bodycomplex.PrimitiveClientBuilder;

public class PrimitivePutDateTime {
    public static void main(String[] args) {
        PrimitiveClient primitiveClient = new PrimitiveClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.primitiveputdatetime.primitiveputdatetime
        BinaryData complexBody =
                BinaryData.fromString(
                        "{\"field\":\"0001-01-01T12:00:00-04:00\",\"now\":\"2015-05-18T11:38:00-08:00\"}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = primitiveClient.putDateTimeWithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.primitiveputdatetime.primitiveputdatetime
    }
}
