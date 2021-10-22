package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceValue;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChoiceMapper implements IMapper<ChoiceSchema, IType> {
    private static final ChoiceMapper INSTANCE = new ChoiceMapper();
    Map<ChoiceSchema, IType> parsed = new ConcurrentHashMap<>();

    private ChoiceMapper() {
    }

    public static ChoiceMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public IType map(ChoiceSchema enumType) {
        if (enumType == null) {
            return null;
        }

        return parsed.computeIfAbsent(enumType, this::createChoiceType);
    }

    private IType createChoiceType(ChoiceSchema enumType) {
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

            List<ClientEnumValue> enumValues = new ArrayList<>();
            for (ChoiceValue enumValue : enumType.getChoices()) {
                String enumName = enumValue.getValue();
                if (!settings.isFluent()) {
                    // there exists cases that namer in modelerfour doing a really poor job on enum values,
                    // hence for Fluent still do this on raw enum values
                    if (enumValue.getLanguage() != null && enumValue.getLanguage().getJava() != null
                        && enumValue.getLanguage().getJava().getName() != null) {
                        enumName = enumValue.getLanguage().getJava().getName();
                    } else if (enumValue.getLanguage() != null && enumValue.getLanguage().getDefault() != null
                        && enumValue.getLanguage().getDefault().getName() != null) {
                        enumName = enumValue.getLanguage().getDefault().getName();
                    }
                }
                final String memberName = CodeNamer.getEnumMemberName(enumName);
                long counter = enumValues.stream().filter(v -> v.getName().equals(memberName)).count();
                if (counter > 0) {
                    enumValues.add(new ClientEnumValue(memberName + "_" + counter, enumValue.getValue()));
                } else {
                    enumValues.add(new ClientEnumValue(memberName, enumValue.getValue()));
                }
            }

            return new EnumType.Builder()
                .packageName(enumPackage)
                .name(enumTypeName)
                .expandable(true)
                .values(enumValues)
                .elementType(Mappers.getSchemaMapper().map(enumType.getChoiceType()))
                .build();
        }
    }
}
