// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodystring.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodystring.EnumClient;
import fixtures.bodystring.EnumClientBuilder;

public class EnumPutReferenced {
    public static void main(String[] args) {
        EnumClient enumClient = new EnumClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodystring.generated.enum-put-referenced.enum-put-referenced
        BinaryData enumStringBody = BinaryData.fromString("\"red color\"");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = enumClient.putReferencedWithResponse(enumStringBody, requestOptions);
        // END:fixtures.bodystring.generated.enum-put-referenced.enum-put-referenced
    }
}
