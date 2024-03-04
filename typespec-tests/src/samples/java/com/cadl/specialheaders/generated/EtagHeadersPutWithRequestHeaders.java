// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.specialheaders.generated;

import com.azure.core.http.RequestConditions;
import com.azure.core.util.Configuration;
import com.cadl.specialheaders.EtagHeadersClient;
import com.cadl.specialheaders.SpecialHeadersClientBuilder;
import com.cadl.specialheaders.models.Resource;

public class EtagHeadersPutWithRequestHeaders {
    public static void main(String[] args) {
        EtagHeadersClient etagHeadersClient
            = new SpecialHeadersClientBuilder().endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT"))
                .buildEtagHeadersClient();
        // BEGIN:com.cadl.specialheaders.generated.etagheadersputwithrequestheaders.etagheadersputwithrequestheaders
        Resource response = etagHeadersClient.putWithRequestHeaders("name",
            new Resource().setDescription("This is sample for Etag headers").setType("myType"),
            new RequestConditions().setIfMatch("64e005"));
        // END:com.cadl.specialheaders.generated.etagheadersputwithrequestheaders.etagheadersputwithrequestheaders
    }
}
