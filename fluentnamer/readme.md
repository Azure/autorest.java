#### Fluentnamer

``` yaml
pass-thru:
  - model-deduplicator
  - subset-reducer

use-extension:
  "@autorest/modelerfour": "4.13.309"

pipeline:

# --- extension remodeler ---

  # "Shake the tree", and normalize the model
  modelerfour:
    input: openapi-document/multi-api/identity     # the plugin where we get inputs from
    additional-checks: false
    flatten-models: true
    flatten-payloads: true
    naming:
      override:
        api: Api
        cpk: Cpk
        sas: Sas
        url: Url
        xml: Xml
  
  # allow developer to do transformations on the code model.
  modelerfour/new-transform:
    input: modelerfour

  fluentnamer:
    input: modelerfour/identity
```
