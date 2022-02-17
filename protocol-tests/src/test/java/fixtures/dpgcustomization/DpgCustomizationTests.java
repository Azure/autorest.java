// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.dpgcustomization;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import fixtures.dpgcustomization.models.Input;
import fixtures.dpgcustomization.models.LROProduct;
import fixtures.dpgcustomization.models.Product;
import fixtures.dpgcustomization.models.ProductReceived;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DpgCustomizationTests {

    private static DpgClient client;

    @BeforeAll
    public static void setup() {
        client = new DpgClientBuilder().buildClient();
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
        Assertions.assertEquals("raw", rawModel.get("received"));

        response = client.getPages("model", null);
        Assertions.assertEquals(200, response.iterableByPage().iterator().next().getStatusCode());
        List<Product> modelList = response.mapPage(data -> data.toObject(Product.class)).stream().collect(Collectors.toList());
        Assertions.assertEquals(ProductReceived.MODEL, modelList.get(0).getReceived());
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
        ProductSyncPoller modelPoller = new ProductSyncPoller(poller);
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, modelPoller.waitForCompletion().getStatus());
        LROProduct model = modelPoller.getFinalResult();
        Assertions.assertEquals(ProductReceived.MODEL, model.getReceived());
        Assertions.assertEquals("Succeeded", model.getProvisioningState());
    }

    static class ProductSyncPoller implements SyncPoller<LROProduct, LROProduct> {

        private final SyncPoller<BinaryData, BinaryData> poller;

        public ProductSyncPoller(SyncPoller<BinaryData, BinaryData> poller) {
            this.poller = poller;
        }

        private PollResponse<LROProduct> map(PollResponse<BinaryData> response) {
            return new PollResponse<>(response.getStatus(),
                    response.getValue().toObject(LROProduct.class),
                    response.getRetryAfter());
        }

        @Override
        public PollResponse<LROProduct> poll() {
            return map(poller.poll());
        }

        @Override
        public PollResponse<LROProduct> waitForCompletion() {
            return map(poller.waitForCompletion());
        }

        @Override
        public PollResponse<LROProduct> waitForCompletion(Duration timeout) {
            return map(poller.waitForCompletion(timeout));
        }

        @Override
        public PollResponse<LROProduct> waitUntil(LongRunningOperationStatus statusToWaitFor) {
            return map(poller.waitUntil(statusToWaitFor));
        }

        @Override
        public PollResponse<LROProduct> waitUntil(Duration timeout, LongRunningOperationStatus statusToWaitFor) {
            return map(poller.waitUntil(timeout, statusToWaitFor));
        }

        @Override
        public LROProduct getFinalResult() {
            return poller.getFinalResult().toObject(LROProduct.class);
        }

        @Override
        public void cancelOperation() {
            poller.cancelOperation();
        }
    }
}
