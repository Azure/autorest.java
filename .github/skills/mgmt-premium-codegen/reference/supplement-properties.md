# Scenario: Supplement existing resource's missing properties

Use this when a convenience layer already exists for `<resource-name>`, but some properties from the inner model are not exposed.

> **Rule:** Do NOT supplement properties whose type is a deprecated class (annotated with `@Deprecated`). Skip those entirely.

> **Rule:** Do NOT supplement infrastructure/plumbing properties. Skip the following property types entirely — they are handled by separate mechanisms (base interfaces, dedicated APIs), not as convenience getters:
> - `provisioningState` — provisioning state of the resource
> - `privateEndpointConnections` — private endpoint connections list
> - `privateLinkResources` / `privateLinks` — private link resources

## Identify missing properties

1. Read the inner model class at `<src-base>/fluent/models/<ResourceName>Inner.java`.
   List all getter methods and their return types.
   Also list all setter methods (`withXxx(...)`) — these indicate which properties are **settable**.

2. Read the convenience interface at `<src-base>/models/<ResourceName>.java`.
   List all existing property getters.

3. Compare the two lists. The "missing properties" are getters present in the inner model but not exposed in the convenience interface.

4. **Categorize missing properties by settability:**
   - **Settable properties**: Properties that have a corresponding `withXxx()` setter in the inner model. These can be set during resource creation or update.
   - **Read-only properties**: Properties that only have a getter and no setter. These are server-populated and cannot be controlled by the user.

5. **Prioritize settable properties.** When presenting missing properties to the user:
   - List settable properties first, clearly marked as "✏️ settable"
   - List read-only properties second, clearly marked as "📖 read-only"
   - Recommend supplementing settable properties, as they:
     - Allow creating Definition/Update stages with meaningful `withXxx()` methods
     - Produce tests with real assertions (set value → assert persisted value)
   - Advise caution with read-only properties, as they:
     - Often return null or server-default values for newly created resources
     - Lead to blank `assertNotNull` assertions that don't validate anything meaningful
     - Should only be supplemented if the property has clear, predictable values (e.g. `type`, `location`)

6. If the user has already specified which properties to add, use those.
   If the user says "supplement all properties" or similar, still **prefer settable properties** and skip read-only properties that would produce empty/null values unless the user explicitly insists.

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

## Add property setters (Definition/Update stages)

**For every settable property being supplemented** (i.e., the inner model has a `withXxx()` setter), you SHOULD add Definition/Update stages — not just the getter. Exposing a settable property as read-only in the convenience layer defeats the purpose.

If the property has a setter in the inner model:

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

After supplementing properties, verify them with tests. **Settable properties MUST have set-then-assert tests; avoid blank `assertNotNull` for read-only properties.**

### If tests already exist for this resource

Find the existing test class (e.g. `<ResourceName>Tests.java`) in `<project-path>/src/test/java/com/azure/resourcemanager/<service>/`. Add assertions for the new properties in the existing CRUD test.

**For settable properties (preferred):** Test by setting a value during create/update and asserting the persisted value. This produces meaningful, non-trivial assertions:

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

**For read-only properties (use sparingly):** Only add assertions if the property has a predictable, meaningful value. Do NOT add blank `Assertions.assertNotNull(resource.<propertyName>())` if you cannot predict the value — such assertions add no test coverage and may fail on null server responses:

```java
// GOOD: read-only property with a known/predictable value
Assertions.assertEquals("Microsoft.Service/resourceType", resource.type());

// BAD: blank assertion with no meaningful validation — AVOID
Assertions.assertNotNull(resource.someServerPopulatedField());
```

### If no tests exist yet

Create a new test class following the instructions in [generate-tests.md](./generate-tests.md). Include assertions for the supplemented properties in the test methods. Focus on settable properties that can be tested with real values.

### Run the tests

Run the tests in record mode to verify the new properties work correctly. See [generate-tests.md](./generate-tests.md) for prerequisites and commands.
