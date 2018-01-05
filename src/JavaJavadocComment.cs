namespace AutoRest.Java
{
    public class JavaJavadocComment
    {
        private readonly JavaFileContents contents;
        private bool expectsLineSeparator;

        public JavaJavadocComment(JavaFileContents contents)
        {
            this.contents = contents;
        }

        private void AddExpectedLineSeparator()
        {
            if (expectsLineSeparator)
            {
                expectsLineSeparator = false;
                contents.Line();
            }
        }

        public JavaJavadocComment Description(string description)
        {
            contents.Line(ProcessText(description));
            expectsLineSeparator = true;
            return this;
        }

        public JavaJavadocComment Param(string parameterName, string parameterDescription)
        {
            AddExpectedLineSeparator();
            contents.CommentParam(parameterName, ProcessText(parameterDescription));
            return this;
        }

        public JavaJavadocComment Return(string returnValueDescription)
        {
            AddExpectedLineSeparator();
            contents.CommentReturn(ProcessText(returnValueDescription));
            return this;
        }

        public JavaJavadocComment Throws(string exceptionTypeName, string description)
        {
            AddExpectedLineSeparator();
            contents.CommentThrows(exceptionTypeName, ProcessText(description));
            return this;
        }

        private static string Trim(string value) => string.IsNullOrEmpty(value) ? value : value.Trim();

        private static string EnsurePeriod(string value) => string.IsNullOrEmpty(value) || value.EndsWith('.') ? value : value + '.';

        private static string ProcessText(string value) => EnsurePeriod(Trim(value));
    }
}
