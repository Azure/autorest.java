import "@typespec/rest";
import "@azure-tools/typespec-azure-core";

using TypeSpec.Http;
using Azure.Core;
using Azure.Core.Foundations;

@summary("summary of Naming")
@doc("description of Naming")
@service({
  title: "Naming",
  version: "1.0.0",
})
namespace Cadl.Naming;

@summary("summary of Response")
@doc("description of Response")
@friendlyName("DataResponse")
model Response {
  @summary("summary of name property")
  @doc("description of name property")
  name: string;

  @summary("summary of data property")
  @doc("description of data property")
  data: DataModel;

  @summary("summary of type property")
  @doc("description of type property")
  @friendlyName("dataType")
  type: TypesModel;

  @summary("summary of status property")
  @doc("description of status property")
  status: StatusModel;
}

@summary("summary of Data")
@doc("description of Data")
@friendlyName("BinaryData")
model DataModel {
  @summary("summary of data property")
  @doc("description of data property")
  data: bytes;
}

#suppress "@azure-tools/typespec-azure-core/use-extensible-enum" "For testing"
@summary("summary of Types")
@doc("description of Types")
@fixed
enum TypesModel {
  Blob,
  File,
}

@summary("summary of Statuses")
@doc("description of Statuses")
@friendlyName("DataStatus")
enum StatusModel {
  Running,
  Completed,
  Failed,
}

@summary("summary of Request")
@doc("description of Request")
@friendlyName("DataRequest")
model Request {
  @summary("summary of name query parameter")
  @doc("description of name query parameter")
  @query
  name: string;

  @header
  @summary("summary of etag header parameter")
  @doc("description of etag header parameter")
  etag?: string;

  parameters?: {
    type: "Type1" | "Type2";
  };
}

@summary("summary of naming route")
@doc("description of naming route")
@route("/naming")
interface NamingOp {
  @summary("summary of POST op")
  @doc("description of POST op")
  @post
  post(...Request): Response | ErrorResponse;

  getAnonymouse(): {
    name: string;
  };
}
