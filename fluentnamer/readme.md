#### Fluentnamer

``` yaml
use-extension:
  "@autorest/modelerfour": "4.10.242"

pipeline:

# --- extension remodeler ---

  # "Shake the tree", and normalize the model
  modelerfour:
    input: openapi-document/multi-api/identity     # the plugin where we get inputs from
    flatten-models: true
    flatten-payloads: true
    naming:
      preserve-uppercase-max-length: 4
  
  # allow developer to do transformations on the code model.
  modelerfour/new-transform:
    input: modelerfour

  fluentnamer:
    input: modelerfour/identity
```
