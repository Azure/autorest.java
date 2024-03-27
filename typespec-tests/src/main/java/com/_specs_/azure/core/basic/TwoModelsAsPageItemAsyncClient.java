// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.basic;

import com._specs_.azure.core.basic.implementation.TwoModelsAsPageItemsImpl;
import com._specs_.azure.core.basic.models.FirstItem;
import com._specs_.azure.core.basic.models.SecondItem;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;

/**
 * Initializes a new instance of the asynchronous BasicClient type.
 */
@ServiceClient(builder = BasicClientBuilder.class, isAsync = true)
public final class TwoModelsAsPageItemAsyncClient {
    @Generated
    private final TwoModelsAsPageItemsImpl serviceClient;

    /**
     * Initializes an instance of TwoModelsAsPageItemAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    TwoModelsAsPageItemAsyncClient(TwoModelsAsPageItemsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Two operations with two different page item types should be successfully generated. Should generate model for
     * FirstItem.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: int (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of FirstItem items as paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listFirstItem(RequestOptions requestOptions) {
        return this.serviceClient.listFirstItemAsync(requestOptions);
    }

    /**
     * Two operations with two different page item types should be successfully generated. Should generate model for
     * SecondItem.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of SecondItem items as paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listSecondItem(RequestOptions requestOptions) {
        return this.serviceClient.listSecondItemAsync(requestOptions);
    }

    /**
     * Two operations with two different page item types should be successfully generated. Should generate model for
     * FirstItem.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of FirstItem items as paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<FirstItem> listFirstItem() {
        // Generated convenience method for listFirstItem
        RequestOptions requestOptions = new RequestOptions();
        PagedFlux<BinaryData> pagedFluxResponse = listFirstItem(requestOptions);
        return PagedFlux.create(() -> (continuationToken, pageSize) -> {
            Flux<PagedResponse<BinaryData>> flux = (continuationToken == null)
                ? pagedFluxResponse.byPage().take(1)
                : pagedFluxResponse.byPage(continuationToken).take(1);
            return flux.map(pagedResponse -> new PagedResponseBase<Void, FirstItem>(pagedResponse.getRequest(),
                pagedResponse.getStatusCode(), pagedResponse.getHeaders(),
                pagedResponse.getValue()
                    .stream()
                    .map(protocolMethodData -> protocolMethodData.toObject(FirstItem.class))
                    .collect(Collectors.toList()),
                pagedResponse.getContinuationToken(), null));
        });
    }

    /**
     * Two operations with two different page item types should be successfully generated. Should generate model for
     * SecondItem.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of SecondItem items as paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<SecondItem> listSecondItem() {
        // Generated convenience method for listSecondItem
        RequestOptions requestOptions = new RequestOptions();
        PagedFlux<BinaryData> pagedFluxResponse = listSecondItem(requestOptions);
        return PagedFlux.create(() -> (continuationToken, pageSize) -> {
            Flux<PagedResponse<BinaryData>> flux = (continuationToken == null)
                ? pagedFluxResponse.byPage().take(1)
                : pagedFluxResponse.byPage(continuationToken).take(1);
            return flux.map(pagedResponse -> new PagedResponseBase<Void, SecondItem>(pagedResponse.getRequest(),
                pagedResponse.getStatusCode(), pagedResponse.getHeaders(),
                pagedResponse.getValue()
                    .stream()
                    .map(protocolMethodData -> protocolMethodData.toObject(SecondItem.class))
                    .collect(Collectors.toList()),
                pagedResponse.getContinuationToken(), null));
        });
    }
}
