// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceValue;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains utility methods to help map from modelerfour to Java Autorest.
 */
final class MapperUtils {
    static IType createEnumType(ChoiceSchema enumType, boolean expandable) {
        JavaSettings settings = JavaSettings.getInstance();
        String enumTypeName = enumType.getLanguage().getJava().getName();

        if (enumTypeName == null || enumTypeName.isEmpty() || enumTypeName.equals("enum")) {
            return ClassType.String;
        } else {
            String enumSubpackage = settings.getModelsSubpackage();
            if (settings.isCustomType(enumTypeName)) {
                enumSubpackage = settings.getCustomTypesSubpackage();
            }
            String enumPackage = settings.getPackage(enumSubpackage);

            String summary = enumType.getSummary();
            String description = enumType.getLanguage().getJava() == null ? null : enumType.getLanguage().getJava().getDescription();
            description = SchemaUtil.mergeSummaryWithDescription(summary, description);
            if (CoreUtils.isNullOrEmpty(description)) {
                description = "Defines values for " + enumTypeName + ".";
            }

            List<ClientEnumValue> enumValues = new ArrayList<>();
            for (ChoiceValue enumValue : enumType.getChoices()) {
                String enumName = enumValue.getValue();
                String enumDescription = null;
                if (!settings.isFluent()) {
                    if (enumValue.getLanguage() != null && enumValue.getLanguage().getJava() != null
                        && enumValue.getLanguage().getJava().getName() != null) {
                        enumName = enumValue.getLanguage().getJava().getName();
                        enumDescription = enumValue.getLanguage().getJava().getDescription();
                    } else if (enumValue.getLanguage() != null && enumValue.getLanguage().getDefault() != null
                        && enumValue.getLanguage().getDefault().getName() != null) {
                        enumName = enumValue.getLanguage().getDefault().getName();
                        enumDescription = enumValue.getLanguage().getDefault().getDescription();
                    }
                }
                final String memberName = CodeNamer.getEnumMemberName(enumName);
                long counter = enumValues.stream().filter(v -> v.getName().equals(memberName)).count();
                if (counter > 0) {
                    enumValues.add(new ClientEnumValue(memberName + "_" + counter, enumValue.getValue(), enumDescription));
                } else {
                    enumValues.add(new ClientEnumValue(memberName, enumValue.getValue(), enumDescription));
                }
            }

            return new EnumType.Builder()
                .packageName(enumPackage)
                .name(enumTypeName)
                .description(description)
                .expandable(expandable)
                .values(enumValues)
                .elementType(Mappers.getSchemaMapper().map(enumType.getChoiceType()))
                .implementationDetails(new ImplementationDetails.Builder()
                    .usages(SchemaUtil.mapSchemaContext(enumType.getUsage()))
                    .build())
                .build();
        }
    }

    private MapperUtils() {
    }
}
