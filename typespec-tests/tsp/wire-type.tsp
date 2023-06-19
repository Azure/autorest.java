import "@typespec/rest";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using Azure.ClientGenerator.Core;

@doc("Test for mismatch of wire type and client type on class constructors.")
@service({
  title: "WireType",
  version: "1.0.0",
})
namespace Cadl.WireType;

model SuperClassMismatch {
  @encode(DateTimeKnownEncoding.unixTimestamp, int64)
  unixTimestamp: utcDateTime;
}

model SuperClass {
  dateTime: utcDateTime;
}

model SubClass extends SuperClassMismatch {
  dateTime: utcDateTime;
}

model SubClassMismatch extends SuperClass {
  @encode(DateTimeKnownEncoding.unixTimestamp, int64)
  unixTimestamp: utcDateTime;
}

model SubClassBothMismatch extends SuperClassMismatch {
  @encode(DateTimeKnownEncoding.rfc7231, string)
  dateTimeRfc7231: utcDateTime;
}

@route("/wireType")
interface WireTypeOp {
  @route("/superClassMismatch")
  @put
  superClassMismatch(...SubClass): SubClass;

  @route("/subClassMismatch")
  @put
  subClassMismatch(...SubClassMismatch): SubClassMismatch;

  @route("/bothClassMismatch")
  @put
  bothClassMismatch(...SubClassBothMismatch): SubClassBothMismatch;
}
