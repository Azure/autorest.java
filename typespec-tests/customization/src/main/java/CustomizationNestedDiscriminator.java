// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.ConstructorCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.PackageCustomization;
import org.slf4j.Logger;

/**
 * This class contains the customization code to customize the AutoRest generated code for App Configuration.
 */
public class CustomizationNestedDiscriminator extends Customization {

    @Override
    public void customize(LibraryCustomization customization, Logger logger) {
        logger.info("Customizing the Base64urlArrayBytesProperty");

        PackageCustomization packageCustomization = customization.getPackage("com.type.model.inheritance.nesteddiscriminator.models");
        ClassCustomization classCustomization = packageCustomization.getClass("GoblinShark");

        // ConstructorCustomization constructorCustomization = classCustomization.getConstructor("GoblinShark");
        // constructorCustomization.replaceBody("super(age, sharktype); this.sharktype = \"goblin\";");

        // classCustomization = packageCustomization.getClass("SawShark");

        // constructorCustomization = classCustomization.getConstructor("SawShark");
        // constructorCustomization.replaceBody("super(age, sharktype); this.sharktype = \"saw\";");
    }
}
