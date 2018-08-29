# Fluent Test

> see https://aka.ms/autorest

This is the AutoRest configuration file for Fluent test SDKs.

### Basic Information

``` yaml
openapi-type: arm
tag: testpackage
```

### Tag: testpackage

``` yaml $(tag) == 'testpackage'
input-file:
- create_update_with_nonstring_path_parameters.json
```

---
# Code Generation

### Java multi-api

``` yaml $(java) && $(multiapi)
batch:
  - tag: testpackage
```

### Tag: testpackage and java

``` yaml $(tag)=='testpackage' && $(java) && $(multiapi)
java:
  azure-arm: true
  fluent: true
  license-header: MICROSOFT_MIT_NO_CODEGEN
  payload-flattening-threshold: 1
  namespace: com.fluent.gencode.create_update_with_nonstring_path_parameters
  output-folder: $(fluent-test-folder)/arm/code/create_update_with_nonstring_path_parameters
regenerate-manager: true
generate-interface: true
```
