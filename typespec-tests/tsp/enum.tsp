import "@typespec/rest";
import "@azure-tools/typespec-azure-core";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;
using Azure.Core;
using Azure.ClientGenerator.Core;

@service({
  title: "EnumService",
})
namespace Cadl.EnumService;

#suppress "@azure-tools/typespec-azure-core/use-extensible-enum" "For testing"
@fixed
enum Color {
  Red,
  Blue,
  Green,
}

union ColorModel {
  string,
  "Red",
  "Blue",
  "Green",
}

#suppress "@azure-tools/typespec-azure-core/use-extensible-enum" "For testing"
@fixed
enum Priority {
  High: 100,
  Low: 0,
}

#suppress "@azure-tools/typespec-azure-core/use-extensible-enum" "For testing"
@fixed
enum Unit {
  Grams: 1,
  KiloGrams: 0.001,
  Milligram: 1000,
}

#suppress "@azure-tools/typespec-azure-core/use-extensible-enum" "For testing"
@fixed
enum OperationStateValues {
  Running,
  Completed,
  Failed,
}

model Operation {
  name: "Read" | "Write";
  best: true;
  age: 50;
  priority: Priority;
  color: ColorModel;
  unit: Unit;
  priorityValue: Priority.Low;
  colorValue: Color.Green;
  colorModelValue: ColorModel.Blue;
  unitValue: Unit.Milligram;
}

@client({
  service: Cadl.EnumService,
  name: "EnumServiceClient",
})
@route("/enum")
interface EnumOp {
  @get
  @route("/color")
  getColor(): Color;

  @get
  @route("/colormodel")
  getColorModel(): ColorModel;

  @post
  @route("operation/colormodel")
  setColorModel(@query color: ColorModel): Operation;

  @post
  @route("operation/priority")
  setPriority(@query priority: Priority): Operation;

  @get
  @route("operation/state/running")
  getRunningOperation(@query state: "Running"): Operation;

  @get
  @route("operation/state")
  getOperation(@query state: OperationStateValues): Operation;

  @post
  @route("operation/stringenumarray")
  setStringEnumArray(
    @query({
      format: "csv",
    })
    colorArray: ColorModel[],

    @query({
      format: "csv",
    })
    colorArrayOpt?: ColorModel[],
  ): string;

  @post
  @route("operation/intenumarray")
  setIntEnumArray(
    @query({
      format: "csv",
    })
    priorityArray: Priority[],

    @query({
      format: "csv",
    })
    priorityArrayOpt?: Priority[],
  ): string;

  @post
  @route("operation/stringarray")
  setStringArray(
    @query({
      format: "csv",
    })
    stringArray: string[],

    @query({
      format: "csv",
    })
    stringArrayOpt?: string[],
  ): string;

  @post
  @route("operation/intarray")
  setIntArray(
    @query({
      format: "csv",
    })
    intArray: int32[],

    @query({
      format: "csv",
    })
    intArrayOpt?: int32[],
  ): string;

  @post
  @route("operation/stringenummulti")
  setStringEnumMulti(
    @query({
      format: "multi",
    })
    colorArray: ColorModel[],

    @query({
      format: "multi",
    })
    colorArrayOpt?: ColorModel[],
  ): string;

  @post
  @route("operation/intenummulti")
  setIntEnumMulti(
    @query({
      format: "multi",
    })
    priorityArray: Priority[],

    @query({
      format: "multi",
    })
    priorityArrayOpt?: Priority[],
  ): string;

  @post
  @route("operation/stringmulti")
  setStringMulti(
    @query({
      format: "multi",
    })
    stringArray: string[],

    @query({
      format: "multi",
    })
    stringArrayOpt?: string[],
  ): string;

  @post
  @route("operation/intmulti")
  setIntMulti(
    @query({
      format: "multi",
    })
    intArray: int32[],

    @query({
      format: "multi",
    })
    intArrayOpt?: int32[],
  ): string;

  @post
  @route("operation/stringenumarrayheader")
  setStringEnumArrayHeader(
    @header({
      format: "csv",
    })
    colorArray: ColorModel[],

    @header({
      format: "csv",
    })
    colorArrayOpt?: ColorModel[],
  ): string;
}
