// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NonStandardToStandardModelMappingHelper
    {
        private readonly FluentMethodGroup fluentMethodGroup;
        private readonly StandardModel standardModel;

        public NonStandardToStandardModelMappingHelper(FluentMethodGroup fluentMethodGroup)
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
                    imports.AddRange(this.getInnerAsyncFunc.ImportsForImpl);
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
                string getInnerMethodName;
                if (isGeneralized)
                {
                    getInnerMethodName = this.getInnerAsyncFunc.GeneralizedMethodName;
                }
                else
                {
                    getInnerMethodName = this.getInnerAsyncFunc.MethodName;
                }
                //
                var standardInnerTypeName = this.standardModel.RawModelName;
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"    .flatMap(new Func1<{nonStandardInnerModelName}, Observable<{standardInnerTypeName}>>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public Observable<{standardInnerTypeName}> call({nonStandardInnerModelName} inner) {{");
                methodBuilder.AppendLine($"            return {getInnerMethodName}({this.getInnerAsyncFunc.MethodInvocationParameter});");
                methodBuilder.AppendLine($"        }}");
                methodBuilder.AppendLine($"    }})");
                return methodBuilder.ToString();
            }
            else
            {
                return string.Empty;
            }
        }

        public string GetPagedListConvertor(string nonStandardInnerModelName, string apiCall, bool isGeneralized)
        {
            if (this.innersRequireMapping.ContainsKey(nonStandardInnerModelName) && this.standardModel != null)
            {
                string getInnerMethodName;
                string wrapModelMethodName;
                if (isGeneralized)
                {
                    getInnerMethodName = this.getInnerAsyncFunc.GeneralizedMethodName;
                    wrapModelMethodName = this.standardModel.WrapExistingModelFunc.GeneralizedMethodName;
                }
                else
                {
                    getInnerMethodName = this.getInnerAsyncFunc.MethodName;
                    wrapModelMethodName = this.standardModel.WrapExistingModelFunc.MethodName;
                }
                //
                var stdInnerModel = this.standardModel.RawModelName;
                var stdModelInterfaceName = this.standardModel.JavaInterfaceName;
                //
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"    PagedListConverter<{nonStandardInnerModelName}, {stdModelInterfaceName}> converter =");
                methodBuilder.AppendLine($"        new PagedListConverter<{nonStandardInnerModelName}, {stdModelInterfaceName}>() {{");
                methodBuilder.AppendLine($"        @Override");
                methodBuilder.AppendLine($"        public Observable<{stdModelInterfaceName}> typeConvertAsync({nonStandardInnerModelName} inner) {{");
                methodBuilder.AppendLine($"             return Observable.just(inner)");
                methodBuilder.AppendLine($"                    .flatMap(new Func1<{nonStandardInnerModelName}, Observable<{stdInnerModel}>>() {{");
                methodBuilder.AppendLine($"                        @Override");
                methodBuilder.AppendLine($"                        public Observable<{stdInnerModel}> call({nonStandardInnerModelName} inner) {{");
                methodBuilder.AppendLine($"                            return {getInnerMethodName}({this.getInnerAsyncFunc.MethodInvocationParameter});");
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
                return string.Empty;
            }
        }

        private Dictionary<string, CompositeTypeJvaf> innersRequireMapping;
        private IGetInnerAsyncFunc getInnerAsyncFunc;
        private void Init()
        {
            this.innersRequireMapping = new Dictionary<string, CompositeTypeJvaf>();
            if (this.standardModel == null)
            {
                return;
            }
            if (this.fluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup)
            {
                this.getInnerAsyncFunc = this.fluentMethodGroup
                    .ResourceGetDescription
                    .GetInnerAsyncFuncFactory
                    .GetFromResourceGroupAsyncFunc;
                //
                var standardModelInner = this.standardModel.RawModel;
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
            else if (this.fluentMethodGroup.ResourceGetDescription.SupportsGetByImmediateParent)
            {
                this.getInnerAsyncFunc = this.fluentMethodGroup
                    .ResourceGetDescription
                    .GetInnerAsyncFuncFactory
                    .GetFromParentAsyncFunc;
                //
                var standardModelInner = this.standardModel.RawModel;
                var getByParentInnerModel = this.fluentMethodGroup.ResourceGetDescription.GetByImmediateParentMethod.InnerReturnType;
                if (getByParentInnerModel.ClassName.Equals(standardModelInner.ClassName))
                {
                    if (this.fluentMethodGroup.ResourceListingDescription.SupportsListByImmediateParent)
                    {
                        var im = this.fluentMethodGroup.ResourceListingDescription.ListByImmediateParentMethod.InnerReturnType;
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
