using System.IO;

namespace AutoRest.Java.DanModel
{
    public class JavaException : JavaFileGenerator
    {
        public JavaException(string exceptionName, string exceptionBodyTypeName)
        {
            ExceptionName = exceptionName;
            ExceptionBodyTypeName = exceptionBodyTypeName;
        }

        private string ExceptionName { get; }

        private string ExceptionBodyTypeName { get; }

        protected override string FileNameWithoutExtension => ExceptionName;

        public override JavaFile GenerateJavaFile(string folderPath, string headerComment, string package, int maximumHeaderCommentWidth)
        {
            return GenerateJavaFileWithHeaderAndPackage(folderPath, headerComment, package, maximumHeaderCommentWidth)
                .Import("com.microsoft.rest.RestException",
                        "com.microsoft.rest.http.HttpResponse")
                .MultipleLineComment((comment) =>
                {
                    comment.Line($"Exception thrown for an invalid response with {ExceptionBodyTypeName} information.");
                })
                .Block($"public class {ExceptionName} extends RestException", (classBlock) =>
                {
                    classBlock.MultipleLineComment((comment) =>
                        {
                            comment.Line($"Initializes a new instance of the {ExceptionName} class.")
                                .Line()
                                .Param("message", "the exception message or the response content if a message is not available")
                                .Param("response", "the HTTP response");
                        })
                        .Block($"public {ExceptionName}(final String message, HttpResponse response)", (constructorBlock) =>
                        {
                            constructorBlock.Line("super(message, response);");
                        })
                        .Line()
                        .MultipleLineComment((comment) =>
                        {
                            comment.Line($"Initializes a new instance of the {ExceptionName} class.")
                                .Line()
                                .Param("message", "the exception message or the response content if a message is not available")
                                .Param("response", "the HTTP response")
                                .Param("body", "the deserialized response body");
                        })
                        .Block($"public {ExceptionName}(final String message, final HttpResponse response, final {ExceptionBodyTypeName} body)", (constructorBlock) =>
                        {
                            constructorBlock.Line("super(message, response, body);");
                        })
                        .Line()
                        .Annotation("Override")
                        .Block($"public {ExceptionBodyTypeName} body()", (methodBlock) =>
                        {
                            methodBlock.Return($"({ExceptionBodyTypeName}) super.body()");
                        });
                });
        }
    }
}
