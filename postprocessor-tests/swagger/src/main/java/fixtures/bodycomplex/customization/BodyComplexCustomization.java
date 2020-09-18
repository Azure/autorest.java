package fixtures.bodycomplex.customization;

import com.azure.autorest.postprocessor.Customization;
import com.azure.autorest.postprocessor.LibraryCustomization;
import com.azure.autorest.postprocessor.ModelCustomization;
import com.azure.autorest.postprocessor.PackageCustomization;

import java.util.Map;

public class BodyComplexCustomization extends Customization {
    @Override
    public void customize(LibraryCustomization customization) {
        PackageCustomization implementationModels = customization.getPackage("fixtures.bodycomplex.implementation.models");
        implementationModels.renameClass("Goblinshark", "GoblinShark");

        ModelCustomization dotSalmon = implementationModels.getModel("DotSalmon");
        dotSalmon.renameProperty("iswild", "isWild");
        dotSalmon.renameMethod("setIsWild", "setWild");
    }
}
