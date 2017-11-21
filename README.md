# AutoRest Java Extension
This project enables Java code generation in [AutoRest](https://github.com/Azure/AutoRest).

**GitHub Issues** for this project should be filed [here](https://github.com/Azure/autorest/issues?q=is%3Aopen+is%3Aissue+label%3Ajava).


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
