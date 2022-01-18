/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.url.multi;

import com.azure.core.http.rest.RequestOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class QueriesTests {

    private static AutoRestUrlMutliCollectionFormatTestServiceClient client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestUrlMutliCollectionFormatTestServiceClientBuilder().buildClient();
    }

    @Test
    public void arrayStringMultiValid() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("arrayQuery", "ArrayQuery1");
        requestOptions.addQueryParam("arrayQuery", "begin!*'();:@ &=+$,/?#[]end");
        requestOptions.addQueryParam("arrayQuery", ""); // null does not work, use empty instead
        requestOptions.addQueryParam("arrayQuery", "");
        client.arrayStringMultiValidWithResponse(requestOptions);
    }
}
