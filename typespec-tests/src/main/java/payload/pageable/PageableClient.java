// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package payload.pageable;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import payload.pageable.implementation.ServerDrivenPaginationsImpl;
import payload.pageable.serverdrivenpagination.models.LinkResponse;

/**
 * Initializes a new instance of the synchronous PageableClient type.
 */
@ServiceClient(builder = PageableClientBuilder.class)
public final class PageableClient {
    @Generated
    private final ServerDrivenPaginationsImpl serviceClient;

    /**
     * Initializes an instance of PageableClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    PageableClient(ServerDrivenPaginationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The link operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     pets (Required): [
     *          (Required){
     *             id: String (Required)
     *             name: String (Required)
     *         }
     *     ]
     *     links (Required): {
     *         next: String (Optional)
     *         prev: String (Optional)
     *         first: String (Optional)
     *         last: String (Optional)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> linkWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.linkWithResponse(requestOptions);
    }

    /**
     * The link operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LinkResponse link() {
        // Generated convenience method for linkWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return linkWithResponse(requestOptions).getValue().toObject(LinkResponse.class);
    }
}
