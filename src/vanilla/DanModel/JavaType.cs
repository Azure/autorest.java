namespace AutoRest.Java.DanModel
{
    public class JavaType
    {
        public JavaType(string name, bool isPrimitive)
        {
            Name = name;
            IsPrimitive = isPrimitive;
        }

        public string Name { get; }

        public bool IsPrimitive { get; }
    }
}
