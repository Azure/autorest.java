package fixtures.bodycomplex.customization;

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

        implementationModels.getClass("ReadonlyObj")
                .generateGetterAndSetter("id")
                .changeMethodReturnType("getId", "UUID", "UUID.fromString(%s)")
                .changeMethodModifier("setId", "");

        PackageCustomization root = customization.getPackage("fixtures.bodycomplex");
        root.getClass("ArrayClient")
                .changeMethodReturnType("putValid", "ArrayClient", "this");
    }
}
