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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoiceMapper implements IMapper<ChoiceSchema, IType> {
    private static ChoiceMapper instance = new ChoiceMapper();
    Map<ChoiceSchema, IType> parsed = new HashMap<>();

    private ChoiceMapper() {
    }

    public static ChoiceMapper getInstance() {
        return instance;
    }

    @Override
    public IType map(ChoiceSchema enumType) {
        if (enumType == null) {
            return null;
        }

        JavaSettings settings = JavaSettings.getInstance();

        if (parsed.containsKey(enumType)) {
            return parsed.get(enumType);
        }
        IType _itype;
        String enumTypeName = enumType.getLanguage().getJava().getName();

        if (enumTypeName == null || enumTypeName.isEmpty() || enumTypeName.equals("enum")) {
            _itype = ClassType.String;
        } else {
            enumTypeName = CodeNamer.getTypeName(enumTypeName);
            String enumSubpackage = settings.getModelsSubpackage();
            if (settings.isCustomType(enumTypeName)) {
                enumSubpackage = settings.getCustomTypesSubpackage();
            }
            String enumPackage = settings.getPackage(enumSubpackage);

            List<ClientEnumValue> enumValues = new ArrayList<>();
            for (ChoiceValue enumValue : enumType.getChoices()) {
                String enumName = enumValue.getValue();
                if (!settings.isFluent()) {
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

            _itype = new EnumType.Builder()
                    .packageName(enumPackage)
                    .name(enumTypeName)
                    .expandable(true)
                    .values(enumValues)
                    .elementType(Mappers.getSchemaMapper().map(enumType.getChoiceType()))
                    .build();
            parsed.put(enumType, _itype);
        }

        return _itype;
    }
}
