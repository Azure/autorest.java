# Scenario: Generate tests for convenience layer

Use this after creating or supplementing a convenience layer to generate tests that exercise the new code. Tests run in **record mode** using Azure test proxy, following the same pattern as existing mgmt SDK tests.

## Understand the test infrastructure

Convenience layer tests extend `ResourceManagerTestProxyTestBase`. The test framework supports three modes:

- **RECORD**: Tests hit real Azure endpoints; HTTP traffic is recorded for future playback.
- **PLAYBACK**: Tests replay previously recorded HTTP traffic (no Azure calls).
- **LIVE**: Tests hit real Azure endpoints without recording.

The goal is to create tests that can be run in RECORD mode to capture recordings, then run in PLAYBACK mode in CI.

## Check for existing test infrastructure

1. Look in `<project-path>/src/test/java/com/azure/resourcemanager/<service>/` for existing test files.
2. If a test base class already exists (e.g. `<Service>ManagementTest.java` or `<Service>Test.java`), reuse it.
3. If no test infrastructure exists, create it (see below).

## Create the test base class (if needed)

Create `<project-path>/src/test/java/com/azure/resourcemanager/<service>/<Service>ManagementTest.java`:

```java
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.resourcemanager.<service>;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.management.Region;
import com.azure.core.management.profile.AzureProfile;
import com.azure.resourcemanager.resources.ResourceManager;
import com.azure.resourcemanager.resources.fluentcore.utils.HttpPipelineProvider;
import com.azure.resourcemanager.resources.fluentcore.utils.ResourceManagerUtils;
import com.azure.resourcemanager.test.ResourceManagerTestProxyTestBase;
import com.azure.resourcemanager.test.utils.TestDelayProvider;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class <Service>ManagementTest extends ResourceManagerTestProxyTestBase {
    protected ResourceManager resourceManager;
    protected <Service>Manager <service>Manager;
    protected String rgName = "";

    @Override
    protected HttpPipeline buildHttpPipeline(TokenCredential credential, AzureProfile profile,
        HttpLogOptions httpLogOptions, List<HttpPipelinePolicy> policies, HttpClient httpClient) {
        return HttpPipelineProvider.buildHttpPipeline(credential, profile, null, httpLogOptions, null,
            new RetryPolicy("Retry-After", ChronoUnit.SECONDS), policies, httpClient);
    }

    @Override
    protected void initializeClients(HttpPipeline httpPipeline, AzureProfile profile) {
        ResourceManagerUtils.InternalRuntimeContext.setDelayProvider(new TestDelayProvider(!isPlaybackMode()));
        rgName = generateRandomResourceName("rg", 15);
        <service>Manager = buildManager(<Service>Manager.class, httpPipeline, profile);
        resourceManager = <service>Manager.resourceManager();
    }

    @Override
    protected void cleanUpResources() {
        try {
            resourceManager.resourceGroups().beginDeleteByName(rgName);
        } catch (Exception e) {
            // ignore cleanup failures
        }
    }
}
```

## Create the test class

Create `<project-path>/src/test/java/com/azure/resourcemanager/<service>/<ResourceName>Tests.java`:

