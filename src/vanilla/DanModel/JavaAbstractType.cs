using System;

namespace AutoRest.Java.DanModel
{
    public interface JavaAbstractType
    {
        void MultipleLineComment(Action<JavaMultipleLineComment> commentAction);

        void Annotation(params string[] annotations);
    }
}
