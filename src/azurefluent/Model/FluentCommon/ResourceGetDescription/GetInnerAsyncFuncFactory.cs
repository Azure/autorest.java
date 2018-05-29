// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Implementation of IGetInnerAsyncFuncFactory contract.
    /// </summary>
    public class GetInnerAsyncFuncFactory : IGetInnerAsyncFuncFactory
    {
        /// <summary>
        /// Describes how to retrieve the inner standard model.
        /// </summary>
        private readonly ResourceGetDescription resourceGetDescription;

        public GetInnerAsyncFuncFactory(ResourceGetDescription resourceGetDescription)
        {
            this.resourceGetDescription = resourceGetDescription;
        }

        public bool IsGetInnerSupported
        {
            get
            {
                return GetFromResourceGroupAsyncFunc.IsGetInnerSupported 
                    || GetFromParentAsyncFunc.IsGetInnerSupported;
            }
        }

        private IGetInnerAsyncFunc innerAsyncFuncInRG;
        public IGetInnerAsyncFunc GetFromResourceGroupAsyncFunc
        {
            get
            {
                if (this.innerAsyncFuncInRG == null)
                {
                    this.innerAsyncFuncInRG = new GetInnerFromResourceGroupAsyncFunc(this.resourceGetDescription);
                }
                return this.innerAsyncFuncInRG;
            }
        }

        private IGetInnerAsyncFunc innerAsyncFuncInParent;
        public IGetInnerAsyncFunc GetFromParentAsyncFunc
        {
            get
            {
                if (this.innerAsyncFuncInParent == null)
                {
                    this.innerAsyncFuncInParent = new GetInnerFromParentAsyncFunc(this.resourceGetDescription);
                }
                return this.innerAsyncFuncInParent;
            }
        }
    }
}
