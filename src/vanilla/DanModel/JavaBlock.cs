using System;
using System.Collections.Generic;

namespace AutoRest.Java.DanModel
{
    public class JavaBlock
    {
        private readonly JavaFileContents contents;

        public JavaBlock(JavaFileContents file)
        {
            this.contents = file;
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

        public JavaBlock Text(string text)
        {
            contents.Text(text);
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

        public JavaBlock BlockStatement(string text, Action<JavaBlock> bodyAction)
        {
            contents.BlockStatement(text, bodyAction);
            return this;
        }

        public JavaBlock SingleLineComment(string text)
        {
            contents.SingleLineComment(text);
            return this;
        }

        public JavaBlock SingleLineSlashSlashComment(string text)
        {
            contents.SingleLineSlashSlashComment(text);
            return this;
        }

        public JavaBlock MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            contents.MultipleLineComment(commentAction);
            return this;
        }

        public JavaBlock WordWrappedMultipleLineComment(int wordWrapWidth, Action<JavaMultipleLineComment> commentAction)
        {
            contents.WordWrappedMultipleLineComment(wordWrapWidth, commentAction);
            return this;
        }

        public JavaBlock Return(string text)
        {
            contents.Return(text);
            return this;
        }

        public JavaBlock ReturnBlock(string text, Action<JavaBlock> block)
        {
            contents.ReturnBlock(text, block);
            return this;
        }

        public JavaBlock Annotation(params string[] annotations)
        {
            contents.Annotation(annotations);
            return this;
        }

        public JavaBlock Annotation(IEnumerable<string> annotations)
        {
            contents.Annotation(annotations);
            return this;
        }

        public JavaIfBlock<JavaBlock> If(string condition, Action<JavaBlock> ifAction)
        {
            contents.If(condition, ifAction);
            return new JavaIfBlock<JavaBlock>(this, contents);
        }
    }
}
