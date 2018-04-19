using AutoRest.Core.Model;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Azure.Model;

namespace AutoRest.Java.Azure
{
    public static class ClientModelExtensions
    {
        public static bool IsResource(this IModelType type)
        {
            CompositeTypeJva compositeType = type as CompositeTypeJva;
            return compositeType != null && compositeType.IsResource;
        }
    }
}
