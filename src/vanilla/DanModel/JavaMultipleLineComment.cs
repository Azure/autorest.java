namespace AutoRest.Java.DanModel
{
    public class JavaMultipleLineComment
    {
        private readonly JavaFileContents contents;
        private bool expectsLineSeparator;

        public JavaMultipleLineComment(JavaFileContents contents)
        {
            this.contents = contents;
        }

        private void AddExpectedLineSeparator()
        {
            if (expectsLineSeparator)
            {
                expectsLineSeparator = false;
                Line();
            }
        }

        public JavaMultipleLineComment Line(string text)
        {
            AddExpectedLineSeparator();
            contents.Line(text);
            return this;
        }

        public JavaMultipleLineComment Line()
        {
            AddExpectedLineSeparator();
            contents.Line();
            return this;
        }

        public JavaMultipleLineComment Description(string description)
        {
            contents.Line(description);
            expectsLineSeparator = true;
            return this;
        }

        public JavaMultipleLineComment Param(string parameterName, string parameterDescription)
        {
            AddExpectedLineSeparator();
            contents.CommentParam(parameterName, parameterDescription);
            return this;
        }

        public JavaMultipleLineComment Return(string returnValueDescription)
        {
            AddExpectedLineSeparator();
            contents.CommentReturn(returnValueDescription);
            return this;
        }

        public JavaMultipleLineComment Throws(string exceptionTypeName, string description)
        {
            AddExpectedLineSeparator();
            contents.CommentThrows(exceptionTypeName, description);
            return this;
        }
    }
}
