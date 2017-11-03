namespace AutoRest.Java.DanModel
{
    public class JavaMethodParameter
    {
        public JavaMethodParameter(string description, string type, string name, bool final = false)
        {
            Description = description;
            Type = type;
            Name = name;
            Final = final;
        }

        public string Description { get; }

        public string Type { get; }

        public string Name { get; }

        public bool Final { get; }

        public string Declaration => (Final ? "final " : "") + $"{Type} {Name}";
    }
}
