# DPG Java Arch Board Review

## Goals of DPG clients

1. Version resiliency
2. Clean grow-up story
    * DPG methods and handwritten methods co-exist in the same artifact
    * Support adding handwritten methods to a subset of REST operations

## Key changes in DPG client

1. No models for DPG clients
2. Only "WithResponse" client methods generated
3. Glass breaker `sendRequest` method
4. JavaDocs for DPG client methods include REST API details

## DPG vs Legacy codegen - User experience comparison

DPG: `data-plane: true`

Legacy: `data-plane: false`

### Creating a client

There's no difference in either the generated code or user experience when creating a client for a DPG client or a legacy client.

```java
PhoneNumberClient client = new PhoneNumberClientBuilder()
                  .credential(tokenCredential)
                  .buildClient();
```
### GET request

**Legacy generator**

```java
@Generated
@ServiceMethod(returns = ReturnType.SINGLE)
public Response<PurchasedPhoneNumber> getByNumberWithResponse(String phoneNumber, Context context)
```

User Experience

```java
PurchasedPhoneNumber purchasedPhoneNumber = client.getByNumberWithResponse("1112223333", Context.NONE).getValue();
String phoneNumber = purchasedPhoneNumber.getPhoneNumber();
OffsetDateTime purchaseDate = purchasedPhoneNumber.getPurchaseDate();
```

**DPG generator**

```java
@Generated
@ServiceMethod(returns = ReturnType.SINGLE)
public Response<BinaryData> getByNumberWithResponse(String phoneNumber, RequestOptions requestOptions)
```
User Experience

```java
ObjectMapper objectMapper = new ObjectMapper();
BinaryData responseBody = client.getByNumberWithResponse("1112223333", null).getValue();
JsonNode responseBodyJson = objectMapper.readTree(responseBody.toBytes());
String phoneNumber = responseBodyJson.get("phoneNumber").asText();
OffsetDateTime purchaseDate = OffsetDateTime.parse(responseBodyJson.get("purchasedDate").asText());
```

### POST request

**Legacy generator**
```java
@Generated
@ServiceMethod(returns = ReturnType.SINGLE)
public Response<Group> createOrUpdateGroupWithResponse(String groupId, Group group)
```

User experience

```java
Group groupToCreate = new Group().setGroupType(GroupType.IOT_HUB_TAG);
Group group = client.createOrUpdateGroupWithResponse("a-group", groupToCreate).getValue();
Integer deviceCount = group.getDeviceCount();
```

**DPG generator**

```java
@Generated
@ServiceMethod(returns = ReturnType.SINGLE)
public Response<BinaryData> createOrUpdateGroupWithResponse(String groupId, BinaryData group, RequestOptions requestOptions)
```

User experience
```java
ObjectNode groupNode = JsonNodeFactory.instance.objectNode();
// set the properties of group
groupNode.put("groupType","IoTHubTag");
// set other properties...
BinaryData body = BinaryData.fromObject(groupNode);

BinaryData responseBody = client.createOrUpdateGroupWithResponse("a-group", body, null).getValue(); 
JsonNode responseBodyJson = objectMapper.readTree(responseBody.toBytes());
Integer deviceCount = responseBodyJson.get("deviceCount").asInt();
```

### Paging

**Legacy generator**

```java
@Generated
@ServiceMethod(returns = ReturnType.COLLECTION)
public PagedIterable<PurchasedPhoneNumber> listPhoneNumbers(Integer skip, Integer top, Context context)
```
User experience

```java
PagedIterable<PurchasedPhoneNumber> purchasedPhoneNumbers = client.listPhoneNumbers(null, null, Context.NONE);
purchasedPhoneNumbers.forEach(purchasedPhoneNumber -> {
            String phoneNumber = purchasedPhoneNumber.getPhoneNumber();
            OffsetDateTime purchasedDate = purchasedPhoneNumber.getPurchaseDate();
        });
```

**DPG generator**

```java
@Generated
@ServiceMethod(returns = ReturnType.COLLECTION)
public PagedIterable<BinaryData> listPhoneNumbers(RequestOptions requestOptions)
```

User experience

