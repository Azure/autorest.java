# Scenario: Add new convenience layer for new resource(s)

Use this when `<resource-name>` exists in the generated (inner) layer but has no convenience layer yet.

## Examine the inner model

1. Read `<src-base>/fluent/models/<ResourceName>Inner.java` to understand all properties.
2. Read `<src-base>/fluent/<ResourceName>sClient.java` (the inner client interface) to understand available operations (CRUD, list, etc.).
3. Determine the resource type:
   - **Groupable resource**: Has `resourceGroupName` in CRUD operations, extends `Resource` (has `id`, `name`, `type`, `location`, `tags`). Use `GroupableResource` as the base.
   - **Proxy resource / child resource**: Nested under a parent resource. Use `IndependentChild` or `ExternalChildResource` as appropriate.
   - **Read-only resource**: No create/update operations. Only needs read-only interface.

## Look at an existing convenience resource for reference

Find a similar existing resource in the same project (or another mgmt project like `azure-resourcemanager-compute`) to use as a pattern. The reference resource should have a similar resource type (groupable, child, read-only).

## Create the resource interface

Create `<src-base>/models/<ResourceName>.java`:

```java
package com.azure.resourcemanager.<service>.models;

import com.azure.core.annotation.Fluent;
import com.azure.resourcemanager.<service>.<Service>Manager;
import com.azure.resourcemanager.<service>.fluent.models.<ResourceName>Inner;
import com.azure.resourcemanager.resources.fluentcore.arm.models.GroupableResource;
import com.azure.resourcemanager.resources.fluentcore.model.Appliable;
import com.azure.resourcemanager.resources.fluentcore.model.Creatable;
import com.azure.resourcemanager.resources.fluentcore.model.Refreshable;
import com.azure.resourcemanager.resources.fluentcore.model.Updatable;

/** An immutable client-side representation of an Azure <ResourceName>. */
@Fluent
public interface <ResourceName>
    extends GroupableResource<<Service>Manager, <ResourceName>Inner>,
        Refreshable<<ResourceName>>,
        Updatable<<ResourceName>.Update> {

    // Property getters - one for each meaningful property from the inner model
    // ...

    /** Container interface for all the definitions related to <ResourceName>. */
    interface Definition
        extends DefinitionStages.Blank, DefinitionStages.WithGroup, DefinitionStages.WithCreate {
    }

    /** Grouping of <ResourceName> definition stages. */
    interface DefinitionStages {
        /** The first stage of a <ResourceName> definition. */
        interface Blank extends GroupableResource.DefinitionWithRegion<WithGroup> {
        }

        /** The stage allowing to specify the resource group. */
        interface WithGroup extends GroupableResource.DefinitionStages.WithGroup<WithCreate> {
        }

        // Add With<Property> stages for optional properties...

        /** The stage of the definition which contains all the minimum required inputs to create the resource. */
        interface WithCreate extends Creatable<<ResourceName>> /*, With<Property>, ... */ {
        }
    }

    /** Grouping of <ResourceName> update stages. */
    interface UpdateStages {
        // Add With<Property> stages for updatable properties...
    }

    /** The template for a <ResourceName> update operation. */
    interface Update extends Appliable<<ResourceName>> /*, UpdateStages.With<Property>, ... */ {
    }
}
```

Adapt the base interfaces based on the resource type identified earlier. Remove `Updatable` and `Update` if the resource is read-only.

## Create the resource implementation

Create `<src-base>/implementation/<ResourceName>Impl.java`:

```java
package com.azure.resourcemanager.<service>.implementation;

import com.azure.resourcemanager.<service>.<Service>Manager;
import com.azure.resourcemanager.<service>.fluent.models.<ResourceName>Inner;
import com.azure.resourcemanager.<service>.models.<ResourceName>;
import com.azure.resourcemanager.resources.fluentcore.arm.models.implementation.GroupableResourceImpl;
import reactor.core.publisher.Mono;

/** Implementation for <ResourceName> and its create and update interfaces. */
class <ResourceName>Impl
    extends GroupableResourceImpl<<ResourceName>, <ResourceName>Inner, <ResourceName>Impl, <Service>Manager>
    implements <ResourceName>, <ResourceName>.Definition, <ResourceName>.Update {

    <ResourceName>Impl(String name, <ResourceName>Inner innerModel, <Service>Manager manager) {
        super(name, innerModel, manager);
    }

    // Property getter implementations delegating to innerModel()
    // ...

    @Override
    protected Mono<<ResourceName>Inner> getInnerAsync() {
        return this.manager().serviceClient().get<ResourceName>s()
            .getByResourceGroupAsync(this.resourceGroupName(), this.name());
    }

    @Override
    public Mono<<ResourceName>> createResourceAsync() {
        return this.manager().serviceClient().get<ResourceName>s()
            .createOrUpdateAsync(this.resourceGroupName(), this.name(), this.innerModel())
            .map(inner -> {
                this.setInner(inner);
                return this;
            });
    }

    // Setter implementations for Definition/Update stages
    // ...
}
```

