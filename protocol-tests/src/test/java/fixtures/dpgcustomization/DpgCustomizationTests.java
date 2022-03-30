// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.dpgcustomization;

import com.azure.core.http.HttpMethod;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.paging.PageRetriever;
import com.azure.core.util.polling.DefaultPollingStrategy;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.SyncPoller;
import com.azure.core.util.serializer.TypeReference;
import fixtures.dpgcustomization.implementation.models.Input;
import fixtures.dpgcustomization.implementation.models.LroProduct;
import fixtures.dpgcustomization.implementation.models.Product;
import fixtures.dpgcustomization.implementation.models.ProductReceived;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DpgCustomizationTests {

    private static DpgClient client;
    private static DpgAsyncClient asyncClient;

    @BeforeAll
    public static void setup() {
        client = new DpgClientBuilder().buildClient();
        asyncClient = new DpgClientBuilder().buildAsyncClient();
    }

    @Test
    public void getModel() {
        Response<BinaryData> response = client.getModelWithResponse("raw", null);
        Assertions.assertEquals(200, response.getStatusCode());
        Map<String, String> rawModel = (Map<String, String>) response.getValue().toObject(Object.class);
        Assertions.assertTrue(rawModel.containsKey("received"));
        Assertions.assertEquals("raw", rawModel.get("received"));

        response = client.getModelWithResponse("model", null);
        Assertions.assertEquals(200, response.getStatusCode());
        Product model = response.getValue().toObject(Product.class);
        Assertions.assertEquals(ProductReceived.MODEL, model.getReceived());
    }

    @Test
    public void postModel() {
        BinaryData rawInput = BinaryData.fromString("{\"hello\":\"world!\"}");
        Response<BinaryData> response = client.postModelWithResponse("raw", rawInput, null);
        Assertions.assertEquals(200, response.getStatusCode());

        Input modelInput = new Input().setHello("world!");
        response = client.postModelWithResponse("model", BinaryData.fromObject(modelInput), null);
        Assertions.assertEquals(200, response.getStatusCode());
        Product model = response.getValue().toObject(Product.class);
        Assertions.assertEquals(ProductReceived.MODEL, model.getReceived());
    }

    @Test
    public void getPages() {
        PagedIterable<BinaryData> response = client.getPages("raw", null);
        Assertions.assertEquals(200, response.iterableByPage().iterator().next().getStatusCode());
        List<BinaryData> rawList = response.stream().collect(Collectors.toList());
        Map<String, String> rawModel = (Map<String, String>) rawList.get(0).toObject(Object.class);
        Assertions.assertTrue(rawModel.containsKey("received"));
//        Assertions.assertEquals("raw", rawModel.get("received"));

        PagedFlux<BinaryData> pagedFlux = asyncClient.getPages("model", null);
        // PagedFlux<BinaryData> to PagedFlux<Product>
        PagedFlux<Product> modelPagedFlux = mapPage(pagedFlux, data -> data.toObject(Product.class));
        // PagedIterable from PagedFlux
        PagedIterable<Product> modelPagedIterable = new PagedIterable<>(modelPagedFlux);
        Assertions.assertEquals(200, modelPagedIterable.iterableByPage().iterator().next().getStatusCode());
        List<Product> modelList = modelPagedIterable.stream().collect(Collectors.toList());
        Assertions.assertEquals(ProductReceived.MODEL, modelList.get(0).getReceived());
    }

    @Test
    public void getPagesTracing() {
        TracingValidationPolicy tracingValidationPolicy = new TracingValidationPolicy();
        this.asyncClient = new DpgClientBuilder().addPolicy(tracingValidationPolicy).buildAsyncClient();

        Tracer TRACER = configureJaegerExporter();

        Span userParentSpan = TRACER.spanBuilder("user-parent-span").startSpan();

        Context context = new Context(
                com.azure.core.util.tracing.Tracer.PARENT_TRACE_CONTEXT_KEY,
                io.opentelemetry.context.Context.current().with(userParentSpan));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);

        PagedFlux<BinaryData> pagedFlux = asyncClient.getPages("model", requestOptions);
        // PagedFlux<BinaryData> to PagedFlux<Product>
        PagedFlux<Product> modelPagedFlux = mapPage(pagedFlux, data -> data.toObject(Product.class));
        // PagedIterable from PagedFlux
        PagedIterable<Product> modelPagedIterable = new PagedIterable<>(modelPagedFlux);
        Assertions.assertEquals(200, modelPagedIterable.iterableByPage().iterator().next().getStatusCode());
        List<Product> modelList = modelPagedIterable.stream().collect(Collectors.toList());
        Assertions.assertEquals(ProductReceived.MODEL, modelList.get(0).getReceived());

        Assertions.assertTrue(tracingValidationPolicy.context.getData("trace-context").isPresent());

        userParentSpan.end();
    }

    private static <T, S> PagedFlux<S> mapPage(PagedFlux<T> pagedFlux, Function<T, S> mapper) {
        Supplier<PageRetriever<String, PagedResponse<S>>> provider = () -> (continuationToken, pageSize) -> {
            Flux<PagedResponse<T>> flux = (continuationToken == null)
                    ? pagedFlux.byPage().take(1)
                    : pagedFlux.byPage(continuationToken).take(1);
            return flux.map(mapPagedResponse(mapper));
        };
        return PagedFlux.create(provider);
    }

    private static <T, S> Function<PagedResponse<T>, PagedResponse<S>> mapPagedResponse(Function<T, S> mapper) {
        return pagedResponse -> new PagedResponseBase<Void, S>(pagedResponse.getRequest(),
                pagedResponse.getStatusCode(),
                pagedResponse.getHeaders(),
                pagedResponse.getValue().stream().map(mapper).collect(Collectors.toList()),
                pagedResponse.getContinuationToken(),
                null);
    }

    /**
     * Configure the OpenTelemetry {@link LoggingSpanExporter} to enable tracing.
     *
     * @return The OpenTelemetry {@link Tracer} instance.
     */
    private static Tracer configureJaegerExporter() {
        // Create a channel towards Jaeger end point
        ManagedChannel jaegerChannel =
                ManagedChannelBuilder.forAddress("localhost", 14250).usePlaintext().build();
        // Export traces to Jaeger
        JaegerGrpcSpanExporter jaegerExporter =
                JaegerGrpcSpanExporter.builder()
                        .setChannel(jaegerChannel)
                        .setTimeout(Duration.ofMinutes(30000))
                        .build();

        // Set to process the spans by the Jaeger Exporter
        OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(
                        SdkTracerProvider.builder().addSpanProcessor(SimpleSpanProcessor.create(jaegerExporter)).build())
                .build();
        return openTelemetry.getSdkTracerProvider().get("List-KV-Secrets-Sample");
    }

    @Test
    public void lroTracing() {
        TracingValidationPolicy tracingValidationPolicy = new TracingValidationPolicy();
        this.asyncClient = new DpgClientBuilder().addPolicy(tracingValidationPolicy).buildAsyncClient();
        Tracer TRACER = configureJaegerExporter();

        Span userParentSpan = TRACER.spanBuilder("user-parent-span").startSpan();

        Context context = new Context(
                com.azure.core.util.tracing.Tracer.PARENT_TRACE_CONTEXT_KEY,
                io.opentelemetry.context.Context.current().with(userParentSpan));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);

        PollerFlux<BinaryData, BinaryData> poller = asyncClient.beginLro("raw", requestOptions);

        BinaryData binaryData = poller.last().block().getFinalResult().block();
        Map<String, String> rawModel = (Map<String, String>) binaryData.toObject(Object.class);
        Assertions.assertEquals("raw", rawModel.get("received"));

        Assertions.assertTrue(tracingValidationPolicy.context.getData("trace-context").isPresent());

        userParentSpan.end();
    }

    @Test
    public void lro() {
        SyncPoller<BinaryData, BinaryData> poller = client.beginLro("raw", null);
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, poller.waitForCompletion().getStatus());
        Map<String, String> rawModel = (Map<String, String>) poller.getFinalResult().toObject(Object.class);
        Assertions.assertTrue(rawModel.containsKey("received"));
        Assertions.assertEquals("raw", rawModel.get("received"));
        Assertions.assertEquals("Succeeded", rawModel.get("provisioningState"));

        poller = client.beginLro("model", null);
        SyncPoller<LroProduct, LroProduct> modelPoller = new ProductSyncPoller(poller);
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, modelPoller.waitForCompletion().getStatus());
        LroProduct model = modelPoller.getFinalResult();
        Assertions.assertEquals(ProductReceived.MODEL, model.getReceived());
        Assertions.assertEquals("Succeeded", model.getProvisioningState());

        // PollerFlux<LroProduct, LroProduct>
        HttpPipeline httpPipeline = new HttpPipelineBuilder().build();  // httpPipeline exists in serviceClient
        PollerFlux<LroProduct, LroProduct> modelPollerFlux = PollerFlux.create(
                Duration.ofSeconds(1),
                () -> asyncClient.lroWithResponse("model", null),
                new DefaultPollingStrategy<>(httpPipeline),
                new TypeReferenceLroProduct(),
                new TypeReferenceLroProduct());
        model = modelPollerFlux.last().block().getFinalResult().block();
        Assertions.assertEquals(ProductReceived.MODEL, model.getReceived());
        Assertions.assertEquals("Succeeded", model.getProvisioningState());
        // SyncPoller from PollerFlux
        modelPoller = modelPollerFlux.getSyncPoller();
    }

    private static final class TypeReferenceLroProduct extends TypeReference<LroProduct> {
        // empty
    }

    static class ProductSyncPoller implements SyncPoller<LroProduct, LroProduct> {

        private final SyncPoller<BinaryData, BinaryData> poller;

        public ProductSyncPoller(SyncPoller<BinaryData, BinaryData> poller) {
            this.poller = poller;
        }

        private static PollResponse<LroProduct> map(PollResponse<BinaryData> response) {
            return new PollResponse<>(response.getStatus(),
                    response.getValue().toObject(LroProduct.class),
                    response.getRetryAfter());
        }

        @Override
        public PollResponse<LroProduct> poll() {
            return map(poller.poll());
        }

        @Override
        public PollResponse<LroProduct> waitForCompletion() {
            return map(poller.waitForCompletion());
        }

        @Override
        public PollResponse<LroProduct> waitForCompletion(Duration timeout) {
            return map(poller.waitForCompletion(timeout));
        }

        @Override
        public PollResponse<LroProduct> waitUntil(LongRunningOperationStatus statusToWaitFor) {
            return map(poller.waitUntil(statusToWaitFor));
        }

        @Override
        public PollResponse<LroProduct> waitUntil(Duration timeout, LongRunningOperationStatus statusToWaitFor) {
            return map(poller.waitUntil(timeout, statusToWaitFor));
        }

        @Override
        public LroProduct getFinalResult() {
            return poller.getFinalResult().toObject(LroProduct.class);
        }

        @Override
        public void cancelOperation() {
            poller.cancelOperation();
        }
    }

    @Test
    public void testSendRequestMethod() {
        HttpRequest request = new HttpRequest(HttpMethod.GET, "http://localhost:3000/customization/model/raw");
        Response<BinaryData> response = client.sendRequest(request, Context.NONE);
        Assertions.assertEquals(200, response.getStatusCode());
        Map<String, String> rawModel = (Map<String, String>) response.getValue().toObject(Object.class);
        Assertions.assertTrue(rawModel.containsKey("received"));
        Assertions.assertEquals("raw", rawModel.get("received"));

        response = asyncClient.sendRequest(request).block();
        Assertions.assertEquals(200, response.getStatusCode());
        rawModel = (Map<String, String>) response.getValue().toObject(Object.class);
        Assertions.assertTrue(rawModel.containsKey("received"));
        Assertions.assertEquals("raw", rawModel.get("received"));
    }

    static class TracingValidationPolicy implements HttpPipelinePolicy {

        public HttpPipelineCallContext context;

        TracingValidationPolicy() {
        }

        @Override
        public Mono<HttpResponse> process(HttpPipelineCallContext httpPipelineCallContext, HttpPipelineNextPolicy httpPipelineNextPolicy) {
            this.context = httpPipelineCallContext;
            return httpPipelineNextPolicy.process();
        }

    }

}