```java
ObjectMapper objectMapper = new ObjectMapper();
PagedIterable<BinaryData> purchasedPhoneNumbers = client.listPhoneNumbers(null);
purchasedPhoneNumbers.forEach(purchasedPhoneNumber -> {
    JsonNode responseBodyJson = objectMapper.readTree(purchasedPhoneNumber.toBytes());
    String phoneNumber = responseBodyJson.get("phoneNumber").asText();
    OffsetDateTime purchaseDate = OffsetDateTime.parse(responseBodyJson.get("purchasedDate").asText());
});
```

### LRO operations

**Legacy generator**
```java
@Generated
@ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
public SyncPoller<PurchasedPhoneNumber, PurchasedPhoneNumber> beginUpdateCapabilities(
        String phoneNumber, PhoneNumberCapabilitiesRequest body)
```

**DPG generator**

```java
@Generated
@ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
public SyncPoller<BinaryData, BinaryData> beginUpdateCapabilities(
        String phoneNumber, RequestOptions requestOptions)
```

### Requests with multiple media types

**Legacy generator**

```java
@Generated
@ServiceMethod(returns = ReturnType.SINGLE)
public Response<String> binaryBodyWithTwoContentTypesWithResponse(ContentType contentType, BinaryData message)
```

User Experience
```java
String response = client.binaryBodyWithTwoContentTypesWithResponse(ContentType.OCTET_STREAM, BinaryData.fromBytes(bytes));
```

**DPG generator**
```java
@Generated
@ServiceMethod(returns = ReturnType.SINGLE)
public Response<BinaryData> binaryBodyWithTwoContentTypesWithResponse(BinaryData message, RequestOptions requestOptions)
```

User Experience
```java
RequestOptions requestOptions = new RequestOptions()
        .addHeader("Content-Type", "application/octet-stream");

BinaryData responseBody = client.binaryBodyWithTwoContentTypesWithResponse(requestOptions).getValue();
String response = responseBody.toString();
```

### Glass breaker method

**DPG generator**
```java
@Generated
@ServiceMethod(returns = ReturnType.SINGLE)
public Response<BinaryData> sendRequest(HttpRequest httpRequest, Context context)
```

User experience
```java
BinaryData requestBody = BinaryData.fromObject(user);
HttpHeaders httpHeaders = new HttpHeaders().add("header-key", "header-value");
HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, new URL("https://example.com/user"), httpHeaders,
        requestBody);
BinaryData responseBody = this.sendRequest(httpRequest, Context.NONE).getValue();
```


## Grow-up Story Experiment on Metrics Advisor

### Overview of the experiment

