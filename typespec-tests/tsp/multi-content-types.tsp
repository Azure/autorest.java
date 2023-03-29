import "@typespec/rest";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using Azure.ClientGenerator.Core;

@service({
  title: "MultiContentTypes",
  version: "1.0.0",
})
namespace Cadl.MultiContentTypes;

model Resource {
  @visibility("read")
  id: string;

  @key
  @visibility("read")
  name: string;
}

@route("/single")
interface SingleContentType {
  @doc("response is binary")
  @route("/download/image")
  @get
  downloadImage(): OkResponse & {
    @header("content-type") contentType: "image/png";
    @body data: bytes;
  };

  @doc("request is binary")
  @route("/upload/image")
  @post
  uploadImage(@body data: bytes, @header contentType: "image/png"): void;
}

@route("multiple")
interface MultipleContentTypes {
  @doc("one data type maps to multiple content types")
  @route("upload/single-body-type")
  @post
  uploadBytes(@body data: bytes, @header contentType: "application/octet-stream" | "image/jpeg" | "image/png"): void;
}

// @doc("multiple data types map to multiple content types")
// @route("upload/multi-body-types")
// @post
// @internal
// @convenientAPI(false)
// op upload(
//   @body data: string | bytes | Resource,
//   @header contentType: "text/plain" | "application/json" | "application/octet-stream" | "image/jpeg" | "image/png"
// ): void;

// @doc("multiple data types map to multiple content types")
// @route("upload/multi-body-types")
// @post
// @overload(upload)
// op uploadString(@body data: string, @header contentType: "text/plain"): void;

// @doc("multiple data types map to multiple content types using `@overload`")
// @route("upload/multi-body-types")
// @post
// @overload(upload)
// op uploadImages(@body data: bytes, @header contentType: "image/jpeg" | "image/png"): void;
