/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.azurespecials.implementation;

import com.microsoft.azure.v2.AzureProxy;
import com.microsoft.rest.v2.ServiceCallback;
import com.microsoft.rest.v2.ServiceFuture;
import com.microsoft.rest.v2.VoidResponse;
import com.microsoft.rest.v2.annotations.ExpectedResponses;
import com.microsoft.rest.v2.annotations.GET;
import com.microsoft.rest.v2.annotations.HeaderParam;
import com.microsoft.rest.v2.annotations.Host;
import com.microsoft.rest.v2.annotations.QueryParam;
import com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType;
import fixtures.azurespecials.Odatas;
import fixtures.azurespecials.models.ErrorException;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * An instance of this class provides access to all the operations defined in
 * Odatas.
 */
public final class OdatasImpl implements Odatas {
    /**
     * The proxy service used to perform REST calls.
     */
    private OdatasService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestAzureSpecialParametersTestClientImpl client;

    /**
     * Initializes an instance of OdatasImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public OdatasImpl(AutoRestAzureSpecialParametersTestClientImpl client) {
        this.service = AzureProxy.create(OdatasService.class, client);
        this.client = client;
    }

    /**
     * The interface defining all the services for Odatas to be used by the
     * proxy service to perform REST calls.
     */
    @Host("http://localhost:3000")
    private interface OdatasService {
        @GET("azurespecials/odata/filter")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<VoidResponse> getWithFilter(@QueryParam("$filter") String filter, @QueryParam("$top") Integer top, @QueryParam("$orderby") String orderby, @HeaderParam("accept-language") String acceptLanguage);
    }

    /**
     * Specify filter parameter with value '$filter=id gt 5 and name eq 'foo'&amp;$orderby=id&amp;$top=10'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getWithFilter() {
        getWithFilterAsync().blockingAwait();
    }

    /**
     * Specify filter parameter with value '$filter=id gt 5 and name eq 'foo'&amp;$orderby=id&amp;$top=10'.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> getWithFilterAsync(ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getWithFilterAsync(), serviceCallback);
    }

    /**
     * Specify filter parameter with value '$filter=id gt 5 and name eq 'foo'&amp;$orderby=id&amp;$top=10'.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> getWithFilterWithRestResponseAsync() {
        final String filter = null;
        final Integer top = null;
        final String orderby = null;
        return service.getWithFilter(filter, top, orderby, this.client.acceptLanguage());
    }

    /**
     * Specify filter parameter with value '$filter=id gt 5 and name eq 'foo'&amp;$orderby=id&amp;$top=10'.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Completable getWithFilterAsync() {
        return getWithFilterWithRestResponseAsync()
            .ignoreElement();
    }

    /**
     * Specify filter parameter with value '$filter=id gt 5 and name eq 'foo'&amp;$orderby=id&amp;$top=10'.
     *
     * @param filter The filter parameter with value '$filter=id gt 5 and name eq 'foo''.
     * @param top The top parameter with value 10.
     * @param orderby The orderby parameter with value id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getWithFilter(String filter, Integer top, String orderby) {
        getWithFilterAsync(filter, top, orderby).blockingAwait();
    }

    /**
     * Specify filter parameter with value '$filter=id gt 5 and name eq 'foo'&amp;$orderby=id&amp;$top=10'.
     *
     * @param filter The filter parameter with value '$filter=id gt 5 and name eq 'foo''.
     * @param top The top parameter with value 10.
     * @param orderby The orderby parameter with value id.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> getWithFilterAsync(String filter, Integer top, String orderby, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getWithFilterAsync(filter, top, orderby), serviceCallback);
    }

    /**
     * Specify filter parameter with value '$filter=id gt 5 and name eq 'foo'&amp;$orderby=id&amp;$top=10'.
     *
     * @param filter The filter parameter with value '$filter=id gt 5 and name eq 'foo''.
     * @param top The top parameter with value 10.
     * @param orderby The orderby parameter with value id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> getWithFilterWithRestResponseAsync(String filter, Integer top, String orderby) {
        return service.getWithFilter(filter, top, orderby, this.client.acceptLanguage());
    }

    /**
     * Specify filter parameter with value '$filter=id gt 5 and name eq 'foo'&amp;$orderby=id&amp;$top=10'.
     *
     * @param filter The filter parameter with value '$filter=id gt 5 and name eq 'foo''.
     * @param top The top parameter with value 10.
     * @param orderby The orderby parameter with value id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable getWithFilterAsync(String filter, Integer top, String orderby) {
        return getWithFilterWithRestResponseAsync(filter, top, orderby)
            .ignoreElement();
    }
}
