package com.azure.autorest.util;

import com.azure.autorest.extension.base.model.codemodel.AnySchema;
import com.azure.autorest.extension.base.model.codemodel.Header;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.core.http.HttpMethod;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class SchemaUtil {
    private SchemaUtil() {
    }

    // TODO: P3 support multiple inheritance
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

    public static IType getOperationResponseType(Operation operation) {
        Schema responseBodySchema = SchemaUtil.getLowestCommonParent(
                operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
        IType responseBodyType = Mappers.getSchemaMapper().map(responseBodySchema);

        if (responseBodyType == null) {
            if (operation.getRequests().stream().anyMatch(req -> HttpMethod.HEAD.name().equalsIgnoreCase(req.getProtocol().getHttp().getMethod()))
                    && operation.getResponses().stream().flatMap(r -> r.getProtocol().getHttp().getStatusCodes().stream()).anyMatch(c -> c.equals("404"))) {
                // Azure core would internally convert the response status code to boolean.
                responseBodyType = PrimitiveType.Boolean;
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
     * @param operation the operation
     * @return whether response of the operation contains headers
     */
    public static boolean responseContainsHeaderSchemas(Operation operation) {
        return operation.getResponses().stream()
                .filter(r -> r.getProtocol() != null && r.getProtocol().getHttp() != null && r.getProtocol().getHttp().getHeaders() != null)
                .flatMap(r -> r.getProtocol().getHttp().getHeaders().stream().map(Header::getSchema))
                .anyMatch(Objects::nonNull);
    }

    public static String mergeDescription(String summary, String description) {
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
        return String.join(" ", parts);
    }
}
