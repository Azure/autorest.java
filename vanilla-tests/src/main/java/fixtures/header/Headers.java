package fixtures.header;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Base64Util;
import com.azure.core.util.Context;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
import fixtures.header.models.ErrorException;
import fixtures.header.models.GreyscaleColors;
import fixtures.header.models.HeadersResponseBoolResponse;
import fixtures.header.models.HeadersResponseByteResponse;
import fixtures.header.models.HeadersResponseDateResponse;
import fixtures.header.models.HeadersResponseDatetimeResponse;
import fixtures.header.models.HeadersResponseDatetimeRfc1123Response;
import fixtures.header.models.HeadersResponseDoubleResponse;
import fixtures.header.models.HeadersResponseDurationResponse;
import fixtures.header.models.HeadersResponseEnumResponse;
import fixtures.header.models.HeadersResponseExistingKeyResponse;
import fixtures.header.models.HeadersResponseFloatResponse;
import fixtures.header.models.HeadersResponseIntegerResponse;
import fixtures.header.models.HeadersResponseLongResponse;
import fixtures.header.models.HeadersResponseProtectedKeyResponse;
import fixtures.header.models.HeadersResponseStringResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Headers. */
public final class Headers {
    /** The proxy service used to perform REST calls. */
    private final HeadersService service;

    /** The service client containing this operation class. */
    private final AutoRestSwaggerBATHeaderService client;

