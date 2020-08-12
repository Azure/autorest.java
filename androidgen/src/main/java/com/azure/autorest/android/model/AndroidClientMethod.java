package com.azure.autorest.android.model;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MethodPageDetails;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.util.CodeNamer;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AndroidClientMethod extends ClientMethod {
    private AndroidClientMethod(String description, ReturnValue returnValue,
                                String name, List<ClientMethodParameter> parameters,
                                boolean onlyRequiredParameters, ClientMethodType type,
                                ProxyMethod proxyMethod, Map<String, String> validateExpressions,
                                String clientReference, List<String> requiredNullableParameterExpressions,
                                boolean isGroupedParameterRequired, String groupedParameterTypeName,
                                MethodPageDetails methodPageDetails, List<MethodTransformationDetail> methodTransformationDetails) {
        super(description, returnValue,
                name, parameters,
                onlyRequiredParameters, type,
                proxyMethod, validateExpressions,
                clientReference, requiredNullableParameterExpressions,
                isGroupedParameterRequired, groupedParameterTypeName,
                methodPageDetails, methodTransformationDetails);
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        getReturnValue().addImportsTo(imports, includeImplementationImports);

        for (ClientMethodParameter parameter : getParameters()) {
            parameter.addImportsTo(imports, includeImplementationImports);
        }
        ProxyMethod proxyMethod = super.getProxyMethod();
        if (includeImplementationImports) {
            proxyMethod.addImportsTo(imports, includeImplementationImports, settings);
            for (ProxyMethodParameter parameter : proxyMethod.getParameters()) {
                parameter.getClientType().addImportsTo(imports, true);
            }
        }
    }

    @Override
    public List<String> getProxyMethodArguments(JavaSettings settings) {
        List<String> restAPIMethodArguments = getProxyMethod().getParameters()
                .stream()
                .filter(parameter -> {
                    // The Host/Endpoint param will provided at the time of creating Retrofit,
                    // hence not an argument to retrofit method calls.
                    return parameter.getRequestParameterLocation() != RequestParameterLocation.Uri;
                })
                .map(parameter -> {
                    if (parameter.getRequestParameterLocation() == RequestParameterLocation.Body) {
                        // Body Parameter.
                        return "okHttp3RequestBody";
                    } else {
                        // Non Body Parameters.
                        //
                        String parameterName = parameter.getParameterReference();
                        IType parameterWireType = parameter.getWireType();
                        if (parameter.getIsNullable()) {
                            parameterWireType = parameterWireType.asNullable();
                        }
                        IType parameterClientType = parameter.getClientType();

                        if (parameterWireType != ClassType.Base64Url &&
                                (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType)) {
                            parameterWireType = ClassType.String;
                        }

                        String parameterWireName
                                = parameterClientType != parameterWireType ?
                                    String.format("%1$sConverted", CodeNamer.toCamelCase(parameterName)) : parameterName;

                        String result;
                        if (getMethodTransformationDetails()
                                .stream()
                                .anyMatch(d -> d.getOutParameter().getName().equals(parameterName + "1"))) {
                            result = getMethodTransformationDetails()
                                    .stream()
                                    .filter(d -> d.getOutParameter().getName().equals(parameterName + "1"))
                                    .findFirst().get()
                                    .getOutParameter()
                                    .getName();
                        } else {
                            result = parameterWireName;
                        }
                        return result;
                    }
                })
                .collect(Collectors.toList());
        return restAPIMethodArguments;
    }

    public static class Builder extends ClientMethod.Builder {
        public AndroidClientMethod build() {
            return new AndroidClientMethod(super.description, super.returnValue,
                    super.name, super.parameters,
                    super.onlyRequiredParameters, super.type,
                    super.proxyMethod, super.validateExpressions,
                    super.clientReference, super.requiredNullableParameterExpressions,
                    super.isGroupedParameterRequired, super.groupedParameterTypeName,
                    super.methodPageDetails, super.methodTransformationDetails);
        }
    }
}
