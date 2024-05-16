import "@typespec/rest";
import "@typespec/versioning";
import "@azure-tools/typespec-azure-core";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using Azure.Core;
using Azure.ClientGenerator.Core;

@service({
  title: "Internal",
})
namespace Cadl.Internal;

// ApiRequest and RequestInner will be generated
@access(Access.public, "client")
model ApiRequest {
  property: string;
  resource?: Resource;
}

// Response will be generated as output-only
@access(Access.public)
model ApiResponse {
  name: string;
  observation?: Observation;
}

// intput+output
model Resource {
  name: string;
  prop0?: string;
}

// output
@discriminator("kind")
model DomainResource extends Resource {
  kind: string;
  prop1?: string;
}

model SubType extends DomainResource {
  kind: "a" | "b";
}

model SubSubType extends SubType {
  kind: "a";
}

// output
model Observation extends DomainResource {
  kind: "Observation";
  prop2?: string;
}

// not public
model ObservationSub extends Observation {
  prop3?: string;
}

@route("/internal")
interface InternalOp {
  // test ApiRequest with Access.public
  @access(Access.public, "python")
  @access(Access.internal, "client")
  @post
  postInternal(...ApiRequest): void;

  // test ApiResponse with Access.public
  @get
  @access(Access.internal, "java")
  getInternal(): ApiResponse;
}
