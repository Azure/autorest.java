using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java
{
    public class JavaFileContents
    {
        private const string singleIndent = "    ";

        private readonly StringBuilder contents;
        private readonly StringBuilder linePrefix;

        private int? wordWrapWidth;

        private CurrentLineType currentLineType;
        private enum CurrentLineType
        {
            Empty,
            AfterIf,
            Text,
        }

        public JavaFileContents()
            : this(null)
        {
        }

        public JavaFileContents(string fileContents)
        {
            contents = new StringBuilder();
            linePrefix = new StringBuilder();

            if (!string.IsNullOrEmpty(fileContents))
            {
                contents.Append(fileContents);
            }
        }

        public override string ToString()
        {
            return contents.ToString();
        }

        public string[] Lines
        {
            get { return ToString().Split("\n"); }
        }

        public void AddToPrefix(string toAdd)
        {
            linePrefix.Append(toAdd);
        }

        private void RemoveFromPrefix(string toRemove)
        {
            int toRemoveLength = toRemove.Length;
            if (linePrefix.Length <= toRemoveLength)
            {
                linePrefix.Clear();
            }
            else
            {
                linePrefix.Remove(linePrefix.Length - toRemoveLength, toRemoveLength);
            }
        }

        public void SetWordWrapWidth(int? wordWrapWidth)
        {
            this.wordWrapWidth = wordWrapWidth;
        }

        private void WithWordWrap(int wordWrapWidth, Action action)
        {
            SetWordWrapWidth(wordWrapWidth);
            action.Invoke();
            SetWordWrapWidth(null);
        }

        public void Indent(Action action)
        {
            IncreaseIndent();
            action.Invoke();
            DecreaseIndent();
        }

        public void IncreaseIndent()
            => AddToPrefix(singleIndent);

        public void DecreaseIndent()
            => RemoveFromPrefix(singleIndent);

        private IEnumerable<string> WordWrap(string line, bool addPrefix)
        {
            List<string> lines = new List<string>();

            if (wordWrapWidth == null)
            {
                lines.Add(line);
            }
            else
            {
                // Subtract an extra column from the word wrap width because columns generally are
                // 1 -based instead of 0-based.
                int wordWrapIndexMinusLinePrefixLength = wordWrapWidth.Value - (addPrefix ? linePrefix.Length : 0) - 1;
                IEnumerable<string> wrappedLines = line.WordWrap(wordWrapIndexMinusLinePrefixLength);
                foreach (string wrappedLine in wrappedLines.SkipLast(1))
                {
                    lines.Add(wrappedLine + "\n");
                }

                string lastWrappedLine = wrappedLines.Last();
                if (!string.IsNullOrEmpty(lastWrappedLine))
                {
                    lines.Add(lastWrappedLine);
                }
            }

            return lines;
        }

        private void Text(string text, bool addPrefix)
        {
            List<string> lines = new List<string>();

            if (string.IsNullOrEmpty(text))
            {
                lines.Add("");
            }
            else
            {
                int lineStartIndex = 0;
                int textLength = text.Length;
                while (lineStartIndex < textLength)
                {
                    int newLineCharacterIndex = text.IndexOf('\n', lineStartIndex);
                    if (newLineCharacterIndex == -1)
                    {
                        string line = text.Substring(lineStartIndex);
                        IEnumerable<string> wrappedLines = WordWrap(line, addPrefix);
                        lines.AddRange(wrappedLines);
                        lineStartIndex = textLength;
                    }
                    else
                    {
                        int nextLineStartIndex = newLineCharacterIndex + 1;
                        string line = text.Substring(lineStartIndex, nextLineStartIndex - lineStartIndex);
                        IEnumerable<string> wrappedLines = WordWrap(line, addPrefix);
                        lines.AddRange(wrappedLines);
                        lineStartIndex = nextLineStartIndex;
                    }
                }
            }

            string prefix = addPrefix ? linePrefix.ToString() : null;
            foreach (string line in lines)
            {
                if (addPrefix && !string.IsNullOrWhiteSpace(prefix) || (!string.IsNullOrEmpty(prefix) && !string.IsNullOrWhiteSpace(line)))
                {
                    contents.Append(prefix);
                }

                contents.Append(line);
            }
        }

        public void Text(string text)
        {
            if (currentLineType == CurrentLineType.Empty)
            {
                Text(text, addPrefix: true);
            }
            else if (currentLineType == CurrentLineType.Text)
            {
                Text(text, addPrefix: false);
            }
            else if (currentLineType == CurrentLineType.AfterIf)
            {
                Line("", addPrefix: false);
                Text(text, addPrefix: true);
            }
            currentLineType = CurrentLineType.Text;
        }

        private void Line(string text, bool addPrefix)
        {
            Text($"{text}{Environment.NewLine}", addPrefix);
            currentLineType = CurrentLineType.Empty;
        }

        public void Line(string text, params object[] formattedArguments)
        {
            if (formattedArguments != null && formattedArguments.Length > 0)
            {
                text = string.Format(text, formattedArguments);
            }

            if (currentLineType == CurrentLineType.Empty)
            {
                Line(text, addPrefix: true);
            }
            else if (currentLineType == CurrentLineType.Text)
            {
                Line(text, addPrefix: false);
            }
            else if (currentLineType == CurrentLineType.AfterIf)
            {
                Line("", addPrefix: false);
                Line(text, addPrefix: true);
            }
            currentLineType = CurrentLineType.Empty;
        }

        public void Line()
        {
            Line("");
        }

        public void Package(string package)
        {
            Line($"package {package};");
        }

        public void Block(string text, Action<JavaBlock> bodyAction)
        {
            Line($"{text} {{");
            Indent(() =>
                {
                    bodyAction.Invoke(new JavaBlock(this));
                });
            Line($"}}");
        }

        public void Import(params string[] imports)
        {
            Import((IEnumerable<string>)imports);
        }

        public void Import(IEnumerable<string> imports)
        {
            if (imports != null && imports.Any())
            {
                ISet<string> importSet = new SortedSet<string>(imports, new JavaImportComparer());
                foreach (string import in importSet)
                {
                    if (!string.IsNullOrEmpty(import))
                    {
                        Line($"import {import};");
                    }
                }
                Line();
            }
        }

        public void LineComment(string text)
        {
            LineComment(comment =>
            {
                comment.Line(text);
            });
        }

        public void LineComment(Action<JavaLineComment> commentAction)
        {
            AddToPrefix("// ");
            commentAction.Invoke(new JavaLineComment(this));
            RemoveFromPrefix("// ");
        }

        public void LineComment(int wordWrapWidth, Action<JavaLineComment> commentAction)
        {
            LineComment((comment) =>
            {
                WithWordWrap(wordWrapWidth, () =>
                {
                    commentAction.Invoke(new JavaLineComment(this));
                });
            });
        }

        public void JavadocComment(string text)
        {
            JavadocComment(comment =>
            {
                comment.Description(text);
            });
        }

        public void JavadocComment(Action<JavaJavadocComment> commentAction)
        {
            Line("/**");
            AddToPrefix(" * ");
            commentAction.Invoke(new JavaJavadocComment(this));
            RemoveFromPrefix(" * ");
            Line(" */");
        }

        public void JavadocComment(int wordWrapWidth, Action<JavaJavadocComment> commentAction)
        {
            JavadocComment((comment) =>
            {
                WithWordWrap(wordWrapWidth, () =>
                {
                    commentAction.Invoke(new JavaJavadocComment(this));
                });
            });
        }

        public void Return(string text)
        {
            Line($"return {text};");
        }

        public void ReturnAnonymousClass(string anonymousClassDeclaration, Action<JavaClass> anonymousClassBlock)
        {
            Line($"return {anonymousClassDeclaration} {{");
            Indent(() =>
            {
                JavaClass javaClass = new JavaClass(this);
                anonymousClassBlock(javaClass);
            });
            Line("};");
        }

        public void AnonymousClass(string anonymousClassDeclaration, Action<JavaClass> anonymousClassBlock)
        {
            Line($"{anonymousClassDeclaration} {{");
            Indent(() =>
            {
                JavaClass javaClass = new JavaClass(this);
                anonymousClassBlock(javaClass);
            });
            Line("}");
        }

        public void Annotation(params string[] annotations)
        {
            Annotation((IEnumerable<string>)annotations);
        }

        public void Annotation(IEnumerable<string> annotations)
        {
            if (annotations != null && annotations.Any())
            {
                foreach (string annotation in annotations)
                {
                    if (!string.IsNullOrEmpty(annotation))
                    {
                        Line($"@{annotation}");
                    }
                }
            }
        }

        private static string ToString(JavaVisibility visiblity)
        {
            string result;
            switch (visiblity)
            {
                case JavaVisibility.PackagePrivate:
                    result = "";
                    break;

                default:
                    result = visiblity.ToString().ToLower() + ' ';
                    break;
            }
            return result;
        }

        private static string ToString(IEnumerable<JavaModifier> modifiers)
            => modifiers == null ? "" : string.Join("", modifiers.Select(modifier => modifier.ToString().ToLower() + ' '));

        public void Class(JavaVisibility visibility, IEnumerable<JavaModifier> modifiers, string classDeclaration, Action<JavaClass> classAction)
        {
            Block($"{ToString(visibility)}{ToString(modifiers)}class {classDeclaration}", blockAction =>
            {
                if (classAction != null)
                {
                    JavaClass javaClass = new JavaClass(this);
                    classAction.Invoke(javaClass);
                }
            });
        }

        public void Method(JavaVisibility visibility, IEnumerable<JavaModifier> modifiers, string methodSignature, Action<JavaBlock> method)
        {
            Block($"{ToString(visibility)}{ToString(modifiers)}{methodSignature}", method);
        }

        public void Enum(JavaVisibility visibility, string enumName, Action<JavaEnum> enumAction)
        {
            Block($"{ToString(visibility)}enum {enumName}", block =>
            {
                if (enumAction != null)
                {
                    JavaEnum javaEnum = new JavaEnum(this);
                    enumAction.Invoke(javaEnum);
                    javaEnum.AddExpectedNewLineAfterLastValue();
                }
            });
        }

        public void Interface(JavaVisibility visibility, string interfaceSignature, Action<JavaInterface> interfaceAction)
        {
            Line($"{ToString(visibility)}interface {interfaceSignature} {{");
            Indent(() =>
            {
                interfaceAction.Invoke(new JavaInterface(this));
            });
            Line("}");
        }

        public void If(string condition, Action<JavaBlock> ifAction)
        {
            Line($"if ({condition}) {{");
            Indent(() =>
            {
                ifAction.Invoke(new JavaBlock(this));
            });
            Text($"}}");
            currentLineType = CurrentLineType.AfterIf;
        }

        public void Else(Action<JavaBlock> elseAction)
        {
            Line($" else {{", addPrefix: false);
            Indent(() =>
            {
                elseAction.Invoke(new JavaBlock(this));
            });
            Line($"}}");
        }

        public void Lambda(string parameterType, string parameterName, Action<JavaLambda> body)
        {
            Text($"({parameterType} {parameterName}) -> ");
            using (JavaLambda lambda = new JavaLambda(this))
            {
                body.Invoke(lambda);
            }
        }

        public void Lambda(string parameterType, string parameterName, string returnExpression)
        {
            Lambda(parameterType, parameterName, lambda =>
            {
                lambda.Return(returnExpression);
            });
        }
    }
}
