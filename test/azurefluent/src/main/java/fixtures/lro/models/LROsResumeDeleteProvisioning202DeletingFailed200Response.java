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
import fixtures.lro.LROsDeleteProvisioning202DeletingFailed200Headers;
import fixtures.lro.implementation.ProductInner;
import java.util.Map;

/**
 * Contains all response data for the resumeDeleteProvisioning202DeletingFailed200 operation.
 */
public final class LROsResumeDeleteProvisioning202DeletingFailed200Response extends RestResponse<LROsDeleteProvisioning202DeletingFailed200Headers, ProductInner> {
    /**
     * Creates an instance of LROsResumeDeleteProvisioning202DeletingFailed200Response.
     *
     * @param request the request which resulted in this LROsResumeDeleteProvisioning202DeletingFailed200Response.
     * @param statusCode the status code of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param body the deserialized body of the HTTP response.
     */
    public LROsResumeDeleteProvisioning202DeletingFailed200Response(HttpRequest request, int statusCode, LROsDeleteProvisioning202DeletingFailed200Headers headers, Map<String, String> rawHeaders, ProductInner body) {
        super(request, statusCode, headers, rawHeaders, body);
    }

    /**
     * @return the deserialized response headers.
     */
    @Override
    public LROsDeleteProvisioning202DeletingFailed200Headers headers() {
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
