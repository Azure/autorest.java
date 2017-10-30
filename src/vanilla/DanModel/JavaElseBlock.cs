using System;
using System.Collections.Generic;

namespace AutoRest.Java.DanModel
{
    public class JavaElseBlock
    {
        private readonly JavaFileContents contents;

        public JavaElseBlock(JavaFileContents file)
        {
            this.contents = file;
        }

        public JavaElseBlock Line(string text)
        {
            contents.Line(text);
            return this;
        }

        public JavaElseBlock Line()
        {
            contents.Line();
            return this;
        }

        public JavaElseBlock Block(string text, Action<JavaBlock> bodyAction)
        {
            contents.Block(text, bodyAction);
            return this;
        }

        public JavaElseBlock SingleLineComment(string text)
        {
            contents.SingleLineComment(text);
            return this;
        }

        public JavaElseBlock MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            contents.MultipleLineComment(commentAction);
            return this;
        }

        public JavaElseBlock WordWrappedMultipleLineComment(int wordWrapWidth, Action<JavaWordWrappedMultipleLineComment> commentAction)
        {
            contents.WordWrappedMultipleLineComment(wordWrapWidth, commentAction);
            return this;
        }

        public JavaElseBlock Return(string text)
        {
            contents.Return(text);
            return this;
        }

        public JavaElseBlock Annotation(params string[] annotations)
        {
            contents.Annotation(annotations);
            return this;
        }

        public JavaElseBlock Annotation(IEnumerable<string> annotations)
        {
            contents.Annotation(annotations);
            return this;
        }

        public JavaElseBlock If(string condition, Action<JavaIfBlock> ifAction)
        {
            contents.If(condition, ifAction);
            return this;
        }
    }
}
