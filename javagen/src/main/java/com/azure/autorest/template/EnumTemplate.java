// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Annotation;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.model.javamodel.JavaEnum;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Writes a EnumType to a JavaFile.
 */
public class EnumTemplate implements IJavaTemplate<EnumType, JavaFile> {
    private static final EnumTemplate INSTANCE = new EnumTemplate();

    protected EnumTemplate() {
    }

    public static EnumTemplate getInstance() {
        return INSTANCE;
    }

    public final void write(EnumType enumType, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();

        if (enumType.getExpandable()) {
            writeExpandableStringEnum(enumType, javaFile, settings);
        } else {
            writeEnum(enumType, javaFile, settings);
        }
    }

    private void writeExpandableStringEnum(EnumType enumType, JavaFile javaFile, JavaSettings settings) {
        Set<String> imports = new HashSet<>();
        imports.add("java.util.Collection");
        imports.add(getStringEnumImport());
        if (!settings.isStreamStyleSerialization()) {
            imports.add("com.fasterxml.jackson.annotation.JsonCreator");
        } else {
            imports.add(IOException.class.getName());

            ClassType.JSON_SERIALIZABLE.addImportsTo(imports, false);
            ClassType.JSON_WRITER.addImportsTo(imports, false);
            ClassType.JSON_READER.addImportsTo(imports, false);
            ClassType.JSON_TOKEN.addImportsTo(imports, false);
        }

        addGeneratedImport(imports);

        javaFile.declareImport(imports);
        javaFile.javadocComment(comment -> comment.description(enumType.getDescription()));

        String enumName = enumType.getName();
        String declaration = enumName + " extends ExpandableStringEnum<" + enumName + ">";
        if (settings.isStreamStyleSerialization()) {
            declaration += " implements JsonSerializable<" + enumName + ">";
        }

        javaFile.publicFinalClass(declaration, classBlock -> {
            IType elementType = enumType.getElementType();
            String typeName = elementType.getClientType().toString();
            String pascalTypeName = CodeNamer.toPascalCase(typeName);
            for (ClientEnumValue enumValue : enumType.getValues()) {
                String value = enumValue.getValue();
                classBlock.javadocComment(CoreUtils.isNullOrEmpty(enumValue.getDescription())
                    ? "Static value " + value + " for " + enumName + "."
                    : enumValue.getDescription());
                addGeneratedAnnotation(classBlock);
                classBlock.publicStaticFinalVariable(String.format("%1$s %2$s = from%3$s(%4$s)", enumName,
                    enumValue.getName(), pascalTypeName, elementType.defaultValueExpression(value)));
            }

            // ctor, marked as Deprecated
            classBlock.javadocComment(comment -> {
                comment.description("Creates a new instance of " + enumName + " value.");
                comment.deprecated(String.format("Use the {@link #from%1$s(%2$s)} factory method.", pascalTypeName, typeName));
            });

            addGeneratedAnnotation(classBlock);
            classBlock.annotation("Deprecated");
            classBlock.publicConstructor(enumName + "()", ctor -> { });

            // fromString(typeName)
            classBlock.javadocComment(comment -> {
                comment.description("Creates or finds a " + enumName + " from its string representation.");
                comment.param("name", "a name to look for");
                comment.methodReturns("the corresponding " + enumName);
            });

            addGeneratedAnnotation(classBlock);
            if (!settings.isStreamStyleSerialization()) {
                classBlock.annotation("JsonCreator");
            }

            classBlock.publicStaticMethod(String.format("%1$s from%2$s(%3$s name)", enumName, pascalTypeName, typeName),
                function -> {
                    String stringValue = (ClassType.STRING.equals(elementType)) ? "name" : "String.valueOf(name)";
                    function.methodReturn("fromString(" + stringValue + ", " + enumName + ".class)");
                });

            // values()
            classBlock.javadocComment(comment -> {
                comment.description("Gets known " + enumName + " values.");
                comment.methodReturns("known " + enumName + " values");
            });

            addGeneratedAnnotation(classBlock);
            classBlock.publicStaticMethod("Collection<" + enumName + "> values()",
                function -> function.methodReturn("values(" + enumName + ".class)"));

            if (settings.isStreamStyleSerialization()) {
                addGeneratedAnnotation(classBlock);
                classBlock.annotation("Override");
                classBlock.publicMethod("JsonWriter toJson(JsonWriter jsonWriter) throws IOException",
                    function -> function.methodReturn("jsonWriter.writeString(toString())"));

                classBlock.javadocComment(javadoc -> {
                    javadoc.description("Reads a " + enumName + " from the JSON stream.");
                    javadoc.line("<p>");
                    javadoc.description("The passed JsonReader must be positioned at a JsonToken.STRING value.");
                    javadoc.param("jsonReader", "The JsonReader being read.");
                    javadoc.methodReturns("The " + enumName + " that the JSON stream represented, may return null.");
                    javadoc.methodThrows(IOException.class.getName(), "If a " + enumName + " fails to be read from the JsonReader.");
                });
                addGeneratedAnnotation(classBlock);
                classBlock.publicStaticMethod(enumName + " fromJson(JsonReader jsonReader) throws IOException",
                    function -> function.methodReturn("fromString(jsonReader.getString(), " + enumName + ".class)"));
            }
        });
    }

