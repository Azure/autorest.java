import {
  resolvePath,
  getNormalizedAbsolutePath,
  Program
} from "@cadl-lang/compiler";
import { 
  dump
} from "js-yaml";
import { 
  promisify
} from 'util';
import {
  execFile
} from "child_process";
import {
  mkdir
} from "fs";
import {
  CodeModelBuilder
} from "./code-model-builder.js";

export async function $onEmit(program: Program) {
  const builder = new CodeModelBuilder(program);
  const codeModel = builder.build();
  const namespace = builder.namespace();

  const outputPath = program.compilerOptions.outputPath ?? getNormalizedAbsolutePath("./cadl-output", undefined);
  const codeModelFileName = resolvePath(outputPath, "./code-model.yaml");

  await promisify(mkdir)(outputPath).catch(err => {
    if (err.code !== "EISDIR" && err.code !== "EEXIST") {
      throw err;
    }
  });

  await program.host.writeFile(codeModelFileName, dump(codeModel));

  await promisify(execFile)("java", [
    "-jar",
    "node_modules/@azure-tools/java-client-emitter/target/azure-cadl-extension-jar-with-dependencies.jar",
    codeModelFileName,
    resolvePath(outputPath, "java"),
    namespace
  ]);
}
