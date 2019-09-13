package com.azure.autorest.model.javamodel;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


public class JavaJavadocComment
{
    private JavaFileContents contents;
    private boolean expectsLineSeparator;

    public JavaJavadocComment(JavaFileContents contents)
    {
        this.contents = contents;
    }

    private void AddExpectedLineSeparator()
    {
        if (expectsLineSeparator)
        {
            expectsLineSeparator = false;
            contents.Line();
        }
    }

    public final void Description(String description)
    {
        String processedText = ProcessText(description);
        if (processedText != null && !processedText.isEmpty())
        {
            contents.Line(processedText);
            expectsLineSeparator = true;
        }
    }

    public final void Param(String parameterName, String parameterDescription)
    {
        AddExpectedLineSeparator();
        contents.Line(String.format("@param %1$s %2$s", parameterName, ProcessText(parameterDescription)));
    }

    public final void Return(String returnValueDescription)
    {
        if (returnValueDescription != null && !returnValueDescription.isEmpty())
        {
            AddExpectedLineSeparator();
            contents.Line(String.format("@return %1$s", ProcessText(returnValueDescription)));
        }
    }

    public final void Throws(String exceptionTypeName, String description)
    {
        AddExpectedLineSeparator();
        contents.Line(String.format("@throws %1$s %2$s", exceptionTypeName, ProcessText(description)));
    }

    private static String Trim(String value)
    {
        return value == null || value.isEmpty() ? value : value.trim();
    }

    private static String EnsurePeriod(String value)
    {
        return value == null || value.isEmpty() || value.endsWith(".") ? value : value + '.';
    }

    private static String ProcessText(String value)
    {
        return EnsurePeriod(Trim(value)).EscapeXmlComment();
    }
}