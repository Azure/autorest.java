### Code generation settings

``` yaml
input-file: https://github.com/Azure/azure-rest-api-specs/blob/main/specification/cognitiveservices/data-plane/FormRecognizer/stable/2022-08-31/FormRecognizer.json
java: true
output-folder: autorest.java/vanilla-tests
generate-client-as-impl: true
namespace: com.azure.ai.formrecognizer.documentanalysis
generate-client-interfaces: false
sync-methods: all
license-header: MICROSOFT_MIT_SMALL
add-context-parameter: true
models-subpackage: implementation.models
context-client-method-parameter: true
service-interface-as-public: true
custom-strongly-typed-header-deserialization: true
generic-response-type: true
custom-types-subpackage: models
enable-sync-stack: true
polling: {}
rp-namespace: Microsoft.CognitiveServices
```
