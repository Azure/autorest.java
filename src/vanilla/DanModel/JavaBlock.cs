using System;

namespace AutoRest.Java.DanModel
{
    public class JavaBlock
    {
        private readonly JavaFileContents contents;

        public JavaBlock(JavaFileContents contents)
        {
            this.contents = contents;
        }

        public JavaBlock Indent(Action indentAction)
        {
            contents.Indent(indentAction);
            return this;
        }

        public JavaBlock IncreaseIndent()
        {
            contents.IncreaseIndent();
            return this;
        }

        public JavaBlock DecreaseIndent()
        {
            contents.DecreaseIndent();
            return this;
        }

        public JavaBlock Line(string text, params object[] formattedArguments)
        {
            contents.Line(text, formattedArguments);
            return this;
        }

        public JavaBlock Line()
        {
            contents.Line();
            return this;
        }

        public JavaBlock Block(string text, Action<JavaBlock> bodyAction)
        {
            contents.Block(text, bodyAction);
            return this;
        }

        public JavaBlock SingleLineComment(string text)
        {
            contents.SingleLineComment(text);
            return this;
        }

        public void MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            contents.MultipleLineComment(commentAction);
        }

        public JavaBlock Return(string text)
        {
            contents.Return(text);
            return this;
        }

        public void Annotation(params string[] annotations)
        {
            contents.Annotation(annotations);
        }

        public void ReturnAnonymousClass(string anonymousClassDeclaration, Action<JavaClass> anonymousClassBlock)
        {
            contents.ReturnAnonymousClass(anonymousClassDeclaration, anonymousClassBlock);
        }

        public JavaIfBlock<JavaBlock> If(string condition, Action<JavaBlock> ifAction)
        {
            contents.If(condition, ifAction);
            return new JavaIfBlock<JavaBlock>(this, contents);
        }
    }
}
