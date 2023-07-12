import "@typespec/rest";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using Azure.ClientGenerator.Core;

@service({
  title: "Visibility",
  version: "1.0.0",
})
namespace Cadl.Visibility;

model Dog {
  @visibility("read") id: int32;
  @visibility("update") secretName: string;

  // no flags are like specifying all flags at once, so in this case
  // equivalent to @visibility("read", "write")
  name: string;
}

model RoundTripModel {
  name: string;
  @visibility("create", "update") secretName: string;
}

// The spread operator will copy all the properties of Dog into ReadDog,
// and withVisibility will remove any that don't match the current
// visibility setting
@withVisibility("read")
model ReadDog {
  ...Dog;
}

@withVisibility("write")
model WriteDog {
  ...Dog;
}

@operationGroup
interface VisibilityRead {
  @get
  @route("/read")
  get(): Dog;
}

@operationGroup
interface VisibilityWrite {
  @put
  @route("/write")
  create(@body dog: WriteDog): Dog;
}

@route("/visibility")
interface VisibilityOp extends VisibilityRead, VisibilityWrite {
  @post
  @route("/query")
  query(@body dog: ReadDog): Dog;

  @put
  @route("/roundtrip")
  roundtrip(@body body: RoundTripModel): RoundTripModel;
}
