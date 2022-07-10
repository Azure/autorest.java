// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.dpgcustomization;

import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
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
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.SyncPoller;
import com.azure.core.util.serializer.TypeReference;
import fixtures.dpgcustomization.models.Input;
import fixtures.dpgcustomization.models.LroProduct;
import fixtures.dpgcustomization.models.Product;
import fixtures.dpgcustomization.models.ProductReceived;
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

        Input modelInput = new Input("world!");
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
    public void pagingContextValidation() {
        ContextValidationPolicy contextValidationPolicy = new ContextValidationPolicy();
        this.asyncClient = new DpgClientBuilder().addPolicy(contextValidationPolicy).buildAsyncClient();

        Context context = new Context(
                ContextValidationPolicy.CONTEXT_VALIDATION_KEY,
                ContextValidationPolicy.CONTEXT_VALIDATION_VALUE);

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

        Assertions.assertTrue(contextValidationPolicy.hasCalledPolicy && contextValidationPolicy.hasContext);
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

    @Test
    public void lroContextValidation() {
        ContextValidationPolicy contextValidationPolicy = new ContextValidationPolicy();
        this.asyncClient = new DpgClientBuilder().addPolicy(contextValidationPolicy).buildAsyncClient();

        Context context = new Context(
                ContextValidationPolicy.CONTEXT_VALIDATION_KEY,
                ContextValidationPolicy.CONTEXT_VALIDATION_VALUE);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);

        PollerFlux<BinaryData, BinaryData> poller = asyncClient.beginLro("raw", requestOptions);

        BinaryData binaryData = poller.last().block().getFinalResult().block();
        Map<String, String> rawModel = (Map<String, String>) binaryData.toObject(Object.class);
        Assertions.assertEquals("raw", rawModel.get("received"));

        Assertions.assertTrue(contextValidationPolicy.hasCalledPolicy && contextValidationPolicy.hasContext);

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

//    @Test
//    public void testSendRequestMethod() {
//        HttpRequest request = new HttpRequest(HttpMethod.GET, "http://localhost:3000/customization/model/raw");
//        Response<BinaryData> response = client.sendRequest(request, Context.NONE);
//        Assertions.assertEquals(200, response.getStatusCode());
//        Map<String, String> rawModel = (Map<String, String>) response.getValue().toObject(Object.class);
//        Assertions.assertTrue(rawModel.containsKey("received"));
//        Assertions.assertEquals("raw", rawModel.get("received"));
//
//        response = asyncClient.sendRequest(request).block();
//        Assertions.assertEquals(200, response.getStatusCode());
//        rawModel = (Map<String, String>) response.getValue().toObject(Object.class);
//        Assertions.assertTrue(rawModel.containsKey("received"));
//        Assertions.assertEquals("raw", rawModel.get("received"));
//    }

    static class ContextValidationPolicy implements HttpPipelinePolicy {

        public static final String CONTEXT_VALIDATION_KEY = "CONTEXT_VALIDATION_KEY";
        public static final String CONTEXT_VALIDATION_VALUE = "CONTEXT_VALIDATION_VALUE";
        public boolean hasContext = true;
        public boolean hasCalledPolicy = false;

        ContextValidationPolicy() {
        }

        @Override
        public Mono<HttpResponse> process(HttpPipelineCallContext httpPipelineCallContext, HttpPipelineNextPolicy httpPipelineNextPolicy) {
            hasCalledPolicy = true;
            if (httpPipelineCallContext.getData(CONTEXT_VALIDATION_KEY).isPresent() &&
                httpPipelineCallContext.getData(CONTEXT_VALIDATION_KEY).get().equals(CONTEXT_VALIDATION_VALUE)){
                hasContext = hasContext && true;
            } else {
                hasContext = false;
            }
            return httpPipelineNextPolicy.process();
        }

    }

}
