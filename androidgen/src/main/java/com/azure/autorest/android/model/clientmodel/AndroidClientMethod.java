package com.azure.autorest.android.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AndroidClientMethod extends ClientMethod {

    public AndroidClientMethod(String description, ReturnValue returnValue, String name,
                               List<ClientMethodParameter> parameters, boolean onlyRequiredParameters,
                               ClientMethodType type, ProxyMethod proxyMethod,
                               Map<String, String> validateExpressions, String clientReference,
                               List<String> requiredNullableParameterExpressions,
                               boolean isGroupedParameterRequired, String groupedParameterTypeName,
                               MethodPageDetails methodPageDetails,
                               List<MethodTransformationDetail> methodTransformationDetails) {
        super(description,
                returnValue,
                name,
                parameters,
                onlyRequiredParameters,
                type,
                proxyMethod,
                validateExpressions,
                clientReference,
                requiredNullableParameterExpressions,
                isGroupedParameterRequired,
                groupedParameterTypeName,
                methodPageDetails,
                methodTransformationDetails);

    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        getReturnValue().addImportsTo(imports, includeImplementationImports);

        for (ClientMethodParameter parameter : getParameters()) {
            parameter.addImportsTo(imports, includeImplementationImports);
        }
    }

    public static class Builder extends ClientMethod.Builder {

        @Override
        public ClientMethod build() {
            return new AndroidClientMethod(
                    description,
                    returnValue,
                    name,
                    parameters,
                    onlyRequiredParameters,
                    type,
                    proxyMethod,
                    validateExpressions,
                    clientReference,
                    requiredNullableParameterExpressions,
                    isGroupedParameterRequired,
                    groupedParameterTypeName,
                    methodPageDetails,
                    methodTransformationDetails);
        }

    }
}
