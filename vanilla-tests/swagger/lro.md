## Generate code with custom polling strategy

```yaml
java: true
input-file: https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/lro.json
output-folder: ..
namespace: fixtures.lro
required-parameter-client-methods: true
sync-methods: all
client-side-validations: true
add-context-parameter: true
polling:
  default:
    strategy: >-
      new ChainedPollingStrategy<>(java.util.Arrays.asList(
            new OperationResourcePollingStrategy<>({httpPipeline}, null, "Azure-AsyncOperation"),
            new LocationPollingStrategy<>({httpPipeline}),
            new StatusCheckPollingStrategy<>()))
```