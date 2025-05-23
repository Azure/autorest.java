// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.PolymorphismClient;
import fixtures.bodycomplex.PolymorphismClientBuilder;

public class PolymorphismPutComplicated {
    public static void main(String[] args) {
        PolymorphismClient polymorphismClient
            = new PolymorphismClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.polymorphism-put-complicated.polymorphism-put-complicated
        BinaryData complexBody = BinaryData.fromString(
            "{\"additionalProperty1\":1,\"additionalProperty2\":false,\"additionalProperty3\":\"hello\",\"additionalProperty4\":{\"a\":1,\"b\":2},\"additionalProperty5\":[1,3],\"fishtype\":\"smart_salmon\",\"iswild\":true,\"length\":1,\"location\":\"alaska\",\"siblings\":[{\"age\":6,\"birthday\":\"2012-01-05T01:00:00Z\",\"fishtype\":\"shark\",\"length\":20,\"species\":\"predator\"},{\"age\":105,\"birthday\":\"1900-01-05T01:00:00Z\",\"fishtype\":\"sawshark\",\"length\":10,\"picture\":\"//////4=\",\"species\":\"dangerous\"},{\"age\":1,\"birthday\":\"2015-08-08T00:00:00.000Z\",\"color\":\"pinkish-gray\",\"fishtype\":\"goblin\",\"jawsize\":5,\"length\":30,\"species\":\"scary\"}],\"species\":\"king\"}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = polymorphismClient.putComplicatedWithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.polymorphism-put-complicated.polymorphism-put-complicated
    }
}
