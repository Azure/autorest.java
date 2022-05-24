#### Preprocessor

``` yaml
pass-thru:
  - model-deduplicator
  - subset-reducer

use-extension:
  "@autorest/modelerfour": "4.23.1"

pipeline:

# --- extension remodeler ---

  # "Shake the tree", and normalize the model
  modelerfour:
    input: openapi-document/multi-api/identity     # the plugin where we get inputs from

  # allow developer to do transformations on the code model.
  modelerfour/new-transform:
    input: modelerfour

  preprocessor:
    input: modelerfour/identity

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
