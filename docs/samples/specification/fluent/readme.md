# Sample Management Generation

Use the flag `--azure-arm` to specify you want to generate [management plane][mgmt] code, ad the flag `--fluent` to show you want a fluent mgmt plane SDK. For more information, see our [flag index][flag_index]

### Settings

``` yaml
input-file: ../../../../node_modules/@microsoft.azure/autorest.testserver/swagger/head.json
namespace: com.azure.fluent.sample
license-header: MICROSOFT_MIT_NO_VERSION
azure-arm: true
```

<!-- LINKS -->
[mgmt]: https://docs.microsoft.com/en-us/azure/azure-resource-manager/management/control-plane-and-data-plane#control-plane
[flag_index]: https://github.com/Azure/autorest/tree/master/docs/generate/flags.md