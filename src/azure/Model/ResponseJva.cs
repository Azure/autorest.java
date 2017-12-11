using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.DanModel;
using AutoRest.Java.Model;
using Newtonsoft.Json;
using System.Globalization;

namespace AutoRest.Java.Azure.Model
{
    public class ResponseJva : ResponseJv
    {
        public ResponseJva()
        {
        }

        public ResponseJva(IModelType body, IModelType headers)
            : base(body, headers)
        {
        }

        public MethodJva Parent { get; set; }

        [JsonIgnore]
        public bool IsPagedResponse => Parent.IsPagingNextOperation || Parent.IsPagingOperation;

        [JsonIgnore]
        public override IModelType BodyClientType
        {
            get
            {
                if (base.BodyClientType is SequenceType bodySequenceType && IsPagedResponse)
                {
                    SequenceType result = DependencyInjection.New<SequenceType>();
                    result.ElementType = bodySequenceType.ElementType;
                    DanCodeGenerator.SequenceTypeSetPageImplType(result, DanCodeGenerator.SequenceTypeGetPageImplType(bodySequenceType));
                    DanCodeGenerator.pagedListTypes.Add(result);
                    return result;
                }
                return base.BodyClientType;
            }
        }

        [JsonIgnore]
        public override string GenericBodyClientTypeString
        {
            get
            {
                if (base.BodyClientType is SequenceType bodySequenceType && IsPagedResponse)
                {
                    return string.Format(CultureInfo.InvariantCulture, "PagedList<{0}>", DanCodeGenerator.GetIModelTypeName(bodySequenceType.ElementType));
                }
                return base.GenericBodyClientTypeString;
            }
        }

        [JsonIgnore]
        public override string ServiceFutureGenericParameterString
        {
            get
            {
                if (base.BodyClientType is SequenceType bodySequenceType && IsPagedResponse)
                {
                    return string.Format(CultureInfo.InvariantCulture, "List<{0}>", DanCodeGenerator.GetIModelTypeName(bodySequenceType.ElementType));
                }
                return GenericBodyClientTypeString;
            }
        }

        [JsonIgnore]
        public override string ServiceResponseGenericParameterString
        {
            get
            {
                if (base.BodyClientType is SequenceType bodySequenceType && (IsPagedResponse || Parent.SimulateAsPagingOperation))
                {
                    return string.Format(CultureInfo.InvariantCulture, "Page<{0}>", DanCodeGenerator.GetIModelTypeName(bodySequenceType.ElementType));
                }
                return GenericBodyClientTypeString;
            }
        }

        [JsonIgnore]
        public override string ServiceResponseConcreteParameterString
        {
            get
            {
                if (base.BodyClientType is SequenceType bodySequenceType && (IsPagedResponse || Parent.SimulateAsPagingOperation))
                {
                    return DanCodeGenerator.SequenceTypeGetPageImplType(bodySequenceType) + "<" + DanCodeGenerator.GetIModelTypeName(bodySequenceType.ElementType) + ">";
                }
                return GenericBodyClientTypeString;
            }
        }

        [JsonIgnore]
        public override string GenericBodyWireTypeString
        {
            get
            {
                if (base.BodyClientType is SequenceType sequenceType && (IsPagedResponse || Parent.IsPagingNonPollingOperation))
                {
                    return string.Format(CultureInfo.InvariantCulture, "{0}<{1}>", DanCodeGenerator.SequenceTypeGetPageImplType(sequenceType), DanCodeGenerator.GetIModelTypeName(sequenceType.ElementType));
                }
                return base.GenericBodyWireTypeString;
            }
        }

        [JsonIgnore]
        public override string ClientCallbackTypeString
        {
            get
            {
                if (Body is SequenceType && IsPagedResponse)
                {
                    return DanCodeGenerator.GetIModelTypeName(BodyClientType);
                }
                return base.ClientCallbackTypeString;
            }
        }
    }
}
