# Release History

## 0.8.6 (2023-07-20)

Compatible with compiler 0.46.

- Operation which refers `Union` type is treated as protocol API, i.e. with `convenientAPI(false)`.

## 0.8.6 (2023-07-18)

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
