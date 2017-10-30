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
                                .Line($"private {memberVariable.Type.Name} {memberVariable.Name};")
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
                                        JavaType type = memberVariable.Type;
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
            JavaType variableType = memberVariable.Type;
            string variableTypeName = variableType.Name;

            classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                {
                    comment.Line($"Get the {variableName} value.")
                        .Line()
                        .Return($"the {variableName} value");
                })
                .Block($"public {variableTypeName} {variableName}()", (methodBlock) =>
                {
                    methodBlock.Return($"this.{variableName}");
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
                    .Block($"public {ClassName} with{variableName.ToPascalCase()}({variableTypeName} {variableName})", (methodBlock) =>
                    {
                        methodBlock.Line($"this.{variableName} = {variableName};")
                            .Return("this");
                    })
                    .Line();
            }
        }
    }
}
