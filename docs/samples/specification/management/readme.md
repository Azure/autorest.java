# Sample Management Generation

Use the flag `--azure-arm` to specify you want to generate [management plane][mgmt] code. This forces the `fluent` flag to be true. For more information, see our [flag index][flag_index]

### Settings

``` yaml
input-file: https://github.com/Azure/autorest.testserver/blob/master/swagger/head.json
namespace: com.azure.mgmt.sample
license-header: MICROSOFT_MIT_NO_VERSION
azure-arm: true
```

<!-- LINKS -->
[mgmt]: https://docs.microsoft.com/azure/azure-resource-manager/management/control-plane-and-data-plane#control-plane
[flag_index]: https://github.com/Azure/autorest/tree/master/docs/generate/flags.md