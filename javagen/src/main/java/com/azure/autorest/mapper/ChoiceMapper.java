package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.codemodel.ChoiceSchema;
import com.azure.autorest.model.codemodel.ChoiceValue;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoiceMapper implements IMapper<ChoiceSchema, IType> {
    private ChoiceMapper() {
    }

    Map<ChoiceSchema, IType> parsed = new HashMap<>();

    @Override
    public IType map(ChoiceSchema enumType) {
        if (enumType == null) {
            return null;
        }

        JavaSettings settings = JavaSettings.getInstance();

        if (enumType == null)
        {
            return null;
        }
        if (parsed.containsKey(enumType))
        {
            return parsed.get(enumType);
        }
        IType _itype;
        String enumTypeName = enumType.getChoiceType().getPattern();

        if (enumTypeName == null || enumTypeName.isEmpty() || enumTypeName.equals("enum"))
        {
            _itype = ClassType.String;
        }
        else
        {
            String enumSubpackage = (settings.getIsFluent() ? "" : settings.getModelsSubpackage());
            String enumPackage = settings.getPackage() + "." + enumSubpackage;

            enumTypeName = CodeNamer.getTypeName(enumTypeName);

            List<ClientEnumValue> enumValues = new ArrayList<>();
            for (ChoiceValue enumValue : enumType.getChoices())
            {
                String memberName = CodeNamer.getEnumMemberName(enumValue.getValue());
                enumValues.add(new ClientEnumValue(memberName, enumValue.getValue()));
            }

            boolean modelAsString = enumType.getExtensions().getXmsEnum().isModelAsString();
            _itype = new EnumType(enumPackage, enumTypeName, modelAsString, enumValues);
            parsed.put(enumType, _itype);
        }

        return _itype;
    }
}
