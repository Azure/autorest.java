#### Fluentgen

``` yaml
pass-thru:
  - model-deduplicator
  - subset-reducer

use-extension:
  "@autorest/modelerfour": "4.26.2"

pipeline:

# --- extension remodeler ---

  # "Shake the tree", and normalize the model
  modelerfour:
    input: openapi-document/multi-api/identity     # the plugin where we get inputs from
  
  # allow developer to do transformations on the code model.
  modelerfour/new-transform:
    input: modelerfour
    
  fluentgen:
    scope: java
    input: modelerfour/identity
    naming:
      override:
        eTag: etag
        userName: username
        metaData: metadata
        timeStamp: timestamp
        hostName: hostname
        webHook: webhook
        coolDown: cooldown
        resourceregion: resourceRegion
        sTag: stag
        tagname: tagName
        tagvalue: tagValue
    output-artifact: java-files
  
  fluentgen/emitter:
    input: fluentgen
    scope: scope-fluentgen/emitter

scope-fluentgen/emitter:
    input-artifact: java-files
    output-uri-expr: $key
  
output-artifact: java-files

modelerfour:
  flatten-models: false
  flatten-payloads: false

  additional-checks: true
  lenient-model-deduplication: false
  remove-empty-child-schemas: false
  seal-single-value-enum-by-default: true

  naming:
    choiceValue: upper
    preserve-uppercase-max-length: 2
    override:
      ip: Ip
      id: Id

include-x-ms-examples-original-file: true

license-header: MICROSOFT_MIT_SMALL
regenerate-pom: true
sync-methods: all
client-side-validations: true
client-logger: true
generate-client-interfaces: true
required-parameter-client-methods: true
client-flattened-annotation-target: none
null-byte-array-maps-to-empty-array: true
graal-vm-config: true
stream-style-serialization: true
use-object-for-unknown: true
enable-sync-stack: true
```
