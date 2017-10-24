package fixtures.lro;

import com.microsoft.azure.AzureProxy;
import com.microsoft.azure.CloudException;
import com.microsoft.azure.serializer.AzureJacksonAdapter;
import com.microsoft.rest.LogLevel;
import com.microsoft.rest.RestClient;
import com.microsoft.rest.credentials.BasicAuthenticationCredentials;
import fixtures.lro.implementation.AutoRestLongRunningOperationTestServiceImpl;
import fixtures.lro.implementation.ProductInner;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LROSADsTests {
    private static AutoRestLongRunningOperationTestServiceImpl client;

    @BeforeClass
    public static void setup() {
        AzureProxy.setDefaultDelayInMilliseconds(0);
        RestClient restClient = new RestClient.Builder()
                .withBaseUrl("http://localhost:3000")
                .withCredentials(new BasicAuthenticationCredentials(null, null))
                .withSerializerAdapter(new AzureJacksonAdapter())
                .withLogLevel(LogLevel.BODY_AND_HEADERS)
                .build();

        client = new AutoRestLongRunningOperationTestServiceImpl(restClient);
    }

    @Test
    public void putNonRetry400() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().putNonRetry400(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void putNonRetry201Creating400() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().putNonRetry201Creating400(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void putAsyncRelativeRetry400() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().putAsyncRelativeRetry400(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void deleteNonRetry400() throws Exception {
        try {
            client.lROSADs().deleteNonRetry400();
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void delete202NonRetry400() throws Exception {
        try {
            client.lROSADs().delete202NonRetry400();
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void deleteAsyncRelativeRetry400() throws Exception {
        try {
            client.lROSADs().deleteAsyncRelativeRetry400();
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void postNonRetry400() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().postNonRetry400(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void post202NonRetry400() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().post202NonRetry400(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void postAsyncRelativeRetry400() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().postAsyncRelativeRetry400(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(400, ex.response().statusCode());
        }
    }

    @Test
    public void putError201NoProvisioningStatePayload() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().putError201NoProvisioningStatePayload(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(201, ex.response().statusCode());
            Assert.assertTrue(ex.getMessage().contains("does not contain a body"));
        }
    }

    @Test
    public void putAsyncRelativeRetryNoStatus() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().putAsyncRelativeRetryNoStatus(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(200, ex.response().statusCode());
            Assert.assertTrue(ex.getMessage().contains("does not contain a valid body"));
        }
    }

    @Test
    public void putAsyncRelativeRetryNoStatusPayload() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().putAsyncRelativeRetryNoStatusPayload(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(200, ex.response().statusCode());
            Assert.assertTrue(ex.getMessage().contains("does not contain a valid body"));
        }
    }

    @Test
    public void delete204Succeeded() throws Exception {
        client.lROSADs().delete204Succeeded();
    }

    @Test
    public void deleteAsyncRelativeRetryNoStatus() throws Exception {
        try {
            client.lROSADs().deleteAsyncRelativeRetryNoStatus();
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(200, ex.response().statusCode());
            Assert.assertTrue(ex.getMessage().contains("does not contain a valid body"));
        }
    }

    @Test
    public void post202NoLocation() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().post202NoLocation(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(202, ex.response().statusCode());
        }
    }

    @Test
    public void postAsyncRelativeRetryNoPayload() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().postAsyncRelativeRetryNoPayload(product);
            fail();
        } catch (CloudException ex) {
            Assert.assertEquals(200, ex.response().statusCode());
            Assert.assertTrue(ex.getMessage().contains("does not contain a valid body"));
        }
    }

    @Test
    public void put200InvalidJson() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().put200InvalidJson(product);
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Unexpected end-of-input"));
        }
    }

    @Test
    public void putAsyncRelativeRetryInvalidHeader() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().putAsyncRelativeRetryInvalidHeader(product);
            fail();
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"/bar\"", e.getMessage());
        }
    }

    @Test
    public void putAsyncRelativeRetryInvalidJsonPolling() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().putAsyncRelativeRetryInvalidJsonPolling(product);
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("does not contain a valid body"));
        }
    }

    @Test
    public void delete202RetryInvalidHeader() throws Exception {
        try {
            client.lROSADs().delete202RetryInvalidHeader();
            fail();
        } catch (IllegalArgumentException ignored) {

        }
    }

    @Test
    public void deleteAsyncRelativeRetryInvalidHeader() throws Exception {
        try {
            client.lROSADs().deleteAsyncRelativeRetryInvalidHeader();
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void deleteAsyncRelativeRetryInvalidJsonPolling() throws Exception {
        try {
            client.lROSADs().deleteAsyncRelativeRetryInvalidJsonPolling();
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("does not contain a valid body"));
        }
    }

    @Test
    public void post202RetryInvalidHeader() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().post202RetryInvalidHeader(product);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void postAsyncRelativeRetryInvalidHeader() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().postAsyncRelativeRetryInvalidHeader(product);
            fail();
        } catch (NumberFormatException ex) {
            Assert.assertEquals("For input string: \"/bar\"", ex.getMessage());
        }
    }

    @Test
    public void postAsyncRelativeRetryInvalidJsonPolling() throws Exception {
        ProductInner product = new ProductInner();
        product.withLocation("West US");
        try {
            client.lROSADs().postAsyncRelativeRetryInvalidJsonPolling(product);
            fail();
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("does not contain a valid body"));
        }
    }
}