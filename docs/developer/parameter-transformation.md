# Parameter Transformation

## Overview

AutoRest extension for OpenAPI 2.0 includes [parameter grouping](https://azure.github.io/autorest/extensions/#x-ms-parameter-grouping) and [parameter flattening](https://github.com/Azure/autorest/blob/main/packages/extensions/modelerfour/readme.md#modelfour-options).

Note that parameter flattening via `payload-flattening-threshold` option is discouraged, as it could cause breaking changes.

---

TypeSpec also has syntax for flattening (called [spread](https://github.com/Azure/cadl-ranch/blob/5a6d929/packages/cadl-ranch-specs/http/parameters/spread/main.tsp#L66)).
And emitter may choose to group some parameters, for example, etag headers.

## Flattening

### ModerFour

The parameters under `parameters` and `signatureParameters` would be a mix of `Parameter` from existing parameters, and new `VirtualParameter` from the property of the flattened body parameter.

The major difference of a `VirtualParameter` is that it has additional `originalParameter` and `targetProperty`, which points to the flattened body parameter, and the propery of that body parameter.

Sample of `VirtualParameter`:

```yaml
language:
  default:
    name: user
    description: ''
    serializedName: user
  protocol: {}
  schema: *ref_2
  originalParameter: &ref_18
  language:
    default:
    name: request
    description: ''
  protocol:
    http:
    in: body
  schema: *ref_16
  implementation: Method
  required: true
  targetProperty: *ref_17
  implementation: Method
  required: false
  nullable: false
```

### Client Model

Client model has `ClientMethod.methodTransformationDetails`.

Flatten results to a single `MethodTransformationDetail`, which represents the flattening of the single body parameter.

`MethodTransformationDetail` contains multiple `ParameterMapping`, one for each property of the flattened body parameter.

`outputParameter` represents the flattened body parameter (the real parameter).

`ParameterMapping.inputParameter` represents the parameter in method signature.
`inputParameterProperty` be `null`.
`outputParameterPropertyName` and `outputParameterProperty` be property in body parameter/schema.

For example:

```yaml
outParameter: request
parameterMappings:
- inputParameter: user
  outputParameterPropertyName: user
- inputParameter: input
  outputParameterPropertyName: input
```

```java
Map<String, Object> requestObj = new HashMap<>();
requestObj.put("user", user);
requestObj.put("input", input);
BinaryData request = BinaryData.fromObject(requestObj);
```

## Grouping

### ModerFour

First, the grouping will result in a `GroupSchema` and `GroupProperty`s.

`GroupSchema` can be think of a simpler `ObjectSchema`, which contains `properties` as `GroupProperty`s.
`GroupProperty` is `Property` with an additional `originalParameter`, which points to the grouped parameter.

Second, the new parameter (calling it grouping parameter) representing the grouping (whose `schema` is `GroupSchema`) is a `Parameter`, which is added to both `parameters` and `signatureParameters` (as it is in method signature).

The parameters been grouped (calling them grouped parameters) exists under `parameters`, but not under `signatureParameters` (as it no longer be method signature). It has `groupedBy` that points to the grouping parameter above.

Sample of `GroupSchema` with `GroupProperty` and grouped `Parameter`:

```yaml
language:
  default:
    name: RequestConditions
    description: >-
      Specifies HTTP options for conditional requests based on
      modification time.
    namespace: Cadl.SpecialHeaders
  java:
    namespace: com.azure.core.http
protocol: {}
type: group
usage:
  - input
  - convenience-api
properties:
  - language:
      default:
        name: ifMatch
        description: >-
          The request should only proceed if an entity matches this
          string.
    protocol: {}
    schema: *ref_1
    serializedName: If-Match
    originalParameter:
      - &ref_33
        language:
          default:
            name: ifMatch
            description: >-
              The request should only proceed if an entity matches this
              string.
            serializedName: If-Match
        protocol:
          http: &ref_40
            in: header
        schema: *ref_1
        implementation: Method
        required: false
        nullable: false
        groupedBy: &ref_7
          language:
            default:
              name: RequestConditions
              description: >-
                Specifies HTTP options for conditional requests based on
                modification time.
          protocol: {}
          schema: *ref_6
          implementation: Method
          required: false
          nullable: true
    required: false
    nullable: true
    readOnly: false
```

### Client Model

Grouping results in multiple `MethodTransformationDetail` under `ClientMethod`, each represents mapping from property of a grouping parameter to a grouped parameter.

`outputParameter` represents the grouped parameter (the real parameter).

`ParameterMapping.inputParameter` represents the grouping parameter (the parameter in method signature).
`inputParameterProperty` represents the property of that grouping parameter/schema, i.e. the `GroupProperty`.
`outputParameterPropertyName` and `outputParameterProperty` be `null`.

For example:

```yaml
outParameter: ifMatch
parameterMappings:
- inputParameter: requestConditions
  inputParameterProperty: ifMatch
```

```java
String ifMatch = requestConditions == null ? null : requestConditions.getIfMatch();
```

## Flattening then Grouping

TODO
