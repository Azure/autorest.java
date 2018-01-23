using AutoRest.Core.Utilities;

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

        public void Description(string description)
        {
            string processedText = ProcessText(description);
            if (!string.IsNullOrEmpty(processedText))
            {
                contents.Line(processedText);
                expectsLineSeparator = true;
            }
        }

        public void Param(string parameterName, string parameterDescription)
        {
            AddExpectedLineSeparator();
            contents.Line($"@param {parameterName} {ProcessText(parameterDescription)}");
        }

        public void Return(string returnValueDescription)
        {
            AddExpectedLineSeparator();
            contents.Line($"@return {ProcessText(returnValueDescription)}");
        }

        public void Throws(string exceptionTypeName, string description)
        {
            AddExpectedLineSeparator();
            contents.Line($"@throws {exceptionTypeName} {ProcessText(description)}");
        }

        private static string Trim(string value) => string.IsNullOrEmpty(value) ? value : value.Trim();

        private static string EnsurePeriod(string value) => string.IsNullOrEmpty(value) || value.EndsWith('.') ? value : value + '.';

        private static string ProcessText(string value) => EnsurePeriod(Trim(value)).EscapeXmlComment();
    }
}
