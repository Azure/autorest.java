package fixtures.lro.implementation;

import fixtures.lro.LroManager;
import fixtures.lro.fluent.models.SkuInner;
import fixtures.lro.models.Sku;

public final class SkuImpl implements Sku {
    private SkuInner innerObject;

    private final LroManager serviceManager;

    SkuImpl(SkuInner innerObject, LroManager serviceManager) {
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

    private LroManager manager() {
        return this.serviceManager;
    }
}
