// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.time.Instant;
import java.util.Set;
import java.util.function.Function;

/**
 * A basic type used by a client.
 */
public class PrimitiveType implements IType {
    public static final PrimitiveType Void = new Builder()
            .name("void")
            .nullableType(ClassType.Void)
            .build();

    public static final PrimitiveType Boolean = new Builder()
            .name("boolean")
            .nullableType(ClassType.Boolean)
            .defaultValueExpressionConverter(String::toLowerCase)
            .defaultValue("false")
            .serializationMethodBase("writeBoolean")
            .jsonDeserializationMethod("getBoolean()")
            .xmlAttributeDeserializationTemplate("getBooleanAttribute(%s, %s)")
            .xmlElementDeserializationMethod("getBooleanElement()")
            .build();

    public static final PrimitiveType Byte = new Builder()
            .name("byte")
            .nullableType(ClassType.Byte)
            .defaultValueExpressionConverter(Function.identity())
            .defaultValue("0")
            .serializationMethodBase("writeInt")
            .jsonDeserializationMethod("getInt()")
            .xmlAttributeDeserializationTemplate("getIntAttribute(%s, %s)")
            .xmlElementDeserializationMethod("getIntElement()")
            .build();

    public static final PrimitiveType Int = new Builder()
            .name("int")
            .nullableType(ClassType.Integer)
            .defaultValueExpressionConverter(Function.identity())
            .defaultValue("0")
            .serializationMethodBase("writeInt")
            .jsonDeserializationMethod("getInt()")
            .xmlAttributeDeserializationTemplate("getIntAttribute(%s, %s)")
            .xmlElementDeserializationMethod("getIntElement()")
            .build();

    public static final PrimitiveType Long = new Builder()
            .prototypeAsLong()
            .build();

    public static final PrimitiveType Float = new Builder()
            .name("float")
            .nullableType(ClassType.Float)
            .defaultValueExpressionConverter(defaultValueExpression -> defaultValueExpression + "f")
            .defaultValue("0.0")
            .serializationMethodBase("writeFloat")
            .jsonDeserializationMethod("getFloat()")
            .xmlAttributeDeserializationTemplate("getFloatAttribute(%s, %s)")
            .xmlElementDeserializationMethod("getFloatElement()")
            .build();

    public static final PrimitiveType Double = new Builder()
            .prototypeAsDouble()
            .build();

    public static final PrimitiveType Char = new Builder()
            .name("char")
            .nullableType(ClassType.Character)
            .defaultValueExpressionConverter(defaultValueExpression -> Integer.toString(defaultValueExpression.charAt(0)))
            .defaultValue("\u0000")
            .serializationMethodBase("writeString")
            .wrapSerializationWithObjectsToString(true)
            .jsonDeserializationMethod("getString().charAt(0)")
            .xmlAttributeDeserializationTemplate("getStringAttribute(%s, %s).charAt(0)")
            .xmlElementDeserializationMethod("getStringElement().charAt(0)")
            .build();

    public static final PrimitiveType UnixTimeLong = new Builder()
            .prototypeAsLong()
            .nullableType(ClassType.UnixTimeLong)
            .build();

    public static final PrimitiveType DurationLong = new Builder()
            .prototypeAsLong()
            .nullableType(ClassType.DurationLong)
            .build();

    public static final PrimitiveType DurationDouble = new Builder()
            .prototypeAsDouble()
            .nullableType(ClassType.DurationDouble)
            .build();

    /**
     * The name of this type.
     */
    private final String name;
    /**
     * The nullable version of this primitive type.
     */
    private final ClassType nullableType;
    private final Function<String, String> defaultValueExpressionConverter;
    private final String defaultValue;
    private final String serializationMethodBase;
    private final boolean wrapSerializationWithObjectsToString;
    private final String jsonDeserializationMethod;
    private final String xmlAttributeDeserializationTemplate;
    private final String xmlElementDeserializationMethod;

