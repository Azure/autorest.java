/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.model.arm.ResourceClientModel;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.LocalVariable;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceLocalVariables;
import com.azure.autorest.fluent.template.UtilsTemplate;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FluentUtils {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), FluentUtils.class);

    private static final Set<String> RESERVED_CLASS_NAMES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            Response.class.getSimpleName(),
            Context.class.getSimpleName()
    )));

    private FluentUtils() {
    }

    public static void log(String format) {
        logger.info(format);
    }

    public static void log(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    public static Set<String> reservedClassNames() {
        return RESERVED_CLASS_NAMES;
    }

    public static boolean isInnerClassType(ClassType classType) {
        return isInnerClassType(classType.getPackage(), classType.getName());
    }

    public static boolean isInnerClassType(String packageName, String name) {
        JavaSettings settings = JavaSettings.getInstance();
        String innerPackageName = settings.getPackage(settings.getFluentModelsSubpackage());
        return packageName.equals(innerPackageName) && name.endsWith("Inner");
    }

    public static ClassType resourceModelInterfaceClassType(ClassType innerModelClassType) {
        return resourceModelInterfaceClassType(innerModelClassType.getName());
    }

    public static ClassType resourceModelInterfaceClassType(String innerModelClassName) {
        JavaSettings settings = JavaSettings.getInstance();
        String modelName = innerModelClassName.substring(0, innerModelClassName.length() - "Inner".length());
        if (reservedClassNames().contains(modelName)) {
            modelName += "Model";
        }
        return new ClassType.Builder()
                .packageName(settings.getPackage(settings.getModelsSubpackage()))
                .name(modelName)
                .build();
    }

    public static String getGetterName(String propertyName) {
        return CodeNamer.getModelNamer().modelPropertyGetterName(propertyName);
    }

    public static String getServiceName(String clientName) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceName = settings.getServiceName();
        if (CoreUtils.isNullOrEmpty(serviceName)) {
            serviceName = getServiceNameFromClientName(clientName, settings.getPackage());
        }
        return serviceName;
    }

    static String getServiceNameFromClientName(String clientName, String packageName) {
        String serviceName = null;
        String packageLastName = getPackageLastName(packageName);

        if (clientName != null) {
            if (clientName.toLowerCase(Locale.ROOT).startsWith(packageLastName.toLowerCase(Locale.ROOT))) {
                serviceName = clientName.substring(0, packageLastName.length());
            } else {
                final String keywordManagementClient = "ManagementClient";
                final String keywordClient = "Client";
                if (clientName.endsWith(keywordManagementClient)) {
                    serviceName = clientName.substring(0, clientName.length() - keywordManagementClient.length());
                } else if (clientName.endsWith(keywordClient)) {
                    serviceName = clientName.substring(0, clientName.length() - keywordClient.length());
                }
            }
        }

        if (CoreUtils.isNullOrEmpty(serviceName)) {
            serviceName = packageLastName;
        }
        return serviceName;
    }

    public static String getArtifactId() {
        JavaSettings settings = JavaSettings.getInstance();
        String artifactId = settings.getArtifactId();
        if (CoreUtils.isNullOrEmpty(artifactId)) {
            artifactId = getArtifactIdFromPackageName(settings.getPackage().toLowerCase(Locale.ROOT));
        }
        return artifactId;
    }

    static String getArtifactIdFromPackageName(String packageName) {
        String artifactId;
        if (packageName.startsWith("com.azure.resourcemanager")) {
            // if namespace looks good, convert it to artifactId directly
            artifactId = packageName.substring("com.".length()).replaceAll(Pattern.quote("."), "-");
        } else {
            String packageLastName = getPackageLastName(packageName).toLowerCase(Locale.ROOT);
            artifactId = String.format("azure-resourcemanager-%1$s-generated", packageLastName);
        }
        return artifactId;
    }

    private static String getPackageLastName(String packageName) {
        String packageLastName = packageName;
        if (packageLastName.endsWith(".generated")) {
            packageLastName = packageLastName.substring(0, packageLastName.lastIndexOf("."));
        }
        int pos = packageLastName.lastIndexOf(".");
        if (pos != -1 && pos != packageLastName.length() - 1) {
            packageLastName = packageLastName.substring(pos + 1);
        }
        return packageLastName;
    }

    public static IType getFluentWrapperType(IType clientType) {
        IType wrapperType = clientType;
        if (clientType instanceof ClassType) {
            ClassType type = (ClassType) clientType;
            if (FluentUtils.isInnerClassType(type)) {
                wrapperType = FluentUtils.resourceModelInterfaceClassType(type);
            }
        } else if (clientType instanceof ListType) {
            ListType type = (ListType) clientType;
            IType wrapperElementType = getFluentWrapperType(type.getElementType());
            wrapperType = wrapperElementType == type.getElementType() ? type : new ListType(wrapperElementType);
        } else if (clientType instanceof MapType) {
            MapType type = (MapType) clientType;
            IType wrapperElementType = getFluentWrapperType(type.getValueType());
            wrapperType = wrapperElementType == type.getValueType() ? type : new MapType(wrapperElementType);
        } else if (clientType instanceof GenericType) {
            GenericType type = (GenericType) clientType;
            if (PagedIterable.class.getSimpleName().equals(type.getName())) {
                IType wrapperItemType = getFluentWrapperType(type.getTypeArguments()[0]);
                wrapperType = wrapperItemType == type.getTypeArguments()[0] ? type : GenericType.PagedIterable(wrapperItemType);
            } else if (Response.class.getSimpleName().equals(type.getName())) {
                IType wrapperItemType = getFluentWrapperType(type.getTypeArguments()[0]);
                wrapperType = wrapperItemType == type.getTypeArguments()[0] ? type : GenericType.Response(wrapperItemType);
            }
        }
        return wrapperType;
    }

    public static String getSingular(String name) {
        if (name == null) {
            return null;
        }

        if (name.endsWith("ies")) {
            return name.substring(0, name.length() - 3) + 'y';
        } else if (name.endsWith("sses")) {
            return name.substring(0, name.length() - 2);
        } else if (name.endsWith("s") && !name.endsWith("ss")) {
            return name.substring(0, name.length() - 1);
        } else {
            return name;
        }
    }

    public static boolean isContextParameter(ClientMethodParameter parameter) {
        return ClassType.Context.getName().equals(parameter.getClientType().toString());
    }

    public static ClientModel getClientModel(String name) {
        ClientModel clientModel = null;
        for (ClientModel model : FluentStatic.getClient().getModels()) {
            if (name.equals(model.getName())) {
                clientModel = model;
                break;
            }
        }
        if (clientModel == null) {
            Optional<ClientModel> modelOpt = ResourceClientModel.getResourceClientModel(name);
            if (modelOpt.isPresent()) {
                clientModel = modelOpt.get();
            }
        }
        return clientModel;
    }

    public static String loadTextFromResource(String filename, String... replacements) {
        String text = "";
        try (InputStream inputStream = UtilsTemplate.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream != null) {
                text = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining(System.lineSeparator()));
                if (!text.isEmpty()) {
                    text += System.lineSeparator();
                }
            }

            if (replacements.length > 0) {
                if (replacements.length % 2 == 0) {
                    // replacement in template
                    for (int i = 0; i < replacements.length; i += 2) {
                        String key = replacements[i];
                        String value = replacements[i+1];
                        text = text.replaceAll(Pattern.quote("{{" + key + "}}"), value);
                    }
                } else {
                    logger.warn("Replacements skipped due to incorrect length: {}", Arrays.asList(replacements));
                }
            }
            return text;
        } catch (IOException e) {
            logger.warn("Failed to read file '{}'", filename);
            throw new IllegalStateException(e);
        }
    }

    public static String getLocalMethodArgument(ClientMethodParameter parameter,
                                                Set<ClientMethodParameter> inputParametersSet, ResourceLocalVariables localVariables,
                                                FluentResourceModel resourceModel, FluentCollectionMethod collectionMethod) {
        return getLocalMethodArgument(parameter, inputParametersSet, localVariables, resourceModel, collectionMethod, null);
    }

    public static String getLocalMethodArgument(ClientMethodParameter parameter,
                                                Set<ClientMethodParameter> inputParametersSet, ResourceLocalVariables localVariables,
                                                FluentResourceModel resourceModel, FluentCollectionMethod collectionMethod,
                                                ResourceLocalVariables resourceLocalVariablesDefinedInClass) {
        if (inputParametersSet.contains(parameter)) {
            // input parameter
            return parameter.getName();
        } else if (resourceModel.getInnerModel().getName().equals(parameter.getClientType().toString())) {
            // body payload, use innerModel
            return String.format("this.%1$s()", ModelNaming.METHOD_INNER_MODEL);
        } else if (ClassType.Context == parameter.getClientType()) {
            // context not in input, use NONE
            return "Context.NONE";
        } else {
            // local variables
            LocalVariable localVariable = localVariables.getLocalVariableByMethodParameter(parameter);
            if (localVariable == null) {
                throw new IllegalStateException(String.format("local variable not found for method %1$s, model %2$s, parameter %3$s, available local variables %4$s",
                        collectionMethod.getMethodName(),
                        resourceModel.getName(),
                        parameter.getName(),
                        localVariables.getLocalVariablesMap().entrySet().stream().collect(Collectors.toMap(e -> e.getKey().getName(), e -> e.getValue().getName()))));
            }
            String name = localVariable.getName();

            // there could be case that the variable used in method (ResourceUpdate or ResourceRefresh) is different from the one defined in class (by ResourceCreate)
            LocalVariable localVariableDefinedInClass = resourceLocalVariablesDefinedInClass == null
                    ? null
                    : resourceLocalVariablesDefinedInClass.getLocalVariablesMap().values().stream()
                    .filter(var -> localVariable.getName().equals(var.getName())).findFirst().orElse(null);
            if (localVariableDefinedInClass != null
                    && !Objects.equals(localVariableDefinedInClass.getVariableType().toString(), localVariable.getVariableType().toString())) {
                if (localVariableDefinedInClass.getVariableType() == ClassType.String) {
                    name = String.format("%1$s.fromString(%2$s)", localVariable.getVariableType().toString(), name);
                } else if (localVariable.getVariableType() == ClassType.String) {
                    name = String.format("%1$s.toString()", name);
                }
            }
            return name;
        }
    }

    public static boolean modelHasLocationProperty(FluentResourceModel resourceModel) {
        return resourceModel.hasProperty(ResourceTypeName.FIELD_LOCATION)
                && resourceModel.getProperty(ResourceTypeName.FIELD_LOCATION).getFluentType() == ClassType.String;
    }

    public static boolean modelHasLocationProperty(List<ClientModelProperty> properties) {
        return properties.stream()
                .anyMatch(p -> ResourceTypeName.FIELD_LOCATION.equals(p.getName()) && p.getClientType() == ClassType.String);
    }
}
