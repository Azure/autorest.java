// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing get description of standard model (of a method group) under subscription scope.
    /// </summary>
    public class GetByParameterizedParentDescription
    {
        protected readonly string package = Settings.Instance.Namespace.ToLower();

        protected readonly IFluentMethodGroup FluentMethodGroup;
        private readonly IGetInnerAsyncFuncFactory getInnerAsyncFuncFactory;

        public GetByParameterizedParentDescription(IFluentMethodGroup fluentMethodGroup, IGetInnerAsyncFuncFactory getInnerAsyncFuncFactory)
        {
            this.FluentMethodGroup = fluentMethodGroup;
            this.getInnerAsyncFuncFactory = getInnerAsyncFuncFactory;
        }

        private bool supportsGet;
        public bool SupportsGet
        {
            get
            {
                this.Process();
                return this.supportsGet;
            }
        }

        private StandardFluentMethod getMethod;
        public StandardFluentMethod GetMethod
        {
            get
            {
                this.Process();
                return this.getMethod;
            }
        }

        public HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                // There is no standard fluent core interface that needs to be extend
                // inorder to support getting a resource in subscription scope.
                //
                return Utils.EmptyStringSet;
            }
        }

        public HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                return Utils.EmptyStringSet;
            }
        }

        public HashSet<string> ImportsForMethodGroupImpl
        {
            get
            {
                return Utils.EmptyStringSet;
            }
        }

        public HashSet<string> ImportsForGeneralizedInterface
        {
            get
            {
                return Utils.EmptyStringSet;
            }
        }

        public HashSet<string> ImportsForGeneralizedImpl
        {
            get
            {
                return Utils.EmptyStringSet;
            }
        }

        public string GeneralizedMethodDecl
        {
            get
            {
                return string.Empty;
            }
        }

        public string GeneralizedMethodImpl
        {
            get
            {
                return string.Empty;
            }
        }

        public HashSet<string> ImportsForMethodGroupWithLocalGetMethodImpl
        {
            get
            {
                return Utils.EmptyStringSet;
            }
        }

        public IEnumerable<string> GetSyncAsyncMethodImplementations
        {
            get
            {
                yield break;
            }
        }

        private bool isProcessed;
        private void Process()
        {
            if (this.isProcessed)
            {
                return;
            }
            else
            {
                this.isProcessed = true;
                this.CheckGetByParameterizedParentSupport();
            }
        }

        private void CheckGetByParameterizedParentSupport()
        {
            if (this.FluentMethodGroup.Level == 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to map response to standard model T where T is class/interface type
                        // it need to be composite type. If the return type is primitive type (e.g. void), sequence type
                        // dict type then mapping cannot be done. Skip get methods returning such types they will be appear
                        // as other methods
                        continue;
                    }
                    else
                    {
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            var requiredParameters = Utils.RequiredParametersOfMethod(innerMethod);
                            if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase))
                            {
                                var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                                var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));

                                if (subscriptionSegment == null && resourceGroupSegment == null)
                                {
                                    if (StandardFluentMethod.CanWrap(innerMethod))
                                    {
                                        this.supportsGet = true;
                                        this.getMethod = new StandardFluentMethod(innerMethod, this.FluentMethodGroup);
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
                this.supportsGet = false;
                this.getMethod = null;
            }
        }

        private string GetSyncImplementation
        {
            get
            {
                return string.Empty;
            }
        }

        private string GetFutureAsyncMethodImplementation
        {
            get
            {
                return string.Empty;
            }
        }

        private string GetRxAsyncMethodImplementation
        {
            get
            {
                return string.Empty;
            }
        }
    }
}
