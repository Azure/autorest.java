package fixtures.url.multi;

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
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.url.multi.models.ErrorException;
import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Queries. */
public final class Queries {
    /** The proxy service used to perform REST calls. */
    private final QueriesService service;

    /** The service client containing this operation class. */
    private final AutoRestUrlMutliCollectionFormatTestService client;

    /**
     * Initializes an instance of Queries.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Queries(AutoRestUrlMutliCollectionFormatTestService client) {
        this.service = RestProxy.create(QueriesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestUrlMutliCollectionFormatTestServiceQueries to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestUrlMutliColl")
    private interface QueriesService {
        @Get("/queries/array/multi/string/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayStringMultiNull(
                @HostParam("$host") String host,
                @QueryParam(value = "arrayQuery", multipleQueryParams = true) List<String> arrayQuery,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/queries/array/multi/string/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayStringMultiEmpty(
                @HostParam("$host") String host,
                @QueryParam(value = "arrayQuery", multipleQueryParams = true) List<String> arrayQuery,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/queries/array/multi/string/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> arrayStringMultiValid(
                @HostParam("$host") String host,
                @QueryParam(value = "arrayQuery", multipleQueryParams = true) List<String> arrayQuery,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Get a null array of string using the multi-array format.
     *
     * @param arrayQuery a null array of string using the multi-array format.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array of string using the multi-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringMultiNullWithResponseAsync(List<String> arrayQuery) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        List<String> arrayQueryConverted =
                arrayQuery.stream().map((item) -> (item != null) ? item.toString() : "").collect(Collectors.toList());
        return FluxUtil.withContext(
                context -> service.arrayStringMultiNull(this.client.getHost(), arrayQueryConverted, accept, context));
    }

    /**
     * Get a null array of string using the multi-array format.
     *
     * @param arrayQuery a null array of string using the multi-array format.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array of string using the multi-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringMultiNullAsync(List<String> arrayQuery) {
        return arrayStringMultiNullWithResponseAsync(arrayQuery).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a null array of string using the multi-array format.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array of string using the multi-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringMultiNullAsync() {
        final List<String> arrayQuery = null;
        return arrayStringMultiNullWithResponseAsync(arrayQuery).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get a null array of string using the multi-array format.
     *
     * @param arrayQuery a null array of string using the multi-array format.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringMultiNull(List<String> arrayQuery) {
        arrayStringMultiNullAsync(arrayQuery).block();
    }

    /**
     * Get a null array of string using the multi-array format.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringMultiNull() {
        final List<String> arrayQuery = null;
        arrayStringMultiNullAsync(arrayQuery).block();
    }

    /**
     * Get an empty array [] of string using the multi-array format.
     *
     * @param arrayQuery an empty array [] of string using the multi-array format.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty array [] of string using the multi-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringMultiEmptyWithResponseAsync(List<String> arrayQuery) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        List<String> arrayQueryConverted =
                arrayQuery.stream().map((item) -> (item != null) ? item.toString() : "").collect(Collectors.toList());
        return FluxUtil.withContext(
                context -> service.arrayStringMultiEmpty(this.client.getHost(), arrayQueryConverted, accept, context));
    }

    /**
     * Get an empty array [] of string using the multi-array format.
     *
     * @param arrayQuery an empty array [] of string using the multi-array format.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty array [] of string using the multi-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringMultiEmptyAsync(List<String> arrayQuery) {
        return arrayStringMultiEmptyWithResponseAsync(arrayQuery).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an empty array [] of string using the multi-array format.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty array [] of string using the multi-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringMultiEmptyAsync() {
        final List<String> arrayQuery = null;
        return arrayStringMultiEmptyWithResponseAsync(arrayQuery).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an empty array [] of string using the multi-array format.
     *
     * @param arrayQuery an empty array [] of string using the multi-array format.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringMultiEmpty(List<String> arrayQuery) {
        arrayStringMultiEmptyAsync(arrayQuery).block();
    }

    /**
     * Get an empty array [] of string using the multi-array format.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringMultiEmpty() {
        final List<String> arrayQuery = null;
        arrayStringMultiEmptyAsync(arrayQuery).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array format.
     *
     * @param arrayQuery an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the
     *     mult-array format.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringMultiValidWithResponseAsync(List<String> arrayQuery) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        List<String> arrayQueryConverted =
                arrayQuery.stream().map((item) -> (item != null) ? item.toString() : "").collect(Collectors.toList());
        return FluxUtil.withContext(
                context -> service.arrayStringMultiValid(this.client.getHost(), arrayQueryConverted, accept, context));
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array format.
     *
     * @param arrayQuery an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the
     *     mult-array format.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringMultiValidAsync(List<String> arrayQuery) {
        return arrayStringMultiValidWithResponseAsync(arrayQuery).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array format.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> arrayStringMultiValidAsync() {
        final List<String> arrayQuery = null;
        return arrayStringMultiValidWithResponseAsync(arrayQuery).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array format.
     *
     * @param arrayQuery an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the
     *     mult-array format.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringMultiValid(List<String> arrayQuery) {
        arrayStringMultiValidAsync(arrayQuery).block();
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array format.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringMultiValid() {
        final List<String> arrayQuery = null;
        arrayStringMultiValidAsync(arrayQuery).block();
    }
}
