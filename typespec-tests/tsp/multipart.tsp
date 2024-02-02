import "@typespec/http";

using TypeSpec.Http;

@service({
  title: "Multipart",
})
namespace Cadl.Multipart;

enum ImageType {
  JPEG,
  PNG,
}

model Size {
  width: int32;
  height: int32;
}

model FormData {
  name: string;
  resolution: int32;
  type: ImageType;
  size: Size;
  image: bytes;
  file?: bytes[];
}

@route("/upload/images/{name}")
@post
op upload(
  @path name: string,
  @query compress?: boolean,
  @header contentType: "multipart/form-data",
  @body data: FormData,
): OkResponse;

@route("/upload/files/{name}")
@post
op uploadFile(
  @path name: string,
  @header contentType: "multipart/form-data",
  file: bytes,
): OkResponse;
