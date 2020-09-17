package fixtures.bodycomplex.customization;

import com.azure.autorest.postprocessor.Customization;

import java.util.Map;

public class BodyComplexCustomization extends Customization {
    public BodyComplexCustomization(Map<String, String> files) {
        super(files);
    }

    @Override
    public void customize() {
        renameClass("fixtures.bodycomplex.implementation.models", "Goblinshark", "GoblinShark");
    }
}
