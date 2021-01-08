# <img align="center" src="../images/logo.png">  Generating a Fluent Java Client

The Java generator offers the ability to generate fluent interfaces for [management plane][mgmt] SDKs.
Fluent interfaces look like [this][fluent_ex], and can be enabled with the flag `--fluent`. When
generating a fluent library, you have access to [additional flags][fluent_flags]

<!-- LINKS -->
[mgmt]: https://docs.microsoft.com/azure/azure-resource-manager/management/control-plane-and-data-plane#control-plane
[fluent_ex]: https://github.com/Azure/azure-sdk-for-java/tree/master/sdk/resourcemanager#fluent-interface
[fluent_flags]: ../../readme.md#additional-settings-for-fluent