    private PrimitiveType(String name, ClassType nullableType, Function<String, String> defaultValueExpressionConverter,
        String defaultValue, String serializationMethodBase, boolean wrapSerializationWithObjectsToString,
        String jsonDeserializationMethod, String xmlAttributeDeserializationTemplate,
        String xmlElementDeserializationMethod, String... importsToAdd) {
        this.name = name;
        this.nullableType = nullableType;
        this.defaultValueExpressionConverter = defaultValueExpressionConverter;
        this.defaultValue = defaultValue;
        this.serializationMethodBase = serializationMethodBase;
        this.wrapSerializationWithObjectsToString = wrapSerializationWithObjectsToString;
        this.jsonDeserializationMethod = jsonDeserializationMethod;
        this.xmlAttributeDeserializationTemplate = xmlAttributeDeserializationTemplate;
        this.xmlElementDeserializationMethod = xmlElementDeserializationMethod;
    }

//    public static PrimitiveType fromNullableType(ClassType nullableType) {
//        if (nullableType == ClassType.Void) {
//            return PrimitiveType.Void;
//        } else if (nullableType == ClassType.Boolean) {
//            return PrimitiveType.Boolean;
//        } else if (nullableType == ClassType.Byte) {
//            return PrimitiveType.Byte;
//        } else if (nullableType == ClassType.Integer) {
//            return PrimitiveType.Int;
//        } else if (nullableType == ClassType.Long) {
//            return PrimitiveType.Long;
//        } else if (nullableType == ClassType.Double) {
//            return PrimitiveType.Double;
//        } else if (nullableType == ClassType.Float) {
//            return PrimitiveType.Float;
//        } else {
//            throw new IllegalArgumentException("Class type " + nullableType + " is not a boxed type");
//        }
//    }

    public final String getName() {
        return name;
    }

    private ClassType getNullableType() {
        return nullableType;
    }

