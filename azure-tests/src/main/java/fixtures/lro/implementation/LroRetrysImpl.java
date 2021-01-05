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
import fixtures.lro.fluent.LroRetrysClient;
import fixtures.lro.fluent.models.ProductInner;
import fixtures.lro.models.LroRetrys;
import fixtures.lro.models.Product;
import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class LroRetrysImpl implements LroRetrys {
    @JsonIgnore
    private final ClientLogger logger = new ClientLogger(LroRetrysImpl.class);

    private final LroRetrysClient innerClient;

    private final LroManager serviceManager;

    public LroRetrysImpl(LroRetrysClient innerClient, LroManager serviceManager) {
        this.innerClient = innerClient;
        this.serviceManager = serviceManager;
    }

    public Product put201CreatingSucceeded200(ProductInner product) {
        ProductInner inner = this.serviceClient().put201CreatingSucceeded200(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put201CreatingSucceeded200() {
        ProductInner inner = this.serviceClient().put201CreatingSucceeded200();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put201CreatingSucceeded200(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().put201CreatingSucceeded200(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetrySucceeded(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetrySucceeded(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetrySucceeded() {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetrySucceeded();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRelativeRetrySucceeded(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncRelativeRetrySucceeded(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product deleteProvisioning202Accepted200Succeeded() {
        ProductInner inner = this.serviceClient().deleteProvisioning202Accepted200Succeeded();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product deleteProvisioning202Accepted200Succeeded(Context context) {
        ProductInner inner = this.serviceClient().deleteProvisioning202Accepted200Succeeded(context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public void delete202Retry200() {
        this.serviceClient().delete202Retry200();
    }

    public void delete202Retry200(Context context) {
        this.serviceClient().delete202Retry200(context);
    }

    public void deleteAsyncRelativeRetrySucceeded() {
        this.serviceClient().deleteAsyncRelativeRetrySucceeded();
    }

    public void deleteAsyncRelativeRetrySucceeded(Context context) {
        this.serviceClient().deleteAsyncRelativeRetrySucceeded(context);
    }

    public void post202Retry200(ProductInner product) {
        this.serviceClient().post202Retry200(product);
    }

    public void post202Retry200() {
        this.serviceClient().post202Retry200();
    }

    public void post202Retry200(ProductInner product, Context context) {
        this.serviceClient().post202Retry200(product, context);
    }

    public void postAsyncRelativeRetrySucceeded(ProductInner product) {
        this.serviceClient().postAsyncRelativeRetrySucceeded(product);
    }

    public void postAsyncRelativeRetrySucceeded() {
        this.serviceClient().postAsyncRelativeRetrySucceeded();
    }

    public void postAsyncRelativeRetrySucceeded(ProductInner product, Context context) {
        this.serviceClient().postAsyncRelativeRetrySucceeded(product, context);
    }

    private LroRetrysClient serviceClient() {
        return this.innerClient;
    }

    private LroManager manager() {
        return this.serviceManager;
    }
}
