// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.PackageCustomization;
import org.slf4j.Logger;

import java.lang.reflect.Modifier;

/**
 * This class contains the customization code to customize the AutoRest generated code for App Configuration.
 */
public class CustomizationEncodeBytes extends Customization {

    @Override
    public void customize(LibraryCustomization customization, Logger logger) {
        logger.info("Customizing the Base64urlArrayBytesProperty");

        PackageCustomization packageCustomization = customization.getPackage("com.encode.bytes.models");
        ClassCustomization classCustomization = packageCustomization.getClass("Base64urlArrayBytesProperty");

        classCustomization.getProperty("value").setModifier(Modifier.PRIVATE);

        // as stream-serialization, we no longer need these Jackson annotations
//        ConstructorCustomization constructorCustomization = classCustomization.getConstructor("Base64UrlArrayBytesProperty");
//        constructorCustomization.removeAnnotation("JsonCreator");
//        constructorCustomization = classCustomization.getConstructor("Base64UrlArrayBytesProperty");
//        constructorCustomization.addAnnotation("JsonCreator(mode=JsonCreator.Mode.DISABLED)");
//
//        classCustomization.addConstructor("@Generated private Base64UrlArrayBytesProperty() {}");
    }
}
