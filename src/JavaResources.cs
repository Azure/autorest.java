using AutoRest.Core.Model;
using System;
using AutoRest.Extensions.Azure;
using static AutoRest.Core.Utilities.DependencyInjection;

namespace AutoRest.Java
{
    public class JavaResources
    {
        internal static CompositeType _resourceType;
        internal static CompositeType _proxyResourceType;
        internal static CompositeType _subResourceType;

        static JavaResources()
        {
            var stringType = New<PrimaryType>(KnownPrimaryType.String, new
            {
                Name = "string"
            });

            _proxyResourceType = New<CompositeType>(new
            {
                SerializedName = "ProxyResource"
            });
            _proxyResourceType.Name.FixedValue = "ProxyResource";
            _proxyResourceType.Add(New<Property>(new { Name = "id", SerializedName = "id", ModelType = stringType, IsReadOnly = true }));
            _proxyResourceType.Add(New<Property>(new { Name = "name", SerializedName = "name", ModelType = stringType, IsReadOnly = true }));
            _proxyResourceType.Add(New<Property>(new { Name = "type", SerializedName = "type", ModelType = stringType, IsReadOnly = true }));
            _proxyResourceType.Extensions[AzureExtensions.AzureResourceExtension] = true;

            _resourceType = New<CompositeType>(new
            {
                SerializedName = "Resource",
            });
            _resourceType.Name.FixedValue = "Resource";
            _resourceType.BaseModelType = _proxyResourceType;
            _resourceType.Add(New<Property>(new { Name = "location", SerializedName = "location", ModelType = stringType, IsRequired = true }));
            _resourceType.Add(New<Property>(new { Name = "tags", SerializedName = "tags", ModelType = New<DictionaryType>(new { ValueType = stringType, NameFormat = "System.Collections.Generic.IDictionary<string, {0}>" }) }));
            _resourceType.Extensions[AzureExtensions.AzureResourceExtension] = true;

            _subResourceType = New<CompositeType>(new
            {
                SerializedName = "SubResource"
            });
            _subResourceType.Name.FixedValue = "SubResource";
            _subResourceType.Add(New<Property>(new { Name = "id", SerializedName = "id", ModelType = stringType, IsReadOnly = true }));
            _subResourceType.Extensions[AzureExtensions.AzureResourceExtension] = true;
        }
    }
}
