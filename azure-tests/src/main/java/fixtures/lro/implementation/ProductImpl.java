package fixtures.lro.implementation;

import fixtures.lro.fluent.models.ProductInner;
import fixtures.lro.models.Product;
import fixtures.lro.models.ProductPropertiesProvisioningStateValues;
import java.util.Collections;
import java.util.Map;

public final class ProductImpl implements Product {
    private ProductInner innerObject;

    private final fixtures.lro.LroManager serviceManager;

    ProductImpl(ProductInner innerObject, fixtures.lro.LroManager serviceManager) {
        this.innerObject = innerObject;
        this.serviceManager = serviceManager;
    }

    public String id() {
        return this.innerModel().id();
    }

    public String name() {
        return this.innerModel().name();
    }

    public String type() {
        return this.innerModel().type();
    }

    public String location() {
        return this.innerModel().location();
    }

    public Map<String, String> tags() {
        Map<String, String> inner = this.innerModel().tags();
        if (inner != null) {
            return Collections.unmodifiableMap(inner);
        } else {
            return Collections.emptyMap();
        }
    }

    public String provisioningState() {
        return this.innerModel().provisioningState();
    }

    public ProductPropertiesProvisioningStateValues provisioningStateValues() {
        return this.innerModel().provisioningStateValues();
    }

    public ProductInner innerModel() {
        return this.innerObject;
    }

    private fixtures.lro.LroManager manager() {
        return this.serviceManager;
    }
}
