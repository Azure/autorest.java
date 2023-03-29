# DPG archboard

For the purpose of this review, we'll use [Text Analysis Authoring](https://docs.microsoft.com/en-us/rest/api/language/text-analysis-authoring) REST APIs to demonstrate the following:

- [Parity with DPG 1.0](#parity-with-dpg-10) ([API View](https://apiview.dev/Assemblies/Review/953fc91e3ec54be1938cfc6c43ed0ed6?revisionId=50ff38081be943e298aa17ae98fb5256&doc=False))
- [Models Generation](#models-generation) ([API View](https://apiview.dev/Assemblies/Review/953fc91e3ec54be1938cfc6c43ed0ed6?revisionId=7aa48d86e0f84c60a4b720496e34819b&doc=False))
- [Convenience client methods generation](#convenience-apis-client-methods-with-models) ([API View](https://apiview.dev/Assemblies/Review/953fc91e3ec54be1938cfc6c43ed0ed6?revisionId=9702f0a1ce9f49fb894fed5f7131d793&doc=False))

For the purpose of this review, we'll use [Authoring CADL](https://github.com/Azure/cadl-azure/blob/main/packages/cadl-samples/data-plane/language/authoring/authoring.cadl) as the source of REST API definition.

## Parity with DPG 1.0

In this section, we'll be reviewing the protocol methods generated using the CADL.

**[API view](https://apiview.dev/Assemblies/Review/953fc91e3ec54be1938cfc6c43ed0ed6?revisionId=50ff38081be943e298aa17ae98fb5256&doc=False)**

### Package structure

* Artifact name: `azure-language-authoring`
* Module name: `com.azure.language.authoring`
* Root package name: `com.azure.language.authoring`

**NOTE**: For client libraries generated from swagger, the above names are defined in the README.md configuration. For cadl based generation, the above names are derived from the namespace definition.

> [`namespace Azure.Language.Authoring`](https://github.com/Azure/cadl-azure/blob/main/packages/cadl-samples/data-plane/language/authoring/authoring.cadl#L24)

### Client creation

The generated code includes multiple clients. We'll pick an example for reviewing the client creation

#### REST API definition

```ts
@route("projects/global/")
interface Global {
  @get
  @route("languages")
  getSupportedLanguages(
    ...ListQueryParams,
    ...Foundations.ApiVersionParameter
  ): SupportedLanguages | Foundations.ErrorResponse;

  @get
  @route("training-config-versions")
  listTrainingConfigVersions(
    ...ListQueryParams,
    ...Foundations.ApiVersionParameter
  ): TrainingConfigVersions | Foundations.ErrorResponse;
}
```

#### Generated client classes

* [GlobalClientBuilder](https://apiview.dev/Assemblies/Review/953fc91e3ec54be1938cfc6c43ed0ed6?revisionId=50ff38081be943e298aa17ae98fb5256&doc=False#com.azure.language.authoring.GlobalClientBuilder)

```java
@ServiceClientBuilder(serviceClients = { GlobalClient, GlobalAsyncClient })
public final class GlobalClientBuilder implements HttpTrait<GlobalClientBuilder>, ConfigurationTrait<GlobalClientBuilder>, EndpointTrait<GlobalClientBuilder> {
    ...
}
```

* [GlobaAsyncClient](https://apiview.dev/Assemblies/Review/953fc91e3ec54be1938cfc6c43ed0ed6?revisionId=50ff38081be943e298aa17ae98fb5256&doc=False#com.azure.language.authoring.GlobalAsyncClient)

```java
@ServiceClient(builder = GlobalClientBuilder, isAsync = true)
public final class GlobalAsyncClient {
    ...
}
```

* [GlobalClient](https://apiview.dev/Assemblies/Review/953fc91e3ec54be1938cfc6c43ed0ed6?revisionId=50ff38081be943e298aa17ae98fb5256&doc=False#com.azure.language.authoring.GlobalClient)

```java
@ServiceClient(builder = GlobalClientBuilder)
public final class GlobalClient {
    ...
}
```

#### Usage

```java
GlobalClient client = new GlobalClientBuilder()
                        .endpoint("<endpoint>")
                        .buildClient();
```

**NOTE**: No credential method included in the builder. Adding the following to the cadl will generate the build with AzureKeyCredential support.

```ts
@useAuth(ApiKeyAuth<ApiKeyLocation.header, "Ocp-Apim-Subscription-Key">)
```

### GET method

#### REST API definition
```ts
@doc("Gets the details of a deployment.")
getDeployment is ResourceRead<Deployment>;
```
#### Generated client method

```java
// async
@Generated public Mono<Response<BinaryData>> getDeploymentWithResponse(String projectName, String deploymentName, RequestOptions requestOptions) 

// sync
@Generated public Response<BinaryData> getDeploymentWithResponse(String projectName, String deploymentName, RequestOptions requestOptions) 

```
#### Usage

```java
DeploymentsClient deploymentsClient = new DeploymentsClientBuilder()
        .endpoint("<endpoint>" )
        .buildClient();

Response<BinaryData> response = deploymentsClient.getDeploymentWithResponse("project-name", "deployment-name", new RequestOptions());
ObjectMapper objectMapper = new ObjectMapper();
BinaryData deployment = response.getValue();
JsonNode jsonNode = objectMapper.readTree(deployment.toBytes());
String projectName = jsonNode.get("projectName").asText();
String description = jsonNode.get("description").asText();
```

### Pageable API

#### REST API definition
```ts
@doc("Lists the existing deployments.")
list is ResourceList<Deployment>;
```
#### Generated client method
```java
// async
@Generated public PagedFlux<BinaryData> list(String projectName, RequestOptions requestOptions) 

// sync
@Generated public PagedIterable<BinaryData> list(String projectName, RequestOptions requestOptions) 
```
#### Usage
```java
DeploymentsClient deploymentsClient = new DeploymentsClientBuilder()
        .endpoint("<endpoint>" )
        .buildClient();

PagedIterable<BinaryData> response = deploymentsClient.list("project-name", new RequestOptions());
ObjectMapper objectMapper = new ObjectMapper();
list.forEach(deployment -> {
    JsonNode jsonNode = objectMapper.readTree(deployment.toBytes());
    String deploymentName = jsonNode.get("name").asText();
    String description = jsonNode.get("description").asText();
});
```

### Long Running Operation

#### REST API definition
```ts
@doc("Creates a new deployment or replaces an existing one.")
deployProject is LongRunningResourceCreateOrReplace<Deployment>;
```
#### Generated client method

```java
// async
@Generated public PollerFlux<BinaryData, BinaryData> beginDeployProject(String projectName, String deploymentName, RequestOptions requestOptions) 

// sync
@Generated public SyncPoller<BinaryData, BinaryData> beginDeployProject(String projectName, String deploymentName, RequestOptions requestOptions) 
```
#### Usage

```java
DeploymentsClient deploymentsClient = new DeploymentsClientBuilder()
        .endpoint("<endpoint>" )
        .buildClient();
SyncPoller<BinaryData, BinaryData> poller = deploymentsClient.beginDeployProject("project-name", "deployment-name", new RequestOptions());
poller.waitForCompletion();
BinaryData deployment = poller.getFinalResult();

ObjectMapper objectMapper = new ObjectMapper();
JsonNode jsonNode = objectMapper.readTree(deployment.toBytes());
String deploymentName = jsonNode.get("name").asText();
String description = jsonNode.get("description").asText();
```

### Documentation

#### REST API definition
```ts
@doc("Creates a new project or updates an existing one.")
createOrUpdate is LongRunningResourceCreateOrUpdate<Project>;
```
#### Generated client JavaDoc

```java
/**
 * Creates a new project or updates an existing one.
 *
 * <p><strong>Request Body Schema</strong>
 *
 * <pre>{@code
 * {
 *     projectKind: String(CustomSingleLabelClassification/CustomMultiLabelClassification/CustomEntityRecognition) (Optional)
 *     storageInputContainerName: String (Optional)
 *     settings (Optional): {
 *         String: String (Optional)
 *     }
 *     multilingual: Boolean (Optional)
 *     description: String (Optional)
 *     language: String (Optional)
 * }
 * }</pre>
 *
 * <p><strong>Response Body Schema</strong>
 *
 * <pre>{@code
 * {
 *     projectName: String (Required)
 *     projectKind: String(CustomSingleLabelClassification/CustomMultiLabelClassification/CustomEntityRecognition) (Required)
 *     storageInputContainerName: String (Required)
 *     settings (Optional): {
 *         String: String (Optional)
 *     }
 *     multilingual: Boolean (Optional)
 *     description: String (Optional)
 *     language: String (Required)
 *     createdDateTime: OffsetDateTime (Required)
 *     lastModifiedDateTime: OffsetDateTime (Required)
 *     lastTrainedDateTime: OffsetDateTime (Required)
 *     lastDeployedDateTime: OffsetDateTime (Required)
 * }
 * }</pre>
 *
 * @param projectName The projectName parameter.
 * @param optionalProperties The template for adding optional properties.
 * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
 * @throws HttpResponseException thrown if the request is rejected by server.
 * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
 * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
 * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
 * @return the {@link SyncPoller} for polling of long-running operation.
 */
@Generated
@ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
public SyncPoller<BinaryData, BinaryData> beginCreateOrUpdate(
        String projectName, BinaryData optionalProperties, RequestOptions requestOptions)
```
## Key notes

- Artifact name, module name and package name definition are supported using cadl `namespace` definition, we currently support overrite `namespace` definition in cadl using [java.json](https://github.com/weidongxu-microsoft/cadl-textanalytics/blob/main/authoring/java.json).
- `Client` and `ClientBuilder` are generated as DPG 1.0.
- Service version is from cadl `@serviceVersion` and is supported as DPG 1.0.
- Authentication including API key authentication and OAuth2 authentication are supported as DPG 1.0.
- All operation methods (GET, POST, DELETE, PATCH) are supported as DPG 1.0.
- Method signature only has required parameters, request and response body will be `BinaryData`, optional parameters will be set in `RequestOptions`.
- OperationResource polling strategy based long running operation is supported.
- Pageble operation is supported as DPG 1.0.
- Documentation including @doc and @summary are supported.


## Missing features

- LRO is not fully supported yet. We only support what is in current cadl-azure-core, the [OperationResource](https://github.com/Azure/cadl-azure/blob/main/packages/cadl-azure-core/lib/foundations.cadl#L57-L63) pattern. ([GitHub issue]( https://github.com/Azure/autorest.java/issues/1684))
- Samples/Tests generation is not supported yet. ([GitHub issue](https://github.com/Azure/autorest.java/issues/1682))


## Models Generation

In this section, we'll look at the generated models. Here, the models are not used in the client method. We'll cover that in the next section when we discuss convenience APIs.

[API View with models](https://apiview.dev/Assemblies/Review/953fc91e3ec54be1938cfc6c43ed0ed6?revisionId=7aa48d86e0f84c60a4b720496e34819b&doc=False)

### Fluent models

```ts
model Project {
  @key
  @segment("projects")
  projectName: string;

  @doc("The project kind.")
  projectKind: ProjectKind;

  @doc("The storage container name.")
  storageInputContainerName: string;

  @doc("The project settings.")
  settings?: ProjectSettings;

  @doc("Whether the project would be used for multiple languages or not.")
  multilingual?: boolean;

  @doc("The project description.")
  description?: string;

  @doc("The project language. This is BCP-47 representation of a language. For example, use \"en\" for English, \"en-gb\" for English (UK), \"es\" for Spanish etc.")
  language: string;

  @doc("Represents the project creation datetime.")
  @format("date-time")
  @visibility("read")
  createdDateTime: string;

  @doc("Represents the project last modification datetime.")
  @format("date-time")
  @visibility("read")
  lastModifiedDateTime: string;

  @doc("Represents the project last training datetime.")
  @format("date-time")
  @visibility("read")
  lastTrainedDateTime: string;

  @doc("Represents the project last deployment datetime.")
  @format("date-time")
  @visibility("read")
  lastDeployedDateTime: string;
}
```

```java
@Fluent
public final class ProjectRequest {
    public ProjectRequest()
    public String getDescription() 
    public ProjectRequest setDescription(String description) 
    public String getLanguage() 
    public ProjectRequest setLanguage(String language) 
    public Boolean isMultilingual() 
    public ProjectRequest setMultilingual(Boolean multilingual) 
    public ProjectKind getProjectKind() 
    public ProjectRequest setProjectKind(ProjectKind projectKind) 
    public Map<String, String> getSettings() 
    public ProjectRequest setSettings(Map<String, String> settings) 
    public String getStorageInputContainerName() 
    public ProjectRequest setStorageInputContainerName(String storageInputContainerName) 
}
```
### Immutable models
```ts
@doc("Represents a supported language.")
model SupportedLanguage {
  @doc("The language name.")
  languageName: string;

  @doc("The language code. This is BCP-47 representation of a language. For example, \"en\" for English, \"en-gb\" for English (UK), \"es\" for Spanish etc.")
  languageCode: string;
}
```

```java
@Immutable
public final class SupportedLanguage {
    public SupportedLanguage(String languageName, String languageCode) 
    public String getLanguageCode() 
    public String getLanguageName() 
}
```
### Property types

Built-in models and a few `@format` decorator is supported ([cases](https://github.com/Azure/autorest.java/blob/main/typespec-tests/tsp/builtin.cadl)).


### Enums

```ts
@doc("Represents the job status.")
@knownValues(JobStatusValues)
model JobStatus is string;

@doc("Represents the job status values.")
enum JobStatusValues {
  // These values are all camel-cased in the original spec
  notStarted,
  running,
  succeeded,
  failed,
  cancelled,
  cancelling,
  partiallyCompleted,
}
```

```java
public final class JobStatus extends ExpandableStringEnum<JobStatus> {
    public static final JobStatus NOT_STARTED = fromString("notStarted");
    public static final JobStatus RUNNING = fromString("running");
    public static final JobStatus SUCCEEDED = fromString("succeeded");
    public static final JobStatus FAILED = fromString("failed");
    public static final JobStatus CANCELLED = fromString("cancelled");
    public static final JobStatus CANCELLING = fromString("cancelling");
    public static final JobStatus PARTIALLY_COMPLETED = fromString("partiallyCompleted");
    public JobStatus()
    public static JobStatus fromString(String name) 
    public static Collection<JobStatus> values() 
}
```

### Polymorphic

`AnalyzeTextEntityLinkingInput`, Cadl model from textanalytics

```ts
@discriminator("kind")
model AnalyzeTextTask {}

model Task<TDiscriminator, TParameters, TAnalysisInput = MultiLanguageAnalysisInput>
  extends AnalyzeTextTask {
  kind: TDiscriminator;
  analysisInput?: TAnalysisInput;
  parameters?: TParameters;
}

model AnalyzeTextEntityLinkingInput is Task<"EntityLinking", EntityLinkingTaskParameters> {}
```

Java model

```java
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonTypeName("EntityLinking")
@Fluent
public final class AnalyzeTextEntityLinkingInput extends AnalyzeTextTask {
    ...
}
```

### ResponseError
Referencing errors from `Azure.Core.Foundations.Error`.

```ts
model Job {
  @doc("The job ID.")
  jobId: string;

  @doc("The creation date time of the job.")
  @format("date-time")
  @visibility("read")
  createdDateTime: string;

  @doc("The the last date time the job was updated.")
  @format("date-time")
  @visibility("read")
  lastUpdatedDateTime: string;

  @doc("The expiration date time of the job.")
  @format("date-time")
  @visibility("read")
  expirationDateTime: string;

  @doc("The job status.")
  status: JobStatus;

  @doc("The warnings that were encountered while executing the job.")
  warnings: JobWarning[];

  @doc("The errors encountered while executing the job.")
  errors: Azure.Core.Foundations.Error;
}

@parentResource(Deployment)
model DeploymentJob {
  ...Job;

  @key("jobId")
  @segment("jobs")
  id: string;
}
```

```java
@Immutable
public final class DeploymentJob {
    public DeploymentJob(String jobId, OffsetDateTime createdDateTime, OffsetDateTime lastUpdatedDateTime, OffsetDateTime expirationDateTime, JobStatus status, List<JobWarning> warnings, ResponseError errors, String id) 
    public OffsetDateTime getCreatedDateTime() 
    public ResponseError getErrors() 
    public OffsetDateTime getExpirationDateTime() 
    public String getId() 
    public String getJobId() 
    public OffsetDateTime getLastUpdatedDateTime() 
    public JobStatus getStatus() 
    public List<JobWarning> getWarnings() 
}
```

### Key notes
- There is discussion on polymorphic on Cadl. SDK will follow up and adapt.
- Some model that does not need to be visible to user (e.g. `Page<>`) is moved to `implementation.models`.
- `float32` and `float64` both results in `Double`.
- Client flatten and parameter grouping is not supported.
- There is no client validation in model.

### Missing features

- Special handling for output-only models (as adding required or optional properties to output-only model is allowed). [Issue](https://github.com/Azure/autorest.java/issues/1685)
- Naming on templated models in request body. [Issue](https://github.com/Azure/cadl-azure/issues/1995)
- Finalize on Cadl representation of types like `URL` and `GUID` (`@format` is discouraged). [Issue](https://github.com/Azure/autorest.java/issues/1683)

## Convenience APIs (client methods with models)

To generate the convenience methods, CADL has to be updated to mark the clients that need convenience methods with `@convenienceMethod` annotation as shown below. Note that this is not directly supported in CADL yet. This is coming from a decorator added explicitly in Java repository to make this work.

**[API view](https://apiview.dev/Assemblies/Review/953fc91e3ec54be1938cfc6c43ed0ed6?revisionId=9702f0a1ce9f49fb894fed5f7131d793&doc=False)**

```ts
@convenienceMethod
interface Jobs {
  @doc("Gets the status of an existing deployment job.")
  getDeploymentStatus is ResourceRead<DeploymentJob>;

  @doc("Gets the status of an existing swap deployment job.")
  getSwapDeploymentsStatus is ResourceRead<SwapDeploymentsJob>;
}


@parentResource(Deployment)
model DeploymentJob {
  ...Job;

  @key("jobId")
  @segment("jobs")
  id: string;
}
```

### GET method

#### Generated client methods

```java
@ServiceClient(builder = JobsClientBuilder)
public final class JobsClient {
    // This class does not have any public constructors, and is not able to be instantiated using 'new'.
    // Service Methods:
    @Generated public DeploymentJob getDeploymentStatus(String projectName, String deploymentName, String jobId) 
    @Generated public Response<BinaryData> getDeploymentStatusWithResponse(String projectName, String deploymentName, String jobId, RequestOptions requestOptions) 
    @Generated public SwapDeploymentsJob getSwapDeploymentsStatus(String projectName, String deploymentName, String jobId) 
    @Generated public Response<BinaryData> getSwapDeploymentsStatusWithResponse(String projectName, String deploymentName, String jobId, RequestOptions requestOptions) 
}

// Model for DeploymentJob
@Immutable
public final class DeploymentJob {
    public DeploymentJob(String jobId, OffsetDateTime createdDateTime, OffsetDateTime lastUpdatedDateTime, OffsetDateTime expirationDateTime, JobStatus status, List<JobWarning> warnings, ResponseError errors, String id) 
    public OffsetDateTime getCreatedDateTime() 
    public ResponseError getErrors() 
    public OffsetDateTime getExpirationDateTime() 
    public String getId() 
    public String getJobId() 
    public OffsetDateTime getLastUpdatedDateTime() 
    public JobStatus getStatus() 
    public List<JobWarning> getWarnings() 
}
```

### Pageable API

#### REST API definition
```ts
@doc("Lists the existing projects.")
list is ResourceList<
    Project,
    {
        parameters: ListQueryParams;
    }
>;
```
#### Generated client methods
```java
// async
@Generated public PagedFlux<Project> list() 

// sync
@Generated public PagedIterable<Project> list() 


// Project model
@Fluent
public final class Project {
    public Project(String projectName, ProjectKind projectKind, String storageInputContainerName, String language, OffsetDateTime createdDateTime, OffsetDateTime lastModifiedDateTime, OffsetDateTime lastTrainedDateTime, OffsetDateTime lastDeployedDateTime) 
    public OffsetDateTime getCreatedDateTime() 
    public String getDescription() 
    public Project setDescription(String description) 
    public String getLanguage() 
    public OffsetDateTime getLastDeployedDateTime() 
    public OffsetDateTime getLastModifiedDateTime() 
    public OffsetDateTime getLastTrainedDateTime() 
    public Boolean isMultilingual() 
    public Project setMultilingual(Boolean multilingual) 
    public ProjectKind getProjectKind() 
    public String getProjectName() 
    public Map<String, String> getSettings() 
    public Project setSettings(Map<String, String> settings) 
    public String getStorageInputContainerName() 
}
```

### Long Running Operation

Not supported yet

### Key notes

- This is a prototype of convenience API. We demonstrate that the framework for providing them is available now, but we would have further discussion on what kind of overload to generate (required parameters, optional parameters, `Context` or not, whether to group the parameters to `Options` class). [Issue](https://github.com/Azure/autorest.java/issues/1686)

### Missing features

- Convenience API for long-running operation is not supported. We will work closely with `cadl-azure-core`, to devise Cadl that can let SDK know which pattern of LRO is used, which model is used in polling, and which model is used in final result (preferably, which operation is used for polling, which for fetching final result). [Issue](https://github.com/Azure/autorest.java/issues/1672)
- Convenience API (as well as model) for JSON Merge Patch operation will be different. In design process within Java. [Issue](https://github.com/Azure/autorest.java/issues/1530)
