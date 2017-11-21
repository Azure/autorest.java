using System;

namespace AutoRest.Java.DanModel
{
    public class JavaClass : JavaBlock
    {
        public JavaClass(JavaFileContents contents)
            : base(contents)
        {
        }

        public JavaClass PrivateMemberVariable(string description, string variableType, string variableName)
        {
            MultipleLineComment(comment =>
            {
                comment.Line("The HTTP pipeline to send requests through.");
            });
            Line($"private {variableType} {variableName};");
            return this;
        }

        public JavaClass PublicGetter(string variableType, string variableName)
        {
            MultipleLineComment(comment =>
            {
                comment.Line($"Gets the {variableType} object to access its operations.");
                comment.Line();
                comment.Return($"the {variableType} object.");
            });
            Block($"public {variableType} {variableName}()", function =>
            {
                function.Return($"this.{variableName}");
            });
            return this;
        }

        public JavaClass PublicConstructor(string description, string className, Action<JavaBlock> constructor)
        {
            MultipleLineComment(comment =>
            {
                comment.Line(description);
            });
            Block($"public {className}()", constructor);
            return this;
        }
    }
}
