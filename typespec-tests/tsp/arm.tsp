import "@typespec/http";
import "@typespec/rest";
import "@typespec/versioning";
import "@azure-tools/typespec-azure-core";
import "@azure-tools/typespec-azure-resource-manager";

@armProviderNamespace
@service({
  title: "ArmResource",
  version: "2023-11-01",
})
@doc("Arm Resource Provider management API.")
@useDependency(Azure.ResourceManager.Versions.v1_0_Preview_1)
@useDependency(Azure.Core.Versions.v1_0_Preview_2)
namespace Cadl.ArmResourceProvider;

using TypeSpec.Http;
using TypeSpec.Rest;
using TypeSpec.Versioning;
using Azure.Core;
using Azure.ResourceManager;

//----------------------- Child Resources -----------------------
@doc("Subresource of Top Level Arm Resource.")
@parentResource(TopLevelArmResource)
model ChildResource is TrackedResource<ChildResourceProperties> {
  @key("childResourceName")
  @doc("ChildResources")
  @visibility("read")
  @path
  @segment("childResources")
  @pattern("^[A-Za-z0-9]([A-Za-z0-9-_.]{0,62}[A-Za-z0-9])?$")
  name: string;
}

@doc("Child Resource Properties.")
model ChildResourceProperties {
  @visibility("read")
  @doc("Provisioning State of Top Level Arm Resource")
  provisioningState?: ProvisioningState;
}

//----------------------- Top Level Arm Resource -----------------------
@resource("topLevelArmResources")
model TopLevelArmResource is TrackedResource<TopLevelArmResourceProperties> {
  @key("topLevelArmResourceName")
  @path
  @segment("topLevelArmResources")
  @doc("arm resource name for path")
  @pattern("^[A-Za-z0-9]([A-Za-z0-9-_.]{0,62}[A-Za-z0-9])?$")
  name: string;
}

@doc("Top Level Arm Resource Properties.")
model TopLevelArmResourceProperties {
  @doc("Configuration Endpoints.")
  @visibility("read")
  configurationEndpoints?: string[];

  userName: string; // this should be renamed to username
  userNames: string; // this isn't a whole word match, shouldn't be renamed to usernames
  accuserName: string; // this isn't a whole word match, shouldn't be renamed to accusername
  startTimeStamp: utcDateTime; // this should be renamed to startTimestamp

  @visibility("read")
  @doc("The status of the last operation.")
  provisioningState?: ProvisioningState;
}

//----------------------- Custom Template Resource -----------------------
@resource("customTemplateResources")
model CustomTemplateResource is TrackedResource<CustomTemplateResourceProperties> {
  @key("customTemplateResourceName")
  @path
  @segment("customTemplateResources")
  @doc("arm resource name for path")
  @pattern("^[A-Za-z0-9]([A-Za-z0-9-_.]{0,62}[A-Za-z0-9])?$")
  name: string;
}

@doc("Top Level Arm Resource Properties.")
model CustomTemplateResourceProperties {
  @visibility("read")
  @doc("The status of the last operation.")
  provisioningState?: ProvisioningState;
}

@lroStatus
enum ProvisioningState {
  ...ResourceProvisioningState,
  Provisioning,
  Updating,
  Deleting,
  Accepted,
}

model ResponseWithHeader<T> {
  ...OkResponse;
  ...Azure.Core.Foundations.RetryAfterHeader;
  ...Body<T>;
}

//----------------------- Paths -----------------------
@armResourceOperations
interface ChildResourcesInterface
  extends ResourceInstanceOperations<ChildResource, ChildResourceProperties>,
    ResourceListByParent<ChildResource> {
  actionWithoutBody is ArmResourceActionAsync<ChildResource, void, void>;
}

@armResourceOperations
interface TopLevelArmResourceInterface
  extends TrackedResourceOperations<TopLevelArmResource, TopLevelArmResourceProperties> {}

@armResourceOperations
interface CustomTemplateResourceInterface extends ResourceCreate<CustomTemplateResource> {
  update is ArmResourceUpdateSync<CustomTemplateResource>;
}

interface Operations extends Azure.ResourceManager.Operations {}

#suppress "@azure-tools/typespec-azure-resource-manager/arm-resource-operation" "This is a valid template"
@autoRoute
@doc("Update a {name}", TResource)
@armResourceCreateOrUpdate(TResource)
@patch
op ArmResourceUpdateSync<
  TResource extends Azure.ResourceManager.Foundations.ArmResource,
  TBaseParameters = Azure.ResourceManager.Foundations.BaseParameters<TResource>
>(
  ...ResourceInstanceParameters<TResource, TBaseParameters>,

  @doc("Resource create parameters.")
  @body
  resource: TResource,
): ResponseWithHeader<TResource> | ErrorResponse;
