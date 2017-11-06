using System;

namespace AutoRest.Java.DanModel
{
    public class JavaMethodReturn
    {
        public JavaMethodReturn(string type, string description)
        {
            Type = type;
            Description = description;
        }

        public string Type { get; }

        public string Description { get; }
    }
}
