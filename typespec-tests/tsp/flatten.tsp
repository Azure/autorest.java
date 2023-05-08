import "@typespec/rest";
import "@typespec/versioning";

using TypeSpec.Http;
using TypeSpec.Versioning;

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
  data_float?: float64;
};

model User {
  user: string;
}

@route("/flatten")
interface FlattenOp {
  @route("send")
  @post
  send(@query id: string, ...Request, ...ApiVersionParameter): OkResponse;

  @route("send-long")
  @post
  sendLong(@query id: string, @query filter?: string, ...LongRequest, ...ApiVersionParameter): OkResponse;
}
