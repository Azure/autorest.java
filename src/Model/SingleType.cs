// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    public class SingleType : GenericType
    {
        public SingleType(IType typeArgument)
            : base("io.reactivex", "Single", typeArgument)
        {
        }

        public IType TypeArgument => TypeArguments[0];
    }
}
