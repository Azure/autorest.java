package fixtures.lro.implementation;

import fixtures.lro.fluent.models.SubProductInner;
import fixtures.lro.models.SubProduct;
import fixtures.lro.models.SubProductPropertiesProvisioningStateValues;

public final class SubProductImpl implements SubProduct {
    private SubProductInner innerObject;

    private final fixtures.lro.LroManager serviceManager;

    SubProductImpl(SubProductInner innerObject, fixtures.lro.LroManager serviceManager) {
        this.innerObject = innerObject;
        this.serviceManager = serviceManager;
    }

    public String id() {
        return this.innerModel().id();
    }

    public String provisioningState() {
        return this.innerModel().provisioningState();
    }

    public SubProductPropertiesProvisioningStateValues provisioningStateValues() {
        return this.innerModel().provisioningStateValues();
    }

    public SubProductInner innerModel() {
        return this.innerObject;
    }

    private fixtures.lro.LroManager manager() {
        return this.serviceManager;
    }
}
