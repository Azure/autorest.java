// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.extension.base.model.codemodel.AnySchema;
import com.azure.autorest.extension.base.model.codemodel.Header;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SchemaContext;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.autorest.model.clientmodel.IterableType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.core.http.HttpMethod;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class SchemaUtil {
    private SchemaUtil() {
    }

    public static Schema getLowestCommonParent(List<Schema> schemas) {
        if (schemas == null || schemas.isEmpty()) {
            return null;
        }
        LinkedList<Schema> chain = null;
        for (Schema schema : schemas) {
            if (chain == null) {
                chain = new LinkedList<>();
                chain.addFirst(schema);
                while (schema instanceof ObjectSchema
                        && ((ObjectSchema) schema).getParents() != null
                        && ((ObjectSchema) schema).getParents().getImmediate() != null
                        && !((ObjectSchema) schema).getParents().getImmediate().isEmpty()) {
                    // Assume always inheriting from an ObjectSchema and no multiple inheritance
                    schema = ((ObjectSchema) schema).getParents().getImmediate().get(0);
                    chain.addFirst(schema);
                }
            } else {
                Stack<Schema> newChain = new Stack<>();
                newChain.push(schema);
                while (schema instanceof ObjectSchema
                        && ((ObjectSchema) schema).getParents() != null
                        && ((ObjectSchema) schema).getParents().getImmediate() != null
                        && !((ObjectSchema) schema).getParents().getImmediate().isEmpty()) {
                    // Assume always inheriting from an ObjectSchema and no multiple inheritance
                    schema = ((ObjectSchema) schema).getParents().getImmediate().get(0);
                    newChain.push(schema);
                }
                int i = 0;
                while (!newChain.empty() && i < chain.size()) {
                    Schema top = chain.get(i);
                    Schema compare = newChain.pop();
                    if (top == compare) {
                        i++;
                    } else {
                        for (; i < chain.size(); i++) {
                            chain.remove(i);
                        }
                    }
                }
            }
        }
        return chain.isEmpty() ? new AnySchema() : chain.getLast();
    }

    /*
     * Returns raw response type.
     * In case of binary response:
     *   For DPG, returns BinaryData
     *   For vanilla/mgmt, returns InputStream
     */
    public static IType getOperationResponseType(Operation operation, JavaSettings settings) {
        Schema responseBodySchema = SchemaUtil.getLowestCommonParent(
                operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
        IType responseBodyType = Mappers.getSchemaMapper().map(responseBodySchema);

        if (responseBodyType == null) {
            if (operation.getRequests().stream().anyMatch(req -> HttpMethod.HEAD.name().equalsIgnoreCase(req.getProtocol().getHttp().getMethod()))
                    && operation.getResponses().stream().flatMap(r -> r.getProtocol().getHttp().getStatusCodes().stream()).anyMatch(c -> c.equals("404"))) {
                // Azure core would internally convert the response status code to boolean.
                responseBodyType = PrimitiveType.Boolean;
            } else if (containsBinaryResponse(operation)) {
                if (settings.isDataPlaneClient() || !settings.isInputStreamForBinary()) {
                    responseBodyType = ClassType.BinaryData;
                } else {
                    responseBodyType = ClassType.InputStream;
                }
            } else {
                responseBodyType = PrimitiveType.Void;
            }
        }

        return responseBodyType;
    }

    public static Property getDiscriminatorProperty(ObjectSchema compositeType) {
        Property discriminatorProperty = null;
        if (compositeType.getDiscriminator() != null) {
            discriminatorProperty = compositeType.getDiscriminator().getProperty();
        } else {
            for (Schema parent : compositeType.getParents().getAll()) {
                if (parent instanceof ObjectSchema && ((ObjectSchema) parent).getDiscriminator() != null) {
                    discriminatorProperty = ((ObjectSchema) parent).getDiscriminator().getProperty();
                    break;
                }
            }
        }
        if (discriminatorProperty == null) {
            throw new IllegalArgumentException(String.format("discriminator not found in type %s and its parents",
                compositeType.getLanguage().getJava().getName()));
        }

        return discriminatorProperty;
    }

    public static String getDiscriminatorSerializedName(ObjectSchema compositeType) {
        String discriminator = null;
        if (compositeType.getDiscriminator() != null) {
            discriminator = compositeType.getDiscriminator().getProperty().getSerializedName();
        } else if (compositeType.getDiscriminatorValue() != null) {
            for (Schema parent : compositeType.getParents().getAll()) {
                if (parent instanceof ObjectSchema && ((ObjectSchema) parent).getDiscriminator() != null) {
                    discriminator = ((ObjectSchema) parent).getDiscriminator().getProperty().getSerializedName();
                    break;
                }
            }
        }
        if (discriminator == null) {
            throw new IllegalArgumentException(String.format("discriminator not found in type %s and its parents",
                    compositeType.getLanguage().getJava().getName()));
        }
        return discriminator;
    }

    /**
     * Whether response contains header schemas.
     * <p>
     * Response headers will be omitted, as polling method has return type as SyncPoller or PollerFlux, not Response.
     *
     * @param operation the operation
     * @param settings the JavaSetting object
     * @return whether response of the operation contains headers
     */
    public static boolean responseContainsHeaderSchemas(Operation operation, JavaSettings settings) {
        return operation.getResponses().stream()
                .filter(r -> r.getProtocol() != null && r.getProtocol().getHttp() != null && r.getProtocol().getHttp().getHeaders() != null)
                .flatMap(r -> r.getProtocol().getHttp().getHeaders().stream().map(Header::getSchema))
                .anyMatch(Objects::nonNull)
                && notFluentLRO(operation, settings) && notDataPlaneLRO(operation, settings);
    }

    /**
     * Merge summary and description.
     * <p>
     * If summary exists, it will take 1st line, and description will be moved to 2nd line in Javadoc.
     *
     * @param summary the summary text.
     * @param description the description text.
     * @return the merged text for Javadoc.
     */
    public static String mergeSummaryWithDescription(String summary, String description) {
        if (Objects.equals(summary, description)) {
            summary = null;
        }

        List<String> parts = new ArrayList<>();
        if (!CoreUtils.isNullOrEmpty(summary)) {
            parts.add(summary);
        }
        if (!CoreUtils.isNullOrEmpty(description)) {
            parts.add(description);
        }
        return String.join("\n\n", parts);
    }

    public static IType removeModelFromParameter(RequestParameterLocation parameterRequestLocation, IType type) {
        IType returnType = type;
        if (parameterRequestLocation == RequestParameterLocation.BODY) {
            returnType = ClassType.BinaryData;
        } else if (!(returnType instanceof PrimitiveType)) {
            if(type instanceof EnumType) {
                returnType = ClassType.String;
            }
            if(type instanceof IterableType && ((IterableType) type).getElementType() instanceof EnumType) {
                returnType = new IterableType(ClassType.String);
            }
            if(type instanceof ListType && ((ListType) type).getElementType() instanceof EnumType) {
                returnType = new ListType(ClassType.String);
            }
        }
        return returnType;
    }

    /**
     * Maps CADL model to model from external packages.
     *
     * @param compositeType the CADL model.
     * @return the model from external packages, if available.
     */
    public static Optional<ClassType> mapExternalModel(ObjectSchema compositeType) {
        // For now, the external packages is the azure-core

        ClassType classType = null;
        if (compositeType.getLanguage() != null && compositeType.getLanguage().getDefault() != null) {
            String namespace = compositeType.getLanguage().getDefault().getNamespace();
            String name = compositeType.getLanguage().getDefault().getName();

            if (!CoreUtils.isNullOrEmpty(namespace) && !CoreUtils.isNullOrEmpty(name)) {
                if ("Azure.Core.Foundations".equals(namespace)) {
                    // https://github.com/Azure/cadl-azure/blob/main/packages/cadl-azure-core/lib/operations.cadl
                    if ("Error".equals(name)) {
                        classType = ClassType.ResponseError;
                    } else if ("InnerError".equals(name)) {
                        // InnerError is not public, but usually it is only referenced from Error
                        classType = ClassType.ResponseInnerError;
                    }
                    // ErrorResponse is not available
                }
            }
        }
        return Optional.ofNullable(classType);
    }

    /**
     * Maps set of SchemaContext to set of ImplementationDetails.Usage.
     *
     * @param schemaContexts the set of SchemaContext.
     * @return the set of ImplementationDetails.Usage.
     */
    public static Set<ImplementationDetails.Usage> mapSchemaContext(Set<SchemaContext> schemaContexts) {
        if (schemaContexts == null) {
            return Collections.emptySet();
        }
        return schemaContexts.stream()
                .map(c -> ImplementationDetails.Usage.fromValue(c.value()))
                .collect(Collectors.toSet());
    }

    private static boolean containsBinaryResponse(Operation operation) {
        return operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()));
    }

    // SyncPoller or PollerFlux does not contain full Response and hence does not have headers
    private static boolean notFluentLRO(Operation operation, JavaSettings settings) {
        return !(settings.isFluent() && operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation());
    }

    private static boolean notDataPlaneLRO(Operation operation, JavaSettings settings) {
        return !(settings.isDataPlaneClient() && operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation());
    }
}
