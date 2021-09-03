## Generate code with custom polling strategy

```yaml
input-file: https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/lro.json
output-folder: ..
required-parameter-client-methods: true
polling:
  default:
    strategy: >-
      new ChainedPollingStrategy<>(java.util.Arrays.asList(
            new OperationResourcePollingStrategy<>({httpPipeline}, null, "Azure-AsyncOperation"),
            new LocationPollingStrategy<>({httpPipeline}),
            new StatusCheckPollingStrategy<>()))
```