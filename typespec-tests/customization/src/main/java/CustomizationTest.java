import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.PackageCustomization;
import org.slf4j.Logger;

import java.util.Arrays;

/**
 * This class contains the customization code to customize the AutoRest generated code for App Configuration.
 */
public class CustomizationTest extends Customization {

    @Override
    public void customize(LibraryCustomization customization, Logger logger) {
        logger.info("Customizing the NamingClient javadoc");
        PackageCustomization packageCustomization = customization.getPackage("com.cadl.naming");
        ClassCustomization classCustomization = packageCustomization.getClass("NamingClient");
        classCustomization.getMethod("postWithResponse")
                .getJavadoc()
                .setDescription("Protocol method for POST operation.");
    }
}