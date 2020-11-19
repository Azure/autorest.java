# Azure Cognitive Service - Form Recognizer for Java

> see https://aka.ms/autorest

### Setup
```ps
Fork and clone https://github.com/Azure/autorest.java 
git checkout v4
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
input-file: https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-complex.json
java: true
output-folder: ..\
required-parameter-client-methods: true
generate-client-as-impl: true
namespace: fixtures.bodycomplex
generate-client-interfaces: false
sync-methods: all
generate-sync-async-clients: true
license-header: MICROSOFT_MIT_SMALL
add-context-parameter: true
models-subpackage: implementation.models
context-client-method-parameter: true
customization-class: BodyComplexCustomization
```

### Customization
```java
public class BodyComplexCustomization extends Customization {
    @Override
    public void customize(LibraryCustomization customization) {
        PackageCustomization implementationModels = customization.getPackage("fixtures.bodycomplex.implementation.models");
        implementationModels.renameClass("Goblinshark", "GoblinShark");

        implementationModels.getClass("DotSalmon")
                .renameProperty("iswild", "isWild")
                .renameMethod("setIsWild", "setWild")
                .changeMethodReturnType("isWild", "boolean", "%s")
                .changeMethodReturnType("setWild", "void", null);

        implementationModels.getClass("CMYKColors")
                .renameEnumMember("CYAN", "TEAL");

        ClassCustomization readonlyObj = implementationModels.getClass("ReadonlyObj")
                .generateGetterAndSetter("id")
                .changeMethodReturnType("getId", "UUID", "UUID.fromString(%s)")
                .changeMethodModifier("setId", "");
        readonlyObj.methodJavadoc("getId").setDescription("Get the ID of the object.");
        readonlyObj.methodJavadoc("setId").setDescription("Set the ID of the object.")
                .setReturn("The current ReadonlyObj instance")
                .setParam("id", "The ID value");

        PackageCustomization root = customization.getPackage("fixtures.bodycomplex");
        ClassCustomization arrayClient = root.getClass("ArrayClient")
                .changeMethodReturnType("putValid", "ArrayClient", "this");
        arrayClient.classJavadoc().setDescription("The sync client containing Array operations.");
        arrayClient.methodJavadoc("putValid").setReturn("The ArrayClient itself");
    }
}
```