## Create the collection interface

Create `<src-base>/models/<ResourceName>s.java`:

```java
package com.azure.resourcemanager.<service>.models;

import com.azure.core.annotation.Fluent;
import com.azure.resourcemanager.<service>.<Service>Manager;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.SupportsDeletingByResourceGroup;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.SupportsGettingById;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.SupportsGettingByResourceGroup;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.SupportsListingByResourceGroup;
import com.azure.resourcemanager.resources.fluentcore.arm.models.HasManager;
import com.azure.resourcemanager.resources.fluentcore.collection.SupportsCreating;
import com.azure.resourcemanager.resources.fluentcore.collection.SupportsDeletingById;
import com.azure.resourcemanager.resources.fluentcore.collection.SupportsListing;

/** Entry point to <ResourceName> management API. */
@Fluent
public interface <ResourceName>s
    extends SupportsCreating<<ResourceName>.DefinitionStages.Blank>,
        SupportsListing<<ResourceName>>,
        SupportsListingByResourceGroup<<ResourceName>>,
        SupportsGettingByResourceGroup<<ResourceName>>,
        SupportsGettingById<<ResourceName>>,
        SupportsDeletingById,
        SupportsDeletingByResourceGroup,
        HasManager<<Service>Manager> {
}
```

Only include the `Supports*` interfaces that correspond to operations available in the inner client.

## Create the collection implementation

Create `<src-base>/implementation/<ResourceName>sImpl.java`:

```java
package com.azure.resourcemanager.<service>.implementation;

import com.azure.resourcemanager.<service>.<Service>Manager;
import com.azure.resourcemanager.<service>.fluent.<ResourceName>sClient;
import com.azure.resourcemanager.<service>.fluent.models.<ResourceName>Inner;
import com.azure.resourcemanager.<service>.models.<ResourceName>;
import com.azure.resourcemanager.<service>.models.<ResourceName>s;
import com.azure.resourcemanager.resources.fluentcore.arm.collection.implementation.GroupableResourcesImpl;
import reactor.core.publisher.Mono;

/** The implementation for <ResourceName>s. */
public class <ResourceName>sImpl
    extends GroupableResourcesImpl<<ResourceName>, <ResourceName>Impl, <ResourceName>Inner, <ResourceName>sClient, <Service>Manager>
    implements <ResourceName>s {

    public <ResourceName>sImpl(<Service>Manager manager) {
        super(manager.serviceClient().get<ResourceName>s(), manager);
    }

    @Override
    protected <ResourceName>Impl wrapModel(String name) {
        return new <ResourceName>Impl(name, new <ResourceName>Inner(), this.manager());
    }

    @Override
    protected <ResourceName>Impl wrapModel(<ResourceName>Inner inner) {
        if (inner == null) {
            return null;
        }
        return new <ResourceName>Impl(inner.name(), inner, this.manager());
    }

    @Override
    public <ResourceName>Impl define(String name) {
        return wrapModel(name);
    }

    @Override
    protected Mono<<ResourceName>Inner> getInnerAsync(String resourceGroupName, String name) {
        return this.inner().getByResourceGroupAsync(resourceGroupName, name);
    }

    @Override
    protected Mono<Void> deleteInnerAsync(String resourceGroupName, String name) {
        return this.inner().deleteAsync(resourceGroupName, name);
    }
}
```

## Wire up in the Manager

Edit `<src-base>/<Service>Manager.java`:

1. Add a private field for the collection:
   ```java
   private <ResourceName>s <resourceName>s;
   ```

2. Add a public accessor method:
   ```java
   /** @return the <ResourceName> resource management API entry point */
   public <ResourceName>s <resourceName>s() {
       if (this.<resourceName>s == null) {
           this.<resourceName>s = new <ResourceName>sImpl(this);
       }
       return this.<resourceName>s;
   }
   ```

3. Add the necessary imports.

## Generate tests

After wiring up the convenience layer, generate tests to verify the implementation. See [generate-tests.md](./generate-tests.md) for detailed instructions.

At minimum, create tests that cover:
1. Creating the resource via the convenience layer
2. Getting/listing the resource
3. Updating the resource (if the resource supports updates)
4. Deleting the resource (if the resource supports deletion)
