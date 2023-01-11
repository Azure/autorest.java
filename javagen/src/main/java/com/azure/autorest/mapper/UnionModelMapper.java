// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.OrSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.autorest.util.SchemaUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UnionModelMapper implements IMapper<OrSchema, ClientModel> {

    private static final UnionModelMapper INSTANCE = new UnionModelMapper();
    private final ClientModels serviceModels = ClientModels.getInstance();

    protected UnionModelMapper() {
    }

    public static UnionModelMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ClientModel map(OrSchema type) {
        ClassType modelType = Mappers.getUnionMapper().map(type);
        String modelName = modelType.getName();
        ClientModel model = serviceModels.getModel(modelType.getName());
        if (model == null) {
            ClientModel.Builder builder = new ClientModel.Builder()
                    .name(modelName)
                    .packageName(modelType.getPackage())
                    .type(modelType)
                    .implementationDetails(new ImplementationDetails.Builder()
                            .usages(SchemaUtil.mapSchemaContext(type.getUsage()))
                            .build());

            HashSet<String> modelImports = new HashSet<>();

            // properties
            List<ClientModelProperty> properties = new ArrayList<>();
            for (Property property : type.getAnyOf()) {
                // import
                IType propertyType = Mappers.getSchemaMapper().map(property.getSchema());
                if (!property.isRequired()) {
                    propertyType = propertyType.asNullable();
                }
                propertyType.addImportsTo(modelImports, false);

                IType propertyClientType = Mappers.getSchemaMapper().map(property.getSchema()).getClientType();
                propertyClientType.addImportsTo(modelImports, false);

                // property
                ClientModelProperty modelProperty = Mappers.getModelPropertyMapper().map(property);
                properties.add(modelProperty);
            }
            builder.properties(properties);

            builder.imports(new ArrayList<>(modelImports));

            model = builder.build();

            serviceModels.addModel(model);
        }
        return model;
    }
}
