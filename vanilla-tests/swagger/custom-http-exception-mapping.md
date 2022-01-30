## Generate code with custom http exception mapping

```yaml
java: true
input-file: https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/head-exceptions.json
output-folder: ..
namespace: fixtures.customhttpexceptionmapping
license-header: MICROSOFT_MIT_SMALL
required-parameter-client-methods: true
sync-methods: all
client-side-validations: true
add-context-parameter: true
service-interface-as-public: true
default-http-exception-type: com.azure.core.exception.ResourceNotFoundException
http-status-code-to-exception-type-mapping:
  404: com.azure.core.exception.ResourceExistsException
```