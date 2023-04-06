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
  title: "SyncStack",
  version: "1.0.0",
})
@useDependency(Azure.Core.Versions.v1_0_Preview_2)
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

model ExportParams {
  @query
  projectFileVersion: string;
}

model ExportedResource {
  id: string;
  resourceUri: string;
}

interface ResourceOperations
  extends Azure.Core.ResourceOperations<NoRepeatableRequests & NoConditionalRequests & NoClientRequestId> {}

@route("/syncstack")
interface SyncStackOp {
  // get
  get is ResourceOperations.ResourceRead<Resource>;
  
  // pageable
  @convenientAPI(true)
  list is ResourceOperations.ResourceList<Resource>;

  // lro
  @pollingOperation(ResourceOperations.GetResourceOperationStatus<Resource, never>)
  createOrUpdate is ResourceOperations.ResourceCreateOrUpdate<Resource>;

  @pollingOperation(ResourceOperations.GetResourceOperationStatus<Resource, never>)
  delete is ResourceOperations.LongRunningResourceDelete<Resource>;

  @pollingOperation(ResourceOperations.GetResourceOperationStatus<Resource, ExportedResource>)
  export is ResourceOperations.LongRunningResourceAction<Resource, ExportParams, ExportedResource>;
}