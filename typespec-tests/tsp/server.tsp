import "@typespec/rest";
import "@typespec/versioning";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using TypeSpec.Versioning;

@service({
  title: "HttpbinServer",
  version: "1.0.0",
})
@server(
  "https://{domain}.{tld}",
  "Httpbin endpoint",
  {
    @doc("second-level domain, use httpbin")
    domain?: string = "httpbin",

    @doc("top-level domain, use org")
    tld?: string = "org",
  }
)
namespace Cadl.Server {
  @Azure.ClientGenerator.Core.client({
    name: "HttpbinClient",
    service: Cadl.Server,
  })
  @route("/status/{code}")
  interface ServerOp {
    status(@path code: int32): OkResponse | NoContentResponse;
  }
}

@service({
  title: "ContosoServer",
})
@versioned(Cadl.ContosoServer.APIVersions)
@server(
  "{Endpoint}/contoso/{ApiVersion}",
  "Service endpoint",
  {
    @doc("Service endpoint")
    Endpoint: url,

    @doc("Api Version")
    @path
    ApiVersion: APIVersions,
  }
)
namespace Cadl.ContosoServer {
  enum APIVersions {
    v1: "v1",
  }

  @Azure.ClientGenerator.Core.client({
    name: "ContosoClient",
    service: Cadl.ContosoServer,
  })
  @route("/contoso/{group}")
  interface ServerOp {
    get(@path group: url): OkResponse | NoContentResponse;
  }
}
