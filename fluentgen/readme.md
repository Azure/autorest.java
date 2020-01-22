
#### Javagen

``` yaml
use-extension:
  "@autorest/modelerfour": "4.3.142"

pipeline:

# --- extension remodeler ---

  fluentgen:
    scope: java
    input: modelerfour/identity      # the plugin where we get inputs from
    output-artifact: java-files
  
  fluentgen/emitter:
    input: fluentgen
    scope: scope-fluentgen/emitter

scope-fluentgen/emitter:
    input-artifact: java-files
    output-uri-expr: $key
  
output-artifact: java-files
```
