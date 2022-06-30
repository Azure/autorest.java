# <img align="center" src="../images/logo.png">  Generating a Data Plane Client

The Java generator offers the ability to generate [data-plane][data-plane] client libraries.

Data-plane client libraries look like [this][data-plane-client].
It can be enabled with the flag `--data-plane`.

There is additional options that can enhance the generated client library.

### Service Name and Service Versions

The option `service-name` specifies the name of the service.
If not specified, it is deduced from `title` in the OpenAPI specs, or the `title` option of AutoRest.

The option `service-versions` as an array of `api-version`s specify the list of compatible [service API versions][service-api-version] with this client.
If not specified, it uses the single `api-version` in the OpenAPI specs.

As an example, following `service-name` and `service-versions`

```
service-name: PurviewScanning
service-version:
- 2018-12-01-preview
- 2021-10-01-preview
```

results in this `PurviewScanningServiceVersion` class

```java
/** Service version of PurviewScanning. */
public enum PurviewScanningServiceVersion implements ServiceVersion {
    /** Enum value 2018-12-01-preview. */
    V2018_12_01_PREVIEW("2018-12-01-preview"),
    /** Enum value 2021-10-01-preview. */
    V2021_10_01_PREVIEW("2021-10-01-preview");

    /**
     * Gets the latest service version supported by this client library.
     *
     * @return The latest {@link PurviewScanningServiceVersion}.
     */
    public static PurviewScanningServiceVersion getLatest() {
        return V2021_10_01_PREVIEW;
    }
}
```

### Long-running Operation

By default, generated client library uses the `DefaultPollingStrategy` for polling of long-running operation.

This default polling behavior can be configured via [polling configuration][polling-configuration].

### Samples

The flag `--generate-samples` generates sample classes in `src/samples/java/<namespace>/generated`, which can be selectively modified and moved to `src/samples/java/<namespace>` as samples in README or in [Javadoc][code-snippet].
Here is an [example in Purview][sample-examples].

### Tests

The flag `--generate-tests` generates test classes in `src/tests/java/<namespace>/generated`, which can be selectively modified and moved to `src/tests/java/<namespace>` as live tests or recorded tests.

### POM

The flag `--regenerate-pom` generates the Maven POM for the library.

Note that it replaces the original `pom.xml`, if exists.

Generally, this flag is implied when generating the client library first time with `--sdk-integration` flag in order to integrate the project to [Azure SDK for Java repository][azure-sdk-for-java].

[data-plane]: https://docs.microsoft.com/azure/azure-resource-manager/management/control-plane-and-data-plane#data-plane
[data-plane-client]: https://github.com/Azure/azure-sdk-for-java/wiki/Protocol-Methods
[sample-examples]: https://github.com/Azure/azure-sdk-for-java/tree/main/sdk/purview/azure-analytics-purview-catalog/src/samples/java/com/azure/analytics/purview/catalog/generated
[code-snippet]: https://github.com/Azure/azure-sdk-tools/blob/main/packages/java-packages/codesnippet-maven-plugin/README.md
[azure-sdk-for-java]: https://github.com/Azure/azure-sdk-for-java
[service-api-version]: https://azure.github.io/azure-sdk/general_design.html#service-api-versions
[polling-configuration]: ../../readme.md#polling-configuration
