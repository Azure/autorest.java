using AutoRest.Core.Utilities;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.DanModel
{
    public class JavaModel : JavaFileGenerator
    {
        public JavaModel(IEnumerable<string> imports, string classComment, IEnumerable<string> classAnnotations, string className, string baseTypeName, IEnumerable<JavaMemberVariable> memberVariables)
        {
            Imports = imports;
            ClassComment = classComment;
            ClassAnnotations = classAnnotations;
            ClassName = className;
            BaseTypeName = baseTypeName;
            MemberVariables = memberVariables;
        }

        protected IEnumerable<string> Imports { get; }

        protected string ClassComment { get; }

        protected IEnumerable<string> ClassAnnotations { get; }

        protected string ClassName { get; }

        protected string BaseTypeName { get; }

        protected IEnumerable<JavaMemberVariable> MemberVariables { get; }

        protected override string FileNameWithoutExtension => ClassName;

        private string ClassNameWithBaseType
        {
            get
            {
                string result = ClassName;

                if (!string.IsNullOrEmpty(BaseTypeName))
                {
                    result += $" extends {BaseTypeName}";
                }

                return result;
            }
        }

        public override JavaFile GenerateJavaFile(string folderPath, string headerComment, string package, int maximumCommentWidth)
        {
            return GenerateJavaFileWithHeaderAndPackage(folderPath, headerComment, package, maximumCommentWidth)
                .Import(Imports)
                .WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                {
                    comment.Line(ClassComment);
                })
                .Annotation(ClassAnnotations)
                .PublicClass(ClassNameWithBaseType, (classBlock) =>
                {
                    if (MemberVariables != null && MemberVariables.Any())
                    {
                        foreach (JavaMemberVariable memberVariable in MemberVariables)
                        {
                            classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                                {
                                    comment.Line(memberVariable.Comment);
                                })
                                .Annotation(memberVariable.Annotation)
                                .Line($"private {memberVariable.WireType.Name} {memberVariable.Name};")
                                .Line();
                        }

                        IEnumerable<JavaMemberVariable> constantMemberVariables = MemberVariables.Where((memberVariable) => memberVariable.IsConstant);
                        if (constantMemberVariables.Any())
                        {
                            classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                                {
                                    comment.Line($"Creates an instance of {ClassName} class.");
                                })
                                .Block($"public {ClassName}()", (constructor) =>
                                {
                                    foreach (JavaMemberVariable memberVariable in constantMemberVariables)
                                    {
                                        JavaType type = memberVariable.WireType;
                                        if (!type.IsPrimitive)
                                        {
                                            classBlock.Line($"{memberVariable.Name} = new {type.Name}();");
                                        }
                                        else
                                        {
                                            classBlock.Line($"{memberVariable.Name} = {memberVariable.DefaultValue};");
                                        }
                                    }
                                })
                                .Line();
                        }

                        foreach (JavaMemberVariable memberVariable in MemberVariables)
                        {
                            GenerateMemberVariableMethods(memberVariable, classBlock, maximumCommentWidth);
                        }
                    }
                });
        }

        private void GenerateMemberVariableMethods(JavaMemberVariable memberVariable, JavaClass classBlock, int maximumCommentWidth)
        {
            string variableName = memberVariable.Name;
            JavaType clientType = memberVariable.ClientType;
            JavaType wireType = memberVariable.WireType;
            string clientTypeName = clientType.Name;
            bool clientTypeDifferentFromWireType = !clientType.Equals(memberVariable.WireType);

            classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                {
                    comment.Line($"Get the {variableName} value.")
                        .Line()
                        .Return($"the {variableName} value");
                })
                .Block($"public {clientTypeName} {variableName}()", (methodBlock) =>
                {
                    if (clientTypeDifferentFromWireType)
                    {
                        methodBlock.If($"this.{variableName} == null", (ifBlock) =>
                        {
                            ifBlock.Return("null");
                        });
                        methodBlock.Return(wireType.ConvertTo(clientType, $"this.{variableName}"));
                    }
                    else
                    {
                        methodBlock.Return($"this.{variableName}");
                    }
                })
                .Line();

            if (!memberVariable.IsReadOnly)
            {
                classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                    {
                        comment.Line($"Set the {variableName} value.")
                            .Line()
                            .Param(variableName, $"the {variableName} value to set")
                            .Return($"the {ClassName} object itself.");
                    })
                    .Block($"public {ClassName} with{variableName.ToPascalCase()}({clientTypeName} {variableName})", (methodBlock) =>
                    {
                        if (clientTypeDifferentFromWireType)
                        {
                            methodBlock.If($"{variableName} == null", (ifBlock) =>
                                {
                                    ifBlock.Line($"this.{variableName} = null;");

                                    ifBlock.Else((elseBlock) =>
                                        {
                                            elseBlock.Line($"this.{variableName} = {clientType.ConvertTo(wireType, variableName)};");
                                        });
                                });
                        }
                        else
                        {
                            methodBlock.Line($"this.{variableName} = {variableName};");
                        }
                        methodBlock.Return("this");
                    })
                    .Line();
            }
        }
    }
}
