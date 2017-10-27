namespace AutoRest.Java.DanModel
{
    public class JavaMemberVariable
    {
        public JavaMemberVariable(string comment, string annotation, bool final, JavaType type, string name, string defaultValue)
        {
            Comment = comment;
            Annotation = annotation;
            Final = final;
            Type = type;
            Name = name;
            DefaultValue = defaultValue;
        }

        public string Comment { get; }

        public string Annotation { get; }

        public bool Final { get; }

        public JavaType Type { get; }

        public string Name { get; }

        public string DefaultValue { get; }
    }
}
