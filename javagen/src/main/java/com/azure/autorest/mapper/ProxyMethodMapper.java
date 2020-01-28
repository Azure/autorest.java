package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.Header;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProxyMethodMapper implements IMapper<Operation, ProxyMethod> {
    private static final List<IType> unixTimeTypes = Arrays.asList(PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong, ClassType.UnixTimeDateTime);
    private static final List<IType> returnValueWireTypeOptions = Stream.concat(Stream.of(ClassType.Base64Url, ClassType.DateTimeRfc1123), unixTimeTypes.stream()).collect(Collectors.toList());
    private static ProxyMethodMapper instance = new ProxyMethodMapper();

    private Map<Operation, ProxyMethod> parsed = new HashMap<Operation, ProxyMethod>();
    private ProxyMethodMapper() {
    }

    public static ProxyMethodMapper getInstance() {
        return instance;
    }

    @Override
    public ProxyMethod map(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();
        if (parsed.containsKey(operation)) {
            return parsed.get(operation);
        }

        ProxyMethod.Builder builder = new ProxyMethod.Builder()
                .description(operation.getDescription())
                .name(operation.getLanguage().getJava().getName())
                .urlPath(operation.getRequest().getProtocol().getHttp().getPath())
                .httpMethod(HttpMethod.valueOf(operation.getRequest().getProtocol().getHttp().getMethod().toUpperCase()))
                .isResumable(false);

        if (operation.getRequest().getProtocol().getHttp().getMediaTypes() != null && !operation.getRequest().getProtocol().getHttp().getMediaTypes().isEmpty()) {
            builder.requestContentType(operation.getRequest().getProtocol().getHttp().getMediaTypes().get(0));
        } else {
            builder.requestContentType("application/json");
        }

        builder.responseExpectedStatusCodes(operation.getResponses().stream()
                .flatMap(r -> r.getProtocol().getHttp().getStatusCodes().stream())
                .map(s -> s.replaceAll("'", ""))
                .map(s -> HttpResponseStatus.valueOf(Integer.parseInt(s)))
                .sorted().collect(Collectors.toList()));

        Schema responseBodySchema = SchemaUtil.getLowestCommonParent(
                operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
        IType responseBodyType = Mappers.getSchemaMapper().map(responseBodySchema);

        if (responseBodyType == null) {
            responseBodyType = PrimitiveType.Void;
        }

        if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
            // BinaryResponse
            IType singleValueType = ClassType.StreamResponse;
            builder.returnType(GenericType.Mono(singleValueType));
        } else if (operation.getResponses().stream()
                .filter(r -> r.getProtocol() != null && r.getProtocol().getHttp() != null && r.getProtocol().getHttp().getHeaders() != null)
                .flatMap(r -> r.getProtocol().getHttp().getHeaders().stream().map(Header::getSchema))
                .anyMatch(Objects::nonNull)) {
            // SchemaResponse
            // method with schema in headers would require a ClientResponse
            ClassType clientResponseClassType = ClientMapper.getClientResponseClassType(operation, settings);
            builder.returnType(GenericType.Mono(clientResponseClassType));
        } else {
            IType singleValueType;
            if (responseBodyType.equals(GenericType.FluxByteBuffer)) {
                singleValueType = ClassType.StreamResponse;
            } else if (responseBodyType.equals(PrimitiveType.Void)) {
                singleValueType = GenericType.Response(ClassType.Void);
            } else {
                singleValueType = GenericType.BodyResponse(responseBodyType);
            }
            builder.returnType(GenericType.Mono(singleValueType));
        }

        ClassType errorType = null;
        if (operation.getExceptions() != null && !operation.getExceptions().isEmpty()) {
            errorType = (ClassType) Mappers.getSchemaMapper().map(operation.getExceptions().get(0).getSchema());
        }

        if (settings.isAzureOrFluent() && (errorType == null || errorType.getName().equals("CloudError"))) {
            builder.unexpectedResponseExceptionType(ClassType.CloudException);
        } else if (errorType != null) {
            String exceptionName = errorType.getExtensions() == null ? null : errorType.getExtensions().getXmsClientName();
            if (exceptionName == null || exceptionName.isEmpty()) {
                exceptionName = errorType.getName();
                // TODO: Fluent
//                if (settings.isFluent() && exceptionName != null && !exceptionName.isEmpty() && errorType.IsInnerModelType)
//                {
//                    exceptionName += "Inner";
//                }
                exceptionName += "Exception";
            }

            String exceptionPackage;
            if (settings.isCustomType(exceptionName)) {
                exceptionPackage = settings.getPackage(settings.getCustomTypesSubpackage());
            }
//            else if (settings.isFluent())
//            {
//                if (((CompositeTypeJv) autoRestExceptionType).IsInnerModel)
//                {
//                    exceptionPackage = settings.GetPackage(settings.ImplementationSubpackage);
//                }
//            }
            else {
                exceptionPackage = settings.getPackage(settings.getModelsSubpackage());
            }

            builder.unexpectedResponseExceptionType(new ClassType(exceptionPackage, exceptionName, null, null, false));
        } else {
            builder.unexpectedResponseExceptionType(ClassType.HttpResponseException);
        }

        List<ProxyMethodParameter> parameters = new ArrayList<>();
        for (Parameter parameter : operation.getRequest().getParameters().stream()
                .filter(p -> p.getProtocol() != null && p.getProtocol().getHttp() != null).collect(Collectors.toList())) {
            parameters.add(Mappers.getProxyParameterMapper().map(parameter));
        }
        builder.parameters(parameters);

        AtomicReference<IType> responseBodyTypeReference = new AtomicReference<>(responseBodyType);
        builder.returnValueWireType(returnValueWireTypeOptions
            .stream()
            .filter(type -> responseBodyTypeReference.get().contains(type))
            .findFirst()
            .orElse(null));

        ProxyMethod proxyMethod = builder.build();

        parsed.put(operation, proxyMethod);
        return proxyMethod;
    }
}
