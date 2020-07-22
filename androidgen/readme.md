#### Androidgen

``` yaml
use: $(this-folder)/../preprocessor

pipeline:

# --- extension remodeler ---
  androidgen:
    scope: android
    input: preprocessor
    output-artifact: java-files
  
  androidgen/emitter:
    input: androidgen
    scope: scope-androidgen/emitter

scope-androidgen/emitter:
    input-artifact: java-files
    output-uri-expr: $key

output-artifact: java-files
```