```java
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.resourcemanager.<service>;

import com.azure.core.management.Region;
import com.azure.resourcemanager.<service>.models.<ResourceName>;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class <ResourceName>Tests extends <Service>ManagementTest {

    @Test
    public void canCreate<ResourceName>() {
        String resourceName = generateRandomResourceName("<short-prefix>", 15);

        <ResourceName> resource = <service>Manager.<resourceName>s()
            .define(resourceName)
            .withRegion(Region.US_EAST)
            .withNewResourceGroup(rgName)
            // chain required .with<Property>(...) calls for mandatory properties
            .create();

        Assertions.assertNotNull(resource);
        Assertions.assertEquals(resourceName, resource.name());
        // Assert key properties from the convenience layer
    }

    @Test
    public void canGet<ResourceName>() {
        String resourceName = generateRandomResourceName("<short-prefix>", 15);

        <service>Manager.<resourceName>s()
            .define(resourceName)
            .withRegion(Region.US_EAST)
            .withNewResourceGroup(rgName)
            .create();

        <ResourceName> resource = <service>Manager.<resourceName>s()
            .getByResourceGroup(rgName, resourceName);

        Assertions.assertNotNull(resource);
        Assertions.assertEquals(resourceName, resource.name());
    }

    @Test
    public void canList<ResourceName>s() {
        String resourceName = generateRandomResourceName("<short-prefix>", 15);

        <service>Manager.<resourceName>s()
            .define(resourceName)
            .withRegion(Region.US_EAST)
            .withNewResourceGroup(rgName)
            .create();

        boolean found = <service>Manager.<resourceName>s()
            .listByResourceGroup(rgName)
            .stream()
            .anyMatch(r -> r.name().equals(resourceName));

        Assertions.assertTrue(found);
    }

    @Test
    public void canUpdate<ResourceName>() {
        String resourceName = generateRandomResourceName("<short-prefix>", 15);

        <ResourceName> resource = <service>Manager.<resourceName>s()
            .define(resourceName)
            .withRegion(Region.US_EAST)
            .withNewResourceGroup(rgName)
            .create();

        resource.update()
            .withTag("testKey", "testValue")
            // chain .with<Property>(...) calls for properties to update
            .apply();

        Assertions.assertEquals("testValue", resource.tags().get("testKey"));
    }

    @Test
    public void canDelete<ResourceName>() {
        String resourceName = generateRandomResourceName("<short-prefix>", 15);

        <ResourceName> resource = <service>Manager.<resourceName>s()
            .define(resourceName)
            .withRegion(Region.US_EAST)
            .withNewResourceGroup(rgName)
            .create();

        <service>Manager.<resourceName>s().deleteById(resource.id());

        // Verify the resource no longer exists
        Assertions.assertNull(
            <service>Manager.<resourceName>s()
                .getByResourceGroup(rgName, resourceName));
    }
}
```

### Adapting the test class

- **Read-only resources**: Omit `canCreate`, `canUpdate`, `canDelete` tests. Only test `canGet` and `canList`.
- **Child resources**: Adjust the create flow to use the parent resource (e.g. `parentResource.<childResource>s().define(...)`).
- **Resources without tags**: Replace the update test with a property-specific update.
- **Combined CRUD test**: For simpler resources, you may combine create/read/update/delete into a single `canCRUD<ResourceName>()` test method to reduce test execution time and resource consumption. Use the existing tests in the same project as a reference for which style to follow.

### Testing supplemented properties

When properties have been supplemented on an existing resource (Scenario 1), update existing tests or add new ones to cover the new properties. **Prioritize settable properties — they produce meaningful tests.**

1. **Settable properties (PREFERRED — always test these)**: Add the setter in the create/update flow and assert the persisted value. This is the gold standard for property tests:
   ```java
   // In create
   <ResourceName> resource = <service>Manager.<resourceName>s()
       .define(resourceName)
       .withRegion(Region.US_EAST)
       .withNewResourceGroup(rgName)
       .with<PropertyName>(testValue)
       .create();
   Assertions.assertEquals(testValue, resource.<propertyName>());

   // In update
   resource.update()
       .with<PropertyName>(updatedValue)
       .apply();
   Assertions.assertEquals(updatedValue, resource.<propertyName>());
   ```

2. **Read-only properties (use sparingly)**: Only assert if the value is predictable and meaningful. **Do NOT add blank `Assertions.assertNotNull()` checks** — they provide no real test coverage and may fail when the server returns null for newly created resources:
   ```java
   // GOOD: known value for a read-only property
   Assertions.assertEquals("Microsoft.Service/resourceType", resource.type());

   // BAD: blank assertion — AVOID
   Assertions.assertNotNull(resource.someServerPopulatedField());
   ```

3. **If no test class exists yet for the resource**: Create one following the test class template above, and include assertions for the supplemented properties. Focus on settable properties.

