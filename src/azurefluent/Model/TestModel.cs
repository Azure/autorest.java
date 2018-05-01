// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class TestModel
    {
        private readonly CodeModelJvaf codeModel;
        private string moduleName;

        public TestModel(CodeModelJvaf codeModel)
        {
            this.codeModel = codeModel;
        }

        public string ClassName
        {
            get
            {
                if (moduleName == null)
                {
                    FluentConfig fluentConfig = FluentConfig.Create();
                    this.moduleName = fluentConfig.ModuleName ?? this.codeModel.ServiceName;
                }
                return this.moduleName;
            }
        }

        public string ManagerName
        {
            get
            {
                return $"{this.moduleName}Manager";
            }
        }

        public string ManagerVariableName
        {
            get
            {
                return $"{this.ManagerName.ToCamelCase()}";
            }
        }
    }
}
