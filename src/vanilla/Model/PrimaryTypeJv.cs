using AutoRest.Core.Model;
using AutoRest.Java.DanModel;
using Newtonsoft.Json;
using System;

namespace AutoRest.Java.Model
{
    public class PrimaryTypeJv : PrimaryType
    {
        public PrimaryTypeJv()
        {
        }

        public PrimaryTypeJv(KnownPrimaryType type)
            : base(type)
        {
        }

        public bool WantNullable { get; internal set; } = true;

        [JsonIgnore]
        public bool Nullable
        {
            get
            {
                if (WantNullable)
                {
                    return true;
                }
                switch (KnownPrimaryType)
                {
                    case KnownPrimaryType.None:
                    case KnownPrimaryType.Boolean:
                    case KnownPrimaryType.Double:
                    case KnownPrimaryType.Int:
                    case KnownPrimaryType.Long:
                    case KnownPrimaryType.UnixTime:
                        return false;
                }
                return true;
            }
        }

        [JsonIgnore]
        public override string DefaultValue
        {
            get
            {
                if (DanCodeGenerator.GetIModelTypeName(this) == "byte[]")
                {
                    return "new byte[0]";
                }
                else if (DanCodeGenerator.GetIModelTypeName(this) == "Byte[]")
                {
                    return "new Byte[0]";
                }
                else if (Nullable)
                {
                    return null;
                }
                else
                {
                    throw new NotSupportedException(DanCodeGenerator.GetIModelTypeName(this) + " does not have default value!");
                }
            }
        }

        [JsonIgnore]
        public virtual string ImplementationName
        {
            get
            {
                switch (KnownPrimaryType)
                {
                    case KnownPrimaryType.None:
                        return WantNullable ? "Void" : "void";
                    case KnownPrimaryType.Base64Url:
                        return "Base64Url";
                    case KnownPrimaryType.Boolean:
                        return WantNullable ? "Boolean" : "boolean";
                    case KnownPrimaryType.ByteArray:
                        return "byte[]";
                    case KnownPrimaryType.Date:
                        return "LocalDate";
                    case KnownPrimaryType.DateTime:
                        return "DateTime";
                    case KnownPrimaryType.DateTimeRfc1123:
                        return "DateTimeRfc1123";
                    case KnownPrimaryType.Double:
                        return WantNullable ? "Double" : "double";
                    case KnownPrimaryType.Decimal:
                        return "BigDecimal";
                    case KnownPrimaryType.Int:
                        return WantNullable ? "Integer" : "int";
                    case KnownPrimaryType.Long:
                        return WantNullable ? "Long" : "long";
                    case KnownPrimaryType.Stream:
                        return "InputStream";
                    case KnownPrimaryType.String:
                        return "String";
                    case KnownPrimaryType.TimeSpan:
                        return "Period";
                    case KnownPrimaryType.UnixTime:
                        return WantNullable ? "Long" : "long";
                    case KnownPrimaryType.Uuid:
                        return "UUID";
                    case KnownPrimaryType.Object:
                        return "Object";
                    case KnownPrimaryType.Credentials:
                        return "ServiceClientCredentials";
                }
                throw new NotImplementedException($"Primary type {KnownPrimaryType} is not implemented in {GetType().Name}");
            }
        }
    }
}
