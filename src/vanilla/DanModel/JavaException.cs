using System.IO;

namespace AutoRest.Java.DanModel
{
    public class JavaException
    {
        public const string RelativePackage = "models";

        public JavaException(string exceptionName, string exceptionBodyTypeName)
        {
            ExceptionName = exceptionName;
            ExceptionBodyTypeName = exceptionBodyTypeName;
        }

        private string ExceptionName { get; }

        private string ExceptionBodyTypeName { get; }

        protected string GetFilePath()
        {
            return Path.Combine(RelativePackage, $"{ExceptionName}.java");
        }

        public virtual JavaFile GenerateJavaFile(string headerComment, string package, int maximumMultipleLineCommentWidth)
        {
            string filePath = GetFilePath();
            return new JavaFile(filePath, headerComment, package, maximumMultipleLineCommentWidth)
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
                                .Line("@param message the exception message or the response content if a message is not available")
                                .Line("@param response the HTTP response");
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
                                .Line("@param message the exception message or the response content if a message is not available")
                                .Line("@param response the HTTP response")
                                .Line("@param body the deserialized response body");
                        })
                        .Block($"public {ExceptionName}(final String message, final HttpResponse response, final {ExceptionBodyTypeName} body)", (constructorBlock) =>
                        {
                            constructorBlock.Line("super(message, response, body);");
                        })
                        .Line()
                        .Line("@Override")
                        .Block($"public {ExceptionBodyTypeName} body()", (methodBlock) =>
                        {
                            methodBlock.Return($"({ExceptionBodyTypeName}) super.body()");
                        });
                });
        }
    }
}
