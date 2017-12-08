using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using AutoRest.Core.Utilities;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using System;
using AutoRest.Java.DanModel;

namespace AutoRest.Java.Model
{
    public class ResponseJv : Response
    {
        protected readonly List<string> _implImports = new List<string>();
        private readonly Lazy<string> returnValueWireType;

        public ResponseJv()
        {
            returnValueWireType = createReturnValueWireTypeLazy();
        }

        public ResponseJv(IModelTypeJv body, IModelTypeJv headers)
            : base(body, headers)
        {
            returnValueWireType = createReturnValueWireTypeLazy();
        }

        private Lazy<string> createReturnValueWireTypeLazy()
        {
            return new Lazy<string>(() =>
            {
                string returnValueWireType = null;

                IModelType body = Body;
                if (body != null)
                {
                    Stack<IModelType> typeStack = new Stack<IModelType>();
                    typeStack.Push(body);
                    while (typeStack.Any())
                    {
                        IModelType currentType = typeStack.Pop();
                        if (currentType is SequenceType currentSequenceType)
                        {
                            typeStack.Push(currentSequenceType.ElementType);
                        }
                        else if (currentType is DictionaryType currentDictionaryType)
                        {
                            typeStack.Push(currentDictionaryType.ValueType);
                        }
                        else if (currentType is PrimaryType currentPrimaryType)
                        {
                            string currentPrimaryTypeName = DanCodeGenerator.GetIModelTypeFixedName(currentPrimaryType);
                            if (currentPrimaryTypeName.EqualsIgnoreCase("Base64Url") ||
                                currentPrimaryTypeName.EqualsIgnoreCase("DateTimeRfc1123") ||
                                currentPrimaryTypeName.EqualsIgnoreCase("UnixTime"))
                            {
                                returnValueWireType = currentPrimaryTypeName;
                                break;
                            }
                        }
                    }
                }

                return returnValueWireType;
            });
        }

        #region types

        [JsonIgnore]
        public bool NeedsConversion
        {
            get
            {
                return 
                    ((BodyWireType == null ? BodyClientType != null : !BodyWireType.StructurallyEquals(BodyClientType)) && DanCodeGenerator.GetIModelTypeName(BodyClientType) != "void") ||
                    (HeaderWireType == null ? HeaderClientType != null : !HeaderWireType.StructurallyEquals(HeaderClientType));
            }
        }

        [JsonIgnore]
        public virtual IModelType BodyClientType
        {
            get
            {
                return DanCodeGenerator.GetIModelTypeResponseVariant(BodyWireType);
            }
        }

        private IModelType _bodyWireType;

        [JsonIgnore]
        public IModelType BodyWireType
        {
            get
            {
                if (_bodyWireType == null)
                {
                    if (Body == null)
                    {
                        _bodyWireType = new PrimaryTypeJv(KnownPrimaryType.None);
                    }
                    else
                    {
                        _bodyWireType = Body;
                    }
                }
                return _bodyWireType;
            }
        }

        [JsonIgnore]
        public IModelTypeJv HeaderClientType
        {
            get
            {
                if (Headers == null)
                {
                    return null;
                }
                else
                {
                    return (IModelTypeJv)DanCodeGenerator.GetIModelTypeResponseVariant(HeaderWireType);
                }
            }
        }

        [JsonIgnore]
        public IModelTypeJv HeaderWireType
        {
            get
            {
                if (Headers == null)
                {
                    return null;
                }
                return (IModelTypeJv)Headers;
            }
        }

        #endregion

        #region template strings

        [JsonIgnore]
        [Obsolete]
        public string ClientResponseTypeString => GenericBodyClientTypeString;

        [JsonIgnore]
        public virtual string ClientCallbackTypeString
        {
            get
            {
                return GenericBodyClientTypeString;
            }
        }

        [JsonIgnore]
        public virtual string GenericBodyClientTypeString
        {
            get
            {
                IModelType respvariant = DanCodeGenerator.GetIModelTypeResponseVariant(BodyWireType);
                if ((respvariant as PrimaryTypeJv)?.Nullable != false)
                {
                    return DanCodeGenerator.GetIModelTypeName(respvariant);
                }
                return DanCodeGenerator.GetIModelTypeName(BodyWireType);
            }
        }

        // The virtual properties below seem redundant in vanilla, but they are needed to allow fine-grained distinctions when generating paging methods in Azure.

        /// <summary>
        /// The generic type argument to a method overload with a ServiceFuture return type.
        /// </summary>
        [JsonIgnore]
        public virtual string ServiceFutureGenericParameterString => GenericBodyClientTypeString;

        /// <summary>
        /// The generic type argument to a method overload with an Observable or Single return type.
        /// </summary>
        [JsonIgnore]
        public virtual string ServiceResponseGenericParameterString => GenericBodyClientTypeString;

        /// <summary>
        /// The generic type argument to a service interface method, which uses concrete types for serialization.
        /// </summary>
        [JsonIgnore]
        public virtual string ServiceResponseConcreteParameterString => GenericBodyClientTypeString;

        [JsonIgnore]
        public virtual string GenericHeaderClientTypeString => DanCodeGenerator.GetIModelTypeName(DanCodeGenerator.GetIModelTypeResponseVariant(HeaderClientType));

        [JsonIgnore]
        public virtual string GenericBodyWireTypeString => DanCodeGenerator.GetIModelTypeName(BodyWireType);

        [JsonIgnore]
        public virtual string GenericHeaderWireTypeString => DanCodeGenerator.GetIModelTypeName(HeaderWireType);

        [JsonIgnore]
        public virtual string SequenceElementTypeString
        {
            get
            {
                var sequenceType = Body as SequenceTypeJv;
                return sequenceType != null ? DanCodeGenerator.GetIModelTypeName(sequenceType.ElementType) : "Void";
            }
        }

        [JsonIgnore]
        public virtual string ReturnValueWireType
        {
            get
            {
                return returnValueWireType.Value;
            }
        }

        #endregion

        [JsonIgnore]
        public IEnumerable<string> InterfaceImports
        {
            get
            {
                return DanCodeGenerator.GetIModelTypeImports(BodyClientType).Concat(DanCodeGenerator.GetIModelTypeImports(HeaderClientType));
            }
        }

        [JsonIgnore]
        public IEnumerable<string> ImplImports
        {
            get
            {
                var imports = new List<string>(InterfaceImports);
                imports.AddRange(DanCodeGenerator.GetIModelTypeImports(BodyWireType));
                imports.AddRange(DanCodeGenerator.GetIModelTypeImports(HeaderWireType));

                if (ReturnValueWireType != null)
                {
                    imports.Add("com.microsoft.rest.v2.annotations.ReturnValueWireType");
                    imports.Add("com.microsoft.rest.v2." + ReturnValueWireType);
                }

                if (this.NeedsConversion)
                {
                    if (Body is SequenceType || Headers is SequenceType)
                    {
                        imports.Add("java.util.ArrayList");
                    }
                    else if (Body is DictionaryType || Headers is DictionaryType)
                    {
                        imports.Add("java.util.HashMap");
                    }
                }
                return imports;
            }
        }
    }
}
