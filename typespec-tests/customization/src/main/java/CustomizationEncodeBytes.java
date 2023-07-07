// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.PackageCustomization;
import org.slf4j.Logger;

import java.util.Arrays;

/**
 * This class contains the customization code to customize the AutoRest generated code for App Configuration.
 */
public class CustomizationEncodeBytes extends Customization {

    @Override
    public void customize(LibraryCustomization customization, Logger logger) {
        logger.info("Customizing the Base64UrlArrayBytesProperty, add default private constructor");
        PackageCustomization packageCustomization = customization.getPackage("com.encode.bytes.models");
        ClassCustomization classCustomization = packageCustomization.getClass("Base64UrlArrayBytesProperty");
        classCustomization.addConstructor("@Generated private Base64UrlArrayBytesProperty() {}");
    }
}
