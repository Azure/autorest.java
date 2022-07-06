import {
  logDiagnostics,
  resolvePath,
  Program,
  Diagnostic,
} from "@cadl-lang/compiler";
import { 
  dump, 
} from "js-yaml";
import {
  execFile
} from "child_process";
import {
  CodeModelBuilder,
} from "./code-model-builder.js";

export async function $onEmit(program: Program) {
  const builder = new CodeModelBuilder(program);
  const codeModel = builder.build();
  const namespace = builder.namespace();

  const outputPath = program.compilerOptions.outputPath || "./";
  const codeModelFileName = resolvePath(outputPath, "./code-model.yaml");

  await program.host.writeFile(codeModelFileName, dump(codeModel));

  await new Promise((resolve, reject) => {
    execFile("java", [
      "-jar",
      "node_modules/@azure-tools/java-client-emitter/target/azure-cadl-extension-jar-with-dependencies.jar",
      codeModelFileName,
      resolvePath(outputPath, "java"),
      namespace
    ], (error, stdout, stderr) => {
      if (error) {
        throw error;
      }
      program.logger.info(stdout ? stdout : stderr);
      resolve(stdout ? stdout : stderr);
    });
  });
}
