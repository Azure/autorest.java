# <img align="center" src="../images/logo.png">  Initializing Your Java Client

The first step to using your generated client in code is to import and initialize your client. Our SDKs are modelled such
that the client is the main point of access to the generated code.

## Importing Your Client

You include the package from the namespace specified when generating (under flag `--namespace`). For the sake of this example,
let's say the namespace is `com.azure.pets`.

Putting this together, we import our client like so:

```java
package com.azure.pets;
```

## Initializing and Authenticating Your Client

Your client's name is detailed in the swagger, (TODO link to swagger docs), and let's say
ours is called `PetsClient`. We use builders to build our client, with each builder being the service client's name + suffix `Builder()`.
The builder takes in any number of parameters, and ultimately calls `buildClient()`, which ultimately what returns the built client.

```java
package com.azure.pets;

public static void main(String args[])
{
    PetsClient client = new PetsClientBuilder().buildClient();
}
```

You can also install your client with a credential, using the flag `--credential-types`. If you generate with `--credential-types=TokenCredential`, your
client will take in an [Azure Active Directory (AAD) token credential][aad_authentication]. We always recommend
using a [credential type][identity_credentials] obtained from the [`com.azure.identity`][azure_identity_library] package for AAD authentication. For this example,
we use the most common [`DefaultAzureCredential`][default_azure_credential].

As an installation note, the [`com.azure.identity`][azure_identity_library] is not listed as a dependency in the `pom` we generate
(see `--regenerate-pom` in our [flag index][flag_index] for more information), so you would need to install this library separately.

```java
package com.azure.pets;

import com.azure.identity.DefaultAzureCredentialBuilder;

public static void main(String args[])
{
    PetsClient client = new PetsClientBuilder()
        .credential(new DefaultAzureCredentialBuilder().build())
        .buildClient();
}
```

You can also have your generated client take in an [`AzureKeyCredential`][azure_key_credential] instead. To do so, generate with flag `--credential-types=AzureKeyCredential`,
and for more information on this flag, see our [flag index][flag_index]

```java
package com.azure.pets;

import com.azure.core.credential.AzureKeyCredential;

public static void main(String args[])
{
    PetsClient client = new PetsClientBuilder()
        .credential(new AzureKeyCredential("{key}"))
        .buildClient();
}
```

Currently, we only support generating credentials of type `TokenCredential` and / or `AzureKeyCredential`.

<!-- LINKS -->
[multiapi_generation]: ../generate/multiapi.md
[azure_core_library]: https://pypi.org/project/azure-core/
[msrest_library]: https://pypi.org/project/msrest/
[azure_mgmt_core_library]: https://pypi.org/project/azure-mgmt-core/
[azure_identity_library]: https://docs.microsoft.com/en-us/java/api/com.azure.identity?view=azure-java-stable
[flag_index]: https://github.com/Azure/autorest/tree/master/docs/generate/flags.md
[aad_authentication]: https://docs.microsoft.com/en-us/azure/cognitive-services/authentication?tabs=powershell#authenticate-with-azure-active-directory
[identity_credentials]: https://github.com/Azure/azure-sdk-for-java/tree/master/sdk/identity/azure-identity#credential-classes
[default_azure_credential]: https://docs.microsoft.com/en-us/java/api/com.azure.identity.defaultazurecredential?view=azure-java-stable
[azure_key_credential]: https://docs.microsoft.com/en-us/java/api/com.azure.core.credential.azurekeycredential?view=azure-java-stable
