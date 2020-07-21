#### Androidgen

``` yaml
use: $(this-folder)/../preprocessor

pipeline:

# --- extension remodeler ---
  javagen:
    scope: java
    input: preprocessor
    output-artifact: java-files
  
  javagen/emitter:
    input: androidgen
    scope: scope-androidgen/emitter

scope-androidgen/emitter:
    input-artifact: java-files
    output-uri-expr: $key

output-artifact: java-files
```
