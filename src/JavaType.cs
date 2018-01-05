using System;

namespace AutoRest.Java
{
    public interface JavaType
    {
        void JavadocComment(Action<JavaJavadocComment> commentAction);

        void Annotation(params string[] annotations);

        void PublicMethod(string methodSignature, Action<JavaBlock> functionBlock);
    }
}