    private void writeEnum(EnumType enumType, JavaFile javaFile, JavaSettings settings) {
        Set<String> imports = new HashSet<>();
        if (!settings.isStreamStyleSerialization()) {
            imports.add("com.fasterxml.jackson.annotation.JsonCreator");
            imports.add("com.fasterxml.jackson.annotation.JsonValue");
        } else {
            imports.add(IOException.class.getName());

            ClassType.JSON_SERIALIZABLE.addImportsTo(imports, false);
            ClassType.JSON_WRITER.addImportsTo(imports, false);
            ClassType.JSON_READER.addImportsTo(imports, false);
            ClassType.JSON_TOKEN.addImportsTo(imports, false);
        }

        addGeneratedImport(imports);
        IType elementType = enumType.getElementType();
        elementType.getClientType().addImportsTo(imports, false);

        javaFile.declareImport(imports);
        javaFile.javadocComment(comment -> comment.description(enumType.getDescription()));
        String declaration = enumType.getName();
        if (settings.isStreamStyleSerialization()) {
            declaration += " implements JsonSerializable<" + declaration + ">";
        }

        javaFile.publicEnum(declaration, enumBlock -> {
            for (ClientEnumValue value : enumType.getValues()) {
                enumBlock.value(value.getName(), value.getValue(), value.getDescription(), elementType);
            }

            String enumName = enumType.getName();
            String typeName = elementType.getClientType().toString();

            // This will be 'from*'.
            String converterName = enumType.getFromMethodName();

            enumBlock.javadocComment("The actual serialized value for a " + enumName + " instance.");
            enumBlock.privateFinalMemberVariable(typeName, "value");

            enumBlock.constructor(enumName + "(" +typeName + " value)", constructor -> constructor.line("this.value = value;"));

            enumBlock.javadocComment((comment) -> {
                comment.description("Parses a serialized value to a " + enumName + " instance.");
                comment.param("value", "the serialized value to parse.");
                comment.methodReturns("the parsed " + enumName + " object, or null if unable to parse.");
            });

            if (!settings.isStreamStyleSerialization()) {
                enumBlock.annotation("JsonCreator");
            }

            enumBlock.publicStaticMethod(enumName + " " + converterName + "(" + typeName + " value)", function -> {
                if (elementType.isNullable()) {
                    function.ifBlock("value == null", ifAction -> ifAction.methodReturn("null"));
                }
                function.line(enumName + "[] items = " + enumName + ".values();");
                function.block("for (" + enumName + " item : items)", foreachBlock ->
                    foreachBlock.ifBlock(createEnumJsonCreatorIfCheck(enumType), ifBlock -> ifBlock.methodReturn("item")));
                function.methodReturn("null");
            });

            if (elementType == ClassType.STRING) {
                enumBlock.javadocComment(JavaJavadocComment::inheritDoc);
                if (!settings.isStreamStyleSerialization()) {
                    enumBlock.annotation("JsonValue");
                }
                enumBlock.annotation("Override");
            } else {
                enumBlock.javadocComment(comment -> {
                    comment.description("De-serializes the instance to " + elementType + " value.");
                    comment.methodReturns("the " + elementType + " value");
                });

                if (!settings.isStreamStyleSerialization()) {
                    enumBlock.annotation("JsonValue");
                }
            }
            enumBlock.publicMethod(typeName + " " + enumType.getToMethodName() + "()",
                function -> function.methodReturn("this.value"));

            if (settings.isStreamStyleSerialization()) {
                String serializationCall = enumType.getElementType().jsonSerializationMethodCall("jsonWriter", null, "value");
                addGeneratedAnnotation(enumBlock);
                enumBlock.annotation("Override");
                enumBlock.publicMethod("JsonWriter toJson(JsonWriter jsonWriter) throws IOException",
                    function -> function.methodReturn(serializationCall));

                String deserializationCall = enumType.getElementType().jsonDeserializationMethod("jsonReader");
                enumBlock.javadocComment(javadoc -> {
                    javadoc.description("Reads a " + enumName + " from the JSON stream.");
                    javadoc.line("<p>");
                    javadoc.description("The passed JsonReader must be positioned at a " + enumType.getElementType().jsonToken() + " value.");
                    javadoc.param("jsonReader", "The JsonReader being read.");
                    javadoc.methodReturns("The " + enumName + " that the JSON stream represented, may return null.");
                    javadoc.methodThrows(IOException.class.getName(), "If a " + enumName + " fails to be read from the JsonReader.");
                });
                addGeneratedAnnotation(enumBlock);
                enumBlock.publicStaticMethod(enumName + " fromJson(JsonReader jsonReader) throws IOException",
                    function -> function.methodReturn(converterName + "(" + deserializationCall + ")"));
            }
        });
    }

