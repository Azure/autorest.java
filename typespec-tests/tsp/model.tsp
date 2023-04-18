import "@typespec/rest";
import "@azure-tools/typespec-client-generator-core";

using TypeSpec.Http;

@service({
  title: "Model",
  version: "1.0.0",
})
namespace Cadl.Model;

// input-output
model Resource1 {
  name: string;

  @visibility("read")
  outputData: OutputData;

  @visibility("read")
  outputData2: InputOutputData2;
}

// input-output
model Resource2 {
  name: string;
  data2: InputOutputData2;
}

// output
model Resource3 {
  name: string;
  outputData3: OutputData3;
}

// output
model OutputData {
  data: string;
}

// input-output, due to Resource2
model InputOutputData2 {
  data: string;
}

// output
model OutputData3 {
  data: string;
}

@route("/model")
interface ModelOp {
  @put
  @route("/resource1")
  put1(@body body: Resource1): Resource1;

  @put
  @route("/resource2")
  put2(@body body: Resource2): Resource2;

  @get
  @route("/resource3")
  get3(): Resource3;
}
