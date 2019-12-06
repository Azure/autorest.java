package com.azure.autorest.transformer;

import com.azure.autorest.extension.base.model.codemodel.AndSchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Schemas;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.util.CodeNamer;

import java.util.List;

public class Transformer {
    public CodeModel transform(CodeModel codeModel) {
        renameCodeModel(codeModel);
        transformSchemas(codeModel.getSchemas());
        transformOperationGroups(codeModel.getOperationGroups(), codeModel);
        return codeModel;
    }

    private void transformSchemas(Schemas schemas) {
        for (ObjectSchema objectSchema : schemas.getObjects()) {
            renameType(objectSchema);
            for (Property property : objectSchema.getProperties()) {
                renameProperty(property);
            }
        }
        for (AndSchema andSchema : schemas.getAnds()) {
            renameType(andSchema);
        }
        for (ChoiceSchema choiceSchema : schemas.getChoices()) {
            renameType(choiceSchema);
        }
        for (SealedChoiceSchema sealedChoiceSchema : schemas.getSealedChoices()) {
            renameType(sealedChoiceSchema);
        }
    }

    private void transformOperationGroups(List<OperationGroup> operationGroups, CodeModel codeModel) {
        for (OperationGroup operationGroup : operationGroups) {
            operationGroup.setCodeModel(codeModel);
            renameMethodGroup(operationGroup);
            for (Operation operation : operationGroup.getOperations()) {
                operation.setOperationGroup(operationGroup);
                renameMethod(operation);
                for (Parameter parameter : operation.getRequest().getParameters()) {
                    parameter.setOperation(operation);
                    renameVariable(parameter);
                }
            }
        }
    }

    private void renameType(Metadata schema) {
        Language language = schema.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getTypeName(language.getName()));
        schema.getLanguage().setJava(java);
    }

    private void renameProperty(Property property) {
        Language language = property.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getPropertyName(language.getName()));
        property.getLanguage().setJava(java);
    }

    private void renameCodeModel(CodeModel codeModel) {
        renameType(codeModel);
        if (codeModel.getLanguage().getJava().getName() == null
                || codeModel.getLanguage().getJava().getName().isEmpty()) {
            codeModel.getLanguage().getJava().setName(CodeNamer.getTypeName(codeModel.getInfo().getTitle()));
        }
    }

    private void renameVariable(Metadata schema) {
        Language language = schema.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getParameterName(language.getName()));
        schema.getLanguage().setJava(java);
    }

    private void renameMethodGroup(Metadata schema) {
        Language language = schema.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getMethodGroupName(language.getName()));
        schema.getLanguage().setJava(java);
    }

    private void renameMethod(Metadata schema) {
        Language language = schema.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getMethodName(language.getName()));
        schema.getLanguage().setJava(java);
    }
}
