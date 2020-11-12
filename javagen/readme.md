#### Javagen

``` yaml
use:
  - $(this-folder)/../preprocessor
  - $(this-folder)/../postprocessor

pipeline:

# --- extension remodeler ---
  javagen:
    scope: java
    input: preprocessor
    output-artifact: java-files
  
  postprocessor:
    input: javagen
    output-artifact: java-files
  
  postprocess/emitter:
    input: postprocessor
    scope: scope-javagen/emitter

scope-javagen/emitter:
    input-artifact: java-files
    output-uri-expr: $key

output-artifact: java-files
```
