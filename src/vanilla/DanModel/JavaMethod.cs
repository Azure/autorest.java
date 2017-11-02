using System.Collections.Generic;

namespace AutoRest.Java.DanModel
{
    public class JavaMethod
    {
        public JavaMethod(string description, IEnumerable<JavaThrow> throws, JavaMethodReturn methodReturn, string name, IEnumerable<JavaMethodParameter> parameters)
        {
            Description = description;
            Throws = throws;
            Return = methodReturn;
            Name = name;
            Parameters = parameters;
        }

        public string Description { get; }

        public IEnumerable<JavaThrow> Throws { get; }

        public JavaMethodReturn Return { get; }

        public string Name { get; }

        public IEnumerable<JavaMethodParameter> Parameters { get; }
    }
}
