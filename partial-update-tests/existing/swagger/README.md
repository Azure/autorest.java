## Generate code
```yaml
input-file: ./specification/bodystring.json
java: true
output-folder: ../../generated/codeupdate
required-parameter-client-methods: true
generate-client-as-impl: true
namespace: fixtures.bodystring.codeupdate
generate-client-interfaces: false
sync-methods: all
generate-sync-async-clients: true
license-header: MICROSOFT_MIT_SMALL
add-context-parameter: true
models-subpackage: implementation.models
context-client-method-parameter: true
regenerate-pom: true
low-level-client: true
```