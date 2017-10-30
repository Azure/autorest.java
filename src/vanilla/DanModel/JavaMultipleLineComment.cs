using System;

namespace AutoRest.Java.DanModel
{
    public class JavaMultipleLineComment
    {
        private readonly JavaFileContents contents;

        public JavaMultipleLineComment(JavaFileContents contents)
        {
            this.contents = contents;
        }

        public JavaMultipleLineComment Line(string text)
        {
            contents.Line(text);
            return this;
        }

        public JavaMultipleLineComment Line()
        {
            contents.Line();
            return this;
        }

        public JavaMultipleLineComment Param(string parameterName, string parameterDescription)
        {
            contents.CommentParam(parameterName, parameterDescription);
            return this;
        }

        public JavaMultipleLineComment Return(string returnValueDescription)
        {
            contents.CommentReturn(returnValueDescription);
            return this;
        }
    }
}
