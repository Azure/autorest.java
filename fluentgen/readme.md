#### Fluentgen

``` yaml
use-extension:
  "@autorest/modelerfour": "4.6.200"

pipeline:

  fluentgen:
    scope: java
    input: fluentnamer
    output-artifact: java-files
  
  fluentgen/emitter:
    input: fluentgen
    scope: scope-fluentgen/emitter

scope-fluentgen/emitter:
    input-artifact: java-files
    output-uri-expr: $key
  
output-artifact: java-files
```
