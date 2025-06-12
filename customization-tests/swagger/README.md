# Azure Cognitive Service - Form Recognizer for Java

> see https://aka.ms/autorest

### Setup
```ps
Fork and clone https://github.com/Azure/autorest.java 
git checkout main
git submodule update --init --recursive
mvn package -Dlocal
npm install
npm install -g autorest
```

### Generation
```ps
cd <swagger-folder>
autorest --java --use=C:/work/autorest.java
```

### Code generation settings
```yaml
input-file: https://raw.githubusercontent.com/Azure/autorest.testserver/main/swagger/body-complex.json
java: true
output-folder: ..\
required-parameter-client-methods: true
generate-client-as-impl: true
namespace: fixtures.bodycomplex
generate-client-interfaces: false
sync-methods: all
generate-sync-async-clients: true
license-header: MICROSOFT_MIT_SMALL
models-subpackage: implementation.models
customization-class: src/main/java/BodyComplexCustomization.java
```

### Rename goblinshark to GoblinShark

```yaml
directive:
  - from: swagger-document
    where: $.definitions.goblinshark
    transform: >
      $["x-ms-client-name"] = "GoblinShark";
```

### Rename DotSalmon.iswild to isWild

```yaml
directive:
  - from: swagger-document
    where: $.definitions.DotSalmon
    transform: >
      $.properties.iswild["x-ms-client-name"] = "isWild";
```
