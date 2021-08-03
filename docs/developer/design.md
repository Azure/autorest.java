# Design

## Modules

`extension-base` is the shared module providing `CodeModel` represented in Java language, JSON RPC, and `NewPlugin` plugin entry.

`preprocessor`, `javagen`, `postprocessor` is the shared modules on models and operations.
Also, they are the pipeline for data-plane SDK generator.
`postprocessor` uses Eclipse JDT Language Server to provide customizations.

`fluentnamer`, `fluentgen` is activated when `--fluent` option is specified.
They are the pipeline for management-plane SDK generator.

`androidgen` is activated when `--android` option is specified.
Together with `preprocessor`, they are the pipeline for data-plane SDK generator for Android.

## Configure on Modeler Four

Modeler Four is configured on `readme.md` of `preprocessor` and `fluentnamer`.

The most important configure is the version of Modeler Four.
The important options are:
- `flatten-models`, whether to process for `x-ms-client-flatten` extension.
- `flatten-payloads`, whether to process for `payload-flattening-threshold` options.
- `group-parameters`, whether to process for `x-ms-parameter-grouping` extension.
- `naming`, options for the prenamer.

Historically, data-plane leaves all of these options as `true`, while management-plane try to turn them to `false`.

Usually `preprocessor` and `fluentnamer` would save the input from pipeline to `code-model.yaml` in module root.
The content of this file is helpful for inspecting Modeler Four output.

## RPC within AutoRest Java

### Between `preprocessor` and `javagen`

The content of RPC still follows `CodeModel`, after transformation of `preprocessor`.
However, it could look quite different from output of Modeler Four.

### Between `java` and `postprocessor`

The content would be Java files.

## Details on Modules

### `extension-base` Module

`NewPlugin` as entry for plugin, providing RPC for pipeline.

`CodeModel` as Java representation of the code model from Modeler Four.
`CodeModelCustomConstructor` involves lots of work-around, so that SnakeYAML can parse the code model YAML.
Roughly speaking, `schemas` contain the models, while `operations` grouped into `operationGroups` represent the REST APIs.

`JavaSettings` provides basic configuration for AutoRest Java.

### `preprocessor` Module

`Transformer` renames model/property/method/parameter to be consistent to Java naming. It also supplies the `next` operation for `x-ms-pageable` extension.

`CodeNamer` as utility for Java naming, including conflict handling e.g. from reserved word in Java.

### `fluentnamer` Module

In addition to what is done in `preprocessor` module, its `Transformer` handles normalization of resource type (e.g. subclass of `Resource`), error type (e.g. subclass of `ManagementException` and `ManagementError`), operation name (e.g. `getByResourceGroup` and `listByResourceGroup`).

It also handles naming conflict between operation groups and models (e.g. `FooClient` from operation group client and `FooClient` from model, maybe another `FooClient` of the service client), naming of anonymous model (e.g. property of anonymous schema, anonymous superclass), rename from options, cleaning up of unused schemas (which is necessary because of the normalization).

`FluentJavaSettings` provides additional configuration on Fluent.

### `javagen` Module

#### Client Model

Classes under `model.clientmodel` namespace is the client model, which is the representation of Java code to be generated in memory.

`Client` is the container of the client model objects.

`IType` is the interface for all models. Its subclass contains:
- `PrimitiveType` for primitive data type.
- `ClassType` for Java classes.
- `GenericType` for generic types (List, Map, Iterator, etc.).
- `ArrayType` for arrays.
- `EnumType` for enums, or subclass of `ExpandableStringEnum` if it has `x-ms-enum` extension.

`ClientModel` and `ClientModelProperty` represents a class and its fields (`ClassType` that requires a class definition in generated code).
`ClientModelPropertyReference` represents the accessor to certain field, but not necessarily the field defined in this class.

`ProxyMethod` and `ProxyMethodParameter` represents a proxy method (the REST API interface that will be supplied a proxy implementation by `RestProxy`) for the REST API, and its parameters.
`ProxyMethodExample` represents the example request provided via `x-ms-examples` extension.

`ClientMethod`, `ClientMethodParameter`, and `ReturnValue` represents the (usually public) user-facing client method that calls the `ProxyMethod` to make the REST API call, and its parameters and return value.
It is usually one-to-many mapping from `ProxyMethod` to `ClientMethod`, as client method has different overloading for convenience.

`MethodGroupClient` represents the grouping of `ClientMethod`, according to operation group in code model.

