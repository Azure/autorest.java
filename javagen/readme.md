#### Javagen

``` yaml
use-extension:
  "@autorest/modelerfour": "4.6.200"

pipeline:

# --- extension remodeler ---
  javagenfluent:
    scope: fluentnamer
    input: fluentnamer
    output-artifact: java-files
  
  javagen:
    scope: basic
    input: preprocessor
    output-artifact: java-files
  
  javagen/emitter:
    input: javagen
    scope: scope-javagen/emitter

  javagenfluent/emitter:
    input: javagenfluent
    scope: scope-javagenfluent/emitter

scope-javagen/emitter:
    input-artifact: java-files
    output-uri-expr: $key

scope-javagenfluent/emitter:
    input-artifact: java-files
    output-uri-expr: $key

output-artifact: java-files
```
