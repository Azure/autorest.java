import "@typespec/http";

using TypeSpec.Http;

@service({
  title: "Multipart",
  version: "1.0.0",
})
namespace Cadl.Multipart;

model FormData {
  name: string;
  image: bytes;
}

@doc("request is binary")
@route("/upload/images/{name}")
@post
op upload(
  @path name: string,
  @query compress?: boolean,
  @header contentType: "multipart/form-data",
  @body data: FormData,
): void;
