package com.azure.autorest.android.mapper;

import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AndroidOptionalParameterMapper {
    private String packageName;
    private String methodName;
    private List<Parameter> parameters = new ArrayList<Parameter>();

    public AndroidOptionalParameterMapper packageName(String packageName) {
        this.packageName = packageName;
        return this;
    }
    public AndroidOptionalParameterMapper methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public AndroidOptionalParameterMapper parameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public boolean hasOptionalParameters() {
        return parameters.size() > 0;
    }

    public String getModelTypeName() {
        return CodeNamer.toPascalCase(String.format("%1$sOptions", methodName));
    }

    class AdHocType implements IType  {
        private String typeName;
        AdHocType(String typeName) {
            this.typeName = typeName;
        }
        @Override
        public IType getClientType() {
            return this;
        }

        @Override
        public String convertToClientType(String expression) {
            return typeName;
        }

        @Override
        public String convertFromClientType(String expression) {
            return expression;
        }

        @Override
        public IType asNullable() {
            return this;
        }

        @Override
        public boolean contains(IType type) {
            return false;
        }

        @Override
        public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        }

        @Override
        public String defaultValueExpression(String sourceExpression) {
            return null;
        }

        @Override
        public String validate(String expression) {
            return expression;
        }

        public String toString() {
            return typeName;
        }
    }

    public IType getModelType() {
        return new AdHocType(getModelTypeName());
    }

    public ClientModel build() {
        ClientModel.Builder builder = new ClientModel.Builder();
        builder.description(String.format("Options for %1$s", methodName))
                .packageName(packageName)
                .name(getModelTypeName())
                .imports(new ArrayList<>())
                .isPolymorphic(false)
                .derivedModels(new ArrayList<>())
                .needsFlatten(false)
                .serializedName(getModelTypeName())
                .properties(parameters.stream().map(parameter -> {
                    ClientMethodParameter clientMethodParameter = Mappers.getClientParameterMapper().map(parameter);

                    List<String> annotationArgumentList = new ArrayList<String>() {{
                        add(String.format("value = \"%s\"", clientMethodParameter.getName()));
                    }};
                    annotationArgumentList.add("required = false");

                    ClientModelProperty.Builder propertyBuilder = new ClientModelProperty.Builder();
                    propertyBuilder.description(parameter.getDescription())
                            .name(clientMethodParameter.getName())
                            .clientType(clientMethodParameter.getClientType())
                            .defaultValue(clientMethodParameter.getDefaultValue())
                            .wireType(clientMethodParameter.getWireType())
                            .annotationArguments(String.join(", ", annotationArgumentList))
                            .isReadOnly(false);

                    return propertyBuilder.build();
                }).collect(Collectors.toList()));

        return builder.build();
    }
}