Metrics Advisor is a released package. We use this [released code](https://apiview.dev/Assemblies/Review/8567acee5f8a44cab9ac67b09c2820a8) as our target grow up code.

This experiment introduces how to grow up from pure DPG code to the target code.

### Configuration for generating DPG code

Please refer
to [`swagger/README.md`](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/main/swagger/README.md) for the detailed configuration.

```yaml
input-file: https://github.com/Azure/azure-rest-api-specs/blob/f9a5bf06925934b7841bdc95c14e9b70379b426b/specification/cognitiveservices/data-plane/MetricsAdvisor/stable/v1.0/MetricsAdvisor.json
output-folder: ../
java: true
partial-update: true
data-plane: true
security: AADToken
security-scopes: https://cognitiveservices.azure.com/.default
generate-samples: true
generate-tests: true
```

Highlight a few codegen properties:

* **data-plane**: this enables data-plane codegen
* **partial-update**: with this flag, user manually added/updated code will not be deleted in next codegen.
* **generate-samples**: this enables sample generation
* **generate-tests**: this enables test generation


### Data-plane generated code

DPG generated code: [API View](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?revisionId=6919fc2319694d039069cd9e777e9d8b&doc=False
), [Java code](https://github.com/haolingdong-msft/metrics-advisor-poc/commit/6b602c187bba2dec122da35837cbc4aedf051858)

We also generate JavaDoc for each method to let user know what does the payload look like.

We also generate [tests and samples](https://github.com/haolingdong-msft/metrics-advisor-poc/tree/main/src) automatically.

### Customized authentication

DPG supports AzureAD OAuth2 authentication and API key authentication.
Refer [here](https://github.com/Azure/autorest/blob/main/docs/generate/authentication.md) for more detail.

Since MetricsAdvisor needs to have two keys in the header to do authentication, I added a method that accepts MetricsAdvisorKeyCredential. Then modify createHttpPipeline() to handle MetricsAdvisorKeyCredential, putting keys to header.

Customized authentication code: [API View](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=603cb6f7e7ad41499b6768cb2044c791&doc=False&diffOnly=False&revisionId=dff270dd1f75419b86e43eff6f2dcac1#com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClientBuilder), [Java code](https://github.com/haolingdong-msft/metrics-advisor-poc/commit/79dbb93fb8ec395a6f79ea5971d6b4b2d090c765)

Usage:
```java
MetricsAdvisorAdministrationClientBuilder builder = new MetricsAdvisorAdministrationClientBuilder()
            .endpoint(getEndpoint());
builder.credential(new MetricsAdvisorKeyCredential("subscription_key", "api_key"));
```

### Add convenient methods

We add a set of APIs to DPG code. Those methods are convenient to users, e.g. it can accept customized model as input value or return value.
This is an overview of selected APIs to add.


#### Convenient APIs overview

| API  | Method |  Return Type        | API View | Code Reference |
|------|--------|----------|----------------|--------------|
|getDataFeed|GET| Single Value  |[API](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=dff270dd1f75419b86e43eff6f2dcac1&doc=False&diffOnly=False&revisionId=11c5d413369e4f099e6e8db7157bebd5)|[code](https://github.com/haolingdong-msft/metrics-advisor-poc/commit/b86c9353a90c3cbf5709cb3c982b2ff175dc59d9)|
|createDataFeed|POST| Single Value  |[API](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=11c5d413369e4f099e6e8db7157bebd5&doc=False&diffOnly=False&revisionId=6a47cc424fd842539135795a8f6c9863)|[code](https://github.com/haolingdong-msft/metrics-advisor-poc/commit/acb4235e8c2f62bf497130cce7fc147818551565)|
|listDataFeed|GET| Pageble Object    |[API](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=6a47cc424fd842539135795a8f6c9863&doc=False&diffOnly=False&revisionId=7384d93fddd84ffd86645da221a54392)|[code](https://github.com/haolingdong-msft/metrics-advisor-poc/commit/effbe61a64dde133d47779b4787f17d91809e495)|
|deleteDataFeed|DELETE| Void    |[API](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=7384d93fddd84ffd86645da221a54392&doc=False&diffOnly=False&revisionId=3c03646e40b0412f90de084648710e2c)|[code](https://github.com/haolingdong-msft/metrics-advisor-poc/commit/effbe61a64dde133d47779b4787f17d91809e495)|


#### Comparison between calling DPG method and convenient method

* getDataFeed

  [API](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=dff270dd1f75419b86e43eff6f2dcac1&doc=False&diffOnly=False&revisionId=11c5d413369e4f099e6e8db7157bebd5)

<table>
<tr>
<td> DPG </td>
</tr>
<tr>
<td>


  ```java
  RequestOptions requestOptions = new RequestOptions();
  Response<BinaryData> response =
          metricsAdvisorAdministrationClient.getDataFeedByIdWithResponse(
                  "01234567-8901-2345-6789-012345678901", requestOptions);
  BinaryData data = response.getValue();

  // get datafeed name
  JsonNode dataFeed = MAPPER.readTree(data.toBytes());
  String name = dataFeed.get("dataFeedName").asText();
  ```

</td>
</tr>
<tr>
<td> Convenient Method </td>
</tr>
<td>

  ```java
  Response<DataFeed> response =
          metricsAdvisorAdministrationClient.getDataFeedWithResponse(
                  "01234567-8901-2345-6789-012345678901");
  DataFeed dataFeed = response.getValue();

  // get datafeed name
  String name = dataFeed.getName();
  ```
</td>
</tr>
</table>


* createDataFeed

  [API](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=11c5d413369e4f099e6e8db7157bebd5&doc=False&diffOnly=False&revisionId=6a47cc424fd842539135795a8f6c9863)


<table>
<tr>
<td> DPG </td>
</tr>
<tr>
<td>

  ```java
  ObjectNode dataFeedObjectNode = JsonNodeFactory.instance.objectNode();
  // set the properties of data feed
  dataFeedObjectNode.put("dataFeedName","sampleDataFeed.");
  // set other properties...
  BinaryData body = BinaryData.fromObject(dataFeedObjectNode);

  RequestOptions requestOptions = new RequestOptions();
  Response<Void> response = metricsAdvisorAdministrationClient.createDataFeedWithResponse(body, requestOptions); 

  // get data feed ID from "Location" header, then calls getDataFeedByIdWithResponse() to get the DataFeed BinaryData object
  // get name from DataFeed BinaryData object
  ```
</td>
</tr>
<tr>
<td> Convenient Method </td>
</tr>
<td>

  ```java
  DataFeed dataFeed = new DataFeed();
  // set the properties of the dataFeed
  dataFeed.setName("testDataFeed");
  // set other properties...
  
  Response<DataFeed> response = metricsAdvisorAdministrationClient.createDataFeedWithResponse(dataFeed);

  // get data feed name
  DataFeed dataFeed = response.getValue();
  String name = dataFeed.getName();
  ```
</td>
</tr>
</table>

* listDataFeeds

  [API](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=6a47cc424fd842539135795a8f6c9863&doc=False&diffOnly=False&revisionId=7384d93fddd84ffd86645da221a54392)

<table>
<tr>
<td> DPG </td>
</tr>
<tr>
<td>

```java
  RequestOptions requestOptions = new RequestOptions();
  requestOptions.addQueryParam("creator", "demo@microsoft.com");
  requestOptions.addQueryParam("dataFeedName", "name_prefix");

  PagedIterable<BinaryData> response = metricsAdvisorAdministrationClient.listDataFeeds(requestOptions);

  // get datafeed name
  BinaryData data = response.iterator().next();
  JsonNode dataFeed = MAPPER.readTree(data.toBytes());
  String name = dataFeed.get("dataFeedName").asText();
  ```
</td>
</tr>
<tr>
<td> Convenient Method </td>
</tr>
<td>

   ```java
  ListDataFeedOptions listDataFeedOptions = new ListDataFeedOptions();
  ListDataFeedFilter listDataFeedFilter = new ListDataFeedFilter();
  listDataFeedFilter.setName("name_prefix");
  listDataFeedFilter.setCreator("demo@microsoft.com");
  listDataFeedOptions.setListDataFeedFilter(listDataFeedFilter);

  PagedIterable<DataFeed> response = metricsAdvisorAdministrationClient.listDataFeeds(listDataFeedOptions);

  // get datafeed name
  DataFeed dataFeed = response.iterator().next();
  String name = dataFeed.getName();
   ```
</td>
</tr>
</table>

* deleteDataFeed

  [API](https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=7384d93fddd84ffd86645da221a54392&doc=False&diffOnly=False&revisionId=3c03646e40b0412f90de084648710e2c)

<table>
<tr>
<td> DPG </td>
</tr>
<tr>
<td>

  ```java
  RequestOptions requestOptions = new RequestOptions();
  Response<Void> response = metricsAdvisorAdministrationClient.deleteDataFeedWithResponse(
                          "01234567-8901-2345-6789-012345678901", requestOptions);
  ```

</td>
</tr>
<tr>
<td> Convenient Method </td>
</tr>
<td>

  ```java
  metricsAdvisorAdministrationClient.deleteDataFeed("01234567-8901-2345-6789-012345678901");
  ```

</td>
</tr>
</table>


## Grow-up Story on Long-Running Operation

Since MetricsAdvisor does not contain long-running operation (LRO), we choose `azure-communication-phonenumbers` for grow-up story of the `SearchAvailablePhoneNumbers` method.

### Initial DPG configuration

We directly add the Java configuration to swagger `readme.java.md`, and sdk-automation will create the pull request, with all SDK CI green.

```yaml
security: AADToken
security-scopes: https://communication.azure.com//.default

tag: package-phonenumber-2021-03-07

output-folder: $(java-sdks-folder)/sdk/communication/azure-communication-phonenumbersdemo
data-plane: true
namespace: com.azure.communication.phonenumbersdemo

service-name: PhoneNumbers
service-versions:
  - '2021-03-07'

generate-samples: true
generate-tests: true
partial-update: true
```

* [ApiView](https://apiview.dev/Assemblies/Review/10f4f4e429594af0ba7b78f19c2e4133?revisionId=cf4ecd32a53b4a3e8a48109625ca10de&doc=False)
* [Java code](https://github.com/Azure/azure-sdk-for-java/pull/28715/commits/3eabd2671260ed6aafd8b9fd4fc4f2f29aca7de8)

### Write a test

```java
// request
ObjectNode searchRequest = MAPPER.createObjectNode();
searchRequest.put("assignmentType", "application");
searchRequest.putObject("capabilities")
    .put("calling", "none")
    .put("sms", "inbound+outbound");
searchRequest.put("phoneNumberType", "tollFree");
searchRequest.put("quantity", 1);

BinaryData body = BinaryData.fromObject(searchRequest);
SyncPoller<BinaryData, BinaryData> poller =
        phoneNumbersClient.beginSearchAvailablePhoneNumbers("US", body, new RequestOptions());

// poll once
PollResponse<BinaryData> pollResponse = poller.poll();
JsonNode pollResponseNode = MAPPER.readTree(pollResponse.getValue().toBytes());
Assertions.assertEquals("search", pollResponseNode.get("operationType").asText());
Assertions.assertEquals("notStarted", pollResponseNode.get("status").asText());

// poll till completion
PollResponse<BinaryData> lastPollResponse = poller.waitForCompletion();
pollResponseNode = MAPPER.readTree(lastPollResponse.getValue().toBytes());
Assertions.assertEquals("succeeded", pollResponseNode.get("status").asText());

Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, lastPollResponse.getStatus());

// final result
BinaryData finalResult = poller.getFinalResult();
JsonNode finalResultNode = MAPPER.readTree(finalResult.toBytes());
Assertions.assertEquals("+18332705858", finalResultNode.get("phoneNumbers").get(0).asText());
```

### Adapt to the non-standard LRO

The test would fail, because it turned out that the LRO used in `SearchAvailablePhoneNumbers` method is not a standard one.

The activation operation returns `operation-id` and `search-id` in headers, and SDK is required to use these parameters for polling and getting final result.

Therefore, developer is required to adapt to this, and provides a customized [PhoneNumbersSearchPollingStrategy](https://github.com/Azure/azure-sdk-for-java/blob/6338c084ae480f48a0029b85fd9b24dd722d4c4f/sdk/communication/azure-communication-phonenumbersdemo/src/main/java/com/azure/communication/phonenumbersdemo/implementation/PhoneNumbersSearchPollingStrategy.java), which implements above polling logic.

And then update the configuration to let `SearchAvailablePhoneNumbers` method use the new `PhoneNumbersSearchPollingStrategy`.
```yaml
polling:
  PhoneNumbers_SearchAvailablePhoneNumbers:
    strategy: new com.azure.communication.phonenumbersdemo.implementation.PhoneNumbersSearchPollingStrategy<>(this, {httpPipeline}, null, {context})
```

* No API change
* [Java code change](https://github.com/Azure/azure-sdk-for-java/pull/28715/commits/6338c084ae480f48a0029b85fd9b24dd722d4c4f) (only `PhoneNumbersSearchPollingStrategy.java` is hand-written)

Test would pass now.

### Customize `SearchAvailablePhoneNumbers` method

Now, we are in the position to customize the `SearchAvailablePhoneNumbers` method.

We can see in above code that uses the method, user is required to compose a JSON payload for the API, and parse the response as JSON, which is not convenient for a frequently used API.

Developer chooses to provide models for input and output of the method.
After reviewing the models in swagger, we decide to re-use some of the models that directly generated from swagger.

Designed `SearchAvailablePhoneNumbers` method would be
```java
SyncPoller<PhoneNumberOperation, PhoneNumberSearchResult> beginSearchAvailablePhoneNumbers(
    String countryCode, PhoneNumberType phoneNumberType, PhoneNumberAssignmentType assignmentType, 
    PhoneNumberCapabilities capabilities, Context context)
```

Update configuration to
1. Generate models from swagger (in implementation package), and expose some to user.
2. Use the `PhoneNumberOperation` and `PhoneNumberSearchResult` as polling response and final result of the method. AutoRest.Java would generate methods in implementation to help customization.
```yaml
polling:
  PhoneNumbers_SearchAvailablePhoneNumbers:
    strategy: new com.azure.communication.phonenumbersdemo.implementation.PhoneNumbersSearchPollingStrategy<>(this, {httpPipeline}, null, {context})
    intermediate-type: com.azure.communication.phonenumbersdemo.models.PhoneNumberOperation
    final-type: com.azure.communication.phonenumbersdemo.models.PhoneNumberSearchResult

generate-models: true
custom-types: BillingFrequency,PhoneNumberAssignmentType,PhoneNumberCapabilities,PhoneNumberCapabilityType,PhoneNumberCost,PhoneNumberOperationStatus,PhoneNumberOperationType,PhoneNumberSearchResult,PhoneNumberType
custom-types-subpackage: models
```

* [ApiView](https://apiview.dev/Assemblies/Review/10f4f4e429594af0ba7b78f19c2e4133?diffRevisionId=cf4ecd32a53b4a3e8a48109625ca10de&doc=False&diffOnly=False&revisionId=4048da5001f14312acf985a140bb8b35)
* [Java code change](https://github.com/Azure/azure-sdk-for-java/pull/28715/commits/ea3ca197941e8bae5c717a701d9afd31e5b04e32) (`PhoneNumberOperation.java` and 3 methods in clients is hand-written)

Test can now use models as input and output.
```java
// request
SyncPoller<PhoneNumberOperation, PhoneNumberSearchResult> poller =
    phoneNumbersClient.beginSearchAvailablePhoneNumbers(
        "US", PhoneNumberType.TOLL_FREE, PhoneNumberAssignmentType.APPLICATION,
        new PhoneNumberCapabilities()
            .setCalling(PhoneNumberCapabilityType.NONE)
            .setSms(PhoneNumberCapabilityType.INBOUND_OUTBOUND), Context.NONE);

// poll once
PollResponse<PhoneNumberOperation> pollResponse = poller.poll();
Assertions.assertEquals(PhoneNumberOperationType.SEARCH, pollResponse.getValue().getOperationType());
Assertions.assertEquals(PhoneNumberOperationStatus.NOT_STARTED, pollResponse.getValue().getStatus());

// poll till completion
PollResponse<PhoneNumberOperation> lastPollResponse = poller.waitForCompletion();
Assertions.assertEquals(PhoneNumberOperationStatus.SUCCEEDED, lastPollResponse.getValue().getStatus());

Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, lastPollResponse.getStatus());

// final result
PhoneNumberSearchResult finalResult = poller.getFinalResult();
Assertions.assertEquals("+18332705858", finalResult.getPhoneNumbers().get(0));
```

Difference for customer:

<table>
<tr>
<td> Before </td> <td> After </td>
</tr>
<tr>
<td>

```java
ObjectNode searchRequest = MAPPER.createObjectNode();
searchRequest.put("assignmentType", "application");
searchRequest.putObject("capabilities")
    .put("calling", "none")
    .put("sms", "inbound+outbound");
searchRequest.put("phoneNumberType", "tollFree");
searchRequest.put("quantity", 1);

BinaryData body = BinaryData.fromObject(searchRequest);
SyncPoller<BinaryData, BinaryData> poller =
    phoneNumbersClient.beginSearchAvailablePhoneNumbers("US", body, null);

PollResponse<BinaryData> lastPollResponse = poller.waitForCompletion();
if (lastPollResponse.getStatus() == LongRunningOperationStatus.SUCCESSFULLY_COMPLETED) {
    BinaryData finalResult = poller.getFinalResult();
    JsonNode finalResultNode = MAPPER.readTree(finalResult.toBytes());
    String phoneNumber = finalResultNode.get("phoneNumbers").get(0).asText();
}
```

</td>
<td>

```java
SyncPoller<PhoneNumberOperation, PhoneNumberSearchResult> poller =
    phoneNumbersClient.beginSearchAvailablePhoneNumbers(
        "US",
        PhoneNumberType.TOLL_FREE,
        PhoneNumberAssignmentType.APPLICATION,
        new PhoneNumberCapabilities()
            .setCalling(PhoneNumberCapabilityType.NONE)
            .setSms(PhoneNumberCapabilityType.INBOUND_OUTBOUND), Context.NONE);

PollResponse<PhoneNumberOperation> lastPollResponse = poller.waitForCompletion();
if (lastPollResponse.getStatus() == LongRunningOperationStatus.SUCCESSFULLY_COMPLETED) {
    PhoneNumberSearchResult finalResult = poller.getFinalResult();
    String phoneNumber = finalResult.getPhoneNumbers().get(0);
}
```

</td>
</tr>
</table>
