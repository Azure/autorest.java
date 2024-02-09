// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.basic;

import com._specs_.azure.core.basic.implementation.BasicClientImpl;
import com._specs_.azure.core.basic.implementation.JsonMergePatchHelper;

import com._specs_.azure.core.basic.models.ListItemInputBody;
import com._specs_.azure.core.basic.models.ListItemInputExtensibleEnum;
import com._specs_.azure.core.basic.models.User;

import com.azure.core.annotation.Generated;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;

import com.azure.core.annotation.ServiceMethod;

import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;

import com.azure.core.http.rest.PagedIterable;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;

import com.azure.core.util.BinaryData;

import java.util.List;

/**
 * Initializes a new instance of the synchronous BasicClient type.
 */
@ServiceClient(builder = BasicClientBuilder.class)
public final class BasicClient {
    @Generated
    private final BasicClientImpl serviceClient;

    /**
     * Initializes an instance of BasicClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    BasicClient(BasicClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Adds a user or updates a user's fields.
     * 
     * Creates or updates a User.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * 
     * @param id The user's id.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a user along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createOrUpdateWithResponse(int id, BinaryData resource, RequestOptions requestOptions) {
        return this.serviceClient.createOrUpdateWithResponse(id, resource, requestOptions);
    }

    /**
     * Adds a user or replaces a user's fields.
     * 
     * Creates or replaces a User.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * 
     * @param id The user's id.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a user along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createOrReplaceWithResponse(int id, BinaryData resource,
        RequestOptions requestOptions) {
        return this.serviceClient.createOrReplaceWithResponse(id, resource, requestOptions);
    }

    /**
     * Gets a user.
     * 
     * Gets a User.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * 
     * @param id The user's id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a User along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getWithResponse(int id, RequestOptions requestOptions) {
        return this.serviceClient.getWithResponse(id, requestOptions);
    }

    /**
     * Lists all users.
     * 
     * Lists all Users.
     * <p>
     * <strong>Query Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>top</td>
     * <td>Integer</td>
     * <td>No</td>
     * <td>The number of result items to return.</td>
     * </tr>
     * <tr>
     * <td>skip</td>
     * <td>Integer</td>
     * <td>No</td>
     * <td>The number of result items to skip.</td>
     * </tr>
     * <tr>
     * <td>maxpagesize</td>
     * <td>Integer</td>
     * <td>No</td>
     * <td>The maximum number of result items per page.</td>
     * </tr>
     * <tr>
     * <td>orderby</td>
     * <td>List&lt;String&gt;</td>
     * <td>No</td>
     * <td>Expressions that specify the order of returned results. Call {@link RequestOptions#addQueryParam} to add
     * string to array.</td>
     * </tr>
     * <tr>
     * <td>filter</td>
     * <td>String</td>
     * <td>No</td>
     * <td>Filter the result list using the given expression.</td>
     * </tr>
     * <tr>
     * <td>select</td>
     * <td>List&lt;String&gt;</td>
     * <td>No</td>
     * <td>Select the specified fields to be included in the response. Call {@link RequestOptions#addQueryParam} to add
     * string to array.</td>
     * </tr>
     * <tr>
     * <td>expand</td>
     * <td>List&lt;String&gt;</td>
     * <td>No</td>
     * <td>Expand the indicated resources into the response. Call {@link RequestOptions#addQueryParam} to add string to
     * array.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of User items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> list(RequestOptions requestOptions) {
        return this.serviceClient.list(requestOptions);
    }

    /**
     * List with Azure.Core.Page&lt;&gt;.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of User items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listWithPage(RequestOptions requestOptions) {
        return this.serviceClient.listWithPage(requestOptions);
    }

    /**
     * List with extensible enum parameter Azure.Core.Page&lt;&gt;.
     * <p>
     * <strong>Query Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>another</td>
     * <td>String</td>
     * <td>No</td>
     * <td>Another query parameter. Allowed values: "First", "Second".</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     inputName: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * 
     * @param bodyInput The body of the input.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of User items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listWithParameters(BinaryData bodyInput, RequestOptions requestOptions) {
        return this.serviceClient.listWithParameters(bodyInput, requestOptions);
    }

    /**
     * List with custom page model.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listWithCustomPageModel(RequestOptions requestOptions) {
        return this.serviceClient.listWithCustomPageModel(requestOptions);
    }

    /**
     * Deletes a user.
     * 
     * Deletes a User.
     * 
     * @param id The user's id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteWithResponse(int id, RequestOptions requestOptions) {
        return this.serviceClient.deleteWithResponse(id, requestOptions);
    }

    /**
     * Exports a user.
     * 
     * Exports a User.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     *     orders (Optional): [
     *          (Optional){
     *             id: int (Required)
     *             userId: int (Required)
     *             detail: String (Required)
     *         }
     *     ]
     *     etag: String (Required)
     * }
     * }</pre>
     * 
     * @param id The user's id.
     * @param format The format of the data.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a user along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> exportWithResponse(int id, String format, RequestOptions requestOptions) {
        return this.serviceClient.exportWithResponse(id, format, requestOptions);
    }

    /**
     * Adds a user or updates a user's fields.
     * 
     * Creates or updates a User.
     * 
     * @param id The user's id.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return details about a user.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public User createOrUpdate(int id, User resource) {
        // Generated convenience method for createOrUpdateWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getUserAccessor().prepareModelForJsonMergePatch(resource, true);
        BinaryData resourceInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(resource).toBytes());
        JsonMergePatchHelper.getUserAccessor().prepareModelForJsonMergePatch(resource, false);
        return createOrUpdateWithResponse(id, resourceInBinaryData, requestOptions).getValue().toObject(User.class);
    }

    /**
     * Adds a user or replaces a user's fields.
     * 
     * Creates or replaces a User.
     * 
     * @param id The user's id.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return details about a user.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public User createOrReplace(int id, User resource) {
        // Generated convenience method for createOrReplaceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return createOrReplaceWithResponse(id, BinaryData.fromObject(resource), requestOptions).getValue()
            .toObject(User.class);
    }

    /**
     * Gets a user.
     * 
     * Gets a User.
     * 
     * @param id The user's id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a User.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public User get(int id) {
        // Generated convenience method for getWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getWithResponse(id, requestOptions).getValue().toObject(User.class);
    }

    /**
     * Lists all users.
     * 
     * Lists all Users.
     * 
     * @param top The number of result items to return.
     * @param skip The number of result items to skip.
     * @param orderBy Expressions that specify the order of returned results.
     * @param filter Filter the result list using the given expression.
     * @param select Select the specified fields to be included in the response.
     * @param expand Expand the indicated resources into the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of User items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<User> list(Integer top, Integer skip, List<String> orderBy, String filter, List<String> select,
        List<String> expand) {
        // Generated convenience method for list
        RequestOptions requestOptions = new RequestOptions();
        if (top != null) {
            requestOptions.addQueryParam("top", String.valueOf(top), false);
        }
        if (skip != null) {
            requestOptions.addQueryParam("skip", String.valueOf(skip), false);
        }
        if (orderBy != null) {
            for (String paramItemValue : orderBy) {
                if (paramItemValue != null) {
                    requestOptions.addQueryParam("orderby", paramItemValue, false);
                }
            }
        }
        if (filter != null) {
            requestOptions.addQueryParam("filter", filter, false);
        }
        if (select != null) {
            for (String paramItemValue : select) {
                if (paramItemValue != null) {
                    requestOptions.addQueryParam("select", paramItemValue, false);
                }
            }
        }
        if (expand != null) {
            for (String paramItemValue : expand) {
                if (paramItemValue != null) {
                    requestOptions.addQueryParam("expand", paramItemValue, false);
                }
            }
        }
        return serviceClient.list(requestOptions).mapPage(bodyItemValue -> bodyItemValue.toObject(User.class));
    }

    /**
     * Lists all users.
     * 
     * Lists all Users.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of User items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<User> list() {
        // Generated convenience method for list
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.list(requestOptions).mapPage(bodyItemValue -> bodyItemValue.toObject(User.class));
    }

    /**
     * List with Azure.Core.Page&lt;&gt;.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of User items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<User> listWithPage() {
        // Generated convenience method for listWithPage
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.listWithPage(requestOptions).mapPage(bodyItemValue -> bodyItemValue.toObject(User.class));
    }

    /**
     * List with extensible enum parameter Azure.Core.Page&lt;&gt;.
     * 
     * @param bodyInput The body of the input.
     * @param another Another query parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of User items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<User> listWithParameters(ListItemInputBody bodyInput, ListItemInputExtensibleEnum another) {
        // Generated convenience method for listWithParameters
        RequestOptions requestOptions = new RequestOptions();
        if (another != null) {
            requestOptions.addQueryParam("another", another.toString(), false);
        }
        return serviceClient.listWithParameters(BinaryData.fromObject(bodyInput), requestOptions)
            .mapPage(bodyItemValue -> bodyItemValue.toObject(User.class));
    }

    /**
     * List with extensible enum parameter Azure.Core.Page&lt;&gt;.
     * 
     * @param bodyInput The body of the input.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of User items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<User> listWithParameters(ListItemInputBody bodyInput) {
        // Generated convenience method for listWithParameters
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.listWithParameters(BinaryData.fromObject(bodyInput), requestOptions)
            .mapPage(bodyItemValue -> bodyItemValue.toObject(User.class));
    }

    /**
     * List with custom page model.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<User> listWithCustomPageModel() {
        // Generated convenience method for listWithCustomPageModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.listWithCustomPageModel(requestOptions)
            .mapPage(bodyItemValue -> bodyItemValue.toObject(User.class));
    }

    /**
     * Deletes a user.
     * 
     * Deletes a User.
     * 
     * @param id The user's id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete(int id) {
        // Generated convenience method for deleteWithResponse
        RequestOptions requestOptions = new RequestOptions();
        deleteWithResponse(id, requestOptions).getValue();
    }

    /**
     * Exports a user.
     * 
     * Exports a User.
     * 
     * @param id The user's id.
     * @param format The format of the data.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return details about a user.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public User export(int id, String format) {
        // Generated convenience method for exportWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return exportWithResponse(id, format, requestOptions).getValue().toObject(User.class);
    }
}
