// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.OrSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.autorest.model.clientmodel.UnionModel;
import com.azure.autorest.model.clientmodel.UnionModels;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UnionModelMapper implements IMapper<OrSchema, UnionModel> {

    private static final UnionModelMapper INSTANCE = new UnionModelMapper();
    private final UnionModels serviceModels = UnionModels.getInstance();

    protected UnionModelMapper() {
    }

    public static UnionModelMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public UnionModel map(OrSchema type) {
        ClassType modelType = Mappers.getUnionMapper().map(type);
        String modelName = modelType.getName();
        UnionModel model = serviceModels.getModel(modelType.getName());
        if (model == null) {
            UnionModel.Builder builder = new UnionModel.Builder()
                    .name(modelName)
                    .packageName(modelType.getPackage())
                    .implementationDetails(new ImplementationDetails.Builder()
                            .usages(SchemaUtil.mapSchemaContext(type.getUsage()))
                            .build());

            String summary = type.getSummary();
            String description = type.getLanguage().getJava() == null ? null : type.getLanguage().getJava().getDescription();
            if (CoreUtils.isNullOrEmpty(summary) && CoreUtils.isNullOrEmpty(description)) {
                builder.description(String.format("The %s model.", type.getLanguage().getJava().getName()));
            } else {
                builder.description(SchemaUtil.mergeSummaryWithDescription(summary, description));
            }

            HashSet<String> modelImports = new HashSet<>();

            // properties
            List<ClientModelProperty> properties = new ArrayList<>();
            for (Property property : type.getAnyOf()) {
//                // import
//                IType propertyType = Mappers.getSchemaMapper().map(property.getSchema());
//                if (!property.isRequired()) {
//                    propertyType = propertyType.asNullable();
//                }
//                propertyType.addImportsTo(modelImports, false);
//
//                IType propertyClientType = Mappers.getSchemaMapper().map(property.getSchema()).getClientType();
//                propertyClientType.addImportsTo(modelImports, false);

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
