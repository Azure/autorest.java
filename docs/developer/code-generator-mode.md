# Mode in Code Generator

## Vanilla

AutoRest with flag `--java`. This is used primary for lib that involves a handwritten (data-plane) client over generated code. For example, `azure-storage-blob`.

This mode is not recommended to be used for new lib.

### Client Method

```java
Response<ResponseBody> operationWithResponse(String pathParam, EnumType requiredQueryParam, RequestBody body, EnumType optionalQueryParam, String optionalHeaderParam, Context context);

ResponseBody operation(String pathParam, EnumType requiredQueryParam, RequestBody body);
```

### Client

By default, the client takes the form of

```java
ServiceClient client = new ClientBuilder().buildServiceClient();

OperationGroup operationGroup = client.getOperationGroup();

operationGroup.operation();
```

Lots of the flags can be used to tune the generated client/method in this mode.

## Data-plane from Swagger

AutoRest with flag `--java --data-plane`. This is used for data-plane lib generated from Swagger.

### Client Method

```java
Response<BinaryData> operationWithResponse(String pathParam, String requiredQueryParam, BinaryData body, RequestOptions requestOptions);
```

The request body and response body both take the type of `BinaryData`. And optional parameters are set via `RequestOptions`. This pattern of client method is called "Protocol API".

No model class is generated in this mode.

### Client

The client is accessed via the Builder pattern.

```java
OperationsClient operationsClient = new ClientBuilder().buildOperationsClient();

OperationGroupClient operationGroupClient = new ClientBuilder().buildOperationGroupClient();

operationsClient.operation1();
operationGroupClient.operation2();
```

## Data-plane from TypeSpec

This is used for data-plane lib generated from TypeSpec source.

### Client Method

```java
Response<BinaryData> operationWithResponse(String pathParam, String requiredQueryParam, BinaryData body, RequestOptions requestOptions);

ResponseBody operation(String pathParam, EnumType requiredQueryParam, RequestBody body);

ResponseBody operation(String pathParam, EnumType requiredQueryParam, RequestBody body, EnumType optionalQueryParam, String optionalHeaderParam);
```

In additional to "Protocol API", it also generates the client method overloads with model class and optional parameters. This is called "Convenience API".

### Client

By default, The client is accessed via the Builder pattern, same as those in [Data-plane from Swagger](#data-plane-from-swagger).

`enable-subclient` emitter options can be used to switch the client structure to parent-child clients.

```java
ServiceClient client = new ClientBuilder().buildClient();

ChildClient childClient = client.getChildClient(childClientParam);

GrandchildClient grandchildClient2 = childClient.getGrandchildClient(grandchildParam);

client.operation1();
childClient.operation2();
grandchildClient2.operation3();
```

## Management-plane from Swagger/TypeSpec

This is used for management-plane lib generated from Swagger, with flag `--java --fluent=LITE`, or from TypeSpec.

The generated client method is similar to that generated from [Vanilla mode](#vanilla).

### Client

Model class would be modeled as `Resource`, `ProxyResource`.

Another Fluent code layer would be generated, modeling concept as "subscription", "resource group", "resource", "parent resource", "extension resource", and CRUD of the resource.

## Unbranded

This is used for 3rd-party lib that generated from TypeSpec.

### Client Method

The client method takes the form of
```java
Response<ResponseBody> operationWithResponse(String pathParam, String requiredQueryParam, BinaryData body, RequestOptions requestOptions);

ResponseBody operation(String pathParam, EnumType requiredQueryParam, RequestBody body);

ResponseBody operation(String pathParam, EnumType requiredQueryParam, RequestBody body, EnumType optionalQueryParam, String optionalHeaderParam);
```
