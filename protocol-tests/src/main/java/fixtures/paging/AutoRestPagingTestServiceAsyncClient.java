package fixtures.paging;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import fixtures.paging.implementation.PagingsImpl;

/** Initializes a new instance of the asynchronous AutoRestPagingTestService type. */
@ServiceClient(builder = AutoRestPagingTestServiceBuilder.class, isAsync = true)
public final class AutoRestPagingTestServiceAsyncClient {
    private final PagingsImpl serviceClient;

    /**
     * Initializes an instance of Pagings client.
     *
     * @param serviceClient the service client implementation.
     */
    AutoRestPagingTestServiceAsyncClient(PagingsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listNoItemNamePages(RequestOptions requestOptions) {
        return this.serviceClient.listNoItemNamePagesAsync(requestOptions);
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listNullNextLinkNamePages(RequestOptions requestOptions) {
        return this.serviceClient.listNullNextLinkNamePagesAsync(requestOptions);
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listSinglePages(RequestOptions requestOptions) {
        return this.serviceClient.listSinglePagesAsync(requestOptions);
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> firstResponseEmpty(RequestOptions requestOptions) {
        return this.serviceClient.firstResponseEmptyAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePages(RequestOptions requestOptions) {
        return this.serviceClient.listMultiplePagesAsync(requestOptions);
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>requiredQueryParameter</td><td>int</td><td>Yes</td><td>A required integer query parameter. Put in value '100' to pass test.</td></tr>
     *     <tr><td>queryConstant</td><td>boolean</td><td>Yes</td><td>A constant. Must be True and will be passed as a query parameter to nextOperationWithQueryParams</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listWithQueryParams(RequestOptions requestOptions) {
        return this.serviceClient.listWithQueryParamsAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listOdataMultiplePages(RequestOptions requestOptions) {
        return this.serviceClient.listOdataMultiplePagesAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param offset Offset of return value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesWithOffset(int offset, RequestOptions requestOptions) {
        return this.serviceClient.listMultiplePagesWithOffsetAsync(offset, requestOptions);
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesRetryFirst(RequestOptions requestOptions) {
        return this.serviceClient.listMultiplePagesRetryFirstAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesRetrySecond(RequestOptions requestOptions) {
        return this.serviceClient.listMultiplePagesRetrySecondAsync(requestOptions);
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listSinglePagesFailure(RequestOptions requestOptions) {
        return this.serviceClient.listSinglePagesFailureAsync(requestOptions);
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFailure(RequestOptions requestOptions) {
        return this.serviceClient.listMultiplePagesFailureAsync(requestOptions);
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFailureUri(RequestOptions requestOptions) {
        return this.serviceClient.listMultiplePagesFailureUriAsync(requestOptions);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFragmentNextLink(String tenant, RequestOptions requestOptions) {
        return this.serviceClient.listMultiplePagesFragmentNextLinkAsync(tenant, requestOptions);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFragmentWithGroupingNextLink(
            String tenant, RequestOptions requestOptions) {
        return this.serviceClient.listMultiplePagesFragmentWithGroupingNextLinkAsync(tenant, requestOptions);
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesLRO(RequestOptions requestOptions) {
        return this.serviceClient.listMultiplePagesLROAsync(requestOptions);
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     indexes: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listPagingModelWithItemNameWithXMSClientName(RequestOptions requestOptions) {
        return this.serviceClient.listPagingModelWithItemNameWithXMSClientNameAsync(requestOptions);
    }
}
