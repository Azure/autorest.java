
#### Javagen

``` yaml
use-extension:
  "@autorest/modelerfour": "~4.0.29"

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
    input: modelerfour/new-transform      # the plugin where we get inputs from
    output-artifact: java-files
    
  javagen/emitter:
    input: javagen
    scope: scope-javagen/emitter

scope-javagen/emitter:
    input-artifact: java-files
    output-uri-expr: $key
  
output-artifact: java-files
```
