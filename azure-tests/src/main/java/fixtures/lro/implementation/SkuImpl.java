package fixtures.lro.implementation;

import fixtures.lro.fluent.models.SkuInner;
import fixtures.lro.models.Sku;

public final class SkuImpl implements Sku {
    private SkuInner innerObject;

    private final fixtures.lro.LroManager serviceManager;

    SkuImpl(SkuInner innerObject, fixtures.lro.LroManager serviceManager) {
        this.innerObject = innerObject;
        this.serviceManager = serviceManager;
    }

    public String name() {
        return this.innerModel().name();
    }

    public String id() {
        return this.innerModel().id();
    }

    public SkuInner innerModel() {
        return this.innerObject;
    }

    private fixtures.lro.LroManager manager() {
        return this.serviceManager;
    }
}
