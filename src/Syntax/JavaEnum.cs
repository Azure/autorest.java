using System;
using System.Collections.Generic;

namespace AutoRest.Java
{
    public class JavaEnum
    {
        private readonly JavaFileContents contents;
        private bool previouslyAddedValue;
        private bool addNewLine;

        public JavaEnum(JavaFileContents contents)
        {
            this.contents = contents;
        }

        private void AddExpectedNewLine()
        {
            if (addNewLine)
            {
                contents.Line();
                addNewLine = false;
            }
        }

        private void AddExpectedCommaAndNewLine()
        {
            if (previouslyAddedValue)
            {
                contents.Line(",");
                previouslyAddedValue = false;
            }

            AddExpectedNewLine();
        }

        private void AddExpectedSemicolonAndNewLine()
        {
            if (previouslyAddedValue)
            {
                contents.Line(";");
                previouslyAddedValue = false;
            }

            AddExpectedNewLine();
        }

        public void AddExpectedNewLineAfterLastValue()
        {
            if (previouslyAddedValue)
            {
                contents.Line();
                previouslyAddedValue = false;
                addNewLine = false;
            }
        }

        public void Value(string name, string value)
        {
            AddExpectedCommaAndNewLine();
            contents.JavadocComment($"Enum value {value}.");
            contents.Text($"{name}(\"{value}\")");
            previouslyAddedValue = true;
            addNewLine = true;
        }

        public void PrivateFinalMemberVariable(string variableType, string variableName)
        {
            AddExpectedSemicolonAndNewLine();
            contents.Line($"private final {variableType} {variableName};");
            addNewLine = true;
        }

        public void Constructor(string constructorSignature, Action<JavaBlock> constructor)
        {
            AddExpectedSemicolonAndNewLine();
            contents.Block($"{constructorSignature}", constructor);
            previouslyAddedValue = false;
            addNewLine = true;
        }

        public void Method(JavaVisibility visibility, IEnumerable<JavaModifier> modifiers, string methodSignature, Action<JavaBlock> method)
        {
            AddExpectedSemicolonAndNewLine();
            contents.Method(visibility, modifiers, methodSignature, method);
            previouslyAddedValue = false;
            addNewLine = true;
        }

        public void PublicMethod(string methodSignature, Action<JavaBlock> method)
        {
            Method(JavaVisibility.Public, null, methodSignature, method);
        }

        public void PublicStaticMethod(string methodSignature, Action<JavaBlock> method)
        {
            Method(JavaVisibility.Public, new[] { JavaModifier.Static }, methodSignature, method);
        }

        public void JavadocComment(string description)
        {
            AddExpectedSemicolonAndNewLine();
            contents.JavadocComment(description);
        }

        public void JavadocComment(Action<JavaJavadocComment> commentAction)
        {
            AddExpectedSemicolonAndNewLine();
            contents.JavadocComment(commentAction);
        }

        public void Annotation(params string[] annotations)
        {
            AddExpectedSemicolonAndNewLine();
            contents.Annotation(annotations);
        }
    }
}
