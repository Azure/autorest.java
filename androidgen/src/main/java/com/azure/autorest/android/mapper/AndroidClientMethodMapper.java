package com.azure.autorest.android.mapper;

import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.List;

public class AndroidClientMethodMapper extends ClientMethodMapper {

    private List<ClientModel> optionalParametersModels = new ArrayList<>();

    public static AndroidClientMethodMapper getInstance() {
        return new AndroidClientMethodMapper();
    }

    public void addModelsTo(List<ClientModel> clientModels) {
        clientModels.addAll(optionalParametersModels);
    }

    @Override
    protected boolean shouldCollapseOptionalParameters() {
        return true;
    }

    @Override
    protected void collapseOptionalParameters(String methodName, List<Parameter> optionalParameters, List<ClientMethodParameter> parameters) {
        JavaSettings settings = JavaSettings.getInstance();
        String packageName = settings.getPackage(settings.getModelsSubpackage());
        AndroidOptionalParameterMapper optionalParameterMapper = new AndroidOptionalParameterMapper();
        ClientModel optionsModel = optionalParameterMapper.packageName(packageName).methodName(methodName).parameters(optionalParameters).build();
        optionalParametersModels.add(optionsModel);

        String typeName = optionsModel.getName();
        ClientMethodParameter.Builder optionalParameterBuilder = new ClientMethodParameter.Builder();
        optionalParameterBuilder.name(CodeNamer.toCamelCase(typeName))
                .annotations(new ArrayList<>())
                .wireType(optionalParameterMapper.getModelType());

        parameters.add(optionalParameterBuilder.build());
    }
}
