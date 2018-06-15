/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.child_in_parent_opgroup.implementation;

import com.fluent.gencode.child_in_parent_opgroup.Puppy;
import com.microsoft.azure.arm.model.implementation.CreatableUpdatableImpl;
import rx.Observable;
import com.fluent.gencode.child_in_parent_opgroup.CatUpdate;
import java.util.Map;
import com.fluent.gencode.child_in_parent_opgroup.PuppySku;
import org.joda.time.DateTime;
import com.fluent.gencode.child_in_parent_opgroup.ColorTypes;
import com.fluent.gencode.child_in_parent_opgroup.CreationData;
import rx.functions.Func1;

class PuppyImpl extends CreatableUpdatableImpl<Puppy, PuppyInner, PuppyImpl> implements Puppy, Puppy.Definition, Puppy.Update {
    private final Child_In_Parent_OpGroupManager manager;
    private String resourceGroupName;
    private String dogName;
    private String puppyName;
    private CatUpdate updateParameter;

    PuppyImpl(String name, Child_In_Parent_OpGroupManager manager) {
        super(name, new PuppyInner());
        this.manager = manager;
        // Set resource name
        this.puppyName = name;
        //
        this.updateParameter = new CatUpdate();
    }

    PuppyImpl(PuppyInner inner, Child_In_Parent_OpGroupManager manager) {
        super(inner.name(), inner);
        this.manager = manager;
        // Set resource name
        this.puppyName = inner.name();
        // resource ancestor names
        this.resourceGroupName = IdParsingUtils.getValueFromIdByName(inner.id(), "resourceGroups");
        this.dogName = IdParsingUtils.getValueFromIdByName(inner.id(), "dogs");
        this.puppyName = IdParsingUtils.getValueFromIdByName(inner.id(), "puppies");
        //
        this.updateParameter = new CatUpdate();
    }

    @Override
    public Child_In_Parent_OpGroupManager manager() {
        return this.manager;
    }

    @Override
    public Observable<Puppy> createResourceAsync() {
        DogsInner client = this.manager().inner().dogs();
        return client.createOrUpdatePuppyAsync(this.resourceGroupName, this.dogName, this.puppyName, this.inner())
            .map(new Func1<PuppyInner, PuppyInner>() {
               @Override
               public PuppyInner call(PuppyInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<Puppy> updateResourceAsync() {
        DogsInner client = this.manager().inner().dogs();
        return client.updatePuppyAsync(this.resourceGroupName, this.dogName, this.puppyName, this.updateParameter)
            .map(new Func1<PuppyInner, PuppyInner>() {
               @Override
               public PuppyInner call(PuppyInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<PuppyInner> getInnerAsync() {
        DogsInner client = this.manager().inner().dogs();
        return client.getPuppyAsync(this.resourceGroupName, this.dogName, this.puppyName);
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }

    private void resetCreateUpdateParameters() {
        this.updateParameter = new CatUpdate();
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
    public String id() {
        return this.inner().id();
    }

    @Override
    public String location() {
        return this.inner().location();
    }

    @Override
    public String managedBy() {
        return this.inner().managedBy();
    }

    @Override
    public String name() {
        return this.inner().name();
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
    public PuppySku sku() {
        return this.inner().sku();
    }

    @Override
    public Map<String, String> tags() {
        return this.inner().getTags();
    }

    @Override
    public DateTime timeCreated() {
        return this.inner().timeCreated();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

    @Override
    public PuppyImpl withExistingDog(String resourceGroupName, String dogName) {
        this.resourceGroupName = resourceGroupName;
        this.dogName = dogName;
        return this;
    }

    @Override
    public PuppyImpl withCreationData(CreationData creationData) {
        this.inner().withCreationData(creationData);
        return this;
    }

    @Override
    public PuppyImpl withLocation(String location) {
        this.inner().withLocation(location);
        return this;
    }

    @Override
    public PuppyImpl withAnimalSizeGB(Integer animalSizeGB) {
        if (isInCreateMode()) {
            this.inner().withAnimalSizeGB(animalSizeGB);
        } else {
            this.updateParameter.withAnimalSizeGB(animalSizeGB);
        }
        return this;
    }

    @Override
    public PuppyImpl withOsType(ColorTypes osType) {
        if (isInCreateMode()) {
            this.inner().withOsType(osType);
        } else {
            this.updateParameter.withOsType(osType);
        }
        return this;
    }

    @Override
    public PuppyImpl withSku(PuppySku sku) {
        if (isInCreateMode()) {
            this.inner().withSku(sku);
        } else {
            this.updateParameter.withSku(sku);
        }
        return this;
    }

    @Override
    public PuppyImpl withTags(Map<String, String> tags) {
        if (isInCreateMode()) {
            this.inner().withTags(tags);
        } else {
            this.updateParameter.withTags(tags);
        }
        return this;
    }

}
