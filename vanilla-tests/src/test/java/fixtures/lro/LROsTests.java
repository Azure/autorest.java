package fixtures.lro;

import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpResponse;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.polling.SyncPoller;
import fixtures.lro.models.Product;
import fixtures.lro.models.Sku;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LROsTests {
    private static AutoRestLongRunningOperationTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestLongRunningOperationTestServiceBuilder().buildClient();
    }

    @Test
    public void beginPut200Succeeded() throws Exception {
        Product product = new Product();
        product.setLocation("West US");

        SyncPoller<Product, Product> poller = client.getLROs().beginPut200Succeeded(product);
        assertEquals("Succeeded", poller.getFinalResult().getProvisioningState());
    }
//
//    @Test
//    public void beginPatch200SucceededIgnoreHeaders() throws Exception {
//        Product product = new Product();
//        product.setLocation("West US");
//
//        SyncPoller<Product, Product> poller = client.getLROs().beginPatch200SucceededIgnoreHeaders(product);
//        assertEquals("Succeeded", poller.getFinalResult().getProvisioningState());
//    }

    @Test
    public void beginPut201Succeeded() throws Exception {
        Product product = new Product();
        product.setLocation("West US");

        SyncPoller<Product, Product> poller = client.getLROs().beginPut201Succeeded(product);
        assertEquals("Succeeded", poller.getFinalResult().getProvisioningState());
    }

    @Test
    public void beginPost202List() throws Exception {
        SyncPoller<List<Product>, List<Product>> poller = client.getLROs().beginPost202List();
        List<Product> products = poller.getFinalResult();
        assertEquals(1, products.size());
        assertEquals("100", products.get(0).getId());
        assertEquals("foo", products.get(0).getName());
    }

    @Test
    public void beginPut200SucceededNoStateWithContextValidation() throws Exception {
        ContextValidationPolicy contextValidationPolicy = new ContextValidationPolicy();
        this.client = new AutoRestLongRunningOperationTestServiceBuilder().addPolicy(contextValidationPolicy).buildClient();

        Context context = new Context(
                ContextValidationPolicy.CONTEXT_VALIDATION_KEY,
                ContextValidationPolicy.CONTEXT_VALIDATION_VALUE);

        Product product = new Product();
        product.setLocation("West US");

        SyncPoller<Product, Product> poller = client.getLROs().beginPut200SucceededNoState(product, context);
        Product actual = poller.getFinalResult();
        assertEquals("100", actual.getId());
        assertEquals("foo", actual.getName());

        Assertions.assertTrue(contextValidationPolicy.hasCalledPolicy && contextValidationPolicy.hasContext);
    }

    @Test
    public void beginPut200SucceededNoState() throws Exception {
        Product product = new Product();
        product.setLocation("West US");

        SyncPoller<Product, Product> poller = client.getLROs().beginPut200SucceededNoState(product);
        Product actual = poller.getFinalResult();
        assertEquals("100", actual.getId());
        assertEquals("foo", actual.getName());
    }

//    @Test
//    public void beginPut202Retry200() throws Exception {
//        Product product = new Product();
//        product.setLocation("West US");
//
//        SyncPoller<Product, Product> poller = client.getLROs().beginPut202Retry200(product);
//        Product actual = poller.getFinalResult();
//        assertEquals("100", actual.getId());
//        assertEquals("foo", actual.getName());
//    }

    @Test
    public void beginPut201CreatingSucceeded200() throws Exception {
        Product product = new Product();
        product.setLocation("West US");

        SyncPoller<Product, Product> poller = client.getLROs().beginPut201CreatingSucceeded200(product);
        Product actual = poller.getFinalResult();
        assertEquals("100", actual.getId());
        assertEquals("foo", actual.getName());
    }

    @Test
    public void beginPut200UpdatingSucceeded204() throws Exception {
        Product product = new Product();
        product.setLocation("West US");

        SyncPoller<Product, Product> poller = client.getLROs().beginPut200UpdatingSucceeded204(product);
        Product actual = poller.getFinalResult();
        assertEquals("100", actual.getId());
        assertEquals("foo", actual.getName());
    }

    @Test
    public void beginPut201CreatingFailed200() throws Exception {
        Product product = new Product();
        product.setLocation("West US");

        SyncPoller<Product, Product> poller = client.getLROs().beginPut201CreatingFailed200(product);
        Product actual = poller.getFinalResult();
        assertEquals("100", actual.getId());
        assertEquals("foo", actual.getName());
    }

    @Test
    public void beginPost200WithPayload() throws Exception {
        SyncPoller<Sku, Sku> poller = client.getLROs().beginPost200WithPayload();
        Sku actual = poller.getFinalResult();
        assertEquals("1", actual.getId());
        assertEquals("product", actual.getName());
    }

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
