// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    public class RestResponseType : GenericType
    {
        public RestResponseType(IType headersType, IType bodyType)
            : base("com.microsoft.rest.v2", "RestResponse", headersType, bodyType)
        {
        }

        public IType HeadersType => TypeArguments[0];

        public IType BodyType => TypeArguments[1];
    }
}
