// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.IterableType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Writes a ClientModel to a JavaFile using stream-style serialization.
 */
public class StreamSerializationModelTemplate extends ModelTemplate {
    private static final StreamSerializationModelTemplate INSTANCE = new StreamSerializationModelTemplate();

    protected StreamSerializationModelTemplate() {
    }

    public static StreamSerializationModelTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void addSerializationImports(Set<String> imports, JavaSettings settings) {
        imports.add(JsonSerializable.class.getName());
        imports.add(JsonWriter.class.getName());
        imports.add(JsonReader.class.getName());
        imports.add(JsonToken.class.getName());
        imports.add(JsonUtils.class.getName());

        imports.add(Base64.class.getName());
        imports.add(LinkedHashMap.class.getName());
    }

    @Override
    protected void handlePolymorphism(ClientModel model, boolean hasDerivedModels,
        boolean isDiscriminatorPassedToChildDeserialization, JavaFile javaFile) {
        // no-op as stream-style serialization doesn't need to add anything for polymorphic types.
    }

    @Override
    protected void addClassLevelAnnotations(ClientModel model, JavaFile javaFile, JavaSettings settings) {
        // no-op as stream-style serialization doesn't add any class-level annotations.
    }

    @Override
    protected String addSerializationImplementations(String classSignature, ClientModel model, JavaSettings settings) {
        // TODO (alzimmer): Once XML stream-style serialization is supported this will need to determine whether
        //  JsonCapable and/or XmlCapable need to be added as an implemented interface.
        if (settings.isStreamStyleSerialization()) {
            classSignature += " implements " + JsonSerializable.class.getSimpleName() + "<" + model.getName() + ">";
        }

        return classSignature;
    }

    @Override
    protected void addModelConstructorParameter(ClientModelProperty property,
        StringBuilder constructorSignatureBuilder) {
        constructorSignatureBuilder.append(property.getClientType()).append(" ").append(property.getName());
    }

    @Override
    protected void addFieldAnnotations(ClientModelProperty property, JavaClass classBlock, JavaSettings settings) {
        // no-op as stream-style serialization doesn't add any field-level annotations.
    }

    @Override
    protected void writeStreamStyleSerialization(JavaClass classBlock, ClientModel model,
        List<ClientModelProperty> propertiesDeclaredByModel,
        List<ClientModelPropertyReference> propertiesDeclaredBySuperTypes, boolean isFluent, JavaSettings settings) {
        // Start by determining the JSON field names, the type they deserialize into, and whether the type or a super
        // type owns the field.
        classBlock.annotation("Override");
        classBlock.publicMethod("JsonWriter toJson(JsonWriter jsonWriter)", methodBlock -> {
            methodBlock.methodReturn("jsonWriter.flush()");
        });

        classBlock.publicStaticMethod(model.getName() + " fromJson(JsonReader jsonReader)", methodBlock -> {
            // For now, use the basic JsonUtils.readObject which will return null if the JsonReader is pointing
            // to JsonToken.NULL.
            //
            // Support for a default value if null will need to be supported and for objects that get their value
            // from a JSON value instead of JSON object or are an array type.
            methodBlock.line("return JsonUtils.readObject(jsonReader, reader -> {");
            methodBlock.indent(() -> {
                // If the model is fluent create a new instance of it and use setters during deserialization.
                if (isFluent) {
                    methodBlock.line(model.getName() + " deserializedValue = new " + model.getName() + "();");
                } else {
                    // If the model isn't fluent a local variable to track the deserialized value is needed.
                    Consumer<ClientModelProperty> localVariableCreator = property -> {
                        if (property.getWireType() instanceof ClassType || property.getWireType() instanceof GenericType) {
                            methodBlock.line(property.getWireType() + " " + property.getName() + " = null;");
                        } else {
                            methodBlock.line(property.getWireType() + " " + property.getName() + ";");
                        }
                    };

                    propertiesDeclaredByModel.forEach(localVariableCreator);
                    propertiesDeclaredBySuperTypes.forEach(reference ->
                        localVariableCreator.accept(reference.getTargetProperty()));
                }

                methodBlock.line("while (reader.nextToken() != JsonToken.END_OBJECT) {");
                methodBlock.increaseIndent();
                methodBlock.line("String fieldName = reader.getFieldName();");
                methodBlock.line("reader.nextToken();");
                methodBlock.line("");

                JavaIfBlock ifBlock = null;

                // Loop over the properties and handle deserializing their fields.
                for (ClientModelProperty propertyDeclaredByModel : propertiesDeclaredByModel) {
                    ifBlock = handlePropertyDeserialization(propertyDeclaredByModel, methodBlock, ifBlock, isFluent,
                        settings);
                }

                // Then do the same for referenced properties as the subtype need to handle deserializing super type
                // properties.
                for (ClientModelPropertyReference propertyDeclaredBySuperTypes : propertiesDeclaredBySuperTypes) {
                    ifBlock = handlePropertyDeserialization(
                        (ClientModelProperty) propertyDeclaredBySuperTypes.getReferenceProperty(),
                        methodBlock, ifBlock, isFluent, settings);
                }

                // All properties have been checked for, add an else block that will either ignore unknown properties
                // or add them into an additional properties bag.
                handleUnknownFieldDeserialization(methodBlock, ifBlock,
                    model.getProperties().stream().anyMatch(ClientModelProperty::isAdditionalProperties));
            });

            methodBlock.line("}");
            methodBlock.decreaseIndent();
            if (isFluent) {
                methodBlock.methodReturn("deserializedValue");
            }
            methodBlock.line("});");
        });
    }

