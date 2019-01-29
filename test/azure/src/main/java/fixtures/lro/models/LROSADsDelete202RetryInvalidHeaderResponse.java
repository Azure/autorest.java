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
import java.util.Map;

/**
 * Contains all response data for the delete202RetryInvalidHeader operation.
 */
public final class LROSADsDelete202RetryInvalidHeaderResponse extends RestResponse<LROSADsDelete202RetryInvalidHeaderHeaders, Void> {
    /**
     * Creates an instance of LROSADsDelete202RetryInvalidHeaderResponse.
     *
     * @param request the request which resulted in this LROSADsDelete202RetryInvalidHeaderResponse.
     * @param statusCode the status code of the HTTP response.
     * @param headers the deserialized headers of the HTTP response.
     * @param rawHeaders the raw headers of the HTTP response.
     * @param body the deserialized body of the HTTP response.
     */
    public LROSADsDelete202RetryInvalidHeaderResponse(HttpRequest request, int statusCode, LROSADsDelete202RetryInvalidHeaderHeaders headers, Map<String, String> rawHeaders, Void body) {
        super(request, statusCode, headers, rawHeaders, body);
    }

    /**
     * @return the deserialized response headers.
     */
    @Override
    public LROSADsDelete202RetryInvalidHeaderHeaders headers() {
        return super.headers();
    }
}
