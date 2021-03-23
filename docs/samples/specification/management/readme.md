# Sample Management Generation

Use the flag `--azure-arm` to specify you want to generate [management plane][mgmt] code. We generate management plane code with the `--fluent` flag. For more information, see our [flag index][flag_index]

### Generation
```ps
cd <this folder>
autorest readme.md --java
```
>Note: You can also generate automatically with the fluent generator instead of the java generator. To do so, add the `--fluent` to the generation example. With this, you don't need to set the `fluent` flag in this readme.

>Use e.g. `--use=@autorest/java@4.0.21` to choose a specific java extension version.

### Settings

``` yaml
input-file: https://github.com/Azure/azure-rest-api-specs/blob/master/specification/resources/resource-manager/Microsoft.Authorization/stable/2016-09-01/locks.json
namespace: com.azure.resourcemanager.locks
license-header: MICROSOFT_MIT_SMALL
azure-arm: true
fluent: lite
```

<!-- LINKS -->
[mgmt]: https://docs.microsoft.com/azure/azure-resource-manager/management/control-plane-and-data-plane#control-plane
[flag_index]: https://github.com/Azure/autorest/tree/master/docs/generate/flags.md