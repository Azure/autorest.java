// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class AncestorsStack
    {
        private readonly FluentMethodGroup fluentMethodGroup;
        private Stack<string> stack;

        public AncestorsStack(FluentMethodGroup fluentMethodGroup)
        {
            this.fluentMethodGroup = fluentMethodGroup;
        }

        public string PopNextAncestorSingularName
        {
            get
            {
                if (this.stack == null)
                {
                    this.stack = new Stack<string>();
                    var current = this.fluentMethodGroup.ParentFluentMethodGroup;
                    while (current != null)
                    {
                        this.stack.Push(current.LocalSingularNameInPascalCase);
                        current = current.ParentFluentMethodGroup;
                    }
                    this.stack = new Stack<string>(this.stack.Reverse());
                }
                //
                if (this.stack.TryPop(out string value))
                {
                    return value;
                }
                else
                {
                    return null;
                }
            }
        }

        public void Reset()
        {
            this.stack = null;
        }
    }
}
