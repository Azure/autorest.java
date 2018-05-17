using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ProxyFluentMethodGroup : IFluentMethodGroup
    {
        private readonly IFluentMethodGroup subjectFluentMethodGroup;

        ProxyFluentMethodGroup(IFluentMethodGroup subjectFluentMethodGroup)
        {
            this.subjectFluentMethodGroup = subjectFluentMethodGroup;
            this.generalizedOutputs = new List<GeneralizedOutput>();
            this.generalizedOutputs.AddRange(subjectFluentMethodGroup.GeneralizedOutputs);
        }

        ProxyFluentMethodGroup(IFluentMethodGroup subjectFluentMethodGroup, GeneralizedOutput generalizedOutput) : this(subjectFluentMethodGroup)
        {
            this.subjectFluentMethodGroup = subjectFluentMethodGroup;
            this.generalizedOutputs.Add(generalizedOutput);
            this.generalizedOutputs.AddRange(generalizedOutput.GeneralizedOutputs); // Flatting it for easy processing later
        }

        public FluentMethodGroups FluentMethodGroups
        {
            get
            {
                return this.subjectFluentMethodGroup.FluentMethodGroups;
            }
        }

        public StandardModel StandardFluentModel
        {
            get
            {
                return this.subjectFluentMethodGroup.StandardFluentModel;
            }
        }

        public string JavaInterfaceName
        {
            get
            {
                return Utils.TrimInnerSuffix(this.InnerMethodGroupTypeName);
            }
        }

        public string ManagerName
        {
            get
            {
                return this.subjectFluentMethodGroup.ManagerName;
            }
        }

        public MethodGroupJvaf InnerMethodGroup
        {
            get
            {
                return this.subjectFluentMethodGroup.InnerMethodGroup;
            }
        }

        public string InnerMethodGroupTypeName
        {
            get
            {
                return this.subjectFluentMethodGroup.InnerMethodGroupTypeName;
            }
        }

        public string InnerMethodGroupAccessorName
        {
            get
            {
                return this.subjectFluentMethodGroup.InnerMethodGroupAccessorName;
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                return this.subjectFluentMethodGroup.ImportsForImpl;
            }
        }

        public HashSet<string> ImportsForInterface
        {
            get
            {
                return this.subjectFluentMethodGroup.ImportsForInterface;
            }
        }

        public OtherMethods OtherMethods
        {
            get
            {
                return this.subjectFluentMethodGroup.OtherMethods;
            }
        }

        public ResourceCreateDescription ResourceCreateDescription
        {
            get
            {
                return this.subjectFluentMethodGroup.ResourceCreateDescription;
            }
        }

        public ResourceUpdateDescription ResourceUpdateDescription
        {
            get
            {
                return this.subjectFluentMethodGroup.ResourceUpdateDescription;
            }
        }

        public ResourceGetDescription ResourceGetDescription
        {
            get
            {
                return this.subjectFluentMethodGroup.ResourceGetDescription;
            }
        }

        public ResourceListingDescription ResourceListingDescription
        {
            get
            {
                return this.subjectFluentMethodGroup.ResourceListingDescription;
            }
        }

        public ResourceDeleteDescription ResourceDeleteDescription
        {
            get
            {
                return this.subjectFluentMethodGroup.ResourceDeleteDescription;
            }
        }

        public IReadOnlyList<FluentMethodGroup> ChildFluentMethodGroups
        {
            get
            {
                return EmptyFluentMethodGroupList;
            }
        }

        private List<GeneralizedOutput> generalizedOutputs;
        public IReadOnlyList<GeneralizedOutput> GeneralizedOutputs
        {
            get
            {
                return this.generalizedOutputs;
            }
        }

        private static List<FluentMethodGroup> EmptyFluentMethodGroupList = new List<FluentMethodGroup>();
    }
}
