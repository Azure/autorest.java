package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.CodeNamer;

/**
 Writes an XmlSequenceWrapper to a JavaFile.
*/
public class XmlSequenceWrapperTemplate implements IJavaTemplate<XmlSequenceWrapper, JavaFile>
{
    private static XmlSequenceWrapperTemplate _instance = new XmlSequenceWrapperTemplate();
    public static XmlSequenceWrapperTemplate getInstance()
    {
        return _instance;
    }

    private XmlSequenceWrapperTemplate()
    {
    }

    public final void Write(XmlSequenceWrapper xmlSequenceWrapper, JavaFile javaFile)
    {
        String xmlRootElementName = xmlSequenceWrapper.getXmlRootElementName();
        String xmlListElementName = xmlSequenceWrapper.getXmlListElementName();

        String xmlElementNameCamelCase = CodeNamer.toCamelCase(xmlRootElementName);

        ListType sequenceType = xmlSequenceWrapper.getSequenceType();

        javaFile.Import(xmlSequenceWrapper.getImports());

        javaFile.JavadocComment(comment ->
        {
                comment.Description(String.format("A wrapper around %1$s which provides top-level metadata for serialization.", sequenceType));
        });
        javaFile.Annotation(String.format("JacksonXmlRootElement(localName = \"%1$s\")", xmlRootElementName));
        javaFile.PublicFinalClass(xmlSequenceWrapper.getWrapperClassName(), classBlock ->
        {
                classBlock.Annotation(String.format("JacksonXmlProperty(localName = \"%1$s\")", xmlListElementName));
                classBlock.PrivateFinalMemberVariable(sequenceType.toString(), xmlElementNameCamelCase);

                classBlock.JavadocComment(comment ->
                {
                    comment.Description(String.format("Creates an instance of %1$s.", xmlSequenceWrapper.getWrapperClassName()));
                    comment.Param(xmlElementNameCamelCase, "the list");
                });
                classBlock.Annotation("JsonCreator");
                classBlock.PublicConstructor(String.format("%1$s(@JsonProperty(\"%2$s\") %3$s %4$s)", xmlSequenceWrapper.getWrapperClassName(), xmlListElementName, sequenceType, xmlElementNameCamelCase), constructor ->
                {
                    constructor.Line(String.format("this.%1$s = %2$s;", xmlElementNameCamelCase, xmlElementNameCamelCase));
                });

                classBlock.JavadocComment(comment ->
                {
                    comment.Description(String.format("Get the %1$s contained in this wrapper.", sequenceType));
                    comment.Return(String.format("the %1$s", sequenceType));
                });
                classBlock.PublicMethod(String.format("%1$s items()", sequenceType), function ->
                {
                    function.Return(xmlElementNameCamelCase);
                });
        });
    }
}