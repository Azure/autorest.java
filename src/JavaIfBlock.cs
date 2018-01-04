using System;

namespace AutoRest.Java
{
    public class JavaIfBlock<TParentBlock>
    {
        private readonly TParentBlock parentBlock;
        private readonly JavaFileContents contents;

        public JavaIfBlock(TParentBlock parentBlock, JavaFileContents contents)
        {
            this.parentBlock = parentBlock;
            this.contents = contents;
        }

        public TParentBlock Line(string text)
        {
            contents.Line(text);
            return parentBlock;
        }

        public TParentBlock Line()
        {
            contents.Line();
            return parentBlock;
        }

        public TParentBlock Block(string text, Action<JavaBlock> bodyAction)
        {
            contents.Block(text, bodyAction);
            return parentBlock;
        }

        public TParentBlock SingleLineComment(string text)
        {
            contents.SingleLineComment(text);
            return parentBlock;
        }

        public TParentBlock MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            contents.MultipleLineComment(commentAction);
            return parentBlock;
        }

        public TParentBlock WordWrappedMultipleLineComment(int wordWrapWidth, Action<JavaMultipleLineComment> commentAction)
        {
            contents.WordWrappedMultipleLineComment(wordWrapWidth, commentAction);
            return parentBlock;
        }

        public TParentBlock Return(string text)
        {
            contents.Return(text);
            return parentBlock;
        }

        public JavaIfBlock<TParentBlock> If(string condition, Action<JavaBlock> ifAction)
        {
            contents.If(condition, ifAction);
            return this;
        }

        public JavaIfBlock<TParentBlock> ElseIf(string condition, Action<JavaBlock> ifAction)
        {
            contents.ElseIf(condition, ifAction);
            return this;
        }

        public TParentBlock Else(Action<JavaBlock> elseAction)
        {
            contents.Else(elseAction);
            return parentBlock;
        }
    }
}
