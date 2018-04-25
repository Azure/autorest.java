// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Java.azurefluent.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NonStandardToStanardModelMappingHelper
    {
        private readonly FluentMethodGroup fluentMethodGroup;
        private FluentModel standardModel;
        private Dictionary<string, CompositeTypeJvaf> innersRequireMapping;

        public NonStandardToStanardModelMappingHelper(FluentMethodGroup fluentMethodGroup)
        {
            this.fluentMethodGroup = fluentMethodGroup;
            this.standardModel = this.fluentMethodGroup.StandardFluentModel;
            this.Init();
        }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.innersRequireMapping.Any())
                {
                    imports.Add("com.microsoft.azure.arm.resources.ResourceUtilsCore");
                    imports.Add("rx.functions.Func1");
                    imports.Add("com.microsoft.azure.arm.utils.PagedListConverter");
                }
                return imports;
            }
        }

        public string GetFlatMapToStandardModelFor(string nonStandardInnerModelName)
        {
            if (this.innersRequireMapping.ContainsKey(nonStandardInnerModelName) && this.standardModel != null)
            {
                var standardInnerTypeName = this.standardModel.InnerModel.ClassName;
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"    .flatMap(new Func1<{nonStandardInnerModelName}, Observable<{standardInnerTypeName}>>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public Observable<{standardInnerTypeName}> call({nonStandardInnerModelName} inner) {{");
                methodBuilder.AppendLine($"            return getInnerAsync(ResourceUtilsCore.groupFromResourceId(inner.id()), ResourceUtilsCore.nameFromResourceId(inner.id()));");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"    }})");
                return methodBuilder.ToString();
            }
            else
            {
                return String.Empty;
            }
        }

        public string GetPagedListConvertor(string nonStandardInnerModelName, string apiCall)
        {
            if (this.innersRequireMapping.ContainsKey(nonStandardInnerModelName) && this.standardModel != null)
            {
                var standardInnerTypeName = this.standardModel.InnerModel.ClassName;
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"    PagedListConverter<{nonStandardInnerModelName}, {this.standardModel.JavaInterfaceName}> converter =");
                methodBuilder.AppendLine($"        new PagedListConverter<{nonStandardInnerModelName}, {this.standardModel.JavaInterfaceName}>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public Observable<{this.standardModel.JavaInterfaceName}> typeConvertAsync({nonStandardInnerModelName} inner) {{");
                methodBuilder.AppendLine($"             return Observable.just(inner)");
                methodBuilder.AppendLine($"                    .flatMap(new Func1<{nonStandardInnerModelName}, Observable<{this.standardModel.InnerModel.ClassName}>>() {{");
                methodBuilder.AppendLine($"                        @Override");
                methodBuilder.AppendLine($"                        public Observable<{this.standardModel.InnerModel.ClassName}> call({nonStandardInnerModelName} inner) {{");
                methodBuilder.AppendLine($"                            return getInnerAsync(ResourceUtilsCore.groupFromResourceId(inner.id()), ResourceUtilsCore.nameFromResourceId(inner.id()));");
                methodBuilder.AppendLine($"                        }}");
                methodBuilder.AppendLine($"                    }})");
                methodBuilder.AppendLine($"                    .map(new Func1<{this.standardModel.InnerModel.ClassName}, MediaService>() {{");
                methodBuilder.AppendLine($"                        @Override");
                methodBuilder.AppendLine($"                        public {this.standardModel.JavaInterfaceName} call({this.standardModel.InnerModel.ClassName} inner) {{");
                methodBuilder.AppendLine($"                            return wrapModel(inner);");
                methodBuilder.AppendLine($"                        }}");
                methodBuilder.AppendLine($"                    }});");
                methodBuilder.AppendLine($"            }}");
                methodBuilder.AppendLine($"        }};");
                methodBuilder.AppendLine($"        return converter.convert({apiCall});");
                return methodBuilder.ToString();
            }
            else
            {
                return String.Empty;
            }
        }


        private void Init()
        {
            this.innersRequireMapping = new Dictionary<string, CompositeTypeJvaf>();
            if (this.standardModel == null)
            {
                return;
            }
            if (this.fluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup)
            {
                var standardModelInner = this.standardModel.InnerModel;
                var getByRGInnerModel = this.fluentMethodGroup.ResourceGetDescription.GetByResourceGroupMethod.InnerReturnType;
                if (getByRGInnerModel.ClassName.Equals(standardModelInner.ClassName))
                {
                    if (this.fluentMethodGroup.ResourceCreateDescription.SupportsCreating)
                    {
                        var im = this.fluentMethodGroup.ResourceCreateDescription.CreateMethod.InnerReturnType;
                        if (!im.ClassName.Equals(standardModelInner.ClassName))
                        {
                            this.innersRequireMapping.AddIfNotExists(im.ClassName, im);
                        }
                    }
                    //
                    if (this.fluentMethodGroup.ResourceListingDescription.SupportsListByResourceGroup)
                    {
                        var im = this.fluentMethodGroup.ResourceListingDescription.ListByResourceGroupMethod.InnerReturnType;
                        if (!im.ClassName.Equals(standardModelInner.ClassName))
                        {
                            this.innersRequireMapping.AddIfNotExists(im.ClassName, im);
                        }
                    }
                    //
                    if (this.fluentMethodGroup.ResourceListingDescription.SupportsListBySubscription)
                    {
                        var im = this.fluentMethodGroup.ResourceListingDescription.ListBySubscriptionMethod.InnerReturnType;
                        if (!im.ClassName.Equals(standardModelInner.ClassName))
                        {
                            this.innersRequireMapping.AddIfNotExists(im.ClassName, im);
                        }
                    }
                }
            }
        }
    }
}
