using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.DanModel
{
    public class JavaFileContents
    {
        private const string singleIndent = "    ";

        private readonly StringBuilder contents = new StringBuilder();
        private readonly StringBuilder linePrefix = new StringBuilder();

        private int? wordWrapWidth;

        private bool previousLineEndingPending;

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

        private void ClosePreviousLineEnding()
        {
            if (previousLineEndingPending)
            {
                previousLineEndingPending = false;

                Line();
            }
        }

        public void Text(string text)
        {
            ClosePreviousLineEnding();

            Text(text, addPrefix: true);
        }

        private void Line(string text, bool addPrefix)
        {
            Text($"{text}{Environment.NewLine}", addPrefix);
        }

        public JavaFileContents Line(string text, params object[] formattedArguments)
        {
            ClosePreviousLineEnding();

            if (formattedArguments != null && formattedArguments.Length > 0)
            {
                text = string.Format(text, formattedArguments);
            }

            Line(text, addPrefix: true);
            return this;
        }

        public JavaFileContents Line()
        {
            return Line("");
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

        public void BlockStatement(string text, Action<JavaBlock> bodyAction)
        {
            Line($"{text} {{");
            Indent(() =>
            {
                bodyAction.Invoke(new JavaBlock(this));
            });
            Line($"}};");
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

        public void SingleLineSlashStarComment(string text)
        {
            Line($"/** {text} */");
        }

        public void SingleLineSlashSlashComment(string text)
        {
            Line($"// {text}");
        }

        public void SingleLineComment(string text)
        {
            SingleLineSlashStarComment(text);
        }

        public void MultipleLineSlashStarComment(Action<JavaMultipleLineComment> commentAction)
        {
            Line("/**");
            AddToPrefix(" * ");
            commentAction.Invoke(new JavaMultipleLineComment(this));
            RemoveFromPrefix(" * ");
            Line(" */");
        }

        public void MultipleLineSlashSlashComment(Action<JavaMultipleLineComment> commentAction)
        {
            AddToPrefix("// ");
            commentAction.Invoke(new JavaMultipleLineComment(this));
            RemoveFromPrefix("// ");
        }

        public void MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            MultipleLineSlashStarComment(commentAction);
        }

        public void WordWrappedMultipleLineSlashStarComment(int wordWrapWidth, Action<JavaMultipleLineComment> commentAction)
        {
            MultipleLineComment((comment) =>
            {
                WithWordWrap(wordWrapWidth, () =>
                {
                    commentAction.Invoke(new JavaMultipleLineComment(this));
                });
            });
        }

        public void WordWrappedMultipleLineSlashSlashComment(int wordWrapWidth, Action<JavaMultipleLineComment> commentAction)
        {
            MultipleLineSlashSlashComment((comment) =>
            {
                WithWordWrap(wordWrapWidth, () =>
                {
                    commentAction.Invoke(new JavaMultipleLineComment(this));
                });
            });
        }

        public void WordWrappedMultipleLineComment(int wordWrapWidth, Action<JavaMultipleLineComment> commentAction)
        {
            WordWrappedMultipleLineSlashStarComment(wordWrapWidth, commentAction);
        }

        public void Return(string text)
        {
            Line($"return {text};");
        }

        public void ReturnBlock(string text, Action<JavaBlock> bodyAction)
        {
            Line($"return {text} {{");
            Indent(() =>
            {
                bodyAction.Invoke(new JavaBlock(this));
            });
            Line($"}};");
        }

        public void ReturnAnonymousClass(string anonymousClassDeclaration, Action<JavaClass> anonymousClassBlock)
        {
            Line($"return {anonymousClassDeclaration} {{");
            Indent(() =>
            {
                JavaClass javaClass = new JavaClass(this);
                anonymousClassBlock.Invoke(javaClass);
            });
            Line("};");
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

        public void PublicFinalClass(string classDeclaration, Action<JavaClass> classAction)
        {
            Block($"public final class {classDeclaration}", (blockAction) =>
            {
                if (classAction != null)
                {
                    JavaClass javaClass = new JavaClass(this);
                    classAction.Invoke(javaClass);
                }
            });
        }

        public void PublicClass(string classDeclaration, Action<JavaClass> classAction)
        {
            Block($"public class {classDeclaration}", (blockAction) =>
            {
                if (classAction != null)
                {
                    JavaClass javaClass = new JavaClass(this);
                    classAction.Invoke(javaClass);
                }
            });
        }

        public void PrivateStaticFinalClass(string classSignature, Action<JavaClass> classAction)
        {
            Block($"private static final class {classSignature}", (blockAction) =>
            {
                if (classAction != null)
                {
                    JavaClass javaClass = new JavaClass(this);
                    classAction.Invoke(javaClass);
                }
            });
        }

        public void PublicEnum(string enumName, Action<JavaBlock> enumAction)
        {
            Block($"public enum {enumName}", enumAction);
        }

        public void PublicInterface(string interfaceSignature, Action<JavaInterface> interfaceAction)
        {
            Line($"public interface {interfaceSignature} {{");
            Indent(() =>
            {
                interfaceAction.Invoke(new JavaInterface(this));
            });
            Line($"}}");
        }

        public void Interface(string interfaceSignature, Action<JavaInterface> interfaceAction)
        {
            Line($"interface {interfaceSignature} {{");
            Indent(() =>
            {
                interfaceAction.Invoke(new JavaInterface(this));
            });
            Line($"}}");
        }

        public void CommentParam(string parameterName, string parameterDescription)
        {
            Line($"@param {parameterName} {parameterDescription}");
        }

        public void CommentReturn(string returnValueDescription)
        {
            Line($"@return {returnValueDescription}");
        }

        public void CommentThrows(string exceptionTypeName, string description)
        {
            Line($"@throws {exceptionTypeName} {description}");
        }

        public void If(string condition, Action<JavaBlock> ifAction)
        {
            Line($"if ({condition}) {{");
            Indent(() =>
            {
                ifAction.Invoke(new JavaBlock(this));
            });
            Text($"}}");

            previousLineEndingPending = true;
        }

        public void ElseIf(string condition, Action<JavaBlock> elseIfAction)
        {
            previousLineEndingPending = false;

            Line($" else if ({condition}) {{", addPrefix: false);
            Indent(() =>
            {
                elseIfAction.Invoke(new JavaBlock(this));
            });
            Text($"}}");

            previousLineEndingPending = true;
        }

        public void Else(Action<JavaBlock> elseAction)
        {
            previousLineEndingPending = false;

            Line($" else {{", addPrefix: false);
            Indent(() =>
            {
                elseAction.Invoke(new JavaBlock(this));
            });
            Line($"}}");
        }
    }
}
