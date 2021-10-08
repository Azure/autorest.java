package fixtures.lro.implementation;

import com.azure.core.util.Context;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fixtures.lro.fluent.LROsClient;
import fixtures.lro.fluent.models.ProductInner;
import fixtures.lro.fluent.models.SkuInner;
import fixtures.lro.fluent.models.SubProductInner;
import fixtures.lro.models.LROs;
import fixtures.lro.models.Product;
import fixtures.lro.models.Sku;
import fixtures.lro.models.SubProduct;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class LROsImpl implements LROs {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(LROsImpl.class);

    private final LROsClient innerClient;

    private final fixtures.lro.LroManager serviceManager;

    public LROsImpl(LROsClient innerClient, fixtures.lro.LroManager serviceManager) {
        this.innerClient = innerClient;
        this.serviceManager = serviceManager;
    }

    public Product put200Succeeded(ProductInner product) {
        ProductInner inner = this.serviceClient().put200Succeeded(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200Succeeded() {
        ProductInner inner = this.serviceClient().put200Succeeded();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200Succeeded(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().put200Succeeded(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product patch200SucceededIgnoreHeaders(ProductInner product) {
        ProductInner inner = this.serviceClient().patch200SucceededIgnoreHeaders(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product patch200SucceededIgnoreHeaders() {
        ProductInner inner = this.serviceClient().patch200SucceededIgnoreHeaders();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product patch200SucceededIgnoreHeaders(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().patch200SucceededIgnoreHeaders(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put201Succeeded(ProductInner product) {
        ProductInner inner = this.serviceClient().put201Succeeded(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put201Succeeded() {
        ProductInner inner = this.serviceClient().put201Succeeded();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put201Succeeded(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().put201Succeeded(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public List<Product> post202List() {
        List<ProductInner> inner = this.serviceClient().post202List();
        if (inner != null) {
            return Collections
                .unmodifiableList(
                    inner.stream().map(inner1 -> new ProductImpl(inner1, this.manager())).collect(Collectors.toList()));
        } else {
            return Collections.emptyList();
        }
    }

    public List<Product> post202List(Context context) {
        List<ProductInner> inner = this.serviceClient().post202List(context);
        if (inner != null) {
            return Collections
                .unmodifiableList(
                    inner.stream().map(inner1 -> new ProductImpl(inner1, this.manager())).collect(Collectors.toList()));
        } else {
            return Collections.emptyList();
        }
    }

    public Product put200SucceededNoState(ProductInner product) {
        ProductInner inner = this.serviceClient().put200SucceededNoState(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200SucceededNoState() {
        ProductInner inner = this.serviceClient().put200SucceededNoState();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200SucceededNoState(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().put200SucceededNoState(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put202Retry200(ProductInner product) {
        ProductInner inner = this.serviceClient().put202Retry200(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put202Retry200() {
        ProductInner inner = this.serviceClient().put202Retry200();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put202Retry200(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().put202Retry200(product, context);
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

    public Product put200UpdatingSucceeded204(ProductInner product) {
        ProductInner inner = this.serviceClient().put200UpdatingSucceeded204(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200UpdatingSucceeded204() {
        ProductInner inner = this.serviceClient().put200UpdatingSucceeded204();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200UpdatingSucceeded204(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().put200UpdatingSucceeded204(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put201CreatingFailed200(ProductInner product) {
        ProductInner inner = this.serviceClient().put201CreatingFailed200(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put201CreatingFailed200() {
        ProductInner inner = this.serviceClient().put201CreatingFailed200();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put201CreatingFailed200(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().put201CreatingFailed200(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200Acceptedcanceled200(ProductInner product) {
        ProductInner inner = this.serviceClient().put200Acceptedcanceled200(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200Acceptedcanceled200() {
        ProductInner inner = this.serviceClient().put200Acceptedcanceled200();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product put200Acceptedcanceled200(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().put200Acceptedcanceled200(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNoHeaderInRetry(ProductInner product) {
        ProductInner inner = this.serviceClient().putNoHeaderInRetry(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNoHeaderInRetry() {
        ProductInner inner = this.serviceClient().putNoHeaderInRetry();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putNoHeaderInRetry(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putNoHeaderInRetry(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
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

    public Product putAsyncNoRetrySucceeded(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncNoRetrySucceeded(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncNoRetrySucceeded() {
        ProductInner inner = this.serviceClient().putAsyncNoRetrySucceeded();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncNoRetrySucceeded(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncNoRetrySucceeded(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRetryFailed(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncRetryFailed(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRetryFailed() {
        ProductInner inner = this.serviceClient().putAsyncRetryFailed();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncRetryFailed(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncRetryFailed(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncNoRetrycanceled(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncNoRetrycanceled(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncNoRetrycanceled() {
        ProductInner inner = this.serviceClient().putAsyncNoRetrycanceled();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncNoRetrycanceled(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncNoRetrycanceled(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncNoHeaderInRetry(ProductInner product) {
        ProductInner inner = this.serviceClient().putAsyncNoHeaderInRetry(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncNoHeaderInRetry() {
        ProductInner inner = this.serviceClient().putAsyncNoHeaderInRetry();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product putAsyncNoHeaderInRetry(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().putAsyncNoHeaderInRetry(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Sku putNonResource(SkuInner sku) {
        SkuInner inner = this.serviceClient().putNonResource(sku);
        if (inner != null) {
            return new SkuImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Sku putNonResource() {
        SkuInner inner = this.serviceClient().putNonResource();
        if (inner != null) {
            return new SkuImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Sku putNonResource(SkuInner sku, Context context) {
        SkuInner inner = this.serviceClient().putNonResource(sku, context);
        if (inner != null) {
            return new SkuImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Sku putAsyncNonResource(SkuInner sku) {
        SkuInner inner = this.serviceClient().putAsyncNonResource(sku);
        if (inner != null) {
            return new SkuImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Sku putAsyncNonResource() {
        SkuInner inner = this.serviceClient().putAsyncNonResource();
        if (inner != null) {
            return new SkuImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Sku putAsyncNonResource(SkuInner sku, Context context) {
        SkuInner inner = this.serviceClient().putAsyncNonResource(sku, context);
        if (inner != null) {
            return new SkuImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public SubProduct putSubResource(SubProductInner product) {
        SubProductInner inner = this.serviceClient().putSubResource(product);
        if (inner != null) {
            return new SubProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public SubProduct putSubResource() {
        SubProductInner inner = this.serviceClient().putSubResource();
        if (inner != null) {
            return new SubProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public SubProduct putSubResource(SubProductInner product, Context context) {
        SubProductInner inner = this.serviceClient().putSubResource(product, context);
        if (inner != null) {
            return new SubProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public SubProduct putAsyncSubResource(SubProductInner product) {
        SubProductInner inner = this.serviceClient().putAsyncSubResource(product);
        if (inner != null) {
            return new SubProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public SubProduct putAsyncSubResource() {
        SubProductInner inner = this.serviceClient().putAsyncSubResource();
        if (inner != null) {
            return new SubProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public SubProduct putAsyncSubResource(SubProductInner product, Context context) {
        SubProductInner inner = this.serviceClient().putAsyncSubResource(product, context);
        if (inner != null) {
            return new SubProductImpl(inner, this.manager());
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

    public Product deleteProvisioning202DeletingFailed200() {
        ProductInner inner = this.serviceClient().deleteProvisioning202DeletingFailed200();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product deleteProvisioning202DeletingFailed200(Context context) {
        ProductInner inner = this.serviceClient().deleteProvisioning202DeletingFailed200(context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product deleteProvisioning202Deletingcanceled200() {
        ProductInner inner = this.serviceClient().deleteProvisioning202Deletingcanceled200();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product deleteProvisioning202Deletingcanceled200(Context context) {
        ProductInner inner = this.serviceClient().deleteProvisioning202Deletingcanceled200(context);
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

    public Product delete202Retry200() {
        ProductInner inner = this.serviceClient().delete202Retry200();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product delete202Retry200(Context context) {
        ProductInner inner = this.serviceClient().delete202Retry200(context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product delete202NoRetry204() {
        ProductInner inner = this.serviceClient().delete202NoRetry204();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product delete202NoRetry204(Context context) {
        ProductInner inner = this.serviceClient().delete202NoRetry204(context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public void deleteNoHeaderInRetry() {
        this.serviceClient().deleteNoHeaderInRetry();
    }

    public void deleteNoHeaderInRetry(Context context) {
        this.serviceClient().deleteNoHeaderInRetry(context);
    }

    public void deleteAsyncNoHeaderInRetry() {
        this.serviceClient().deleteAsyncNoHeaderInRetry();
    }

    public void deleteAsyncNoHeaderInRetry(Context context) {
        this.serviceClient().deleteAsyncNoHeaderInRetry(context);
    }

    public void deleteAsyncRetrySucceeded() {
        this.serviceClient().deleteAsyncRetrySucceeded();
    }

    public void deleteAsyncRetrySucceeded(Context context) {
        this.serviceClient().deleteAsyncRetrySucceeded(context);
    }

    public void deleteAsyncNoRetrySucceeded() {
        this.serviceClient().deleteAsyncNoRetrySucceeded();
    }

    public void deleteAsyncNoRetrySucceeded(Context context) {
        this.serviceClient().deleteAsyncNoRetrySucceeded(context);
    }

    public void deleteAsyncRetryFailed() {
        this.serviceClient().deleteAsyncRetryFailed();
    }

    public void deleteAsyncRetryFailed(Context context) {
        this.serviceClient().deleteAsyncRetryFailed(context);
    }

    public void deleteAsyncRetrycanceled() {
        this.serviceClient().deleteAsyncRetrycanceled();
    }

    public void deleteAsyncRetrycanceled(Context context) {
        this.serviceClient().deleteAsyncRetrycanceled(context);
    }

    public Sku post200WithPayload() {
        SkuInner inner = this.serviceClient().post200WithPayload();
        if (inner != null) {
            return new SkuImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Sku post200WithPayload(Context context) {
        SkuInner inner = this.serviceClient().post200WithPayload(context);
        if (inner != null) {
            return new SkuImpl(inner, this.manager());
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

    public Product post202NoRetry204(ProductInner product) {
        ProductInner inner = this.serviceClient().post202NoRetry204(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product post202NoRetry204() {
        ProductInner inner = this.serviceClient().post202NoRetry204();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product post202NoRetry204(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().post202NoRetry204(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postDoubleHeadersFinalLocationGet() {
        ProductInner inner = this.serviceClient().postDoubleHeadersFinalLocationGet();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postDoubleHeadersFinalLocationGet(Context context) {
        ProductInner inner = this.serviceClient().postDoubleHeadersFinalLocationGet(context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postDoubleHeadersFinalAzureHeaderGet() {
        ProductInner inner = this.serviceClient().postDoubleHeadersFinalAzureHeaderGet();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postDoubleHeadersFinalAzureHeaderGet(Context context) {
        ProductInner inner = this.serviceClient().postDoubleHeadersFinalAzureHeaderGet(context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postDoubleHeadersFinalAzureHeaderGetDefault() {
        ProductInner inner = this.serviceClient().postDoubleHeadersFinalAzureHeaderGetDefault();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postDoubleHeadersFinalAzureHeaderGetDefault(Context context) {
        ProductInner inner = this.serviceClient().postDoubleHeadersFinalAzureHeaderGetDefault(context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postAsyncRetrySucceeded(ProductInner product) {
        ProductInner inner = this.serviceClient().postAsyncRetrySucceeded(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postAsyncRetrySucceeded() {
        ProductInner inner = this.serviceClient().postAsyncRetrySucceeded();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postAsyncRetrySucceeded(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().postAsyncRetrySucceeded(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postAsyncNoRetrySucceeded(ProductInner product) {
        ProductInner inner = this.serviceClient().postAsyncNoRetrySucceeded(product);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postAsyncNoRetrySucceeded() {
        ProductInner inner = this.serviceClient().postAsyncNoRetrySucceeded();
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public Product postAsyncNoRetrySucceeded(ProductInner product, Context context) {
        ProductInner inner = this.serviceClient().postAsyncNoRetrySucceeded(product, context);
        if (inner != null) {
            return new ProductImpl(inner, this.manager());
        } else {
            return null;
        }
    }

    public void postAsyncRetryFailed(ProductInner product) {
        this.serviceClient().postAsyncRetryFailed(product);
    }

    public void postAsyncRetryFailed() {
        this.serviceClient().postAsyncRetryFailed();
    }

    public void postAsyncRetryFailed(ProductInner product, Context context) {
        this.serviceClient().postAsyncRetryFailed(product, context);
    }

    public void postAsyncRetrycanceled(ProductInner product) {
        this.serviceClient().postAsyncRetrycanceled(product);
    }

    public void postAsyncRetrycanceled() {
        this.serviceClient().postAsyncRetrycanceled();
    }

    public void postAsyncRetrycanceled(ProductInner product, Context context) {
        this.serviceClient().postAsyncRetrycanceled(product, context);
    }

    private LROsClient serviceClient() {
        return this.innerClient;
    }

    private fixtures.lro.LroManager manager() {
        return this.serviceManager;
    }
}
