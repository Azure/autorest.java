// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    public class OperationStatusType : GenericType
    {
        public OperationStatusType(IType typeArgument)
            : base("com.microsoft.azure.v2", "OperationStatus", typeArgument)
        {
        }

        public IType TypeArgument => TypeArguments[0];
    }
}
