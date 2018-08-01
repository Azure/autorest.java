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
import fixtures.lro.implementation.LROsCustomHeaderPostAsyncRetrySucceededHeadersInner;
import java.util.Map;

/**
 * Contains all response data for the beginPostAsyncRetrySucceeded operation.
 */
public final class LROsCustomHeaderBeginPostAsyncRetrySucceededResponse extends RestResponse<LROsCustomHeaderPostAsyncRetrySucceededHeadersInner, Void> {
    /**
     * Creates an instance of LROsCustomHeaderBeginPostAsyncRetrySucceededResponse.
     *
     * @param request the request which resulted in this {response.Name}.
     * @param statusCode the status code of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param body the deserialized body of the HTTP response.
     */
    public LROsCustomHeaderBeginPostAsyncRetrySucceededResponse(HttpRequest request, int statusCode, LROsCustomHeaderPostAsyncRetrySucceededHeadersInner headers, Map<String, String> rawHeaders, Void body) {
        super(request, statusCode, headers, rawHeaders, body);
    }

    /**
     * @return the deserialized response headers.
     */
    @Override
    public LROsCustomHeaderPostAsyncRetrySucceededHeadersInner headers() {
        return super.headers();
    }
}
