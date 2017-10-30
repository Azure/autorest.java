using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.DanModel
{
    public class JavaWordWrappedMultipleLineComment
    {
        private readonly JavaFileContents contents;

        public JavaWordWrappedMultipleLineComment(JavaFileContents contents)
        {
            this.contents = contents;
        }

        public JavaWordWrappedMultipleLineComment Line(string text)
        {
            contents.Line(text);
            return this;
        }

        public JavaWordWrappedMultipleLineComment Line()
        {
            contents.Line();
            return this;
        }

        public JavaWordWrappedMultipleLineComment Param(string parameterName, string parameterDescription)
        {
            contents.Line($"@param {parameterName} {parameterDescription}");
            return this;
        }

        public JavaWordWrappedMultipleLineComment Return(string returnValueDescription)
        {
            contents.Line($"@return {returnValueDescription}");
            return this;
        }
    }
}
