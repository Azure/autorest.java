using System;
using System.Collections.Generic;

namespace AutoRest.Java
{
    public class JavaClass : JavaType
    {
        private readonly JavaFileContents contents;
        private bool addNewLine;

        public JavaClass(JavaFileContents contents)
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

        public void PrivateMemberVariable(string variableType, string variableName)
        {
            PrivateMemberVariable($"{variableType} {variableName}");
        }

        public void PrivateMemberVariable(string variableDeclaration)
        {
            AddExpectedNewLine();
            contents.Line($"private {variableDeclaration};");
            addNewLine = true;
        }

        public void PrivateFinalMemberVariable(string variableType, string variableName)
        {
            AddExpectedNewLine();
            contents.Line($"private final {variableType} {variableName};");
            addNewLine = true;
        }

        public void PublicStaticFinalVariable(string variableDeclaration)
        {
            AddExpectedNewLine();
            contents.Line($"public static final {variableDeclaration};");
            addNewLine = true;
        }

        public void PrivateConstructor(string constructorSignature, Action<JavaBlock> constructor)
        {
            AddExpectedNewLine();
            contents.Block($"private {constructorSignature}", constructor);
            addNewLine = true;
        }

        public void PublicConstructor(string constructorSignature, Action<JavaBlock> constructor)
        {
            AddExpectedNewLine();
            contents.Block($"public {constructorSignature}", constructor);
            addNewLine = true;
        }

        public void PackagePrivateConstructor(string constructorSignature, Action<JavaBlock> constructor)
        {
            AddExpectedNewLine();
            contents.Block($"{constructorSignature}", constructor);
            addNewLine = true;
        }

        public void Method(JavaVisibility visibility, IEnumerable<JavaModifier> modifiers, string methodSignature, Action<JavaBlock> method)
        {
            AddExpectedNewLine();
            contents.Method(visibility, modifiers, methodSignature, method);
            addNewLine = true;
        }

        public void PublicMethod(string methodSignature, Action<JavaBlock> method)
        {
            Method(JavaVisibility.Public, null, methodSignature, method);
        }
        
        public void PackagePrivateMethod(string methodSignature, Action<JavaBlock> method)
        {
            Method(JavaVisibility.PackagePrivate, null, methodSignature, method);
        }

        public void PrivateMethod(string methodSignature, Action<JavaBlock> method)
        {
            Method(JavaVisibility.Private, null, methodSignature, method);
        }

        public void PublicStaticMethod(string methodSignature, Action<JavaBlock> method)
        {
            Method(JavaVisibility.Public, new[] { JavaModifier.Static }, methodSignature, method);
        }

        public void Interface(JavaVisibility visibility, string interfaceSignature, Action<JavaInterface> interfaceBlock)
        {
            AddExpectedNewLine();
            contents.Interface(visibility, interfaceSignature, interfaceBlock);
            addNewLine = true;
        }

        public void PublicInterface(string interfaceSignature, Action<JavaInterface> interfaceBlock)
        {
            Interface(JavaVisibility.Public, interfaceSignature, interfaceBlock);
        }

        public void PrivateStaticFinalClass(string classSignature, Action<JavaClass> classBlock)
        {
            AddExpectedNewLine();
            contents.Class(JavaVisibility.Private, new[] { JavaModifier.Static, JavaModifier.Final }, classSignature, classBlock);
            addNewLine = true;
        }

        public void BlockComment(string description)
        {
            AddExpectedNewLine();
            contents.BlockComment(description);
        }

        public void BlockComment(Action<JavaLineComment> commentAction)
        {
            AddExpectedNewLine();
            contents.BlockComment(commentAction);
        }

        public void BlockComment(int wordWrapWidth, Action<JavaLineComment> commentAction)
        {
            AddExpectedNewLine();
            contents.BlockComment(wordWrapWidth, commentAction);
        }

        public void JavadocComment(string description)
        {
            AddExpectedNewLine();
            contents.JavadocComment(description);
        }

        public void JavadocComment(Action<JavaJavadocComment> commentAction)
        {
            AddExpectedNewLine();
            contents.JavadocComment(commentAction);
        }

        public void JavadocComment(int wordWrapWidth, Action<JavaJavadocComment> commentAction)
        {
            AddExpectedNewLine();
            contents.JavadocComment(wordWrapWidth, commentAction);
        }

        public void Annotation(params string[] annotations)
        {
            AddExpectedNewLine();
            contents.Annotation(annotations);
        }
    }
}
