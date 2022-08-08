// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodPollingDetails;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFileContents;
import com.azure.autorest.template.Templates;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TemplateUtil {

    private static final Logger LOGGER = new PluginLogger(Javagen.getPluginInstance(), TemplateUtil.class);

    // begin of constant for template replacement, used in ResourceUtil.loadTextFromResource
    public static final String SERVICE_NAME = "service-name";
    public static final String SERVICE_DESCRIPTION = "service-description";

    public static final String GROUP_ID = "group-id";
    public static final String ARTIFACT_ID = "artifact-id";
    public static final String ARTIFACT_VERSION = "artifact-version";
    public static final String PACKAGE_NAME = "package-name";

    public static final String MANAGER_CLASS = "manager-class";

    public static final String SAMPLE_CODES = "sample-codes";

    public static final String DATE_UTC = "date-utc";
    // end of constant for template replacement

    /**
     * Load text from resources, with string replacement.
     *
     * @param filename the filename of the text template.
     * @param replacements the string replacement to apply to the text template.
     * @return the text, with string replacement applied.
     */
    public static String loadTextFromResource(String filename, String... replacements) {
        String text = "";
        try (InputStream inputStream = TemplateUtil.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream != null) {
                text = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining(System.lineSeparator()));
                if (!text.isEmpty()) {
                    text += System.lineSeparator();
                }

                if (replacements.length > 0) {
                    if (replacements.length % 2 == 0) {
                        // replacement in template
                        for (int i = 0; i < replacements.length; i += 2) {
                            String key = replacements[i];
                            String value = replacements[i+1];
                            text = text.replace("{{" + key + "}}", value);
                        }
                    } else {
                        LOGGER.warn("Replacements skipped due to incorrect length: {}", Arrays.asList(replacements));
                    }
                }
            }
            return text;
        } catch (IOException e) {
            LOGGER.error("Failed to read file '{}'", filename);
            throw new IllegalStateException(e);
        }
    }

    /**
     * Helper function to write client methods to service client and method group
     *
     * @param classBlock Java class block
     * @param clientMethods collection of client methods
     */
    public static void writeClientMethodsAndHelpers(JavaClass classBlock, List<ClientMethod> clientMethods) {
        JavaSettings settings = JavaSettings.getInstance();

        // collect types of TypeReference<T>
        Set<GenericType> typeReferenceStaticClasses = new HashSet<>();

        for (ClientMethod clientMethod : clientMethods) {
            Templates.getClientMethodTemplate().write(clientMethod, classBlock);

            // this is coupled with ClientMethodTemplate.generateLongRunningBeginAsync, see writeLongRunningOperationTypeReference
            if (clientMethod.getType() == ClientMethodType.LongRunningBeginAsync && clientMethod.getMethodPollingDetails() != null) {
                if (clientMethod.getMethodPollingDetails().getIntermediateType() instanceof GenericType) {
                    typeReferenceStaticClasses.add((GenericType) clientMethod.getMethodPollingDetails().getIntermediateType());
                }

                if (clientMethod.getMethodPollingDetails().getFinalType() instanceof GenericType) {
                    typeReferenceStaticClasses.add((GenericType) clientMethod.getMethodPollingDetails().getFinalType());
                }
            }
        }

        // static classes for LRO
        for (GenericType typeReferenceStaticClass : typeReferenceStaticClasses) {
            writeTypeReferenceStaticClass(classBlock, typeReferenceStaticClass);
        }

        // helper methods for LLC
        if (settings.isDataPlaneClient() &&
                clientMethods.stream().anyMatch(m -> m.getMethodPageDetails() != null)) {
            writePagingHelperMethods(classBlock);
        }
    }

    public static String getLongRunningOperationTypeReferenceExpression(MethodPollingDetails details) {
        // see writeTypeReferenceStaticClass

        return String.format("%s, %s",
            getTypeReferenceCreation(details.getIntermediateType()),
            getTypeReferenceCreation(details.getFinalType()));
    }

    private static String getTypeReferenceCreation(IType type) {
        // see writeTypeReferenceStaticClass

        // Array, class, enum, and primitive types are all able to use TypeReference.createInstance which will create
        // or use a singleton instance.
        // Generic types must use a custom instance that supports complex generic parameters.
        return (type instanceof ArrayType || type instanceof ClassType || type instanceof EnumType || type instanceof PrimitiveType)
            ? String.format("TypeReference.createInstance(%s.class)", type.asNullable())
            : CodeNamer.getEnumMemberName("TypeReference" + ((GenericType) type).toJavaPropertyString());
    }

    private static void writeTypeReferenceStaticClass(JavaClass classBlock, GenericType type) {
        // see writeLongRunningOperationTypeReference

        classBlock.privateStaticFinalVariable(String.format("TypeReference<%1$s> %2$s = new TypeReference<%1$s>() {}",
            type, CodeNamer.getEnumMemberName("TypeReference" + type.toJavaPropertyString())));
    }

    /**
     * Helper function to write helper methods for LLC paging
     *
     * @param classBlock Java class block
     */
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
        if (!settings.isStreamStyleSerialization()) {
            addJsonGetterOrJsonSetter(classBlock, settings, () -> "JsonGetter(\"" + propertyName + "\")");
        }
    }

    /**
     * Helper function to add a JsonSetter to a class block.
     *
     * @param classBlock The class block being annotated.
     * @param settings The AutoRest settings to determine if JsonSetter should be added.
     * @param propertyName The JSON property name for the JsonSetter.
     */
    public static void addJsonSetter(JavaClass classBlock, JavaSettings settings, String propertyName) {
        if (!settings.isStreamStyleSerialization()) {
            addJsonGetterOrJsonSetter(classBlock, settings, () -> "JsonSetter(\"" + propertyName + "\")");
        }
    }

    private static void addJsonGetterOrJsonSetter(JavaClass classBlock, JavaSettings settings,
        Supplier<String> annotation) {
        if (settings.isGettersAndSettersAnnotatedForSerialization()) {
            classBlock.annotation(annotation.get());
        }
    }

    public static void addClientLogger(JavaClass classBlock, String className, JavaFileContents javaFileContents) {
        String content = javaFileContents.toString();
        if (content.contains("throw LOGGER")
                || content.contains("LOGGER.logThrowable")
                || content.contains("LOGGER.logException")) {
            // hack to add LOGGER class variable only if LOGGER is used in code
            classBlock.privateStaticFinalVariable(String.format("%1$s LOGGER = new ClientLogger(%2$s.class)",
                    ClassType.ClientLogger.toString(), className));
        }
    }
}
