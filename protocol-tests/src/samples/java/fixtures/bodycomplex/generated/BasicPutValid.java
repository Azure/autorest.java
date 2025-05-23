// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.BasicClient;
import fixtures.bodycomplex.BasicClientBuilder;

public class BasicPutValid {
    public static void main(String[] args) {
        BasicClient basicClient = new BasicClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.basic-put-valid.basic-put-valid
        BinaryData complexBody = BinaryData.fromString("{\"name\":\"abc\",\"color\":\"Magenta\",\"id\":2}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = basicClient.putValidWithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.basic-put-valid.basic-put-valid
    }
}
