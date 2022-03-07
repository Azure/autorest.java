## Generate code with custom http exception mapping

```yaml
java: true
low-level-client: true
input-file: ../../node_modules/@microsoft.azure/autorest.testserver/swagger/head-exceptions.json
output-folder: ..
namespace: fixtures.headexceptions
license-header: MICROSOFT_MIT_SMALL
required-parameter-client-methods: true
sync-methods: all
client-side-validations: true
add-context-parameter: true
service-interface-as-public: true
default-http-exception-type: fixtures.headexceptions.models.CustomizedException
http-status-code-to-exception-type-mapping:
  404: com.azure.core.exception.ResourceNotFoundException
```
