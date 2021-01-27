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
import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.PackageCustomization;
import com.azure.autorest.customization.models.Modifier;

public class BodyComplexCustomization extends Customization {
    @Override
    public void customize(LibraryCustomization customization) {
        PackageCustomization implementationModels = customization.getPackage("fixtures.bodycomplex.implementation.models");
        implementationModels.getClass("Goblinshark").rename("GoblinShark");

        ClassCustomization dotSalmon = implementationModels.getClass("DotSalmon");
        dotSalmon.getProperty("iswild").rename("isWild").removeAnnotation("JsonProperty").addAnnotation("@JsonProperty(value = \"isWild\")");
        dotSalmon.getMethod("setIsWild")
                .rename("setWild")
                .setReturnType("void", null);
        dotSalmon.getMethod("isWild")
                .setReturnType("boolean", "%s");
        dotSalmon.addMethod("public boolean isDomestic() {\n" +
                "    return \"US\".equalsIgnoreCase(getLocation());\n" +
                "}")
                .addAnnotation("Deprecated")
                .getJavadoc()
                    .setDescription("Return if the salmon is a domestic species.")
                    .setReturn("true if the salmon is domestic")
                    .setDeprecated("Removing in the next version");

        implementationModels.getClass("CMYKColors")
                .renameEnumMember("CYAN", "TEAL");

        ClassCustomization readonlyObj = implementationModels.getClass("ReadonlyObj");
        readonlyObj.getProperty("id").generateGetterAndSetter();
        readonlyObj.getMethod("getId").setReturnType("UUID", "UUID.fromString(%s)");
        readonlyObj.getMethod("setId").setModifier(Modifier.PACKAGE_PRIVATE);
        readonlyObj.getMethod("getId").getJavadoc().setDescription("Get the ID of the object.");
        readonlyObj.getMethod("setId").getJavadoc().setDescription("Set the ID of the object.")
                .setReturn("The current ReadonlyObj instance")
                .setParam("id", "The ID value");

        PackageCustomization root = customization.getPackage("fixtures.bodycomplex");
        ClassCustomization arrayClient = root.getClass("ArrayClient");
        arrayClient.getMethod("putValid").setReturnType("ArrayClient", "this").setModifier(Modifier.PACKAGE_PRIVATE)
            .removeAnnotation("ServiceMethod");
        arrayClient.getJavadoc().setDescription("The sync client containing Array operations.");
        arrayClient.getMethod("putValid").getJavadoc()
                .setReturn("The ArrayClient itself")
                .setSince("1.0.0-beta.1")
                .addSee("ArrayAsyncClient#putValid(ArrayWrapper)");

        ClassCustomization cat = implementationModels.getClass("Cat");
        cat.getMethod("getColor")
                .setReturnType("byte[]", "String colorStr = %s; try { return colorStr.getBytes" +
                        "(\"UTF-8\"); } catch (UnsupportedEncodingException ex) { return colorStr.getBytes(); }", true);
    }
}
```