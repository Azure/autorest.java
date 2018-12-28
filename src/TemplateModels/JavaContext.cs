using System;

namespace AutoRest.Java
{
    public interface JavaContext
    {
        void JavadocComment(Action<JavaJavadocComment> commentAction);

        void Annotation(params string[] annotations);
    }
}
