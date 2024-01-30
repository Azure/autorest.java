import "@typespec/rest";
import "@typespec/versioning";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using TypeSpec.Versioning;
using Azure.ClientGenerator.Core;

@service({
  title: "Flatten",
})
@server(
  "{endpoint}/openai",
  "Flatten",
  {
    endpoint: string,
  }
)
@versioned(ServiceApiVersions)
namespace Cadl.Flatten;

enum ServiceApiVersions {
  v2022_06_01_preview: "2022-06-01-preview",
}

@doc("The ApiVersion query parameter.")
model ApiVersionParameter {
  @query("api-version")
  @minLength(1)
  @doc("The API version to use for this operation.")
  apiVersion: string;
}

alias Request = {
  user?: User;
  input: string;
};

alias LongRequest = {
  user?: User;
  input: string;
  dataInt: int32;
  dataIntOptional?: int32;
  dataLong?: int64;

  @clientName("dataFloat")
  @encodedName("application/json", "data_float")
  float?: float64;
};

model User {
  user: string;
}

@client({
  service: Cadl.Flatten,
  name: "FlattenClient",
})
@route("/flatten")
interface FlattenOp {
  @route("send")
  @post
  send(@query id: string, ...Request, ...ApiVersionParameter): OkResponse;

  @route("send-projected-name")
  @post
  sendProjectedName(
    @query id: string,

    @encodedName("application/json", "file_id")
    @projectedName("client", "fileIdentifier")
    fileId: string,
  ): OkResponse;

  @route("send-long")
  @post
  sendLong(
    @query id: string,
    @query filter?: string,
    ...LongRequest,
    ...ApiVersionParameter,
  ): OkResponse;
}
