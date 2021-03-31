package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.CodeNamer;

/**
 * Writes an XmlSequenceWrapper to a JavaFile.
 */
public class XmlSequenceWrapperTemplate implements IJavaTemplate<XmlSequenceWrapper, JavaFile> {
    private static XmlSequenceWrapperTemplate _instance = new XmlSequenceWrapperTemplate();

    private XmlSequenceWrapperTemplate() {
    }

    public static XmlSequenceWrapperTemplate getInstance() {
        return _instance;
    }

    public final void write(XmlSequenceWrapper xmlSequenceWrapper, JavaFile javaFile) {
        String xmlRootElementName = xmlSequenceWrapper.getXmlRootElementName();
        String xmlListElementName = xmlSequenceWrapper.getXmlListElementName();

        String xmlElementNameCamelCase = CodeNamer.toCamelCase(xmlRootElementName);

        IType sequenceType = xmlSequenceWrapper.getSequenceType();

        javaFile.declareImport(xmlSequenceWrapper.getImports());

        javaFile.javadocComment(comment ->
        {
            comment.description(String.format("A wrapper around %1$s which provides top-level metadata for serialization.", sequenceType));
        });
        javaFile.annotation(String.format("JacksonXmlRootElement(localName = \"%1$s\")", xmlRootElementName));
        javaFile.publicFinalClass(xmlSequenceWrapper.getWrapperClassName(), classBlock ->
        {
            classBlock.annotation(String.format("JacksonXmlProperty(localName = \"%1$s\")", xmlListElementName));
            classBlock.privateFinalMemberVariable(sequenceType.toString(), xmlElementNameCamelCase);

            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Creates an instance of %1$s.", xmlSequenceWrapper.getWrapperClassName()));
                comment.param(xmlElementNameCamelCase, "the list");
            });
            classBlock.annotation("JsonCreator");
            classBlock.publicConstructor(String.format("%1$s(@JsonProperty(\"%2$s\") %3$s %4$s)", xmlSequenceWrapper.getWrapperClassName(), xmlListElementName, sequenceType, xmlElementNameCamelCase), constructor ->
            {
                constructor.line(String.format("this.%1$s = %2$s;", xmlElementNameCamelCase, xmlElementNameCamelCase));
            });

            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Get the %1$s contained in this wrapper.", sequenceType));
                comment.methodReturns(String.format("the %1$s", sequenceType));
            });
            classBlock.publicMethod(String.format("%1$s items()", sequenceType), function ->
            {
                function.methodReturn(xmlElementNameCamelCase);
            });
        });
    }
}
