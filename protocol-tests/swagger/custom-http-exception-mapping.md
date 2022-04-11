## Generate code with custom http exception mapping

```yaml
java: true
data-plane: true
input-file: ../../node_modules/@microsoft.azure/autorest.testserver/swagger/head-exceptions.json
output-folder: ..
namespace: fixtures.headexceptions

default-http-exception-type: fixtures.headexceptions.models.CustomizedException
http-status-code-to-exception-type-mapping:
  404: com.azure.core.exception.ResourceNotFoundException
```
