import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.PackageCustomization;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import org.slf4j.Logger;

import java.util.UUID;

public class BodyComplexCustomization extends Customization {
    @Override
    public void customize(LibraryCustomization customization, Logger logger) {
        PackageCustomization implementationModels = customization.getPackage("fixtures.bodycomplex.implementation.models");

        ClassCustomization dotSalmon = implementationModels.getClass("DotSalmon");
        dotSalmon.addImports("com.fasterxml.jackson.annotation.JsonProperty");
        dotSalmon.getProperty("iswild").removeAnnotation("JsonProperty").addAnnotation("@JsonProperty(value = \"isWild\")");
        dotSalmon.getMethod("setIswild")
            .rename("setWild")
            .setReturnType("void", null)
            .getJavadoc()
            .removeReturn();
        dotSalmon.getMethod("iswild")
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
        readonlyObj.customizeAst(ast -> {
            ast.addImport(UUID.class);

            ClassOrInterfaceDeclaration clazz = ast.getClassByName("ReadonlyObj").get();
            MethodDeclaration getter = clazz.getMethodsByName("getId").get(0);
            getter.setType(UUID.class).setBody(new BlockStmt().addStatement("return UUID.fromString(id);"));
            getter.setJavadocComment(StaticJavaParser.parseJavadoc("/**\n * Get the ID of the object.\n *\n"
                + " * @return The ID value\n */"));


            MethodDeclaration setter = clazz.addMethod("setId").setType("ReadonlyObj")
                .addParameter(String.class, "id");
            setter.setBody(new BlockStmt().addStatement("this.id = id;").addStatement("return this;"));
            setter.setJavadocComment(StaticJavaParser.parseJavadoc("/**\n * Set the ID of the object.\n *\n"
                + " * @param id The ID value\n * @return The current ReadonlyObj instance\n */"));
        });

        PackageCustomization root = customization.getPackage("fixtures.bodycomplex");
        ClassCustomization arrayClient = root.getClass("ArrayClient");
        arrayClient.getJavadoc().setDescription("The sync client containing Array operations.");
        arrayClient.getMethod("putValid").getJavadoc()
            .setReturn("The ArrayClient itself")
            .setSince("1.0.0-beta.1")
            .addSee("ArrayAsyncClient#putValid(ArrayWrapper)");
        arrayClient.getMethod("putValid").setReturnType("ArrayClient", "this").setModifier(0)
            .removeAnnotation("ServiceMethod");
    }
}
