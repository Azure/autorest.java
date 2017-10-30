namespace AutoRest.Java.DanModel
{
    public class JavaMemberVariable
    {
        public JavaMemberVariable(string comment, string annotation, bool isConstant, bool isReadOnly, JavaType type, string name, string defaultValue)
        {
            Comment = comment;
            Annotation = annotation;
            IsConstant = isConstant;
            IsReadOnly = isReadOnly;
            Type = type;
            Name = name;
            DefaultValue = defaultValue;
        }

        public string Comment { get; }

        public string Annotation { get; }

        /// <summary>
        /// Whether or not this member variable has only one valid value and is therefore
        /// constantly set to that value.
        /// </summary>
        public bool IsConstant { get; }

        /// <summary>
        /// Whether or not this member variable can only be modified by the server (not by the
        /// client, like a ProvisioningState value).
        /// </summary>
        public bool IsReadOnly { get; }

        public JavaType Type { get; }

        public string Name { get; }

        public string DefaultValue { get; }
    }
}