    protected String getStringEnumImport() {
        return ClassType.EXPANDABLE_STRING_ENUM.getFullName();
    }

    /**
     * Creates the if check used by the JsonCreator method used in the Enum type.
     *
     * @param enumType The enum type.
     * @return The JsonCreator if check.
     */
    protected String createEnumJsonCreatorIfCheck(EnumType enumType) {
        IType enumElementType = enumType.getElementType();
        String toJsonMethodName = enumType.getToMethodName();

        if (enumElementType == PrimitiveType.FLOAT) {
            return String.format("Float.floatToIntBits(item.%s()) == Float.floatToIntBits(value)", toJsonMethodName);
        } else if (enumElementType == PrimitiveType.DOUBLE) {
            return String.format("Double.doubleToLongBits(item.%s()) == Double.doubleToLongBits(value)", toJsonMethodName);
        } else if (enumElementType instanceof PrimitiveType) {
            return String.format("item.%s() == value", toJsonMethodName);
        } else if (enumElementType == ClassType.STRING) {
            return String.format("item.%s().equalsIgnoreCase(value)", toJsonMethodName);
        } else {
            return String.format("item.%s().equals(value)", toJsonMethodName);
        }
    }

    protected void addGeneratedImport(Set<String> imports) {
        if (JavaSettings.getInstance().isDataPlaneClient()) {
            Annotation.GENERATED.addImportsTo(imports);
        }
    }

    protected void addGeneratedAnnotation(JavaContext classBlock) {
        if (JavaSettings.getInstance().isDataPlaneClient()) {
            classBlock.annotation(Annotation.GENERATED.getName());
        }
    }

    protected void addGeneratedAnnotation(JavaEnum enumBlock) {
        if (JavaSettings.getInstance().isDataPlaneClient()) {
            enumBlock.annotation(Annotation.GENERATED.getName());
        }
    }
}
