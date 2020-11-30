### Java Code Formatter

Formatter depends on a customized [google-java-format](https://github.com/weidongxu-microsoft/google-java-format?organization=weidongxu-microsoft&organization=weidongxu-microsoft), which requires JRE 11.

#### Fluentgen

``` yaml
use: $(this-folder)/../fluentnamer
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
