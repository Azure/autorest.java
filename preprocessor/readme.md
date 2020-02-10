#### Preprocessor

``` yaml
use-extension:
  "@autorest/modelerfour": "4.3.142"

pipeline:

# --- extension remodeler ---

  # "Shake the tree", and normalize the model
  modelerfour:
    input: openapi-document/multi-api/identity     # the plugin where we get inputs from
    flatten-models: true
    flatten-payloads: true
  
  # allow developer to do transformations on the code model.
  modelerfour/new-transform:
    input: modelerfour

  preprocessor:
    input: modelerfour/identity
```
