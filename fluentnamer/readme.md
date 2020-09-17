### Settings

| Option      | Description |
| ----------- | ----------- |
| `--fluent` | Enum. LITE for Fluent Lite; PREMIUM for Fluent Premium. Case insensitive. Default to PREMIUM if provided as other values. |
| `--track1-naming` | Boolean. Use track1 naming style (`withFoo` / `foo` as setter / getter). |
| `--add-inner` | CSV. Treat as inner class (append `Inner` to class name). |
| `--add-inner` | CSV. Exclude from inner classes. |
| `--name-for-ungrouped-operations` | String. Name for ungrouped operation group. |
| `--resource-property-as-subresource` | Boolean, experimental. Automatically correct input-only resource type as `SubResource`. |

#### Fluentnamer

``` yaml
pass-thru:
  - model-deduplicator
  - subset-reducer

use-extension:
  "@autorest/modelerfour": "4.15.375"

pipeline:

# --- extension remodeler ---

  # "Shake the tree", and normalize the model
  modelerfour:
    input: openapi-document/multi-api/identity     # the plugin where we get inputs from
    flatten-models: true
    flatten-payloads: true
    naming:
      choiceValue: upper
      preserve-uppercase-max-length: 2
      override:
        ip: Ip
        id: Id
  
  # allow developer to do transformations on the code model.
  modelerfour/new-transform:
    input: modelerfour

  fluentnamer:
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
```
