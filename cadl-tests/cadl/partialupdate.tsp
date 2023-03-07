import "@typespec/rest";

using TypeSpec.Http;

@service({
  title: "PartialUpdate",
  version: "1.0.0",
})
namespace TypeSpec.PartialUpdate;

model PartialUpdateModel {
  boolean: boolean;
  string: string;
  bytes: bytes;
}

@route("/partialupdate")
interface PartialUpdateOp {
  read(): {
    @body body: PartialUpdateModel;
  };
}
