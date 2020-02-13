package com.azure.autorest.transformer;

import com.azure.autorest.extension.base.model.codemodel.AndSchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Protocol;
import com.azure.autorest.extension.base.model.codemodel.Protocols;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Schemas;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.StringSchema;
import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.Collections;
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
                property.setParentSchema(objectSchema);
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
        for (DictionarySchema dictionarySchema : schemas.getDictionaries()) {
            renameType(dictionarySchema);
        }
    }

    private void transformOperationGroups(List<OperationGroup> operationGroups, CodeModel codeModel) {
        List<Operation> pagingOperations = new ArrayList<>();
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

                if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null) {
                    pagingOperations.add(operation);
                }
            }
        }

        // paging
        for (Operation operation : pagingOperations) {
            if (ClientMethodMapper.nonNullNextLink(operation)) {
                addPagingNextOperation(codeModel, operation.getOperationGroup(), operation);
            }
        }
    }

    private void addPagingNextOperation(CodeModel codeModel, OperationGroup operationGroup, Operation operation) {
        String operationGroupName;
        String operationName;
        if (operation.getExtensions().getXmsPageable().getOperationName() != null) {
            String operationGroupAndName = operation.getExtensions().getXmsPageable().getOperationName();
            if (operationGroupAndName.contains("_")) {
                String[] parts = operationGroupAndName.split("_", 2);
                operationGroupName = CodeNamer.getMethodGroupName(parts[0]);
                operationName = CodeNamer.getMethodName(parts[1]);
            } else {
                operationGroupName = operationGroup.getLanguage().getJava().getName();
                operationName = CodeNamer.getMethodName(operationGroupAndName);
            }
        } else {
            operationGroupName = operationGroup.getLanguage().getJava().getName();
            operationName = operation.getLanguage().getJava().getName() + "Next";
        }
        if (!codeModel.getOperationGroups().stream()
                .anyMatch(og -> og.getLanguage().getJava().getName().equals(operationGroupName))) {
            OperationGroup newOg = new OperationGroup();
            newOg.setCodeModel(codeModel);
            newOg.set$key(operationGroupName);
            newOg.setOperations(new ArrayList<>());
            newOg.setExtensions(operationGroup.getExtensions());
            newOg.setLanguage(new Languages());
            newOg.getLanguage().setJava(new Language());
            newOg.getLanguage().getJava().setName(operationGroupName);
            newOg.getLanguage().getJava().setDescription(operationGroup.getLanguage().getJava().getDescription());
            newOg.setProtocol(operationGroup.getProtocol());

            codeModel.getOperationGroups().add(newOg);
            operationGroup = newOg;
        }

        if (!operationGroup.getOperations().stream()
                .anyMatch(o -> o.getLanguage().getJava().getName().equals(operationName))) {
            Operation nextOperation = new Operation();
            nextOperation.setOperationGroup(operationGroup);
            nextOperation.set$key(operationName);
            nextOperation.setLanguage(new Languages());
            nextOperation.getLanguage().setJava(new Language());
            nextOperation.getLanguage().getJava().setName(operationName);
            nextOperation.getLanguage().getJava().setDescription("Get the next page of items");
            nextOperation.setRequest(new Request());
            nextOperation.getRequest().setProtocol(new Protocols());
            nextOperation.getRequest().getProtocol().setHttp(new Protocol());
            nextOperation.getRequest().getProtocol().getHttp().setPath("{nextLink}");
            nextOperation.getRequest().getProtocol().getHttp().setUri(operation.getRequest().getProtocol().getHttp().getUri());
            nextOperation.getRequest().getProtocol().getHttp().setMethod("get");
            nextOperation.getRequest().setExtensions(operation.getRequest().getExtensions());
            nextOperation.getRequest().setLanguage(operation.getLanguage());
            Parameter nextLink = new Parameter();
            nextLink.setOperation(nextOperation);
            nextLink.setImplementation(Parameter.ImplementationLocation.METHOD);
            nextLink.set$key("nextLink");
            nextLink.setNullable(false);
            nextLink.setSummary("The URL to get the next list of items");
            nextLink.setSchema(new StringSchema());
            nextLink.setRequired(true);
            nextLink.setLanguage(new Languages());
            nextLink.getLanguage().setJava(new Language());
            nextLink.getLanguage().getJava().setName("nextLink");
            nextLink.getLanguage().getJava().setSerializedName("nextLink");
            nextLink.getLanguage().setDefault(nextLink.getLanguage().getJava());
            nextLink.setProtocol(new Protocols());
            nextLink.getProtocol().setHttp(new Protocol());
            nextLink.getProtocol().getHttp().setIn(RequestParameterLocation.Path);
            nextLink.setExtensions(new XmsExtensions());
            nextLink.getExtensions().setXmsSkipUrlEncoding(true);
            nextOperation.getRequest().setParameters(Collections.singletonList(nextLink));
            nextOperation.setApiVersions(operation.getApiVersions());
            nextOperation.setDeprecated(operation.getDeprecated());
            nextOperation.setDescription(operation.getDescription());
            nextOperation.setExceptions(operation.getExceptions());
            nextOperation.setExtensions(operation.getExtensions());
            nextOperation.setExternalDocs(operation.getExternalDocs());
            nextOperation.setProfile(operation.getProfile());
            nextOperation.setResponses(operation.getResponses());
            nextOperation.setSummary(operation.getSummary());
            nextOperation.setUid(operation.getUid());

            operation.getExtensions().getXmsPageable().setNextOperation(nextOperation);
            nextOperation.getExtensions().getXmsPageable().setNextOperation(nextOperation);
            operationGroup.getOperations().add(nextOperation);
        } else {
            Operation nextOperation = operationGroup.getOperations().stream()
                    .filter(o -> o.getLanguage().getJava().getName().equals(operationName))
                    .findFirst().get();
            operation.getExtensions().getXmsPageable().setNextOperation(nextOperation);
            nextOperation.getExtensions().getXmsPageable().setNextOperation(nextOperation);
        }
    }

    private void renameType(Metadata schema) {
        Language language = schema.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getTypeName(language.getName()));
        java.setSerializedName(language.getSerializedName());
        java.setDescription(language.getDescription());
        schema.getLanguage().setJava(java);
    }

    private void renameProperty(Property property) {
        Language language = property.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getPropertyName(language.getName()));
        java.setSerializedName(language.getSerializedName());
        java.setDescription(language.getDescription());
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
        java.setSerializedName(language.getSerializedName());
        java.setDescription(language.getDescription());
        schema.getLanguage().setJava(java);
    }

    private void renameMethodGroup(Metadata schema) {
        Language language = schema.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getMethodGroupName(language.getName()));
        java.setSerializedName(language.getSerializedName());
        java.setDescription(language.getDescription());
        schema.getLanguage().setJava(java);
    }

    private void renameMethod(Metadata schema) {
        Language language = schema.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getMethodName(language.getName()));
        java.setSerializedName(language.getSerializedName());
        java.setDescription(language.getDescription());
        schema.getLanguage().setJava(java);
    }
}
