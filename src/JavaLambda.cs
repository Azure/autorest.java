using System;

namespace AutoRest.Java
{
    public class JavaLambda : IDisposable
    {
        private readonly JavaFileContents contents;
        private bool isFirstStatement;
        private bool needsClosingCurlyBracket;

        public JavaLambda(JavaFileContents contents)
        {
            this.contents = contents;
            isFirstStatement = true;
            needsClosingCurlyBracket = false;
        }

        private void NonReturnStatement()
        {
            if (isFirstStatement)
            {
                isFirstStatement = false;

                contents.Line("{");
                contents.IncreaseIndent();
                needsClosingCurlyBracket = true;
            }
        }

        public void Dispose()
        {
            if (needsClosingCurlyBracket)
            {
                contents.DecreaseIndent();
                contents.Text("}");
            }
        }

        public void Line(string text)
        {
            NonReturnStatement();
            contents.Line(text);
        }

        public void IncreaseIndent()
        {
            contents.IncreaseIndent();
        }

        public void DecreaseIndent()
        {
            contents.DecreaseIndent();
        }

        public JavaIfBlock If(string condition, Action<JavaBlock> ifAction)
        {
            NonReturnStatement();
            contents.If(condition, ifAction);
            return new JavaIfBlock(contents);
        }

        public void Return(string text)
        {
            if (isFirstStatement)
            {
                contents.Text(text);
            }
            else
            {
                contents.Return(text);
            }
        }
    }
}
