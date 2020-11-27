package com.azure.autorest.android.model;

import com.azure.autorest.mapper.ModelPropertyMapper;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;

import java.util.Set;

public class AndroidClientModelProperty extends ClientModelProperty {

    private AndroidClientModelProperty(String name, String description, String annotationArguments, boolean isXmlAttribute,
                                       String xmlName, String xmlNamespace, String serializedName, boolean isXmlWrapper, String xmlListElementName,
                                       IType wireType, IType clientType, boolean isConstant, String defaultValue, boolean isReadOnly,
                                       boolean isRequired, String headerCollectionPrefix, boolean isAdditionalProperties) {
        super(name,
                description,
                annotationArguments,
                isXmlAttribute,
                xmlName,
                xmlNamespace,
                serializedName,
                isXmlWrapper,
                xmlListElementName,
                wireType,
                clientType,
                isConstant,
                defaultValue,
                isReadOnly,
                isRequired,
                headerCollectionPrefix,
                isAdditionalProperties);
    }

    @Override
    protected void addCoreUtilsImports(Set<String> imports) {
        imports.add("com.azure.android.core.util.CoreUtil");
    }

    public static class Builder extends ClientModelProperty.Builder {
        public AndroidClientModelProperty build() {
            return new AndroidClientModelProperty(name,
                    description,
                    annotationArguments,
                    isXmlAttribute,
                    xmlName,
                    xmlNamespace,
                    serializedName,
                    isXmlWrapper,
                    xmlListElementName,
                    wireType,
                    clientType,
                    isConstant,
                    defaultValue,
                    isReadOnly,
                    isRequired,
                    headerCollectionPrefix,
                    isAdditionalProperties);
        }
    }
}
