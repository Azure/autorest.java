# Scenario: Supplement existing resource's missing properties

Use this when a convenience layer already exists for `<resource-name>`, but some properties from the inner model are not exposed.

## Identify missing properties

1. Read the inner model class at `<src-base>/fluent/models/<ResourceName>Inner.java`.
   List all getter methods and their return types.

2. Read the convenience interface at `<src-base>/models/<ResourceName>.java`.
   List all existing property getters.

3. Compare the two lists. The "missing properties" are getters present in the inner model but not exposed in the convenience interface.

4. Present the missing properties to the user and ask which ones to add.
   If the user has already specified which properties, use those.

## Add property getters to the convenience interface

Edit `<src-base>/models/<ResourceName>.java`:

1. For each missing property, add a getter method to the interface. Follow the existing style in the file. The pattern is:
   ```java
   /**
    * Gets the <propertyName> property: <description from inner model>.
    *
    * @return the <propertyName> value
    */
   <ReturnType> <propertyName>();
   ```

2. If the property type is an inner model type (from `fluent.models` package), decide whether to expose it directly or wrap it. Simple value types and types already in the `models` package can be exposed directly.

## Add property implementations

Edit `<src-base>/implementation/<ResourceName>Impl.java`:

1. For each new property getter, add the implementation. The typical pattern delegates to the inner model:
   ```java
   @Override
   public <ReturnType> <propertyName>() {
       return this.innerModel().<propertyName>();
   }
   ```

2. If the return type is a collection, wrap it with `Collections.unmodifiableList(...)` or `Collections.unmodifiableSet(...)` as appropriate, and handle null safely.

3. If the return type is an inner model type that has a convenience wrapper, convert it using the wrapper.

## Add property setters (Definition/Update stages) if needed

If the user requests that the property be settable during create (Definition) or update (Update):

1. Add a new stage interface in the `<ResourceName>.DefinitionStages`, `<ResourceName>.UpdateStages`, or `<ResourceName>.UpdateDefinitionStages(for child resources) nested interfaces inside `<src-base>/models/<ResourceName>.java`:
   ```java
   interface With<PropertyName> {
       /**
        * Specifies the <propertyName> property: <description>.
        *
        * @param <propertyName> <description>
        * @return the next stage of the definition/update
        */
       <NextStage> with<PropertyName>(<ParamType> <propertyName>);
   }
   ```

2. Add the stage to the appropriate composite interface (`WithCreate` for Definition, `Update` for Update).

3. Implement the setter in `<ResourceName>Impl.java`:
   ```java
   @Override
   public <ResourceName>Impl with<PropertyName>(<ParamType> <propertyName>) {
       this.innerModel().with<PropertyName>(<propertyName>);
       return this;
   }
   ```

## Add necessary imports

Ensure all new imports are added to both the interface and implementation files.

## Add or update tests for the new properties

After supplementing properties, verify them with tests. There are two approaches depending on whether tests already exist for the resource:

### If tests already exist for this resource

Find the existing test class (e.g. `<ResourceName>Tests.java`) in `<project-path>/src/test/java/com/azure/resourcemanager/<service>/`. Add assertions for the new properties in the existing CRUD test:

```java
// After creating or getting the resource, assert the new properties
Assertions.assertNotNull(resource.<propertyName>());
// For properties with known expected values:
Assertions.assertEquals(expectedValue, resource.<propertyName>());
```

If the property is settable (Definition/Update stage was added), also test setting it:

```java
// In the create flow, chain the new setter
<ResourceName> resource = <service>Manager.<resourceName>s()
    .define(resourceName)
    .withRegion(Region.US_EAST)
    .withNewResourceGroup(rgName)
    .with<PropertyName>(testValue)
    .create();

Assertions.assertEquals(testValue, resource.<propertyName>());

// In the update flow, if UpdateStages was added
resource.update()
    .with<PropertyName>(updatedValue)
    .apply();

Assertions.assertEquals(updatedValue, resource.<propertyName>());
```

### If no tests exist yet

Create a new test class following the instructions in [generate-tests.md](./generate-tests.md). Include assertions for the supplemented properties in the test methods.

### Run the tests

Run the tests in record mode to verify the new properties work correctly. See [generate-tests.md](./generate-tests.md) for prerequisites and commands.
