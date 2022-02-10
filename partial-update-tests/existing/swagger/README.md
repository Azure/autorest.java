## Partial Update Integration Test

This integration test tests the overall process for partial update in partial-update-tests. The test will generate code based on existing folder and `bodystring_updated.json`(partial-update-tests/existing/swagger/specification/bodystring_updated.json). The existing files are in `existing` folder and the generated files are in `generated` folder. `StringOperationClient.java` covers manually code change on existing file, `EnumClient.java` covers swagger change.

## Test cases:

1. Manually add class member (field / method / constructor)

2. Manually update method signature - parameter change

3. Manually update method signature -  method accessibility level change

4. Swagger update api - method parameter name change

5. Swagger add new api

6. Swagger remove api

  
## Generate code
```yaml
input-file: ./specification/bodystring_updated.json
java: true
output-folder: ../../generated
namespace: fixtures.bodystring
low-level-client: true
partial-update: true
generate-builder-per-client: false
```
