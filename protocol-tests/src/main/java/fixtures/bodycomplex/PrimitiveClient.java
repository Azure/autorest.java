// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

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
import fixtures.bodycomplex.implementation.PrimitivesImpl;

/**
 * Initializes a new instance of the synchronous AutoRestComplexTestServiceClient type.
 */
@ServiceClient(builder = PrimitiveClientBuilder.class)
public final class PrimitiveClient {
    @Generated
    private final PrimitivesImpl serviceClient;

    /**
     * Initializes an instance of PrimitiveClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    PrimitiveClient(PrimitivesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with integer properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field1: Integer (Optional)
     * field2: Integer (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with integer properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getIntWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getIntWithResponse(requestOptions);
    }

    /**
     * Put complex types with integer properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field1: Integer (Optional)
     * field2: Integer (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put -1 and 2.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putIntWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putIntWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with long properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field1: Long (Optional)
     * field2: Long (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with long properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getLongWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getLongWithResponse(requestOptions);
    }

    /**
     * Put complex types with long properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field1: Long (Optional)
     * field2: Long (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put 1099511627775 and -999511627788.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putLongWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putLongWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with float properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field1: Float (Optional)
     * field2: Float (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with float properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getFloatWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getFloatWithResponse(requestOptions);
    }

    /**
     * Put complex types with float properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field1: Float (Optional)
     * field2: Float (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put 1.05 and -0.003.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putFloatWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putFloatWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with double properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field1: Double (Optional)
     * field_56_zeros_after_the_dot_and_negative_zero_before_dot_and_this_is_a_long_field_name_on_purpose: Double
     * (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with double properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDoubleWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDoubleWithResponse(requestOptions);
    }

    /**
     * Put complex types with double properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field1: Double (Optional)
     * field_56_zeros_after_the_dot_and_negative_zero_before_dot_and_this_is_a_long_field_name_on_purpose: Double
     * (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put 3e-100 and -0.000000000000000000000000000000000000000000000000000000005.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDoubleWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDoubleWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with bool properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field_true: Boolean (Optional)
     * field_false: Boolean (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with bool properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getBoolWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getBoolWithResponse(requestOptions);
    }

    /**
     * Put complex types with bool properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field_true: Boolean (Optional)
     * field_false: Boolean (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put true and false.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putBoolWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putBoolWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with string properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: String (Optional)
     * empty: String (Optional)
     * null: String (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with string properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getStringWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getStringWithResponse(requestOptions);
    }

    /**
     * Put complex types with string properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: String (Optional)
     * empty: String (Optional)
     * null: String (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put 'goodrequest', '', and null.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putStringWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putStringWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with date properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: String (Optional)
     * leap: String (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with date properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDateWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDateWithResponse(requestOptions);
    }

    /**
     * Put complex types with date properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: String (Optional)
     * leap: String (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put '0001-01-01' and '2016-02-29'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDateWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDateWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with datetime properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: OffsetDateTime (Optional)
     * now: OffsetDateTime (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with datetime properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDateTimeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDateTimeWithResponse(requestOptions);
    }

    /**
     * Put complex types with datetime properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: OffsetDateTime (Optional)
     * now: OffsetDateTime (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put '0001-01-01T12:00:00-04:00' and '2015-05-18T11:38:00-08:00'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDateTimeWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDateTimeWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with datetimeRfc1123 properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: DateTimeRfc1123 (Optional)
     * now: DateTimeRfc1123 (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with datetimeRfc1123 properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDateTimeRfc1123WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDateTimeRfc1123WithResponse(requestOptions);
    }

    /**
     * Put complex types with datetimeRfc1123 properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: DateTimeRfc1123 (Optional)
     * now: DateTimeRfc1123 (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put 'Mon, 01 Jan 0001 12:00:00 GMT' and 'Mon, 18 May 2015 11:38:00 GMT'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDateTimeRfc1123WithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDateTimeRfc1123WithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with duration properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: Duration (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with duration properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDurationWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDurationWithResponse(requestOptions);
    }

    /**
     * Put complex types with duration properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: Duration (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put 'P123DT22H14M12.011S'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putDurationWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putDurationWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with byte properties.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: byte[] (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with byte properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getByteWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getByteWithResponse(requestOptions);
    }

    /**
     * Put complex types with byte properties.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * field: byte[] (Optional)
     * }
     * }</pre>
     * 
     * @param complexBody Please put non-ascii byte string hex(FF FE FD FC 00 FA F9 F8 F7 F6).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putByteWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putByteWithResponse(complexBody, requestOptions);
    }
}
