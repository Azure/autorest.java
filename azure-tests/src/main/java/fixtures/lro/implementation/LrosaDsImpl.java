package fixtures.lro.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Headers;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.management.exception.ManagementException;
import com.azure.core.util.Context;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fixtures.lro.LroManager;
import fixtures.lro.fluent.LrosaDsClient;
import fixtures.lro.fluent.models.ProductInner;
import fixtures.lro.models.LrosaDs;
import fixtures.lro.models.Product;
import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class LrosaDsImpl implements LrosaDs {
    @JsonIgnore
    private final ClientLogger logger = new ClientLogger(LrosaDsImpl.class);

    private final LrosaDsClient innerClient;

    private final LroManager serviceManager;

    public LrosaDsImpl(LrosaDsClient innerClient, LroManager serviceManager) {
        this.innerClient = innerClient;
        this.serviceManager = serviceManager;
    }

    public Product putNonRetry400(ProductInner product) {
        ProductInner inner = this.serviceClient().putNonRetry400(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNonRetry400() {
        ProductInner inner = this.serviceClient().putNonRetry400();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNonRetry400(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putNonRetry400(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNonRetry201Creating400(ProductInner product) {
        ProductInner inner = this.serviceClient().putNonRetry201Creating400(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNonRetry201Creating400() {
        ProductInner inner = this.serviceClient().putNonRetry201Creating400();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNonRetry201Creating400(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putNonRetry201Creating400(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNonRetry201Creating400InvalidJson(ProductInner product) {
        ProductInner inner = this.serviceClient().putNonRetry201Creating400InvalidJson(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNonRetry201Creating400InvalidJson() {
        ProductInner inner = this.serviceClient().putNonRetry201Creating400InvalidJson();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNonRetry201Creating400InvalidJson(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putNonRetry201Creating400InvalidJson(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetry400(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetry400(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetry400() {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetry400();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetry400(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetry400(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public void deleteNonRetry400() {
        this.serviceClient().deleteNonRetry400();
    }

    public void deleteNonRetry400(Context context) {
        this.serviceClient().deleteNonRetry400(context);
    }

    public void delete202NonRetry400() {
        this.serviceClient().delete202NonRetry400();
    }

    public void delete202NonRetry400(Context context) {
        this.serviceClient().delete202NonRetry400(context);
    }

    public void deleteAsyncRelativeRetry400() {
        this.serviceClient().deleteAsyncRelativeRetry400();
    }

    public void deleteAsyncRelativeRetry400(Context context) {
        this.serviceClient().deleteAsyncRelativeRetry400(context);
    }

    public void postNonRetry400(ProductInner product) {
        this.serviceClient().postNonRetry400(product);
    }

    public void postNonRetry400() {
        this.serviceClient().postNonRetry400();
    }

    public void postNonRetry400(ProductInner product, Context context) {
        this.serviceClient().postNonRetry400(product, context);
    }

    public void post202NonRetry400(ProductInner product) {
        this.serviceClient().post202NonRetry400(product);
    }

    public void post202NonRetry400() {
        this.serviceClient().post202NonRetry400();
    }

    public void post202NonRetry400(ProductInner product, Context context) {
        this.serviceClient().post202NonRetry400(product, context);
    }

    public void postAsyncRelativeRetry400(ProductInner product) {
        this.serviceClient().postAsyncRelativeRetry400(product);
    }

    public void postAsyncRelativeRetry400() {
        this.serviceClient().postAsyncRelativeRetry400();
    }

    public void postAsyncRelativeRetry400(ProductInner product, Context context) {
        this.serviceClient().postAsyncRelativeRetry400(product, context);
    }

    public Product putError201NoProvisioningStatePayload(ProductInner product) {
        ProductInner inner = this.serviceClient().putError201NoProvisioningStatePayload(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putError201NoProvisioningStatePayload() {
        ProductInner inner = this.serviceClient().putError201NoProvisioningStatePayload();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putError201NoProvisioningStatePayload(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putError201NoProvisioningStatePayload(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryNoStatus(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryNoStatus(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryNoStatus() {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryNoStatus();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryNoStatus(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryNoStatus(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryNoStatusPayload(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryNoStatusPayload(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryNoStatusPayload() {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryNoStatusPayload();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryNoStatusPayload(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryNoStatusPayload(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public void delete204Succeeded() {
        this.serviceClient().delete204Succeeded();
    }

    public void delete204Succeeded(Context context) {
        this.serviceClient().delete204Succeeded(context);
    }

    public void deleteAsyncRelativeRetryNoStatus() {
        this.serviceClient().deleteAsyncRelativeRetryNoStatus();
    }

    public void deleteAsyncRelativeRetryNoStatus(Context context) {
        this.serviceClient().deleteAsyncRelativeRetryNoStatus(context);
    }

    public void post202NoLocation(ProductInner product) {
        this.serviceClient().post202NoLocation(product);
    }

    public void post202NoLocation() {
        this.serviceClient().post202NoLocation();
    }

    public void post202NoLocation(ProductInner product, Context context) {
        this.serviceClient().post202NoLocation(product, context);
    }

    public void postAsyncRelativeRetryNoPayload(ProductInner product) {
        this.serviceClient().postAsyncRelativeRetryNoPayload(product);
    }

    public void postAsyncRelativeRetryNoPayload() {
        this.serviceClient().postAsyncRelativeRetryNoPayload();
    }

    public void postAsyncRelativeRetryNoPayload(ProductInner product, Context context) {
        this.serviceClient().postAsyncRelativeRetryNoPayload(product, context);
    }

    public Product put200InvalidJson(ProductInner product) {
        ProductInner inner = this.serviceClient().put200InvalidJson(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200InvalidJson() {
        ProductInner inner = this.serviceClient().put200InvalidJson();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200InvalidJson(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().put200InvalidJson(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryInvalidHeader(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryInvalidHeader(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryInvalidHeader() {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryInvalidHeader();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryInvalidHeader(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryInvalidHeader(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryInvalidJsonPolling(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryInvalidJsonPolling(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryInvalidJsonPolling() {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryInvalidJsonPolling();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetryInvalidJsonPolling(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetryInvalidJsonPolling(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public void delete202RetryInvalidHeader() {
        this.serviceClient().delete202RetryInvalidHeader();
    }

    public void delete202RetryInvalidHeader(Context context) {
        this.serviceClient().delete202RetryInvalidHeader(context);
    }

    public void deleteAsyncRelativeRetryInvalidHeader() {
        this.serviceClient().deleteAsyncRelativeRetryInvalidHeader();
    }

    public void deleteAsyncRelativeRetryInvalidHeader(Context context) {
        this.serviceClient().deleteAsyncRelativeRetryInvalidHeader(context);
    }

    public void deleteAsyncRelativeRetryInvalidJsonPolling() {
        this.serviceClient().deleteAsyncRelativeRetryInvalidJsonPolling();
    }

    public void deleteAsyncRelativeRetryInvalidJsonPolling(Context context) {
        this.serviceClient().deleteAsyncRelativeRetryInvalidJsonPolling(context);
    }

    public void post202RetryInvalidHeader(ProductInner product) {
        this.serviceClient().post202RetryInvalidHeader(product);
    }

    public void post202RetryInvalidHeader() {
        this.serviceClient().post202RetryInvalidHeader();
    }

    public void post202RetryInvalidHeader(ProductInner product, Context context) {
        this.serviceClient().post202RetryInvalidHeader(product, context);
    }

    public void postAsyncRelativeRetryInvalidHeader(ProductInner product) {
        this.serviceClient().postAsyncRelativeRetryInvalidHeader(product);
    }

    public void postAsyncRelativeRetryInvalidHeader() {
        this.serviceClient().postAsyncRelativeRetryInvalidHeader();
    }

    public void postAsyncRelativeRetryInvalidHeader(ProductInner product, Context context) {
        this.serviceClient().postAsyncRelativeRetryInvalidHeader(product, context);
    }

    public void postAsyncRelativeRetryInvalidJsonPolling(ProductInner product) {
        this.serviceClient().postAsyncRelativeRetryInvalidJsonPolling(product);
    }

    public void postAsyncRelativeRetryInvalidJsonPolling() {
        this.serviceClient().postAsyncRelativeRetryInvalidJsonPolling();
    }

    public void postAsyncRelativeRetryInvalidJsonPolling(ProductInner product, Context context) {
        this.serviceClient().postAsyncRelativeRetryInvalidJsonPolling(product, context);
    }

    private LrosaDsClient serviceClient() {
        return this.innerClient;
    }

    private LroManager manager() {
        return this.serviceManager;
    }
}
