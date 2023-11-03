#### Javagen

``` yaml
pass-thru:
  - model-deduplicator
  - subset-reducer

use-extension:
  "@autorest/modelerfour": "4.26.2"
  
use: $(this-folder)/../postprocessor

pipeline:

# --- extension remodeler ---

  # "Shake the tree", and normalize the model
  modelerfour:
    input: openapi-document/multi-api/identity     # the plugin where we get inputs from

  # allow developer to do transformations on the code model.
  modelerfour/new-transform:
    input: modelerfour

  javagen:
    scope: java
    input: modelerfour/identity
    output-artifact: java-files
  
  postprocessor:
    input: javagen
    output-artifact: java-files
  
  postprocess/emitter:
    input: postprocessor
    scope: scope-javagen/emitter

scope-javagen/emitter:
    input-artifact: java-files
    output-uri-expr: $key

output-artifact: java-files

modelerfour:
  seal-single-value-enum-by-default: true

  skip-special-headers:
    - Repeatability-Request-ID
    - Repeatability-First-Sent
```

```yaml !$(data-plane)
modelerfour:
  flatten-models: true
  flatten-payloads: true
  group-parameters: true
```

```yaml $(data-plane)
modelerfour:
  flatten-models: false
  flatten-payloads: false
  group-parameters: false

  lenient-model-deduplication: true

  naming:
    choiceValue: upper
    preserve-uppercase-max-length: 2
    override:
      ip: Ip
      id: Id

require:
  - $(this-folder)data-plane.md
```

```yaml $(data-plane) && $(sdk-integration)
regenerate-pom: true
```
