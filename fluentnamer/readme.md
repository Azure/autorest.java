### Settings

| Option      | Description |
| ----------- | ----------- |
| `--fluent` | Enum. `LITE` for Fluent Lite; `PREMIUM` for Fluent Premium. Case insensitive. Default to `PREMIUM` if provided as other values. |
| `--pom-file` | String. Name for Maven POM file. Default to `pom.xml`. |
| `--package-version` | String. Version number for Maven artifact. Default to `1.0.0-beta.1`. |
| `--service-name` | String. Service name used in Manager class and other documentations. If not provided, service name is deduced from `title` configure (from swagger or readme). |
| `--sdk-integration` | Boolean. Integrate to `azure-sdk-for-java`.  Default to `false`. |
| `--track1-naming` | Boolean. Use track1 naming style (`withFoo` / `foo` as setter / getter). Default to `true`. |
| `--add-inner` | CSV. Treat as inner class (append `Inner` to class name). |
| `--remove-inner` | CSV. Exclude from inner classes. |
| `--name-for-ungrouped-operations` | String. Name for ungrouped operation group. |
| `--resource-property-as-subresource` | Boolean, experimental. Automatically correct input-only resource type as `SubResource`. |

#### Fluentnamer

``` yaml
pass-thru:
  - model-deduplicator
  - subset-reducer

use-extension:
  "@autorest/modelerfour": "4.15.423"

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
