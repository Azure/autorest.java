import "@typespec/http";
import "@typespec/rest";
import "@typespec/versioning";
import "@azure-tools/typespec-azure-core";

using TypeSpec.Http;
using TypeSpec.Rest;
using TypeSpec.Versioning;
using Azure.Core;
using Azure.Core.Traits;

@service({
  title: "StreamStyleSerialization",
})
@versioned(Versions)
@doc("stream-style-serialization API.")
namespace Cadl.StreamStyleSerialization;

@doc("Azure API versions.")
enum Versions {
  @useDependency(Azure.Core.Versions.v1_0_Preview_2)
  @doc("Preview API version 2023-12-01-preview.")
  v2023_12_01_preview: "2023-12-01-preview",
}

@doc("A inventory base model created for swagger documentation purpose")
model InventoryAsset {}

#suppress "@azure-tools/typespec-azure-core/documentation-required" "Documentation will be added next preview version"
model AsAsset extends InventoryAsset {
  asn?: int64;
  firstSeen?: utcDateTime;
  lastSeen?: utcDateTime;
  count?: int64;
}

#suppress "@azure-tools/typespec-azure-core/documentation-required" "Documentation will be added next preview version"
model AsAssetResource extends AssetResource {
  @doc("The kind of AssetResource")
  kind: "as";

  @doc("asset")
  asset: AsAsset;
}

#suppress "@azure-tools/typespec-azure-core/documentation-required" "Documentation will be added next preview version"
@doc("The items in the current page of results.")
@discriminator("kind")
@resource("assets")
model AssetResource {
  /** Discriminator property for AssetResource. */
  #suppress "@azure-tools/typespec-azure-core/no-string-discriminator" "Existing"
  kind: string;

  @doc("The system generated unique id for the resource.")
  @key("assetId")
  @visibility("read")
  id: string;

  @doc("The caller provided unique name for the resource.")
  name?: string;
}

alias ServiceTraits = NoRepeatableRequests &
  NoConditionalRequests &
  NoClientRequestId;

alias Operations = Azure.Core.ResourceOperations<ServiceTraits>;
@tag("Assets")
@doc("Provides access to inventory assets.")
interface Assets {
  @doc("Retrieve a list of assets for the provided search parameters.")
  listAssetResource is Operations.ResourceList<AssetResource>;
}