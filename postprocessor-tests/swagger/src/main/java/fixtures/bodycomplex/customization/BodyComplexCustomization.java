package fixtures.bodycomplex.customization;

import com.azure.autorest.postprocessor.ClassCustomization;
import com.azure.autorest.postprocessor.Customization;
import com.azure.autorest.postprocessor.LibraryCustomization;
import com.azure.autorest.postprocessor.PackageCustomization;

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
