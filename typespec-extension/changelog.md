# Release History

## 0.18.3 (Unreleased)

Compatible with compiler 0.58.

## 0.18.2 (2024-07-22)

Compatible with compiler 0.58.

## 0.18.1 (2024-07-17)

Compatible with compiler 0.58.

## 0.18.0 (2024-07-10)

Compatible with compiler 0.57.

### Breaking Changes

- Request body without `@body` or `@bodyRoot`: the properties of the request body is flattened into method signature.

## 0.17.1 (2024-07-03)

Compatible with compiler 0.57.

- Bug fix for stream-style-serialization for ARM.
- Enabled stream-style-serialization by default for ARM.

## 0.17.0 (2024-06-14)

Compatible with compiler 0.57.

## 0.16.3 (2024-06-03)

Compatible with compiler 0.56.

- Generate customizable `validateClient` method in client builder class.

## 0.16.2 (2024-05-30)

Compatible with compiler 0.56.

- Generate `OperationLocationPollingStrategy` class in implementation package for long-running operation.
The generated lib would no longer need to depend on the `azure-core-experimental` lib.

## 0.16.1 (2024-05-29)

Compatible with compiler 0.56.

- Improvement on json-merge-patch model classes.
- Improvement on stream-style-serialization on polymorphic classes.
- Improvement on management-plane sample generation from TypeSpec.

## 0.16.0 (2024-05-17)

Compatible with compiler 0.56.

- Adopt TCGC model types.

## 0.15.18 (2024-05-13)

Compatible with compiler 0.56.

- `enum` is closed enum.

## 0.15.17 (2024-05-11)

Compatible with compiler 0.56.

- Behavior change on access/usage of non-polymorphic subclass.

## 0.15.16 (2024-05-08)

Compatible with compiler 0.56.

## 0.15.15 (2024-05-06)

Compatible with compiler 0.55.

## 0.15.14 (2024-04-27)

Compatible with compiler 0.55.

- Added `ServiceVersion` filter for pinned api-version.

## 0.15.13 (2024-04-26)

Compatible with compiler 0.55.

- Supported `api-version` from typespec config.
- Update dependency of "typespec-client-generator-core" for bug fix.

## 0.15.12 (2024-04-20)

Compatible with compiler 0.55.

## 0.15.11 (2024-04-19)

Compatible with compiler 0.55.

- Bug fix on supporting `application/merge-patch+json`.

## 0.15.10 (2024-04-17)

Compatible with compiler 0.55.

## 0.15.9 (2024-04-15)

Compatible with compiler 0.55.

- Update dependency of "typespec-client-generator-core" for bug fix.

## 0.15.8 (2024-04-11)

Compatible with compiler 0.55.

- Bug fix on `@encode`.
- Bug fix on required property of nullable type.

## 0.15.7 (2024-04-03)

Compatible with compiler 0.55.

## 0.15.6 (2024-03-29)

Compatible with compiler 0.54.

## 0.15.5 (2024-03-27)

Compatible with compiler 0.54.

## 0.15.4 (2024-03-26)

Compatible with compiler 0.54.

## 0.15.3 (2024-03-18)

Compatible with compiler 0.54.

## 0.15.2 (2024-03-08)

Compatible with compiler 0.54.

## 0.15.1 (2024-03-08)

Compatible with compiler 0.54.

## 0.15.0 (2024-03-08)

Compatible with compiler 0.54.

- Supported "flavor" from typespec config.

## 0.14.1 (2024-03-07)

Compatible with compiler 0.54.

## 0.14.0 (2024-02-29)

Compatible with compiler 0.53.

- Enhanced convenience API for "application/merge-patch+json".

## 0.13.11 (2024-03-04)

Compatible with compiler 0.53.

## 0.13.10 (2024-02-28)

Compatible with compiler 0.53.

## 0.13.9 (2024-02-27)

Compatible with compiler 0.53.

## 0.13.8 (2024-02-26)

Compatible with compiler 0.53.

- Fixed bug that 'partial update' will execute twice when generate from TypeSpec.

## 0.13.7 (2024-02-23)

Compatible with compiler 0.53.

## 0.13.6 (2024-02-20)

Compatible with compiler 0.53.

- Fixed bug related to the support of `union` as extensible enum.

## 0.13.5 (2024-02-09)

Compatible with compiler 0.53.

- Behavior changed on "multipart/form-data" request. If `filename` is not provided, implementation will no longer provide a default filename to `Content-Disposition` line.

## 0.13.4 (2024-02-07)

Compatible with compiler 0.53.

## 0.13.3 (2024-02-04)

Compatible with compiler 0.52.

## 0.13.2 (2024-02-02)

Compatible with compiler 0.52.

- Supported `@encodedName` for "application/json".

## 0.13.1 (2024-01-26)

Compatible with compiler 0.52.

- Behavior changed on "multipart/form-data" request. The file field would take a `##FileDetails` model, instead of `BinaryData`. 

## 0.13.0 (2024-01-25)

Compatible with compiler 0.52.

- Supported `@clientName` from "@azure-tools/typespec-client-generator-core".
- Supported `@flattenProperty` from "@azure-tools/typespec-client-generator-core".

## 0.12.3 (2024-01-22)

Compatible with compiler 0.51.

- (Preview) Supported generating SDK from management-plane TypeSpec (`@armProviderNamespace`).

## 0.12.2 (2024-01-09)

Compatible with compiler 0.51.

- Supported convenience API for "application/merge-patch+json" (basic functionality), only available when stream-style-serialization is enabled.

## 0.12.1 (2023-12-18)

