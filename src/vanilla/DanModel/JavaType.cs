using System;

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

        public override bool Equals(object rhs)
        {
            return rhs is JavaType rhsJavaType &&
                object.Equals(Name, rhsJavaType.Name) &&
                IsPrimitive == rhsJavaType.IsPrimitive;
        }

        public override int GetHashCode()
        {
            return Name.GetHashCode() ^ IsPrimitive.GetHashCode();
        }

        public string ConvertTo(JavaType target, string expression)
        {
            return Convert(this, target, expression);
        }

        private static string Convert(JavaType sourceType, JavaType targetType, string expression)
        {
            string result = null;

            if (Equals(sourceType, targetType))
            {
                result = expression;
            }
            else
            {
                switch (sourceType.Name.ToLower())
                {
                    case "datetime":
                        switch (targetType.Name.ToLower())
                        {
                            case "datetimerfc1123":
                                result = $"new DateTimeRfc1123({expression})";
                                break;
                        }
                        break;

                    case "datetimerfc1123":
                        switch (targetType.Name.ToLower())
                        {
                            case "datetime":
                                result = $"{expression}.dateTime()";
                                break;
                        }
                        break;
                }

                if (result == null)
                {
                    throw new NotSupportedException();
                }
            }

            return result;
        }
    }
}
