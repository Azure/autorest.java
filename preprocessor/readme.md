#### Preprocessor

``` yaml
pass-thru:
  - model-deduplicator
  - subset-reducer

use-extension:
  "@autorest/modelerfour": "4.18.1"

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
```

```yaml !$(low-level-client)
pipeline:
  modelerfour:
    flatten-models: true
    flatten-payloads: true
    group-parameters: true
```

```yaml $(low-level-client)
generate-client-interfaces: false
generate-client-as-impl: true
generate-sync-async-clients: true
add-context-parameter: true
context-client-method-parameter: true
sync-methods: all
polling: {}

pipeline:
  modelerfour:
    flatten-models: false
    flatten-payloads: false
    group-parameters: false
    lenient-model-deduplication: true
```

```yaml $(low-level-client) && $(sdk-integration)
regenerate-pom: true
```
