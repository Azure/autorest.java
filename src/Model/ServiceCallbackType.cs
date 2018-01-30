// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    public class ServiceCallbackType : GenericType
    {
        public ServiceCallbackType(IType typeArgument)
            : base("com.microsoft.rest.v2", "ServiceCallback", typeArgument)
        {
        }

        public IType TypeArgument => TypeArguments[0];
    }
}
