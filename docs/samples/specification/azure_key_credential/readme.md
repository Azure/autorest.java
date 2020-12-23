# Sample Azure Key Credential Generation

Use the flag `--credential-types` to specify you want your credential to be of type [`AzureKeyCredential`][azure_key_credential].

### Settings

``` yaml
input-file: ../../../../node_modules/@microsoft.azure/autorest.testserver/swagger/head.json
namespace: com.azure.key.credential.sample
license-header: MICROSOFT_MIT_NO_VERSION
credential-types: AzureKeyCredential
sync-methods: all
```

<!-- LINKS -->
[azure_key_credential]: https://docs.microsoft.com/en-us/java/api/com.azure.core.credential.azurekeycredential?view=azure-java-stable