    @Override
    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (this == PrimitiveType.UnixTimeLong) {
            imports.add(Instant.class.getName());
        }
    }

    @Override
    public final boolean isNullable() {
        return false;
    }

    @Override
    public final IType asNullable() {
        return getNullableType();
    }

    @Override
    public final boolean contains(IType type) {
        return this == type;
    }

    private java.util.function.Function<String, String> getDefaultValueExpressionConverter() {
        return defaultValueExpressionConverter;
    }

    @Override
    public final String defaultValueExpression(String sourceExpression) {
        String result = sourceExpression;
        if (result != null && getDefaultValueExpressionConverter() != null) {
            result = defaultValueExpressionConverter.apply(sourceExpression);
        }
        return result;
    }

    @Override
    public final String defaultValueExpression() {
        String result = defaultValue;
        if (result != null && getDefaultValueExpressionConverter() != null) {
            result = defaultValueExpressionConverter.apply(defaultValue);
        }
        return result;
    }

    @Override
    public final IType getClientType() {
        IType clientType = this;
        if (this == PrimitiveType.UnixTimeLong) {
            clientType = ClassType.UnixTimeDateTime;
        } else if (this == PrimitiveType.DurationLong) {
            clientType = ClassType.Duration;
        } else if (this == PrimitiveType.DurationDouble) {
            clientType = ClassType.Duration;
        }
        return clientType;
    }

    @Override
    public final String convertToClientType(String expression) {
        if (getClientType() == this) {
            return expression;
        }

        if (this == PrimitiveType.UnixTimeLong) {
            expression = String.format("OffsetDateTime.from(Instant.ofEpochSecond(%1$s))", expression);
        } else if (this == PrimitiveType.DurationLong) {
            expression = java.lang.String.format("Duration.ofSeconds(%s)", expression);
        } else if (this == PrimitiveType.DurationDouble) {
            expression = java.lang.String.format("Duration.ofNanos((long) (%s * 1000_000_000L))", expression);
        }
        return expression;
    }

    @Override
    public final String convertFromClientType(String expression) {
        if (getClientType() == this) {
            return expression;
        }

        if (this == PrimitiveType.UnixTimeLong) {
            expression = String.format("%1$s.toEpochSecond()", expression);
        } else if (this == PrimitiveType.DurationLong) {
            expression = java.lang.String.format("%s.getSeconds()", expression);
        } else if (this == PrimitiveType.DurationDouble) {
            expression = java.lang.String.format("(double) %s.toNanos() / 1000_000_000L", expression);
        }
        return expression;
    }

    @Override
    public final String validate(String expression) {
        return null;
    }

    @Override
    public String jsonDeserializationMethod(String jsonReaderName) {
        if (jsonDeserializationMethod == null) {
            return null;
        }

        return jsonReaderName + "." + jsonDeserializationMethod;
    }

    @Override
    public java.lang.String jsonSerializationMethodCall(java.lang.String jsonWriterName, java.lang.String fieldName,
        java.lang.String valueGetter) {
        if (wrapSerializationWithObjectsToString) {
            return fieldName == null
                ? java.lang.String.format("%s.%s(Objects.toString(%s, null))", jsonWriterName,
                serializationMethodBase, valueGetter)
                : java.lang.String.format("%s.%sField(\"%s\", Objects.toString(%s, null))", jsonWriterName,
                serializationMethodBase, fieldName, valueGetter);
        }

        return fieldName == null
            ? java.lang.String.format("%s.%s(%s)", jsonWriterName, serializationMethodBase, valueGetter)
            : java.lang.String.format("%s.%sField(\"%s\", %s)", jsonWriterName, serializationMethodBase, fieldName,
            valueGetter);
    }

    @Override
    public String xmlDeserializationMethod(String attributeName, String attributeNamespace) {
        if (attributeName == null) {
            return xmlElementDeserializationMethod;
        } else {
            return (attributeNamespace == null)
                ? String.format(xmlAttributeDeserializationTemplate, "null", "\"" + attributeName + "\"")
                : String.format(xmlAttributeDeserializationTemplate, "\"" + attributeNamespace + "\"",
                    "\"" + attributeName + "\"");
        }
    }

    @Override
    public java.lang.String xmlSerializationMethodCall(java.lang.String xmlWriterName,
        java.lang.String attributeOrElementName, java.lang.String namespaceUri, java.lang.String valueGetter,
        boolean isAttribute, boolean nameIsVariable) {
        String value = wrapSerializationWithObjectsToString
            ? "Objects.toString(" + valueGetter + ", null)" : valueGetter;

        return ClassType.xmlSerializationCallHelper(xmlWriterName, serializationMethodBase, attributeOrElementName,
            namespaceUri, value, isAttribute, nameIsVariable);
    }

    @Override
    public String toString() {
        return getName();
    }

    private static class Builder {

        private String name;
        private ClassType nullableType;
        private Function<String, String> defaultValueExpressionConverter;
        private String defaultValue;
        private String serializationMethodBase;
        private boolean wrapSerializationWithObjectsToString = false;
        private String jsonDeserializationMethod;
        private String xmlAttributeDeserializationTemplate;
        private String xmlElementDeserializationMethod;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder prototypeAsLong() {
            return this.name("long")
                .nullableType(ClassType.Long)
                .defaultValueExpressionConverter(defaultValueExpression -> defaultValueExpression + 'L')
                .defaultValue("0")
                .serializationMethodBase("writeLong")
                .wrapSerializationWithObjectsToString(false)
                .jsonDeserializationMethod("getLong()")
                .xmlAttributeDeserializationTemplate("getLongAttribute(%s, %s)")
                .xmlElementDeserializationMethod("getLongElement()");
        }

        public Builder prototypeAsDouble() {
            return this.name("double")
                .nullableType(ClassType.Double)
                .defaultValueExpressionConverter(defaultValueExpression -> java.lang.Double.toString(java.lang.Double.parseDouble(defaultValueExpression)))
                .defaultValue("0.0")
                .serializationMethodBase("writeDouble")
                .wrapSerializationWithObjectsToString(false)
                .jsonDeserializationMethod("getDouble()")
                .xmlAttributeDeserializationTemplate("getDoubleAttribute(%s, %s)")
                .xmlElementDeserializationMethod("getDoubleElement()");
        }

        public Builder nullableType(ClassType nullableType) {
            this.nullableType = nullableType;
            return this;
        }

        public Builder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder defaultValueExpressionConverter(java.util.function.Function<String, String> defaultValueExpressionConverter) {
            this.defaultValueExpressionConverter = defaultValueExpressionConverter;
            return this;
        }

        public Builder wrapSerializationWithObjectsToString(boolean wrapSerializationWithObjectsToString) {
            this.wrapSerializationWithObjectsToString = wrapSerializationWithObjectsToString;
            return this;
        }

        public Builder jsonDeserializationMethod(String jsonDeserializationMethod) {
            this.jsonDeserializationMethod = jsonDeserializationMethod;
            return this;
        }

        public Builder serializationMethodBase(String serializationMethodBase) {
            this.serializationMethodBase = serializationMethodBase;
            return this;
        }

        public Builder xmlAttributeDeserializationTemplate(String xmlAttributeDeserializationTemplate) {
            this.xmlAttributeDeserializationTemplate = xmlAttributeDeserializationTemplate;
            return this;
        }

        public Builder xmlElementDeserializationMethod(String xmlElementDeserializationMethod) {
            this.xmlElementDeserializationMethod = xmlElementDeserializationMethod;
            return this;
        }

        public PrimitiveType build() {
            return new PrimitiveType(name, nullableType, defaultValueExpressionConverter, defaultValue,
                    serializationMethodBase, wrapSerializationWithObjectsToString,
                    jsonDeserializationMethod, xmlAttributeDeserializationTemplate, xmlElementDeserializationMethod);
        }
    }
}
