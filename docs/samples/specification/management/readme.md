# Sample Management Generation

Use the flag `--azure-arm` to specify you want to generate [management plane][mgmt] code. We generate management plane code with the `--fluent` flag. For more information, see our [flag index][flag_index]

### Generation
```ps
cd <this folder>
autorest readme.md --java
```
>Note: You can also generate automatically with the fluent generator instead of the java generator. To do so, replace the `--java` in the generation example
with `--fluent`. With this, you don't need to set the `fluent` flag to true in this readme.

### Settings

``` yaml
input-file: https://github.com/Azure/autorest.testserver/blob/master/swagger/head.json
namespace: com.azure.mgmt.sample
license-header: MICROSOFT_MIT_NO_VERSION
azure-arm: true
fluent: true
```

<!-- LINKS -->
[mgmt]: https://docs.microsoft.com/azure/azure-resource-manager/management/control-plane-and-data-plane#control-plane
[flag_index]: https://github.com/Azure/autorest/tree/master/docs/generate/flags.md