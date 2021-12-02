## Generate code
```yaml
input-file: ./specification/bodystring_updated.json
java: true
output-folder: ../../generated
required-parameter-client-methods: true
generate-client-as-impl: true
namespace: fixtures.bodystring
generate-client-interfaces: false
sync-methods: all
generate-sync-async-clients: true
license-header: MICROSOFT_MIT_SMALL
add-context-parameter: true
models-subpackage: implementation.models
context-client-method-parameter: true
regenerate-pom: false
low-level-client: true
```