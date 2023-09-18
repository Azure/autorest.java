import "@typespec/http";

using TypeSpec.Http;

@service({
  title: "Multipart",
  version: "1.0.0",
})
namespace Cadl.Multipart;

model Data {
  name: string;
  image: bytes;
}

@doc("request is binary")
@route("/upload/image")
@post
op upload(@header contentType: "multipart/form-data", data: Data): void;