`ServiceClient` and `ServiceClientProperty` represents the service client and its fields, the entry class (and its builder) of the generated SDK.

Other auxiliary classes, e.g. `PackageInfo`, `ModuleInfo`, `Pom`, etc. 

#### Mapper

Classes under `mapper` namespace is the mappers, which transforms code model to client model of Java code.

For example,
- `SchemaMapper` transform `Schema` to `IType`.
- `ModelMapper` transform `ObjectSchema` to `ClientModel`.
- `ProxyMethodMapper` transform `Operation` to (potentially multiple) `ProxyMethod`.
- `ClientMethodMapper` transform `Operation` to multiple `ClientMethod`.
- `MethodGroupMapper` transform `OperationGroup` to `MethodGroupClient`.
- `ServiceClientMapper` transform `CodeModel` to `ServiceClient`.

`ClientMapper` invokes other mappers to transform `CodeModel` to `Client`.

#### Template

Classes under `template` namespace is the templates, which transforms client model to Java code.

It writes Java code using classes under `model.javamodel` namespace.
`JavaPackage` contains all the files for the complete package, which may include non-Java file as well (e.g. Maven POM).

Classes of mapper and template is constructed by abstract factory, which enables overriding the behavior in other modules.

### `fluentgen` Module

Fluent Java SDK for management-plane has a different [API design](https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/resourcemanager/docs/DESIGN.md).
Simply speaking, the Fluent interface wraps the vanilla Client and REST API.

#### Client Model

`FluentClient` is the container of the client model objects, which includes `Client`.

`FluentManager` and `FluentManagerProperty` represents the manager and fields for resource collections, which is the entry class of Fluent SDK.

`FluentResourceCollection` and `FluentCollectionMethod` represents the resource collection and its methods.

`FluentResourceModel` and `FluentModelProperty` represents the resource model and its fields. 

Resource model is the most complicated construct in Fluent.
It can be categorized by `ModelCategory`.
- `IMMUTABLE` if it is a response of service, but actually not look like an Azure resource.
- `RESOURCE_GROUP_AS_PARENT`, `SUBSCRIPTION_AS_PARENT`, `NESTED_CHILD` for different path pattern of the Azure resource.
- `SCOPE_AS_PARENT`, `SCOPE_NESTED_CHILD` for different path pattern of the extension resource.

It is possible to have an Azure resource matching multiple path pattern.
Currently, this case is not well-handled.

Except for `IMMUTABLE` category, `FluentResourceModel` contains `ResourceCreate`, optionally `ResourceUpdate`, `ResourceRefresh`, `ResourceActions`, for respective mutable API to the resource.
- `ResourceCreate` contains a sequence of `DefinitionStage` for definition and creation of the Azure resource, and the related `FluentMethod`.
- `ResourceUpdate` contains a collection of `UpdateStage` for updating of the resource, and the related `FluentMethod`.
- `ResourceRefresh` for `refresh` of the resource, and convenient `getById` API of the resource in resource collection.
- `ResourceDelete` for convenient `deleteById` API of the resource in resource collection.
- `ResourceActions` for proxy APIs on the resource. They are convenient APIs for the proxy APIs provided in resource collections.

It is possible that `ResourceCreate` and `ResourceUpdate` define duplicate methods (same method signature, but possibly different implementation) and/or class variables.
`ResourceImplementation` helps to merge them.

Classes under `fluent.model.clientmodel.examplemodel` is model to represent code sample for `FluentCollectionMethod`, `ResourceCreate`, or `ResourceUpdate`.

#### Mapper

`ResourceParser` does the heavy lift of figuring out the category of the `FluentResourceModel`, and then assign related `ResourceCreate` etc. to it.

#### Template

As the variety of the Fluent methods, model and template has greater dependency on `MethodTemplate` class, which provides an all-in-one representation of the Java method and its code.
On the other side, this blurs the separation of client model and template.

`FluentJavaPackage` contains more items, including `pom.xml`, `readme.md`, `changelog.md`, utility classes, classes for sample codes, etc.

One important option is `--sdk-integration`.
When the option is set, fluentgen will try to generate package that conform to the requirement of [azure-sdk-for-java](https://github.com/Azure/azure-sdk-for-java).
- It reads the dependency package version from `eng/versioning/version_client.txt`.
- It generates `pom.xml` that refers to parent pom.
- It tries a few hack to "update" the `pom.xml` and `changelog.md`.
- It generates `readme.md` that provides basic usage and authentication.
- It generates `sample.md` that refers to sample codes.
