
#### Javagen

``` yaml
use-extension:
  "@autorest/modelerfour": "4.6.199"

pipeline:

  modelerfour:
    flatten-models: true
    flatten-payloads: true

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
