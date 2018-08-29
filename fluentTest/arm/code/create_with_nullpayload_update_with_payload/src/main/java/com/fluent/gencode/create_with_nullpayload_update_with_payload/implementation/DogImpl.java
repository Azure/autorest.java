/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.create_with_nullpayload_update_with_payload.implementation;

import com.microsoft.azure.arm.resources.models.implementation.GroupableResourceCoreImpl;
import com.fluent.gencode.create_with_nullpayload_update_with_payload.Dog;
import rx.Observable;
import com.fluent.gencode.create_with_nullpayload_update_with_payload.DogUpdate;
import com.fluent.gencode.create_with_nullpayload_update_with_payload.CreationData;
import com.fluent.gencode.create_with_nullpayload_update_with_payload.ColorTypes;
import com.fluent.gencode.create_with_nullpayload_update_with_payload.DogSku;
import org.joda.time.DateTime;
import java.util.List;
import rx.functions.Func1;

class DogImpl extends GroupableResourceCoreImpl<Dog, DogInner, DogImpl, Create_With_NullPayload_Update_With_PayloadManager> implements Dog, Dog.Definition, Dog.Update {
    private DogUpdate updateParameter;
    DogImpl(String name, DogInner inner, Create_With_NullPayload_Update_With_PayloadManager manager) {
        super(name, inner, manager);
        this.updateParameter = new DogUpdate();
    }

    @Override
    public Observable<Dog> createResourceAsync() {
        DogsInner client = this.manager().inner().dogs();
        return client.createOrUpdateAsync(this.resourceGroupName(), this.name())
            .map(new Func1<DogInner, DogInner>() {
               @Override
               public DogInner call(DogInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<Dog> updateResourceAsync() {
        DogsInner client = this.manager().inner().dogs();
        return client.updateDogAsync(this.resourceGroupName(), this.name(), this.updateParameter)
            .map(new Func1<DogInner, DogInner>() {
               @Override
               public DogInner call(DogInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<DogInner> getInnerAsync() {
        DogsInner client = this.manager().inner().dogs();
        return client.getByResourceGroupAsync(this.resourceGroupName(), this.name());
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }

    private void resetCreateUpdateParameters() {
        this.updateParameter = new DogUpdate();
    }

    @Override
    public Integer animalSizeGB() {
        return this.inner().animalSizeGB();
    }

    @Override
    public CreationData creationData() {
        return this.inner().creationData();
    }

    @Override
    public String managedBy() {
        return this.inner().managedBy();
    }

    @Override
    public ColorTypes osType() {
        return this.inner().osType();
    }

    @Override
    public String provisioningState() {
        return this.inner().provisioningState();
    }

    @Override
    public DogSku sku() {
        return this.inner().sku();
    }

    @Override
    public DateTime timeCreated() {
        return this.inner().timeCreated();
    }

    @Override
    public List<String> zones() {
        return this.inner().zones();
    }

    @Override
    public DogImpl withAnimalSizeGB(Integer animalSizeGB) {
        this.updateParameter.withAnimalSizeGB(animalSizeGB);
        return this;
    }

    @Override
    public DogImpl withOsType(ColorTypes osType) {
        this.updateParameter.withOsType(osType);
        return this;
    }

    @Override
    public DogImpl withSku(DogSku sku) {
        this.updateParameter.withSku(sku);
        return this;
    }

}
