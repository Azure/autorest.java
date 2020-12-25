package com.azure.autorest.preprocessor.tranformer;

import com.azure.autorest.extension.base.model.codemodel.AndSchema;
import com.azure.autorest.extension.base.model.codemodel.BinarySchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.NumberSchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Protocol;
import com.azure.autorest.extension.base.model.codemodel.Protocols;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.Schemas;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.StringSchema;
import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.preprocessor.namer.CodeNamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Transformer {

  public CodeModel transform(CodeModel codeModel) {
    renameCodeModel(codeModel);
    transformSchemas(codeModel.getSchemas());
    transformOperationGroups(codeModel.getOperationGroups(), codeModel);
    return codeModel;
  }

  private void transformSchemas(Schemas schemas) {
    schemas.getObjects().addAll(schemas.getGroups());
    schemas.setGroups(new ArrayList<>());
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
        for (Request request : operation.getRequests()) {
          Stream<Parameter> newParameters = Stream.concat(operation.getParameters().stream(), request.getParameters().stream());
          request.setParameters(newParameters.collect(Collectors.toList()));
          Stream<Parameter> newSignatureParameters = Stream.concat(operation.getSignatureParameters().stream(), request.getSignatureParameters().stream());
          request.setSignatureParameters(newSignatureParameters.collect(Collectors.toList()));
          for (int i = 0; i < request.getParameters().size(); i++) {
            Parameter parameter = request.getParameters().get(i);
            parameter.setOperation(operation);
            renameVariable(parameter);
            // add Content-Length for Flux<ByteBuffer> if not already present
            if (parameter.getSchema() instanceof BinarySchema) {
              if (request.getParameters().stream().noneMatch(p -> p.getProtocol() != null
                      && p.getProtocol().getHttp() != null
                      && p.getProtocol().getHttp().getIn() == RequestParameterLocation.Header
                      && "content-length".equalsIgnoreCase(p.getLanguage().getDefault().getSerializedName()))) {
                Parameter contentLength = createContentLengthParameter(operation, parameter);
                request.getParameters().add(++i, contentLength);
                request.getSignatureParameters().add(request.getSignatureParameters().indexOf(parameter) + 1, contentLength);
              }
            }
            // convert contentType to header param
            Optional<Parameter> contentType = request.getParameters().stream()
                    .filter(p -> (p.getProtocol() == null || p.getProtocol().getHttp() == null) && "contentType".equals(p.getLanguage().getDefault().getName()))
                    .findFirst();
            if (contentType.isPresent()) {
              Protocols protocols = new Protocols();
              protocols.setHttp(new Protocol());
              protocols.getHttp().setIn(RequestParameterLocation.Header);
              contentType.get().setProtocol(protocols);
              contentType.get().getLanguage().getDefault().setSerializedName("Content-Type");
            }
          }
        }

        if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null) {
          pagingOperations.add(operation);
        }
      }
    }

    // paging
    for (Operation operation : pagingOperations) {
      if (nonNullNextLink(operation)) {
        addPagingNextOperation(codeModel, operation.getOperationGroup(), operation);
      }
    }
  }

  public static boolean nonNullNextLink(Operation operation) {
    return operation.getExtensions().getXmsPageable().getNextLinkName() != null && !operation.getExtensions().getXmsPageable().getNextLinkName().isEmpty();
  }

  private void addPagingNextOperation(CodeModel codeModel, OperationGroup operationGroup, Operation operation) {
    String operationGroupName;
    String operationName;
    if (operation.getExtensions().getXmsPageable().getOperationName() != null && !JavaSettings.getInstance().isFluent()) {
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
      nextOperation.setRequests(new ArrayList<>());
      Request request = new Request();
      nextOperation.getRequests().add(request);
      nextOperation.getRequests().get(0).setProtocol(new Protocols());
      nextOperation.getRequests().get(0).getProtocol().setHttp(new Protocol());
      nextOperation.getRequests().get(0).getProtocol().getHttp().setPath("{nextLink}");
      nextOperation.getRequests().get(0).getProtocol().getHttp()
          .setUri(operation.getRequests().get(0).getProtocol().getHttp().getUri());
      nextOperation.getRequests().get(0).getProtocol().getHttp().setMethod("get");
      nextOperation.getRequests().get(0).setExtensions(operation.getRequests().get(0).getExtensions());
      nextOperation.getRequests().get(0).setLanguage(operation.getLanguage());
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
      List<Parameter> requestParams = new ArrayList<>();
      requestParams.add(nextLink);
      nextOperation.getRequests().get(0).setParameters(requestParams);
      List<Parameter> signatureParams = new ArrayList<>();
      signatureParams.add(nextLink);
      nextOperation.getRequests().get(0).setSignatureParameters(signatureParams);
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

      if (operation.getExtensions().getXmsPageable().getOperationName() == null) {
        operation.getRequests().stream().flatMap(r -> r.getParameters().stream())
                .filter(parameter -> {
                  return parameter.getProtocol() == null || parameter.getProtocol().getHttp() == null
                          || (parameter.getProtocol().getHttp().getIn() != null
                          && (parameter.getProtocol().getHttp().getIn().equals(RequestParameterLocation.Header)
                          || parameter.getProtocol().getHttp().getIn().equals(RequestParameterLocation.Uri)));
                })
                .forEach(param -> {
                  nextOperation.getRequests().get(0).getParameters().add(param);
                });

        operation.getRequests().stream().flatMap(r -> r.getSignatureParameters().stream())
                .filter(parameter -> {
                  return parameter.getProtocol() == null || parameter.getProtocol().getHttp() == null
                          || (parameter.getProtocol().getHttp().getIn() != null
                          && (parameter.getProtocol().getHttp().getIn().equals(RequestParameterLocation.Header)
                          || parameter.getProtocol().getHttp().getIn().equals(RequestParameterLocation.Uri)));
                })
                .forEach(param -> {
                  nextOperation.getRequests().get(0).getSignatureParameters().add(param);
                });
      }
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

  private Parameter createContentLengthParameter(Operation operation, Parameter bodyParam) {
    Parameter contentType = new Parameter();
    contentType.setOperation(operation);
    contentType.setDescription("The Content-Length header for the request");
    contentType.setRequired(bodyParam.isRequired());
    NumberSchema longSchema = new NumberSchema();
    longSchema.setPrecision(64);
    longSchema.setType(Schema.AllSchemaTypes.INTEGER);
    contentType.setSchema(longSchema);
    contentType.setImplementation(Parameter.ImplementationLocation.METHOD);
    contentType.setProtocol(new Protocols());
    contentType.getProtocol().setHttp(new Protocol());
    contentType.getProtocol().getHttp().setIn(RequestParameterLocation.Header);
    Language language = new Language();
    language.setName("contentLength");
    language.setSerializedName("Content-Length");
    contentType.setLanguage(new Languages());
    contentType.getLanguage().setDefault(language);
    contentType.getLanguage().setJava(language);
    return contentType;
  }
}