## Ensure test dependencies exist

Verify that `<project-path>/pom.xml` includes the following test dependencies. If any are missing, add them:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.13.4</version> <!-- {x-version-update;org.junit.jupiter:junit-jupiter-engine;external_dependency} -->
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.13.4</version> <!-- {x-version-update;org.junit.jupiter:junit-jupiter-api;external_dependency} -->
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>com.azure</groupId>
    <artifactId>azure-core-http-netty</artifactId>
    <version>1.16.3</version> <!-- {x-version-update;com.azure:azure-core-http-netty;dependency} -->
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>1.7.36</version> <!-- {x-version-update;org.slf4j:slf4j-simple;external_dependency} -->
    <scope>test</scope>
</dependency>
```

Also ensure the `azure-mgmt-sdk-test-jar` profile exists (it is in most mgmt SDKs already):

```xml
<profiles>
    <profile>
        <id>azure-mgmt-sdk-test-jar</id>
        <activation>
            <property>
                <name>!maven.test.skip</name>
            </property>
        </activation>
        <dependencies>
            <dependency>
                <groupId>com.azure.resourcemanager</groupId>
                <artifactId>azure-resourcemanager-test</artifactId>
                <version>2.0.0-beta.2</version> <!-- {x-version-update;com.azure.resourcemanager:azure-resourcemanager-test;dependency} -->
                <scope>test</scope>
            </dependency>
        </dependencies>
    </profile>
</profiles>
```

If `azure-resourcemanager-resources` is not already a dependency, add it as a test-scope dependency (it provides `ResourceManager`):

```xml
<dependency>
    <groupId>com.azure.resourcemanager</groupId>
    <artifactId>azure-resourcemanager-resources</artifactId>
    <version>2.51.0</version> <!-- {x-version-update;com.azure.resourcemanager:azure-resourcemanager-resources;dependency} -->
    <scope>test</scope>
</dependency>
```

## Create test resources configuration (if needed)

If the project does not already have `<project-path>/src/test/resources/junit-platform.properties`, create it:

```properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.config.strategy=fixed
junit.jupiter.execution.parallel.config.fixed.parallelism=32
```

## Run tests in record mode

### Prerequisites

1. Sign in to Azure CLI: `az login`
2. Set environment variables:
   - `AZURE_TENANT_ID` — your Azure AD tenant ID
   - `AZURE_SUBSCRIPTION_ID` — your Azure subscription ID
   - `AZURE_TEST_MODE=RECORD`

### Run the tests

```bash
mvn test -f <project-path>/pom.xml -pl . -Dtest=<ResourceName>Tests
```

For example:
```bash
mvn test -f sdk/containerservice/azure-resourcemanager-containerservice/pom.xml -pl . -Dtest=KubernetesClustersTests
```

### After recording

After tests pass in RECORD mode, test recordings are saved locally. Push them to the assets repository:

```bash
# From the sdk repo root
test-proxy push -a <project-path>/assets.json
```

If `assets.json` does not yet exist for the project, create it:

```json
{
  "AssetsRepo": "Azure/azure-sdk-assets",
  "AssetsRepoPrefixPath": "java",
  "TagPrefix": "java/<service-folder>/<project-name>",
  "Tag": ""
}
```

The `Tag` field will be populated automatically after the first `test-proxy push`.

## Notes

- Always model your tests after existing tests in the same project. If the project has a `<Service>ManagementTest` base class, extend it rather than creating a new one.
- For groupable resources, the test base class typically creates a resource group in `initializeClients()` and deletes it in `cleanUpResources()`.
- Use `generateRandomResourceName(prefix, maxLength)` for all Azure resource names to ensure uniqueness across record/playback.
- Use `isPlaybackMode()` to skip assertions or logic that only make sense against live Azure (e.g. comparing regenerated keys).
- Keep tests focused: test the convenience layer APIs (not the underlying generated client).
- If the resource takes a long time to create, consider combining CRUD operations into a single test method to reduce total test time.
