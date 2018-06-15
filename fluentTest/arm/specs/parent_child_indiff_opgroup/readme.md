# Fluent Test

> see https://aka.ms/autorest

This is the AutoRest configuration file for Fluent test SDKs.

This swagger describes two resource types (dogs and it's child resource puppies).
Operations on these two types are grouped in it's own operation groups.
Operations on Dogs are grouped under operation group with name Dogs and Operations
on Puppies are grouped under another operation group with name Puppies.


### Basic Information

``` yaml
openapi-type: arm
tag: testpackage
```

### Tag: testpackage

``` yaml $(tag) == 'testpackage'
input-file:
- parent_child_indiff_opgroup.json
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
  namespace: com.fluent.gencode.parent_child_indiff_opgroup
  output-folder: $(fluent-test-folder)/arm/code/parent_child_indiff_opgroup
regenerate-manager: true
generate-interface: true
```
