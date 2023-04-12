import "@typespec/rest";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using Azure.ClientGenerator.Core;

@service({
  title: "Builtin",
  version: "1.0.0",
})
namespace Cadl.Builtin;

model Builtin {
  formatString: FormatString;
  boolean: boolean;
  string: string;
  bytes: bytes;
  int: int32;
  safeint: safeint;
  long: int64;
  float: float32;
  double: float64;
  duration: duration;
  date: plainDate;
  dateTime: utcDateTime;
  stringList: string[];
  bytesDict: Record<bytes>;
  url: url;
  nullableFloatDict: Record<float64 | null>;
}

model FormatString {
  @format("byte")
  base64Encoded: string;

  @format("binary")
  binary: string;

  @format("date-time")
  dateTime: string;
  dateTimeRfc1123: DateTimeRfc1123;

  @format("password")
  password: string;

  @format("url")
  uri: string;

  @knownValues(KnownValues)
  extensibleEnum: string;
  extensibleEnumScalar: KnownValuesScalar;
}

@summary("summary of RFC1123 date-time")
@doc("description of RFC1123 date-time")
@friendlyName("DateTime")
@format("date-time-rfc1123")
scalar DateTimeRfc1123 extends string;

enum KnownValues {
  Value1,
  Value2,
}

@knownValues(KnownValues)
scalar KnownValuesScalar extends string;

model Request {
  @header("x-ms-date")
  dateTime?: utcDateTime;

  @query
  filter?: string;

  @query("query")
  queryParam: string;

  @query("query-encoded")
  queryParamEncoded: url;

  @query("query-opt")
  queryParamOptional?: string;

  @query("query-opt-encoded")
  queryParamOptionalEncoded?: url;
}

@route("/builtin")
interface BuiltinOp {
  read(...Request): {
    @body body: Builtin;
  };

  @convenientAPI(false)
  write(@body body: Builtin): OkResponse;
}
