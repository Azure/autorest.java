package fixtures.lro.implementation;

import com.azure.core.util.Context;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fixtures.lro.fluent.LrosCustomHeadersClient;
import fixtures.lro.fluent.models.ProductInner;
import fixtures.lro.models.LrosCustomHeaders;
import fixtures.lro.models.Product;

public final class LrosCustomHeadersImpl implements LrosCustomHeaders {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(LrosCustomHeadersImpl.class);

    private final LrosCustomHeadersClient innerClient;

    private final fixtures.lro.LroManager serviceManager;

    public LrosCustomHeadersImpl(LrosCustomHeadersClient innerClient, fixtures.lro.LroManager serviceManager) {
        this.innerClient = innerClient;
        this.serviceManager = serviceManager;
    }

    public Product putAsyncRetrySucceeded(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncRetrySucceeded(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRetrySucceeded() {
        ProductInner inner = this.serviceClient().putAsyncRetrySucceeded();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRetrySucceeded(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncRetrySucceeded(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
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

    public void post202Retry200(ProductInner product) {
        this.serviceClient().post202Retry200(product);
    }

    public void post202Retry200() {
        this.serviceClient().post202Retry200();
    }

    public void post202Retry200(ProductInner product, Context context) {
        this.serviceClient().post202Retry200(product, context);
    }

    public void postAsyncRetrySucceeded(ProductInner product) {
        this.serviceClient().postAsyncRetrySucceeded(product);
    }

    public void postAsyncRetrySucceeded() {
        this.serviceClient().postAsyncRetrySucceeded();
    }

    public void postAsyncRetrySucceeded(ProductInner product, Context context) {
        this.serviceClient().postAsyncRetrySucceeded(product, context);
    }

    private LrosCustomHeadersClient serviceClient() {
        return this.innerClient;
    }

    private fixtures.lro.LroManager manager() {
        return this.serviceManager;
    }
}
