package com.azure.autorest.template;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodPageDetails;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.util.CodeNamer;

import java.util.stream.Collectors;

/**
 Writes a ClientMethod to a JavaType block.
*/
public class ClientMethodTemplate implements IJavaTemplate<ClientMethod, JavaType>
{
    private static ClientMethodTemplate _instance = new ClientMethodTemplate();
    public static ClientMethodTemplate getInstance()
    {
        return _instance;
    }

    private ClientMethodTemplate()
    {
    }

    public final void Write(ClientMethod clientMethod, JavaType typeBlock)
    {
        JavaSettings settings = JavaSettings.getInstance();

        ProxyMethod restAPIMethod = clientMethod.getProxyMethod();
        IType restAPIMethodReturnBodyClientType = restAPIMethod.getReturnType().getClientType();

        MethodPageDetails pageDetails = clientMethod.getMethodPageDetails();


        boolean isFluentDelete = settings.getIsFluent() && restAPIMethod.getName().equalsIgnoreCase("Delete") && clientMethod.getMethodRequiredParameters().size() == 2;

        switch (clientMethod.getType())
        {
            case PagingSync:
                typeBlock.JavadocComment(comment ->
                {
                        comment.Description(clientMethod.getDescription());
                        for (ClientMethodParameter parameter : clientMethod.getParameters())
                        {
                            comment.Param(parameter.getName(), parameter.getDescription());
                        }
                        if (clientMethod.getParametersDeclaration() != null && !clientMethod.getParametersDeclaration().isEmpty())
                        {
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                        }
                        if (restAPIMethod.getUnexpectedResponseExceptionType() != null)
                        {
                            comment.Throws(restAPIMethod.getUnexpectedResponseExceptionType().toString(), "thrown if the request is rejected by server");
                        }
                        comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                        comment.Return(clientMethod.getReturnValue().getDescription());
                });
                typeBlock.Annotation("ServiceMethod(returns = ReturnType.COLLECTION)");
                typeBlock.PublicMethod(clientMethod.getDeclaration(), function ->
                {
                    function.Line(String.format("%1$s response = %2$s(%3$s).block();", pageDetails.getPageType(), clientMethod.getPagingAsyncSinglePageMethodName(), clientMethod.getArgumentList()));
                    function.ReturnAnonymousClass(String.format("new %1$s(response)", clientMethod.getReturnValue().getType()), anonymousClass ->
                    {
                        anonymousClass.Annotation("Override");
                        anonymousClass.PublicMethod(String.format("%1$s nextPage(String %2$s)", pageDetails.getPageType(), pageDetails.getNextLinkParameterName()), subFunction ->
                        {
                            if (clientMethod.getMethodTransformationDetails() != null && !clientMethod.getMethodTransformationDetails().isEmpty() && pageDetails.getNextMethod().getMethodTransformationDetails() != null)
                            {
                                if (!pageDetails.getNextGroupParameterTypeName().equals(clientMethod.getGroupedParameterTypeName()) && (!clientMethod.getOnlyRequiredParameters() || clientMethod.getIsGroupedParameterRequired()))
                                {
                                    String nextGroupTypeCamelCaseName = CodeNamer.toCamelCase(pageDetails.getNextGroupParameterTypeName());
                                    String groupedTypeCamelCaseName = CodeNamer.toCamelCase(clientMethod.getGroupedParameterTypeName());

                                    String nextGroupTypeCodeName = CodeNamer.Instance.GetTypeName(pageDetails.getNextGroupParameterTypeName()) + (settings.getIsFluent() ? "Inner" : "");

                                    if (!clientMethod.getIsGroupedParameterRequired())
                                    {
                                        subFunction.Line(String.format("%1$s %2$s = null;", nextGroupTypeCodeName, nextGroupTypeCamelCaseName));
                                        subFunction.Line(String.format("if (%s != null) {{", groupedTypeCamelCaseName));
                                        subFunction.IncreaseIndent();
                                        subFunction.Line(String.format("%1$s = new %2$s();", nextGroupTypeCamelCaseName, nextGroupTypeCodeName));
                                    } else {
                                        subFunction.Line(String.format("%1$s %2$s = new %3$s();", nextGroupTypeCodeName, nextGroupTypeCamelCaseName, nextGroupTypeCodeName));
                                    }
                                    for (ClientMethodParameter outputParameter : pageDetails.getNextMethod().getMethodTransformationDetails().stream().map(MethodTransformationDetail::getOutParameter).collect(Collectors.toList()))
                                        {
                                        String outputParameterName;
                                        if (!outputParameter.getFromClient()) {
                                            outputParameterName = outputParameter.getName();
                                        } else {
                                            String caller = (outputParameter.Method != null && outputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                            String clientPropertyName = outputParameter.ClientProperty == null ? null : (outputParameter.ClientProperty.Name == null ? null : outputParameter.ClientProperty.Name.toString());
                                            if (clientPropertyName != null && !clientPropertyName.isEmpty()) {
                                                CodeNamer codeNamer = CodeNamer.Instance;
                                            }
                                            clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                        } outputParameterName = String.format("%1$s.%2$s()", caller, clientPropertyName);
                                        }
                                        subFunction.Line(String.format("%1$s.%2$s(%3$s.%4$s());", nextGroupTypeCamelCaseName, outputParameterName.ToCamelCase(), groupedTypeCamelCaseName, outputParameterName.ToCamelCase()));
                                        }
                                        if (!clientMethod.getGroupedParameter().IsRequired) {subFunction.DecreaseIndent(); subFunction.Line("}");}
                                        }
                                        }
                                        subFunction.Return(String.format("%1$s(%2$s).block()", pageDetails.getNextMethodInvocation() + "SinglePageAsync", pageDetails.getNextMethodParameterInvocation()));
                                        });
                                    }
                   );
                                }
               );
                break;

            case ClientMethodType.PagingAsync:
                typeBlock.JavadocComment(comment ->
                typeBlock.Annotation(String.format("ServiceMethod(returns = ReturnType.COLLECTION)"));
                typeBlock.PublicMethod(clientMethod.getDeclaration(), function ->
                {
                    function.Line(String.format("return %1$s(%2$s)", clientMethod.getPagingAsyncSinglePageMethodName(), clientMethod.getArgumentList()));
                    function.Indent(() ->
                    {
                        function.Line(".repeat(1)");
                        function.Text(String.format(".concatMap("));
                        function.Lambda(pageDetails.getPageType().toString(), "page", lambda ->
            if (conditionalAssignment)
            {
                function.Line("{0} {1} = null;", transformation.OutParameterType.ClientType, transformation.OutParameterName);
//C# TO JAVA CONVERTER TODO TASK: The following line uses invalid syntax:
//                    function.Line(string.Format(")if ({nullCheck}) {{"); function.IncreaseIndent(); } ClassType transformationOutputParameterModelClassType = transformation.OutParameterType as ClassType; bool generatedCompositeType = false; if(transformationOutputParameterModelClassType != null) { generatedCompositeType = transformationOutputParameterModelClassType.Package.StartsWith(settings.Package); } if(generatedCompositeType && transformation.ParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty))) { string transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelClassType.ToString(); if(settings.IsFluent && !string.IsNullOrEmpty(transformationOutputParameterModelCompositeTypeName) && transformationOutputParameterModelClassType.IsInnerModelType) { transformationOutputParameterModelCompositeTypeName += "Inner"; } function.Line("{0}{1} = new {2}();", !conditionalAssignment ? transformation.OutParameterType.ClientType + " " : "", transformation.OutParameterName, transformationOutputParameterModelCompositeTypeName); } foreach(ParameterMapping mapping in transformation.ParameterMappings) { string inputPath; if(!mapping.InputParameter.IsClientProperty) { inputPath = mapping.InputParameter.Name; } else { string caller = (mapping.InputParameter.Method != null && mapping.InputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client"); string clientPropertyName = mapping.InputParameter.ClientProperty == null ? null : (mapping.InputParameter.ClientProperty.Name == null ? null : mapping.InputParameter.ClientProperty.Name.ToString()); if(!string.IsNullOrEmpty(clientPropertyName)) { CodeNamer codeNamer = CodeNamer.Instance; clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName)); } inputPath = string.Format("{0}.{1}()", caller, clientPropertyName); } if(mapping.InputParameterProperty != null) { inputPath += "." + CodeNamer.Instance.CamelCase(mapping.InputParameterProperty) + "()"; } if(clientMethod.OnlyRequiredParameters && !mapping.InputParameter.IsRequired) { inputPath = "null"; } string getMapping; if(mapping.OutputParameterProperty != null) { getMapping = string.Format(".{0}({1})", CodeNamer.Instance.CamelCase(mapping.OutputParameterProperty), inputPath); } else { getMapping = string.Format(" = {0}", inputPath); } function.Line("{0}{1}{2};", !conditionalAssignment && !generatedCompositeType ? transformation.OutParameterType.ClientType + " " : "", transformation.OutParameterName, getMapping); } if(conditionalAssignment) { function.DecreaseIndent(); function.Line("}"); } } } private static void ConvertClientTypesToWireTypes(JavaBlock function, ClientMethod clientMethod, IEnumerable<ProxyMethodParameter> autoRestMethodRetrofitParameters, string methodClientReference, JavaSettings settings) { foreach(ProxyMethodParameter parameter in autoRestMethodRetrofitParameters) { IType parameterWireType = parameter.WireType; if(parameter.IsNullable) { parameterWireType = parameterWireType.AsNullable(); } IType parameterClientType = parameter.ClientType; if(parameterWireType != ClassType.Base64Url && parameter.RequestParameterLocation != RequestParameterLocation.Body && parameter.RequestParameterLocation != RequestParameterLocation.FormData && (parameterClientType is ArrayType || parameterClientType is ListType)) { parameterWireType = ClassType.String; } if(parameterWireType != parameterClientType) { string parameterName = parameter.ParameterReference; string parameterWireName = parameter.ParameterReferenceConverted; bool addedConversion = false; bool alwaysNull = clientMethod.OnlyRequiredParameters && !parameter.IsRequired; RequestParameterLocation parameterLocation = parameter.RequestParameterLocation; if(parameterLocation != RequestParameterLocation.Body && parameterLocation != RequestParameterLocation.FormData && (parameterClientType is ArrayType || parameterClientType is ListType)) { string parameterWireTypeName = parameterWireType.ToString(); if(parameterClientType == ArrayType.ByteArray) { if(parameterWireType == ClassType.String) { string expression; if(alwaysNull) { expression = "null"; } else { expression = string.Format("Base64Util.encodeToString({0})", parameterName); } function.Line(string.Format("{0} {1} = {2};", parameterWireTypeName, parameterWireName, expression)); } else { string expression; if(alwaysNull) { expression = "null"; } else { expression = string.Format("Base64Url.encode({0})", parameterName); } function.Line(string.Format("{0} {1} = {2};", parameterWireTypeName, parameterWireName, expression)); } addedConversion = true; } else if(parameterClientType is ListType) { string expression; if(alwaysNull) { expression = "null"; } else { expression = string.Format("JacksonAdapter.createDefaultSerializerAdapter().serializeList({0}, CollectionFormat.{1})", parameterName, parameter.CollectionFormat.ToString().ToUpperInvariant()); } function.Line(string.Format("{0} {1} = {2};", parameterWireTypeName, parameterWireName, expression)); addedConversion = true; } } if(settings.ShouldGenerateXmlSerialization && parameterClientType is ListType && (parameterLocation == RequestParameterLocation.Body || parameterLocation == RequestParameterLocation.FormData)) { function.Line("{0} {1} = new {0}({2});", parameter.WireType, parameterWireName, alwaysNull? "null": parameterName); addedConversion = true; } if(!addedConversion) { function.Line(parameter.ConvertFromClientType(parameterName, parameterWireName, clientMethod.OnlyRequiredParameters && !parameter.IsRequired, parameter.IsConstant || alwaysNull)); } } } } } }