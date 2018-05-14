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
        private readonly StandardModel standardModel;

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

        public string GetFlatMapToStandardModelFor(string nonStandardInnerModelName, bool isGeneralized)
        {
            if (this.innersRequireMapping.ContainsKey(nonStandardInnerModelName) && this.standardModel != null)
            {
                string getInnerMethodName = isGeneralized ? this.getInnerAsyncFunc.GeneralizedMethodName 
                    : this.getInnerAsyncFunc.MethodName;

                var standardInnerTypeName = this.standardModel.InnerModelName;
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"    .flatMap(new Func1<{nonStandardInnerModelName}, Observable<{standardInnerTypeName}>>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public Observable<{standardInnerTypeName}> call({nonStandardInnerModelName} inner) {{");
                methodBuilder.AppendLine($"            return {getInnerMethodName}(ResourceUtilsCore.groupFromResourceId(inner.id()), ResourceUtilsCore.nameFromResourceId(inner.id()));");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"    }})");
                return methodBuilder.ToString();
            }
            else
            {
                return String.Empty;
            }
        }

        public string GetPagedListConvertor(string nonStandardInnerModelName, string apiCall, bool isGeneralized)
        {
            if (this.innersRequireMapping.ContainsKey(nonStandardInnerModelName) && this.standardModel != null)
            {
                string getInnerMethodName = isGeneralized ? this.getInnerAsyncFunc.GeneralizedMethodName
                    : this.getInnerAsyncFunc.MethodName;

                string wrapModelMethodName = isGeneralized ? this.standardModel.WrapExistingModelFunc.GeneralizedMethodName
                    : this.standardModel.WrapExistingModelFunc.MethodName;

                var stdInnerModel = this.standardModel.InnerModelName;
                var stdModelInterfaceName = this.standardModel.JavaInterfaceName;
                
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"    PagedListConverter<{nonStandardInnerModelName}, {stdModelInterfaceName}> converter =");
                methodBuilder.AppendLine($"        new PagedListConverter<{nonStandardInnerModelName}, {stdModelInterfaceName}>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public Observable<{stdModelInterfaceName}> typeConvertAsync({nonStandardInnerModelName} inner) {{");
                methodBuilder.AppendLine($"             return Observable.just(inner)");
                methodBuilder.AppendLine($"                    .flatMap(new Func1<{nonStandardInnerModelName}, Observable<{stdInnerModel}>>() {{");
                methodBuilder.AppendLine($"                        @Override");
                methodBuilder.AppendLine($"                        public Observable<{stdInnerModel}> call({nonStandardInnerModelName} inner) {{");
                methodBuilder.AppendLine($"                            return {getInnerMethodName}(ResourceUtilsCore.groupFromResourceId(inner.id()), ResourceUtilsCore.nameFromResourceId(inner.id()));");
                methodBuilder.AppendLine($"                        }}");
                methodBuilder.AppendLine($"                    }})");
                methodBuilder.AppendLine($"                    .map(new Func1<{stdInnerModel}, {stdModelInterfaceName}>() {{");
                methodBuilder.AppendLine($"                        @Override");
                methodBuilder.AppendLine($"                        public {stdModelInterfaceName} call({stdInnerModel} inner) {{");
                methodBuilder.AppendLine($"                            return {wrapModelMethodName}(inner);");
                methodBuilder.AppendLine($"                        }}");
                methodBuilder.AppendLine($"                    }});");
                methodBuilder.AppendLine($"            }}");
                methodBuilder.AppendLine($"        }};");
                methodBuilder.AppendLine($"    return converter.convert({apiCall});");
                return methodBuilder.ToString();
            }
            else
            {
                return String.Empty;
            }
        }

        private Dictionary<string, CompositeTypeJvaf> innersRequireMapping;
        private GetInnerAsyncFunc getInnerAsyncFunc;
        private void Init()
        {
            this.innersRequireMapping = new Dictionary<string, CompositeTypeJvaf>();
            if (this.standardModel == null)
            {
                return;
            }
            if (this.fluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup)
            {
                this.getInnerAsyncFunc = this.fluentMethodGroup.ResourceGetDescription.GetInnerAsyncFunc;
                //
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
