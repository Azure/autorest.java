// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    public class FlowableType : GenericType
    {
        public static readonly FlowableType ByteArray = new FlowableType(ArrayType.ByteArray);

        public FlowableType(IType typeArgument)
            : base("io.reactivex", "Flowable", typeArgument)
        {
        }

        public IType TypeArgument => TypeArguments[0];
    }
}
