# AutoRest Java Extension
This project enables Java code generation in [AutoRest](https://github.com/Azure/AutoRest).

**GitHub Issues** for this project should be filed [here](https://github.com/Azure/autorest/issues?q=is%3Aopen+is%3Aissue+label%3Ajava).


# Java AutoRest Generator Command Line Arguments

- *--java.client-type-prefix*: The prefix to add to the beginning on the Service and Method Group clients that are generated.
  - Default: Nothing
  - Without Argument: `public class MyServiceClient { ... }`
  - With Argument (`zzz`): `public class zzzMyServiceClient { ... }`
- *--java.generate-client-interfaces*: Whether or not to generate interfaces for Service and Method Group clients.
  - Default: `true`
  - Without Argument: `public class MyServiceClientImpl extends ServiceClient implements MyServiceClient { ... }`
  - With Argument (`false`): `public class MyServiceClient extends ServiceClient { ... }`
- *--java.implementation-subpackage*: The sub-package where service and method group clients, Manager, Page, and XmlWrapper classes will be generated.
  - Default: `implementation`
  - Without Argument:
    ```java
    package com.fruit.service.implementation;
    public class ApplesServiceClient { ... }
    ```
  - With Argument (`spam`):
    ```java
    package com.fruit.service.spam;
    public class ApplesServiceClient { ... }
    ```
- *--java.models-subpackage*: The sub-package where Enums, Exceptions, and Model types will be generated.
  - Default: `models`
  - Without Argument:
  ```java
  package com.fruit.service.models;
  public class MyAppleClass { ... }
  ```
  - With Argument: (`spam`):
  ```java
  package com.fruit.service.spam;
  public class MyAppleClass { ... }
  ```
- *--java.non-null-annotations*: Whether or not to add `@NonNull` (from the `io.reactivex.annotations` package) annotations to each required parameter in client methods.
  - Default: `true`
  - Without Argument: `int Plus(Integer val1, Integer val2)`
  - With Argument (`true`): `int Plus(@NonNull Integer val1, @NonNull Integer val2)`
- *--java.required-parameter-client-methods*: Whether or not Service and Method Group client method overloads that omit optional parameters will be created.
  - Default: `true`
- *--java.add-context-parameter*: Whether to always add an extra leading parameter of type com.microsoft.rest.v2.Context to each operation.
  - Default: `false`

# Contributing

This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.microsoft.com.

When you submit a pull request, a CLA-bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., label, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.

# IMPORTANT NOTE

This project uses a git submodule for dependent code. When cloning this repository use `git clone --recursive ...` or perform a `git submodule init ` after the project is cloned.



# AutoRest extension configuration

``` yaml
use-extension:
  "@microsoft.azure/autorest.modeler": "2.3.38"

pipeline:
  java/imodeler1:
    input: openapi-document/identity
    output-artifact: code-model-v1
    scope: java
  java/commonmarker:
    input: imodeler1
    output-artifact: code-model-v1
  java/cm/transform:
    input: commonmarker
    output-artifact: code-model-v1
  java/cm/emitter:
    input: transform
    scope: scope-cm/emitter
  java/generate:
    plugin: java
    input: cm/transform
    output-artifact: source-file-java
  java/transform:
    input: generate
    output-artifact: source-file-java
    scope: scope-transform-string
  java/emitter:
    input: transform
    scope: scope-java/emitter

scope-java/emitter:
  input-artifact: source-file-java
  output-uri-expr: $key

output-artifact:
- source-file-java
```
