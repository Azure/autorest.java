// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    public class MaybeType : GenericType
    {
        public MaybeType(IType typeArgument)
            : base("io.reactivex", "Maybe", typeArgument)
        {
        }

        public IType TypeArgument => TypeArguments[0];
    }
}
