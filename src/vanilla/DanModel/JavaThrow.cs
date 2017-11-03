namespace AutoRest.Java.DanModel
{
    public class JavaThrow
    {
        public JavaThrow(string exceptionTypeName, string description)
        {
            ExceptionTypeName = exceptionTypeName;
            Description = description;
        }

        public string ExceptionTypeName { get; }

        public string Description { get; }
    }
}
