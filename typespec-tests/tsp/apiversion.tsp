import "@typespec/rest";

using TypeSpec.Http;

@service({
  title: "ApiVersion",
  version: "1.0.0",
})
@server(
  "{endpoint}/{ApiVersion}",
  "Api Version",
  {
    endpoint: string,

    @doc("Api Version")
    @path
    ApiVersion: APIVersions,
  }
)
namespace Cadl.ApiVersion;

enum APIVersions {
  v1_0: "v1.0",
}

@route("/apiversion")
interface ApiVersionOp {
  read(): string;
}
