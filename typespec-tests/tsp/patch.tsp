import "@typespec/rest";
import "@azure-tools/typespec-azure-core";
import "@azure-tools/typespec-client-generator-core";
import "@typespec/versioning";

using TypeSpec.Http;
using TypeSpec.Rest;
using Azure.Core;
using Azure.ClientGenerator.Core;
using TypeSpec.Versioning;

@service({
  title: "Patch",
  version: "1.0.0",
})
@useDependency(Azure.Core.Versions.v1_0_Preview_2)
namespace Cadl.Patch;

@resource("resources")
model Resource {
  @visibility("read")
  id: string;

  @key
  @visibility("read")
  name: string;

  description?: string;
  map: Record<InnerModel>;

  longValue?: int64;
  intValue?: int32;
  enumValue?: "a" | "b" | "c";

  @projectedName("json", "wireNameForInnerModelProperty")
  innerModelProperty?: InnerModel;
}

model InnerModel {
  name: string;
}

@doc("This is base model for polymorphic multiple levels inheritance with a discriminator.")
@discriminator("kind")
@resource("resources")
model Fish {
  @visibility("read")
  id: string;

  @key
  @visibility("read")
  name: string;

  age: int32;

  color?: string;
}

@doc("The second level model in polymorphic multiple levels inheritance and it defines a new discriminator.")
@discriminator("sharktype")
model Shark extends Fish {
  kind: "shark";
  sharktype: string;
}

@doc("The second level model in polymorphic multiple levels inheritance which contains references to other polymorphic instances.")
model Salmon extends Fish {
  kind: "salmon";
  friends?: Fish[];
  hate?: Record<Fish>;
  partner?: Fish;
}

@doc("The third level model SawShark in polymorphic multiple levels inheritance.")
model SawShark extends Shark {
  sharktype: "saw";
}

@doc("The third level model GoblinShark in polymorphic multiple levels inheritance.")
model GoblinShark extends Shark {
  sharktype: "goblin";
}

@route("/patch")
interface Patch {
  @patch
  @route("/resource")
  createOrUpdateResource is ResourceOperations.ResourceCreateOrUpdate<Resource>;

  @patch
  @route("/fish")
  createOrUpdateFish is ResourceOperations.ResourceCreateOrUpdate<Fish>;
}