    /**
     * Initializes an instance of Headers.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Headers(AutoRestSwaggerBATHeaderService client) {
        this.service = RestProxy.create(HeadersService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestSwaggerBATHeaderServiceHeaders to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATHe")
    private interface HeadersService {
        @Post("/header/param/existingkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramExistingKey(
                @HostParam("$host") String host, @HeaderParam("User-Agent") String userAgent, Context context);

        @Post("/header/response/existingkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseExistingKeyResponse> responseExistingKey(@HostParam("$host") String host, Context context);

        @Post("/header/param/protectedkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramProtectedKey(
                @HostParam("$host") String host, @HeaderParam("Content-Type") String contentType, Context context);

        @Post("/header/response/protectedkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseProtectedKeyResponse> responseProtectedKey(
                @HostParam("$host") String host, Context context);

        @Post("/header/param/prim/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramInteger(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") int value,
                Context context);

        @Post("/header/response/prim/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseIntegerResponse> responseInteger(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramLong(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") long value,
                Context context);

        @Post("/header/response/prim/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseLongResponse> responseLong(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramFloat(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") float value,
                Context context);

        @Post("/header/response/prim/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseFloatResponse> responseFloat(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDouble(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") double value,
                Context context);

        @Post("/header/response/prim/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDoubleResponse> responseDouble(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramBool(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") boolean value,
                Context context);

        @Post("/header/response/prim/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseBoolResponse> responseBool(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramString(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") String value,
                Context context);

        @Post("/header/response/prim/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseStringResponse> responseString(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDate(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") LocalDate value,
                Context context);

        @Post("/header/response/prim/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDateResponse> responseDate(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDatetime(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") OffsetDateTime value,
                Context context);

        @Post("/header/response/prim/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDatetimeResponse> responseDatetime(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDatetimeRfc1123(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") DateTimeRfc1123 value,
                Context context);

        @Post("/header/response/prim/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDatetimeRfc1123Response> responseDatetimeRfc1123(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDuration(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") Duration value,
                Context context);

        @Post("/header/response/prim/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDurationResponse> responseDuration(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramByte(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") String value,
                Context context);

        @Post("/header/response/prim/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseByteResponse> responseByte(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/param/prim/enum")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramEnum(
                @HostParam("$host") String host,
                @HeaderParam("scenario") String scenario,
                @HeaderParam("value") GreyscaleColors value,
                Context context);

        @Post("/header/response/prim/enum")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseEnumResponse> responseEnum(
                @HostParam("$host") String host, @HeaderParam("scenario") String scenario, Context context);

        @Post("/header/custom/x-ms-client-request-id/9C4D50EE-2D56-4CD3-8152-34347DC9F2B0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> customRequestId(@HostParam("$host") String host, Context context);
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramExistingKeyWithResponseAsync(String userAgent) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (userAgent == null) {
            return Mono.error(new IllegalArgumentException("Parameter userAgent is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramExistingKey(this.client.getHost(), userAgent, context));
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramExistingKeyAsync(String userAgent) {
        return paramExistingKeyWithResponseAsync(userAgent).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramExistingKey(String userAgent) {
        paramExistingKeyAsync(userAgent).block();
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "User-Agent": "overwrite".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseExistingKeyResponse> responseExistingKeyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseExistingKey(this.client.getHost(), context));
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "User-Agent": "overwrite".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseExistingKeyAsync() {
        return responseExistingKeyWithResponseAsync().flatMap((HeadersResponseExistingKeyResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseExistingKey() {
        responseExistingKeyAsync().block();
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramProtectedKeyWithResponseAsync(String contentType) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (contentType == null) {
            return Mono.error(new IllegalArgumentException("Parameter contentType is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramProtectedKey(this.client.getHost(), contentType, context));
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramProtectedKeyAsync(String contentType) {
        return paramProtectedKeyWithResponseAsync(contentType).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramProtectedKey(String contentType) {
        paramProtectedKeyAsync(contentType).block();
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "Content-Type": "text/html".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseProtectedKeyResponse> responseProtectedKeyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseProtectedKey(this.client.getHost(), context));
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "Content-Type": "text/html".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseProtectedKeyAsync() {
        return responseProtectedKeyWithResponseAsync()
                .flatMap((HeadersResponseProtectedKeyResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseProtectedKey() {
        responseProtectedKeyAsync().block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramIntegerWithResponseAsync(String scenario, int value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramInteger(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramIntegerAsync(String scenario, int value) {
        return paramIntegerWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramInteger(String scenario, int value) {
        paramIntegerAsync(scenario, value).block();
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 1 or -2.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseIntegerResponse> responseIntegerWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseInteger(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 1 or -2.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseIntegerAsync(String scenario) {
        return responseIntegerWithResponseAsync(scenario).flatMap((HeadersResponseIntegerResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseInteger(String scenario) {
        responseIntegerAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramLongWithResponseAsync(String scenario, long value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramLong(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramLongAsync(String scenario, long value) {
        return paramLongWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramLong(String scenario, long value) {
        paramLongAsync(scenario, value).block();
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 105 or -2.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseLongResponse> responseLongWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseLong(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 105 or -2.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseLongAsync(String scenario) {
        return responseLongWithResponseAsync(scenario).flatMap((HeadersResponseLongResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseLong(String scenario) {
        responseLongAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramFloatWithResponseAsync(String scenario, float value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramFloat(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramFloatAsync(String scenario, float value) {
        return paramFloatWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramFloat(String scenario, float value) {
        paramFloatAsync(scenario, value).block();
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseFloatResponse> responseFloatWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseFloat(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseFloatAsync(String scenario) {
        return responseFloatWithResponseAsync(scenario).flatMap((HeadersResponseFloatResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseFloat(String scenario) {
        responseFloatAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDoubleWithResponseAsync(String scenario, double value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramDouble(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDoubleAsync(String scenario, double value) {
        return paramDoubleWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDouble(String scenario, double value) {
        paramDoubleAsync(scenario, value).block();
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 7e120 or -3.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDoubleResponse> responseDoubleWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseDouble(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 7e120 or -3.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDoubleAsync(String scenario) {
        return responseDoubleWithResponseAsync(scenario).flatMap((HeadersResponseDoubleResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDouble(String scenario) {
        responseDoubleAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramBoolWithResponseAsync(String scenario, boolean value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramBool(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramBoolAsync(String scenario, boolean value) {
        return paramBoolWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramBool(String scenario, boolean value) {
        paramBoolAsync(scenario, value).block();
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": true or false.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseBoolResponse> responseBoolWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseBool(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": true or false.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseBoolAsync(String scenario) {
        return responseBoolWithResponseAsync(scenario).flatMap((HeadersResponseBoolResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseBool(String scenario) {
        responseBoolAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param value Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or "".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramStringWithResponseAsync(String scenario, String value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramString(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param value Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or "".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramStringAsync(String scenario, String value) {
        return paramStringWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param value Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or "".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramString(String scenario, String value) {
        paramStringAsync(scenario, value).block();
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseStringResponse> responseStringWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseString(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseStringAsync(String scenario) {
        return responseStringWithResponseAsync(scenario).flatMap((HeadersResponseStringResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseString(String scenario) {
        responseStringAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDateWithResponseAsync(String scenario, LocalDate value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        if (value == null) {
            return Mono.error(new IllegalArgumentException("Parameter value is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramDate(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDateAsync(String scenario, LocalDate value) {
        return paramDateWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDate(String scenario, LocalDate value) {
        paramDateAsync(scenario, value).block();
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "2010-01-01" or "0001-01-01".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDateResponse> responseDateWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseDate(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "2010-01-01" or "0001-01-01".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDateAsync(String scenario) {
        return responseDateWithResponseAsync(scenario).flatMap((HeadersResponseDateResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDate(String scenario) {
        responseDateAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeWithResponseAsync(String scenario, OffsetDateTime value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        if (value == null) {
            return Mono.error(new IllegalArgumentException("Parameter value is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramDatetime(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeAsync(String scenario, OffsetDateTime value) {
        return paramDatetimeWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetime(String scenario, OffsetDateTime value) {
        paramDatetimeAsync(scenario, value).block();
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDatetimeResponse> responseDatetimeWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseDatetime(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeAsync(String scenario) {
        return responseDatetimeWithResponseAsync(scenario)
                .flatMap((HeadersResponseDatetimeResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetime(String scenario) {
        responseDatetimeAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00
     *     GMT".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeRfc1123WithResponseAsync(String scenario, OffsetDateTime value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        DateTimeRfc1123 valueConverted = value == null ? null : new DateTimeRfc1123(value);
        return FluxUtil.withContext(
                context -> service.paramDatetimeRfc1123(this.client.getHost(), scenario, valueConverted, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00
     *     GMT".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeRfc1123Async(String scenario, OffsetDateTime value) {
        return paramDatetimeRfc1123WithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00
     *     GMT".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetimeRfc1123(String scenario, OffsetDateTime value) {
        paramDatetimeRfc1123Async(scenario, value).block();
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDatetimeRfc1123Response> responseDatetimeRfc1123WithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context -> service.responseDatetimeRfc1123(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeRfc1123Async(String scenario) {
        return responseDatetimeRfc1123WithResponseAsync(scenario)
                .flatMap((HeadersResponseDatetimeRfc1123Response res) -> Mono.empty());
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetimeRfc1123(String scenario) {
        responseDatetimeRfc1123Async(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDurationWithResponseAsync(String scenario, Duration value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        if (value == null) {
            return Mono.error(new IllegalArgumentException("Parameter value is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramDuration(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDurationAsync(String scenario, Duration value) {
        return paramDurationWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDuration(String scenario, Duration value) {
        paramDurationAsync(scenario, value).block();
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "P123DT22H14M12.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDurationResponse> responseDurationWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseDuration(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "P123DT22H14M12.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDurationAsync(String scenario) {
        return responseDurationWithResponseAsync(scenario)
                .flatMap((HeadersResponseDurationResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDuration(String scenario) {
        responseDurationAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramByteWithResponseAsync(String scenario, byte[] value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        if (value == null) {
            return Mono.error(new IllegalArgumentException("Parameter value is required and cannot be null."));
        }
        String valueConverted = Base64Util.encodeToString(value);
        return FluxUtil.withContext(
                context -> service.paramByte(this.client.getHost(), scenario, valueConverted, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramByteAsync(String scenario, byte[] value) {
        return paramByteWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramByte(String scenario, byte[] value) {
        paramByteAsync(scenario, value).block();
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseByteResponse> responseByteWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseByte(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseByteAsync(String scenario) {
        return responseByteWithResponseAsync(scenario).flatMap((HeadersResponseByteResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseByte(String scenario) {
        responseByteAsync(scenario).block();
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param value Send a post request with header values 'GREY'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramEnumWithResponseAsync(String scenario, GreyscaleColors value) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.paramEnum(this.client.getHost(), scenario, value, context));
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param value Send a post request with header values 'GREY'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramEnumAsync(String scenario, GreyscaleColors value) {
        return paramEnumWithResponseAsync(scenario, value).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param value Send a post request with header values 'GREY'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramEnum(String scenario, GreyscaleColors value) {
        paramEnumAsync(scenario, value).block();
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "GREY" or null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseEnumResponse> responseEnumWithResponseAsync(String scenario) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (scenario == null) {
            return Mono.error(new IllegalArgumentException("Parameter scenario is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.responseEnum(this.client.getHost(), scenario, context));
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "GREY" or null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseEnumAsync(String scenario) {
        return responseEnumWithResponseAsync(scenario).flatMap((HeadersResponseEnumResponse res) -> Mono.empty());
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseEnum(String scenario) {
        responseEnumAsync(scenario).block();
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> customRequestIdWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.customRequestId(this.client.getHost(), context));
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> customRequestIdAsync() {
        return customRequestIdWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void customRequestId() {
        customRequestIdAsync().block();
    }
}
