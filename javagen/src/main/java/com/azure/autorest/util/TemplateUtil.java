/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.util;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.template.Templates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class TemplateUtil {

    public static void writeClientMethodsAndHelpers(JavaClass classBlock, List<ClientMethod> clientMethods) {
        JavaSettings settings = JavaSettings.getInstance();

        // collect types of TypeReference<T>
        Set<String> typeReferenceStaticClasses = new HashSet<>();

        for (ClientMethod clientMethod : clientMethods) {
            Templates.getClientMethodTemplate().write(clientMethod, classBlock);

            // this is coupled with ClientMethodTemplate.generateLongRunningBeginAsync, see writeLongRunningOperationTypeReference
            if (clientMethod.getType() == ClientMethodType.LongRunningBeginAsync && clientMethod.getMethodPollingDetails() != null) {
                if (clientMethod.getMethodPollingDetails().getIntermediateType() instanceof ClassType && clientMethod.getMethodPollingDetails().getFinalType() instanceof ClassType) {
                    typeReferenceStaticClasses.add(clientMethod.getMethodPollingDetails().getIntermediateType().toString());
                    typeReferenceStaticClasses.add(clientMethod.getMethodPollingDetails().getFinalType().toString());
                }
            }
        }

        // static classes for LRO
        for (String typeReferenceStaticClass : typeReferenceStaticClasses) {
            writeTypeReferenceStaticClass(classBlock, typeReferenceStaticClass);
        }

        // helper methods for LLC
        if (settings.isLowLevelClient() &&
                clientMethods.stream().anyMatch(m -> m.getMethodPageDetails() != null)) {
            writePagingHelperMethods(classBlock);
        }
    }

    public static void writeLongRunningOperationTypeReference(JavaBlock javaBlock, ClientMethod clientMethod) {
        // see writeTypeReferenceStaticClass

        if (clientMethod.getMethodPollingDetails().getIntermediateType() instanceof ClassType && clientMethod.getMethodPollingDetails().getFinalType() instanceof ClassType) {
            // use static inner class
            javaBlock.line("new TypeReference%s(), new TypeReference%s());",
                    clientMethod.getMethodPollingDetails().getIntermediateType(),
                    clientMethod.getMethodPollingDetails().getFinalType());
        } else {
            javaBlock.line("new TypeReference<%s>() { }, new TypeReference<%s>() { });",
                    clientMethod.getMethodPollingDetails().getIntermediateType(),
                    clientMethod.getMethodPollingDetails().getFinalType());
        }
    }

    private static void writeTypeReferenceStaticClass(JavaClass classBlock, String typeReferenceStaticClass) {
        // see writeLongRunningOperationTypeReference

        classBlock.privateStaticFinalClass(String.format("TypeReference%1$s extends TypeReference<%1$s>", typeReferenceStaticClass), classBlock1 -> {
        });
    }

    private static void writePagingHelperMethods(JavaClass classBlock) {
        classBlock.privateMethod("List<BinaryData> getValues(BinaryData binaryData, String path)", block -> {
            block.line("try {");
            block.line("Map<?, ?> obj = binaryData.toObject(Map.class);");
            block.line("List<?> values = (List<?>) obj.get(path);");
            block.line("return values.stream().map(BinaryData::fromObject).collect(Collectors.toList());");
            block.line("} catch (RuntimeException e) { return null; }");
        });
        classBlock.privateMethod("String getNextLink(BinaryData binaryData, String path)", block -> {
            block.line("try {");
            block.line("Map<?, ?> obj = binaryData.toObject(Map.class);");
            block.line("return (String) obj.get(path);");
            block.line("} catch (RuntimeException e) { return null; }");
        });
    }

    /**
     * Helper function to add a JsonGetter to a class block.
     *
     * @param classBlock The class block being annotated.
     * @param settings The AutoRest settings to determine if JsonGetter should be added.
     * @param propertyName The JSON property name for the JsonGetter.
     */
    public static void addJsonGetter(JavaClass classBlock, JavaSettings settings, String propertyName) {
        addJsonGetterOrJsonSetter(classBlock, settings, () -> "JsonGetter(\"" + propertyName + "\")");
    }

    /**
     * Helper function to add a JsonSetter to a class block.
     *
     * @param classBlock The class block being annotated.
     * @param settings The AutoRest settings to determine if JsonSetter should be added.
     * @param propertyName The JSON property name for the JsonSetter.
     */
    public static void addJsonSetter(JavaClass classBlock, JavaSettings settings, String propertyName) {
        addJsonGetterOrJsonSetter(classBlock, settings, () -> "JsonSetter(\"" + propertyName + "\")");
    }

    private static void addJsonGetterOrJsonSetter(JavaClass classBlock, JavaSettings settings,
        Supplier<String> annotation) {
        if (settings.isGettersAndSettersAnnotatedForSerialization()) {
            classBlock.annotation(annotation.get());
        }
    }
}
