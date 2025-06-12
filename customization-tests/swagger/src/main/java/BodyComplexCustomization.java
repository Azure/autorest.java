import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.PackageCustomization;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;
import org.slf4j.Logger;

import java.util.UUID;

public class BodyComplexCustomization extends Customization {
    @Override
    public void customize(LibraryCustomization customization, Logger logger) {
        PackageCustomization implementationModels = customization.getPackage("fixtures.bodycomplex.implementation.models");

        implementationModels.getClass("DotSalmon").customizeAst(ast -> ast.getClassByName("DotSalmon")
            .ifPresent(clazz -> {
                clazz.getAnnotationByName("JsonProperty").ifPresent(Node::remove);
                clazz.addAnnotation(StaticJavaParser.parseAnnotation("@JsonProperty(value = \"isWild\")"));

                clazz.getMethodsByName("setIsWild").forEach(method -> {
                    method.setName("setWild");
                    method.setType("void");
                    method.getJavadoc().ifPresent(javadoc -> {
                        javadoc.getBlockTags().removeIf(tag -> tag.getType() == JavadocBlockTag.Type.RETURN);
                        method.setJavadocComment(javadoc);
                    });
                    method.getBody().ifPresent(body -> body.getStatements().removeIf(Statement::isReturnStmt));
                });

                clazz.getMethodsByName("isWild").forEach(method -> method.setType("boolean"));

                clazz.addMethod("isDomestic", Modifier.Keyword.PUBLIC)
                    .addMarkerAnnotation("Deprecated")
                    .setType("boolean")
                    .setBody(StaticJavaParser.parseBlock("{ return \"US\".equalsIgnoreCase(getLocation()); }"))
                    .setJavadocComment(new Javadoc(JavadocDescription.parseText(
                        "Return if the salmon is a domestic species."))
                        .addBlockTag("return", "true if the salmon is domestic")
                        .addBlockTag("deprecated", "Removing in the next version"));
            }));

        implementationModels.getClass("CMYKColors").customizeAst(ast -> ast.getEnumByName("CMYKColors")
                .ifPresent(clazz -> clazz.getEntries().stream()
                    .filter(entry -> "CYAN".equals(entry.getNameAsString()))
                    .forEach(entry -> entry.setName("TEAL"))));

        implementationModels.getClass("ReadonlyObj").customizeAst(ast -> {
            ast.addImport(UUID.class);
            ast.getClassByName("ReadonlyObj").ifPresent(clazz -> {
                clazz.addMethod("getId", Modifier.Keyword.PUBLIC)
                    .setType("UUID")
                    .setBody(StaticJavaParser.parseBlock("{ return UUID.fromString(this.id); }"))
                    .setJavadocComment(new Javadoc(JavadocDescription.parseText("Get the ID of the object."))
                        .addBlockTag("return", "the id value."));

                clazz.addMethod("setId").setType("ReadonlyObj")
                    .addParameter("String", "id")
                    .setBody(StaticJavaParser.parseBlock("{ this.id = id; return this; }"))
                    .setJavadocComment(new Javadoc(JavadocDescription.parseText("Set the ID of the object."))
                        .addBlockTag("param", "id", "The ID value")
                        .addBlockTag("return", "The current ReadonlyObj instance"));

            });
        });

        customization.getClass("fixtures.bodycomplex", "ArrayClient").customizeAst(ast ->
            ast.getClassByName("ArrayClient").ifPresent(clazz -> {
                clazz.setJavadocComment("The sync client containing Array operations.");
                clazz.getMethodsByName("putValid").forEach(method -> {
                    method.setModifiers();
                    method.setType("ArrayClient");
                    method.getAnnotationByName("ServiceMethod").ifPresent(Node::remove);
                    method.getBody().ifPresent(body -> body.addStatement(new ReturnStmt(new ThisExpr())));
                    method.getJavadoc().ifPresent(javadoc -> {
                        javadoc.getBlockTags().stream()
                            .filter(tag -> tag.getType() == JavadocBlockTag.Type.RETURN)
                            .forEach(tag -> {
                                tag.getContent().getElements().clear();
                                tag.getContent().getElements()
                                    .addAll(JavadocDescription.parseText("The ArrayClient itself").getElements());
                            });
                        javadoc.addBlockTag("see", "ArrayAsyncClient#putValid(ArrayWrapper)")
                            .addBlockTag("since", "1.0.0-beta.1");
                        method.setJavadocComment(javadoc);
                    });
                });
            }));
    }
}
