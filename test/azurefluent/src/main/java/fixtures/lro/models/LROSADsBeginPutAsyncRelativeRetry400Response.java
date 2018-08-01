/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.lro.models;

import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.http.HttpRequest;
import fixtures.lro.implementation.LROSADsPutAsyncRelativeRetry400HeadersInner;
import fixtures.lro.implementation.ProductInner;
import java.util.Map;

/**
 * Contains all response data for the beginPutAsyncRelativeRetry400 operation.
 */
public final class LROSADsBeginPutAsyncRelativeRetry400Response extends RestResponse<LROSADsPutAsyncRelativeRetry400HeadersInner, ProductInner> {
    /**
     * Creates an instance of LROSADsBeginPutAsyncRelativeRetry400Response.
     *
     * @param request the request which resulted in this {response.Name}.
     * @param statusCode the status code of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param body the deserialized body of the HTTP response.
     */
    public LROSADsBeginPutAsyncRelativeRetry400Response(HttpRequest request, int statusCode, LROSADsPutAsyncRelativeRetry400HeadersInner headers, Map<String, String> rawHeaders, ProductInner body) {
        super(request, statusCode, headers, rawHeaders, body);
    }

    /**
     * @return the deserialized response headers.
     */
    @Override
    public LROSADsPutAsyncRelativeRetry400HeadersInner headers() {
        return super.headers();
    }

    /**
     * @return the deserialized response body.
     */
    @Override
    public ProductInner body() {
        return super.body();
    }
}
