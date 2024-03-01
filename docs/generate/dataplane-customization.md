# Customization on Data-Plane Client

## Generate Data-Plane Client

Follow [Quickstart](https://aka.ms/azsdk/dpcodegen/java) to generate the Data-Plane Client from OpenAPI specs.

## Change Client Behavior

### Support Azure Active Directory Authentication

See [Authentication in SDK](https://github.com/Azure/autorest/blob/main/docs/generate/authentication.md).

It is advised to update OpenAPI specs with `security: AADToken`. And then re-generate the SDK.

If one does not wish to change the specs, add following configure to README_SPEC.md, and re-generate the SDK.
```
security: AADToken
security-scopes: https://cognitiveservices.azure.com/.default
```
Here is an [example](https://github.com/weidongxu-microsoft/azure-sdk-for-java/commit/d30773a776f36e8269c7b2377e1d948fad2f5b82) of the modification of README_SPEC.md and the resulted code changes.
Note that a new method `credential(TokenCredential tokenCredential)` is generated in builder class.

### Support Customized Authentication

Usually `security: AzureKey` can provide implementation of API key authentication.

For more complex API key authentication, one can customize the builder class.

Here is an [example](https://github.com/weidongxu-microsoft/azure-sdk-for-java/commit/f5337b6fbc5937683e01299a509b505e01d8b7ce) of the modification of builder to support subscription key and API key.
1. Create `MetricsAdvisorKeys` and `MetricsAdvisorKeyCredential` class in `models` package.
2. Modify `MetricsAdvisorClientBuilder` to accept `MetricsAdvisorKeyCredential` from user, and then pass the keys in it to HTTP pipeline.

Note that the `Generated` annotation on `createHttpPipeline()` is removed, as we customized this method.
This ensures that future code generate will not override this customized method.

## Change Operation Behavior

Generated client methods accept `RequestOptions` for request parameters.
For frequently invoked methods, one can customize them for better user-experience.

Here is an [example](https://github.com/weidongxu-microsoft/azure-sdk-for-java/commit/c3e8ebc1bf13dd29d67da0b68d23a84197ce681e) of the modification of client to support options for client methods.

1. Create `ListDataFeedFilter` and `ListDataFeedOptions` class in `models` package.
2. Modify `MetricsAdvisorAsyncClient` and `MetricsAdvisorClient` to provide overloaded `listDataFeeds` method that takes `ListDataFeedOptions` options.
3. Convert options from `ListDataFeedOptions` to `RequestOptions` in `listDataFeeds` method.

Note that the conversion only happen once in package private `MetricsAdvisorAsyncClient.listDataFeeds(ListDataFeedOptions, Context)` method, which is invoked by all added `listDataFeeds` methods.

## Add Model

Generated client methods accept and produce `BinaryData` for request payload and response body.
For frequently invoked methods, one can customize them for better user-experience.

### Model as Response Body

Here is an [example](https://github.com/weidongxu-microsoft/azure-sdk-for-java/commit/bb9de3dd39e6327998fdc61c55c870f3b9b850bd) of the modification of client to support `DataFeedDetail` as response of paged operation.

1. Create `DataFeedDetail` class and all related classed in `models` package.
2. Modify `MetricsAdvisorAsyncClient` and `MetricsAdvisorClient` to provide overloaded `listDataFeeds` method that produce paged response of `DataFeedDetail`. (as these are already customized method, in example we only modify them in place)
3. `BinaryData.toObject(Class<T>)` convert `BinaryData` to `DataFeedDetail`.
4. Utility method `mapPage(PagedFlux<T> pagedFlux, Function<T, S> mapper)` is provided to convert `PagedFlux`.
5. Convert response of `PagedFlux<BinaryData>` to `PagedFlux<DataFeedDetail>` in `listDataFeeds` method.

Note that the conversion only happen once in package private `MetricsAdvisorAsyncClient.listDataFeeds(ListDataFeedOptions, Context)` method, which is invoked by all added `listDataFeeds` methods.

### Model as Request Payload

Here is an [example](https://github.com/weidongxu-microsoft/azure-sdk-for-java/commit/91c4c5409fc5173192aba58b11485576643b9c7a) of the modification of client to support `DataFeedDetail` as request payload.

1. Create `DataFeedDetail` class and all related classed in `models` package.
2. Modify `MetricsAdvisorAsyncClient` and `MetricsAdvisorClient` to provide overloaded `createDataFeed` method that accept `DataFeedDetail`.
3. `BinaryData.fromObject(Class<T>)` convert `DataFeedDetail` to `BinaryData`.

Note that the conversion only happen once in package private `createDataFeed(DataFeedDetail dataFeed, Context context)` method, which is invoked by all added `createDataFeed` methods.

In this special scenario, service only return a "Location" header of the resource URL of the created data feed.
For better user-experience, SDK send another GET request to retrieve the detail of the created data feed, following the resource URL.

## Java Code Considerations

### models Package

When one create `models` package to house the model classes, one need to consider:
1. Add a `package-info.java` in `models` package.
2. Modify `module-info.java` to expose it to user, and optionally open it to JSON serialization. See [Java Modules](https://www.oracle.com/corporate/features/understanding-java-9-modules.html).

A typical section in `module-info.java`:
```java
exports com.azure.ai.metricsadvisor.models;

opens com.azure.ai.metricsadvisor.models to
    com.azure.core,
    com.fasterxml.jackson.databind;
```
