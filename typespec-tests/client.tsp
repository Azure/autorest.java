import "@azure-tools/typespec-client-generator-core";

import "./main.tsp";

using TypeSpec.Versioning;
using Azure.ResourceManager;
using Azure.ClientGenerator.Core;
using Microsoft.AVS;

@client({
  name: "AvsClient", 
  service: Microsoft.AVS
})
@useDependency(Microsoft.AVS.Versions.v2023_09_01)
namespace Customizations;

@operationGroup
@armResourceOperations
interface Operations extends Microsoft.AVS.Operations {};

@operationGroup
@armResourceOperations
interface PrivateClouds extends Microsoft.AVS.PrivateClouds {};

@operationGroup
@armResourceOperations
interface WorkloadNetworks extends Microsoft.AVS.WorkloadNetworks {
  #suppress "@azure-tools/typespec-azure-core/no-operation-id" "Can not change existing operationId."
  listSegments is Microsoft.AVS.WorkloadNetworkSegments.list;

  #suppress "@azure-tools/typespec-azure-core/no-operation-id" "Can not change existing operationId."
  getSegment is Microsoft.AVS.WorkloadNetworkSegments.get;

  #suppress "@azure-tools/typespec-azure-core/no-operation-id" "Can not change existing operationId."
  #suppress "@azure-tools/typespec-azure-core/invalid-final-state" "MUST CHANGE ON NEXT UPDATE"
  createSegment is Microsoft.AVS.WorkloadNetworkSegments.create;

  #suppress "@azure-tools/typespec-azure-core/no-operation-id" "Can not change existing operationId."
  updateSegment is Microsoft.AVS.WorkloadNetworkSegments.update;

  #suppress "@azure-tools/typespec-azure-core/no-operation-id" "Can not change existing operationId."
  #suppress "@azure-tools/typespec-azure-resource-manager/arm-delete-operation-response-codes"
  deleteSegment is Microsoft.AVS.WorkloadNetworkSegments.deleteSegment;
};

// client
@@clientName(Microsoft.AVS, "Avs", "java");
@@clientName(Microsoft.AVS, "AVS", "python");

// flatten
#suppress "deprecated" "property flatten for SDK backward compatibility"
@@flattenProperty(Azure.ResourceManager.TrackedResource.properties);
#suppress "deprecated" "property flatten for SDK backward compatibility"
@@flattenProperty(Azure.ResourceManager.Foundations.ResourceUpdateModel.properties
);
#suppress "deprecated" "property flatten for SDK backward compatibility"
@@flattenProperty(Azure.ResourceManager.ProxyResource.properties);
#suppress "deprecated" "property flatten for SDK backward compatibility"
@@flattenProperty(Azure.ResourceManager.Foundations.ProxyResourceUpdateModel.properties
);

#suppress "deprecated" "property flatten for SDK backward compatibility"
@@flattenProperty(PrivateCloudUpdate.properties);
#suppress "deprecated" "property flatten for SDK backward compatibility"
@@flattenProperty(ClusterUpdate.properties);
#suppress "deprecated" "property flatten for SDK backward compatibility"
@@flattenProperty(PlacementPolicyUpdate.properties);

// operation
@@friendlyName(Addons.list, "list");
@@friendlyName(PlacementPolicies.list, "list");
@@friendlyName(VirtualMachines.list, "list");
@@friendlyName(HcxEnterpriseSites.list, "list");
@@friendlyName(ScriptExecutions.list, "list");
@@friendlyName(ScriptPackages.list, "list");
@@friendlyName(Datastores.list, "list");
@@friendlyName(Clusters.list, "list");
@@friendlyName(ScriptCmdlets.list, "list");
@@friendlyName(Authorizations.list, "list");
@@friendlyName(CloudLinks.list, "list");
@@friendlyName(GlobalReachConnections.list, "list");
@@friendlyName(WorkloadNetworks.list, "list");

// model
@@clientName(Azure.ResourceManager.CommonTypes.SystemAssignedServiceIdentity,
  "PrivateCloudIdentity"
);
@@clientName(Azure.ResourceManager.CommonTypes.SystemAssignedServiceIdentityType,
  "ResourceIdentityType"
);

@@clientName(VmVmPlacementPolicyProperties, "VmPlacementPolicyProperties");
@@clientName(WorkloadNetworkPublicIP, "WorkloadNetworkPublicIp", "java");
@@clientName(WorkloadNetworkPublicIPProvisioningState,
  "WorkloadNetworkPublicIpProvisioningState",
  "java"
);

// property
@@clientName(Circuit.expressRouteID, "expressRouteId", "java");
@@clientName(Circuit.expressRoutePrivatePeeringID,
  "expressRoutePrivatePeeringId",
  "java"
);
@@clientName(WorkloadNetworkPublicIPProperties,
  "WorkloadNetworkPublicIpProperties",
  "java"
);
@@clientName(WorkloadNetworkPublicIPProperties.publicIPBlock,
  "publicIpBlock",
  "java"
);
