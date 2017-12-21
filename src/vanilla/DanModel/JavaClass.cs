using System;

namespace AutoRest.Java.DanModel
{
    public class JavaClass : JavaAbstractType
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

        public void PublicConstructor(string constructorSignature, Action<JavaBlock> constructor)
        {
            AddExpectedNewLine();
            contents.Block($"public {constructorSignature}", constructor);
            addNewLine = true;
        }

        public void PublicMethod(string methodSignature, Action<JavaBlock> method)
        {
            AddExpectedNewLine();
            contents.Block($"public {methodSignature}", method);
            addNewLine = true;
        }

        public void ProtectedMethod(string methodSignature, Action<JavaBlock> method)
        {
            AddExpectedNewLine();
            contents.Block($"protected {methodSignature}", method);
            addNewLine = true;
        }

        public void PrivateMethod(string methodSignature, Action<JavaBlock> method)
        {
            AddExpectedNewLine();
            contents.Block($"private {methodSignature}", method);
            addNewLine = true;
        }

        public void PublicStaticMethod(string methodSignature, Action<JavaBlock> method)
        {
            AddExpectedNewLine();
            contents.Block($"public static {methodSignature}", method);
            addNewLine = true;
        }

        public void PublicInterface(string interfaceSignature, Action<JavaInterface> interfaceBlock)
        {
            AddExpectedNewLine();
            contents.PublicInterface(interfaceSignature, interfaceBlock);
            addNewLine = true;
        }

        public void Interface(string interfaceSignature, Action<JavaInterface> interfaceBlock)
        {
            AddExpectedNewLine();
            contents.Interface(interfaceSignature, interfaceBlock);
            addNewLine = true;
        }

        public void PrivateStaticFinalClass(string classSignature, Action<JavaClass> classBlock)
        {
            AddExpectedNewLine();
            contents.PrivateStaticFinalClass(classSignature, classBlock);
            addNewLine = true;
        }

        public void MultipleLineComment(string description)
        {
            MultipleLineComment(comment =>
            {
                comment.Description(description);
            });
        }

        public void MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            AddExpectedNewLine();
            contents.MultipleLineComment(commentAction);
        }

        public void WordWrappedMultipleLineComment(int wordWrapWidth, Action<JavaMultipleLineComment> commentAction)
        {
            AddExpectedNewLine();
            contents.WordWrappedMultipleLineComment(wordWrapWidth, commentAction);
        }

        public void Annotation(params string[] annotations)
        {
            AddExpectedNewLine();
            contents.Annotation(annotations);
        }
    }
}
