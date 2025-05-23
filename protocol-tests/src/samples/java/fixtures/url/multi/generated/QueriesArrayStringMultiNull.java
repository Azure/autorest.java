// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.url.multi.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.url.multi.AutoRestUrlMutliCollectionFormatTestServiceClient;
import fixtures.url.multi.AutoRestUrlMutliCollectionFormatTestServiceClientBuilder;

public class QueriesArrayStringMultiNull {
    public static void main(String[] args) {
        AutoRestUrlMutliCollectionFormatTestServiceClient autoRestUrlMutliCollectionFormatTestServiceClient
            = new AutoRestUrlMutliCollectionFormatTestServiceClientBuilder().host("http://localhost:3000")
                .buildClient();
        // BEGIN:fixtures.url.multi.generated.queries-array-string-multi-null.queries-array-string-multi-null
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response
            = autoRestUrlMutliCollectionFormatTestServiceClient.arrayStringMultiNullWithResponse(requestOptions);
        // END:fixtures.url.multi.generated.queries-array-string-multi-null.queries-array-string-multi-null
    }
}
