namespace AutoRest.Java.DanModel
{
    public class JavaMemberVariable
    {
        public JavaMemberVariable(string comment, string annotation, bool isConstant, bool isReadOnly, JavaType wireType, JavaType clientType, string name, string defaultValue)
        {
            Comment = comment;
            Annotation = annotation;
            IsConstant = isConstant;
            IsReadOnly = isReadOnly;
            WireType = wireType;
            ClientType = clientType;
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

        /// <summary>
        /// The type of this variable that is transferred over the network.
        /// </summary>
        public JavaType WireType { get; }

        /// <summary>
        /// The type of this variable that is exposed via getter and setter methods.
        /// </summary>
        public JavaType ClientType { get; }

        public string Name { get; }

        public string DefaultValue { get; }
    }
}
