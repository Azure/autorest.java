using System;

namespace AutoRest.Java
{
    public class JavaBlock : JavaContext
    {
        private readonly JavaFileContents contents;

        public JavaBlock(JavaFileContents contents)
        {
            this.contents = contents;
        }

        public void Indent(Action indentAction)
        {
            contents.Indent(indentAction);
        }

        public void IncreaseIndent()
        {
            contents.IncreaseIndent();
        }

        public void DecreaseIndent()
        {
            contents.DecreaseIndent();
        }

        public void Text(string text)
        {
            contents.Text(text);
        }

        public void Line(string text, params object[] formattedArguments)
        {
            contents.Line(text, formattedArguments);
        }

        public void Line()
        {
            contents.Line();
        }

        public void Block(string text, Action<JavaBlock> bodyAction)
        {
            contents.Block(text, bodyAction);
        }

        public void JavadocComment(string text)
        {
            contents.JavadocComment(text);
        }

        public void JavadocComment(Action<JavaJavadocComment> commentAction)
        {
            contents.JavadocComment(commentAction);
        }

        public void Return(string text)
        {
            contents.Return(text);
        }

        public void Annotation(params string[] annotations)
        {
            contents.Annotation(annotations);
        }

        public void ReturnAnonymousClass(string anonymousClassDeclaration, Action<JavaClass> anonymousClassBlock)
        {
            contents.ReturnAnonymousClass(anonymousClassDeclaration, anonymousClassBlock);
        }

        public JavaIfBlock If(string condition, Action<JavaBlock> ifAction)
        {
            contents.If(condition, ifAction);
            return new JavaIfBlock(contents);
        }

        public void Lambda(string parameterType, string parameterName, Action<JavaLambda> body)
        {
            contents.Lambda(parameterType, parameterName, body);
        }

        public void Lambda(string parameterType, string parameterName, string returnExpression)
        {
            contents.Lambda(parameterType, parameterName, returnExpression);
        }
    }
}
