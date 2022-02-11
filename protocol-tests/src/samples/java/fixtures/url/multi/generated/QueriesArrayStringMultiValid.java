// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.url.multi.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.url.multi.AutoRestUrlMutliCollectionFormatTestServiceClient;
import fixtures.url.multi.AutoRestUrlMutliCollectionFormatTestServiceClientBuilder;

public class QueriesArrayStringMultiValid {
    public static void main(String[] args) {
        // BEGIN: fixtures.url.multi.queries.arraystringmultivalid
        AutoRestUrlMutliCollectionFormatTestServiceClient client =
                new AutoRestUrlMutliCollectionFormatTestServiceClientBuilder()
                        .host("http://localhost:3000")
                        .buildClient();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("arrayQuery", "ArrayQuery1,begin!*'();:@ &= $,/?#[]end,,");
        Response<Void> response = client.arrayStringMultiValidWithResponse(requestOptions);
        // END: fixtures.url.multi.queries.arraystringmultivalid
    }
}
