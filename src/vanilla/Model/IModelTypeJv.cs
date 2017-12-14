using AutoRest.Core.Model;
using AutoRest.Java.DanModel;

namespace AutoRest.Java.Model
{
    public static class IModelTypeExtensions
    {
        public static IModelType ServiceResponseVariant(this IModelType original, bool wantNullable = false)
        {
            if (wantNullable)
            {
                return original; // the original is always nullable
            }
            return DanCodeGenerator.GetIModelTypeNonNullableVariant(DanCodeGenerator.GetIModelTypeResponseVariant(original)) ?? original;
        }

        public static string GetDefaultValue(this IModelType type, Method parent)
        {
            return type is PrimaryTypeJv && DanCodeGenerator.GetIModelTypeName(type) == "RequestBody"
                ? "RequestBody.create(MediaType.parse(\"" + parent.RequestContentType + "\"), new byte[0])"
                : type.DefaultValue;
        }
    }
}