    private static JavaIfBlock handlePropertyDeserialization(ClientModelProperty property, JavaBlock methodBlock,
        JavaIfBlock ifBlock, boolean isFluent, JavaSettings settings) {
        String jsonPropertyName = property.getSerializedName();
        if (CoreUtils.isNullOrEmpty(jsonPropertyName)) {
            return ifBlock;
        }

        String condition = "\"" + jsonPropertyName + "\".equals(fieldName)";

        // TODO (alzimmer): Need to exclude constant properties and add validation for required properties.
        //  Also need to handle flattened values.
        Consumer<JavaBlock> deserializationHandling = deserializationBlock ->
            handleFieldDeserialization(deserializationBlock, property, isFluent, settings);
        return (ifBlock == null)
            ? methodBlock.ifBlock(condition, deserializationHandling)
            : ifBlock.elseIfBlock(condition, deserializationHandling);
    }

    private static void handleFieldDeserialization(JavaBlock deserializationBlock, ClientModelProperty property,
        boolean isFluent, JavaSettings settings) {
        Consumer<JavaBlock> deserializationLogic = deserialization ->
            generateDeserializationLogic(deserialization, property, isFluent);

        if (wireTypeNeedsNullCheck(property.getWireType())) {
            deserializationBlock.ifBlock("reader.currentToken() != JsonToken.NULL", deserializationLogic);
        } else {
            deserializationLogic.accept(deserializationBlock);
        }
    }

    private static boolean wireTypeNeedsNullCheck(IType wireType) {
        return wireType == ClassType.Boolean
            || wireType == ClassType.Double
            || wireType == ClassType.Float
            || wireType == ClassType.Integer
            || wireType == ClassType.Long
            || wireType == ArrayType.ByteArray
            || wireType == ClassType.DateTimeRfc1123
            || wireType == ClassType.DateTime
            || wireType == ClassType.LocalDate
            || wireType == ClassType.Duration
            || wireType instanceof EnumType
            || wireType instanceof MapType;
    }

