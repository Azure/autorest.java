using System;

namespace AutoRest.Java
{
    public interface JavaType : JavaContext
    {
        void PublicMethod(string methodSignature, Action<JavaBlock> functionBlock);
    }
}
