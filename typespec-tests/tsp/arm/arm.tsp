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
@useDependency(Azure.Core.Versions.v1_0_Preview_1)
namespace Cadl.ArmResource;

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

  @visibility("read")
  @doc("ChildResources References List")
  childResources?: ResourceId[];

  @visibility("read")
  @doc("The status of the last operation.")
  provisioningState?: ProvisioningState;
}

#suppress "@azure-tools/typespec-azure-core/documentation-required" "MUST fix in next update"
@lroStatus
enum ProvisioningState {
  Provisioning,
  Updating,
  Deleting,
  Accepted,
  Succeeded,
  Failed,
  Canceled,
}

@doc("Resource ID definition used by parent to reference child resources.")
model ResourceId {
  @doc("Resource ID of child resource.")
  id: string;
}

//----------------------- Paths -----------------------
@armResourceOperations
interface ChildResourcesInterface
  extends ResourceInstanceOperations<ChildResource, ChildResourceProperties>,
    ResourceListByParent<ChildResource> {}

@armResourceOperations
interface TopLevelArmResourceInterface
  extends TrackedResourceOperations<
      TopLevelArmResource,
      TopLevelArmResourceProperties
    > {}

interface Operations extends Azure.ResourceManager.Operations {}
