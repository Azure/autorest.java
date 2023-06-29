import "@typespec/rest";
import "@typespec/versioning";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using Azure.ClientGenerator.Core;

@service({
  title: "Internal",
  version: "1.0.0",
})
namespace Cadl.Internal;

// Request and RequestInner will be generated
@include
model Request {
  property: RequestInner;
}

model RequestInner {
  name: string;
}

// Response and ResponseInner will not be generated
model Response {
  property: ResponseInner;
}

model ResponseInner {
  name: string;
}

@route("/internal")
interface InternalOp {
  @internal
  @get
  getInternal(...Request): Response;
}
