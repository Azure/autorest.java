## Generate code with dpg customization

```yaml
java: true
data-plane: true
input-file: ../../node_modules/@microsoft.azure/autorest.testserver/swagger/dpg-customization.json
output-folder: ..
namespace: fixtures.dpgcustomization

generate-send-request-method: true
generate-models: true

custom-types: Input,LroProduct,Product,ProductReceived
custom-types-subpackage: models
```
