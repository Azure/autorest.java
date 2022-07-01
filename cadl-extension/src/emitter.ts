import {
  Program,
  resolvePath,
} from "@cadl-lang/compiler";
import { 
  dump, 
} from "js-yaml";
import {
  CodeModelBuilder,
} from "./code-model-builder.js";

export async function $onEmit(program: Program) {
  const builder = new CodeModelBuilder(program);
  const codeModel = builder.build();
  await program.host.writeFile(resolvePath(program.compilerOptions.outputPath || "", "./code-model.yaml"), dump(codeModel));
}
