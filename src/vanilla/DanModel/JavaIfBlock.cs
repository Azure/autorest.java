using System;
using System.Collections.Generic;

namespace AutoRest.Java.DanModel
{
    public class JavaIfBlock
    {
        private readonly JavaFileContents contents;

        public JavaIfBlock(JavaFileContents file)
        {
            this.contents = file;
        }

        public JavaIfBlock Line(string text)
        {
            contents.Line(text);
            return this;
        }

        public JavaIfBlock Line()
        {
            contents.Line();
            return this;
        }

        public JavaIfBlock Block(string text, Action<JavaBlock> bodyAction)
        {
            contents.Block(text, bodyAction);
            return this;
        }

        public JavaIfBlock SingleLineComment(string text)
        {
            contents.SingleLineComment(text);
            return this;
        }

        public JavaIfBlock MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            contents.MultipleLineComment(commentAction);
            return this;
        }

        public JavaIfBlock WordWrappedMultipleLineComment(int wordWrapWidth, Action<JavaWordWrappedMultipleLineComment> commentAction)
        {
            contents.WordWrappedMultipleLineComment(wordWrapWidth, commentAction);
            return this;
        }

        public JavaIfBlock Return(string text)
        {
            contents.Return(text);
            return this;
        }

        public JavaIfBlock Annotation(params string[] annotations)
        {
            contents.Annotation(annotations);
            return this;
        }

        public JavaIfBlock Annotation(IEnumerable<string> annotations)
        {
            contents.Annotation(annotations);
            return this;
        }

        public JavaIfBlock If(string condition, Action<JavaIfBlock> ifAction)
        {
            contents.If(condition, ifAction);
            return this;
        }

        public JavaIfBlock ElseIf(string condition, Action<JavaIfBlock> ifAction)
        {
            contents.ElseIf(condition, ifAction);
            return this;
        }

        public void Else(Action<JavaBlock> elseAction)
        {
            contents.Else(elseAction);
        }
    }
}
