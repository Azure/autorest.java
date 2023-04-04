import "@typespec/rest";
import "@azure-tools/typespec-azure-core";

using TypeSpec.Http;
using TypeSpec.Rest;
using Azure.Core;

@service({
  title: "SyncStack",
  version: "1.0.0",
})
namespace Cadl.SyncStack;

@resource("resources")
model Resource {
  @visibility("read")
  id: string;

  @key
  @visibility("read")
  name: string;
  type: string;
}

@route("/syncstack")
interface SyncStackOp {
  // get
  get is ResourceOperations.ResourceRead<Resource>;
  // pageable
  list is ResourceOperations.ResourceList<Resource>;
  // lro
  createOrUpdate is ResourceOperations.ResourceCreateOrUpdate<Resource>;
}