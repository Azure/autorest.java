package fixtures.azurespecials;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.azurespecials.models.ErrorException;
import fixtures.azurespecials.models.HeaderCustomNamedRequestIdParamGroupingParameters;
import fixtures.azurespecials.models.HeadersCustomNamedRequestIdHeadResponse;
import fixtures.azurespecials.models.HeadersCustomNamedRequestIdParamGroupingResponse;
import fixtures.azurespecials.models.HeadersCustomNamedRequestIdResponse;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Headers. */
public final class Headers {
    /** The proxy service used to perform REST calls. */
    private final HeadersService service;

    /** The service client containing this operation class. */
    private final AutoRestAzureSpecialParametersTestClient client;

    /**
     * Initializes an instance of Headers.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Headers(AutoRestAzureSpecialParametersTestClient client) {
        this.service = RestProxy.create(HeadersService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestAzureSpecialParametersTestClientHeaders to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestAzureSpecial")
    private interface HeadersService {
        @Post("/azurespecials/customNamedRequestId")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersCustomNamedRequestIdResponse> customNamedRequestId(
                @HostParam("$host") String host,
                @HeaderParam("foo-client-request-id") String fooClientRequestId,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/azurespecials/customNamedRequestIdParamGrouping")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersCustomNamedRequestIdParamGroupingResponse> customNamedRequestIdParamGrouping(
                @HostParam("$host") String host,
                @HeaderParam("foo-client-request-id") String fooClientRequestId,
                @HeaderParam("Accept") String accept,
                Context context);

        @Head("/azurespecials/customNamedRequestIdHead")
        @ExpectedResponses({200, 404})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersCustomNamedRequestIdHeadResponse> customNamedRequestIdHead(
                @HostParam("$host") String host,
                @HeaderParam("foo-client-request-id") String fooClientRequestId,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersCustomNamedRequestIdResponse> customNamedRequestIdWithResponseAsync(String fooClientRequestId) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (fooClientRequestId == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter fooClientRequestId is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.customNamedRequestId(this.client.getHost(), fooClientRequestId, accept, context));
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersCustomNamedRequestIdResponse> customNamedRequestIdWithResponseAsync(
            String fooClientRequestId, Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (fooClientRequestId == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter fooClientRequestId is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.customNamedRequestId(this.client.getHost(), fooClientRequestId, accept, context);
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> customNamedRequestIdAsync(String fooClientRequestId) {
        return customNamedRequestIdWithResponseAsync(fooClientRequestId)
                .flatMap((HeadersCustomNamedRequestIdResponse res) -> Mono.empty());
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> customNamedRequestIdAsync(String fooClientRequestId, Context context) {
        return customNamedRequestIdWithResponseAsync(fooClientRequestId, context)
                .flatMap((HeadersCustomNamedRequestIdResponse res) -> Mono.empty());
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void customNamedRequestId(String fooClientRequestId) {
        customNamedRequestIdAsync(fooClientRequestId).block();
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public HeadersCustomNamedRequestIdResponse customNamedRequestIdWithResponse(
            String fooClientRequestId, Context context) {
        return customNamedRequestIdWithResponseAsync(fooClientRequestId, context).block();
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request, via a parameter
     * group.
     *
     * @param headerCustomNamedRequestIdParamGroupingParameters Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersCustomNamedRequestIdParamGroupingResponse> customNamedRequestIdParamGroupingWithResponseAsync(
            HeaderCustomNamedRequestIdParamGroupingParameters headerCustomNamedRequestIdParamGroupingParameters) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (headerCustomNamedRequestIdParamGroupingParameters == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter headerCustomNamedRequestIdParamGroupingParameters is required and cannot be null."));
        } else {
            headerCustomNamedRequestIdParamGroupingParameters.validate();
        }
        final String accept = "application/json";
        String fooClientRequestId = headerCustomNamedRequestIdParamGroupingParameters.getFooClientRequestId();
        return FluxUtil.withContext(
                context ->
                        service.customNamedRequestIdParamGrouping(
                                this.client.getHost(), fooClientRequestId, accept, context));
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request, via a parameter
     * group.
     *
     * @param headerCustomNamedRequestIdParamGroupingParameters Parameter group.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersCustomNamedRequestIdParamGroupingResponse> customNamedRequestIdParamGroupingWithResponseAsync(
            HeaderCustomNamedRequestIdParamGroupingParameters headerCustomNamedRequestIdParamGroupingParameters,
            Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (headerCustomNamedRequestIdParamGroupingParameters == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter headerCustomNamedRequestIdParamGroupingParameters is required and cannot be null."));
        } else {
            headerCustomNamedRequestIdParamGroupingParameters.validate();
        }
        final String accept = "application/json";
        String fooClientRequestId = headerCustomNamedRequestIdParamGroupingParameters.getFooClientRequestId();
        return service.customNamedRequestIdParamGrouping(this.client.getHost(), fooClientRequestId, accept, context);
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request, via a parameter
     * group.
     *
     * @param headerCustomNamedRequestIdParamGroupingParameters Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> customNamedRequestIdParamGroupingAsync(
            HeaderCustomNamedRequestIdParamGroupingParameters headerCustomNamedRequestIdParamGroupingParameters) {
        return customNamedRequestIdParamGroupingWithResponseAsync(headerCustomNamedRequestIdParamGroupingParameters)
                .flatMap((HeadersCustomNamedRequestIdParamGroupingResponse res) -> Mono.empty());
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request, via a parameter
     * group.
     *
     * @param headerCustomNamedRequestIdParamGroupingParameters Parameter group.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> customNamedRequestIdParamGroupingAsync(
            HeaderCustomNamedRequestIdParamGroupingParameters headerCustomNamedRequestIdParamGroupingParameters,
            Context context) {
        return customNamedRequestIdParamGroupingWithResponseAsync(
                        headerCustomNamedRequestIdParamGroupingParameters, context)
                .flatMap((HeadersCustomNamedRequestIdParamGroupingResponse res) -> Mono.empty());
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request, via a parameter
     * group.
     *
     * @param headerCustomNamedRequestIdParamGroupingParameters Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void customNamedRequestIdParamGrouping(
            HeaderCustomNamedRequestIdParamGroupingParameters headerCustomNamedRequestIdParamGroupingParameters) {
        customNamedRequestIdParamGroupingAsync(headerCustomNamedRequestIdParamGroupingParameters).block();
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request, via a parameter
     * group.
     *
     * @param headerCustomNamedRequestIdParamGroupingParameters Parameter group.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public HeadersCustomNamedRequestIdParamGroupingResponse customNamedRequestIdParamGroupingWithResponse(
            HeaderCustomNamedRequestIdParamGroupingParameters headerCustomNamedRequestIdParamGroupingParameters,
            Context context) {
        return customNamedRequestIdParamGroupingWithResponseAsync(
                        headerCustomNamedRequestIdParamGroupingParameters, context)
                .block();
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersCustomNamedRequestIdHeadResponse> customNamedRequestIdHeadWithResponseAsync(
            String fooClientRequestId) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (fooClientRequestId == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter fooClientRequestId is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.customNamedRequestIdHead(this.client.getHost(), fooClientRequestId, accept, context));
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersCustomNamedRequestIdHeadResponse> customNamedRequestIdHeadWithResponseAsync(
            String fooClientRequestId, Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (fooClientRequestId == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter fooClientRequestId is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.customNamedRequestIdHead(this.client.getHost(), fooClientRequestId, accept, context);
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> customNamedRequestIdHeadAsync(String fooClientRequestId) {
        return customNamedRequestIdHeadWithResponseAsync(fooClientRequestId)
                .flatMap(
                        (HeadersCustomNamedRequestIdHeadResponse res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> customNamedRequestIdHeadAsync(String fooClientRequestId, Context context) {
        return customNamedRequestIdHeadWithResponseAsync(fooClientRequestId, context)
                .flatMap(
                        (HeadersCustomNamedRequestIdHeadResponse res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean customNamedRequestIdHead(String fooClientRequestId) {
        Boolean value = customNamedRequestIdHeadAsync(fooClientRequestId).block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Send foo-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param fooClientRequestId The fooRequestId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public HeadersCustomNamedRequestIdHeadResponse customNamedRequestIdHeadWithResponse(
            String fooClientRequestId, Context context) {
        return customNamedRequestIdHeadWithResponseAsync(fooClientRequestId, context).block();
    }
}
