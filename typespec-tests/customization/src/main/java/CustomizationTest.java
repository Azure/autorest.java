import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;
import org.slf4j.Logger;

/**
 * This class contains the customization code to customize the AutoRest generated code for App Configuration.
 */
public class CustomizationTest extends Customization {

    @Override
    public void customize(LibraryCustomization customization, Logger logger) {
        logger.info("Customizing the NamingClient javadoc");
        customization.getClass("tsptest.naming", "NamingClient").customizeAst(ast -> ast.getClassByName("NamingClient")
            .ifPresent(clazz -> clazz.getMethodsByName("postWithResponse").forEach(method -> method.getJavadoc()
                .ifPresent(javadoc -> {
                    javadoc.getDescription().getElements().clear();
                    javadoc.getDescription().getElements()
                        .addAll(JavadocDescription.parseText("Protocol method for POST operation.").getElements());
                    javadoc.getBlockTags().removeIf(tag -> tag.getType() == JavadocBlockTag.Type.UNKNOWN);
                    method.setJavadocComment(javadoc);
                }))));
    }
}
