import "@typespec/rest";
import "@azure-tools/typespec-azure-core";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using TypeSpec.Rest;
using Azure.Core.Foundations;
using Azure.ClientGenerator.Core;

@service({
  title: "Patch",
  version: "1.0.0",
})
namespace Cadl.Patch;

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

@route("/patch")
interface Patch {
  // convenience methods will not be generated for patch operation for now.
  @patch
  createOrUpdate(
    @header("content-type") contentType: "application/merge-patch+json",
    @path name: string,
    @body body: Resource
  ): Resource | ErrorResponse;
}
