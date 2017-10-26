using System;

namespace AutoRest.Java.DanModel
{
    public class JavaBlock
    {
        private readonly JavaFileContents contents;

        public JavaBlock(JavaFileContents file)
        {
            this.contents = file;
        }

        private JavaBlock Indent(Action action)
        {
            contents.Indent(action);
            return this;
        }

        public JavaBlock Line(string text)
        {
            contents.Line(text);
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

        public JavaBlock MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            contents.MultipleLineComment(commentAction);
            return this;
        }

        public JavaBlock Return(string text)
        {
            contents.Return(text);
            return this;
        }
    }
}
