using System;

namespace AutoRest.Java
{
    public interface JavaType
    {
        void MultipleLineComment(Action<JavaMultipleLineComment> commentAction);

        void Annotation(params string[] annotations);

        void PublicMethod(string methodSignature, Action<JavaBlock> functionBlock);
    }
}
