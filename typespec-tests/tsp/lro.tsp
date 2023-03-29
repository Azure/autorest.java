import "@typespec/rest";
import "@azure-tools/typespec-azure-core";

using TypeSpec.Http;
using TypeSpec.Rest;
using TypeSpec.Versioning;
using Azure.Core;
using Azure.Core.Foundations;
using Azure.Core.Traits;

@service({
  title: "LongRunning",
  version: "1.0.0",
})
@useDependency(Azure.Core.Versions.v1_0_Preview_2)
namespace Cadl.LongRunning;

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

@route("/long-running")
interface LongRunningOp {
  createOrUpdate is ResourceOperations.ResourceCreateOrUpdate<Resource>;

  @pollingOperation(ResourceOperations.GetResourceOperationStatus<Resource, never>)
  createOrReplace is ResourceOperations.LongRunningResourceCreateOrReplace<Resource>;

  get is ResourceOperations.ResourceRead<Resource>;

  @pollingOperation(ResourceOperations.GetResourceOperationStatus<Resource, never>)
  delete is ResourceOperations.LongRunningResourceDelete<Resource>;

  @pollingOperation(ResourceOperations.GetResourceOperationStatus<Resource, ExportedResource>)
  export is ResourceOperations.LongRunningResourceAction<Resource, ExportParams, ExportedResource>;
}