Compatible with compiler 0.51.

- Supported convenience API for "multipart/form-data".

## 0.12.0 (2023-12-13)

Compatible with compiler 0.51.

## 0.11.3 (2023-12-06)

Compatible with compiler 0.50.

- Supported `crossLanguageDefinitionId`.
- Supported `decmial` scalar.

## 0.11.2 (2023-11-16)

Compatible with compiler 0.50.

## 0.11.1 (2023-11-14)

Compatible with compiler 0.50.

## 0.11.0 (2023-11-13)

Compatible with compiler 0.50.

- Supported `branded: false` for prototype of non-Azure SDK.
- Supported generate `Union` as `BinaryData`.

## 0.10.0 (2023-10-23)

Compatible with compiler 0.49.

## 0.9.0 (2023-09-29)

Compatible with compiler 0.48.

- Supported `@include` from typespec-client-generator-core.
- Supported generate model classes to implementation package, for API of `@access(Access.internal)` or `@internal`.

## 0.8.13 (2023-09-14)

Compatible with compiler 0.48.

## 0.8.12 (2023-09-12)

Compatible with compiler 0.47.

- Configuration of API key in builder is via `KeyCredential`.

## 0.8.11 (2023-09-01)

Compatible with compiler 0.47.

- Supported `@access` and `@usage` from typespec-client-generator-core.

## 0.8.10 (2023-08-16)

Compatible with compiler 0.47.

## 0.8.9 (2023-08-16)

Compatible with compiler 0.47.

- Supported `EnumMember` as type.

## 0.8.8 (2023-07-21)

Compatible with compiler 0.46.

- Operation which refers `Union` type is treated as protocol API, i.e. with `convenientAPI(false)`.

## 0.8.7 (2023-07-18)

Compatible with compiler 0.46.

## 0.8.6 (2023-07-17)

Compatible with compiler 0.46.

## 0.8.5 (2023-07-12)

Compatible with compiler 0.46.

- Supported `customization-class` as emitter option. See [Customization](https://github.com/Azure/autorest.java/blob/main/typespec-extension/readme.md#customization).
- `enable-sync-stack` emitter option default to `true`.
- Temporarily disabled support for `@include`.

## 0.8.4 (2023-06-30)

Compatible with compiler 0.45.

- Supported `@include` from typespec-client-generator-core.

## 0.8.3 (2023-06-25)

Compatible with compiler 0.45.

## 0.8.2 (2023-06-15)

Compatible with compiler 0.45.

- (Preview) Supported `RequestConditions` and `MatchConditions` for eTag headers.

## 0.8.1 (2023-06-14)

Compatible with compiler 0.45.

## 0.8.0 (2023-06-08)

Compatible with compiler 0.45.

## 0.7.4 (2023-06-06)

Compatible with compiler 0.44.

- Supported partial update for models and extensible enums.
- Supported custom scheme for AuthType.http.

## 0.7.3 (2023-05-30)

Compatible with compiler 0.44.

## 0.7.2 (2023-05-24)

Compatible with compiler 0.44.

## 0.7.1 (2023-05-19)

Compatible with compiler 0.44.

- Supported generating samples with convenience API.

## 0.7.0 (2023-05-11)

Compatible with compiler 0.44.

- Supported `@encode(DurationKnownEncoding.seconds, ..)` from compiler.

## 0.6.2 (2023-04-25)

Compatible with compiler 0.43.

- Supported `custom-types` and `custom-types-subpackage` as emitter option. See [readme](https://github.com/Azure/autorest.java/blob/main/readme.md).

## 0.6.1 (2023-04-14)

Compatible with compiler 0.43.

- Supported `internal` from typespec-client-generator-core.

## 0.6.0 (2023-04-12)

Compatible with compiler 0.43.

- Supported `examples-directory` as emitter option. Follow same pattern as [typespec-autorest](https://github.com/Azure/typespec-azure/tree/main/packages/typespec-autorest#examples-directory).
- Supported `enable-sync-stack` as emitter option. Currently default to `false`.

## 0.5.1 (2023-03-29)

Compatible with compiler 0.42.

## 0.5.0 (2023-03-16)

Compatible with compiler 0.42 (now `@typespec/compiler`).

## 0.4.2 (2023-03-07)

Compatible with compiler 0.40.

- Supported type `url` in path or query parameter, to skip URL encoding.
- Supported `protocolAPI` from cadl-dpg.

## 0.4.1 (2023-02-20)

Compatible with compiler 0.40.

- Supported `QueryParameterOptions` on `query` in Cadl source.

## 0.4.0 (2023-02-10)

Compatible with compiler 0.40.

## 0.3.1 (2023-02-08)

Compatible with compiler 0.39.

- Supported `projectedName` in Cadl source.
- Generating `ClientTestBase` is the default behavior.

## 0.3.0 (2023-02-01)

Compatible with compiler 0.39.

## 0.2.2 (2023-01-19)

Compatible with compiler 0.38.

- (Preview) Supported `Union` in Cadl source.

## 0.2.1 (2023-01-09)

Compatible with compiler 0.38.

- (Preview) Supported payload flatten, via spread of alias in Cadl source.
- (Preview) Supported options pattern for API parameters. Threshold is 6.

## 0.2.0 (2022-12-13)

Compatible with compiler 0.38.

- Generating convenience API is the default behavior.
- `Union` in Cadl source is treated as `Unknown`.

## 0.1.1 (2022-11-30)

Compatible with compiler 0.37.

- Added "namer" option.

## 0.1.0 (2022-11-17)

Compatible with compiler 0.37.
