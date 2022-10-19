## Generate code with custom polling strategy

```yaml
java: true
input-file: ../../node_modules/@microsoft.azure/autorest.testserver/swagger/lro.json
output-folder: ..
namespace: fixtures.lro
license-header: MICROSOFT_MIT_SMALL
required-parameter-client-methods: true
sync-methods: all
client-side-validations: true
polling:
  default:
    strategy: >-
      new ChainedPollingStrategy<>(java.util.Arrays.asList(
            new OperationResourcePollingStrategy<>({httpPipeline}, null, "Azure-AsyncOperation", {context}),
            new LocationPollingStrategy<>({httpPipeline}, null, {context}),
            new StatusCheckPollingStrategy<>()))
```