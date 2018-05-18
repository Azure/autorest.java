// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ResourceDeleteDescription : IResourceDeleteDescription
    {
        private readonly FluentMethodGroup fluentMethodGroup;
        private bool isProcessed;

        private bool supportsDeleteByResourceGroup;
        private FluentMethod deleteByResourceGroupMethod;
        private bool supportsDeleteBySubscription;
        private FluentMethod deleteBySubscriptionMethod;
        private bool supportsDeleteByImmediateParent;
        private FluentMethod deleteByImmediateParentMethod;

        public ResourceDeleteDescription(FluentMethodGroup fluentMethodGroup)
        {
            this.fluentMethodGroup = fluentMethodGroup;
        }
        public bool SupportsDeleteByResourceGroup
        {
            get
            {
                Process();
                return this.supportsDeleteByResourceGroup;
            }
        }

        public FluentMethod DeleteByResourceGroupMethod
        {
            get
            {
                Process();
                return this.deleteByResourceGroupMethod;
            }
        }

        public bool SupportsDeleteBySubscription
        {
            get
            {
                Process();
                return this.supportsDeleteBySubscription;
            }
        }

        public FluentMethod DeleteBySubcriptionMethod
        {
            get
            {
                Process();
                return this.deleteBySubscriptionMethod;
            }
        }


        public bool SupportsDeleteByImmediateParent
        {
            get
            {
                Process();
                return this.supportsDeleteByImmediateParent;
            }
        }

        public FluentMethod DeleteByImmediateParentMethod
        {
            get
            {
                Process();
                return this.deleteByImmediateParentMethod;
            }
        }

        public HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                HashSet<string> extendsFrom = new HashSet<string>();
                if (this.SupportsDeleteByResourceGroup)
                {
                    extendsFrom.Add("SupportsDeletingByResourceGroup");
                    extendsFrom.Add("SupportsBatchDeletion");
                }
                return extendsFrom;
            }
        }

        public HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsDeleteByResourceGroup)
                {
                    imports.Add("com.microsoft.azure.arm.resources.collection.SupportsDeletingByResourceGroup");
                    imports.Add("com.microsoft.azure.arm.resources.collection.SupportsBatchDeletion");
                }
                if (this.supportsDeleteByImmediateParent)
                {
                    imports.Add("rx.Completable");
                }
                return imports;
            }
        }

        public HashSet<String> ImportsForMethodGroupImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsDeleteByResourceGroup)
                {
                    imports.Add("rx.Completable");
                    // For 'SupportBatchDelete' interface impl
                    //
                    imports.Add($"java.util.ArrayList");
                    imports.Add($"java.util.Arrays");
                    imports.Add($"java.util.Collection");
                    imports.Add($"com.microsoft.azure.arm.resources.ResourceUtilsCore");
                    imports.Add($"com.microsoft.azure.arm.utils.RXMapper");
                }
                if (this.SupportsDeleteByImmediateParent)
                {
                    imports.Add("rx.Completable");
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForMethodGroupWithLocalDeleteByResourceGroupImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsDeleteByResourceGroup)
                {
                    // Imports needed when collection supports deleteByResourceGroup but it is not inheriting from GrouapbleResourcesImpl
                    // hence not getting free implementation for deleteByResourceGroup methods.
                    //
                    imports.Add("rx.Completable");
                    imports.Add("com.microsoft.rest.ServiceFuture");
                    imports.Add("com.microsoft.rest.ServiceCallback");
                    imports.Add("rx.functions.Func1");
                }
                return imports;
            }
        }

        #region ISupportsGeneralizedView

        public HashSet<string> ImportsForGeneralizedInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsDeleteByResourceGroup)
                {
                    imports.Add("rx.Completable");
                }
                if (this.supportsDeleteByImmediateParent)
                {
                    imports.Add("rx.Completable");
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForGeneralizedImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsDeleteByResourceGroup)
                {
                    imports.Add("rx.Completable");
                }
                if (this.supportsDeleteByImmediateParent)
                {
                    imports.Add("rx.Completable");
                }
                return imports;
            }
        }

        public IEnumerable<string> GeneralizedMethodDecls
        {
            get
            {
                if (this.SupportsDeleteByResourceGroup)
                {
                    FluentMethod method = this.DeleteByResourceGroupMethod;
                    yield return $"Completable {method.Name}Async(String resourceGroupName, String name)";
                }
                if (this.supportsDeleteByImmediateParent)
                {
                    FluentMethod method = this.deleteByImmediateParentMethod;
                    yield return $"Completable {method.Name}Async({method.InnerMethodInvocationParameters})";
                }
            }
        }

        public IEnumerable<string> GeneralizedMethodImpls
        {
            get
            {
                if (this.SupportsDeleteByResourceGroup)
                {
                    yield return this.DeleteByResourceGroupRxAsyncMethodGeneralizedImplementation;
                }
                if (this.supportsDeleteByImmediateParent)
                {
                    yield return this.DeleteByImmediateParentRxAsyncMethodGeneralizedImplementation;
                }
            }
        }

        private string DeleteByResourceGroupRxAsyncMethodGeneralizedImplementation
        {
            get
            {
                if (this.SupportsDeleteByResourceGroup)
                {
                    FluentMethod method = this.DeleteByResourceGroupMethod;
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Completable {method.Name}Async(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    return this.inner().{method.Name}Async(resourceGroupName, name).toCompletable();");
                    methodBuilder.AppendLine($"}}");
                    return methodBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        private string DeleteByImmediateParentRxAsyncMethodGeneralizedImplementation
        {
            get
            {
                if (this.supportsDeleteByImmediateParent)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    FluentMethod method = this.DeleteByImmediateParentMethod;
                    string innerClientName = this.fluentMethodGroup.InnerMethodGroupTypeName;
                    //
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                    methodBuilder.AppendLine("@Override");
                    methodBuilder.AppendLine($"public Completable {method.Name}Async({parameterDecl}) {{");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async({method.InnerMethodInvocationParameters}).toCompletable();");
                    methodBuilder.AppendLine($"}}");
                    //
                    return methodBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        #endregion

        private void Process()
        {
            if (this.isProcessed)
            {
                return;
            }
            else
            {
                this.isProcessed = true;
                this.CheckDeleteByResourceGroupSupport();
                this.CheckDeleteBySubscriptionSupport();
                this.CheckDeleteByImmediateParentSupport();
            }
        }

        private void CheckDeleteByResourceGroupSupport()
        {
            if (this.fluentMethodGroup.Level == 0)
            {
                foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Delete))
                {
                    var armUri = new ARMUri(innerMethod);
                    Segment lastSegment = armUri.LastOrDefault();
                    if (lastSegment != null && lastSegment is ParentSegment)
                    {
                        ParentSegment resourceSegment = (ParentSegment)lastSegment;
                        var requiredParameters = RequiredParametersOfMethod(innerMethod);
                        if (resourceSegment.Name.EqualsIgnoreCase(fluentMethodGroup.LocalNameInPascalCase) && requiredParameters.Count() == 2)
                        {
                            var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));
                            if (resourceGroupSegment != null)
                            {
                                bool hasResourceGroupParam = requiredParameters.Any(p => p.SerializedName.EqualsIgnoreCase(resourceGroupSegment.Parameter.SerializedName));
                                bool hasResourceParm = requiredParameters.Any(p => p.SerializedName.EqualsIgnoreCase(resourceSegment.Parameter.SerializedName));
                                if (hasResourceGroupParam && hasResourceParm)
                                {
                                    this.supportsDeleteByResourceGroup = true;
                                    this.deleteByResourceGroupMethod = new FluentMethod(true, innerMethod, this.fluentMethodGroup);
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsDeleteByResourceGroup = false;
                this.deleteByResourceGroupMethod = null;
            }
        }

        private void CheckDeleteBySubscriptionSupport()
        {
            if (this.fluentMethodGroup.Level == 0)
            {
                foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Delete))
                {
                    var armUri = new ARMUri(innerMethod);
                    Segment lastSegment = armUri.LastOrDefault();
                    if (lastSegment != null && lastSegment is ParentSegment)
                    {
                        ParentSegment resourceSegment = (ParentSegment)lastSegment;
                        var requiredParameters = RequiredParametersOfMethod(innerMethod);
                        if (resourceSegment.Name.EqualsIgnoreCase(fluentMethodGroup.LocalNameInPascalCase) && requiredParameters.Count() == 1)
                        {
                            var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                            if (subscriptionSegment != null)
                            {
                                bool hasResourceParm = requiredParameters.Any(p => p.SerializedName.EqualsIgnoreCase(resourceSegment.Parameter.SerializedName));
                                if (hasResourceParm)
                                {
                                    this.supportsDeleteBySubscription = true;
                                    this.deleteBySubscriptionMethod = new FluentMethod(true, innerMethod, this.fluentMethodGroup);
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsDeleteBySubscription = false;
                this.deleteBySubscriptionMethod = null;
            }
        }

        private void CheckDeleteByImmediateParentSupport()
        {
            if (this.fluentMethodGroup.Level > 0)
            {
                foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Delete))
                {
                    IFluentMethodGroup parentMethodGroup = this.fluentMethodGroup.ParentFluentMethodGroup;
                    if (parentMethodGroup != null)
                    {
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(fluentMethodGroup.LocalNameInPascalCase))
                            {
                                Segment secondLastSegment = armUri.SkipLast(1).LastOrDefault();
                                if (secondLastSegment != null && secondLastSegment is ParentSegment)
                                {
                                    ParentSegment parentSegment = (ParentSegment)secondLastSegment;
                                    if (parentSegment.Name.EqualsIgnoreCase(parentMethodGroup.LocalNameInPascalCase))
                                    {
                                        this.supportsDeleteByImmediateParent = true;
                                        this.deleteByImmediateParentMethod = new FluentMethod(true, innerMethod, this.fluentMethodGroup);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsDeleteByImmediateParent = false;
                this.deleteByImmediateParentMethod = null;
            }
        }

        public IEnumerable<string> DeleteByResourceGroupSyncAsyncImplementation()
        {
            if (this.SupportsDeleteByResourceGroup)
            {
                string methodImpl = this.DeleteByResourceGroupSyncImplementation;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                methodImpl = this.DeleteByResourceGroupRxSyncImplementation;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
                methodImpl = this.DeleteByResourceGroupFutureSyncImplementation;
                if (!string.IsNullOrEmpty(methodImpl))
                {
                    yield return methodImpl;
                }
            }
            else
            {
                yield break;
            }
        }

        public IEnumerable<string> BatchDeleteAyncAndSyncMethodImplementations()
        {
            if (this.SupportsDeleteByResourceGroup)
            {
                // It is understood that if delete by resource group is supported in service then batch delete is also supported in SDK
                //
                FluentMethod method = this.DeleteByResourceGroupMethod;
                //
                StringBuilder methodBuilder = new StringBuilder();
                //
                // BatchDeleteByIdCol async 
                methodBuilder.Clear();
                methodBuilder.AppendLine("@Override");
                methodBuilder.AppendLine($"public Observable<String> deleteByIdsAsync(Collection<String> ids) {{");
                methodBuilder.AppendLine($"    if (ids == null || ids.isEmpty()) {{");
                methodBuilder.AppendLine($"        return Observable.empty();");
                methodBuilder.AppendLine($"    }}");
                methodBuilder.AppendLine($"    Collection<Observable<String>> observables = new ArrayList<>();");
                methodBuilder.AppendLine($"    for (String id : ids) {{");
                methodBuilder.AppendLine($"        final String resourceGroupName = ResourceUtilsCore.groupFromResourceId(id);");
                methodBuilder.AppendLine($"        final String name = ResourceUtilsCore.nameFromResourceId(id);");
                methodBuilder.AppendLine($"        Observable<String> o = RXMapper.map(this.inner().{method.Name}Async(resourceGroupName, name), id);");
                methodBuilder.AppendLine($"        observables.add(o);");
                methodBuilder.AppendLine($"    }}");
                methodBuilder.AppendLine($"    return Observable.mergeDelayError(observables);");
                methodBuilder.AppendLine($"}}");
                yield return methodBuilder.ToString();
                //
                // BatchDeleteByIdVarArgs async
                methodBuilder.Clear();
                methodBuilder.AppendLine("@Override");
                methodBuilder.AppendLine($"public Observable<String> deleteByIdsAsync(String...ids) {{");
                methodBuilder.AppendLine($"    return this.deleteByIdsAsync(new ArrayList<String>(Arrays.asList(ids)));");
                methodBuilder.AppendLine($"}}");
                yield return methodBuilder.ToString();
                //
                // BatchDeleteByIdCol sync
                methodBuilder.Clear();
                methodBuilder.AppendLine("@Override");
                methodBuilder.AppendLine($"public void deleteByIds(Collection<String> ids) {{");
                methodBuilder.AppendLine($"    if (ids != null && !ids.isEmpty()) {{");
                methodBuilder.AppendLine($"        this.deleteByIdsAsync(ids).toBlocking().last();");
                methodBuilder.AppendLine($"    }}");
                methodBuilder.AppendLine($"}}");
                yield return methodBuilder.ToString();
                //
                // BatchDeleteByIdVarArgs sync
                //
                methodBuilder.Clear();
                methodBuilder.AppendLine("@Override");
                methodBuilder.AppendLine($"public void deleteByIds(String...ids) {{");
                methodBuilder.AppendLine($"    this.deleteByIds(new ArrayList<String>(Arrays.asList(ids)));");
                methodBuilder.AppendLine($"}}");
                yield return methodBuilder.ToString();
            }
            else
            {
                yield break;
            }
        }

        public string DeleteByImmediateParentMethodImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                if (this.SupportsDeleteByImmediateParent)
                {
                    FluentMethod method = this.DeleteByImmediateParentMethod;
                    FluentModel returnModel = method.ReturnModel;
                    string innerClientName = this.fluentMethodGroup.InnerMethodGroupTypeName;
                    string parentMethodGroupLocalSingularName = this.fluentMethodGroup.ParentFluentMethodGroup.LocalSingularNameInPascalCase;
                    //
                    string methodName = $"deleteBy{parentMethodGroupLocalSingularName}Async";
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;

                    methodBuilder.AppendLine("@Override");
                    methodBuilder.AppendLine($"public Completable {methodName}({parameterDecl}) {{");
                    methodBuilder.AppendLine($"    {innerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{method.Name}Async({method.InnerMethodInvocationParameters}).toCompletable();");
                    methodBuilder.AppendLine($"}}");
                }
                return methodBuilder.ToString();
            }
        }

        private string DeleteByResourceGroupSyncImplementation
        {
            get
            {
                if (this.SupportsDeleteByResourceGroup)
                {
                    // deleteByResourceGroup 
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public void deleteByResourceGroup(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    this.deleteByResourceGroupAsync(resourceGroupName, name).await();");
                    methodBuilder.AppendLine($"}}");
                    return methodBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }


        private string DeleteByResourceGroupRxSyncImplementation
        {
            get
            {
                if (this.SupportsDeleteByResourceGroup)
                {
                    // deleteByResourceGroupAsync
                    //
                    FluentMethod method = this.DeleteByResourceGroupMethod;
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Completable deleteByResourceGroupAsync(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    return this.inner().{method.Name}Async(resourceGroupName, name).toCompletable();");
                    methodBuilder.AppendLine($"}}");
                    return methodBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        private string DeleteByResourceGroupFutureSyncImplementation
        {
            get
            {
                if (this.SupportsDeleteByResourceGroup)
                {
                    //
                    // deleteByResourceGroupAsync(ServiceFuture) 
                    //
                    FluentMethod method = this.DeleteByResourceGroupMethod;
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    methodBuilder.AppendLine($"@Override");
                    methodBuilder.AppendLine($"public Completable deleteByResourceGroupAsync(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    return this.inner().{method.Name}Async(resourceGroupName, name).toCompletable();");
                    methodBuilder.AppendLine($"}}");
                    return methodBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        private static IEnumerable<ParameterJv> RequiredParametersOfMethod(MethodJvaf method)
        {
            return method.LocalParameters.Where(parameter => parameter.IsRequired && !parameter.IsConstant);
        }
    }
}