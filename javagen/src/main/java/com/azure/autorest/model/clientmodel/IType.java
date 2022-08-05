// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.Set;

/**
 * A type used by a client.
 */
public interface IType {
    /**
     * The type variant that users interact with.
     * @return The type's client-side variant.
     */
    IType getClientType();

    /**
     * Convert this type to the type users interact with.
     * @return Java code to convert an expression to client type.
     */
    String convertToClientType(String expression);

    /**
     * Convert the client type variant of this type to the original form that should be sent on the wire.
     * @return Java code to convert a client type expression to wire format.
     */
    String convertFromClientType(String expression);

    /**
     * Indicates whether the type is nullable.
     *
     * @return Whether the type is nullable.
     */
    default boolean isNullable() {
        return true;
    }

    /**
     * Convert this IType to an IType that is nullable.
     * @return A version of this IType that is nullable.
     */
    IType asNullable();

    /**
     * Get whether this IType contains (or is) the provided type.
     * @param type The type to search for.
     *
     * @return Whether this IType contains (or is) the provided type.
     */
    boolean contains(IType type);

    /**
     * Add this type's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether to include imports that are only necessary for method implementations.
     */
    void addImportsTo(Set<String> imports, boolean includeImplementationImports);

    /**
     * Convert the provided default value expression to this type's default value expression.
     * @param sourceExpression The source expression to convert to this type's default value expression.
     *
     * @return This type's default value expression.
     */
    String defaultValueExpression(String sourceExpression);

    /**
     * The default value expression, when this type does not have data.
     *
     * This is the expression of the type provided as client.
     * By default, the expression is "null" for class types.
     * For primitive types, this would be the Java default value, usually "0".
     * For some collection types, this could be the empty collection.
     *
     * @return the default value expression, when this type does not have data.
     */
    String defaultValueExpression();

    String validate(String expression);

    /**
     * Indicates whether the type needs null guarding in deserialization.
     *
     * @return Whether the type needs null guarding in deserialization.
     */
    default boolean deserializationNeedsNullGuarding() {
        return true;
    }

    /**
     * Gets the method that handles field serialization for the type.
     * <p>
     * The field serialization method handles writing both the JSON field name and value.
     * <p>
     * If null is returned it either means the type is complex, such as a List or Map, or doesn't have a field
     * serialization method and support needs to be added.
     *
     * @return The field serialization method, or null if it isn't supported directly.
     */
    default String streamStyleJsonFieldSerializationMethod() {
        return null;
    }

    /**
     * Gets the method that handles value serialization for the type.
     * <p>
     * The value serialization method only handles writing the JSON value. The enables it to be used in situations such
     * as writing an array or writing to the root of the JSON string.
     * <p>
     * If null is returned it either means the type is complex, such as a List or Map, or doesn't have a value
     * serialization method and support needs to be added.
     *
     * @return The value serialization method, or null if it isn't supported directly.
     */
    default String streamStyleJsonValueSerializationMethod() {
        return null;
    }

    /**
     * Gets the method that handles XML attribute serialization for the type.
     * <p>
     * The attribute serialization method handles writing both the XML attribute and value.
     * <p>
     * If null is returned it either means the type is complex, such as a List or Map, or doesn't have an attribute
     * serialization method and support needs to be added.
     *
     * @return The attribute serialization method, or null if it isn't supported directly.
     */
    default String streamStyleXmlAttributeSerializationMethod() {
        return null;
    }

    /**
     * Gets the method that handles XML element serialization for the type.
     * <p>
     * The element serialization method handles writing both the XML element tag and value.
     * <p>
     * If null is returned it either means the type is complex, such as a List or Map, or doesn't have an element
     * serialization method and support needs to be added.
     *
     * @return The element serialization method, or null if it isn't supported directly.
     */
    default String streamStyleXmlElementSerializationMethod() {
        return null;
    }
}
