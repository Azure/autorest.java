package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidClientMethod;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.List;

public class AndroidClientMethodMapper extends ClientMethodMapper {

    private ClientModel optionalParametersModel;

    public static AndroidClientMethodMapper getInstance() {
        return new AndroidClientMethodMapper();
    }

    public void addModelsTo(List<ClientModel> clientModels) {
        if (optionalParametersModel != null) {
        return instance;
    }

    public void addModelsTo(List<ClientModel> clientModels) {
        clientModels.addAll(optionalParametersModels);
            clientModels.add(optionalParametersModel);
        }
    }

    @Override
    protected boolean shouldCollapseOptionalParameters() {
        return true;
    }

    @Override
    protected void collapseOptionalParameters(String methodName, List<Parameter> optionalParameters, List<ClientMethodParameter> parameters) {
        if (optionalParameters.size() == 1) {
            Parameter parameterModel = optionalParameters.get(0);
            ClientMethodParameter clientMethodParameter = Mappers.getClientParameterMapper().map(parameterModel);
            parameters.add(clientMethodParameter);
            return;
        }

        JavaSettings settings = JavaSettings.getInstance();
        String packageName = settings.getPackage(settings.getModelsSubpackage());
        AndroidOptionalParameterMapper optionalParameterMapper = new AndroidOptionalParameterMapper();
        optionalParametersModel = optionalParameterMapper.packageName(packageName).methodName(methodName).parameters(optionalParameters).build();

        String typeName = optionalParametersModel.getName();
        ClientMethodParameter.Builder optionalParameterBuilder = new ClientMethodParameter.Builder();
        optionalParameterBuilder.name(CodeNamer.toCamelCase(typeName))
                .description(String.format("Options for %1$s", methodName))
                .annotations(new ArrayList<>())
                .wireType(optionalParameterMapper.getModelType());

        parameters.add(optionalParameterBuilder.build());
    }

    @Override
    protected ClientMethod.Builder createClientMethodBuilder() {
        return new AndroidClientMethod.Builder();
    }
}
