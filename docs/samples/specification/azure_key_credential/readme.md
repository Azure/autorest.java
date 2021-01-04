# Sample Azure Key Credential Generation

Use the flag `--credential-types` to specify you want your credential to be of type [`AzureKeyCredential`][azure_key_credential].

### Settings

``` yaml
input-file: https://github.com/Azure/autorest.testserver/blob/master/swagger/head.json
namespace: com.azure.key.credential.sample
license-header: MICROSOFT_MIT_NO_VERSION
credential-types: AzureKeyCredential
sync-methods: all
```

<!-- LINKS -->
[azure_key_credential]: https://docs.microsoft.com/java/api/com.azure.core.credential.azurekeycredential?view=azure-java-stable
