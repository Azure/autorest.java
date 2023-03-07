import "@typespec/rest";
import "@azure-tools/typespec-azure-core";

using TypeSpec.Http;
using TypeSpec.Rest;
using TypeSpec.Versioning;
using Azure.Core;
using Azure.Core.Traits;

@service({
  title: "SpecialHeaders",
  version: "1.0.0",
})
@useDependency(Azure.Core.Versions.v1_0_Preview_2)
namespace Cadl.SpecialHeaders;

@resource("resources")
model Resource {
  @visibility("read")
  id: string;

  @key
  @visibility("create", "update")
  name: string;
  description?: string;
  type: string;
}

interface ResourceOperations
  extends Azure.Core.ResourceOperations<SupportsRepeatableRequests & NoConditionalRequests & NoClientRequestId> {}

@route("/special-headers")
interface SpecialHeadersOp {
  @get
  get is ResourceOperations.ResourceRead<Resource>;

  @put
  @doc("Send a put request with header Repeatability-Request-ID and Repeatability-First-Sent")
  put is ResourceOperations.ResourceCreateOrReplace<Resource>;

  @post
  @doc("Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent")
  post is ResourceOperations.ResourceAction<Resource, {}, Resource>;

  @doc("Send a LRO request with header Repeatability-Request-ID and Repeatability-First-Sent")
  createLro is ResourceOperations.LongRunningResourceCreateOrUpdate<Resource>;
}
