import "@typespec/rest";
import "@azure-tools/typespec-azure-core";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using TypeSpec.Rest;
using TypeSpec.Versioning;
using Azure.Core;
using Azure.Core.Traits;
using Azure.ClientGenerator.Core;

@service({
  title: "ServiceOperation",
  version: "1.0.0",
})
@useDependency(Azure.Core.Versions.v1_0_Preview_2)
namespace Cadl.ServiceOperation {
  alias ResourceOperations = Azure.Core.ResourceOperations<NoConditionalRequests &
    NoRepeatableRequests &
    NoClientRequestId>;

  @resource("users")
  @doc("Details about a user.")
  model User {
    @key
    @doc("The user's id.")
    @visibility("read")
    id: int32;

    @doc("The user's name.")
    name: string;
  }

  op createOrReplace is ResourceOperations.ResourceCreateOrReplace<User>;

  op list is ResourceOperations.ResourceList<
    User,
    ListQueryParametersTrait<StandardListQueryParameters>
  >;
}

@useDependency(Azure.Core.Versions.v1_0_Preview_2)
namespace Client {
  @doc("Options for list.")
  model ListOptions {
    ...StandardListQueryParameters;
  }

  @client({
    name: "FooClient",
    service: Cadl.ServiceOperation,
  })
  interface FooClient {
    @operationLink(Cadl.ServiceOperation.createOrReplace, "serviceOperation")
    createOrReplace is Cadl.ServiceOperation.createOrReplace;

    #suppress "@azure-tools/typespec-azure-core/use-standard-operations"
    @operationLink(Cadl.ServiceOperation.list, "serviceOperation")
    @doc("list users.")
    list(@doc("Options.") options: ListOptions): Azure.Core.Page<Cadl.ServiceOperation.User>;
  }
}
