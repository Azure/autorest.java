using AutoRest.Core.Model;
using AutoRest.Extensions.Azure;
using AutoRest.Java.DanModel;

namespace AutoRest.Java.Azure
{
    public static class ClientModelExtensions
    {
        public static bool IsResource(this IModelType type)
        {
            CompositeType compositeType = type as CompositeType;
            return compositeType != null && (DanCodeGenerator.GetIModelTypeName(type) == "Resource" || DanCodeGenerator.GetIModelTypeName(type) == "SubResource") &&
                compositeType.Extensions.ContainsKey(AzureExtensions.AzureResourceExtension) &&
                    (bool)compositeType.Extensions[AzureExtensions.AzureResourceExtension];
        }
    }
}
