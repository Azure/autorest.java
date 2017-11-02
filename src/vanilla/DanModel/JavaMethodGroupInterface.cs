using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.DanModel
{
    class JavaMethodGroupInterface : JavaFileGenerator
    {
        public JavaMethodGroupInterface(IEnumerable<string> imports, string typeName, IEnumerable<JavaMethod> methods)
        {
            Imports = imports;
            TypeName = typeName;
            Methods = methods;
        }

        protected IEnumerable<string> Imports { get; }

        protected string TypeName { get; }

        protected IEnumerable<JavaMethod> Methods { get; }

        protected override string FileNameWithoutExtension => TypeName;

        public override JavaFile GenerateJavaFile(string folderPath, string headerComment, string package, int maximumHeaderCommentWidth)
        {
            return GenerateJavaFileWithHeaderAndPackage(folderPath, headerComment, package, maximumHeaderCommentWidth)
                .Import(Imports)
                .WordWrappedMultipleLineComment(maximumHeaderCommentWidth, (comment) =>
                {
                    comment.Line($"An instance of this class provides access to all the operations defined in {TypeName}.");
                })
                .PublicInterface(TypeName, (typeBlock) =>
                {
                    foreach (JavaMethod method in Methods)
                    {
                        typeBlock.MultipleLineComment((comment) =>
                        {
                            comment.Line(method.Description);
                            comment.Line();
                            if (method.Parameters != null)
                            {
                                foreach (JavaMethodParameter parameter in method.Parameters)
                                {
                                    comment.Param(parameter.Name, parameter.Description);
                                }
                            }
                            if (method.Throws != null)
                            {
                                foreach (JavaThrow methodThrow in method.Throws)
                                {
                                    comment.Throws(methodThrow.ExceptionTypeName, methodThrow.Description);
                                }
                            }
                            if (!string.IsNullOrEmpty(method.Return.Description))
                            {
                                comment.Return(method.Return.Description);
                            }
                        });
                        typeBlock.Line($"{method.Return.Type} {method.Name}({string.Join(", ", method.Parameters.Select(parameter => parameter.Declaration))});");
                        typeBlock.Line();
                    }
                });
        }
    }
}