    private static void generateDeserializationLogic(JavaBlock deserializationBlock, ClientModelProperty property,
        boolean isFluent) {
        IType wireType = property.getWireType();
        String simpleDeserialization = getSimpleDeserialization(wireType);
        if (simpleDeserialization != null) {
            if (isFluent) {
                deserializationBlock.line("deserializedValue." + property.getSetterName()
                    + "(" + simpleDeserialization + ");");
            } else {
                deserializationBlock.line(property.getName() + " = " + simpleDeserialization + ";");
            }
        } else if (wireType instanceof IterableType) {
            IType elementType = ((IterableType) wireType).getElementType();
            String elementDeserialization = getSimpleDeserialization(elementType);

            if (isFluent) {
                deserializationBlock.text(wireType + " value = ");
            } else {
                deserializationBlock.text(property.getName() + " = ");
            }

            if (wireTypeNeedsNullCheck(elementType)) {
                deserializationBlock.block("JsonUtils.readArray(reader, r ->", readArrayBlock -> {
                    readArrayBlock.ifBlock("reader.currentToken() == JsonToken.NULL",
                        ifAction -> ifAction.methodReturn("null"));
                    readArrayBlock.methodReturn(elementDeserialization);
                });
                deserializationBlock.text(");");
            } else {
                deserializationBlock.line("JsonUtils.readArray(reader, r -> " + elementDeserialization + ");");
            }

            if (isFluent) {
                deserializationBlock.line("deserializedValue." + property.getSetterName() + "(value);");
            }
        } else if (wireType instanceof MapType) {
            // Assumption is that the key type for the Map is a String. This may not always hold true and when that
            // becomes reality this will need to be reworked to handle that case.
            IType valueType = ((MapType) wireType).getValueType();
            String valueDeserialization = getSimpleDeserialization(valueType);

            if (isFluent) {
                deserializationBlock.line(wireType + " " + property.getName() + " = new LinkedHashMap<>();");
            } else {
                deserializationBlock.ifBlock(property.getName() + " == null",
                    ifAction -> ifAction.line(property.getName() + " = new LinkedHashMap<>();"));
            }
            deserializationBlock.line();

            deserializationBlock.line("while (reader.nextToken() != JsonToken.END_OBJECT) {");
            deserializationBlock.increaseIndent();

            deserializationBlock.line("fieldName = reader.getFieldName();");
            deserializationBlock.line("reader.nextToken();");
            deserializationBlock.line();
            if (wireTypeNeedsNullCheck(valueType)) {
                deserializationBlock.line(valueType + " value = (reader.currentToken() == JsonToken.NULL)");
                deserializationBlock.indent(() -> {
                    deserializationBlock.line("? null");
                    deserializationBlock.line(": " + valueDeserialization + ";");
                });
            } else {
                deserializationBlock.line(valueType + " value = " + valueDeserialization + ";");
            }
            deserializationBlock.line(property.getName() + ".put(fieldName, value);");

            deserializationBlock.decreaseIndent();
            deserializationBlock.line("}");

            if (isFluent) {
                deserializationBlock.line();
                deserializationBlock.line("deserializedValue." + property.getSetterName()
                    + "(" + property.getName() + ");");
            }
        } else {
            // TODO (alzimmer): Resolve this as deserialization logic generation needs to handle all cases.
            throw new RuntimeException("Unknown wire type " + wireType + ". Need to add support for it.");
        }
    }

    private static String getSimpleDeserialization(IType wireType) {
        if (wireType == PrimitiveType.Boolean || wireType == ClassType.Boolean) {
            return "reader.getBooleanValue()";
        } else if (wireType == PrimitiveType.Double || wireType == ClassType.Double) {
            return "reader.getDoubleValue()";
        } else if (wireType == PrimitiveType.Float || wireType == ClassType.Float) {
            return "reader.getFloatValue()";
        } else if (wireType == PrimitiveType.Int || wireType == ClassType.Integer) {
            return "reader.getIntValue()";
        } else if (wireType == PrimitiveType.Long || wireType == ClassType.Long) {
            return "reader.getLongValue()";
        } else if (wireType == ArrayType.ByteArray) {
            return "Base64.getDecoder().decode(reader.getStringValue())";
        } else if (wireType == ClassType.String) {
            return "reader.getStringValue()";
        } else if (wireType == ClassType.DateTimeRfc1123) {
            return "new DateTimeRfc1123(reader.getStringValue())";
        } else if (wireType == ClassType.DateTime) {
            return "OffsetDateTime.parse(reader.getStringValue())";
        } else if (wireType == ClassType.LocalDate) {
            return "LocalDate.parse(reader.getStringValue())";
        } else if (wireType == ClassType.Duration) {
            return "Duration.parse(reader.getStringValue())";
        } else if (wireType instanceof EnumType) {
            EnumType enumType = (EnumType) wireType;
            return enumType.getName() + "." + enumType.getFromJsonMethodName() + "(reader.getStringValue())";
        } else if (wireType instanceof ClassType) {
            return wireType + ".fromJson(reader)";
        } else {
            return null;
        }
    }

    private static void handleUnknownFieldDeserialization(JavaBlock methodBlock, JavaIfBlock ifBlock,
        boolean hasAdditionalProperties) {
        Consumer<JavaBlock> unknownFieldConsumer = javaBlock -> {
            if (hasAdditionalProperties) {
                // TODO (alzimmer): Deserialize the additional property.
            } else {
                javaBlock.line("reader.skipChildren();");
            }
        };

        if (ifBlock == null) {
            unknownFieldConsumer.accept(methodBlock);
        } else {
            ifBlock.elseBlock(unknownFieldConsumer);
        }
    }
}
