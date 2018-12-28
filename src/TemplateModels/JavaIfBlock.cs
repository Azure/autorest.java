using System;

namespace AutoRest.Java
{
    public class JavaIfBlock
    {
        private readonly JavaFileContents contents;

        public JavaIfBlock(JavaFileContents contents)
        {
            this.contents = contents;
        }

        public void Else(Action<JavaBlock> elseAction)
        {
            contents.Else(elseAction);
        }
    }
}
