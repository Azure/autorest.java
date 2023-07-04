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

// ResponseInternal and ResponseInternalInner will not be generated
model ResponseInternal {
  property: ResponseInternalInner;
}

model ResponseInternalInner {
  name: string;
}

// Response will be generated as output-only
@include
model Response {
  name: string;
}

// StandAloneData and StandAloneDataInner will be generated
@include
model StandAloneData {
  property: StandAloneDataInner;
}

model StandAloneDataInner {
  name: string;
}

@route("/internal")
interface InternalOp {
  // test Request with @include
  @internal
  @post
  postInternal(...Request): ResponseInternal;

  // test Response with @include
  @get
  @internal
  getInternal(): Response;
}
