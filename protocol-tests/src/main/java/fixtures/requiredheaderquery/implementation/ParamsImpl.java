// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredheaderquery.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Params. */
public final class ParamsImpl {
    /** The proxy service used to perform REST calls. */
    private final ParamsService service;

    /** The service client containing this operation class. */
    private final DpgRequiredHeaderQueryClientImpl client;

    /**
     * Initializes an instance of ParamsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    ParamsImpl(DpgRequiredHeaderQueryClientImpl client) {
        this.service = RestProxy.create(ParamsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for DpgRequiredHeaderQueryClientParams to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "DpgRequiredHeaderQue")
    private interface ParamsService {
        @Get("/required/query/parameters")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getRequiredQueryParam(
                @HostParam("$host") String host,
                @QueryParam("parameter_int") int parameterInt,
                @QueryParam("parameter_boolean") boolean parameterBoolean,
                @QueryParam("parameter_csv_string_array") String parameterCsvStringArray,
                @QueryParam("parameter_csv_int_array") String parameterCsvIntArray,
                @QueryParam(value = "parameter_multi_string_array", multipleQueryParams = true)
                        List<String> parameterMultiStringArray,
                @QueryParam(value = "parameter_multi_int_array", multipleQueryParams = true)
                        List<String> parameterMultiIntArray,
                @QueryParam(value = "parameter_multi_enum_array", multipleQueryParams = true)
                        List<String> parameterMultiEnumArray,
                @QueryParam("parameter_datetime") OffsetDateTime parameterDatetime,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/required/header/parameters")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getRequiredHeader(
                @HostParam("$host") String host,
                @HeaderParam("parameter_int") int parameterInt,
                @HeaderParam("parameter_boolean") boolean parameterBoolean,
                @HeaderParam("parameter_csv_string_array") String parameterCsvStringArray,
                @HeaderParam("parameter_csv_int_array") String parameterCsvIntArray,
                @HeaderParam("parameter_datetime") DateTimeRfc1123 parameterDatetime,
                @HeaderParam("parameter_duration") Duration parameterDuration,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Test Case for Required Query Parameters
     *
     * <p>Get Required Query Parameters.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter_optional_csv_int_array</td><td>List&lt;Integer&gt;</td><td>No</td><td>I am a required csv int array parameter. In the form of "," separated string.</td></tr>
     *     <tr><td>parameter_optional_multi_int_array</td><td>List&lt;Integer&gt;</td><td>No</td><td>The array of integer collect by multi. Call {@link RequestOptions#addQueryParam} to add string to array.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param parameterInt I am a required int parameter.
     * @param parameterBoolean I am a required boolean parameter.
     * @param parameterCsvStringArray I am a required csv string array parameter.
     * @param parameterCsvIntArray I am a required csv int array parameter.
     * @param parameterMultiStringArray The array of string collect by multi.
     * @param parameterMultiIntArray The array of integer collect by multi.
     * @param parameterMultiEnumArray The array of enum collect by multi.
     * @param parameterDatetime The datetime parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return required Query Parameters along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getRequiredQueryParamWithResponseAsync(
            int parameterInt,
            boolean parameterBoolean,
            List<String> parameterCsvStringArray,
            List<Integer> parameterCsvIntArray,
            List<String> parameterMultiStringArray,
            List<Integer> parameterMultiIntArray,
            List<String> parameterMultiEnumArray,
            OffsetDateTime parameterDatetime,
            RequestOptions requestOptions) {
        final String accept = "application/json";
        String parameterCsvStringArrayConverted =
                parameterCsvStringArray.stream()
                        .map(value -> Objects.toString(value, ""))
                        .collect(Collectors.joining(","));
        String parameterCsvIntArrayConverted =
                JacksonAdapter.createDefaultSerializerAdapter()
                        .serializeIterable(parameterCsvIntArray, CollectionFormat.CSV);
        List<String> parameterMultiStringArrayConverted =
                parameterMultiStringArray.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> parameterMultiIntArrayConverted =
                parameterMultiIntArray.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> parameterMultiEnumArrayConverted =
                parameterMultiEnumArray.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return FluxUtil.withContext(
                context ->
                        service.getRequiredQueryParam(
                                this.client.getHost(),
                                parameterInt,
                                parameterBoolean,
                                parameterCsvStringArrayConverted,
                                parameterCsvIntArrayConverted,
                                parameterMultiStringArrayConverted,
                                parameterMultiIntArrayConverted,
                                parameterMultiEnumArrayConverted,
                                parameterDatetime,
                                accept,
                                requestOptions,
                                context));
    }

    /**
     * Test Case for Required Query Parameters
     *
     * <p>Get Required Query Parameters.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter_optional_csv_int_array</td><td>List&lt;Integer&gt;</td><td>No</td><td>I am a required csv int array parameter. In the form of "," separated string.</td></tr>
     *     <tr><td>parameter_optional_multi_int_array</td><td>List&lt;Integer&gt;</td><td>No</td><td>The array of integer collect by multi. Call {@link RequestOptions#addQueryParam} to add string to array.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param parameterInt I am a required int parameter.
     * @param parameterBoolean I am a required boolean parameter.
     * @param parameterCsvStringArray I am a required csv string array parameter.
     * @param parameterCsvIntArray I am a required csv int array parameter.
     * @param parameterMultiStringArray The array of string collect by multi.
     * @param parameterMultiIntArray The array of integer collect by multi.
     * @param parameterMultiEnumArray The array of enum collect by multi.
     * @param parameterDatetime The datetime parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return required Query Parameters along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getRequiredQueryParamWithResponse(
            int parameterInt,
            boolean parameterBoolean,
            List<String> parameterCsvStringArray,
            List<Integer> parameterCsvIntArray,
            List<String> parameterMultiStringArray,
            List<Integer> parameterMultiIntArray,
            List<String> parameterMultiEnumArray,
            OffsetDateTime parameterDatetime,
            RequestOptions requestOptions) {
        return getRequiredQueryParamWithResponseAsync(
                        parameterInt,
                        parameterBoolean,
                        parameterCsvStringArray,
                        parameterCsvIntArray,
                        parameterMultiStringArray,
                        parameterMultiIntArray,
                        parameterMultiEnumArray,
                        parameterDatetime,
                        requestOptions)
                .block();
    }

    /**
     * Get Required Header Parameters.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param parameterInt I am a required int parameter.
     * @param parameterBoolean I am a required boolean parameter.
     * @param parameterCsvStringArray The array of string collect by csv.
     * @param parameterCsvIntArray The array of integer collect by csv.
     * @param parameterDatetime The datetime parameter.
     * @param parameterDuration The duration parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return required Header Parameters along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getRequiredHeaderWithResponseAsync(
            int parameterInt,
            boolean parameterBoolean,
            List<String> parameterCsvStringArray,
            List<Integer> parameterCsvIntArray,
            OffsetDateTime parameterDatetime,
            Duration parameterDuration,
            RequestOptions requestOptions) {
        final String accept = "application/json";
        String parameterCsvStringArrayConverted =
                parameterCsvStringArray.stream()
                        .map(value -> Objects.toString(value, ""))
                        .collect(Collectors.joining(","));
        String parameterCsvIntArrayConverted =
                JacksonAdapter.createDefaultSerializerAdapter()
                        .serializeIterable(parameterCsvIntArray, CollectionFormat.CSV);
        DateTimeRfc1123 parameterDatetimeConverted = new DateTimeRfc1123(parameterDatetime);
        return FluxUtil.withContext(
                context ->
                        service.getRequiredHeader(
                                this.client.getHost(),
                                parameterInt,
                                parameterBoolean,
                                parameterCsvStringArrayConverted,
                                parameterCsvIntArrayConverted,
                                parameterDatetimeConverted,
                                parameterDuration,
                                accept,
                                requestOptions,
                                context));
    }

    /**
     * Get Required Header Parameters.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param parameterInt I am a required int parameter.
     * @param parameterBoolean I am a required boolean parameter.
     * @param parameterCsvStringArray The array of string collect by csv.
     * @param parameterCsvIntArray The array of integer collect by csv.
     * @param parameterDatetime The datetime parameter.
     * @param parameterDuration The duration parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return required Header Parameters along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getRequiredHeaderWithResponse(
            int parameterInt,
            boolean parameterBoolean,
            List<String> parameterCsvStringArray,
            List<Integer> parameterCsvIntArray,
            OffsetDateTime parameterDatetime,
            Duration parameterDuration,
            RequestOptions requestOptions) {
        return getRequiredHeaderWithResponseAsync(
                        parameterInt,
                        parameterBoolean,
                        parameterCsvStringArray,
                        parameterCsvIntArray,
                        parameterDatetime,
                        parameterDuration,
                        requestOptions)
                .block();
    }
}
