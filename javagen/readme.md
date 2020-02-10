#### Javagen

``` yaml
use-extension:
  "@autorest/modelerfour": "4.3.142"

pipeline:

# --- extension remodeler ---
  javagen:
    input: preprocessor
    output-artifact: java-files
  
  javagen/emitter:
    input: javagen
    scope: scope-javagen/emitter

scope-javagen/emitter:
    input-artifact: java-files
    output-uri-expr: $key

output-artifact: java-files
```
