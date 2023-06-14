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
  @visibility("read")
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

@route("/etag-headers")
interface EtagHeaders {
  @put
  putWithRequestHeaders is ResourceOperations.ResourceCreateOrReplace<
    Resource,
    RequestHeadersTrait<ConditionalRequestHeaders>
  >;

  @patch
  patchWithMatchHeaders is ResourceOperations.ResourceCreateOrReplace<
    Resource,
    RequestHeadersTrait<{
      @header("If-Match")
      @doc("The request should only proceed if an entity matches this string.")
      ifMatch?: string;

      @header("If-None-Match")
      @doc("The request should only proceed if no entity matches this string.")
      ifNoneMatch?: string;
    }>
  >;
}

@route("/etag-headers-optional-body")
interface EtagHeadersOptionalBody {
  @put
  @doc("etag headers among other optional query/header/body parameters")
  putWithOptionalBody(
    @query format: string,
    @query filter?: string,
    ...ConditionalRequestHeaders,
    @header @encode(DateTimeKnownEncoding.unixTimestamp, int64) timestamp?: utcDateTime,
    @body body?: Resource
  ): Resource;
}
