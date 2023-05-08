import "@typespec/rest";
import "@azure-tools/typespec-azure-core";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using TypeSpec.Rest;
using TypeSpec.Versioning;
using Azure.Core;
using Azure.Core.Traits;
using Azure.ClientGenerator.Core;

@service({
  title: "Paged",
  version: "1.0.0",
})
@useDependency(Azure.Core.Versions.v1_0_Preview_2)
namespace Cadl.Paged;

@resource("resources")
model Resource {
  @visibility("read")
  id: string;

  @key
  @visibility("read")
  name: string;
  type: string;
}

interface ResourceOperations
  extends Azure.Core.ResourceOperations<NoRepeatableRequests & NoConditionalRequests & NoClientRequestId> {}

@route("/paged")
interface PagedOp {
  list is ResourceOperations.ResourceList<Resource>;
}
