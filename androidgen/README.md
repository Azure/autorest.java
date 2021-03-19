#### Androidgen

``` yaml
use: 
  - $(this-folder)/../preprocessor
  - $(this-folder)/../postprocessor
  
pipeline:
# --- extension remodeler ---
  androidgen:
    scope: android
    input: preprocessor
    output-artifact: java-files
    
  postprocessor:
    input: androidgen
    output-artifact: java-files
  
  androidgen/emitter:
    input: androidgen
    scope: scope-androidgen/emitter
scope-androidgen/emitter:
    input-artifact: java-files
    output-uri-expr: $key
output-artifact: java-files
```