// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.
// 

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Extensions;

namespace AutoRest.Java
{
    public class TransformerJv : CodeModelTransformer<CodeModel>
    {
        public override CodeModel TransformCodeModel(CodeModel codeModel)
        {
            // todo: these should be turned into individual transformers
            SwaggerExtensions.NormalizeClientModel(codeModel);

            return codeModel;
        }
    }
}