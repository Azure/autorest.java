import "@typespec/rest";
import "@azure-tools/typespec-azure-core";

using TypeSpec.Http;
using Azure.Core;

@service({
  title: "LiteralService",
  version: "1.0.0",
})
namespace Cadl.LiteralService;

model Model {
  literal: "literal";
  optionalLiteral?: "optionalLiteral";
}

alias Request = {
  @query
  literalParam: "literal";

  @query
  optionalLiteralParam?: "optionalLiteral";
};

@route("/literal")
interface LiteralOp {
  @put
  @route("/put")
  put(...Request, ...Model): Model;
}
