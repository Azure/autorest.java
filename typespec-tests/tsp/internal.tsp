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
@access(Access.public, "client")
model Request {
  property: RequestInner;
}

model RequestInner {
  name: string;
}

// ResponseInternal and ResponseInternalInner will be generated in implementation.models package
model ResponseInternal {
  property: ResponseInternalInner;
}

@access(Access.public, "python")
model ResponseInternalInner {
  name: string;
}

// Response will be generated as output-only
@access(Access.public)
model Response {
  name: string;
}

// StandAloneData will be generated as output
@access(Access.public)
@usage(Usage.output, "java")
model StandAloneData {
  property: StandAloneDataInner;
}

// StandAloneDataInner will be generated as intput + output
@usage(Usage.input | Usage.output, "client")
model StandAloneDataInner {
  name: string;
}

// StandAloneUnion will be generated in implementation.models package
@access(Access.internal)
model StandAloneUnion {
  data: url | bytes;
}

@route("/internal")
interface InternalOp {
  // test Request with Access.public
  @access(Access.public, "python")
  @access(Access.internal, "client")
  @post
  postInternal(...Request): ResponseInternal;

  // test Response with Access.public
  @get
  @access(Access.internal, "java")
  getInternal(): Response;
}
